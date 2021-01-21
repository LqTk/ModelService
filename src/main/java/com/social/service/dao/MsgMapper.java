package com.social.service.dao;

import com.social.service.domain.Msg;

import java.util.List;

public interface MsgMapper {
    int deleteByPrimaryKey(String msgid);

    int insert(Msg record);

    int insertSelective(Msg record);

    Msg selectByPrimaryKey(String msgid);

    int updateByPrimaryKeySelective(Msg record);

    int updateByPrimaryKey(Msg record);

    int deleteByChatReviewId(String chatId);

    int deleteByGoodsId(String goodsId);

    int deleteByReviewId(String reviewId);

    int deleteByPublishId(String publishId);

    List<Msg> selectByPeopleId(String peopleId);
}
