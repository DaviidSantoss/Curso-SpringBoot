package Santos.David.Service.repository;

import Santos.David.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*Essa interface representa o REPOSITORY da entidade Person.Ao estender JpaRepository,
o Spring automaticamente cria uma implementação com diversos métodos prontos para: salvar
 buscar,deletar,atualizar.Sem precisar implementar nada*/
public interface PersonRepository extends JpaRepository<Person, Long> {


    /* A notação "@Modifying" avisa ao Spring que essa "@Query" vai modificar dados
    * no banco, e isso é obrigatório em "@Query" que fazem UPDATE,DELETE,INSERT,
    * sem isso o Spring Lança uma exceção, pois ele espera que a "@Query" só faça
    * SELECT por padrão.Junto do "@Modifying" podemos observar o seguinte código
    * "clearAutomatically = true)" com ele o update é feito no banco e o cache é
    * limpo logo em seguida para que uma nova "Query" seja feita. */
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Person p SET p.enabled = false where p.id =:id")
    void disablePerson(@Param("id") Long id);

    /* Listar apenas as pessoas Habilitadas. */
    @Query("SELECT p FROM Person p WHERE p.enabled = true")
    List<Person> findAllEnabled();

    @Query("SELECT p FROM Person p WHERE LOWER(p.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))")
    Page<Person> findPeopleByName(@Param("firstName") String firstName, Pageable pageable);
}
