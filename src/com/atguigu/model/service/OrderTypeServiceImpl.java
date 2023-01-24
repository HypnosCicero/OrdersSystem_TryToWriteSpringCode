package com.atguigu.model.service;

import com.atguigu.model.pojo.OType;
import com.atguigu.model.dao.otypeDao.OTypeDao;

import java.util.List;

public class OrderTypeServiceImpl implements OrderTypeService {
    //原代码：OTypeDao typeDao = new OTypeDaoImpl();
    OTypeDao typeDao = null;
    public List<OType> selectAll(){
        return typeDao.selectAll();
    }
}
