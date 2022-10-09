package com.zheng.service.impl;

import com.zheng.dao.EmpIndexDao;
import com.zheng.dao.impl.EmpIndexDaoimpl;
import com.zheng.pojo.Emp;
import com.zheng.service.showService;

import java.sql.SQLException;
import java.util.List;

public class showServiceimpl implements showService {
    private EmpIndexDao eid = new EmpIndexDaoimpl();
    @Override
    public List<Emp> showAll() {
        try {
            return eid.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
