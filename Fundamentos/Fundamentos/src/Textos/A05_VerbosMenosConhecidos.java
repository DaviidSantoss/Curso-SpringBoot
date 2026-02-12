package Textos;

public class A05_VerbosMenosConhecidos {

	// ======================
	// PATCH
	// ======================

	/*
	 * Pode ser utilizado para fazer updates parciais de um recurso.Por exemplo quando vc precisa
	 * alterar apenas um campo em um recurso. Executar um PUT com todo o objeto é pesado e acarreta em
	 * um maior consumo de banda.
	 */

	/*
	 * Use com moderação pois colisões entre multiplas PATCH request são mais perigosas que colisões
	 * entre PUT request por que exige que o cliente tenha informações basicas do recurso ou irão
	 * corrompe-lo.
	 */

	// ======================
	// HEAD
	// ======================

	/*
	 * Possui uma funcionalidade similiar ao GET, exceto pelo fato do servidor retornar uma response
	 * line e headers, mas sem um entity-body.
	 */

	// ======================
	// TRACE
	// ======================

	/*
	 * É usado para recuperar o conteúdo de uma requisição HTTP de volta podendo ser usado com propósito
	 * de debug durante o processo de desenvolvimento.
	 */

	// ======================
	// OPTIONS
	// ======================

	/*
	 * É usado pelo cliente para encontrar operações HTTP e outras opções suportadas pelo servidor.O
	 * cliente pode especificar uma URL para o verbo OPTIONS ou um asterisco (*) para se referir a todo
	 * servidor.
	 */

	// ======================
	// CONNECT
	// ======================

	/*
	 * É usado pelo cliente para estabelecer uma conexão de rede com um servidor via HTTP.
	 */

}
