package org.cockloft.common.cache;

import io.vertx.core.impl.ConcurrentHashSet;
import org.cockloft.common.enums.StatusEnum;
import org.cockloft.common.example.CacheException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RamCache implements Cache{

    private static RamCache ramCache = new RamCache();

    public static RamCache getRam(){
        return  ramCache;
    }

    private Map<String,String> keyValue;
    private Set<String> keys;
    private Map<String,Map<String,String>> mapKv;

    public RamCache(){
        super();
        this.keys = new ConcurrentHashSet<>();
        this.keyValue = new ConcurrentHashMap<>();
        this.mapKv = new ConcurrentHashMap<>();
    }

    @Override
    public boolean addKV(String key, String value) {
        this.keyValue.put(key,value);
        return true;
    }

    @Override
    public boolean addMap(String key, String value1,String value2) {
        Map<String,String> valueMap= this.mapKv.get(key);
        if(valueMap==null){
            valueMap = new ConcurrentHashMap<>();
            this.mapKv.put(key,valueMap);
        }
        valueMap.put(value1,value2);
        return true;
    }

    @Override
    public boolean addMap(String key, Map<String, String> map) {
        Map<String,String> valueMap= this.mapKv.get(key);
        if(valueMap==null){
            valueMap = new ConcurrentHashMap<>();
            this.mapKv.put(key,valueMap);
        }
        valueMap.putAll(map);
        return true;
    }

    @Override
    public String getV(String key) {
        String item = this.keyValue.get(key);
        if(item==null){
            item = "";
        }
        return item;
    }

    @Override
    public String getMv(String key, String vk) throws CacheException {
        throw new CacheException(CacheException.CACHE_DISABLE, StatusEnum.CACHE_ERROR);
    }

    @Override
    public Set<String> getKeys() {
        return this.keys;
    }

    @Override
    public Set<String> getKeyS(String patten) {
        Set<String> res = new HashSet<>();
        if(this.keys.contains(patten)){
            res.add(patten);
        }
        return res;
    }

    @Override
    public Set<String> getVs(List<String> keys) throws CacheException {
        throw new CacheException(CacheException.CACHE_DISABLE, StatusEnum.CACHE_ERROR);
    }
}
