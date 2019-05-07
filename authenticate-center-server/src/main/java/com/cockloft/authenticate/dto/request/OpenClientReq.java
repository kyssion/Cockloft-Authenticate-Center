package com.cockloft.authenticate.dto.request;


import com.cockloft.core.dto.BaseDto;

public class OpenClientReq extends BaseDto {
    private String urlId;
    private String redirectUri;//redirect url adress
    private String status;

    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
