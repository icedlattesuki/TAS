package com.se.course.video.domain;

import com.se.global.domain.File;

/**
 * @author Yusen
 * @version 1.0
 * @since 1.0
 */
public class Video extends File {
    private String title;
    private String profile;

    public Video() {

    }

    public Video(File file) {
        super(file);
    }

    public Video(File file, String title, String profile) {
        super(file);
        this.title = title;
        this.profile = profile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
