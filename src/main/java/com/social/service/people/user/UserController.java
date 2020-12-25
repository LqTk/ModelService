package com.social.service.people.user;

import com.social.service.common.ServiceResponse;
import com.social.service.domain.Partner;
import com.social.service.domain.User;
import com.social.service.service.IFileService;
import com.social.service.service.IPartnerService;
import com.social.service.service.IPublicService;
import com.social.service.service.IUserService;
import com.social.service.util.JPushClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IFileService iFileService;

    @Autowired
    private IPublicService iPublicService;

    @Autowired
    private IPartnerService iPartnerService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ServiceResponse login(@RequestBody User user){
        if (null == user){
            return ServiceResponse.createByIllegalArgument();
        }
        if (StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getPassword())){
            return ServiceResponse.createByIllegalArgument();
        }
        ServiceResponse response = iUserService.login(user.getName(), user.getPassword());
        return response;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServiceResponse register(@RequestBody User user){
        if (null == user){
            return ServiceResponse.createByIllegalArgument();
        }
        return iUserService.register(user);
    }

    @RequestMapping(value = "updataInfo", method = RequestMethod.POST)
    public ServiceResponse updateInfo(@RequestBody User user){
        if (null == user){
            return ServiceResponse.createByIllegalArgument();
        }
        return iUserService.updateUserInfo(user);
    }

    @RequestMapping(value = "uploadHead", method = RequestMethod.POST)
    public ServiceResponse uploadHead(@RequestParam(value = "img",required = true) MultipartFile file,
                                      @RequestParam(value = "userId", required = true) String userId,
                                     HttpSession session, HttpServletRequest request){
        HashMap map = new HashMap();
        if (StringUtils.isBlank(userId)){
            return ServiceResponse.createByErrorMessage("未找到用户信息");
        }
        ServiceResponse userInformation = iUserService.getUserInformation(userId);
        if (!userInformation.isSuccess()){
            return ServiceResponse.createByErrorMessage(userInformation.getMsg());
        }
        User user = (User) userInformation.getData();

        String saveDir =  "D:\\tomact\\apache-tomcat-9.0.21\\proDir\\";
        String uploadDir = saveDir+"social/headImg";
        if (!StringUtils.isBlank(user.getImg())){
            File headFile = new File(saveDir+user.getImg());
            if (headFile.exists()){
                headFile.delete();
            }
        }
        String uploadUrl = iFileService.upload(file, uploadDir);
        File uploadFile = new File(uploadUrl);
        if (StringUtils.isBlank(uploadUrl)){
            return ServiceResponse.createByErrorMessage("上传文件失败");
        }else {
            user.setImg("social/headImg/" + uploadFile.getName());
            ServiceResponse response = iUserService.updateUserInfo(user);
            if (response.isSuccess()) {
                map.put("success", true);
                map.put("msg", "上传文件成功");
                map.put("url", "social/headImg/" + uploadFile.getName());
                map.put("uri", uploadFile.getName());

                iPublicService.updataHeadImg(userId,"social/headImg/" + uploadFile.getName());
                JPushClientUtil.sendToRegistrationId(user.getRegistrationid(),"头像更新结果","更新结果","头像更新成功","23333");
//                JPushClientUtil.sendMessageToAll(user.getRegistrationid(),"头像更新结果","头像更新成功","23333");
                return ServiceResponse.createBySuccessData(map);
            }else {
                return ServiceResponse.createByErrorMessage(response.getMsg());
            }
        }
    }

    @RequestMapping(value = "getByUserId", method = RequestMethod.POST)
    public ServiceResponse getByUserId(@RequestBody Map map){
        String userId = (String) map.get("userId");
        int page = (int) map.get("page");
        if (StringUtils.isBlank(userId))
            return ServiceResponse.createByErrorMessage("用户Id不能为空");
        return iPublicService.getByUserId(userId,page);
    }

    @RequestMapping(value = "updataRegisterId", method = RequestMethod.POST)
    public ServiceResponse updataRegisterId(@RequestBody Map map){
        String userId = (String) map.get("userId");
        String registerId = (String) map.get("registerId");
        if (StringUtils.isBlank(userId))
            return ServiceResponse.createByErrorMessage("用户Id不能为空");
        return iUserService.updateUserRegistrationId(userId,registerId);
    }

    @RequestMapping(value = "getProfileByUserId/{userId}",method = RequestMethod.GET)
    public ServiceResponse getProfileByUserId(@PathVariable String userId){
        if (StringUtils.isBlank(userId)){
            return ServiceResponse.createByErrorMessage("用户为空");
        }
        ServiceResponse userInformation = iUserService.getUserInformation(userId);
        if (userInformation==null){
            return ServiceResponse.createByErrorMessage("未找到用户信息");
        }
        User user = (User) userInformation.getData();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("peopleName",user.getName());
        hashMap.put("peopleId",user.getId());
        hashMap.put("peopleHead",user.getImg());
        hashMap.put("peopleSex",user.getSex());
        hashMap.put("peopleDes",user.getDes());
        return ServiceResponse.createBySuccessData(hashMap);
    }

    @RequestMapping(value = "addPartner", method = RequestMethod.POST)
    public ServiceResponse addPartner(@RequestBody HashMap map){
        String userId = (String) map.get("userId");
        String partnerId = (String) map.get("partnerId");
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(partnerId)){
            return ServiceResponse.createByErrorMessage("添加失败");
        }
        if (iPartnerService.getByUserAndPartner(userId,partnerId)>0){
            return ServiceResponse.createByErrorMessage("好友已添加");
        }
        Partner partner = new Partner();
        partner.setId(UUID.randomUUID().toString().replaceAll("-",""));
        partner.setUserid(userId);
        partner.setPartnerid(partnerId);
        return iPartnerService.addPartner(partner);
    }

    @RequestMapping(value = "getPartners/{userId}", method = RequestMethod.GET)
    public ServiceResponse getPartners(@PathVariable String userId){
        if (StringUtils.isBlank(userId))
            return ServiceResponse.createByErrorMessage("列表获取失败");
        ServiceResponse partners = iPartnerService.getPartners(userId);
        if (!partners.isSuccess()){
            return ServiceResponse.createByErrorMessage("列表获取失败");
        }
        List<Partner> partnersData = (List<Partner>) partners.getData();
        List<HashMap> returnList = new ArrayList<>();
        for (Partner partner:partnersData){
            HashMap hashMap = new HashMap();
            ServiceResponse userInformation = iUserService.getUserInformation(partner.getPartnerid());
            User user = (User) userInformation.getData();
            hashMap.put("peopleName",user.getName());
            hashMap.put("peopleId",user.getId());
            hashMap.put("peopleHead",user.getImg());
            hashMap.put("peopleSex",user.getSex());
            hashMap.put("peopleDes",user.getDes());
            returnList.add(hashMap);
        }
        return ServiceResponse.createBySuccessData(returnList);
    }
}
