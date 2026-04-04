package Santos.David.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* É uma classe que define como os Beans são criados */
@Configuration
public class OpenApiConfig {

    /* Um Bean é um objeto gerenciado pelo Spring
    *  Ao iniciar a aplicação esse Bean tbm será iniciado */
    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI().info(new Info().title("REST APIs RESTful from 0 with java, Spring boot, kubernetes and" +
                " Docker").version("V1").description("REST APIs RESTful from 0 with java, Spring boot, kubernetes and Docker")
                .termsOfService("https://www.instagram.com/david.snt0s/").license(new License().name("Apache 2.0").
                        url("https://www.instagram.com/david.snt0s/")));
    }
}
