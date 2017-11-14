package com.tuodao.bp.model.business.product.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BorrowExpandOutput implements Serializable {

    private static final long serialVersionUID = -2641804776078826451L;
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
    private String description;
    /** 项目简介 **/
    private String intro;
    /** 项目说明类型 0：抵押1:质押 2:4s店合作标 **/
    private Integer explaindType;
    /** 项目说明内容 **/
    private String explaind;
    /** 添加时间 **/
    private Date addTime;


    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getRepaySource() {
        return this.repaySource;
    }

    public void setRepaySource(String repaySource) {
        this.repaySource = repaySource;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEducation() {
        return this.education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCensus() {
        return this.census;
    }

    public void setCensus(String census) {
        this.census = census;
    }

    public String getCarBrand() {
        return this.carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return this.carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Date getBuyTime() {
        return this.buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public BigDecimal getBuyPrice() {
        return this.buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getDriveKilometers() {
        return this.driveKilometers;
    }

    public void setDriveKilometers(BigDecimal driveKilometers) {
        this.driveKilometers = driveKilometers;
    }

    public BigDecimal getSecondCarPrice() {
        return this.secondCarPrice;
    }

    public void setSecondCarPrice(BigDecimal secondCarPrice) {
        this.secondCarPrice = secondCarPrice;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getExplaindType() {
        return this.explaindType;
    }

    public void setExplaindType(Integer explaindType) {
        this.explaindType = explaindType;
    }

    public Object getExplaind() {
        return this.explaind;
    }

    public void setExplaind(String explaind) {
        this.explaind = explaind;
    }

    public Date getAddTime() {
        return this.addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}