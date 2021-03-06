package com.social.service.dao;

import com.social.service.domain.Review;
import com.social.service.domain.ReviewEntity;

import java.util.List;

public interface ReviewMapper {
    int deleteByPrimaryKey(String reviewid);

    int insert(Review record);

    int insertSelective(Review record);

    Review selectByPrimaryKey(String reviewid);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);

    ReviewEntity selectByReviewId(String reviewId);

    int deleteByShareId(String pbId);

    List<Review> selectByPbId(String pbId);
}
