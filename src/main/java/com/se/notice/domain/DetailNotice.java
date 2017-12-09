package com.se.notice.domain;

import java.util.ArrayList;
import java.util.Date;

public class DetailNotice {
    private int totalNumber;
    private ArrayList<String> messageList;
    private ArrayList<Integer> messageIdList;
    private ArrayList<Date> messageDateList;

    public DetailNotice() {
        totalNumber = 0;
        messageList = new ArrayList<String>();
        messageIdList = new ArrayList<Integer>();
        messageDateList = new ArrayList<Date>();
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public ArrayList<String> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<String> messageList) {
        this.messageList = messageList;
    }

    public ArrayList<Integer> getMessageIdList() {
        return messageIdList;
    }

    public void setMessageIdList(ArrayList<Integer> messageIdList) {
        this.messageIdList = messageIdList;
    }

    public ArrayList<Date> getMessageDateList() {
        return messageDateList;
    }

    public void setMessageDateList(ArrayList<Date> messageDateList) {
        this.messageDateList = messageDateList;
    }
}
