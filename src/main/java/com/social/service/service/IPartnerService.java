package com.social.service.service;

import com.social.service.common.ServiceResponse;
import com.social.service.domain.Partner;

public interface IPartnerService {
    public ServiceResponse addPartner(Partner partner);

    public ServiceResponse deletePartner(String partId);

    public ServiceResponse getPartners(String userId);

    public ServiceResponse getConcerns(String userId);

    public Partner getByUserAndPartner(String userId, String partnerId);
}
