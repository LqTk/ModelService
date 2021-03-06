package com.social.service.service;

import com.social.service.common.ServiceResponse;
import com.social.service.domain.Chat;

public interface IChatService {
    public ServiceResponse selectByChatId(String chatId);
    public ServiceResponse addChat(Chat chat);
    public ServiceResponse deleteChat(String chatId);
    public ServiceResponse selectAllFromToId(String toId);
    public ServiceResponse selectAllFromTalkId(String talkId);
    public ServiceResponse selectCurrentChat(String talkId, String userId);
}
