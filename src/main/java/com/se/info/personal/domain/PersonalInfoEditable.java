package com.se.info.personal.domain;

import org.springframework.web.multipart.MultipartFile;

public class PersonalInfoEditable {
    private MultipartFile image;
    private String imageLocation;
    private String signature;
    private String profile;
    private String email;

    public MultipartFile getImage() {
        return image;
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

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public void setImageLocation(String imageLocation) { this.imageLocation = imageLocation; }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
