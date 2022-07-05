package com.learning.pojo;

import java.util.Date;

public class ErrorMapperAdvanced {
    private int status;
    private String message;
    private Date date;
    public ErrorMapperAdvanced(int status, String message, Date date) {
        super();
        this.status = status;
        this.message = message;
        this.date = date;
    }
    public ErrorMapperAdvanced() {
        super();
        // TODO Auto-generated constructor stub
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getErrorMsg() {
        return message;
    }
    public void setErrorMsg(String message) {
        this.message = message;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "ErrorMapper [status=" + status + ", message=" + message + ", date=" + date + "]";
    }


}
