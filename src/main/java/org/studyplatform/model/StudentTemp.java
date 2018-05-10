package org.studyplatform.model;

/**
 * Created by dzj on 2017/6/21.
 */
public class StudentTemp {
    private String username;

    private String password;

    private String name;

    private String college;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getCollege() {
        return college;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
