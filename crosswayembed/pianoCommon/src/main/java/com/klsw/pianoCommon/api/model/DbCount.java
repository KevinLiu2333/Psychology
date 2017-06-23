package com.klsw.pianoCommon.api.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "db_count")
public class DbCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型：0表示曲谱名，1表示书名
     */
    private Integer type;

    /**
     * 名字
     */
    private String name;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;
    
    /**
     * 标签
     */
    @Column(name = "label")
    private String label;
    
    /**
     * 路径
     */
    @Column(name = "path")
    private String path;
    
    /**
     * 曲谱对应的副标题
     */
    @Column(name = "assistant")
    private String assistant;

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
     * 获取类型：0表示曲谱名，1表示书名
     *
     * @return type - 类型：0表示曲谱名，1表示书名
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型：0表示曲谱名，1表示书名
     *
     * @param type 类型：0表示曲谱名，1表示书名
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取数量
     *
     * @return count - 数量
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置数量
     *
     * @param count 数量
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取添加时间
     *
     * @return add_time - 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 设置添加时间
     *
     * @param addTime 添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取标签
     * @return
     */
	public String getLabel() {
		return label;
	}

	/**
	 * 设置标签
	 * @param label 标签
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * 获取路径
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 设置路径
	 * @param path 路径
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 获取曲谱副标题
	 * @return
	 */
	public String getAssistant() {
		return assistant;
	}

	/**
	 * 设置曲谱副标题
	 * @param assistant 曲谱副标题
	 */
	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}
    
}