package Textos;

public class A08_AutenticacaoRESTful {

	// ======================
	// Token de Autenticação
	// ======================

	/*
	 * Normalmente enviamos uma requisição contendo dos dados do usuário Email e Senha para api a qual
	 * queros utilizar, e essa requisição é do tipo POST com as informações no body, essa requisiçaõ
	 * chega na api a api processa essa requisição e se tudo estar correto ela nos retorna um status
	 * code 200(OK) e um token "JWT", e caso ocorra um erro ele nos dirá que é uma requisição não
	 * autorizada.
	 * 
	 * Porém é inseguro ficar mandando as informações do cliente todo fez que uma requisição for feita
	 * então quando nos recebemos esse token JWT nos o armazenamos no lado do cliente, e quando o
	 * cliente quiser fazer outra requisição nos enviaremos esse token através do HEADER PAHRMS do tipo
	 * authorazation com o "beares (TOKEN DO USUÁRIO)", assim nós não trafegamos mais com os dados do
	 * cliente.
	 */

}
