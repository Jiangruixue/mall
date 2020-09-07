package com.hynet.mall.model.dao;

import com.hynet.mall.model.pojo.Cart;

/*
 * @progrm:
 * @description: 对应购物车表mall_cart的操作
 * @auther:
 * @create:
 */
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}