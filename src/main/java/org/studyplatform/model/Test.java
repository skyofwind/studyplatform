package org.studyplatform.model;

/**
 * Created by dzj on 2017/6/17.
 */
public class Test {
    private int cid;

    private int[] question;

    private String[] myanswer;

    public int[] getQuestion() {
        return question;
    }

    public String[] getMyanswer() {
        return myanswer;
    }

    public void setMyanswer(String[] myanswer) {
        this.myanswer = myanswer;
    }

    public void setQuestion(int[] question) {
        this.question = question;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
