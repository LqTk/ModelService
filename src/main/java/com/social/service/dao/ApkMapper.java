package com.social.service.dao;

import com.social.service.domain.Apk;
import com.social.service.domain.ApkWithBLOBs;

public interface ApkMapper {
    int deleteByPrimaryKey(String id);

    int insert(ApkWithBLOBs record);

    int insertSelective(ApkWithBLOBs record);

    ApkWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ApkWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ApkWithBLOBs record);

    int updateByPrimaryKey(Apk record);

    ApkWithBLOBs selectMaxVersion(String apkType);
}
