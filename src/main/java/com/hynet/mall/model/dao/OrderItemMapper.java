package com.hynet.mall.model.dao;

import com.hynet.mall.model.pojo.OrderItem;

/*
 * @progrm:
 * @description:对应于mall_order_item表的操作
 * @auther:
 * @create:
 */

public interface OrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}