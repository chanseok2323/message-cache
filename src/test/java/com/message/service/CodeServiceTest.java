package com.message.service;

import com.message.domain.Code;
import com.message.dto.CodeDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;


@Slf4j
@Transactional
@SpringBootTest
class CodeServiceTest {

    @Autowired
    CodeService codeService;

    @Autowired
    CacheManager cacheManager;

    @Test
    void cache() {
        Code code1 = codeService.findByKey("UTT400");
        log.info("code1 = {}", code1);

        Code code2 = codeService.findByKey("UTT400");
        log.info("code2 = {}", code2);

        Code code3 = codeService.findByKey("UTT400");
        log.info("code3 = {}", code3);
    }

    @Test
    void update() {
        Code beforeCode = codeService.findByKey("UTT400");

        assertThat(beforeCode.getNo()).isEqualTo(1L);
        assertThat(beforeCode.getMessageKey()).isEqualTo("UTT400");
        assertThat(beforeCode.getMessage()).isEqualTo("메시지테스트1 입니다.");

        Code modify = codeService.update(new CodeDto(beforeCode.getNo(), "UTT400","UTT420", "메시지테스트10 입니다."));

        Code afterCode1 = codeService.findByKey("UTT400");
        log.info("after1 = {}", afterCode1);
        Code afterCode2 = codeService.findByKey("UTT420");
        log.info("after2 = {}", afterCode2);
        Code afterCode3 = codeService.findByKey("UTT420");
        log.info("afterCode3 = {}", afterCode3);

        assertThat(modify.getNo()).isEqualTo(afterCode1.getNo());
        assertThat(modify.getMessageKey()).isEqualTo(afterCode1.getMessageKey());
        assertThat(modify.getMessage()).isEqualTo(afterCode1.getMessage());

        assertThat(modify.getNo()).isEqualTo(afterCode2.getNo());
        assertThat(modify.getMessageKey()).isEqualTo(afterCode2.getMessageKey());
        assertThat(modify.getMessage()).isEqualTo(afterCode2.getMessage());

        Collection<String> cacheNames = cacheManager.getCacheNames();
        cacheNames.forEach(v ->
                {
                    Cache cache = cacheManager.getCache(v);
                    Cache.ValueWrapper utt400 = cache.get("UTT400");
                    log.info("UTT400 = {}", utt400.get());


                    Cache.ValueWrapper utt420 = cache.get("UTT420");
                    log.info("UTT420 = {}", utt420.get());

                    log.info("UTT400 == UTT420 : {}", utt400.get() == utt420.get());
                }
        );
    }
}