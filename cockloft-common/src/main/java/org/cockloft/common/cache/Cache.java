package org.cockloft.common.cache;

import org.cockloft.common.example.CacheException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Cache {
    boolean addKV(String key,String value) throws CacheException;
    boolean addMap(String key, String value1,String value2) throws CacheException;
    boolean addMap(String key, Map<String,String> map) throws CacheException;
    String getV(String key) throws CacheException;
    String getMv(String key,String vk) throws CacheException;
    Set<String> getKeys() throws CacheException;
    Set<String> getKeyS(String patten) throws CacheException;
    Set<String> getVs(List<String> keys) throws CacheException;
}
