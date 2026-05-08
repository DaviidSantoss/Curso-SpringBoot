package Santos.David.services.repository;

import Santos.David.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book,Long> {
}
