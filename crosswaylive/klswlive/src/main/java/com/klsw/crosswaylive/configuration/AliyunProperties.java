package com.klsw.crosswaylive.configuration;

public class AliyunProperties {
    private String accessKeyId;

    private String accessKeySecret;

    private String ossendpoint;

    private String ossdomain;

    private String ossbucket;

    private String mts_region;

    public String getMts_region() {
        return mts_region;
    }

    public void setMts_region(String mts_region) {
        this.mts_region = mts_region;
    }

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
