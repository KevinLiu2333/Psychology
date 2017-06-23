package com.klsw.apiCommon.api.model;


public class TeacherParam {

	private String teachGrade;
	private Float minPrice;
	private Float maxPrice;
	private String university;
	private String region;
	private Integer studentId;
	private Integer pianoGrade;
	
	public String getTeachGrade() {
		return teachGrade;
	}
	public void setTeachGrade(String teachGrade) {
		this.teachGrade = teachGrade;
	}
	public Float getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}
	public Float getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getPianoGrade() {
		return pianoGrade;
	}
	public void setPianoGrade(Integer pianoGrade) {
		this.pianoGrade = pianoGrade;
	}
	
}
