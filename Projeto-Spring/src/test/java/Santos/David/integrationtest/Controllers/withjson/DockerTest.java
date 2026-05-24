package Santos.David.integrationtest.Controllers.withjson;

import org.junit.jupiter.api.Test;

public class DockerTest {


    @Test
    void checkPropertiesFile() {
        var resource = Thread.currentThread()
                .getContextClassLoader()
                .getResource("testcontainers.properties");
        System.out.println(resource);  // deve imprimir o caminho, não null
    }
}