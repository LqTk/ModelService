package com.social.service.service.impl;

import com.social.service.common.ServiceResponse;
import com.social.service.dao.PartnerMapper;
import com.social.service.dao.UserMapper;
import com.social.service.domain.Partner;
import com.social.service.domain.PartnerEntity;
import com.social.service.domain.User;
import com.social.service.service.IPartnerService;
import com.social.service.util.JPushClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iPartnerService")
public class PartnerService implements IPartnerService {

    @Autowired
    PartnerMapper partnerMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public ServiceResponse addPartner(Partner partner) {
        if (partner==null){
            return ServiceResponse.createByIllegalArgument();
        }
        int insert = partnerMapper.insert(partner);
        if (insert>0){
            User user = userMapper.selectByPrimaryKey(partner.getUserId());
            User partnerUser = userMapper.selectByPrimaryKey(partner.getPartnerId());
            if (user.getRegistrationid()!=null)
            JPushClientUtil.sendToRegistrationId(user.getRegistrationid(),"好友关注成功","好友关注结果","关注成功","");
            if (partnerUser.getRegistrationid()!=null)
            JPushClientUtil.sendToRegistrationId(partnerUser.getRegistrationid(),"消息提示","有人关注了您",user.getName()+"关注了您","");
            return ServiceResponse.createBySuccessMessage("关注成功");
        }
        return ServiceResponse.createByErrorMessage("关注失败");
    }

    @Override
    public ServiceResponse deletePartner(String partId) {
        if (StringUtils.isBlank(partId))
            return ServiceResponse.createByErrorMessage("未找到好友");
        int i = partnerMapper.deleteByPrimaryKey(partId);
        if (i>0){
            return ServiceResponse.createBySuccessMessage("取消关注成功");
        }
        return ServiceResponse.createByErrorMessage("取消关注失败");
    }

    @Override
    public ServiceResponse getPartners(String userId) {
        if (StringUtils.isBlank(userId)){
            return ServiceResponse.createByErrorMessage("好友不能为空");
        }
        List<PartnerEntity> partners = partnerMapper.getPartners(userId);
        if (partners!=null){
            return ServiceResponse.createBySuccessData(partners);
        }
        return ServiceResponse.createByErrorMessage("获取好友信息失败");
    }

    @Override
    public ServiceResponse getConcerns(String userId) {
        if (StringUtils.isBlank(userId)){
            return ServiceResponse.createByErrorMessage("好友不能为空");
        }
        List<PartnerEntity> concerns = partnerMapper.getConcerns(userId);
        List<PartnerEntity> partners = partnerMapper.getPartners(userId);
        if (concerns!=null){
            concerns.removeAll(partners);
            return ServiceResponse.createBySuccessData(concerns);
        }
        return ServiceResponse.createByErrorMessage("获取关注列表失败");
    }

    @Override
    public Partner getByUserAndPartner(String userId, String partnerId) {
        Partner partner = partnerMapper.selectByUserIdAndPartnerId(userId, partnerId);
        return partner;
    }
}
