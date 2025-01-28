package com.project.dalda.search.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dalda.alcohol.service.AlcoholService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RedisDataLoader {
    private final RedisTemplate<String, Object> redisTemplate;
    private final AlcoholService alcoholService;

    public void loadData() {
        ObjectMapper objectMapper = new ObjectMapper();
        Object soju = objectMapper.convertValue(alcoholService.getSojuAlcohols(), Object.class);
        Object beer = objectMapper.convertValue(alcoholService.getBeerAlcohols(), Object.class);
        Object sake = objectMapper.convertValue(alcoholService.getSakeAlcohols(), Object.class);
        Object traditionalLiquor = objectMapper.convertValue(alcoholService.getTraditionalLiquorAlcohols(), Object.class);
        Object wine = objectMapper.convertValue(alcoholService.getWineAlcohols(), Object.class);
        Object whisky = objectMapper.convertValue(alcoholService.getWhiskyAlcohols(), Object.class);
        redisTemplate.opsForValue().set("soju", soju);
        redisTemplate.opsForValue().set("beer", beer);
        redisTemplate.opsForValue().set("sake", sake);
        redisTemplate.opsForValue().set("traditionalLiquor", traditionalLiquor);
        redisTemplate.opsForValue().set("wine", wine);
        redisTemplate.opsForValue().set("whisky", whisky);
    }
}
