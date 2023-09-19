package com.csv.reader.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class ClearCacheTask {
    @Autowired
    private CacheManager cacheManager;

//    @Scheduled(fixedRateString = "${clear.all.cache.fixed.rate}", initialDelayString = "${clear.all.cache.init.delay}") // reset cache every hr, with delay of 1hr after app start
    public void clearCacheManager() {
        cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
    }

}