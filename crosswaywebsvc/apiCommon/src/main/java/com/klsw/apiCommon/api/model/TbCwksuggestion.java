package com.klsw.apiCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_CWKSuggestion")
public class TbCwksuggestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * ��������
	 */
	private String suggestion;

	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * ͼƬ·��
	 */
	private String imgpath;

	private Integer proposerid;
 

	public Integer getProposerid() {
		return proposerid;
	}

	public void setProposerid(Integer proposerid) {
		this.proposerid = proposerid;
	}

	/**
	 * ��������
	 */
	private String suggestiontype;

	/**
	 * ���ʱ��
	 */
	private Date addtime;

	/**
	 * �ظ�/����ʱ��
	 */
	private Date replytime;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ��ȡ��������
	 *
	 * @return suggestion - ��������
	 */
	public String getSuggestion() {
		return suggestion;
	}

	/**
	 * ���ý�������
	 *
	 * @param suggestion
	 *            ��������
	 */
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	/**
	 * ��ȡͼƬ·��
	 *
	 * @return imgpath - ͼƬ·��
	 */
	public String getImgpath() {
		return imgpath;
	}

	/**
	 * ����ͼƬ·��
	 *
	 * @param imgpath
	 *            ͼƬ·��
	 */
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
  
	public String getSuggestiontype() {
		return suggestiontype;
	}

	public void setSuggestiontype(String suggestiontype) {
		this.suggestiontype = suggestiontype;
	}

	/**
	 * ��ȡ���ʱ��
	 *
	 * @return addtime - ���ʱ��
	 */
	public Date getAddtime() {
		return addtime;
	}

	/**
	 * �������ʱ��
	 *
	 * @param addtime
	 *            ���ʱ��
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	/**
	 * ��ȡ�ظ�/����ʱ��
	 *
	 * @return replytime - �ظ�/����ʱ��
	 */
	public Date getReplytime() {
		return replytime;
	}

	/**
	 * ���ûظ�/����ʱ��
	 *
	 * @param replytime
	 *            �ظ�/����ʱ��
	 */
	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}
}