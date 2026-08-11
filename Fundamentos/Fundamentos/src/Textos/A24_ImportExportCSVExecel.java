package Textos;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class A24_ImportExportCSVExecel {

    // =================================================
    // Importação e Exportação de arquivos CSV e Execel
    // =================================================

    /* Iniciamos adicionando as seguintes dependencias:
    *
    * 		<!-- CSV -->
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-csv</artifactId>
			<version>${commons-csv.version}</version>
			<scope>compile</scope>
		</dependency>

		<!-- POI -->
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi-ooxml</artifactId>
			<version>${apache.poi.version}</version>
			<scope>compile</scope>
		</dependency>

	// ==========
    // Importer
    // ==========
    *
    *
    // ====================
    // Classe File Importer
    // ====================
    *
    * Criamos essa Interface para implementar o metodo de importação de
    * arquivos.
    *
    *
    public interface FileImporter {

    List<PersonDTO> importFile(InputStream inputStream) throws Exception;
    }
    *
    *
    // ============================
    // Classe FileImporterFactory
    // ============================
    *
    * Criamos essa classe para "pegarmos" o arquivo importado, sendo ele
    * .xlsx ou .csv, utilizando o metodo da interface "FileImporter" para
    * nos auxiliar:
    *
    *
    //componente genérico gerenciado pelo Spring
    @Component
    public class FileImporterFactory {

        @Autowired
        private ApplicationContext context;
        private Logger logger = LoggerFactory.getLogger(FileImporterFactory.class);

         //Metodo para pegar a importação através do nome do arquivo.
        public FileImporter getImporter(String fileName) throws Exception{
            //Si o nome do arquivo terminar com ".xlsx" ou ".csv" execute esses comandos.
            if(fileName.endsWith(".xlsx")){
                return (FileImporter) context.getBean(XlsxImporter.class);
            } else if (fileName.endsWith(".csv")) {
                return (FileImporter) context.getBean(CSVImporter.class);
            }else {
                throw new BadRequestException();
            }
        }

    }
    *
    *
    // ===================
    // Classe CSVImporter
    // ===================
    *
    *
    * Criamos essa classe para a importação exclusiva de CSV e seus
    * respectivos tratamentos.
    *
    *
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

        //inputStream → é o arquivo CSV em formato binário (bytes).
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
        * Ou seja transforma o CSV binário em um CSV em um texto legível.

    Iterable<CSVRecord> records = format.parse(new InputStreamReader(inputStream));

    //passa todas as linhas lidas para outro metodo, esse metodo
     *  percorre cada CSVRecord e transforma em PersonDTO,
     *  ex: "David,Santos,22,M" → PersonDTO(firstName="David",
     *                                       lastName="Santos",
     *                                        age=22,
     *                                         gender="M")
     *
        return parseRecordsToPersonDTOs(records);
}

//Metodo utilizado para transfromar "Records" em "PersonDTOs".
private List<PersonDTO> parseRecordsToPersonDTOs(Iterable<CSVRecord> records){

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


    // ===================
    // Classe XlsxImporter
    // ===================
     *
     *
     * Criamos essa classe para a importação exclusiva de XLSX e seus
     * respectivos tratamentos.
     *
     public class XlsxImporter implements FileImporter {

    //→ inputStream → arquivo Excel em bytes vindo do upload
    @Override
    public List<PersonDTO> importFile(InputStream inputStream) throws Exception {

        // XSSFWorkbook → classe Apache POI que lê arquivos .xlsx
        //  try-with-resources → fecha o arquivo automaticamente
        //  ao terminar, mesmo se der erro.
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)){

            // → getSheetAt(0) → pega a PRIMEIRA aba da planilha
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Iterator → "dedo" que percorre linha por linha
            //   diferente do for-each, o Iterator permite controle
            //    manual de qual linha pegar
            Iterator<Row> rowIterator = sheet.iterator();

            // → hasNext() → verifica se existe próxima linha
            //    next()    → avança e DESCARTA essa linha
            //     isso pula o CABEÇALHO da planilha:
            if ((rowIterator.hasNext())) rowIterator.next();

            // passa o iterador já sem o cabeçalho
            //   esse metodo lê cada linha restante e
            //     transforma em PersonDTO.
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

    // Metodo para transformar linhas em persondto
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
        // si a primeira celula for diferente de nulo e for dirente de vazio então é valida.
        return row.getCell(0) != null && row.getCell(0).getCellType() != CellType.BLANK;
    }
}
     *
     // ==========
     // Exporter
     // ==========
     *
     *
     *
     // ===============
     // FileExporter
     // ===============
     *
     *
     public interface FileExporter {

     //Metodo do tipo "Resource" que recebe uma lista de pessoas e nos
     // retorna um arquivo pronto para download.
    Resource exportFile(List<PersonDTO> people) throws Exception;
}
     *
     *
     // =====================
     // FileExporterFactory
     // =====================
     *
     *
     *
@Component
public class FileExporterFactory {


    //Log para registrar eventos
    private Logger logger = LoggerFactory.getLogger(FileExporterFactory.class);


    private ApplicationContext context;


    public FileExporterFactory(ApplicationContext context) {
        this.context = context;
    }

    //Metodo que pega os dois tipos de exportação disponiveis .xlsx ou .csv e que
    //decide qual exportador usar baseado no tipo solicitado pelo cliente
    // (Accept Header da requisição) EX:  "Accept: application/xlsx" → cliente quer Excel
    public FileExporter getExporter(String acceptHeader) throws Exception {

        if (acceptHeader.equalsIgnoreCase(MidiaTypes.APPLICATION_XLSX_VALUE)) {
            logger.info("Application XLSX exported successfully");
            return (FileExporter) context.getBean(XlsxExporter.class);

        } else if (acceptHeader.equalsIgnoreCase(MidiaTypes.APPLICATION_CSV_VALUE)) {
            logger.info("Application CSV exported successfully");
            return (FileExporter) context.getBean(CSVExporter.class);
        } else {
            throw new BadRequestException();
        }
    }
}
     *
     *
     *
     // =============================
     // CSVExporter & XlsxExporter
     // =============================
     *
     *
     * Classes que fazem a exportação tanto do csv quanto do xlsx, com suas
     * respectivas formatações e nuances, vale o estudo de cada classe de forma
     * separada.
     *
     *
     // =====================
     // MidiaTypes
     // =====================
     *
     *
     * Utilizada nas classes acima para armazenar valores constantes.
     *

//Primeiro — tipos de uso de Interface em Java
//1. Contrato de comportamento  → define métodos a implementar
//2. Repositório de constantes  → armazena valores fixos (esse caso aqui).
public interface MidiaTypes {

    String APPLICATION_XLSX_VALUE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    String APPLICATION_CSV_VALUE = "text/csv";
}

     * */
}
