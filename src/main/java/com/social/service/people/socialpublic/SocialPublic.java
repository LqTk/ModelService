package com.social.service.people.socialpublic;

import com.social.service.common.ServiceResponse;
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
        sPublic.setShareid(UUID.randomUUID().toString().replaceAll("-",""));
        sPublic.setReviewcount(0);
        sPublic.setGoodscount(0);
        return iPublicService.insert(sPublic);
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ServiceResponse delete(@RequestBody String shareId){
        if (StringUtils.isBlank(shareId))
            return ServiceResponse.createByIllegalArgument();
        return iPublicService.delete(shareId);
    }

    @RequestMapping(value = "addview", method = RequestMethod.PUT)
    public ServiceResponse addView(@RequestBody Review review){
        if (null == review)
            return ServiceResponse.createByIllegalArgument();
        review.setReviewid(UUID.randomUUID().toString().replaceAll("-",""));
        return iPublicService.addReview(review);
    }

    @RequestMapping(value = "deleteView", method = RequestMethod.DELETE)
    public ServiceResponse deleteView(@RequestBody String viewId){
        if (StringUtils.isBlank(viewId))
            return ServiceResponse.createByIllegalArgument();
        return iPublicService.deleteReview(viewId);
    }

    @RequestMapping(value = "addGoods", method = RequestMethod.PUT)
    public ServiceResponse addGoods(@RequestBody Goods goods){
        if (null == goods){
            return ServiceResponse.createByIllegalArgument();
        }
        goods.setGoodsid(UUID.randomUUID().toString().replaceAll("-",""));
        return iPublicService.addGoods(goods);
    }

    @RequestMapping(value = "deleteGoods", method = RequestMethod.DELETE)
    public ServiceResponse deleteGoods(@RequestBody String goodsId){
        if (StringUtils.isBlank(goodsId))
            return ServiceResponse.createByIllegalArgument();
        return iPublicService.deleteGoods(goodsId);
    }

    @RequestMapping(value = "getdatas", method = RequestMethod.GET)
    public ServiceResponse getdatas(){
        return iPublicService.getTenCounts();
    }

    @RequestMapping(value = "getByShareId", method = RequestMethod.GET)
    public ServiceResponse getByShareId(@RequestParam("shareId")String shareId){
        return iPublicService.getByShareId(shareId);
    }
}
