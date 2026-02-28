package Santos.David.Controllers;

import Santos.David.Exception.UnsupportedMathOperationException;
import Santos.David.Service.MathService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*O RestController nos diz que essa classe será responsável por responder as
* requisições HTTP e nos retornar dados, já o RequestMapping (o metodo que define
* a nossa URL) quando aplicado diretamente em um classe nos diz que todos os conteudos
* dentro dessa classe serão acessados atraves do "/math"  */

@RestController
@RequestMapping("/math")
public class MathController {

    MathService MathService = new MathService();

    /* Como explicado anteriormente o resquestMapping é oq define nossa URL ou seja
    * agora teremos que passar "/math/n1/n2" (n1 e n2 representam números ao quais o
    * usuário tera que inserir na URL). */
    @RequestMapping("/sum/{n1}/{n2}")

    /* Esse metodo recebe dois valores diretamente na URL, o PathVariable(n1) pega o número que
    * estiver na parte da URL chamada n1 e faz o mesmo com n2 */
    public double sum(@PathVariable("n1") String n1, @PathVariable("n2")String n2) throws Exception {

        /* Si o n1 ou n2 forem não numéricos então lance uma excessão de
        * "Illegal Argument Exception" */

        return MathService.ConverteEValida(n1) + MathService.ConverteEValida(n2);
    }

    /* Metodo que faz a subtração */
    @RequestMapping("/sub/{n1}/{n2}")
    public double sub(@PathVariable("n1") String n1, @PathVariable("n2")String n2) throws Exception {

        return MathService.ConverteEValida(n1) - MathService.ConverteEValida(n2);

    }

    /* Metodo que faz a multiplicação */
    @RequestMapping("/mult/{n1}/{n2}")
    public double Mult(@PathVariable("n1") String n1, @PathVariable("n2")String n2) throws Exception {

        return MathService.ConverteEValida(n1) * MathService.ConverteEValida(n2);
    }

    /* Metodo que faz a divisão */
    @RequestMapping("/div/{n1}/{n2}")
    public double Div(@PathVariable("n1") String n1, @PathVariable("n2")String n2) throws Exception {

        /* Validação de divisão por zero */
        MathService.DivisaoByZero(n2);

        return MathService.ConverteEValida(n1) / MathService.ConverteEValida(n2);
    }

    /* Metodo que calcula a média */
    @RequestMapping("/med/{n1}/{n2}")
    public double Med(@PathVariable("n1") String n1, @PathVariable("n2")String n2) throws Exception {

        return (MathService.ConverteEValida(n1) + MathService.ConverteEValida(n2)) / 2;
    }

    /* Metodo que calcula a raiz */
    @RequestMapping("/raiz/{n1}/{n2}")
    public String Raiz(@PathVariable("n1") String n1, @PathVariable("n2")String n2) throws Exception {

        return Math.sqrt(MathService.ConverteEValida(n1)) + " , "+  Math.sqrt(MathService.ConverteEValida(n2));
    }









}
