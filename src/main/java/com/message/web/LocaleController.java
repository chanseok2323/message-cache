package com.message.web;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocaleController {

    @GetMapping("/locale")
    public String locale() {
        return LocaleContextHolder.getLocale().getLanguage();
    }

}
