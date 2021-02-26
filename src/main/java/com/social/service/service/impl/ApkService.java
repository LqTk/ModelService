package com.social.service.service.impl;

import com.social.service.common.ServiceResponse;
import com.social.service.dao.ApkMapper;
import com.social.service.domain.ApkWithBLOBs;
import com.social.service.service.IApkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IApkService")
public class ApkService implements IApkService {

    @Autowired
    ApkMapper apkMapper;

    @Override
    public ServiceResponse getMaxVersionCode(String apkType) {
        if (StringUtils.isBlank(apkType)){
            return ServiceResponse.createByErrorMessage("请求参数为空");
        }
        ApkWithBLOBs apkWithBLOBs = apkMapper.selectMaxVersion(apkType);
        if (apkWithBLOBs!=null)
            return ServiceResponse.createBySuccessData(apkWithBLOBs);
        return ServiceResponse.createByErrorMessage("查询失败");
    }

    @Override
    public ServiceResponse addApkInfo(ApkWithBLOBs apkWithBLOBs) {
        int insert = apkMapper.insert(apkWithBLOBs);
        if (insert>0){
            return ServiceResponse.createBySuccessMessage("添加成功");
        }
        return ServiceResponse.createByErrorMessage("添加失败");
    }
}
