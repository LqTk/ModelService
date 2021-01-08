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
            User user = userMapper.selectByPrimaryKey(partner.getUserid());
            User partnerUser = userMapper.selectByPrimaryKey(partner.getPartnerid());
            if (user.getRegistrationid()!=null)
            JPushClientUtil.sendToRegistrationId(user.getRegistrationid(),"好友添加成功","好友添加结果","添加成功","");
            if (partnerUser.getRegistrationid()!=null)
            JPushClientUtil.sendToRegistrationId(partnerUser.getRegistrationid(),"请求添加好友","请求添加好友",user.getName()+"请求添加好友","");
            return ServiceResponse.createBySuccessMessage("添加成功");
        }
        return ServiceResponse.createByErrorMessage("添加失败");
    }

    @Override
    public ServiceResponse deletePartner(String partId) {
        if (StringUtils.isBlank(partId))
            return ServiceResponse.createByErrorMessage("未找到好友");
        int i = partnerMapper.deleteByPrimaryKey(partId);
        if (i>0){
            return ServiceResponse.createBySuccessMessage("删除成功");
        }
        return ServiceResponse.createByErrorMessage("删除失败");
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
    public int getByUserAndPartner(String userId, String partnerId) {
        Partner partner = partnerMapper.selectByUserIdAndPartnerId(userId, partnerId);
        if (partner!=null) {
            return 1;
        }else {
            return 0;
        }
    }
}
