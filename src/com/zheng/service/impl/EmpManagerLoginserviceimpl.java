package com.zheng.service.impl;

import com.zheng.dao.Emlogindao;
import com.zheng.dao.impl.Emlogindaoimpl;
import com.zheng.pojo.Empmanager;
import com.zheng.service.EmpManagerLoginservice;

import java.sql.SQLException;

public class EmpManagerLoginserviceimpl implements EmpManagerLoginservice {
    private Emlogindao eld = new Emlogindaoimpl();
    @Override
    public Empmanager login(Empmanager emg) {
        try {
            return eld.selectlogin(emg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
