package com.social.service.service.impl;

import com.social.service.common.Const;
import com.social.service.common.ServiceResponse;
import com.social.service.dao.ReportPublishMapper;
import com.social.service.dao.SPublicMapper;
import com.social.service.domain.PublicedEntity;
import com.social.service.domain.ReportPublish;
import com.social.service.domain.ReportPublishWithBLOBs;
import com.social.service.service.IFileService;
import com.social.service.service.IReportPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iReportPublishService")
public class ReportPublishService implements IReportPublishService {
    @Autowired
    ReportPublishMapper reportPublishMapper;

    @Autowired
    SPublicMapper sPublicMapper;

    @Autowired
    private IFileService iFileService;

    @Override
    public ServiceResponse insert(ReportPublishWithBLOBs reportPublish) {
        ReportPublishWithBLOBs reportPublishWithBLOBs = reportPublishMapper.selectByUserIdAndPublishId(reportPublish.getPublishid(), reportPublish.getReportuserid());
        if (reportPublishWithBLOBs!=null) {
            String[] split = reportPublishWithBLOBs.getImg().split(";");
            for (String str:split) {
                iFileService.deleteFile(Const.SHARE_PIC,str);
            }
            int i = reportPublishMapper.updateImg(reportPublishWithBLOBs.getReportid(), reportPublish.getText(), reportPublish.getImg());
            if (i>0){
                return ServiceResponse.createBySuccessMessage("举报成功");
            }
        }

        PublicedEntity publicedEntity = sPublicMapper.selectByPrimaryKey(reportPublish.getPublishid());
        if (publicedEntity!=null){
            int count = publicedEntity.getReportCount()+1;
            int i = sPublicMapper.updataReportCount(count, publicedEntity.getShareId());
            if (i>0){
                int insert = reportPublishMapper.insert(reportPublish);
                if (insert>0){
                    return ServiceResponse.createBySuccessMessage("举报成功");
                }
            }
        }
        return ServiceResponse.createByErrorMessage("举报失败");
    }

    @Override
    public void deleteImg(String publishId) {
        List<ReportPublishWithBLOBs> reportPublishWithBLOBs = reportPublishMapper.selectByPublishId(publishId);
        for (ReportPublishWithBLOBs item:reportPublishWithBLOBs){
            String[] split = item.getImg().split(";");
            for (String str:split) {
                iFileService.deleteFile(Const.SHARE_PIC,str);
            }
        }
    }
}
