package com.social.service.service.impl;

import com.social.service.common.Const;
import com.social.service.common.ServiceResponse;
import com.social.service.dao.MsgMapper;
import com.social.service.domain.Msg;
import com.social.service.domain.MsgPublishEntity;
import com.social.service.service.IMsgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<Msg> msgs = msgMapper.selectByPeopleId(peopleId);
        List<MsgPublishEntity> returnList = new ArrayList<>();
        for (Msg msg:msgs){
            if (msg.getMsgType().equals(Const.MSG_GOODS)){
                returnList.add(msgMapper.selectGoods(msg.getMsgId()));
            }else if (msg.getMsgType().equals(Const.MSG_REVIEW)){
                returnList.add(msgMapper.selectReview(msg.getMsgId()));
            }else if (msg.getMsgType().equals(Const.MSG_REVIEW_CHAT)){
                returnList.add(msgMapper.selectChatReview(msg.getMsgId()));
            }
        }
        return ServiceResponse.createBySuccessData(returnList);
    }

    @Override
    public ServiceResponse updateReadedState(String msgId) {
        if (StringUtils.isBlank(msgId))
            return ServiceResponse.createByIllegalArgument();
        int i = msgMapper.updataReadState(msgId);
        if (i>0)
            return ServiceResponse.createBySuccess();
        return ServiceResponse.createByError();
    }

    @Override
    public ServiceResponse getByMsgId(String msgId) {
        if (StringUtils.isBlank(msgId))
            return ServiceResponse.createByIllegalArgument();
        Msg msg = msgMapper.selectByPrimaryKey(msgId);
        if (msg!=null)
            return ServiceResponse.createBySuccessData(msg);
        return ServiceResponse.createByError();
    }
}
