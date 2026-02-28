package Textos;

public class A14_DTO {

    // =====================
    // Data Transfer Object
    // =====================

    /* O que é DTO:
    *
    * É um objeto criado para transportar dados entre camadas do sistema,
    * principalmente entre o backend e o cliente.
    *
    * Ele não representa o banco de dados, ele representa apenas os dados que
    * precisam ser enviados ou recebidos.
    *
    * Por que o DTO existe:
    *
    * Porque nem todos os dados da sua entidade devem ser expostos.
    *
    *
    * Como Funciona o DTO Na pratica:
    *
    * é o seguinte: você criou uma separação clara entre a camada de
    * persistencia (Entity) e a camada de comunicação da api (DTO). A
    * person(Entity) representa exatamente como são os dados armazenados
    * no banco de dados. Já O PersonDTO representa como esses dados entram
    * e saem da sua API.O controller não trabalha mas com Entity, ele trabalha
    * agora apenas com o DTO.Quando uma requisição chega (por exemplo um POST
    * ou um PUT), o Controller recebe um PersonDTO e envia para o Service.No
    * Service Esse DTO é convertido para "person"(Entity) usando o objectMapper
    * com o Dozer, porque quem conversa com o Repository e com o banco é o Entity.
    *
    *
    *
    *   Metodo publico da classe Controller do  tipo "PersonDTO" para a criação de uma nova pessoa:,

        @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
   public PersonDTO create(@RequestBody PersonDTO person) {

        return  service.create(person);
   }

   *
   *
   *
   * Metodo publico da classe "Service" do tipo "PersonDTO" para a criação de uma nova pessao,
   * Esse codigo "var entity  = parseObject(person, Person.class);" recebe um "person" do tipo
   * "PersonDTO" depois converte para  "Person.class"(Entity) e depois salva no banco como "Entitu"
   * "parseObject(repository.save(entity)" e logo em seguida retorna como  "PersonDTO.class" ?

    public PersonDTO create (PersonDTO person) {

        var entity  = parseObject(person, Person.class);

        logger.info("CRIAMOS ALGUEM CARAI!");

        return parseObject(repository.save(entity),PersonDTO.class);
    }

    * Ou seja A Classe Controller não trabalha mais com Entity e sim com DTO isso acontece
    * Porque "Entity" representa banco já o "DTO" representa API. "DTO" desacopla sua API do banco,
    *  O banco pode mudar a sua  API permanecer estável.
    *
    *  */

}
