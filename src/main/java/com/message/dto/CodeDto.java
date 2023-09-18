package com.message.dto;

import lombok.Data;

@Data
public class CodeDto {
    private Long no;
    private String beforeMessageKey;
    private String messageKey;
    private String message;

    public CodeDto(Long no, String beforeMessageKey, String messageKey, String message) {
        this.no = no;
        this.beforeMessageKey = beforeMessageKey;
        this.messageKey = messageKey;
        this.message = message;
    }
}
