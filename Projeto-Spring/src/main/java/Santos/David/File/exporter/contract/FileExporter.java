package Santos.David.File.exporter.contract;

import Santos.David.data.dto.PersonDTO;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.List;

public interface FileExporter {

     //Metodo do tipo "Resource" que recebe uma lista de pessoas e nos
     // retorna um arquivo pronto para download.
    Resource exportFile(List<PersonDTO> people) throws Exception;
}
