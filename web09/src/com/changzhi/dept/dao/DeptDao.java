package com.changzhi.dept.dao;

import com.changzhi.dept.entity.Dept;

import java.util.List;

/**
 *  专门完成增删改查的Dao接口，给业务层提供的CRUD的接口。
 *  为什么定义接口？让DAO层和业务层解耦合。
 *  Dao中不能定义业务代码。只能是单纯的CRUD操作。
 */
public interface DeptDao {
    /**
     * 保存部门
     * @param dept
     * @return
     */
    int insert(Dept dept);

    /**
     * 根据id删除部门信息
     * @param deptNo
     * @return
     */
    int deleteById(Integer deptNo);

    /**
     * 修改部门信息
     * @param newDept
     * @return
     */
    int update(Dept newDept);

    /**
     * 根据部门编号查询部门信息。
     * @param deptNo
     * @return
     */
    Dept selectById(Integer deptNo);

    /**
     * 查询所有部门的信息，返回部门列表
     * @return
     */
    List<Dept> selectAll();

    /**
     * 找出最大的部门编号
     * @return
     */
    Integer selectMaxId();
}
