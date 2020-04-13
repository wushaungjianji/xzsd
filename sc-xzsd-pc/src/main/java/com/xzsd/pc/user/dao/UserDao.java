package com.xzsd.pc.user.dao;

import com.xzsd.pc.user.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    /**
     * 新增用户
     * @param
     * @return
     */
    int saveUser(UserInfo userinfo);
    /**
     * 查询用户
     */
    UserInfo getUserByUserCode(String userId);



    /**
     * 统计用户账号数量
     * @param userInfo 用户信息
     * @return
     */
    int countUserPassword(UserInfo userInfo);

    /**
     * 获取所有用户信息
     * @param userInfo 用户信息
     * @return 所有用户信息
     */
    List<UserInfo> listUsersByPage(UserInfo userInfo);










    /**
     * 修改用户
     * @param
     * @return
     */
    int updateUser(UserInfo userInfo);

    int deleteUser(@Param("listCode") List<String> listCode, @Param("userCode") String userName);
}
