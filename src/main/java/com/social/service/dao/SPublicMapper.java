package com.social.service.dao;

import com.social.service.domain.PublicedEntity;
import com.social.service.domain.SPublic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SPublicMapper {
    int deleteByPrimaryKey(String shareid);

    int insert(SPublic record);

    int insertSelective(SPublic record);

    PublicedEntity selectByPrimaryKey(String shareid);

    int updateByPrimaryKeySelective(SPublic record);

    int updateByPrimaryKey(SPublic record);

    int addReviewCount(@Param("shareId") String shareId, @Param("count") int count);

    int addGoodsCount(@Param("shareId") String shareId, @Param("count") int count);

    List<PublicedEntity> getSocialPublicSize();

    List<PublicedEntity> getSocialByUserId(@Param("userId") String userId, @Param("start")int start, @Param("end")int end);

    int updataHeadImg(@Param("userId")String userId, @Param("imghead")String headImg);

    PublicedEntity selectByShareId(String shareid);

}
