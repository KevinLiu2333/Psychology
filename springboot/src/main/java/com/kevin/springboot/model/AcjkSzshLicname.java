package com.kevin.springboot.model;

import javax.persistence.*;

@Table(name = "ACJK_SZSH_LICNAME")
public class AcjkSzshLicname {
    /**
     * 许可事项名称
     */
    @Column(name = "LIC_NAME")
    private String licName;

    /**
     * 获取许可事项名称
     *
     * @return LIC_NAME - 许可事项名称
     */
    public String getLicName() {
        return licName;
    }

    /**
     * 设置许可事项名称
     *
     * @param licName 许可事项名称
     */
    public void setLicName(String licName) {
        this.licName = licName;
    }
}