package Santos.David.Service;

import Santos.David.Exception.RequiredObjectIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import Santos.David.Exception.ResourceNotFoundException;
import Santos.David.Service.repository.PersonRepository;
import Santos.David.Controllers.PersonController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import Santos.David.data.dto.PersonDTO;
import Santos.David.model.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

import static Santos.David.mapper.ObjectMapper.parseListObjects;
import static Santos.David.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/* A Notação "@Service" nos diz que Essa classe é um componente de
   regra de negócio gerenciado pelo Spring. */
@Service
public class PersonService {

    @Autowired
    PagedResourcesAssembler<PersonDTO> assembler;


    /* Logger é uma ferramenta para registrar o que acontece na sua aplicação. */
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    private Person verifyEnableAndId(Long id) {

        var entity = repository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("No records found for this ID"));

        if (!entity.getEnabled()) {
            throw new ResourceNotFoundException("Person is disabled.");
        }

        return entity;
    }


    @Autowired
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }


    /* Esse meu metodo encontra alguem atravez do id, inicia rodando um looger.info
     * com um mensagem caso um pessoa seja encontrada, logo abaixo ele cria/estrutura
     * essa pessoa e por fim ele nos retorna ela.  */
    public PersonDTO findById(Long id) {

        logger.info("Found a person with ID: " + id);

        var entity = verifyEnableAndId(id);
        var dto =  parseObject(entity, PersonDTO.class);

        addHateoasLinks(dto);

        return  dto;
    }


    /*
     * Metodo para listas todos os usuarios.
     */
    public PagedModel<EntityModel<PersonDTO>> findAll(Pageable pageable){

        logger.info("Everybody Person has been found.");

        var people = repository.findAll(pageable);

        var peopleWithLinks = people.map(person -> {

            var dto =  parseObject(person, PersonDTO.class);

            addHateoasLinks(dto);

            return  dto;

        });

        /* 1. Cria o link "self" apontando para o próprio endpoint */
        Link findAllLink = linkTo(methodOn(PersonController.class)
                            .findAll(
                                    pageable.getPageNumber(), //página atual
                                    pageable.getPageSize(),   // tamanho da página
                                    String.valueOf(pageable.getSort()))) // direção
                                .withSelfRel(); // → withSelfRel() = esse link aponta para SI MESMO

        /* 2. Converte o Page<> para PagedModel<> com os links HAL
        *  assembler  = PagedResourcesAssembler (injetado no Service)
        *  toModel()  = transforma Page<> em PagedModel<> com links  */
        return assembler.toModel(peopleWithLinks, findAllLink);
    }

    public PagedModel<EntityModel<PersonDTO>> findByName(String firstName,Pageable pageable){

        logger.info("Find People by Name");

        var people = repository.findPeopleByName(firstName,pageable);

        var peopleWithLinks = people.map(person -> {

            var dto =  parseObject(person, PersonDTO.class);

            addHateoasLinks(dto);

            return  dto;

        });

        /* 1. Cria o link "self" apontando para o próprio endpoint */
        Link findAllLink = linkTo(methodOn(PersonController.class)
                            .findAll(
                                    pageable.getPageNumber(), //página atual
                                    pageable.getPageSize(),   // tamanho da página
                                    String.valueOf(pageable.getSort()))) // direção
                                .withSelfRel(); // → withSelfRel() = esse link aponta para SI MESMO

        /* 2. Converte o Page<> para PagedModel<> com os links HAL
        *  assembler  = PagedResourcesAssembler (injetado no Service)
        *  toModel()  = transforma Page<> em PagedModel<> com links  */
        return assembler.toModel(peopleWithLinks, findAllLink);
    }



    /* Metodo onde retornamos uma pessoa. */
    public PersonDTO create(PersonDTO person) {

        if (person == null) throw new RequiredObjectIsNullException();

        /* Metodo para converter PersonDTO em Person.  */
        var people = parseObject(person, Person.class);

        logger.info("Person Created.");

        Person saved = repository.save(people);

        var dto =  parseObject(saved, PersonDTO.class);

        addHateoasLinks(dto);

        return  dto;
    }


    /* Metodo para alterar os dados de uma pessoa */
    public PersonDTO update (PersonDTO person) {

        if (person == null) throw new RequiredObjectIsNullException();

        /* variável que verifica o id e verifica se a pessoa
         * está ativa. */
         var entity = verifyEnableAndId(person.getId());

        logger.info("Person updated successfully.");

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto =  parseObject(repository.save(entity),PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }


    /* Metodo que deleta uma pessoa. */
    public void delete(Long id) {

        logger.info("Person has been Delete.");

        var entity = verifyEnableAndId(id);

        repository.delete(entity);
    }

    /* Metodo para desabilitar uma pessoa e não apaga-la do banco de dados,a
    * recuperação posteriormente ficará mais facíl pois com a desabilitação
    * os dados ainda permanecem no banco, isso se chama soft delete. */
    @Transactional
    public PersonDTO disablePerson(Long id) {


        logger.info("Person Disabled.");

        /* Validação se o id da pessoa foi encontrado */
       repository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("No records found for this ID"));

        repository.disablePerson(id);

        /* Pegamos a entidade que foi validada acima. */
        var entity= repository.findById(id).get();

        var dto =  parseObject(entity,PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }


    /*
     * Metodo do Hateoas para inserir links.
     */
    private static void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));

        dto.add(linkTo(methodOn(PersonController.class).findAll(1,12,"asc")).withRel("findAll").withType("GET"));

        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));

        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));

        dto.add(linkTo(methodOn(PersonController.class).disablePerson(dto.getId())).withRel("disable").withType("PATCH"));

        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }



}

