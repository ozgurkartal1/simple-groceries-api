package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.FileOutputStream;

public class TestDataWriter {

    @SneakyThrows
    public static void dataWriter(String path, Object object){
        FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + "/src/test/resources/test_data/" + path);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(fileOutputStream, object);
    }
}
