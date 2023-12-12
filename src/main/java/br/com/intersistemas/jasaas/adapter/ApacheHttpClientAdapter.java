package br.com.intersistemas.jasaas.adapter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import br.com.intersistemas.jasaas.exception.ConnectionException;

/**
 *
 * @author bosco
 */
public class ApacheHttpClientAdapter implements AdapterConnection {

    private final String accessToken;
    private final CloseableHttpClient httpclient;

    public ApacheHttpClientAdapter(String acessToken) {
        this.accessToken = acessToken;
        httpclient = HttpClients
            .custom()
            .setDefaultRequestConfig(
                RequestConfig
                    .custom()
                    .setCookieSpec(CookieSpecs.STANDARD)
                    .build()
            )
            .build();
    }

    @Override
    public String get(String url) throws ConnectionException {
        try {
            System.out.println("ADAPTER GET: "+url);
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("access_token", accessToken);
            CloseableHttpResponse response = httpclient.execute(httpGet);

            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() != 200) {
                throw new ConnectionException(status.getStatusCode(), status.getReasonPhrase());
            }

            HttpEntity entity = response.getEntity();
            String retorno = EntityUtils.toString(entity);

            return retorno;
        } catch (IOException ex) {
            Logger.getLogger(ApacheHttpClientAdapter.class.getName()).log(Level.SEVERE, null, ex);
            throw new ConnectionException(500, ex.getMessage());
        }
        //return null;
    }

    @Override
    public String delete(String url) throws ConnectionException {
        try {
            HttpDelete httpDelete = new HttpDelete(url);
            httpDelete.addHeader("access_token", accessToken);
            CloseableHttpResponse response = httpclient.execute(httpDelete);

            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() != 200) {
                throw new ConnectionException(status.getStatusCode(), status.getReasonPhrase());
            }
            HttpEntity entity = response.getEntity();
            String retorno = EntityUtils.toString(entity);
            //System.out.println(retorno);
            return retorno;
        } catch (IOException ex) {
            Logger.getLogger(ApacheHttpClientAdapter.class.getName()).log(Level.SEVERE, null, ex);
            throw new ConnectionException(500, ex.getMessage());
        }
    }

    /*@Override
    public String put(String url, String content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    @Override
    public String post(String url, String contentJSON) throws ConnectionException {
        try {
            //System.out.println(url);
            //System.out.println(contentJSON);
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("access_token", accessToken);

            StringEntity entity = new StringEntity(contentJSON);
            httpPost.setEntity(entity);

            CloseableHttpResponse response = httpclient.execute(httpPost);
            //System.out.println("CloseableHttpResponse");
//            for (Header allHeader : response.getAllHeaders()) {
//                System.out.println(allHeader.toString());
//            }
            StatusLine status = response.getStatusLine();
            System.out.println("Status: " + status.getStatusCode());
            if (status.getStatusCode() != 200 && status.getStatusCode() != 400) {
                System.out.println(status.getReasonPhrase());
                throw new ConnectionException(status.getStatusCode(), status.getReasonPhrase());
            }
            
            HttpEntity entidade = response.getEntity();
            String retorno = EntityUtils.toString(entidade);
//            System.out.println("retorno");
//            System.out.println(retorno);
            return retorno;
        } catch (IOException ex) {
            Logger.getLogger(ApacheHttpClientAdapter.class.getName()).log(Level.SEVERE, null, ex);
            throw new ConnectionException(500, ex.getMessage());
        }
    }

}
