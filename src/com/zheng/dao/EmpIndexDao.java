package com.zheng.dao;

import com.zheng.pojo.Emp;

import java.sql.SQLException;
import java.util.List;

public interface EmpIndexDao {
    public List<Emp> selectAll() throws SQLException;
}
