package Textos;

public class A02_TiposParametros {

	// ======================
	// Path Params
	// ======================

	/*
	 * Os paths são passados através da própria URL "(baseUrl)) /api/person/v1/asc/10/1?name=leo" no
	 * caso nossos parâmetros são "asc/10/1" queremos 10 repostas por vez começando da primeira página.
	 * 
	 * E se eu preciso que um parâmetro seja obrigatorio eu uso o Path Params.
	 */

	// ======================
	// Query Params
	// ======================

	/*
	 * O Query( ou em portugûes "busca") utilizamos quando vamos fazer uma pesquisa nessa caso vemos
	 * "((baseUrl)) /api/person/v1/find-by-name?firstName=Leo&lastName=an" onde nosso parâmetro é
	 * "firstName=Leo&lastName=an" o Query params vem sempre ao final do path antecedido por uma "?"
	 * 
	 * Se o paraêmtro pode ser opicional usamos o Query Params.
	 * 
	 */

	// ======================
	// Header Params
	// ======================

	/*
	 * No header definimos 3 principais coisas "origin" a qual é a origem de onde está saindo a
	 * informação "accept" é qual é o formato que estão sendo aceito a resposta, e "Authorization" que
	 * são as autorizações pra essa requisição.
	 * 
	 * Se precisamos definir oq é aceito ou não em uma requisição utilizamos o Header Params.
	 */

	// ======================
	// Body Params
	// ======================

	/*
	 * No body podemos alterar atributos via texto "igual um bloco de notas" e mandar essa requisação
	 * que será compreendida normalmente utilizamos o "raw" no postaman.
	 * 
	 * E para cadastrar/Atualizar utilizamos o Body Params.
	 */
}
