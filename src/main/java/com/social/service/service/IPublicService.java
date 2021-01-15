package com.social.service.service;

import com.social.service.common.ServiceResponse;
import com.social.service.domain.ChatReview;
import com.social.service.domain.Goods;
import com.social.service.domain.Review;
import com.social.service.domain.SPublic;

public interface IPublicService {
    public ServiceResponse insert(SPublic pb);

    public ServiceResponse delete(String pbId);

    public ServiceResponse deleteReview(String reviewId);

    public ServiceResponse addReview(Review review);

    public ServiceResponse addReviewChat(ChatReview chatReview);

    public ServiceResponse deleteChatReview(String chatId);

    public ServiceResponse addGoods(Goods goods);

    public ServiceResponse deleteGoods(String goodsId);

    public ServiceResponse getTenCounts();

    public ServiceResponse getByUserId(String userId, int page);

    public ServiceResponse getByShareId(String shareId);

    public ServiceResponse updataHeadImg(String userId, String headImg);
}
