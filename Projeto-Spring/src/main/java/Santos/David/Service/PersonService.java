package Santos.David.Service;

import Santos.David.Exception.ResourceNotFoundException;
import Santos.David.Service.repository.PersonRepository;
import Santos.David.data.dto.v1.PersonDTO;
import static Santos.David.mapper.ObjectMapper.parseListObjects;
import static Santos.David.mapper.ObjectMapper.parseObject;

import Santos.David.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/* A Notação "@Service" nos diz que Essa classe é um componente de
   regra de negócio gerenciado pelo Spring. */
@Service
public class PersonService {

    /* Logger é uma ferramenta para registrar o que acontece na sua aplicação. */
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    /* Criamos um metodo do tipo lista, onde enquanto "i" for menor
     * que "8" retorne uma pessoa. */
    public List<PersonDTO> findAll(){

        logger.info("ACHAMO TODO MUNDO AQUI CARAI!");

        return parseListObjects(repository.findAll(), PersonDTO.class);

    }



    /* Esse meu metodo encontra alguem atravez do id, inicia rodando um looger.info
    * com um mensagem caso um pessoa seja encontrada, logo abaixo ele cria/estrutura
    * essa pessoa e por fim ele nos retorna ela.  */
    public PersonDTO findById(Long id) {

        logger.info("ACHAMO ALGUÉM CARAI!");

        var entity = repository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("No records found for this ID"));

        return parseObject(entity, PersonDTO.class);
    }



    /* Metodo onde retornamos uma pessoa. */
    public PersonDTO create(PersonDTO person) {

        var entity = parseObject(person, Person.class);

        logger.info("CRIAMOS ALGUEM CARAI!");

        Person saved = repository.save(entity);

        return parseObject(saved, PersonDTO.class);
    }

    /* Metodo para alterar os dados de uma pessoa */
    public PersonDTO update (PersonDTO person) {


        logger.info("MUDAMOS ALGUEM CARAI!");

        Person entity = (Person) repository.findById(person.getId()).orElseThrow(() ->  new ResourceNotFoundException("No records found for this ID"));


        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(repository.save(entity),PersonDTO.class);
    }

    /* Metodo que deleta uma pessoa */
    public void delete(Long id) {

        logger.info("APAGAMOS ALGUEM !!");

        Person entity = (Person) repository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }




}

