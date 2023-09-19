package com.csv.reader.controller;

import com.csv.reader.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RefreshCsvController {

    @Autowired(required=true)
    CacheManager cacheManager;

    @Autowired
    CacheService cacheService;

    @GetMapping("/getAllCSVData")
    public ResponseEntity<?> get(){
       return ResponseEntity.ok().body(cacheManager.getCache("all-csv-data"));
    }

    @GetMapping("/loadAllCSVData")
    public ResponseEntity<String> getMe() throws IOException {
        cacheService.setCSVDataCache();
        return ResponseEntity.ok().body("All CSV loaded.");
    }

    @GetMapping("/clearAllCSVData")
    public ResponseEntity<String> clearAllCSVData() throws IOException {
        cacheService.clearCSVDataCache();
        return ResponseEntity.ok().body("clear All cached data.");
    }
}
