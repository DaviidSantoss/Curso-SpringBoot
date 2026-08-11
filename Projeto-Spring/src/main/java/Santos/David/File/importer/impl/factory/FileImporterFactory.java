package Santos.David.File.importer.impl.factory;

import Santos.David.Exception.BadRequestException;
import Santos.David.File.importer.impl.CSVImporter;
import Santos.David.File.importer.impl.XlsxImporter;
import Santos.David.File.importer.impl.contract.FileImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/* componente genérico gerenciado pelo Spring */
@Component
public class FileImporterFactory {

    @Autowired
    private ApplicationContext context;
    private Logger logger = LoggerFactory.getLogger(FileImporterFactory.class);

    /* Metodo para pegar a importação através do nome do arquivo. */
    public FileImporter getImporter(String fileName) throws Exception{
        /* Si o nome do arquivo terminar com ".xlsx" ou ".csv" execute esses comandos. */
        if(fileName.endsWith(".xlsx")){
            return (FileImporter) context.getBean(XlsxImporter.class);
        } else if (fileName.endsWith(".csv")) {
            return (FileImporter) context.getBean(CSVImporter.class);
        }else {
            throw new BadRequestException();
        }
    }

}
