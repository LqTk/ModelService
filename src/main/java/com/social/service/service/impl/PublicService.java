package com.social.service.service.impl;

import com.social.service.common.ServiceResponse;
import com.social.service.dao.GoodsMapper;
import com.social.service.dao.ReviewMapper;
import com.social.service.dao.SPublicMapper;
import com.social.service.dao.UserMapper;
import com.social.service.domain.Goods;
import com.social.service.domain.Review;
import com.social.service.domain.SPublic;
import com.social.service.domain.User;
import com.social.service.service.IPublicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public ServiceResponse insert(SPublic pb) {
        if (pb == null){
            return ServiceResponse.createByIllegalArgument();
        }
        pb.setShareid(UUID.randomUUID().toString().replaceAll("-", ""));
        int insert = sPublicMapper.insert(pb);
        if (insert>0){
            return ServiceResponse.createBySuccessData(pb);
        }
        return ServiceResponse.createByErrorMessage("发布失败！");
    }

    @Override
    public ServiceResponse delete(String pbId) {
        if (StringUtils.isBlank(pbId)){
            return ServiceResponse.createByIllegalArgument();
        }
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
        SPublic sPublic = sPublicMapper.selectByPrimaryKey(review.getPublicid());
        if (sPublic!=null){
            int insert = reviewMapper.insert(review);
            if (insert>0){
                sPublic.setReviewcount(sPublic.getReviewcount()+1);
                sPublicMapper.addGoodsCount(sPublic.getShareid(),sPublic.getReviewcount());
                return ServiceResponse.createBySuccessData(review);
            }
            return ServiceResponse.createByErrorMessage("评论失败");
        }
        return ServiceResponse.createByErrorMessage("动态被隐藏起来啦~");
    }

    @Override
    public ServiceResponse addGoods(Goods goods) {
        if (null == goods){
            return ServiceResponse.createByIllegalArgument();
        }
        SPublic sPublic = sPublicMapper.selectByPrimaryKey(goods.getPublicid());
        if (sPublic!=null){
            int insert = goodsMapper.insert(goods);
            if (insert>0){
                sPublic.setGoodscount(sPublic.getReviewcount()+1);
                sPublicMapper.addGoodsCount(sPublic.getShareid(),sPublic.getGoodscount());
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
        List<SPublic> socialPublicSize = sPublicMapper.getSocialPublicSize();
        if (socialPublicSize!=null){
            return ServiceResponse.createBySuccessData(socialPublicSize);
        }
        return ServiceResponse.createByErrorMessage("获取数据失败");
    }

    @Override
    public ServiceResponse getByUserId(String userId, int page) {
        if (StringUtils.isBlank(userId))
            return ServiceResponse.createByErrorMessage("未找到用户");
        List<SPublic> socialPublicSize = sPublicMapper.getSocialByUserId(userId, page*10, (page+1)*10);
        if (socialPublicSize!=null){
            return ServiceResponse.createBySuccessData(socialPublicSize);
        }
        return ServiceResponse.createByErrorMessage("获取数据失败");
    }

    @Override
    public ServiceResponse getByShareId(String shareId) {
        if (StringUtils.isBlank(shareId))
            return ServiceResponse.createByErrorMessage("未查询到信息");
        SPublic sPublic = sPublicMapper.selectByPrimaryKey(shareId);
        if (sPublic==null)
            return ServiceResponse.createByErrorMessage("未查询到信息");
        return ServiceResponse.createBySuccessData(sPublic);
    }

    @Override
    public ServiceResponse updataHeadImg(String userId, String headImg) {
        if (StringUtils.isBlank(userId))
            return ServiceResponse.createByErrorMessage("未查询到信息");
        int i = sPublicMapper.updataHeadImg(userId, headImg);
        if (i>0){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }


}
