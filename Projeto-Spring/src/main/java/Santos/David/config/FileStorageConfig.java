package Santos.David.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/* Diz ao Spring: "essa classe é uma configuração" o
*  Spring carrega automaticamente na inicialização. */
@Configuration

/* Diz ao spring: "Leia as propriedades que começam
*  com "file" no application.yml e injete na classe,
*  se no yml tiver "file.upload_dir" cai no campo
*  "upload_dir". */
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {

    /* armazena o caminho do diretório de upload. */
    private  String upload_dir;

    public FileStorageConfig() {
    }

    public String getUpload_dir() {
        return upload_dir;
    }

    /*  setter — é AQUI que o Spring injeta o valor
    *    lido do application.yml automaticamente. */
    public void setUpload_dir(String upload_dir) {
        this.upload_dir = upload_dir;
    }
}
