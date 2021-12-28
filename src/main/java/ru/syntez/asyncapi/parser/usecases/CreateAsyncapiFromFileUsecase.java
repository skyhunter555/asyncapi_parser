package ru.syntez.asyncapi.parser.usecases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.syntez.asyncapi.parser.entities.FileExtensionEnum;
import ru.syntez.asyncapi.parser.entities.input.AsyncapiInput;
import ru.syntez.asyncapi.parser.exceptions.AsyncapiParserException;

import java.io.*;
import java.util.Optional;

@Service
public class CreateAsyncapiFromFileUsecase {

    private static Logger LOG = LogManager.getLogger(CreateAsyncapiFromFileUsecase.class);

    public void execute(String fileName) {

        File asyncapiFile = new File(fileName);
        if (!asyncapiFile.isFile()) {
            throw new AsyncapiParserException(String.format("This is not a file %s", fileName));
        }
        try {
            ObjectMapper objectMapper = getObjectMapper(fileName);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,  false);
            objectMapper.findAndRegisterModules();
            AsyncapiInput asyncapi = objectMapper.readValue(asyncapiFile, AsyncapiInput.class);
            LOG.info("asyncapi: " + asyncapi);
        } catch (IOException e) {
            throw new AsyncapiParserException(String.format("Error read file %s", fileName));
        }
    }

    private ObjectMapper getObjectMapper(String fileName) throws AsyncapiParserException {

        Optional<String> extensionOptional = getExtensionFile(fileName);

        if (!extensionOptional.isPresent()) {
            throw new AsyncapiParserException(String.format("Extension undefined for file %s", fileName));
        }

        FileExtensionEnum extension = FileExtensionEnum.parseCode(extensionOptional.get().toLowerCase());
        switch (extension) {
            case YML:
            case YAML:
                return new ObjectMapper(new YAMLFactory());
            case JSON:
                return new ObjectMapper();
        }
        throw new AsyncapiParserException(String.format("Parsing not yet implemented for file with extension %s", extension.getCode()));
    }

    private Optional<String> getExtensionFile(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

}
