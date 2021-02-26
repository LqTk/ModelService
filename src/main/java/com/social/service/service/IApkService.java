package com.social.service.service;

import com.social.service.common.ServiceResponse;
import com.social.service.domain.ApkWithBLOBs;

public interface IApkService {
    ServiceResponse getMaxVersionCode(String apkType);
    ServiceResponse addApkInfo(ApkWithBLOBs apkWithBLOBs);
}
