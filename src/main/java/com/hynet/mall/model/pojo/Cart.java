package com.hynet.mall.model.pojo;

import java.util.Date;

/*
 * @progrm:
 * @description: 购物车类对应于数据库中的mall_cart表。
 * @auther:
 * @create:
 */
public class Cart {

    //购物车id
    private Integer id;

    //商品id
    private Integer productId;

    //用户id
    private Integer userId;

    //商品数量
    private Integer quantity;

    //是否被选中
    private String selected;

    //创建时间
    private Date createTime;

    //更新时间。
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected == null ? null : selected.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}