package com.hynet.mall.model.dao;

import com.hynet.mall.model.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/*
 * @progrm:
 * @description:对应于表mall_user表的操作
 * @auther:
 * @create:
 */
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    //user实体中不
    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);

    //通过username 和 password进行用户信息查询。
    User selectLogin(@Param("username") String userName,@Param("password") String password);
}