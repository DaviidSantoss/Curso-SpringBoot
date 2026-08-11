package Santos.David.File.exporter.factory;

import Santos.David.Exception.BadRequestException;
import Santos.David.File.exporter.MidiaTypes;
import Santos.David.File.exporter.contract.FileExporter;
import Santos.David.File.exporter.impl.CSVExporter;
import Santos.David.File.exporter.impl.XlsxExporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

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