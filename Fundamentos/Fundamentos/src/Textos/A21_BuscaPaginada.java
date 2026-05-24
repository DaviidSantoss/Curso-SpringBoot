package Textos;

public class A21_BuscaPaginada {

    // =======================
    // Query Params
    // =======================

    /* São parâmetros enviados na URL após o "?"
    *
    *
    * /person?page=0&size=12
    *
    * ?page=0   → parâmetro 1
    * &size=12  → parâmetro 2
    *
    *  */

    // =======================
    // Busca Paginada
    // =======================

    /* Em vez de retornar todos os registros do banco
    *  de uma vez, você retorna fatias (páginas).
    *
    * Sem paginação → "SELECT * FROM person" → retorna 10.000 registros.
    *
    * Com paginação → "SELECT * FROM person LIMIT 12 OFFSET 0" → retorna 12
    *
     * */

    // =======================
    // Como Implementar
    // =======================

    /* Na classe PersonControllerDocs modificamos o "findAll" deixando ele da seguinte forma:
    *
    *      ResponseEntity<Page<PersonDTO>> findAll(
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "size",defaultValue = "12") Integer size
    );
    *
    *            "page" = qual página você quer (começa em 0)
    *            defaultValue = "0" → se não informar, pega a primeira página
    *        @RequestParam(value = "page", defaultValue = "0") Integer page,
    *
    *           "size" = quantos registros por página
    *           defaultValue = "12" → se não informar, retorna 12 por página
    *        @RequestParam(value = "size", defaultValue = "12") Integer size
    *
    * Na classe Controller tbm fizemos uma modificação:
    *
    *     @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Page<PersonDTO>> findAll(
            @RequestParam(value = "page",defaultValue = "0") Integer page,  -> captura ?page=0 da URL.
            @RequestParam(value = "size",defaultValue = "12") Integer size, -> → captura ?size=12 da URL usa 12 registros por página.
            RequestParam(value = "direction",defaultValue = "asc") String direction -> "direction" Define a direção da ordenação dos
                                                                                          resultados — crescente ou decrescente.
    {

        Pageable pageable = PageRequest.of(page, size); -> PageRequest.of() monta o objeto Pageable
        return ResponseEntity.ok(service.findAll(pageable)); -> passa o pageable para o Service executar
    }
    *
    * Na classe Service também modificamos o "findAll":
    *
    *     Antes retornavamos uma "List" porém agora retorna os dados + metadados de paginação.
    *     public Page<PersonDTO> findAll(Pageable pageable){

        logger.info("Everybody Person has been found.");

        var people = repository.findAll(pageable);

        para cada Person dentro do Page<>
        converte para DTO e adiciona os links HATEOAS
        e devolve um Page<PersonDTO> com tudo preservado
        var peopleWithLinks = people.map(person -> {

            var dto =  parseObject(person, PersonDTO.class);

            addHateoasLinks(dto);

            return  dto;

        });

        return peopleWithLinks;
    }
    *
    *
    *
        // =======================
        // Parâmetos com Pageable
        // =======================

    * Iniciamos adicionando estes códigos ao "PersonRepository":
    *
    * Busca pessoas no banco pelo nome, de forma parcial e sem
    * diferenciar maiúsculas de minúsculas, com suporte a paginação.
    *
    *     @Query("SELECT p FROM Person p WHERE LOWER(p.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))")
          Page<Person> findPeopleByName(@Param("firstName") String firstName, Pageable pageable);

    *
    * Em PersonControllerDocs adicionamos isso para ajudar na documentação:
    *
    *     ResponseEntity<PagedModel<EntityModel<PersonDTO>>> findByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "size",defaultValue = "12") Integer size,
            @RequestParam(value = "direction",defaultValue = "asc") String direction
    );
    *
    * No controller fizemos as seguinte modificações:
    *
    *     @GetMapping(value = "/findPeopleByName/{firstName}",produces = MediaType.APPLICATION_JSON_VALUE)
            public ResponseEntity<PagedModel<EntityModel<PersonDTO>>> findByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page",defaultValue = "0") Integer page,
            @RequestParam(value = "size",defaultValue = "12") Integer size,
            @RequestParam(value = "direction",defaultValue = "asc") String direction
    )

    {

    var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC: Sort.Direction.ASC;
    Pageable pageable = PageRequest.of(page, size,Sort.by(sortDirection,"firstName"));
    return ResponseEntity.ok(service.findByName(firstName,pageable));
}
    *
    *
    * Começamos passando um nome "GetMapping"  @GetMapping(value = "/findPeopleByName/{firstName}" contendo
    * o endereço "findByName" e o parâmetro passado pelo usuário.
    *
    *
    * */


}
