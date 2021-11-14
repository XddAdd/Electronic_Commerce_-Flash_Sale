package com.add.service.impl;

import com.add.service.CacheService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
public class CacheServiceImpl implements CacheService {

    private Cache<String, Object> commonCache = null;

    @PostConstruct
    public void init() {
        commonCache = CacheBuilder.newBuilder()
                //设置缓存容器初始容量为10
                .initialCapacity(10)
                //设置缓存中最大可存储100个key，超过100个会按照LRU策略执行
                .maximumSize(100)
                //设置写缓存后的过期时间
                .expireAfterWrite(60, TimeUnit.SECONDS).build();
    }


    @Override
    public void setCommonCache(String key, Object value) {
        commonCache.put(key, value);
    }

    @Override
    public Object getFromCommonCache(String key) {
        return commonCache.getIfPresent(key);
    }
}
