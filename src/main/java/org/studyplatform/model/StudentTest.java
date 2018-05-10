package org.studyplatform.model;

/**
 * Created by dzj on 2017/6/20.
 */
public class StudentTest {
    private int cid;

    private int[] single_question;

    private String[] single_myanswer;

    private int[] tof_question;

    private String[] tof_myanswer;

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCid() {
        return cid;
    }

    public int[] getSingle_question() {
        return single_question;
    }

    public int[] getTof_question() {
        return tof_question;
    }

    public String[] getSingle_myanswer() {
        return single_myanswer;
    }

    public String[] getTof_myanswer() {
        return tof_myanswer;
    }

    public void setSingle_myanswer(String[] single_myanswer) {
        this.single_myanswer = single_myanswer;
    }

    public void setSingle_question(int[] single_question) {
        this.single_question = single_question;
    }

    public void setTof_myanswer(String[] tof_myanswer) {
        this.tof_myanswer = tof_myanswer;
    }

    public void setTof_question(int[] tof_question) {
        this.tof_question = tof_question;
    }
}
