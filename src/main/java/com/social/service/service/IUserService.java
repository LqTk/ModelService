package com.social.service.service;

import com.social.service.common.ServiceResponse;
import com.social.service.domain.User;

public interface IUserService {

    public ServiceResponse login(String name, String password);

    public ServiceResponse register(User user);

    public boolean checkRole(int userRoleType, int roleType);

    public ServiceResponse update(User user);

    public ServiceResponse getUserInformation(String userId);

    public ServiceResponse resetPassword(String password, String newPassword, String userId);

    public ServiceResponse updateUserInfo(User user);

    public ServiceResponse updateUserRegistrationId(String userId, String registrationId);
}
