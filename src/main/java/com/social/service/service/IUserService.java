package com.social.service.service;

import com.social.service.common.ServiceResponse;
import com.social.service.domain.User;

import java.util.Map;

public interface IUserService {

    public ServiceResponse login(String name, String password);

    public ServiceResponse register(User user);

    public boolean checkRole(int userRoleType, int roleType);

    public ServiceResponse update(User user);

    public ServiceResponse getUserInformation(String userId);

    public ServiceResponse resetPassword(String password, String newPassword, String userId);

    public ServiceResponse updateUserInfo(Map map);

    public ServiceResponse updateUserRegistrationId(String userId, String registrationId);

    public User checkExistByResgitrationId(String registrationId);

    public ServiceResponse clearRegistrationId(String userId);

    public User getUserByUserId(String userId);
}
