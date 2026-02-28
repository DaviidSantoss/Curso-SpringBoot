package Santos.David.Controllers;

import Santos.David.Service.PersonService;
import Santos.David.data.dto.PersonDTO;
import Santos.David.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    /*Com @Autowired estamos dizendo Spring injete automaticamente
      uma instância de PersonService aqui para mim."  */
    @Autowired
    private PersonService service;

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO findById(@PathVariable("id") Long id) {

        return service.findById(id);

    }

    /* Mapeia requisições HTTP do tipo GET.
    *  Get é utilizando quando queremos "Encontrar" algum objeto.
    *
    * produces = define que a respota será no formato JSON. */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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
        @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
   public PersonDTO create(@RequestBody PersonDTO person) {

        return  service.create(person);
   }

   /* Requisição HTTP PUT utilizada para alterar os dados de um objeto. */
   @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)

   public PersonDTO update(@RequestBody PersonDTO person) {

        return  service.update(person);
   }

   /* Requisição HTTP DELETE utilizada para deltar algum objeto. */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

       service.delete(id);
       return ResponseEntity.noContent().build();

    }
}
