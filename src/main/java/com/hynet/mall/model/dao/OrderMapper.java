package com.hynet.mall.model.dao;

import com.hynet.mall.model.pojo.Order;

/*
 * @progrm:
 * @description:对应于表mall_order的相关操作
 * @auther:
 * @create:
 */
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}