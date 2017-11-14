package com.tuodao.bp.product.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BorrowExpand implements Serializable {
    private static final long serialVersionUID = 6058051941499970165L;
    /** 主键ID **/
    private Integer id;
    /** 产品ID **/
    private Integer productId;
    /** 产品编号 **/
    private String productCode;
    /** 还款来源 **/
    private String repaySource;
    /** 姓名 **/
    private String name;
    /** 性别 0：男 1：女 **/
    private Integer sex;
    /** 年龄 **/
    private Integer age;
    /** 职业 **/
    private String job;
    /** 学历 **/
    private String education;
    /** 户籍 **/
    private String census;
    /** 车辆品牌 **/
    private String carBrand;
    /** 型号 **/
    private String carModel;
    /** 购买时间 **/
    private Date buyTime;
    /** 购买价格 **/
    private BigDecimal buyPrice;
    /** 行驶公里数 **/
    private BigDecimal driveKilometers;
    /** 二手车评估价 **/
    private BigDecimal secondCarPrice;
    /** 业务说明 **/
    private Object description;
    /** 项目简介 **/
    private Object intro;
    /** 项目说明类型 0：抵押1:质押 2:4s店合作标 **/
    private Integer explaindType;
    /** 项目说明内容 **/
    private Object explaind;
    /** 添加时间 **/
    private Date addTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getRepaySource() {
        return repaySource;
    }

    public void setRepaySource(String repaySource) {
        this.repaySource = repaySource == null ? null : repaySource.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getCensus() {
        return census;
    }

    public void setCensus(String census) {
        this.census = census == null ? null : census.trim();
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand == null ? null : carBrand.trim();
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel == null ? null : carModel.trim();
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getDriveKilometers() {
        return driveKilometers;
    }

    public void setDriveKilometers(BigDecimal driveKilometers) {
        this.driveKilometers = driveKilometers;
    }

    public BigDecimal getSecondCarPrice() {
        return secondCarPrice;
    }

    public void setSecondCarPrice(BigDecimal secondCarPrice) {
        this.secondCarPrice = secondCarPrice;
    }

    public Integer getExplaindType() {
        return explaindType;
    }

    public void setExplaindType(Integer explaindType) {
        this.explaindType = explaindType;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}