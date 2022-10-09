package com.zheng.dao.impl;

import com.zheng.dao.EmpDataDao;
import com.zheng.pojo.Emp;
import org.apache.commons.dbutils.QueryRunner;
import com.zheng.Utils.utils;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class EmpDataDaoimpl implements EmpDataDao {
    private QueryRunner qr =  new QueryRunner(utils.getDataSource());
    @Override
    public Emp selectById(int id) throws SQLException {
        String sql = "select * from emp where id = ?";
        return qr.query(sql,new BeanHandler<>(Emp.class),id);
    }

    @Override
    public Integer insert(Emp e) throws SQLException {
        String sql = "insert into emp(name,salary,age) values (?,?,?)";
        return qr.update(sql,e.getName(), e.getSalary(), e.getAge());
    }

    @Override
    public int update(Emp e) throws SQLException {
        String sql = "update emp set name=?,salary=?,age=? where id=?";
        return qr.update(sql,e.getName(),e.getSalary(),e.getAge(),e.getId());
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "delete from emp where id = ?";
        return qr.update(sql,id);
    }
}
