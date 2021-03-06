package com.social.service.dao;

import com.social.service.domain.Chat;
import com.social.service.domain.ChatEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatMapper {
    int deleteByPrimaryKey(String chatid);

    int insert(Chat record);

    int insertSelective(Chat record);

    Chat selectByPrimaryKey(String chatid);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKey(Chat record);

    List<ChatEntity> selectAllChatFromToId(String toId);

    List<Chat> selectAllChatFromTalkId(String talkId);

    List<Chat> selectCurrentChat(@Param("talkId") String talkId, @Param("userId")String userId);
}
