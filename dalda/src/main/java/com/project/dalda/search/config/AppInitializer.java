package com.project.dalda.search.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer {

    private final RedisDataLoader redisDataLoader;

    public AppInitializer(RedisDataLoader redisDataLoader) {
        this.redisDataLoader = redisDataLoader;
    }

    @PostConstruct
    public void initialize() {
        redisDataLoader.loadData();
    }
}
