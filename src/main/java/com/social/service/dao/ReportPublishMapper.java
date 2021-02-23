package com.social.service.dao;

import com.social.service.domain.ReportPublish;
import com.social.service.domain.ReportPublishWithBLOBs;
import org.apache.ibatis.annotations.Param;

public interface ReportPublishMapper {
    int deleteByPrimaryKey(String reportid);

    int insert(ReportPublishWithBLOBs record);

    int insertSelective(ReportPublishWithBLOBs record);

    ReportPublishWithBLOBs selectByPrimaryKey(String reportid);

    int updateByPrimaryKeySelective(ReportPublishWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ReportPublishWithBLOBs record);

    int updateByPrimaryKey(ReportPublish record);

    ReportPublishWithBLOBs selectByUserIdAndPublishId(@Param("publishId") String publishId, @Param("userId") String userId);

    int updateImg(@Param("reportId") String reportId, @Param("text") String text, @Param("img") String img);
}
