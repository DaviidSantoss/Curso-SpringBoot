package Santos.David.services;

import Santos.David.Controllers.BookController;
import Santos.David.Exception.RequiredObjectIsNullException;
import Santos.David.Exception.ResourceNotFoundException;
import Santos.David.services.repository.BookRepository;
import Santos.David.data.dto.BookDTO;
import Santos.David.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;
import static Santos.David.mapper.ObjectMapper.parseListObjects;
import static Santos.David.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {


    /* Serve para retornar uma "Mensagem" quando algum metodo for executado */
    private final Logger logger = Logger.getLogger(BookService.class.getName());


    /*Com @Autowired estamos dizendo Spring injete automaticamente uma instância
    de BookService aqui para mim. */
    @Autowired
    BookRepository repository;


    public BookDTO findById (Long id){

        logger.info("Achamos um Livro");

        /* a variavel "entity" contém o metodo findById de "repository" e caso esse metodo falhar
        * iremos lançar uma "NotFoundException" */
       var entity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

       /* através do "parseObject" transformamos nossa "entity" em um DTO */
       var dto = parseObject(entity, BookDTO.class);

        addHateoasLinks(dto);

        return dto;
    }


    public List<BookDTO> findAll(){

        logger.info("Achamos Todos os Livros");

        var persons = parseListObjects(repository.findAll(),BookDTO.class);

        persons.forEach( p -> addHateoasLinks(p));

        return persons;
    }


    public BookDTO create (BookDTO book) {

        /* Si o livro for igual a nulo então lance uma excessão */
        if(book == null ) throw new RequiredObjectIsNullException();

        logger.info("Criamos um Livro");

        /* Transformamos nosso "Book do tipo DTO" em um "Book do tipo entity" */
        var entity = parseObject(book, Book.class);

        /* Criamos o Book */
        Book booksave = repository.save(entity);

        /* Transformamos o Book criado em DTO novamente */
        var dto = parseObject(booksave, BookDTO.class);

        addHateoasLinks(dto);

        return dto;
    }


    public  BookDTO update (BookDTO book){

        /* Si o livro for igual a nulo então lance uma excessão */
        if(book == null ) throw new RequiredObjectIsNullException();

        logger.info("Atulizamos um livro");

        /* Com essa variavel entity fezemos um busca com o id do objeto se ele existir podera ser atualizado. */
        Book entity = repository.findById(book.getId()).orElseThrow(() -> new RequiredObjectIsNullException("No records found for this ID"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunch_date(book.getLaunch_date());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        /* transforma o entity em dto. */
        var dto = parseObject(repository.save(entity), BookDTO.class);

        addHateoasLinks(dto);

        return  dto;
    }


    public void delete (Long id ){

        if(id == null ) throw new RequiredObjectIsNullException();

        logger.info("Deletemos um livro");

        var entity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

       repository.delete(entity);

    }

    private static void addHateoasLinks(BookDTO dto) {

        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("delete").withType("DELETE"));


//        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));

    }
}
