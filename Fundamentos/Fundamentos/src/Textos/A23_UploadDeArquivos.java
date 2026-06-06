package Textos;

public class A23_UploadDeArquivos {

    // =======================
    // Upload de Arquivoss
    // =======================

    /* Iniciamos setando o diretório dos arquivos no application.yml:
    *
    * file:
        upload-dir: /Users/David/Documents/Curso-SpringBoot-master/Projeto-Spring/arquivos
    *
    *
    // ===========================
    // Criação da classe config
    // ===========================
    *
    * Em seguida criamos a classe de configuração "FileStorageConfig" contendo
    * duas notações, @Configuration e @ConfigurationProperties(prefix = "file").
    *
    * @Configuration -> Diz ao Spring: "essa classe é uma configuração" o Spring
    * carrega automaticamente na inicialização.
    *
    * @ConfigurationProperties(prefix = "file"). -> Diz ao spring: "Leia as propriedades
    * que começam com "file" no application.yml e injete na classe, se no yml tiver
    * "file.upload_dir" cai no campo "upload_dir".
    *
    // ===============================
    // Criação das classes Exception
    // ===============================
    *
    * Antes de fazer upload de arquivos, precisamos preparar o sistema para lidar com
    * erros específicos de arquivo, pra isso criamos as classes FileNotFoundException
    * que lança um erro 404 e a classe FileStorageException que lança um erro 500
    * Internal Server Error.
    *
    * Também criamos dois métodos na classe "CustonEntityResponseHandler" esses métodos
    * são "handleFileNotFoundExceptions" e "handleFileStorageExceptions", ou seja as classes
    * "FileNotFoundException e FileStorageException" lançam o erro o handler capta esses erros
    * através do "@ExceptionHandler" Monta ExceptionResponse com data, mensagem e endpoint e
    * retorna o JSON padronizado com status correto.
    *
    *
     // =====================================
    // Classe FileStorageService
   // =====================================
*
* Iniciamos criando uma variável "Path fileStorageLocation" que representa
* o caminho no sistema de arquivos onde os arquivos serão armazenados.
*
* Logo após criamos o construtor recebendo "FileStorageConfig" por injeção,
* e dentro dele criamos o Path:
*
* Path path = Paths.get(fileStorageConfig.getUpload_dir())
*                  .toAbsolutePath()
*                  .normalize();
*
* Paths.get()      → converte a String do yml em um objeto Path navegável
* toAbsolutePath() → converte para caminho absoluto, garantindo que funciona
*                    independente de onde a aplicação foi iniciada
* normalize()      → remove redundâncias do caminho ex: "uploads/../uploads"
*                    vira "uploads"
*
* Após criar o Path o atribuímos ao "fileStorageLocation", em seguida num
* try/catch criamos o diretório caso não exista via "Files.createDirectories()"
* — se já existir não faz nada. Caso falhe lança FileStorageException.
*
* =====================================
* Metodo storeFile(MultipartFile file)
* =====================================
*
* MultipartFile → tipo Spring que representa um arquivo enviado via HTTP
*
* 1. StringUtils.cleanPath(file.getOriginalFilename())
*    → pega o nome original do arquivo
*    → limpa caracteres problemáticos e normaliza barras de caminho
*
* 2. Validação de segurança — Path Traversal Attack:
*    → se o nome contiver ".." lança FileStorageException
*    → sem essa validação um atacante poderia enviar um arquivo
*      chamado "../../etc/passwd" e sobrescrever arquivos do sistema!
*    → ".." significa "voltar uma pasta" no sistema de arquivos
*
* 3. resolve() → concatena o diretório base com o nome do arquivo
*    ex: "/home/david/uploads" + "curriculo.pdf"
*      = "/home/david/uploads/curriculo.pdf"
*
* 4. Files.copy() → salva o arquivo no disco
*    → StandardCopyOption.REPLACE_EXISTING → substitui se já existir
*      um arquivo com o mesmo nome
*
* 5. Retorna o nome do arquivo salvo — útil para salvar no banco
*    e recuperar depois
*
* Caso não seja possível salvar lança FileStorageException.
*
*
  * */
}
