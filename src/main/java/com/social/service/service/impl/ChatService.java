package com.social.service.service.impl;

import com.social.service.common.ServiceResponse;
import com.social.service.dao.ChatMapper;
import com.social.service.domain.Chat;
import com.social.service.domain.ChatEntity;
import com.social.service.service.IChatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("iChatService")
public class ChatService implements IChatService {

    @Autowired
    ChatMapper chatMapper;

    @Override
    public ServiceResponse selectByChatId(String chatId) {
        if (StringUtils.isBlank(chatId))
            return ServiceResponse.createByErrorMessage("获取失败");
        Chat chat = chatMapper.selectByPrimaryKey(chatId);
        return ServiceResponse.createBySuccessData(chat);
    }

    @Override
    public ServiceResponse addChat(Chat chat) {
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
        List<ChatEntity> chats = chatMapper.selectAllChatFromToId(toId);

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

    @Override
    public ServiceResponse selectCurrentChat(String talkId, String userId) {
        if (StringUtils.isBlank(talkId))
            return ServiceResponse.createByIllegalArgument();
        List<Chat> chats = chatMapper.selectCurrentChat(talkId,userId);
        if (chats!=null){
            return ServiceResponse.createBySuccessData(chats);
        }
        return ServiceResponse.createByErrorMessage("获取失败");
    }
}
