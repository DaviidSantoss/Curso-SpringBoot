package Textos;

public class A11_TratamentoDeErros {

    /* Inciamos criando 3 Classes:
    *
    * 1 - ExceptionResponse: Essa classe funciona como um formulário a ser
    * preenchido caso um erro ocorra, e os dados que serão preenchidos são
    * "(Date timestamp,String menssage,String datails)", com essa dados preenchidos
    * podemos retornar o erro de uma forma estruturada e coesa contendo a data e
    * a hora que o erro aconteceu, uma mensagem de erro, e os detalhes do erro em si.
    *
    * Ela NÃO trata o erro, apenas guarda as informações do erro.
    *
    * 2- UnsupportedMathOperationException: Nesta classe Definimos um erro especifico,
    * o erro " UnsupportedMathOperation", ou em português operação matemática não suportada,
    * e ao lançarmos um erro utilizando essa classe devemos passar uma mensagem explicita
    * falando sobre o erro " throw new UnsupportedMathOperationException("Please set a Numeric value!");"
    *
    * 3- ControllerAdvice: Essa classe é a classe que trata os erros diretamente, nessa classe
    * possuimos dois métodos o primeiro "handleAllExceptions" que trata qualquer exceção que o programa
    * venha a ter e o outro metodo "handleBadRequestException" que só trata exceções do tipo
    * UnsupportedOperationException.
    *
    * Uso Real: Para utilizar o tratamento de erro que projetamos acima fizemos o seguinte lançamos
    * esse erro "throw new UnsupportedMathOperationException("Please set a Numeric value!");" que
    * pertence a classe "UnsupportedMathOperationException", e o que acontece é o seguinte:
    *
    * Nossa classe "UnsupportedMathOperationException" lança um RunTimeException que é tratato pelo
    * metodo "handleAllExceptions" da classe CustomEntityResponse, que nos retorna o erro de uma forma
    * estruturada contendo,data,mensagem e detalhes do erro.
    *
    *
    * Extra:
    *
    *   controller → recebe requisição

        service → faz lógica

        model → representa dados

        repository → salva/busca dados

    *
    *  */

}
