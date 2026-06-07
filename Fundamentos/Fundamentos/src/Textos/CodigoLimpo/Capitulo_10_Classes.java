package Textos.CodigoLimpo;

public class Capitulo_10_Classes {

    // =========
    // Classes
    // =========


    // =========================
    // Organização das Classes
    // =========================

    /* Segundo a convenção padrão Java, uma classe deve começar com
     * uma lista de variáveis. As públicas, estáticas e constantes, se
     * existirem, devem vir primeiro. Depois vêm as variáveis estáticas
     * privadas, seguidas pelas instâncias privadas. Raramente há uma boa
     * razão para se ter uma variável pública.
     *
     * As funções públicas devem vir após a lista de variáveis. Colocamos
     * as tarefas privadas chamadas por uma função pública logo depois
     * dela. Isso segue a regra de cima para baixo e ajuda o programa a ser
     * lido como um artigo de jornal.
     *
     */


    // =================================
    // As Classes Devem Ser Pequenas!
    // =================================

    /* A primeira regra para classes é que devem ser pequenas. A segunda é
     * que devem ser menores ainda. Assim como as funções, ser pequena
     * também é a regra principal quando o assunto é criar classes, e nossa
     * questão imediata é: "O quão pequena?"
     *
     * O nome de uma classe deve descrever quais responsabilidades ela cumpre.
     * Na verdade, selecionar um nome é a primeira forma de ajudar a determinar
     * o tamanho da classe. Se não conseguirmos um nome conciso para ela, então
     * provavelmente ela ficará grande demais. Quanto mais ambíguo for o nome da
     * classe, maiores as chances de ela acumular muitas responsabilidades. Por exemplo,
     * nomes de classes que possuam palavras de vários sentidos, como Processor,
     * Manager ou Super, geralmente indicam um acúmulo lastimável de responsabilidades.
     *
     * Todo sistema expansível poderá conter uma grande quantidade de lógica e complexidade.
     * O objetivo principal no gerenciamento de tal complexidade é organizá-la de modo que o
     * desenvolvedor saiba onde buscar o que deseja e que precise entender apenas a complexidade
     * que afeta diretamente um dado momento. Em contrapartida, um sistema com classes maiores e
     * de vários propósitos sempre nos atrasa, insistindo que percorramos diversas coisas que
     * não precisamos saber no momento.
     *
     */
}