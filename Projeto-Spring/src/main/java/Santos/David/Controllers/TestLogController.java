package Santos.David.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLogController {


    private Logger logger = LoggerFactory.getLogger(TestLogController.class.getName());

    @GetMapping("/test")
    public String testeLog(){

        logger.debug("This is info debug");
        logger.info("This is info info");
        logger.warn("This is info warn");
        logger.error("This is info error");
        return "Logs generated successfully";
    }
}
