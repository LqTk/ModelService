package com.social.service.people;

import com.social.service.common.ServiceResponse;
import com.social.service.domain.ApkWithBLOBs;
import com.social.service.service.IApkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/apk/")
public class ApkController {

    @Autowired
    IApkService iApkService;

    @RequestMapping(value = "checkUpdate/{appType}", method = RequestMethod.POST)
    public ServiceResponse checkUpdate(@PathVariable String appType){
        return iApkService.getMaxVersionCode(appType);
    }

    @RequestMapping(value = "addApkInfo",method = RequestMethod.POST)
    public ServiceResponse addApkInfo(@RequestBody ApkWithBLOBs apkWithBLOBs){
        apkWithBLOBs.setId(UUID.randomUUID().toString().replaceAll("-",""));
        return iApkService.addApkInfo(apkWithBLOBs);
    }
}
