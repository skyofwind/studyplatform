package org.studyplatform.model;

import java.util.Date;

public class Message {
    private Integer id;

    private Integer sid;

    private Integer cid;

    private Date time;

    private Integer setionid;

    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getSetionid() {
        return setionid;
    }

    public void setSetionid(Integer setionid) {
        this.setionid = setionid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}