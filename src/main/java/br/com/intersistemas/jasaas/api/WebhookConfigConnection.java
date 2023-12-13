/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intersistemas.jasaas.api;

import br.com.intersistemas.jasaas.adapter.AdapterConnection;
import br.com.intersistemas.jasaas.entity.WebhookConfig;
import br.com.intersistemas.jasaas.exception.ConnectionException;
import br.com.intersistemas.jasaas.util.JsonUtil;

/**
 * @author atendimento
 */
public class WebhookConfigConnection extends AbstractConnection {

    private final AdapterConnection adapter;

    public WebhookConfigConnection(AdapterConnection adapter, int abstractConnectionEndpoint) {
        super(abstractConnectionEndpoint);
        this.adapter = adapter;
    }

    public WebhookConfig get() throws ConnectionException {
        lastResponseJson = adapter.get((endpoint + "/webhook/"));
        return (WebhookConfig) JsonUtil.parse(lastResponseJson, WebhookConfig.class);
    }

    public WebhookConfig createWebhookConfig(WebhookConfig webhookConfig) throws ConnectionException {
        return updateWebhookConfig(webhookConfig);
    }

    public WebhookConfig updateWebhookConfig(WebhookConfig webhookConfig) throws ConnectionException {
        String webhookConfigJSON = JsonUtil.toJSON(webhookConfig);
        String data = adapter.post((endpoint + "/webhook/"), webhookConfigJSON);
        WebhookConfig subscriptionUpdated = (WebhookConfig) JsonUtil.parse(data, WebhookConfig.class);
        return subscriptionUpdated;
    }
}
