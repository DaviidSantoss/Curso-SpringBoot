package Textos;

public class A17_Docker {


    // =====================
    // Docker
    // =====================


    /* O que é?
    *
    * Docker é uma ferramenta para empacotar e rodar aplicações
    * em "caixas" isoladas chamadas containers.
    *
    * Exemplo: Você pega uma aplicação Spring Boot + java + depêndencias +
    * configurações e coloca tudo dentro de uma caixa que funciona em qualquer
    * maquina, essa caixa é o container.
    *
    * */


    /* Como isso Funciona?
    *
    * Docker usa o conceito chamado de containerização, diferente da maquina
    * virtual que roda um sistema operacional inteiro, o Docker usa o sistema
    * operacional da maquina, isola só o necessário(processos, arquivos,redes)
    *
    *
    * Fluxo básico:
    * Você cria uma imagem (tipo um molde)
    * Docker usa essa imagem para criar um container
    * O container roda sua aplicação
    *
    * */


    /* Principais componentes
    *
    * Imagem (Image):
    *
    * Um “molde”
    * Contém tudo necessário pra rodar a aplicação
    * Ex: Java + seu .jar
    *
    *
    * Container:
    *
    * Uma instância da imagem
    * É o que realmente roda
    *
    * Dockerfile:
    *
    * Um arquivo de instruções
    * Diz como construir a imagem
    *
    * Docker Hub:
    *
    * Tipo um “GitHub de imagens”
    * Você pode baixar imagens prontas
    *
    * */


    /* O que é o Dockerfile
    *
    * O Dockerfile é um roteiro de construção da sua aplicação.
    *
    * Ele diz pro Docker:“Passo a passo, faça isso aqui pra montar
    * o ambiente e rodar meu projeto”.
    *
    * O Docker lê o Dockerfile de cima pra baixo da esquerda pra direita,
    *  linha por linha.
    *
    * Onde colocar o Dockerfile? Coloca na raiz do projeto onde, ou seja abaixo
    * da pasta "src".
    *
    *  */


    /* Como Dockerizar uma aplicação:
    *
    * 1-Criamos um Docker file na raiz do projeto, contendo as seguintes
    informações:
    *
    * FROM eclipse-temurin:21-jdk
    * COPY target/*.jar app.jar
    * RUN bash -c 'touch /app.jar'
    * ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
    *
    *
    * 2- Precisamos adicionar o app.jar na Docker Image
    *
    * Primeiro ir até o maven, depois lifiCycle, e depois escolher as opções "clean e packege",
    * após isso será criado um .jar dentro de "target" que sera de onde ele será copiado para o docker
    *
    * 3-Contruir a Image para o Docker
    *
    * No PowerShell
    *
    * utiliza-se o comando "docker build -t hello-docker:0.0.1-SNAPSHOT ."
    *
    * Obs: o comando principal é "docker build -t" e em seguida é o nome do seu .jar
    * que no caso é "hello-docker:0.0.1-SNAPSHOT ."
    *
    * 4- Subindo um Container Docker a partir da imagem que criamos:
    *
    * Com o comando "docker image ls" conseguimos verificar se nossa imagem foi criada.
    *
    * Para subir o container basta utilizar o comando "docker run -p 80:80 -d hello-docker:0.0.1-SNAPSHOT"
    * o comando "-p 80:80" indica quais portas vamos utilizar o da esquerda indica a porta da maquina e a da
    * direita indica a porta dentro do container "-d" utilizamos para "liberar" o terminal e em seguida
    * inserimos o nome da imagem "hello-docker:0.0.1-SNAPSHOT"
    * */

    /* Gerenciamento de Docker images e Conteineres Docker:
    *
    * "docker container ls" nos mostra quais containeres estão em execução.
    *
    * "docker container stop (ID)" Utilizamos para parar o container em execução.
    *
    * "docker logs -f (ID)" nos mostra os logs atuais do container em execução".
    *
    * "docker images" nos mostra quais images possuimos em nossa maquina.
    *
    * "docker pull (IMAGE_NAME)" utilizamos para baixar uma image em nossa maquina.
    *
    * "docker tag sodavidmesmo/hello-docker:0.0.1-SNAPSHOT sodavidmesmo/hello-docker:latest" com esse comando
    * conseguimos definir a versão "latest" da nossa image.
    *
    * COMO UPAR UMA IMAGE NO DOCKERHUB:
    *
    * "docker tag (YOU_IMAGE): (TAG YOUR_REPO)/ (YOU_IMAGE)" com essa comando conseguimos
    * criar nossa tag do nosso container image, um exemplo dele seria:
    * " "docker tag hello-docker:0.0.1-SNAPSHOT sodavidmesmo/hello-docker:0.0.1-SNAPSHOT".
    *
    * "docker login docker.io" essa comando serve para autenticarmos a nossa conta docker antes
    * de subir a nossa image.
    *
    * "docker push sodavidmesmo/hello-docker:0.0.1-SNAPSHOT" com esse comando subimos nossa image para
    * o docker hub.
    *
    * */














}
