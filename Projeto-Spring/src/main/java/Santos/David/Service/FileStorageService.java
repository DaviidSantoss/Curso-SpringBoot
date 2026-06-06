package Santos.David.Service;

import Santos.David.Exception.FileStorageException;
import Santos.David.config.FileStorageConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private static  final Logger logger = LoggerFactory.getLogger(FileStorageService.class);


    /* Path = classe Java que representa um CAMINHO no sistema de arquivos
     *  é como um "endereço" de pasta/arquivo no HD ex: "C:/uploads",
     *  final = esse caminho nunca muda após ser definido no construtor. */
    private final Path fileStorageLocation;

    public FileStorageService(FileStorageConfig fileStorageConfig) {

        /* Paths.get() → converte a String do yml em um objeto Path,
        *  ex: "uploads" vira um Path navegável. */
        Path path = Paths.get(fileStorageConfig
                              .getUpload_dir())
                              .toAbsolutePath() // -> converte para caminho ABSOLUTO
                              .normalize();// -> remove redundâncias do caminho deixa
                                          // o caminho limpo e sem ambiguidades

        this.fileStorageLocation = path;

        try {

            logger.info("Creating Directories");
            /* createDirectories() → cria a pasta de upload se não existir */
            Files.createDirectories(this.fileStorageLocation);
        }
        catch (Exception e) {

            logger.error("Could not create the directory where files be stored!");
            throw new FileStorageException("Could not create the directory where files be stored!",e);
        }
    }

    /* MultipartFile = tipo Spring para arquivos enviados via HTTP,
    *   é o arquivo que chegou no request do usuário. */
    public String storeFile(MultipartFile file){

        /* → getOriginalFilename() → pega o nome original do arquivo.
        * → StringUtils.cleanPath() → limpa o nome do arquivo remove
        * caracteres problemáticos e normaliza barras ex: "../../etc/passwd"
        * vira um nome seguro. */
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")){
                logger.error("Sorry FileName contains a Invalid path Sequence " + fileName);
                throw new FileStorageException("Sorry FileName contains a Invalid path Sequence " + fileName);
            }

            logger.info("Saving file in disc.");
            /* → resolve() → junta o diretório base com o nome do arquivo,
            * → ex: "/home/david/uploads" + "curriculo.pdf"  = "/home/david/uploads/curriculo.pdf" */
            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            /*  → file.getInputStream() → lê o conteúdo binário do arquivo
            *   → Files.copy() → copia esse conteúdo para o destino
            *   → StandardCopyOption.REPLACE_EXISTING → se já existir um
            *     arquivo com esse nome, substitui. */
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        }
        catch (Exception e) {
            logger.error("Could not store file" + fileName +". Please try Again.");
            throw new FileStorageException("Could not store file" + fileName + ". Please try Again.",e);
        }
    }
}
