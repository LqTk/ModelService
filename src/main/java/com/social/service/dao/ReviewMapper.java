package com.social.service.dao;

import com.social.service.domain.Review;

public interface ReviewMapper {
    int deleteByPrimaryKey(String reviewid);

    int insert(Review record);

    int insertSelective(Review record);

    Review selectByPrimaryKey(String reviewid);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);
}