package ru.syntez.asyncapi.parser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ru.syntez.asyncapi.parser.usecases.CreateAsyncapiFromFileUsecase;

import static java.lang.System.exit;

/**
 * Main class for console running
 *
 * @author Skyhunter
 * @date 25.12.2021
 */
@SpringBootApplication
public class AsyncapiParserMain implements CommandLineRunner {

    private final CreateAsyncapiFromFileUsecase createAsyncapiFromFile;

    private static Logger LOG = LogManager.getLogger(AsyncapiParserMain.class);

    public AsyncapiParserMain(CreateAsyncapiFromFileUsecase createAsyncapiFromFile) {
        this.createAsyncapiFromFile = createAsyncapiFromFile;
    }

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(AsyncapiParserMain.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        LOG.info("Start process parse files...");
        for (int i = 0; i < args.length; ++i) {
            String fileName = args[i];
            LOG.info("args[{}]: {}", i, fileName);
            try {
                createAsyncapiFromFile.execute(fileName);
                LOG.info(String.format("File %s was completed", fileName));
            } catch (Exception ex) {
                LOG.error("Problem: ", ex);
            }
        }
        exit(0);
    }
}