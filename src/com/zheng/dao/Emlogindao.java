package com.zheng.dao;

import com.zheng.pojo.Empmanager;

import java.sql.SQLException;

public interface Emlogindao {
    public Empmanager selectlogin(Empmanager emg) throws SQLException;
}
