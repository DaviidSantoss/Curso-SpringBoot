package Santos.David.config;

public interface TestConfigs {

    int SERVER_PORT = 8888;

    /* Criamos essa string para evitar erros de digitação, pois uma vez que
    * certa palavra é usada diversas vezes é mais vantajoso utilizar uma String
    * que não se altera. */
    String HEADER_PARAM_AUTHORIZATION = "Authorization";

    String HEADER_PARAM_ORIGIN = "Origin";

    String ORIGIN_DAVID = "https://www.instagram.com/david.snt0s/";
    String ORIGIN_ERRO = "https://www.google.com";
}
