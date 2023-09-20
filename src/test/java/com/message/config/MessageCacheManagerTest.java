package com.message.config;

import com.message.domain.Code;
import com.message.service.CodeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MessageCacheManagerTest {

    @Autowired
    CodeService codeService;

    @Autowired
    MessageCacheManager messageCacheManager;

    @Autowired
    CacheManager cacheManager;

    @Test
    void cacheManagerTest() {
        Map<String, String> map = codeService.findByLanguage("ko").stream()
                                    .collect(Collectors.toMap(Code::getMessageKey, Code::getMessage));

        messageCacheManager.reload("ko", map);

        Cache cache = cacheManager.getCache("message");
        Cache.ValueWrapper wrapper = cache.get("ko");
        Map<String, String> cacheCodes = (Map<String, String>) wrapper.get();

        Set<String> keys = cacheCodes.keySet();
        keys.forEach(v -> log.info("before modify key = {}, value = {}", v, cacheCodes.get(v)));

        messageCacheManager.put("UTT400", "메시지10000");

        Cache afterCache = cacheManager.getCache("message");
        Cache.ValueWrapper afterWrapper = afterCache.get("ko");
        Map<String, String> afterCacheCodes = (Map<String, String>) afterWrapper.get();

        Set<String> afterKey = afterCacheCodes.keySet();
        afterKey.forEach(v -> log.info("after modify key = {}, value = {}", v, afterCacheCodes.get(v)));

    }
}