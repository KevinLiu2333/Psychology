package com.klsw.piano.util;

public class AliyunProperties {
    private String accessKeyId;

    private String accessKeySecret;

    private String ossendpoint;

    private String ossdomain;

    private String ossbucket;

    public String getOssdomain() {
        return ossdomain;
    }

    public void setOssdomain(String ossdomain) {
        this.ossdomain = ossdomain;
    }

    public String getOssbucket() {
        return ossbucket;
    }

    public void setOssbucket(String ossbucket) {
        this.ossbucket = ossbucket;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getOssendpoint() {
        return ossendpoint;
    }

    public void setOssendpoint(String ossendpoint) {
        this.ossendpoint = ossendpoint;
    }
}
