package Textos;

public class A18_TestContainers {

    /* TestContainers = testes reais, com banco real, em ambiente descartável via Docker
     *
     * ❓ Por que usar TestContainers ao invés do H2?
     * → H2 é um banco em memória com comportamento DIFERENTE do banco real
     * → TestContainers sobe o banco real (MySQL, PostgreSQL, etc) via Docker
     * → Garante que o teste reflete o comportamento de produção
     *
     * ⚠️ Requisito: Docker instalado e rodando na máquina
     *
     * FLUXO:
     * Teste inicia
     * ↓
     * TestContainers sobe um MySQL real via Docker
     * ↓
     * Seu teste roda contra esse banco real
     * ↓
     * Teste termina → container é destruído automaticamente
     *
     * INFRAESTRUTURA (AbstractIntegrationTest):
     * → Classe base que todos os testes de integração herdam
     * → @ContextConfiguration → diz ao Spring para usar o Initializer
     *    antes de montar o contexto
     * → Initializer sobe o container e injeta as configs no Spring
     * → addFirst → garante prioridade sobre o application.properties
     *
     * DEPENDÊNCIAS:
     *
     *      <dependency>
     *         <groupId>org.testcontainers</groupId>
     *         <artifactId>mysql</artifactId>
     *         <version>1.20.4</version>
     *         <scope>test</scope>
     *      </dependency>
     *
     *      <dependency>
     *         <groupId>io.rest-assured</groupId>
     *         <artifactId>rest-assured</artifactId>
     *         <scope>test</scope>
     *      </dependency>
     *
     * */
}