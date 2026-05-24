package Textos;

public class A22_HAL {

    // =======================
    // HAL - o que é ?
    // =======================

    /* HAL (Hypertext Application Language);
    *
    * Um formato padrão usado para estruturar respostas em APIs RESTful.
    *
    * Inclui metadados e links nas respostas das APIs.
    *
    * Promove um conceito de Hypermidia, permitindo que a interação não dependa
    * de um conhecimento prévio detalhado das suas URLs.
    *
    * Dois elementos principais caracterizam o HAL: os campos "_links" e "_embedded"
    *
    * "_links" lista os links relacionados aos recursos principal ou à coleção retornada.
    *
    * "_embedded" é utilizado para incluir recursos relacionados diretamente na resposta principal.
    *
    * HAL tem como foco promover a interoperabilidade e a escalabilidade entre sistemas que consomem
    * APIs RESTful
    *
    * As respostas da API não apenas organizam os dados retornados, mas também orientam os consumidores
    * a utiliza-las de forma eficiente e dinâmica.
    *
    * Permite que os clientes descubram como usar a API de forma dinâmica.
    *
    * HATEOAS é um conceito genérico e pode ser implementado de várias maneiras;
    *
    * HAL é, uma implementação prática desse conceito
    *
    * Enquanto HATEOAS é um princípio arquitetural que estabelece como APIs devem ser navegáveis através de
    * Hypermidia, HAL é uma implementação específica que organiza as respostas das APIs seguindo um formato padrão.
    *
    *      // =======================
          // paginação com HAL
         // =======================

    * "ResponseEntity<PagedModel<EntityModel<PersonDTO>>>"
    *
    *
    *
    *  */

}
