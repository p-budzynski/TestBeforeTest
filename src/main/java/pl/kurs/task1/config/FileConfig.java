package pl.kurs.task1.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class FileConfig {

    @Value("${dbFileExportPath}")
    private String dbFileExportPath;
}
