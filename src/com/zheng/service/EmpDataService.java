package com.zheng.service;

import com.zheng.pojo.Emp;

public interface EmpDataService {
    public Boolean insert(Emp e);
    public boolean uptdate(Emp e);
    public void delete(int id);
}
