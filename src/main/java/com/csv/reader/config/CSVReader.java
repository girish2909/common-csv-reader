package com.csv.reader.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
public class CSVReader {
    private List<Map<String, String>> readCSVFromClasspath(final String fileNameWithFullyClassifiedPath) {
        ClassPathResource classPathResource = new ClassPathResource(fileNameWithFullyClassifiedPath, CSVReader.class.getClassLoader());
        try (
                Reader in = new FileReader(classPathResource.getFile());
        ) {
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            List<Map<String, String>> listOfMaps = new ArrayList<>();
            for (CSVRecord record : records) {
                listOfMaps.add(record.toMap());
            }
            return listOfMaps;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getResourceFiles(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        try (
                InputStream in = getResourceAsStream(path);
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;
            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }
        return filenames;
    }

    private InputStream getResourceAsStream(String resource) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
    public Map<String, List<Map<String, String>>> getAllMap(final String filePath) throws IOException {
        List<String> resourceFiles = getResourceFiles(filePath);
        System.out.println(resourceFiles);
        Map<String, List<Map<String, String>>> map = new HashMap<>();
        resourceFiles.forEach(fileName -> {
            map.put(filePath + fileName, new CSVReader().readCSVFromClasspath(filePath + fileName));
        });
        return map;
    }
}
