package Santos.David.services;

import Santos.David.Exception.RequiredObjectIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import Santos.David.Exception.ResourceNotFoundException;
import Santos.David.services.repository.PersonRepository;
import Santos.David.Controllers.PersonController;
import org.springframework.stereotype.Service;
import Santos.David.data.dto.PersonDTO;
import Santos.David.model.Person;
import java.util.logging.Logger;
import java.util.List;
import static Santos.David.mapper.ObjectMapper.parseListObjects;
import static Santos.David.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/* A Notação "@Service" nos diz que Essa classe é um componente de
   regra de negócio gerenciado pelo Spring. */
@Service
public class PersonService {


    /* Logger é uma ferramenta para registrar o que acontece na sua aplicação. */
    private final Logger logger = Logger.getLogger(PersonService.class.getName());


    @Autowired
    PersonRepository repository;


    /* Esse meu metodo encontra alguem atravez do id, inicia rodando um looger.info
     * com um mensagem caso um pessoa seja encontrada, logo abaixo ele cria/estrutura
     * essa pessoa e por fim ele nos retorna ela.  */
    public PersonDTO findById(Long id) {

        logger.info("ACHAMO ALGUÉM CARAI!");

        var entity = repository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("No records found for this ID"));

        var dto =  parseObject(entity, PersonDTO.class);

        addHateoasLinks(dto);

        return  dto;
    }


    /*
     * Metodo para listas todos os usuarios.
     */
    public List<PersonDTO> findAll(){

        logger.info("ACHAMO TODO MUNDO AQUI CARAI!");

        var persons =  parseListObjects(repository.findAll(), PersonDTO.class);

        /*
         * Sera iterado sobre cada pessoa e adicionado um link
         */
        persons.forEach(p -> {
            addHateoasLinks(p);
        });

        return persons;
    }



    /* Metodo onde retornamos uma pessoa. */
    public PersonDTO create(PersonDTO person) {

        if(person == null)throw new RequiredObjectIsNullException();

        var entity = parseObject(person, Person.class);

        logger.info("CRIAMOS ALGUEM CARAI!");

        Person saved = repository.save(entity);

        var dto =  parseObject(saved, PersonDTO.class);

        addHateoasLinks(dto);

        return  dto;
    }


    /* Metodo para alterar os dados de uma pessoa */
    public PersonDTO update (PersonDTO person) {

        if(person == null)throw new RequiredObjectIsNullException();

        logger.info("MUDAMOS ALGUEM CARAI!");

        Person entity = (Person) repository.findById(person.getId()).orElseThrow(() ->  new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto =  parseObject(repository.save(entity),PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }


    /* Metodo que deleta uma pessoa */
    public void delete(Long id) {

        logger.info("APAGAMOS ALGUEM !!");

        Person entity = (Person) repository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }


    /*
     * Metodo do Hateoas para inserir links.
     */
    private static void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));

        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));

        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));

        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));

        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }



}

