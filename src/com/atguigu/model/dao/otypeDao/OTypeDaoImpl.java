package com.atguigu.model.dao.otypeDao;

import com.atguigu.model.pojo.OType;
import com.atguigu.myssm.basedao.BaseDao;

import java.util.List;

public class OTypeDaoImpl extends BaseDao<OType> implements OTypeDao{
    @Override
    public List<OType> selectAll() {
        String sql = "SELECT * FROM otype";
        List<OType> oTypeList = selectAll(sql);
        return oTypeList;
    }
}
