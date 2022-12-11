package com.hyperctrl.jenkins.shared.library.utils

import com.aliyuncs.DefaultAcsClient
import com.aliyuncs.IAcsClient
import com.aliyuncs.domain.model.v20180129.QueryDomainListRequest
import com.aliyuncs.domain.model.v20180129.QueryDomainListResponse
import com.aliyuncs.profile.DefaultProfile

class DnsClient {

    private IAcsClient client

    DnsClient(String accessKeyId, String accessKeySecret) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        client = new DefaultAcsClient(profile)
    }

    QueryDomainListResponse getScrollDomainList() {
        QueryDomainListRequest request = new QueryDomainListRequest()
        request.setPageNum(1)
        request.setPageSize(100)
        QueryDomainListResponse response = client.getAcsResponse(request);
        return response
    }
}
