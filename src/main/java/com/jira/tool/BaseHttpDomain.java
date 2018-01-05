package com.jira.tool;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;

public class BaseHttpDomain {
 
	private HttpClient client;
	private HttpDelete httpDelete;
	private HttpPost httpPost;
    private HttpPut httpPut;
    private DefaultHttpClient defaultHttpClient;
    
    //httpClient
    public HttpClient getClientUseProxyAndBasicAuthor(String AuthorName, String AuthorPassword) {
        //ProxyHost proxy = new ProxyHost("135.245.115.235", 80);
        client = new HttpClient();
        client.getParams().setAuthenticationPreemptive(true);
        client.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(AuthorName, AuthorPassword));
        //client.getHostConfiguration().setProxyHost(proxy);
        return client;
    }
    
    //DefaultHttpClient
    public DefaultHttpClient getDefaultHttpClient() {
        defaultHttpClient = new DefaultHttpClient();
        return defaultHttpClient;
    }
    
    //getMethod
    public GetMethod getGetMethodByJson(String url) {
        GetMethod method = new GetMethod(url);
        method.addRequestHeader("content-type", "application/json");
        method.addRequestHeader("Accept", "application/json");
        return method;
    }
    
    //httpDelete
    public HttpDelete getHttpDelete(String url, String authorName, String password) {
        httpDelete = new HttpDelete(url);
        String auth = new String(Base64.encodeBase64((authorName + ":" + password).getBytes()));
        httpDelete.setHeader(HttpHander.TOKEN, HttpHander.CHECK);
        httpDelete.setHeader(HttpHander.AUTHO, HttpHander.BASIC + auth);
        //MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        httpDelete.addHeader("Content-Type", "application/json");
        return httpDelete;
    }
    
    
  //httpPost function with postJSONString
    public HttpPost getDefaultPostMethodWithAuthorPostJson(String AuthorName, String AuthorPassword, String url, String postJSONString) {
        this.getDefaultPostMethod(url);
        String auth = new String(Base64.encodeBase64((AuthorName + ":" + AuthorPassword).getBytes()));
        httpPost.setHeader(HttpHander.TOKEN, HttpHander.CHECK);
        httpPost.setHeader(HttpHander.AUTHO, HttpHander.BASIC + auth);
        StringEntity params = null;
          try {
            params = new StringEntity(postJSONString);
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          }
         
        httpPost.addHeader("content-type", "application/json");
        httpPost.addHeader("Accept", "application/json");
        httpPost.setEntity(params);
        return httpPost;
    }
    
    //httpPost function without postJSONString
    public HttpPost getDefaultPostMethodWithAuthorPostJson(String AuthorName, String AuthorPassword, String url) {
        this.getDefaultPostMethod(url);
        String auth = new String(Base64.encodeBase64((AuthorName + ":" + AuthorPassword).getBytes()));
        httpPost.setHeader(HttpHander.TOKEN, HttpHander.CHECK);
        httpPost.setHeader(HttpHander.AUTHO, HttpHander.BASIC + auth);
        //StringEntity params = null;
        
         
        httpPost.addHeader("content-type", "application/json");
        httpPost.addHeader("Accept", "application/json");
        //httpPost.setEntity(params);
        return httpPost;
    }

    public HttpPost getDefaultPostMethod(String url) {
        try {
            httpPost = new HttpPost(url);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return httpPost;
    }
    
    //httpPut function
    public HttpPut getDefaultPutMethodWithAuthorPutJson(String AuthorName, String AuthorPassword, String url, String postJSONString) {
        this.getDefaultPutMethod(url);
        String auth = new String(Base64.encodeBase64((AuthorName + ":" + AuthorPassword).getBytes()));
        httpPut.setHeader(HttpHander.TOKEN, HttpHander.CHECK);
        httpPut.setHeader(HttpHander.AUTHO, HttpHander.BASIC + auth);
        StringEntity params = null;
        try {
            params = new StringEntity(postJSONString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPut.addHeader("content-type", "application/json");
        httpPut.setEntity(params);
        return httpPut;
    }

    public HttpPut getDefaultPutMethod(String url) {
        try {
            httpPut = new HttpPut(url);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return httpPut;
    }
}
