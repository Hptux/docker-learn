package com.shadows.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RequestService {
    private String domain = "http://www.ahjxjy.cn";
    public HttpClientContext login(String name, String pwd) throws IOException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("userKey", name));
        params.add(new BasicNameValuePair("pwd", pwd));
        params.add(new BasicNameValuePair("username", "stu_" + name));
        params.add(new BasicNameValuePair("stuOrTea", "stu_"));

        return this.post("/users/login", params);
    }

    public String get(HttpClientContext httpClientContext, String url, Map<String, String> params) {
        CookieStore cookieStore = httpClientContext.getCookieStore();
        cookieStore.getCookies().forEach(cookie -> log.info("cookie : {} ==== value : {}", cookie.getName(), cookie.getValue()));
        HttpGet getMethod = new HttpGet(domain + url + "?" + this.genRequestString(params));
        getMethod.addHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = httpClient.execute(getMethod, httpClientContext);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result =  EntityUtils.toString(response.getEntity());
                log.info("response result = {}", result);
                return result;
            } else {
                log.error("request error, http status:{}", response.getStatusLine().getStatusCode());
                throw new RuntimeException("response not ok");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String genRequestString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        params.keySet().forEach(key -> sb.append(key).append("=").append(params.get(key)).append("&"));
        return sb.toString();
    }

    public HttpClientContext post(String url, List<NameValuePair> params) throws IOException {
        HttpClientContext context = HttpClientContext.create();
        HttpPost post = new HttpPost(domain + url);
        post.setConfig(RequestConfig.custom().setConnectTimeout(60 * 1000)
                .setSocketTimeout(60 * 1000).build());
        post.addHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType());
        post.setEntity(new UrlEncodedFormEntity(params));
        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient client = HttpClientBuilder.create().setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setRedirectStrategy(new DefaultRedirectStrategy()).setDefaultCookieStore(cookieStore).build();
        CloseableHttpResponse httpResponse = null;
        try {
            log.info("发送报文[{}]", params.toString());

            httpResponse = client.execute(post, context);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                return context;
            } else if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY){
                Header locations = httpResponse.getFirstHeader("location");
                this.get(context, locations.getValue(), Collections.emptyMap());
            } else {
                log.error("request error, http status:{}", httpResponse.getStatusLine().getStatusCode());
                throw new RuntimeException("response not ok");
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("request : {} error", url, e);
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
            post.releaseConnection();
        }

        return context;
    }
}
