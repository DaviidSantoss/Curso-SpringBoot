package Textos;

public class A06_HATEOAS {

	// ======================================
	// HATEOAS - Hypermedia As The Engine Of Application State
	// ======================================

	/*
	 * Em APIs REST, significa que a resposta da API já vem dizendo quais são os próximos passos
	 * possíveis, através de links.Ou seja o cliente não precisa adivinhar o que pode fazer depois. A
	 * própria API guia o caminho.
	 */

	/*
	 * REST sem HATEOAS → mapa decorado
	 * 
	 * REST com HATEOAS → GPS ligado
	 *
	 *
	 * Para iniciar o Hateoas adicionamos essa dependência:
	 *
	 * 	<dependency>
			<groupId>org.springframework.hateoas</groupId>
			<artifactId>spring-hateoas</artifactId>
		</dependency>


	*  e adicionamos isso na classe DTO ou semelhante,
	*  "public class PersonDTO extends RepresentationModel<PersonDTO>"
	*
	*	Sintaxe para adicionar o Hateoas (Adicionamos normalmente a classe Service)
	*
	* " dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel().withType("GET"));"
	*
	*  "dto.add(...)" - Você está adicionando um link dentro do DTO.
	*
	* 	"methodOn(PersonController.class)" - Spring, simula uma chamada nesse controller, mas
	* 	 sem executar de verdade.
	*
	* 	".findById(id)" - Você “chama” o metodo, mas isso não executa lógica.
	*
	* 	"linkTo(...)" - Aqui o Spring monta a URL final.
	*
	* 	".withSelfRel()" - Define o tipo do link.
	*
	* 	".withType("GET")" - Define o metodo HTTP.
	*



	*	Testes:
	*
	* Também criamos uma classe para testes, onde testamos todos os métodos
	* do CRUD, tbm criamos uma classe "Exception" para tratarmos os "erros"
	* que esses testes podssam apresentar.
	*
	* Vale um estudo mais aprofundando nesses testes para aplicar isso em algum
	* projeto real.
	 */

}
