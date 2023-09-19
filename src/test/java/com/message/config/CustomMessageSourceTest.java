package com.message.config;

import com.message.domain.Code;
import com.message.service.CodeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class CustomMessageSourceTest {

    @Autowired
    MessageSource customMessageSource;

    @Autowired
    CodeService codeService;

    @BeforeEach
    public void init() {
        codeService.insert(new Code("ko", "UTT400", "메시지테스트1 입니다."));
        codeService.insert(new Code("ko", "UTT401", "메시지테스트2 입니다."));
        codeService.insert(new Code("ko", "UTT402", "메시지테스트3 입니다."));
        codeService.insert(new Code("ko", "UTT403", "메시지테스트4 입니다."));
        codeService.insert(new Code("ko", "UTT404", "메시지테스트5 입니다."));
        codeService.insert(new Code("ko", "UTT405", "메시지테스트6 입니다."));
        codeService.insert(new Code("ko", "UTT406", "메시지테스트7 입니다."));
        codeService.insert(new Code("ko", "UTT407", "메시지테스트8 입니다."));
        codeService.insert(new Code("ko", "UTT408", "메시지테스트9 입니다."));
    }

    @Test
    void messageSourceTest() {
        String message1 = customMessageSource.getMessage("UTT400", null, Locale.KOREA);
        log.info("message1 = {}", message1);

        String message2 = customMessageSource.getMessage("UTT400", null, Locale.KOREA);
        log.info("message2 = {}", message2);

        String message3 = customMessageSource.getMessage("UTT400", null, Locale.KOREA);
        log.info("message2 = {}", message3);
    }
}