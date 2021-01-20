package com.social.service.service.impl;

import com.social.service.common.ServiceResponse;
import com.social.service.dao.*;
import com.social.service.domain.*;
import com.social.service.service.IPublicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("iPublicService")
public class PublicService implements IPublicService {

    @Autowired
    SPublicMapper sPublicMapper;

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ChatReviewMapper chatReviewMapper;

    @Override
    public ServiceResponse insert(SPublic pb) {
        if (pb == null){
            return ServiceResponse.createByIllegalArgument();
        }
        pb.setShareId(UUID.randomUUID().toString().replaceAll("-", ""));
        pb.setCreatetime(new Date());
        int insert = sPublicMapper.insert(pb);
        if (insert>0){
            PublicedEntity publicedEntity = sPublicMapper.selectByPrimaryKey(pb.getShareId());
            return ServiceResponse.createBySuccessData(publicedEntity);
        }
        return ServiceResponse.createByErrorMessage("发布失败！");
    }

    @Override
    public ServiceResponse delete(String pbId) {
        if (StringUtils.isBlank(pbId)){
            return ServiceResponse.createByIllegalArgument();
        }
        PublicedEntity publicedEntity = sPublicMapper.selectByPrimaryKey(pbId);
        List<Review> reviews = reviewMapper.selectByPbId(pbId);
        if (reviews!=null && reviews.size()>0){
            for (Review review:reviews){
                deleteReview(review.getReviewId());
            }
        }
        goodsMapper.deleteByPbulishId(pbId);


        int i = sPublicMapper.deleteByPrimaryKey(pbId);
        if (i>0){
            return ServiceResponse.createBySuccessMessage("删除成功");
        }
        return ServiceResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServiceResponse deleteReview(String reviewId) {
        if (StringUtils.isBlank(reviewId)){
            return ServiceResponse.createByIllegalArgument();
        }
        chatReviewMapper.deleteByReviewId(reviewId);
        int i = reviewMapper.deleteByPrimaryKey(reviewId);
        if (i>0){
            return ServiceResponse.createBySuccessMessage("删除成功");
        }
        return ServiceResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServiceResponse addReview(Review review) {
        if (null == review){
            return ServiceResponse.createByIllegalArgument();
        }
        PublicedEntity sPublic = sPublicMapper.selectByShareId(review.getPublicId());
        if (sPublic!=null){
            int insert = reviewMapper.insert(review);
            if (insert>0){
                ReviewEntity reviewEntity = reviewMapper.selectByReviewId(review.getReviewId());
                if (reviewEntity!=null)
                    return ServiceResponse.createBySuccessData(reviewEntity);
            }
            return ServiceResponse.createByErrorMessage("评论失败");
        }
        return ServiceResponse.createByErrorMessage("动态被隐藏起来啦~");
    }

    @Override
    public ServiceResponse addReviewChat(ChatReview chatReview) {
        chatReview.setReviewChatId(UUID.randomUUID().toString().replaceAll("-",""));
        int insert = chatReviewMapper.insert(chatReview);
        if (insert>0){
            ChatReviewEntity chatReview1 = chatReviewMapper.findChatReview(chatReview.getReviewChatId());
            if (chatReview1!=null)
                return ServiceResponse.createBySuccessData(chatReview1);
        }
        return ServiceResponse.createByErrorMessage("回复失败");
    }

    @Override
    public ServiceResponse deleteChatReview(String chatId) {
        if (StringUtils.isBlank(chatId))
            return ServiceResponse.createByIllegalArgument();
        int i = chatReviewMapper.deleteByPrimaryKey(chatId);
        if (i>0)
            return ServiceResponse.createBySuccessMessage("删除成功");
        return ServiceResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServiceResponse addGoods(Goods goods) {
        if (null == goods){
            return ServiceResponse.createByIllegalArgument();
        }
        PublicedEntity sPublic = sPublicMapper.selectByShareId(goods.getPublicId());
        if (sPublic!=null){
            int insert = goodsMapper.insert(goods);
            if (insert>0){
                return ServiceResponse.createBySuccessData(goods);
            }
            return ServiceResponse.createByErrorMessage("点赞失败");
        }
        return ServiceResponse.createByErrorMessage("动态被隐藏起来啦~");
    }

    @Override
    public ServiceResponse deleteGoods(String goodsId) {
        if (StringUtils.isBlank(goodsId)){
            return ServiceResponse.createByIllegalArgument();
        }
        int i = goodsMapper.deleteByPrimaryKey(goodsId);
        if (i>0){
            return ServiceResponse.createBySuccessMessage("取消成功");
        }
        return ServiceResponse.createByErrorMessage("取消失败");
    }

    @Override
    public ServiceResponse getTenCounts() {
        List<PublicedEntity> socialPublicSize = sPublicMapper.getSocialPublicSize();
        if (socialPublicSize!=null){
            return ServiceResponse.createBySuccessData(socialPublicSize);
        }
        return ServiceResponse.createByErrorMessage("获取数据失败");
    }

    @Override
    public ServiceResponse getByUserId(String userId, int page) {
        if (StringUtils.isBlank(userId))
            return ServiceResponse.createByErrorMessage("未找到用户");
        List<PublicedEntity> socialPublicSize = sPublicMapper.getSocialByUserId(userId, page*10, (page+1)*10);
        if (socialPublicSize!=null){
            return ServiceResponse.createBySuccessData(socialPublicSize);
        }
        return ServiceResponse.createByErrorMessage("获取数据失败");
    }

    @Override
    public ServiceResponse getByShareId(String shareId) {
        if (StringUtils.isBlank(shareId))
            return ServiceResponse.createByErrorMessage("未查询到信息");
        PublicedEntity sPublic = sPublicMapper.selectByShareId(shareId);
        if (sPublic==null)
            return ServiceResponse.createByErrorMessage("未查询到信息");
        return ServiceResponse.createBySuccessData(sPublic);
    }

}
