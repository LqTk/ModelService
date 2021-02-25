package com.social.service.people.socialpublic;

import com.social.service.common.Const;
import com.social.service.common.ServiceResponse;
import com.social.service.domain.*;
import com.social.service.service.IFileService;
import com.social.service.service.IMsgService;
import com.social.service.service.IPublicService;
import com.social.service.service.IReportPublishService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/spublic/")
public class SocialPublic {

    @Autowired
    IPublicService iPublicService;

    @Autowired
    IMsgService iMsgService;

    @Autowired
    IFileService iFileService;

    @Autowired
    IReportPublishService iReportPublishService;

    @RequestMapping(value = "publish", method = RequestMethod.POST)
    public ServiceResponse publish(@RequestBody SPublic sPublic){
        if (null == sPublic)
            return ServiceResponse.createByIllegalArgument();
        return iPublicService.insert(sPublic);
    }

    @RequestMapping(value = "deletePublish/{publishId}", method = RequestMethod.DELETE)
    public ServiceResponse delete(@PathVariable String publishId){
        if (StringUtils.isBlank(publishId))
            return ServiceResponse.createByIllegalArgument();
        iReportPublishService.deleteImg(publishId);
        return iPublicService.delete(publishId);
    }

    @RequestMapping(value = "addview", method = RequestMethod.PUT)
    public ServiceResponse addView(@RequestBody Review review){
        if (null == review)
            return ServiceResponse.createByIllegalArgument();
        review.setReviewId(UUID.randomUUID().toString().replaceAll("-",""));
        return iPublicService.addReview(review);
    }

    @RequestMapping(value = "deleteView/{reviewId}", method = RequestMethod.DELETE)
    public ServiceResponse deleteView(@PathVariable String reviewId){
        if (StringUtils.isBlank(reviewId))
            return ServiceResponse.createByIllegalArgument();
        return iPublicService.deleteReview(reviewId);
    }

    @RequestMapping(value = "addGoods", method = RequestMethod.PUT)
    public ServiceResponse addGoods(@RequestBody Goods goods){
        if (null == goods){
            return ServiceResponse.createByIllegalArgument();
        }
        goods.setGoodsId(UUID.randomUUID().toString().replaceAll("-",""));
        return iPublicService.addGoods(goods);
    }

    @RequestMapping(value = "deleteGoods/{goodsId}", method = RequestMethod.DELETE)
    public ServiceResponse deleteGoods(@PathVariable String goodsId){
        if (StringUtils.isBlank(goodsId))
            return ServiceResponse.createByIllegalArgument();
        return iPublicService.deleteGoods(goodsId);
    }

    @RequestMapping(value = "getdatas/{page}", method = RequestMethod.GET)
    public ServiceResponse getdatas(@PathVariable int page){
        return iPublicService.getTenCounts(page);
    }

    @RequestMapping(value = "getByShareId/{shareId}", method = RequestMethod.GET)
    public ServiceResponse getByShareId(@PathVariable String shareId){
        return iPublicService.getByShareId(shareId);
    }

    @RequestMapping(value = "getShareMsg/{shareId}/{msgId}", method = RequestMethod.GET)
    public ServiceResponse getShareMsg(@PathVariable String shareId, @PathVariable String msgId){
        ServiceResponse byShareId = iPublicService.getByShareId(shareId);
        PublicedEntity data = (PublicedEntity) byShareId.getData();
        ServiceResponse msgByMsgId = iPublicService.getMsgByMsgId(msgId);
        if (msgByMsgId.isSuccess()){
            Msg msg = (Msg) msgByMsgId.getData();
            if (msg.getMsgType().equals(Const.MSG_GOODS)){
                data.getReviewEntities().clear();
                return ServiceResponse.createBySuccessData(data);
            }else {
                data.getReviewEntities().clear();
                ServiceResponse review = iPublicService.getReview(msg.getReviewId());
                if (review.isSuccess()){
                    ReviewEntity reviewEntity = (ReviewEntity) review.getData();
                    data.getReviewEntities().add(reviewEntity);
                    return ServiceResponse.createBySuccessData(data);
                }
            }
        }
        return ServiceResponse.createByErrorMessage("数据获取失败");
    }

    @RequestMapping(value = "addChatReview",method = RequestMethod.PUT)
    public ServiceResponse addChatReview(@RequestBody ChatReview chatReview){
        if (chatReview==null)
            return ServiceResponse.createByIllegalArgument();
        return iPublicService.addReviewChat(chatReview);
    }

    @RequestMapping(value = "deleteChatReview/{chatId}",method = RequestMethod.DELETE)
    public ServiceResponse deleteChatReview(@PathVariable String chatId){
        return iPublicService.deleteChatReview(chatId);
    }

    /**
     * 获取公场的未读消息
     * @param userId
     * @return
     */
    @RequestMapping(value = "getMsgCount/{userId}",method = RequestMethod.GET)
    public ServiceResponse getMsgCount(@PathVariable String userId){
        return iMsgService.selectByPeopleId(userId);
    }

    /**
     * 更新公场消息阅读状态
     */
    @RequestMapping(value = "updateReadState/{msgId}",method = RequestMethod.PUT)
    public ServiceResponse updateReadState(@PathVariable String msgId){
        return iMsgService.updateReadedState(msgId);
    }

    /**
     * @param file 上传的文件
     * @return
     */
    @RequestMapping(value = "uploadFile/{userId}", method = RequestMethod.POST)
    public ServiceResponse uploadFile(@RequestParam(value = "file",required = false) MultipartFile file, @PathVariable String userId){
        String filePath = Const.publishFile;
        return saveUploadFile(file, filePath, userId);
    }

    /**
     * @param file 上传的文件
     * @return
     */
    @RequestMapping(value = "reportFile/{userId}", method = RequestMethod.POST)
    public ServiceResponse reportFile(@RequestParam(value = "file",required = false) MultipartFile file, @PathVariable String userId){
        String filePath = Const.reportPublishFile;
        return saveUploadFile(file, filePath, userId);
    }

    private ServiceResponse saveUploadFile(@RequestParam(value = "file", required = false) MultipartFile file, String filePath, String userId) {
        String uploadUrl = iFileService.upload(file, Const.uploadDir+filePath, userId);
        File file1 = new File(uploadUrl);
        if (file1.exists()){
            HashMap map = new HashMap();
            map.put("url",filePath+"/"+file1.getName());
            return ServiceResponse.createBySuccessData(map);
        }
        return ServiceResponse.createByErrorMessage("上传失败");
    }

    /**
     * 举报
     */
    @RequestMapping(value = "reportPublish",method = RequestMethod.POST)
    public ServiceResponse reportPublish(@RequestBody Map map){
        String publishId = (String) map.get("publishId");
        String reportUserId = (String) map.get("userId");
        String text = (String) map.get("text");
        String img = (String) map.get("img");
        if (StringUtils.isBlank(publishId)||StringUtils.isBlank(reportUserId)||StringUtils.isBlank(text)){
            return ServiceResponse.createByErrorMessage("参数不能为空");
        }
        ReportPublishWithBLOBs reportPublish = new ReportPublishWithBLOBs();
        reportPublish.setReportid(UUID.randomUUID().toString().replaceAll("-",""));
        reportPublish.setImg(img);
        reportPublish.setText(text);
        reportPublish.setPublishid(publishId);
        reportPublish.setReportuserid(reportUserId);
        return iReportPublishService.insert(reportPublish);
    }
}
