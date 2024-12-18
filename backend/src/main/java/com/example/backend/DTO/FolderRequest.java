package com.example.backend.DTO;

import java.util.List;

public class FolderRequest {
    private long userId;
    private String name;
    private List<String> subjects;
    private List<String> emails;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> list1) {
        this.subjects = list1;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> list2) {
        this.emails = list2;
    }
}
