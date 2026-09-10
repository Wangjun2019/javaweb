package com.changzhi.dept.dao.impl;

import com.changzhi.dept.dao.DeptDao;
import com.changzhi.dept.entity.Dept;
import com.changzhi.dept.util.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DeptDaoImpl implements DeptDao {

    @Override
    public Dept selectById(Integer deptNo) {
        String sql = "select deptno,dname,loc,contact_person,contact_phone,build_date,budget,description from web10_dept where deptno = ?";
        Dept dept = null;
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, deptNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dept = new Dept();
                    dept.setDeptNo(rs.getInt("deptno"));    // 必须严格写数据库表真实的列名
                    dept.setDeptName(rs.getString("dname"));
                    dept.setLoc(rs.getString("loc"));
                    dept.setContactPerson(rs.getString("contact_person"));
                    dept.setContactPhone(rs.getString("contact_phone"));
                    dept.setBuildDate(rs.getDate("build_date"));
                    dept.setBudget(rs.getBigDecimal("budget"));
                    dept.setDescription(rs.getString("description"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return dept;
    }

    @Override
    public List<Dept> selectBase() {
        String sql = "select deptno,dname,loc from web10_dept";
        List<Dept> deptList = new ArrayList<>();
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Dept dept = new Dept();
                dept.setDeptNo(rs.getInt("deptno"));
                dept.setDeptName(rs.getString("dname"));
                dept.setLoc(rs.getString("loc"));
                deptList.add(dept);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return deptList;
    }


    @Override
    public int deleteById(Integer deptNo) {
        String sql = "delete from web10_dept where deptno = ?";
        int count = 0;
        try(Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,deptNo);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            // 正常来说，不能在DAO中吞没异常
            // 只要异常发生，必须将异常上抛调用者。
            // 因为调用者要控制事务
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public Integer selectMaxId() {
        String sql = "select max(deptno) from web10_dept";
        Integer maxDeptNo = null;
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                maxDeptNo = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return maxDeptNo;
    }

    @Override
    public int insert(Dept dept) {
        String sql = "insert into web10_dept(deptno,dname,loc) values(?,?,?)";
        int count = 0;
        try(Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,dept.getDeptNo());
            ps.setString(2, dept.getDeptName());
            ps.setString(3, dept.getLoc());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            // 正常来说，不能在DAO中吞没异常
            // 只要异常发生，必须将异常上抛调用者。
            // 因为调用者要控制事务
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public int update(Dept newDept) {
        String sql = "update web10_dept set dname = ?,loc = ? where deptno = ?";
        int count = 0;
        try(Connection conn = DbUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,newDept.getDeptName());
            ps.setString(2,newDept.getLoc());
            ps.setInt(3,newDept.getDeptNo());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            // 正常来说，不能在DAO中吞没异常
            // 只要异常发生，必须将异常上抛调用者。
            // 因为调用者要控制事务
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return count;
    }
}
