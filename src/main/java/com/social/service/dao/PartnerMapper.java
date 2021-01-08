package com.social.service.dao;

import com.social.service.domain.Partner;
import com.social.service.domain.PartnerEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PartnerMapper {
    int deleteByPrimaryKey(String id);

    int insert(Partner record);

    int insertSelective(Partner record);

    Partner selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Partner record);

    int updateByPrimaryKey(Partner record);

    List<PartnerEntity> getPartners(String userId);

    Partner selectByUserIdAndPartnerId(@Param("userId") String userId, @Param("partnerId") String partnerId);
}
