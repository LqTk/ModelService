package com.social.service.service;

import com.social.service.common.ServiceResponse;

public interface IMsgService {
    public ServiceResponse selectByPeopleId(String peopleId);
}
