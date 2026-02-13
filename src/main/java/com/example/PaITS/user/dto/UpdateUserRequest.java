package com.example.PaITS.user.dto;

public class UpdateUserRequest {

    private String fullName;
    private String username;
    private String password;
    private String avatarUrl;

    public UpdateUserRequest(String fullName, String username, String password, String avatarUrl) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.avatarUrl = avatarUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
