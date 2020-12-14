package com.social.service.service;

import com.social.service.common.ServiceResponse;
import com.social.service.domain.User;

public interface IUserService {

    public ServiceResponse login(String name, String password);

    public ServiceResponse register(User user);

    public boolean checkRole(int userRoleType, int roleType);

    public ServiceResponse update(User user);

    public ServiceResponse getUserInformation(Integer userId);

    public ServiceResponse resetPassword(String password, String newPassword, Integer userId);
}
