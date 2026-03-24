package Santos.David.Service;

import Santos.David.Exception.RequiredObjectIsNullException;
import Santos.David.Service.repository.PersonRepository;
import Santos.David.data.dto.PersonDTO;
import Santos.David.model.Person;
import Santos.David.unitests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/* Define quantas instâncias da classe de teste são criadas */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

/* Isso ativa o Mockito dentro do JUnit 5. */
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    /* @InjectMocks cria o objeto real e injeta os mocks dentro dele */
    @InjectMocks
    private PersonService service;

    /* O @Mock cria um objeto falso (simulado) */
    @Mock
    PersonRepository repository;


    /* Executa antes de cada teste */
    @BeforeEach
    void setUp() {

        /* Cria um gerador de dados fake */
        input = new MockPerson();

        /* Inicializa os mocks manualmente (ativa @Mock e @InjectMocks) */
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void findById() {

        /* Cria um objeto Person fake usando sua classe de mock */
        Person person = input.mockEntity(1);

        person.setId(1);

        /* Simula o comportamento do repository,Quando buscar ID 1 → retorna esse Person fake
        * não acessa o banco de verdade. */
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        /* Chama o metodo real do Service, aqui é o foco do teste */
        var result = service.findById(1L);

        /* Verifica se o retorno não é nulo */
        assertNotNull(result);

        /* Verifica se o ID foi preenchido */
        assertNotNull(result.getId());

        /* Verifica se os links HATEOAS foram adicionados */
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
            && link.getHref().endsWith("/person/1") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/person") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/person") && link.getType().equals("POST")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/person") && link.getType().equals("PUT")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/person/1") && link.getType().equals("DELETE")
        ));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }


    @Test
    void create() {

        Person person = input.mockEntity(1);
        Person persisted =person;
        persisted.setId(1);

        PersonDTO dto = input.mockDTO(1);

        when(repository.save(person)).thenReturn((persisted));

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/person/1") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/person") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/person") && link.getType().equals("POST")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/person") && link.getType().equals("PUT")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/person/1") && link.getType().equals("DELETE")
        ));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreateWithNullPerson(){

        /* Executa o metodo create(null),Espera que lance RequiredObjectIsNullException
        * Se NÃO lançar → o teste falha, se lançar → captura a exceção na variável "exception". */
        Exception exception = assertThrows(RequiredObjectIsNullException.class,() -> {service.create(null);});

        /* Mensagem esperada do erro */
        String expectedMessage = "It is not allowed to persist a null object";

        /* Pega a mensagem real da exceção lançada, que definimos na classe
        * "RequiredObjectIsNullException". */
        String actualMessage = exception.getMessage();

        /* Verifica se a mensagem contém o texto esperado */
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {

        Person person = input.mockEntity(1);
        Person persisted =person;
        persisted.setId(1);

        PersonDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn((persisted));

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/person/1") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/person") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/person") && link.getType().equals("POST")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/person") && link.getType().equals("PUT")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/person/1") && link.getType().equals("DELETE")
        ));

        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdateWithNullPerson(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class,() -> {service.update(null);});

        String expectedMessage = "It is not allowed to persist a null object";

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void delete() {

        Person person = input.mockEntity(1);
        person.setId(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));

        /* Executa o metodo real que queremos testar */
        service.delete(1L);

        /* Verifica se o metodo findById foi chamado exatamente 1 vez */
        verify(repository,times(1)).findById(anyLong());

        /* Verifica se o delete foi chamado exatamente 1 vez */
        verify(repository,times(1)).delete(any(Person.class));

        /* Verifica se NÃO houve nenhuma outra interação com o repository */
        verifyNoMoreInteractions(repository);
    }


    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        List<PersonDTO> people = service.findAll();

        assertNotNull(people);
        assertEquals(14,people.size());

        var personOne = people.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getId());
        assertNotNull(personOne.getLinks());

        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/person/1") && link.getType().equals("GET")
        ));

        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/person") && link.getType().equals("GET")
        ));

        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/person") && link.getType().equals("POST")
        ));

        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/person") && link.getType().equals("PUT")
        ));

        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/person/1") && link.getType().equals("DELETE")
        ));

        assertEquals("Address Test1", personOne.getAddress());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Female", personOne.getGender());

        var personFour = people.get(4);

        assertNotNull(personFour);
        assertNotNull(personFour.getId());
        assertNotNull(personFour.getLinks());

        assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/person/4") && link.getType().equals("GET")
        ));

        assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/person") && link.getType().equals("GET")
        ));

        assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/person") && link.getType().equals("POST")
        ));

        assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/person") && link.getType().equals("PUT")
        ));

        assertNotNull(personFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/person/4") && link.getType().equals("DELETE")
        ));

        assertEquals("Address Test4", personFour.getAddress());
        assertEquals("First Name Test4", personFour.getFirstName());
        assertEquals("Last Name Test4", personFour.getLastName());
        assertEquals("Male", personFour.getGender());

        var personSeven= people.get(7);

        assertNotNull(personSeven);
        assertNotNull(personSeven.getId());
        assertNotNull(personSeven.getLinks());

        assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/person/7") && link.getType().equals("GET")
        ));

        assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/person") && link.getType().equals("GET")
        ));

        assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/person") && link.getType().equals("POST")
        ));

        assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/person") && link.getType().equals("PUT")
        ));

        assertNotNull(personSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/person/7") && link.getType().equals("DELETE")
        ));

        assertEquals("Address Test7", personSeven.getAddress());
        assertEquals("First Name Test7", personSeven.getFirstName());
        assertEquals("Last Name Test7", personSeven.getLastName());
        assertEquals("Female", personSeven.getGender());


    }
}