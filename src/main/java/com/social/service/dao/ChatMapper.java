package com.social.service.dao;

import com.social.service.domain.Chat;

import java.util.List;

public interface ChatMapper {
    int deleteByPrimaryKey(String chatid);

    int insert(Chat record);

    int insertSelective(Chat record);

    Chat selectByPrimaryKey(String chatid);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKey(Chat record);

    List<Chat> selectAllChatFromToId(String toId);

    List<Chat> selectAllChatFromTalkId(String talkId);
}
