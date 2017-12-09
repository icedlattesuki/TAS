package com.se.user.email.domain;

import java.util.UUID;

/**
 * 封装邮件的内容
 *
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
public class EmailContext {
    private String subject;
    private String text;
    private boolean isHtml;
    private String uuid;

    /**
     * 默认构造函数，对一些属性进行默认设置，便于用户使用
     */
    public EmailContext() {
        subject = "TAS邮箱验证";
        isHtml = true;
        /**
         * <p>
         * uuid是一个随机字符串，其附在邮件URL末尾，不仅可以防止学号或工号泄露，
         * 也可通过它找到对应的User对象
         * </p>
         */
        uuid = UUID.randomUUID().toString();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsHtml() {
        return isHtml;
    }

    public void setIsHtml(boolean isHtml) {
        this.isHtml = isHtml;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
