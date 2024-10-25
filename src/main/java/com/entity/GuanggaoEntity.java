package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 广告
 *
 * @author 
 * @email
 */
@TableName("guanggao")
public class GuanggaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public GuanggaoEntity() {

	}

	public GuanggaoEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 店家
     */
    @ColumnInfo(comment="店家",type="int(11)")
    @TableField(value = "shangjia_id")

    private Integer shangjiaId;


    /**
     * 广告标题
     */
    @ColumnInfo(comment="广告标题",type="varchar(200)")
    @TableField(value = "guanggao_name")

    private String guanggaoName;


    /**
     * 广告照片
     */
    @ColumnInfo(comment="广告照片",type="varchar(200)")
    @TableField(value = "guanggao_photo")

    private String guanggaoPhoto;


    /**
     * 广告类型
     */
    @ColumnInfo(comment="广告类型",type="int(11)")
    @TableField(value = "guanggao_types")

    private Integer guanggaoTypes;


    /**
     * 广告热度
     */
    @ColumnInfo(comment="广告热度",type="int(11)")
    @TableField(value = "guanggao_clicknum")

    private Integer guanggaoClicknum;


    /**
     * 广告详情
     */
    @ColumnInfo(comment="广告详情",type="longtext")
    @TableField(value = "guanggao_content")

    private String guanggaoContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "guanggao_delete")

    private Integer guanggaoDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：店家
	 */
    public Integer getShangjiaId() {
        return shangjiaId;
    }
    /**
	 * 设置：店家
	 */

    public void setShangjiaId(Integer shangjiaId) {
        this.shangjiaId = shangjiaId;
    }
    /**
	 * 获取：广告标题
	 */
    public String getGuanggaoName() {
        return guanggaoName;
    }
    /**
	 * 设置：广告标题
	 */

    public void setGuanggaoName(String guanggaoName) {
        this.guanggaoName = guanggaoName;
    }
    /**
	 * 获取：广告照片
	 */
    public String getGuanggaoPhoto() {
        return guanggaoPhoto;
    }
    /**
	 * 设置：广告照片
	 */

    public void setGuanggaoPhoto(String guanggaoPhoto) {
        this.guanggaoPhoto = guanggaoPhoto;
    }
    /**
	 * 获取：广告类型
	 */
    public Integer getGuanggaoTypes() {
        return guanggaoTypes;
    }
    /**
	 * 设置：广告类型
	 */

    public void setGuanggaoTypes(Integer guanggaoTypes) {
        this.guanggaoTypes = guanggaoTypes;
    }
    /**
	 * 获取：广告热度
	 */
    public Integer getGuanggaoClicknum() {
        return guanggaoClicknum;
    }
    /**
	 * 设置：广告热度
	 */

    public void setGuanggaoClicknum(Integer guanggaoClicknum) {
        this.guanggaoClicknum = guanggaoClicknum;
    }
    /**
	 * 获取：广告详情
	 */
    public String getGuanggaoContent() {
        return guanggaoContent;
    }
    /**
	 * 设置：广告详情
	 */

    public void setGuanggaoContent(String guanggaoContent) {
        this.guanggaoContent = guanggaoContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getGuanggaoDelete() {
        return guanggaoDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setGuanggaoDelete(Integer guanggaoDelete) {
        this.guanggaoDelete = guanggaoDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Guanggao{" +
            ", id=" + id +
            ", shangjiaId=" + shangjiaId +
            ", guanggaoName=" + guanggaoName +
            ", guanggaoPhoto=" + guanggaoPhoto +
            ", guanggaoTypes=" + guanggaoTypes +
            ", guanggaoClicknum=" + guanggaoClicknum +
            ", guanggaoContent=" + guanggaoContent +
            ", guanggaoDelete=" + guanggaoDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
