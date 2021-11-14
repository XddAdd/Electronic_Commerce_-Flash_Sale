package com.add.service;

//封装本地缓存操作类
public interface CacheService {

    //存入cache
    void setCommonCache(String key,Object value);

    //从cache中取出
    Object getFromCommonCache(String key);

}
