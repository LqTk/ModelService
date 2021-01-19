package com.social.service.people.socialpublic;

import com.social.service.common.ServiceResponse;
import com.social.service.domain.ChatReview;
import com.social.service.domain.Goods;
import com.social.service.domain.Review;
import com.social.service.domain.SPublic;
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

    @RequestMapping(value = "publish", method = RequestMethod.POST)
    public ServiceResponse publish(@RequestBody SPublic sPublic){
        if (null == sPublic)
            return ServiceResponse.createByIllegalArgument();
        sPublic.setShareId(UUID.randomUUID().toString().replaceAll("-",""));
        sPublic.setReviewCount(0);
        sPublic.setGoodsCount(0);
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
}
