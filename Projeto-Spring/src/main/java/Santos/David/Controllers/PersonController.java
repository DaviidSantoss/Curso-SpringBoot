package Santos.David.Controllers;

import Santos.David.Controllers.docs.PersonControllerDocs;
import Santos.David.Service.PersonService;
import Santos.David.data.dto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
        person.setBirthDate(new Date());
//      person.setPhoneNumber("123456789");
        person.setPhoneNumber("");
        person.setLastName(null);
        person.setSensitiveData("123");
        return person;
    }

    /* Mapeia requisições HTTP do tipo GET.
     *  Get é utilizando quando queremos "Encontrar" algum objeto.
     *
     * produces = define que a respota será no formato JSON. */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public List<PersonDTO> findAll() {

        return service.findAll();
    }


    /* Mapeia requisições HTTP do tipo POST para este metodo.
     *  POST é usado quando queremos CRIAR um novo recurso.
     *
     *  produces = define que a resposta será no formato JSON
     *  consumes = define que o metodo espera receber JSON
     *
     * */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public PersonDTO create(@RequestBody PersonDTO person) {

        return  service.create(person);
   }

    /* Requisição HTTP PUT utilizada para alterar os dados de um objeto. */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public PersonDTO update(@RequestBody PersonDTO person) {

        return  service.update(person);
   }

    /* Requisição HTTP DELETE utilizada para deltar algum objeto. */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
