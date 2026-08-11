package Santos.David.File.importer.impl;

import Santos.David.File.exporter.contract.FileExporter;
import Santos.David.File.importer.impl.contract.FileImporter;
import Santos.David.data.dto.PersonDTO;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
public class XlsxImporter implements FileImporter {

    /* → inputStream → arquivo Excel em bytes vindo do upload */
    @Override
    public List<PersonDTO> importFile(InputStream inputStream) throws Exception {

        /* → XSSFWorkbook → classe Apache POI que lê arquivos .xlsx
        *  try-with-resources → fecha o arquivo automaticamente
        *  ao terminar, mesmo se der erro. */
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)){

            /*  → getSheetAt(0) → pega a PRIMEIRA aba da planilha */
            XSSFSheet sheet = workbook.getSheetAt(0);

            /* → Iterator → "dedo" que percorre linha por linha
            *    diferente do for-each, o Iterator permite controle
            *    manual de qual linha pegar */
            Iterator<Row> rowIterator = sheet.iterator();

            /*  → hasNext() → verifica se existe próxima linha
            *     next()    → avança e DESCARTA essa linha
            *     isso pula o CABEÇALHO da planilha: */
            if ((rowIterator.hasNext())) rowIterator.next();

            /* → passa o iterador já sem o cabeçalho
            *    esse metodo lê cada linha restante e
            *     transforma em PersonDTO. */
            return parseRowsToPersonDtoList(rowIterator);
        }
    }


    private List<PersonDTO> parseRowsToPersonDtoList(Iterator<Row> rowIterator) {
        List<PersonDTO> people = new ArrayList<>();

       while (rowIterator.hasNext()){
           Row row = rowIterator.next();
           if (isRowValid(row)){
               people.add(parseRowToPersonDto(row));
           }
       }
        return people;
    }

    /* Metodo para transformar linhas em persondto */
    private PersonDTO parseRowToPersonDto(Row row) {

        PersonDTO person = new PersonDTO();
        person.setFirstName(row.getCell(0).getStringCellValue());
        person.setLastName(row.getCell(1).getStringCellValue());
        person.setAddress(row.getCell(2).getStringCellValue());
        person.setGender(row.getCell(3).getStringCellValue());
        person.setEnabled(true);
        return person;
    }


    private static boolean isRowValid(Row row) {
        /* si a primeira celula for diferente de nulo e for dirente de vazio então é valida. */
        return row.getCell(0) != null && row.getCell(0).getCellType() != CellType.BLANK;
    }
}
