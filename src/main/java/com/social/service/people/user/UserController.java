package com.social.service.people.user;

import com.social.service.common.Const;
import com.social.service.common.ServiceResponse;
import com.social.service.domain.Chat;
import com.social.service.domain.ChatEntity;
import com.social.service.domain.Partner;
import com.social.service.domain.User;
import com.social.service.service.*;
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

    @Autowired
    private IChatService iChatService;

    /**
     *登录
     * @param user
     * @return
     */
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

    /**
     * 注册
     * @param map
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServiceResponse register(@RequestBody Map map){
        if (null == map){
            return ServiceResponse.createByIllegalArgument();
        }
        User user = new User();
        user.setName((String) map.get("name"));
        user.setPassword((String) map.get("password"));
        user.setPhone((String) map.get("phone"));
        return iUserService.register(user);
    }

    /**
     * 更新用户资料
     * @param map
     * @return
     */
    @RequestMapping(value = "updataInfo", method = RequestMethod.POST)
    public ServiceResponse updateInfo(@RequestBody Map map){
        if (null == map){
            return ServiceResponse.createByIllegalArgument();
        }
        return iUserService.updateUserInfo(map);
    }

    /**
     * 上传头像
     * @param file
     * @param userId
     * @param session
     * @param request
     * @return
     */
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
            Map map1 = new HashMap();
            map1.put("head",user.getImg());
            map1.put("userId",user.getId());
            ServiceResponse response = iUserService.updateUserInfo(map1);
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

    /**
     * 通过USERID获取用户分享的内容信息每次查询10条分享内容
     * @param map
     * @return
     */
    @RequestMapping(value = "getByUserId", method = RequestMethod.POST)
    public ServiceResponse getByUserId(@RequestBody Map map){
        String userId = (String) map.get("userId");
        int page = (int) map.get("page");
        if (StringUtils.isBlank(userId))
            return ServiceResponse.createByErrorMessage("用户Id不能为空");
        return iPublicService.getByUserId(userId,page);
    }

    /**
     * 更新registerID
     * @param map
     * @return
     */
    @RequestMapping(value = "updataRegisterId", method = RequestMethod.POST)
    public ServiceResponse updataRegisterId(@RequestBody Map map){
        String userId = (String) map.get("userId");
        String registerId = (String) map.get("registerId");

        User user = iUserService.checkExistByResgitrationId(registerId);
        if (user!=null) {
            if (user.getId().equals(userId)) {
                return ServiceResponse.createBySuccessMessage("已存在");
            }else {
                iUserService.clearRegistrationId(user.getId());
            }
        }
        if (StringUtils.isBlank(userId))
            return ServiceResponse.createByErrorMessage("用户Id不能为空");
        return iUserService.updateUserRegistrationId(userId,registerId);
    }

    /**
     * 获取用户信息，userId用户id，peopleId需要获取信息的用户Id
     * @param map
     * @return
     */
    @RequestMapping(value = "getProfileByUserId",method = RequestMethod.GET)
    public ServiceResponse getProfileByUserId(@RequestParam Map map){
        if (map == null){
            return ServiceResponse.createByIllegalArgument();
        }
        String userId = (String) map.get("userId");
        String peopleId = (String) map.get("peopleId");
        if (StringUtils.isBlank(peopleId)){
            return ServiceResponse.createByErrorMessage("用户为空");
        }
        ServiceResponse userInformation = iUserService.getUserInformation(peopleId);
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
        hashMap.put("peopleAge",user.getAge());
        //用户是否关注了该人
        if (iPartnerService.getByUserAndPartner(userId,peopleId)!=null){
            hashMap.put("isConcern",true);
        }else {
            hashMap.put("isConcern",false);
        }
        return ServiceResponse.createBySuccessData(hashMap);
    }

    /**
     * 添加关注userId用户，partnerId需要添加的用户id
     * @param map
     * @return
     */
    @RequestMapping(value = "addPartner", method = RequestMethod.POST)
    public ServiceResponse addPartner(@RequestBody HashMap map){
        String userId = (String) map.get("userId");
        String partnerId = (String) map.get("partnerId");
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(partnerId)){
            return ServiceResponse.createByErrorMessage("添加失败");
        }
        if (iPartnerService.getByUserAndPartner(userId,partnerId)!=null){
            return ServiceResponse.createByErrorMessage("好友已添加");
        }
        Partner partner = new Partner();
        partner.setId(UUID.randomUUID().toString().replaceAll("-",""));
        partner.setUserId(userId);
        partner.setPartnerId(partnerId);
        return iPartnerService.addPartner(partner);
    }

    @RequestMapping(value = "cancelPartner")
    public ServiceResponse cancelPartner(@RequestBody HashMap map){
        String userId = (String) map.get("userId");
        String partnerId = (String) map.get("partnerId");
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(partnerId)){
            return ServiceResponse.createByErrorMessage("参数不能为空");
        }
        Partner partner = iPartnerService.getByUserAndPartner(userId,partnerId);
        if (partner!=null){
            return iPartnerService.deletePartner(partner.getId());
        }
        return ServiceResponse.createByErrorMessage("取消关注失败");
    }

    /**
     * 获取好友列表 userId
     * @param userId
     * @return
     */
    @RequestMapping(value = "getPartners/{userId}", method = RequestMethod.GET)
    public ServiceResponse getPartners(@PathVariable String userId){
        if (StringUtils.isBlank(userId))
            return ServiceResponse.createByErrorMessage("列表获取失败");
        ServiceResponse partners = iPartnerService.getPartners(userId);
        if (partners.isSuccess()){
            return partners;
        }
        return ServiceResponse.createByErrorMessage("列表获取失败");
    }

    /**
     * 获取用户关注的列表
     * @param userId
     * @return
     */
    @RequestMapping(value = "getConcerns/{userId}", method = RequestMethod.GET)
    public ServiceResponse getConcerns(@PathVariable String userId){
        if (StringUtils.isBlank(userId))
            return ServiceResponse.createByErrorMessage("列表获取失败");
        ServiceResponse partners = iPartnerService.getConcerns(userId);
        if (partners.isSuccess()){
            return partners;
        }
        return ServiceResponse.createByErrorMessage("列表获取失败");
    }

    /**
     * 用户聊天信息
     * @param map
     * @param file
     * @return
     */
    @RequestMapping(value = "chat/sendMsg", method = RequestMethod.POST)
    public ServiceResponse sendMsg(@RequestParam HashMap map, @RequestParam(value = "file",required = false) MultipartFile file){
        if (map==null || StringUtils.isBlank((String) map.get("msgtype"))){
            return ServiceResponse.createByErrorMessage("参数错误");
        }
        String msgType = (String) map.get("msgtype");
        Chat chat = new Chat();
        chat.setTalkId((String) map.get("talkid"));
        chat.setToId((String) map.get("toid"));
        chat.setMsgType((String) map.get("msgtype"));
        if (msgType.equals(Const.MODE_IMAGE) || msgType.equals(Const.MODE_VOICE)){
            String saveDir =  "D:\\tomact\\apache-tomcat-9.0.21\\proDir\\";
            String uploadDir = "";
            if (msgType.equals(Const.MODE_IMAGE)){
                //图片
                uploadDir = "social/chatImg";
            }else {
                //语音
                uploadDir = "social/chatVoice";
                chat.setVoiceTime((String) map.get("voicetime"));
            }
            String uploadUrl = iFileService.upload(file, saveDir+uploadDir);
            File uploadFile = new File(uploadUrl);
            if (StringUtils.isBlank(uploadUrl)){
                return ServiceResponse.createByErrorMessage("上传文件失败");
            }
            chat.setFilepath(uploadDir + "/" + uploadFile.getName());
        }else {
            //文字
            chat.setMsgContent((String) map.get("msgcontent"));
        }
        return sendMsgResult(chat);
    }

    private ServiceResponse sendMsgResult(@RequestBody Chat chat) {
        chat.setChatId(UUID.randomUUID().toString().replaceAll("-",""));
        ServiceResponse response = iChatService.addChat(chat);
        if (response.isSuccess()) {
            ServiceResponse userInformation = iUserService.getUserInformation(chat.getToId());
            if (userInformation == null) {
                return ServiceResponse.createBySuccess();
            }
            User user = (User) userInformation.getData();
            if (user != null && !StringUtils.isBlank(user.getRegistrationid()))
            JPushClientUtil.sendMessageToAll(user.getRegistrationid(), "有新消息", "新消息", "talkId", chat.getTalkId());
            return ServiceResponse.createBySuccessData(chatToMsg((Chat) iChatService.selectByChatId(chat.getChatId()).getData()));
        }else {
            return ServiceResponse.createByErrorMessage("消息发送失败");
        }
    }

    /**
     * 获取用户所有聊天列表
     * @param userId
     * @return
     */
    @RequestMapping(value = "chat/getAllChat/{userId}",method = RequestMethod.GET)
    public ServiceResponse getAllMsg(@PathVariable String userId){
        if (StringUtils.isBlank(userId)){
            return ServiceResponse.createByErrorMessage("获取失败");
        }
        ServiceResponse response = iChatService.selectAllFromToId(userId);
        return response;
    }

    private ChatEntity chatToMsg(Chat chat){
        User user = (User) iUserService.getUserInformation(chat.getTalkId()).getData();
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.msgType = chat.getMsgType();
        chatEntity.chatId = chat.getChatId();
        chatEntity.chatTime = chat.getChatTime();
        chatEntity.senderId = chat.getTalkId();
        if (chat.getMsgType().equals(Const.MODE_TEXT)) {
            chatEntity.msgContent = chat.getMsgContent();
        }else{
            if (chat.getMsgType().equals(Const.MODE_VOICE)){
                chatEntity.voiceTime = chat.getVoiceTime();
            }
            chatEntity.msgContent = chat.getFilepath();
        }
        if (user!=null) {
            chatEntity.senderName = user.getName();
            chatEntity.senderAvatar = user.getImg();
        }else {
            chatEntity.senderAvatar = "";
        }
        return chatEntity;
    }

    private void listChatToMsg(List<ChatEntity> returnList, Chat chat) {
        returnList.add(chatToMsg(chat));
    }

    /**
     * 获取单个好友聊天消息列表
     * @param map
     * @return
     */
    @RequestMapping(value = "chat/getSelectAllChat",method = RequestMethod.GET)
    public ServiceResponse getSelectAllChat(@RequestParam HashMap map){
        ServiceResponse response = getChatRes(map);
        return getChatResponse(response);
    }

    /**
     * 获取指定聊天伙伴的聊天未读记录
     * @param map
     * @return
     */
    @RequestMapping(value = "chat/getSelectChat",method = RequestMethod.GET)
    public ServiceResponse getChatMsg(@RequestParam HashMap map){
        ServiceResponse response = getChatRes(map);
        if (response.isSuccess()) {
            List<Chat> chats = (List<Chat>) response.getData();
            for (Chat chat:chats){
                iChatService.deleteChat(chat.getChatId());
            }
        }
        return getChatResponse(response);
    }

    private ServiceResponse getChatRes(HashMap map){
        if (map==null){
            return ServiceResponse.createByErrorMessage("获取失败");
        }
        String userId = (String) map.get("userId");
        String partnerId = (String) map.get("partnerId");
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(partnerId)){
            return ServiceResponse.createByErrorMessage("请求参数错误");
        }
        ServiceResponse response = iChatService.selectCurrentChat(partnerId, userId);
        return response;
    }

    private ServiceResponse getChatResponse(ServiceResponse response) {
        if (!response.isSuccess()){
            return response;
        }
        List<Chat> chats = (List<Chat>) response.getData();
        List<ChatEntity> chatEntities = new ArrayList<>();
        for (Chat chat:chats){
            listChatToMsg(chatEntities, chat);
        }
        return ServiceResponse.createBySuccessData(chatEntities);
    }
}
