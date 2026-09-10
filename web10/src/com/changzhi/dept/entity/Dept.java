package com.changzhi.dept.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  数据库表中的每一行记录，映射一个Java对象
 *  Java对象所在的包一般命名为entity（实体类）
 */
public class Dept {
    private Integer deptNo;        // 对应数据库 deptno
    private String deptName;       // 对应数据库 dname
    private String loc;            // 对应数据库 loc
    private String contactPerson;
    private String contactPhone;
    private Date buildDate;
    private BigDecimal budget;
    private String description;       // 对应数据库 loc

    public Dept() {}

    public Dept(Integer deptNo, String deptName, String loc) {
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.loc = loc;
    }

    public Dept(Integer deptNo, String deptName, String loc, String contactPerson, String contactPhone, Date buildDate, BigDecimal budget, String description) {
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.loc = loc;
        this.contactPerson = contactPerson;
        this.contactPhone = contactPhone;
        this.buildDate = buildDate;
        this.budget = budget;
        this.description = description;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptNo=" + deptNo +
                ", deptName='" + deptName + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
