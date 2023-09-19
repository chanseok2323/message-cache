package com.message.config;

import com.message.domain.Code;
import com.message.service.CodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class CustomMessageSource extends AbstractMessageSource {
    private final CodeService codeService;

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        Code messageKey = codeService.findByKey(code);
        return new MessageFormat(messageKey.getMessage(), locale);
    }
}
