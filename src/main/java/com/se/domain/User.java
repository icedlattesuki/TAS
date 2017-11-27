package com.se.domain;

public class User {
    private String id;
    private String name;
    private String college;
    private String imageLocation;
    private String signature;
    private String profile;
    private String email;
    private int type;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCollege() {
        return college;
    }

    public String getImageLocation() { return imageLocation; }

    public String getSignature() {
        return signature;
    }

    public String getProfile() {
        return profile;
    }

    public String getEmail() {
        return email;
    }

    public int getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(int type) {
        this.type = type;
    }
}
