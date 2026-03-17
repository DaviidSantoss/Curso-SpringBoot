package Santos.David.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig  implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        //Quary params
        /*configurer.favorParameter(true).parameterName("mediaType").ignoreAcceptHeader(true).useRegisteredExtensionsOnly(false).
                defaultContentType(MediaType.APPLICATION_JSON).mediaType(
                        "xml",MediaType.APPLICATION_XML
                );*/

        //Header params
        configurer.favorParameter(false).ignoreAcceptHeader(false).useRegisteredExtensionsOnly(false).
                defaultContentType(MediaType.APPLICATION_JSON).mediaType(
                        "xml",MediaType.APPLICATION_XML
                );


    }
}
