package com.etjava.util;

/**
 * 课程图片相关常量类 - 避免反射修改
 */
public enum SystemFilePath {

    AUTO_COURSE_ORIGINAL("D:\\classroom\\model.jpg\\"),
    AUTO_COURSE_TARGET("D:\\classroom\\courseImages\\");

    private String url;

    SystemFilePath(String envUrl) {
        this.url = envUrl;
    }

    public String getUrl() {
        return url;
    }
}
