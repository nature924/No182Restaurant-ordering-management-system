package com.entity.model;

import com.entity.GuanggaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 广告
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class GuanggaoModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 店家
     */
    private Integer shangjiaId;


    /**
     * 广告标题
     */
    private String guanggaoName;


    /**
     * 广告照片
     */
    private String guanggaoPhoto;


    /**
     * 广告类型
     */
    private Integer guanggaoTypes;


    /**
     * 广告热度
     */
    private Integer guanggaoClicknum;


    /**
     * 广告详情
     */
    private String guanggaoContent;


    /**
     * 逻辑删除
     */
    private Integer guanggaoDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
