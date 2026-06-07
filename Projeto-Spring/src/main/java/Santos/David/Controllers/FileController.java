package Santos.David.Controllers;

import Santos.David.Controllers.docs.FileControllerDocs;
import Santos.David.Service.FileStorageService;
import Santos.David.data.dto.UploadFileResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/file/v1")
public class FileController implements FileControllerDocs {

    private static  final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService service;

    @PostMapping("/uploadFile")
    @Override
    public UploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file) {
        var fileName = service.storeFile(file);

        var fileDownloadUri =
                ServletUriComponentsBuilder // ->classe Spring que constrói URIs baseada na requisição HTTP
                .fromCurrentContextPath() // -> pega o endereço base da aplicação automaticamente
                .path("/api/file/v1/downloadFile/") // -> adiciona o caminho do endpoint de download
                        .path(fileName) // -> adiciona o nome do arquivo no final ex: "curriculo.pdf"
                        .toUriString(); // ->  converte tudo para uma String http://localhost:8080/api/file/v1/downloadFile/curriculo.pdf

        return new UploadFileResponseDTO(fileName, // → nome do arquivo
                                         fileDownloadUri,// → link para download
                                         file.getContentType(),// → tipo do arquivo ex: "application/pdf"
                                         file.getSize());// → tamanho em bytes
    }

    @PostMapping("/uploadMultipleFile")
    @Override
    /* → MultipartFile[] → array de arquivos enviados pelo cliente
    *  [] = vários arquivos de uma vez ao invés de um só */
    public List<UploadFileResponseDTO> uploadMultipleFile(MultipartFile[] files) {

        /* → Arrays.asList() → converte o array [] em uma List
        *   precisamos disso pois array não tem .stream() direto
        *   MultipartFile[] vira List<MultipartFile> */
        return Arrays.asList(files)
                .stream() // permite processar cada arquivo um por um
                .map(file -> uploadFile(file))// para CADA arquivo na lista chama o uploadFile() que já fizemos
                .collect(Collectors.toList());// coleta todos os resultados numa List
    }

    /* o ":.+" é uma regex que diz: "aceita QUALQUER caractere incluindo ponto"
    * "curriculo.pdf" funciona. */
    @GetMapping("/downloadFile/{fileName:.+}")
    @Override
    /* Recebe o nome do arquivo pela URL, localiza no disco e envia para o cliente fazer o download. */
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        /* localiza o arquivo no disco e retorna como Resource */
        Resource resource = service.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext()//  pega o contexto do servidor
                         .getMimeType(resource.getFile()// detecta automaticamente o tipo do arquivo
                                  .getAbsolutePath());//  pelo caminho absoluto
        }catch (Exception e){
            logger.error("Could not determine file type!");
        }

        if (contentType == null){
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                        // informa ao navegador qual o tipo do arquivo
                        // navegador usa isso para saber como tratar o arquivo
                        .contentType(MediaType.parseMediaType(contentType))

                        //CONTENT_DISPOSITION → instrui o navegador a BAIXAR
                        //o arquivo ao invés de abrir no browser,"attachment" = forçar download
                        //filename = nome que o arquivo vai ter ao ser baixado
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment=; filename=\"" +resource.getFilename() + "\"")
                        .body(resource);// envia o arquivo em streaming para o cliente
    }
}
