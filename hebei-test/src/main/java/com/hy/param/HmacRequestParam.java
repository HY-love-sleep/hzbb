package com.hy.param;

/**
 * Description: 哈希请求参数
 *
 * @author: yhong
 * Date: 2024/5/15
 */
public class HmacRequestParam {

    /**
     * transId : 234343434342323231313
     * appId : APP_3510558085994A75A57645255822FBF5
     * keyId : 7421c939337a474c9012b63270773f9e
     * source : MDoCAQEwEgIBATAKBggqgRzPVQFoAQoBAQMhAGPSREPCUlAVnSJvK55xaxHM3C3B6NHNkQfwWrcnkJtK
     */

    private String transId;
    private String appId;
    private String keyId;
    private String source;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
