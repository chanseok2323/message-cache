package com.message.web;

import com.message.domain.Code;
import com.message.dto.CodeDto;
import com.message.service.CodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CodeController {
    private final CodeService codeService;
    private final MessageSource customMessageSource;

    @GetMapping(value = "/code")
    public Code getCode(@RequestBody CodeDto codeDto) {
        log.info("codeDto = {}", codeDto);
        String message = customMessageSource.getMessage("UTT400", null, Locale.KOREA);
        log.info("messageSource = {}", message);
        return codeService.findByKey(codeDto.getMessageKey());
    }
}
