package com.social.service.service.impl;

import com.social.service.common.ServiceResponse;
import com.social.service.dao.MsgMapper;
import com.social.service.domain.Msg;
import com.social.service.service.IMsgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("iMsgService")
public class MsgService implements IMsgService {

    @Autowired
    MsgMapper msgMapper;

    @Override
    public ServiceResponse selectByPeopleId(String peopleId) {
        if (StringUtils.isBlank(peopleId))
            return ServiceResponse.createByIllegalArgument();
        return ServiceResponse.createBySuccessData(msgMapper.selectByPeopleId(peopleId));
    }
}
