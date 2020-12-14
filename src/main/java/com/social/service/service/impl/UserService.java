package com.social.service.service.impl;

import com.social.service.common.Const;
import com.social.service.common.ServiceResponse;
import com.social.service.dao.UserMapper;
import com.social.service.domain.User;
import com.social.service.service.IUserService;
import com.social.service.util.EncryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iUserService")
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public ServiceResponse login(String name, String password) {
        int rowCount = userMapper.checkExistByPhoneOrName(name);
        if (rowCount<=0){
            return ServiceResponse.createByErrorMessage("用户名不存在");
        }
        String encryptPassword = EncryptUtil.encoderUTF8(password);
        User user = userMapper.selectLogin(name, encryptPassword);
        if (null == user){
            return ServiceResponse.createByErrorMessage("密码错误");
        }
        return ServiceResponse.createBySuccessData(user);
    }

    public ServiceResponse register(User user) {
        ServiceResponse validResponse = this.checkValid(user.getPhone(),Const.PHONE);
        if (!validResponse.isSuccess()){
            return validResponse;
        }
        validResponse = this.checkValid(user.getName(),Const.USERNAME);
        if (!validResponse.isSuccess()){
            return validResponse;
        }
        user.setPassword(EncryptUtil.encoderUTF8(user.getPassword()));
        int rowCount = userMapper.insert(user);
        if (rowCount>0){
            return ServiceResponse.createBySuccessMessage("注册成功");
        }
        return ServiceResponse.createByErrorMessage("注册失败，请重试");
    }

    public ServiceResponse checkValid(String msg, String msgType){
        if (StringUtils.isEmpty(msgType)){
            return ServiceResponse.createByErrorMessage("未设置校验类型");
        }
        if (Const.PHONE.equals(msgType)){
            int rowCount = userMapper.checkExistByPhoneOrName(msg);
            if (rowCount>0){
                return ServiceResponse.createByErrorMessage("电话号码已注册");
            }
        }
        if (Const.USERNAME.equals(msgType)){
            int rowCount = userMapper.checkExistByPhoneOrName(msg);
            if (rowCount>0){
                return ServiceResponse.createByErrorMessage("用户名已被使用");
            }
        }
        return ServiceResponse.createBySuccess();
    }

    public boolean checkRole(int userRoleType, int roleType) {
        return false;
    }

    public ServiceResponse update(User user) {
        return null;
    }

    public ServiceResponse getUserInformation(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user){
            return ServiceResponse.createByErrorMessage("找不到该用户的信息");
        }
        user.setPassword("");
        return ServiceResponse.createBySuccessData(user);
    }

    public ServiceResponse resetPassword(String password, String newPassword, Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user){
            return ServiceResponse.createByErrorMessage("无法找到当前用户的个人信息");
        }
        String encryptPassword = EncryptUtil.encoderUTF8(password);
        if (!StringUtils.equals(encryptPassword,user.getPassword())){
            return ServiceResponse.createByErrorMessage("原密码错误");
        }
        String newEncryptPassword = EncryptUtil.encoderUTF8(newPassword);
        int rowCount = userMapper.updatePasswordByUsername(user.getName(),newEncryptPassword);
        if (rowCount>0){
            return ServiceResponse.createBySuccessMessage("重置密码成功");
        }
        return ServiceResponse.createByErrorMessage("重置密码失败");
    }
}
