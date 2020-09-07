package com.hynet.mall.model.pojo;

import java.util.Date;

/*
 * @progrm:
 * @description: 商品分类
 * @auther:
 * @create:
 */
public class Category {

    //商品分类id
    private Integer id;

    //商品分类名称
    private String name;

    //分类目录级别，1：一级；2：二级；3：三级
    private Integer type;

    //父目录id,也就是上一级目录id,如果是一级目录，那么父id为0
    private Integer parentId;

    //目录展示的顺序
    private Integer orderNum;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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