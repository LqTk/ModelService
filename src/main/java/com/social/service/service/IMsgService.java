package com.social.service.service;

import com.social.service.common.ServiceResponse;

public interface IMsgService {
    public ServiceResponse selectByPeopleId(String peopleId);
    public ServiceResponse updateReadedState(String msgId);
    public ServiceResponse getByMsgId(String msgId);
}
