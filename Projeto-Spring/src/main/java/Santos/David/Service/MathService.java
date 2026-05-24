package Santos.David.Service;

import Santos.David.Exception.UnsupportedMathOperationException;

public class MathService {


    /* Metodo para converter String em Double. */
    public double convertToDouble(String strnumber) throws IllegalArgumentException {

        /* Si o número passado for nullo ou vazio lance uma excessão. */
        if (strnumber == null || strnumber.isEmpty())
            throw new UnsupportedMathOperationException("Please set a Numeric value!");


        String number = strnumber.replaceAll(" ", ".");

        /* Retorna a String "strnumber" ja convertida em double. */
        return Double.parseDouble(number);

    }

    public boolean isNumeric(String strnumber) {

        /*Si o número passado for nullo ou estiver vazio retorne falso.  */
        if (strnumber == null || strnumber.isEmpty()) return false;

        /*Sempre que a virgula for usada sera substituida por ponto.  */
        String number = strnumber.replaceAll(" ", ".");

        /*Aplica o regex para fazer a validação do número.  */
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }


    public Double ConverteEValida(String number) {

        if(!isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a Numeric value!");
        }

        return convertToDouble(number);

    }


    public boolean DivisaoByZero(String number) {
        if(ConverteEValida(number) == 0  ) throw new UnsupportedMathOperationException("Division by Zero!");

        return true;
    }

}
