package Santos.David.File.importer.impl;

import Santos.David.File.importer.impl.contract.FileImporter;
import Santos.David.data.dto.PersonDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVImporter implements FileImporter {
    @Override
    public List<PersonDTO> importFile(InputStream inputStream) throws Exception {
        CSVFormat format = CSVFormat.Builder.create()
                                            .setHeader()//-> temos o cabeçalho.
                                            .setSkipHeaderRecord(true)//-> pule o cabeçalho.
                                            .setIgnoreEmptyLines(true)//ignore linhas vazias.
                                            .setTrim(true)//eliminar espaços vazios.
                                            .build();

        /* inputStream → é o arquivo CSV em formato binário (bytes).
        *  InputStreamReader → converte esses bytes em texto legível,
        *  é um "tradutor" de binário para texto.
        *
        *  format.parse() → lê o texto e divide em registros (linhas)
        *  cada linha do CSV vira um CSVRecord,ex: "David,Santos,22,M"
        *  → um CSVRecord com 4 campos.
        *
        * Iterable<CSVRecord> → coleção de linhas do CSV Iterable = algo
        * que pode ser percorrido um item por vez como um for-each
        * sem carregar tudo na memória de uma vez
        *
        * Ou seja transforma o CSV binário em um CSV em um texto legível.*/

        Iterable<CSVRecord> records = format.parse(new InputStreamReader(inputStream));

        /* passa todas as linhas lidas para outro metodo, esse metodo
        *  percorre cada CSVRecord e transforma em PersonDTO,
        *  ex: "David,Santos,22,M" → PersonDTO(firstName="David",
        *                                       lastName="Santos",
        *                                        age=22,
        *                                         gender="M")
         */
        return parseRecordsToPersonDTOs(records);
    }

    /* Metodo utilizado para transfromar "Records" em "PersonDTOs". */
    private List<PersonDTO>parseRecordsToPersonDTOs( Iterable<CSVRecord> records){

        //Lista de pessoas
        List<PersonDTO> people = new ArrayList<>();
        //for each que percorre "records" e salva os dados dentro de "record"
        for (CSVRecord record : records){
            PersonDTO person = new PersonDTO();
            person.setFirstName(record.get("first_name"));
            person.setLastName(record.get("last_name"));
            person.setAddress(record.get("address"));
            person.setGender(record.get("gender"));
            person.setEnabled(true);
            //add a pessoa a lista
            people.add(person);
        }
        return people;
    }
}
