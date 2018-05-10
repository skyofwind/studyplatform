package org.studyplatform.model;

/**
 * Created by dzj on 2017/6/21.
 */
public class MessageTemp {
    private int cid;

    private int setionid;

    private String message;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getSetionid() {
        return setionid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSetionid(int setionid) {
        this.setionid = setionid;
    }
}
