package Textos.CodigoLimpo;

public class Capitulo_2_Nomes {

    // =======================
    // Nomes Significativos
    // =======================

    /* "O nome de uma variável,função ou classe deve responder
    *   a todas as grandes questões.Ele deve lhe dizer porque existe,
    *   o que faz e como é usado. Se um nome requer um comentário, então
    *   ele não revela seu propósito."
    *
    *   int d; //tempo decorrido de dias (ERRADO)
    *
    *   int tempoEmDias; (Certo)
    *
    *   "O nome de uma variável jamais deve conter a palavra "variável". O
    *    nome de uma tabela jamais deve conter a palavra Tabela."
    *
    *    "Na ausência de convenções específicas, não há como distinguir moneyAmount
    *    de money, customerInfo de customer, accountData de account e theMessage de
    *    mesage. Faça a distinção dos nomes de uma forma que o leitor compreenda as
    *    diferenças."
    *
    *   Jeito ERRADO:
    *
    *   Money money;
    *   Money moneyAmount;
    *
    *   Customer customer;
    *   Customer customerInfo;
    *
    *   Jeito Correto:
    *
    *   Money orderTotal;
    *   Money shippingFee;
    *   Money discountValue;
    *
    *   Nome de Classes:
    *
    *   "Classes e objetos devem ter nomes com substantivos, como "Cliente, Pedido
    *    NotaFiscal,Produto". Evitar palavras como "GerenciadorDePedidos,DadosDoCliente
    *    HelperDeUsuario", que também não devem ser um verbo.
    *
    *    Se o nome da sua classe termina em -ador, -eiro, -or, -helper, -util ou -service
    *    é um sinal de alerta. Geralmente significa que a classe está tentando fazer em vez de ser.
    *
    *   Nome de Métodos:
    *
    *    Os nomes de métodos devem ter verbos, como "Post Payment, Delete Page ou Save. Devem-se nomear
    *    métodos de acesso, alteração e autenticação segundo seus valores e adicionar os prefixos get,set
    *    ou is de acordo com o padrão javabean.
    *
    *   Exemplos:
    *
    *   pedido.confirmar();
    *   fatura.cancelar();
    *   contaBancaria.efetuarDebito();
    *
    *   Prefixos get, set e is — padrão JavaBean
    *
    *   cliente.getNome();
    *   cliente.setNome("Ana");
    *   cliente.isAtivo();
    *
    *
    *
    *
    *
     *  */
}
