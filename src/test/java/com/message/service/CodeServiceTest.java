package com.message.service;

import com.message.domain.Code;
import com.message.dto.CodeDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@Slf4j
@Transactional
@SpringBootTest
class CodeServiceTest {

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

        Code modify = codeService.update(new CodeDto(1L, "UTT400", "메시지테스트10 입니다."));
        Code afterCode1 = codeService.findByKey("UTT400");
        Code afterCode2 = codeService.findByKey("UTT400");

        assertThat(modify.getNo()).isEqualTo(afterCode1.getNo());
        assertThat(modify.getMessageKey()).isEqualTo(afterCode1.getMessageKey());
        assertThat(modify.getMessage()).isEqualTo(afterCode1.getMessage());

        assertThat(modify.getNo()).isEqualTo(afterCode2.getNo());
        assertThat(modify.getMessageKey()).isEqualTo(afterCode2.getMessageKey());
        assertThat(modify.getMessage()).isEqualTo(afterCode2.getMessage());
    }
}