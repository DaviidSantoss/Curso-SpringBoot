package Santos.David.DesafioSpringBoot.Repository;

import Santos.David.DesafioSpringBoot.Entity.Investidor;
import org.springframework.data.jpa.repository.JpaRepository;

/* Classe que contem todos os "metodos" do jpa para fazer
* a  manipulação do banco de dados. */
public interface InvestidorRepository extends JpaRepository<Investidor, Long> {
}
