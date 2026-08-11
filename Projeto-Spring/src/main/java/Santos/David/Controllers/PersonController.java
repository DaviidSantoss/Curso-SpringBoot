package Santos.David.Controllers;

import Santos.David.Controllers.docs.PersonControllerDocs;
import Santos.David.File.exporter.MidiaTypes;
import Santos.David.Service.PersonService;
import Santos.David.data.dto.PersonDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/person")
@Tag(name = "People",description = "Endpoints for Managing People")
public class PersonController implements PersonControllerDocs {


    /*Com @Autowired estamos dizendo Spring injete automaticamente
      uma instância de PersonService aqui para mim."  */
    @Autowired
    private PersonService service;


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public PersonDTO findById(@PathVariable("id") Long id) {
        var person  = service.findById(id);
        return person;
    }


    /* Mapeia requisições HTTP do tipo GET.
     *  Get é utilizando quando queremos "Encontrar" algum objeto.
     *
     * produces = define que a respota será no formato JSON. */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<PagedModel<EntityModel<PersonDTO>>> findAll(
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "size",defaultValue = "12") Integer size,
            @RequestParam(value = "direction",defaultValue = "asc") String direction
    )
    {
        /* "o que o usuário passou é 'desc' (ignorando maiúsculas)?", sim → ordena decrescente
        *   não → ordena crescente (default) */
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC: Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size,Sort.by(sortDirection,"firstName"));

        return ResponseEntity.ok(service.findAll(pageable));
    }


    @GetMapping(value = "/exportPage",produces = {MidiaTypes.APPLICATION_XLSX_VALUE, MidiaTypes.APPLICATION_CSV_VALUE})
    @Override
    public  ResponseEntity<Resource> exportPage(
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "size",defaultValue = "12") Integer size,
            @RequestParam(value = "direction",defaultValue = "asc") String direction,
            HttpServletRequest request
    )
    {
        /* "o que o usuário passou é 'desc' (ignorando maiúsculas)?", sim → ordena decrescente
        *   não → ordena crescente (default) */
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC: Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size,Sort.by(sortDirection,"firstName"));

        String acceptHeader = request.getHeader(HttpHeaders.ACCEPT);

        Resource file = service.exportPage(pageable, acceptHeader);

        var contentType =  acceptHeader != null ? acceptHeader : "application/octet-stream";
        var fileExtension = MidiaTypes.APPLICATION_XLSX_VALUE.equalsIgnoreCase(acceptHeader) ? ".xlsx" : "csv";
        var fileName = "people_exported" + fileExtension;

        return ResponseEntity.ok()

                .contentType(MediaType.parseMediaType(contentType))

                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment=; filename=\"" + fileName + "\"")
                .body(file);
    }

    @GetMapping(value = "/findPeopleByName/{firstName}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedModel<EntityModel<PersonDTO>>> findByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "size",defaultValue = "12") Integer size,
            @RequestParam(value = "direction",defaultValue = "asc") String direction
    )

    {
        /* "o que o usuário passou é 'desc' (ignorando maiúsculas)?", sim → ordena decrescente
        *   não → ordena crescente (default) */
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC: Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size,Sort.by(sortDirection,"firstName"));

        return ResponseEntity.ok(service.findByName(firstName,pageable));
    }


    /* Mapeia requisições HTTP do tipo POST para este metodo.
     *  POST é usado quando queremos CRIAR um novo recurso.
     *
     *  produces = define que a resposta será no formato JSON
     *  consumes = define que o metodo espera receber JSON
     *
     * */
//    @CrossOrigin(origins = {"http://localhost:8080","https://www.instagram.com/david.snt0s/"})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public PersonDTO create(@RequestBody PersonDTO person) {
        return  service.create(person);
   }

   @PostMapping(value = "massCreation",produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
   public List<PersonDTO> massCreation(@RequestParam("file") MultipartFile file) {
        return  service.massCreation(file);
   }


    /* Requisição HTTP PUT utilizada para alterar os dados de um objeto. */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public PersonDTO update(@RequestBody PersonDTO person) {

        return  service.update(person);
   }


    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public PersonDTO disablePerson(@PathVariable("id") Long id){

        return service.disablePerson(id);
    }


    /* Requisição HTTP DELETE utilizada para deltar algum objeto. */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
