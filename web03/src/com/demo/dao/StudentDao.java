package com.demo.dao;

import com.demo.entity.Student;
import com.demo.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    //查询全部学生
    public List<Student> findAll(){
        List<Student> list = new ArrayList<>();
        Connection conn = DBUtil.getConn();
        String sql = "select * from web03_student";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Integer id = rs.getInt("student_id");
                String name = rs.getString("student_name");
                Double score = rs.getDouble("score");
                list.add(new Student(id,name,score));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        DBUtil.close(conn);
        return list;
    }

    //新增学生
    public int add(Student stu){
        Connection conn = DBUtil.getConn();
        String sql = "insert into student(student_id,student_name,score) values(?,?,?)";
        int rows = 0;
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,stu.getStudentId());
            pstmt.setString(2,stu.getStudentName());
            pstmt.setDouble(3,stu.getScore());
            rows = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        DBUtil.close(conn);
        return rows;
    }

    //修改学生
    public int update(Student stu){
        Connection conn = DBUtil.getConn();
        String sql = "update student set student_name=?,score=? where student_id=?";
        int rows = 0;
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,stu.getStudentName());
            pstmt.setDouble(2,stu.getScore());
            pstmt.setInt(3,stu.getStudentId());
            rows = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        DBUtil.close(conn);
        return rows;
    }

    //删除学生
    public int delete(Integer id){
        Connection conn = DBUtil.getConn();
        String sql = "delete from student where student_id=?";
        int rows = 0;
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            rows = pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        DBUtil.close(conn);
        return rows;
    }
}
