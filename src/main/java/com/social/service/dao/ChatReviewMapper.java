package com.social.service.dao;

import com.social.service.domain.ChatReview;
import com.social.service.domain.ChatReviewEntity;

public interface ChatReviewMapper {
    int deleteByPrimaryKey(String reviewchatid);

    int insert(ChatReview record);

    int insertSelective(ChatReview record);

    ChatReview selectByPrimaryKey(String reviewChatId);

    int updateByPrimaryKeySelective(ChatReview record);

    int updateByPrimaryKey(ChatReview record);

    ChatReviewEntity findChatReview(String reviewId);

    int deleteByReviewId(String reviewId);
}
