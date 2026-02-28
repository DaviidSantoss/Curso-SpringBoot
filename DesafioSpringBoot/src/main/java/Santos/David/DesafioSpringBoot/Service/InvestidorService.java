package Santos.David.DesafioSpringBoot.Service;

import Santos.David.DesafioSpringBoot.Entity.Investidor;
import Santos.David.DesafioSpringBoot.Repository.InvestidorRepository;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestidorService {

    @Autowired
    InvestidorRepository investidorRepository;

    /* Procurar e retornar  por Id */
    public Investidor findById(Long id) {

        return investidorRepository.findById(id).orElseThrow(() -> new ExecutionException("Ninguem encontrado"));
    }

    /* Exibir todos os usuarios */
    public List<Investidor> findAll(Long id) {
        return investidorRepository.findAll();
    }

    /* Criar Novo usuario */
    public Investidor createInvestidor(Investidor investidor) {

        investidorRepository.save(investidor);
        return investidor;
    }

    /* Modificar um usuário */
    public Investidor updateInvestidor(Investidor investidor) {

        Investidor investidorEntity = investidorRepository.findById(investidor.getId()).orElseThrow(() -> new ExecutionException("Ninguem encontrado"));

        investidorEntity.setName(investidor.getName());
        investidorEntity.setEmail(investidor.getEmail());
        investidorEntity.setProfile(investidor.getProfile());

        return  investidorRepository.save(investidorEntity);
    }


    public void DeleteInvestidor(Long id) {

        Investidor investidorEntity = investidorRepository.findById(id).orElseThrow(() -> new ExecutionException("Ninguem encontrado"));

        investidorRepository.delete(investidorEntity);
    }
}
