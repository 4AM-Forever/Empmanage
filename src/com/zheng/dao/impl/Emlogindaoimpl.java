package com.zheng.dao.impl;

import com.zheng.dao.Emlogindao;
import com.zheng.Utils.utils;
import com.zheng.pojo.Empmanager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class Emlogindaoimpl implements Emlogindao {
    private QueryRunner qr = new QueryRunner(utils.getDataSource());
    @Override
    public Empmanager selectlogin(Empmanager emg) throws SQLException {
        String sql = "select * from empmanager where username = ? and password = ?";
        return qr.query(sql,new BeanHandler<>(Empmanager.class),emg.getUsername(),emg.getPassword());
    }
}
