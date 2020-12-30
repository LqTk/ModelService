package com.social.service.service.impl;

import com.social.service.common.ServiceResponse;
import com.social.service.dao.ChatMapper;
import com.social.service.domain.Chat;
import com.social.service.service.IChatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("iChatService")
public class ChatService implements IChatService {

    @Autowired
    ChatMapper chatMapper;

    @Override
    public ServiceResponse addChat(Chat chat) {
        chat.setChatid(UUID.randomUUID().toString().replaceAll("-",""));
        int insert = chatMapper.insert(chat);
        if (insert>0){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

    @Override
    public ServiceResponse deleteChat(String chatId) {
        if (StringUtils.isBlank(chatId))
            return ServiceResponse.createByIllegalArgument();
        int i = chatMapper.deleteByPrimaryKey(chatId);
        if (i>0){
            return ServiceResponse.createBySuccessMessage("删除成功");
        }
        return ServiceResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServiceResponse selectAllFromToId(String toId) {
        if (StringUtils.isBlank(toId))
            return ServiceResponse.createByIllegalArgument();
        List<Chat> chats = chatMapper.selectAllChatFromToId(toId);
        if (chats!=null){
            return ServiceResponse.createBySuccessData(chats);
        }
        return ServiceResponse.createByErrorMessage("获取失败");
    }

    @Override
    public ServiceResponse selectAllFromTalkId(String talkId) {
        if (StringUtils.isBlank(talkId))
            return ServiceResponse.createByIllegalArgument();
        List<Chat> chats = chatMapper.selectAllChatFromTalkId(talkId);
        if (chats!=null){
            return ServiceResponse.createBySuccessData(chats);
        }
        return ServiceResponse.createByErrorMessage("获取失败");
    }
}
