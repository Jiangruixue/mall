package com.hynet.mall.model.dao;

import com.hynet.mall.model.pojo.Category;

/*
 * @progrm:
 * @description:对应于商品分类表mall_category的相关操作
 * @auther:
 * @create:
 */
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}