package Textos.CodigoLimpo;

public class Capitulo_7_TratamentoDeErro {

    // =======================
    // Tratemento de Erro
    // =======================


    // =============================
    // Forneça exceções com contexto
    // =============================

    /* "Cada exceção lançada deve fornecer contexto suficiente para
    *   determinar a fonte e a localização de um erro, Em java, você
    *   pode capturar uma stack trace de qualquer exceção. Entretanto,
    *   ele não consegue lhe dizer o objetivo da operação que falhou.
    *
    *   Crie mensagens de erro informativas e as passe juntamente com
    *   as exceções. Mencione a operação que falhou e o tipo de falha.
    *   Se tiver registrado as ações do seu aplicativo, passe a informações
    *   suficientes para registrar o erro no seu catch.
    *  */

    // ===============
    // Não passe Null
    // ===============

    /* Retornar null nos métodos é ruim, mas passar null para eles é pior. A
    *  menos que esteja trabalhando com uma API que espere receber null, você
    *  deve evitar passá-lo em seu código sempre que possível.
    *
    *  Na maioria das linguagens de programação não há uma boa forma de lidar com
    *  um valor null passado acidentalmente para um chamador. Como aqui este é o caso,
    *  a abordagem lógica seria proibir,por padrão, a passagem de null. Ao fazer isso,
    *  você pode programar com o conhecimento de que um null numa lista de parâmetros
    *  é sinal de problema, e acabar com mais alguns erros por descuido.
    *  */


}
