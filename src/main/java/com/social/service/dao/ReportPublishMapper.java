package com.social.service.dao;

import com.social.service.domain.ReportPublish;
import com.social.service.domain.ReportPublishWithBLOBs;

public interface ReportPublishMapper {
    int deleteByPrimaryKey(String reportid);

    int insert(ReportPublishWithBLOBs record);

    int insertSelective(ReportPublishWithBLOBs record);

    ReportPublishWithBLOBs selectByPrimaryKey(String reportid);

    int updateByPrimaryKeySelective(ReportPublishWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ReportPublishWithBLOBs record);

    int updateByPrimaryKey(ReportPublish record);

    ReportPublishWithBLOBs selectByUserIdAndPbulishId(String publishId, String userId);

    int updateImg(String reportId, String text, String img);
}
