package com.atguigu.control;

import com.alibaba.fastjson.JSON;
import com.atguigu.model.pojo.OType;
import com.atguigu.model.service.OrderTypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrdersTypesController {
    //原来的代码：OrderTypeService orderTypeService = new OrderTypeServiceImpl();
    OrderTypeService orderTypeService = null;

    private String selectListType(HttpServletRequest req){
        List<OType> oTypes = orderTypeService.selectAll();
        String types = JSON.toJSONString(oTypes);
        return "ajax:"+types;
    }
}
