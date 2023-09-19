package com.csv.reader.service;

import com.csv.reader.config.CSVReader;
import com.csv.reader.config.ClearCacheTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLOutput;

@Service
@EnableScheduling
@Slf4j
public class CacheService {

    @Autowired
    CSVReader csvReader;

    @Autowired
    ClearCacheTask clearCacheTask;

    @Autowired
    CacheManager cacheManager;


    public void setCSVDataCache() throws IOException {
        cacheManager.getCache("all-csv-data").put("all-csv-data", csvReader.getAllMap("/static/csv/"));
    }

    public void clearCSVDataCache() throws IOException {
        clearCacheTask.clearCacheManager();
    }

}