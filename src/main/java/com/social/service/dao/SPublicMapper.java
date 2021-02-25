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

    List<PublicedEntity> getSocialPublicSize(Integer size);

    List<PublicedEntity> getSocialByUserId(@Param("userId") String userId, @Param("start")int start, @Param("end")int end);

    PublicedEntity selectByShareId(String shareid);

    int getAllCount();

    int updataReportCount(@Param("count") int count,@Param("publishId") String publishId);
}
