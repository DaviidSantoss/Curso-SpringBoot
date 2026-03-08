package Textos;

public class A16_Migration {

    // =====================
    // Migration
    // =====================

    /* O que é Migration:
    *
    * Migration = versão do banco de dados. Ou seja é um
    * arquivo que descreve uma mudança na estrutura do banco.
    *
    * Exemplo: criar tabelas,adicionar colunas, remover colunas,criar indicie.
    *
    * O que é flyway:
    *
    *O flyway é uma ferramenta que executa migrations automaticamente, ele lê os arquivos
    * vê quais ja foram executados, executa os novos e salva histórico no banco.
    *
    * Estrutura padrão das migrations:
    *
    * src
        └─ main
            └─ resources
                └─ db
                     └─ migration



    * Exemplo migrations:
    *
    * V1__create_users_table.sql
    * V2__add_email_to_users.sql
    * V3__create_orders_table.sql
    *
    * Padrão flyway:
    *
    * V{numero}__{descricao}.sql
    *
    * Depois do "V" e o {número} se utiliza 2 underscore para
    * separar a versão da descrição, e a separação dos nomes da
    * descrição só se utiliza 1 underscore.
    * */

}
