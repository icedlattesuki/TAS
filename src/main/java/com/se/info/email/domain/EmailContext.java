package com.se.info.email.domain;

import java.util.UUID;

public class EmailContext {
    private String subject;
    private String text;
    private boolean isHtml;
    private String uuid;

    public EmailContext() {
        subject = "TAS邮箱验证";
        isHtml = true;
        uuid = UUID.randomUUID().toString();
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getUuid() {
        return uuid;
    }

    public boolean getIsHtml() {
        return isHtml;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIsHtml(boolean isHtml) {
        this.isHtml = isHtml;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
