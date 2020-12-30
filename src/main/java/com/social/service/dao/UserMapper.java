package com.social.service.dao;

import com.social.service.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkExistByPhoneOrName(String name);

    int clearRegistrationId(String userId);

    User selectByRegistrationId(String registrationId);

    User selectLogin(@Param("name") String name, @Param("password") String password);

    int updatePasswordByUsername(@Param("name") String name, @Param("password") String password);

    int updataResigtrationId(@Param("userId")String userId, @Param("registrationId")String registrationId);
}
