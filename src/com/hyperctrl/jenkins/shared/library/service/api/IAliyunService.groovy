package com.hyperctrl.jenkins.shared.library.service.api

import com.aliyuncs.domain.model.v20180129.QueryDomainListResponse

interface IAliyunService {

    void login()

    QueryDomainListResponse getScrollDomainList()
}
