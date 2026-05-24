package Santos.David.Service;

import Santos.David.Exception.RequiredObjectIsNullException;
import Santos.David.Service.repository.BookRepository;
import Santos.David.data.dto.BookDTO;
import Santos.David.model.Book;
import Santos.David.unitests.mapper.MockBook;
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
class BookServiceTest {

    MockBook input;

    /* @InjectMocks cria o objeto real e injeta os mocks dentro dele */
    @InjectMocks
    private BookService service;

    /* O @Mock cria um objeto falso (simulado) */
    @Mock
    BookRepository repository;


    /* Executa antes de cada teste */
    @BeforeEach
    void setUp() {

        /* Cria um gerador de dados fake */
        input = new MockBook();

        /* Inicializa os mocks manualmente (ativa @Mock e @InjectMocks) */
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void findById() {

        /* Cria um objeto Book fake usando sua classe de mock */
        Book book = input.mockEntity(1);

        book.setId(1);

        /* Simula o comportamento do repository,Quando buscar ID 1 → retorna esse Book fake
        * não acessa o banco de verdade. */
        when(repository.findById(1L)).thenReturn(Optional.of(book));

        /* Chama o metodo real do Service, aqui é o foco do teste */
        var result = service.findById(1L);

        /* Verifica se o retorno não é nulo */
        assertNotNull(result);

        /* Verifica se o ID foi preenchido */
        assertNotNull(result.getId());

        /* Verifica se os links HATEOAS foram adicionados */
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
            && link.getHref().endsWith("/book/1") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/book") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/book") && link.getType().equals("POST")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/book") && link.getType().equals("PUT")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/book/1") && link.getType().equals("DELETE")
        ));

        assertEquals("Title Teste1", result.getTitle());
        assertEquals(book.getPrice(), result.getPrice());
        assertEquals("Author Teste1", result.getAuthor());
        assertEquals(book.getLaunch_date(), result.getLaunch_date());


    }


    @Test
    void create() {

        Book book = input.mockEntity(1);
        Book persisted = book;
        persisted.setId(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.save(any(Book.class))).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/book/1") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/book") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/book") && link.getType().equals("POST")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/book") && link.getType().equals("PUT")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/book/1") && link.getType().equals("DELETE")
        ));

        assertEquals("Title Teste1", result.getTitle());
        assertEquals(book.getPrice(), result.getPrice());
        assertEquals("Author Teste1", result.getAuthor());
        assertEquals(book.getLaunch_date(), result.getLaunch_date());
    }

    @Test
    void testCreateWithNullBook(){

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

        Book book = input.mockEntity(1);
        Book persisted = book;
        persisted.setId(1L);

        BookDTO dto = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(book));
        when(repository.save(any(Book.class))).thenReturn(persisted);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
                && link.getHref().endsWith("/book/1") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
                && link.getHref().endsWith("/book") && link.getType().equals("GET")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
                && link.getHref().endsWith("/book") && link.getType().equals("POST")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
                && link.getHref().endsWith("/book") && link.getType().equals("PUT")
        ));

        assertNotNull(result.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
                && link.getHref().endsWith("/book/1") && link.getType().equals("DELETE")
        ));


        assertEquals("Title Teste1", result.getTitle());
        assertEquals(book.getPrice(), result.getPrice());
        assertEquals("Author Teste1", result.getAuthor());
        assertEquals(book.getLaunch_date(), result.getLaunch_date());
    }

    @Test
    void testUpdateWithNullBook(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class,() -> {service.update(null);});

        String expectedMessage = "It is not allowed to persist a null object";

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void delete() {

        Book book = input.mockEntity(1);
        book.setId(1);

        when(repository.findById(1L)).thenReturn(Optional.of(book));

        /* Executa o metodo real que queremos testar */
        service.delete(1L);

        /* Verifica se o metodo findById foi chamado exatamente 1 vez */
        verify(repository,times(1)).findById(anyLong());

        /* Verifica se o delete foi chamado exatamente 1 vez */
        verify(repository,times(1)).delete(any(Book.class));

        /* Verifica se NÃO houve nenhuma outra interação com o repository */
        verifyNoMoreInteractions(repository);
    }


//    @Test
//    void findAll() {
//        List<Book> list = input.mockEntityList();
//
//        when(repository.findAll()).thenReturn(list);
//
//        List<BookDTO> book = service.findAll();
//
//        assertNotNull(book);
//        assertEquals(14,book.size());
//
//        var BookOne = book.get(1);
//
//        assertNotNull(BookOne);
//        assertNotNull(BookOne.getId());
//        assertNotNull(BookOne.getLinks());
//
//        assertNotNull(BookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
//                && link.getHref().endsWith("/book/1") && link.getType().equals("GET")
//        ));
//
//        assertNotNull(BookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
//                && link.getHref().endsWith("/book") && link.getType().equals("GET")
//        ));
//
//        assertNotNull(BookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
//                && link.getHref().endsWith("/book") && link.getType().equals("POST")
//        ));
//
//        assertNotNull(BookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
//                && link.getHref().endsWith("/book") && link.getType().equals("PUT")
//        ));
//
//        assertNotNull(BookOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
//                && link.getHref().endsWith("/book/1") && link.getType().equals("DELETE")
//        ));
//
//        assertEquals("Title Teste1", BookOne.getTitle());
//        assertEquals(BookOne.getPrice(), BookOne.getPrice());
//        assertEquals("Author Teste1", BookOne.getAuthor());
//        assertEquals(BookOne.getLaunch_date(), BookOne.getLaunch_date());
//
//
//        var bookFour = book.get(4);
//
//        assertNotNull(bookFour);
//        assertNotNull(bookFour.getId());
//        assertNotNull(bookFour.getLinks());
//
//        assertNotNull(bookFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
//                && link.getHref().endsWith("/book/4") && link.getType().equals("GET")
//        ));
//
//        assertNotNull(bookFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
//                && link.getHref().endsWith("/book") && link.getType().equals("GET")
//        ));
//
//        assertNotNull(bookFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
//                && link.getHref().endsWith("/book") && link.getType().equals("POST")
//        ));
//
//        assertNotNull(bookFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
//                && link.getHref().endsWith("/book") && link.getType().equals("PUT")
//        ));
//
//        assertNotNull(bookFour.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
//                && link.getHref().endsWith("/book/4") && link.getType().equals("DELETE")
//        ));
//
//        assertEquals("Title Teste1", BookOne.getTitle());
//        assertEquals(BookOne.getPrice(), BookOne.getPrice());
//        assertEquals("Author Teste1", BookOne.getAuthor());
//        assertEquals(BookOne.getLaunch_date(), BookOne.getLaunch_date());
//
//        var bookSeven= book.get(7);
//
//        assertNotNull(bookSeven);
//        assertNotNull(bookSeven.getId());
//        assertNotNull(bookSeven.getLinks());
//
//        assertNotNull(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")
//                && link.getHref().endsWith("/book/7") && link.getType().equals("GET")
//        ));
//
//        assertNotNull(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")
//                && link.getHref().endsWith("/book") && link.getType().equals("GET")
//        ));
//
//        assertNotNull(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")
//                && link.getHref().endsWith("/book") && link.getType().equals("POST")
//        ));
//
//        assertNotNull(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")
//                && link.getHref().endsWith("/book") && link.getType().equals("PUT")
//        ));
//
//        assertNotNull(bookSeven.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")
//                && link.getHref().endsWith("/book/7") && link.getType().equals("DELETE")
//        ));
//
//        assertEquals("Title Teste1", BookOne.getTitle());
//        assertEquals(BookOne.getPrice(), BookOne.getPrice());
//        assertEquals("Author Teste1", BookOne.getAuthor());
//        assertEquals(BookOne.getLaunch_date(), BookOne.getLaunch_date());
//
//    }
}