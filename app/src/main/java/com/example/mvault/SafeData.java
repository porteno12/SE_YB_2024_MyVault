package com.example.mvault;

public class SafeData {
    private String user_name;
    private String password;
    private String url;

    public SafeData(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }

    public SafeData(String user_name, String password, String url) {
        this.user_name = user_name;
        this.password = password;
        this.url = url;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
