package com.hyperctrl.jenkins.shared.library.service.impl

import com.aliyuncs.domain.model.v20180129.QueryDomainListResponse
import com.hyperctrl.jenkins.shared.library.service.api.IAliyunService
import com.hyperctrl.jenkins.shared.library.utils.CredentialsUtils
import com.hyperctrl.jenkins.shared.library.utils.DnsClient
import com.hyperctrl.jenkins.shared.library.utils.JenkinsCredential

class AliyunService implements IAliyunService {

    def script;

    private DnsClient dnsClient

    AliyunService(def script) {
        this.script = script
    }

    void login() {
        String aliyunCredentialsId = this.script.params.AliyunCredentialsId
        JenkinsCredential credential = CredentialsUtils.getCredentials(script, aliyunCredentialsId)
        dnsClient = new DnsClient(credential.username, credential.password)
    }

    @Override
    QueryDomainListResponse getScrollDomainList() {
        QueryDomainListResponse response = dnsClient.getScrollDomainList()
        return response
    }
}
