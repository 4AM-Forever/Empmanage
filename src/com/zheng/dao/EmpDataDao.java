package com.zheng.dao;

import com.zheng.pojo.Emp;

import java.sql.SQLException;

public interface EmpDataDao {
    public Emp selectById(int id) throws SQLException;
    public Integer insert(Emp e) throws SQLException;
    public int update(Emp e) throws SQLException;
    public int delete(int id) throws SQLException;
}
