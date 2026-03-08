package Santos.David.Service.repository;

import Santos.David.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/*Essa interface representa o REPOSITORY da entidade Person.Ao estender JpaRepository,
o Spring automaticamente cria uma implementação com diversos métodos prontos para: salvar
 buscar,deletar,atualizar.Sem precisar implementar nada*/
public interface PersonRepository extends JpaRepository<Person, Long> {
}
