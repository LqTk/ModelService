package com.social.service.people.user;

import com.social.service.common.ServiceResponse;
import com.social.service.domain.User;
import com.social.service.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ServiceResponse login(@RequestBody User user){
        if (null == user){
            return ServiceResponse.createByIllegalArgument();
        }
        if (StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getPassword())){
            return ServiceResponse.createByIllegalArgument();
        }
        ServiceResponse response = iUserService.login(user.getName(), user.getPassword());
        return response;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServiceResponse register(@RequestBody User user){
        if (null == user){
            return ServiceResponse.createByIllegalArgument();
        }
        return iUserService.register(user);
    }
}
