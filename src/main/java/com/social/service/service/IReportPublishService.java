package com.social.service.service;

import com.social.service.common.ServiceResponse;
import com.social.service.domain.ReportPublishWithBLOBs;

public interface IReportPublishService {
    ServiceResponse insert(ReportPublishWithBLOBs reportPublish);
}
