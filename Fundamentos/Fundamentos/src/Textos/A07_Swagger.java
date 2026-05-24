package Textos;

public class A07_Swagger {

	// ======================
	// Swagger
	// ======================

	/*
	 * Swagger é uma documentação interativa da sua API. Ele mostra, explica e permite testar os
	 * endpoints da API direto no navegador.
	 *
	 * Ele:
	 *
	 * Lê seus controllers.
	 *
	 * Lê suas anotações (@RestController, @GetMapping, etc.).
	 *
	 * Gera a documentação automaticamente.
	 *
	 * Modo de iniciar:
	 *
	 * Adicionamos a seguinte dependencia
	 *
	 * <!-- Swagger -->
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>${springdoc.version}</version>
		</dependency>

	*
	* Obs: Vale ressaltar que tbm criamos uma classe chamada "OpenApiConfig"
	* onde definimos um "Configuration" no topo da classe  e um @Bean no método
	* principal, e é esse método que ira definir o titulo a descrição os termos
	* de servições e etc.
	*
	* Como implementar a documentação do Swagger na Pratica:
	*
	* 1- Criamos uma Interface, geralmente dentro de controles dentro uma packege
	* chamada "docs" ficando assim /Controllers/docs/PersonControllerDocs.class
	*
	* 2-:
	*     @Operation(
            summary = "Find a Person",
            description = "Find a specific Person by your id",
            tags = { "People" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content =
                                    @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
	*
	*
	* @Operation → descreve o endpoint nesse caso é o Find by id.
	*
	* summary → título curto
	* description → explicação mais detalhada
	* tags → agrupa endpoints no Swagger (tipo categoria)
	*
	* @ApiResponse → descreve o que pode acontecer indicando o status code
	*                e sua respectiva resposta.
	*
	* @Content → formato da resposta (JSON, XML)
	*
	* @Schema → estrutura de um objeto
	*
	*
	 */

}
