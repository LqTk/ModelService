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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        HashMap map = new HashMap();
        map.put("userId",user.getId());
        map.put("userName",user.getName());
        map.put("headImg",user.getImg());
        map.put("sex",user.getSex());
        map.put("age",user.getAge());
        map.put("birthday",user.getBirthday());
        map.put("phone",user.getPhone());
        map.put("des",user.getDes());
        return ServiceResponse.createBySuccessData(map);
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
        if (StringUtils.isBlank(user.getPassword()))
            return ServiceResponse.createByErrorMessage("请输入密码");
        user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        user.setRegistrationid("");
        user.setPassword(EncryptUtil.encoderUTF8(user.getPassword()));
        user.setAge(18);
        user.setSex(1);
        user.setImg("");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        Date date = new Date();
        int yearage = Integer.valueOf(year.format(date))-18;
        user.setBirthday(yearage+"-01-01");
        user.setDes("欢迎使用！");
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

    public ServiceResponse getUserInformation(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user){
            return ServiceResponse.createByErrorMessage("找不到该用户的信息");
        }
        user.setPassword("");
        return ServiceResponse.createBySuccessData(user);
    }

    public ServiceResponse resetPassword(String password, String newPassword, String userId) {
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

    @Override
    public ServiceResponse updateUserInfo(Map map) {
        if (map==null || StringUtils.isBlank((String) map.get("userId"))){
            return ServiceResponse.createByIllegalArgument();
        }
        User user = new User();
        user.setId((String) map.get("userId"));
        if (!StringUtils.isBlank((String)map.get("name"))){
            int name = userMapper.checkExistByPhoneOrName((String) map.get("name"));
            if (name>0){
                return ServiceResponse.createByErrorMessage("用户名已被使用");
            }
            user.setName((String)map.get("name"));
        }
        if (map.get("sex")!=null){
            user.setSex((Integer) map.get("sex"));
        }
        if (!StringUtils.isBlank((String)map.get("des"))){
            user.setDes((String)map.get("des"));
        }
        if (!StringUtils.isBlank((String)map.get("head"))){
            user.setImg((String)map.get("head"));
        }
        if (!StringUtils.isBlank((String)map.get("year"))){
            String birthday = (String) map.get("year") + "-" + (String) map.get("month") + "-" + (String) map.get("day");
            SimpleDateFormat year = new SimpleDateFormat("yyyy");
            Date date = new Date();
            int age = Integer.valueOf(year.format(date))-Integer.valueOf((String) map.get("year"));
            user.setBirthday(birthday);
            user.setAge(age);
        }
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i>0){
            return ServiceResponse.createBySuccessMessage("用户信息更新成功");
        }
        return ServiceResponse.createByErrorMessage("用户信息更新失败");
    }

    @Override
    public ServiceResponse updateUserRegistrationId(String userId, String registrationId) {
        if (StringUtils.isBlank(userId))
            return ServiceResponse.createByErrorMessage("未找到用户");
        if (userMapper.selectByPrimaryKey(userId)==null)
            return ServiceResponse.createByErrorMessage("未找到用户");
        int i = userMapper.updataResigtrationId(userId, registrationId);
        if (i>0){
            return ServiceResponse.createBySuccessMessage("更新成功");
        }
        return ServiceResponse.createByErrorMessage("更新失败");
    }

    @Override
    public User checkExistByResgitrationId(String registrationId) {
        if (StringUtils.isBlank(registrationId))
            return null;
        User user = userMapper.selectByRegistrationId(registrationId);
        return user;
    }

    @Override
    public ServiceResponse clearRegistrationId(String userId){
        if (StringUtils.isBlank(userId))
            return ServiceResponse.createByErrorMessage("用户ID不能为空");
        int i = userMapper.clearRegistrationId(userId);
        if (i>0){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    @Override
    public User getUserByUserId(String userId) {
        if (StringUtils.isBlank(userId))
        return null;
        return userMapper.selectByPrimaryKey(userId);
    }
}
