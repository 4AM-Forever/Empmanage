package com.zheng.dao.impl;

import com.zheng.dao.EmpIndexDao;
import com.zheng.pojo.Emp;
import org.apache.commons.dbutils.QueryRunner;
import com.zheng.Utils.utils;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class EmpIndexDaoimpl implements EmpIndexDao {
    private QueryRunner qr = new QueryRunner(utils.getDataSource());
    @Override
    public List<Emp> selectAll() throws SQLException {
        String sql = "select * from emp";
        return qr.query(sql,new BeanListHandler<>(Emp.class));
    }
}
