package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.FileInputStream;

public class TestDataReader {

    @SneakyThrows
    public static <T> T dataReader(String path, Class<T>valueType){
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/test_data/" + path);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(fileInputStream, valueType);
    }
}
