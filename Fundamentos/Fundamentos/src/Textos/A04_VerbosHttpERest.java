package Textos;

public class A04_VerbosHttpERest {


	// ======================
	// Get - READ
	// ======================

	/*
	 * O verbo http get é usado para selecionar ou recuperar uma representação de um recurso. Em um
	 * cenário "feliz", uma requisição GET retorna uma representação em XML ou JSON e um HTTP status
	 * code 200(OK).Em um cenário de erro o retorno mais comum é 404(not found) ou 400 (bad resquest).
	 *
	 * Exemplo: "@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)"
	 *
	 * O @GetMapping acaba pegando um id e produzindo um MediaType do tipo JSON, na URL fica algo como
	 * /person/15.
	 */


	// ======================
	// POST - CREATE
	// ======================

	/*
	 * o verbo HTTP POST é mais frequentemente usado para criar novos recursos, inserir um novo item na
	 * base de dados.Em uma aplicação REST perfeita quando uma operação é executada com sucesso, retorna-se o status
	 * code 200 ou 201.
	 *
	 * Exemplo: @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	 *
	 * O @PostMapping produz um MediaType do tipo JSON, e consome tbm um JSON.
	 */


	// ======================
	// PUT - UPDATE
	// ======================

	/*
	 * o verbo PUT é comumente utilizado para fazer atualizações,colocando uma recurso conhecido no body
	 * corpo da requisição contendo novas informações que representam o recurso original.
	 *
	 * Exemplo: @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	 *
	 *  O @PutMapping produz um MediaType do tipo JSON, e consome tbm um JSON.
	 */


	// ======================
	// DELETE
	// ======================

	/*
	 * o verbo delete é utilizado para remover um recurso identificado por uma URI."URI = Uniform Resource
	 * Identifier ou o endereço que identifica um recurso" no caso o id.Em uma deleção bem sucedida retorna-se
	 * um status code 200 (OK) juntamente com um response body, possivelmente uma representação do recurso deletado.
	 *
	 * Exemplo: @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	 *
	 * O @DeleteMapping recebe como valor um id, e produz um MediaType do tipo JSON.
	 */
}
