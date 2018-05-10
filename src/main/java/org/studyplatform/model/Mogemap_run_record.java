package org.studyplatform.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Mogemap_run_record {
    private Integer rid;

    private String runtype;

    private Double distance;

    private Integer runtime;

    private Integer calories;

    private Date mdate;

    private String json;

    private String phone;

    public Integer getId() {
        return rid;
    }

    public void setId(Integer id) {
        this.rid = id;
    }

    public String getRuntype() {
        return runtype;
    }

    public void setRuntype(String runtype) {
        this.runtype = runtype == null ? null : runtype.trim();
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Date getDate() {
        return mdate;
    }

    public void setDate(Date date) {
        this.mdate = date;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json == null ? null : json.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}