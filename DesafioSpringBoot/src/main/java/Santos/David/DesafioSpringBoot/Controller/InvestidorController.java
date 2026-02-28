package Santos.David.DesafioSpringBoot.Controller;

import Santos.David.DesafioSpringBoot.Entity.Investidor;
import Santos.David.DesafioSpringBoot.Service.InvestidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inv")
public class InvestidorController {

    @Autowired
    InvestidorService service;

    @GetMapping(value = "/{id}")
    public Investidor AcharporId(@PathVariable Long id){

        return service.findById(id);

    }

    @GetMapping()
    public List<Investidor> AcharTodoMundo(Long id){

        return service.findAll(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Investidor CriaInvestidor(@RequestBody Investidor investidor){

        return service.createInvestidor(investidor);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Investidor MudarInvestidor(@RequestBody Investidor investidor){

        return service.updateInvestidor(investidor);
    }
    @DeleteMapping("/{id}")
    public void DeletarInvestidor(@PathVariable Long id){

        service.DeleteInvestidor(id);
    }
}
