package org.studyplatform.other;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.UUID;

public class DownloadRecord {
    public static final int STATUS_ERROR = 0;
    public static final int STATUS_SUCCESS = 1;
    private String uid;
    private String ip;
    private int port;
    private String ua;
    private String fileName;
    private String filePath;
    private long length;
    private int status;
    private Timestamp startTime;
    private Timestamp endTime;

    public DownloadRecord() {
    }

    public DownloadRecord(String fileName, String filePath,
                          HttpServletRequest request) {
        this.uid = UUID.randomUUID().toString().replace("-","");
        this.fileName = fileName;
        this.filePath = filePath;
        this.ip = request.getRemoteAddr();
        this.port = request.getRemotePort();
        this.ua = this.ua = request.getHeader("user-agent");
        this.startTime = new Timestamp(System.currentTimeMillis());
    }

    public int getPort() {
        return port;
    }

    public int getStatus() {
        return status;
    }

    public long getLength() {
        return length;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getIp() {
        return ip;
    }

    public String getUa() {
        return ua;
    }

    public String getUid() {
        return uid;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    /** getter and setter **/
}
