package com.klsw.apiCommon.api.model;

public class Teacher extends TbCwk {

	private String graduatedUniversity;
	private String teachGrade;
	private String certificatePath;
	private Integer authenticationProcess;
	private Boolean isauthentication;
	private Float averageScore;
	private Integer teachExperience;
	public String getGraduatedUniversity() {
		return graduatedUniversity;
	}
	public void setGraduatedUniversity(String graduatedUniversity) {
		this.graduatedUniversity = graduatedUniversity;
	}
	public String getTeachGrade() {
		return teachGrade;
	}
	public void setTeachGrade(String teachGrade) {
		this.teachGrade = teachGrade;
	}
	public String getCertificatePath() {
		return certificatePath;
	}
	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}
	public Integer getAuthenticationProcess() {
		return authenticationProcess;
	}
	public void setAuthenticationProcess(Integer authenticationProcess) {
		this.authenticationProcess = authenticationProcess;
	}
	public Boolean getIsauthentication() {
		return isauthentication;
	}
	public void setIsauthentication(Boolean isauthentication) {
		this.isauthentication = isauthentication;
	}
	public Float getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(Float averageScore) {
		this.averageScore = averageScore;
	}
	public Integer getTeachExperience() {
		return teachExperience;
	}
	public void setTeachExperience(Integer teachExperience) {
		this.teachExperience = teachExperience;
	}
	
}
