package com.social.service.common;

import com.social.service.service.IFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;

@RestController
@RequestMapping("/file/")
public class FileControler {

    @Autowired
    IFileService iFileService;

    @RequestMapping("uploadImg")
    public ServiceResponse uploadImg(@RequestParam(value = "img",required = false)MultipartFile file,
                                     HttpSession session, HttpServletRequest request,@RequestParam("userId") String userId){
        String uploadUrl = iFileService.upload(file, Const.upLoadHead);
        File uploadFile = new File(uploadUrl);
        HashMap map = new HashMap();
        if (StringUtils.isBlank(uploadUrl)){
            map.put("success",false);
            map.put("msg","上传文件失败");
            return ServiceResponse.createBySuccessData(map);
        }
        map.put("success",true);
        map.put("url","social/headImg/"+uploadFile.getName());
        map.put("uri",uploadFile.getName());
        return ServiceResponse.createBySuccessData(map);
    }
}
