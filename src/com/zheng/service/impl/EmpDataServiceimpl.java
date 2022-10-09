package com.zheng.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.zheng.dao.EmpDataDao;
import com.zheng.dao.impl.EmpDataDaoimpl;
import com.zheng.pojo.Emp;
import com.zheng.service.EmpDataService;

import java.awt.print.Book;
import java.sql.SQLException;

public class EmpDataServiceimpl implements EmpDataService {
    private EmpDataDao edd = new EmpDataDaoimpl();

    @Override
    public Boolean insert(Emp e) {
        Emp p = null;
        try {
            Integer a = edd.insert(e);//返回的a值是id属性值
                if (a>0){//判断是否添加成功
//                  return p = edd.selectById(a);
                    return true;
                }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean uptdate(Emp e) {
        try {
            if (edd.update(e)>0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(int id) {
        try {
            edd.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
