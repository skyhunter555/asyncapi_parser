package ru.syntez.asyncapi.parser.usecases;

import org.junit.Test;
import org.springframework.util.ResourceUtils;
import java.io.File;

import static org.junit.Assert.fail;

public class CreateAsyncapiFromFileUsecaseTest {

    @Test
    public void createFromYMLTest() {

        CreateAsyncapiFromFileUsecase createAsyncapiFromFileUsecase = new CreateAsyncapiFromFileUsecase();
        try {
            File file = ResourceUtils.getFile(this.getClass().getResource("/http-kafka.yml"));
            String fileName = file.getAbsolutePath();
            createAsyncapiFromFileUsecase.execute(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            fail( "error createAsyncapiFromFileUsecase" );
        }
    }

    @Test
    public void createFromJSONTest() {

        CreateAsyncapiFromFileUsecase createAsyncapiFromFileUsecase = new CreateAsyncapiFromFileUsecase();
        //TODO
    }

}
