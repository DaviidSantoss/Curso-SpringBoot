package Santos.David.Controllers;

import Santos.David.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/* “Essa classe é responsável por responder requisições HTTP
e retornar dados diretamente */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();

    /* Define que esse metodo será chamado quando alguém acessar /greeting
    * é a URL em si.*/
    @RequestMapping("/greeting")

    /* Metodo que responde a requisição e retorna um objeto Greeting (que vira JSON)
       inicia pegando uma parâmetro da URL chamado "name" se niguém passar essa parâmetro
       sera setado como valor padrão "world". */

    public Greeting greeting(@RequestParam(value = "name",defaultValue = "world") String name) {


        return new Greeting(counter.incrementAndGet(), String.format(template, name));

    }
}
