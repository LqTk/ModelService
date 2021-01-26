package com.social.service.people.socialpublic;

import com.social.service.common.Const;
import com.social.service.common.ServiceResponse;
import com.social.service.domain.*;
import com.social.service.service.IMsgService;
import com.social.service.service.IPublicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/spublic/")
public class SocialPublic {

    @Autowired
    IPublicService iPublicService;

    @Autowired
    IMsgService iMsgService;

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

    @RequestMapping(value = "getdatas", method = RequestMethod.GET)
    public ServiceResponse getdatas(){
        return iPublicService.getTenCounts();
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
}
