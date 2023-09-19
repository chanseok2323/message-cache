package com.message.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@ToString
@Table(name = "code_table")
public class Code implements Serializable {

    @Id
    @Column(name = "code_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String language;

    private String messageKey;

    private String message;

    public Code() {}

    public Code(String language, String messageKey, String message) {
        this.language = language;
        this.messageKey = messageKey;
        this.message = message;
    }

    public Code modify(String messageKey, String message) {
        this.messageKey = messageKey;
        this.message = message;
        return this;
    }
}
