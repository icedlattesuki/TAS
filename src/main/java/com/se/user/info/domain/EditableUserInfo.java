package com.se.user.info.domain;

import org.springframework.web.multipart.MultipartFile;

public class EditableUserInfo {
    private MultipartFile image;
    private String imageLocation;
    private String signature;
    private String profile;

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
}
