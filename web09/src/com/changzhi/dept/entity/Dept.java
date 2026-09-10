package com.changzhi.dept.entity;

/**
 *  数据库表中的每一行记录，映射一个Java对象
 *  Java对象所在的包一般命名为entity（实体类）
 */
public class Dept {
    private Integer deptNo;    // 对应数据库 deptno
    private String deptName;   // 对应数据库 dname
    private String loc;        // 对应数据库 loc


    public Dept(Integer deptNo, String deptName, String loc) {
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.loc = loc;
    }

    public Dept() {}

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

    @Override
    public String toString() {
        return "Dept{" +
                "deptNo=" + deptNo +
                ", deptName='" + deptName + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
