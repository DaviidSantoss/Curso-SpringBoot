package Santos.David.Exception.hadler;

import Santos.David.Exception.ExceptionResponse;
import Santos.David.Exception.UnsupportedMathOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/* @ControllerAdvice é uma classe “conselheira global” que ajuda TODOS os
   controllers da aplicação, ela trata erros, intercepta exceções e padroniza
    respostas.

    @ControllerAdvice permite centralizar o tratamento de exceções, evitando
    duplicação de código e mantendo os controllers limpos.*/
@RestController
@ControllerAdvice
public class CustomEntityResponse extends ResponseEntityExceptionHandler {

    /* Metodo criado para tratal qualquer tipo de excessão, a notação "@ExceptionHandler(value = Exception.class)"
     nos diz que este metodo vai capturar erros do tipo Exception que são os basicamente qualquer erro.  */
    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {

        /* Instânciamos nossa classe ExceptionResponse com o nome de "response" do tipo record a qual guarda dados,
        * criamos uma nova data, pegamos a mensagem do "Exception ex", e por fim pegamos a descrição da Requisição
        *  Web "WebRequest request". */
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        /* Por fim retornamos nosso metodo "ResponseEntity" contendo a "response" criada acima, e o status
        * code "INTERNAL_SERVER_ERROR" fornecido por "HttpStatus".   */
        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* Nesse metodo fizemos o mesmo que o metodo acima porem tratamos uma excessão especifica no caso
    * "UnsupportedOperationException" com o status code sendo "BAD_REQUEST" */
    @ExceptionHandler(UnsupportedMathOperationException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex, WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }

}
