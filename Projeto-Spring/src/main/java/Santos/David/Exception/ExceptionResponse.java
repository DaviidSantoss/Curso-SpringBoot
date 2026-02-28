package Santos.David.Exception;

import java.util.Date;

/* Record é uma classe especial feita só para guardar dados, com muito menos código */
public record ExceptionResponse(Date timestamp,String menssage,String datails) {
}
