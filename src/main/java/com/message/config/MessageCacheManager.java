package com.message.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageCacheManager {

    private final CacheManager cacheManager;
    private static final String MESSAGE_KEY = "message";
    private final Locale locale = LocaleContextHolder.getLocale();

    private String getKey() {
        return locale.getLanguage();
    }

    public void reload(String key, Object message) {
        Cache cache = clear(getCache());
        cache.put(getKey(), message);
    }

    private Cache clear(Cache cache) {
        cache.clear();
        return cache;
    }

    public Object get(String key) {
        Cache.ValueWrapper wrapper = getCache().get(key);
        return wrapper.get();
    }

    public void put(String code, String value) {
        Cache.ValueWrapper wrapper = getCache().get(getKey());
        Map<String, String> map = put(code, value, wrapper);
        Cache cache = getCache();
        cache.put(getKey(), map);
    }

    private Map<String, String> put(String code, String value, Cache.ValueWrapper wrapper) {
        Map<String, String> map = (Map<String, String>) wrapper.get();
        map.put(code, value);
        return map;
    }

    private Cache getCache() {
        return cacheManager.getCache(MESSAGE_KEY);
    }
}
