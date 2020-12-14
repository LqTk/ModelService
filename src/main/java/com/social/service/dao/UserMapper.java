package com.social.service.dao;

import com.social.service.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkExistByPhoneOrName(String name);

    User selectLogin(@Param("name") String name, @Param("password") String password);

    int updatePasswordByUsername(@Param("name") String name, @Param("password") String password);

    int updatePasswordByUserPhone(@Param("phone") String phone);
}
