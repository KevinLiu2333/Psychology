package com.klsw.website.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class UserLoginForm {

    @NotEmpty(message = "手机号不能为空")
    private String phone;
    @NotEmpty(message = "密码不能为空")
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //hibernate validation验证。
    @NotEmpty(message = "用户名不能为空")
    private String mobilePhone;
    @NotBlank(message = "密码不能为空")
    private String userPassword1;
    @NotBlank(message = "两次密码输入不一致")
    private String rePassword1;

    @Email(message = "请输入正确格式的邮箱")
    private String emailAddress;

	/* SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");*/

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getUserPassword1() {
        return userPassword1;
    }

    public void setUserPassword1(String userPassword1) {
        this.userPassword1 = userPassword1;
    }

    public String getRePassword1() {
        return rePassword1;
    }

    public void setRePassword1(String rePassword1) {
        this.rePassword1 = rePassword1;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


}
