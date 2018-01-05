package com.jira.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;

import com.jira.tool.BaseHttpDomain;




public class HttpMethod {

	//httpGet
	public static GetMethod httpGet(String userName, String passWord, String restUrl) {
	      	GetMethod response = getMethodUseProxyAndBasicAuthorByJson(userName, passWord, restUrl);
				return response;
	}
	  
	public static int httpGet2(String userName, String passWord, String restUrl) {
      	GetMethod response = getMethodUseProxyAndBasicAuthorByJson(userName, passWord, restUrl);
      	if (response != null) {
	          if (response.getStatusLine().getStatusCode() >= 200 
	        		  && response.getStatusLine().getStatusCode() <= 300) {
	              return 1;
	          } else return 0;
	      } else return 0;
	}
	public static GetMethod getMethodUseProxyAndBasicAuthorByJson(String AuthorName, String AuthorPassword, String url) {
	  	  BaseHttpDomain baseHttpDomain=  new BaseHttpDomain();
	  	  GetMethod method = baseHttpDomain.getGetMethodByJson(url);
	      try {
	            baseHttpDomain.getClientUseProxyAndBasicAuthor(AuthorName,
	          		  AuthorPassword).executeMethod(method);
	      } catch (IOException e) {
	            e.printStackTrace();
	      }
	      return method;
	}
	//httpGet userInfo
		public static GetMethod getUserInfo(String userName, String passWord, String restUrl) {
		      	GetMethod response = getMethodUseProxyAndBasicAuthorByJson(userName, passWord, restUrl);
		      	
					return response;
		}
		  
		public static GetMethod getMethodUseProxyAndBasicAuthorByJson2(String AuthorName, String AuthorPassword, String url) {
		  	  BaseHttpDomain baseHttpDomain=  new BaseHttpDomain();
		  	  GetMethod method = baseHttpDomain.getGetMethodByJson(url);
		      try {
		            baseHttpDomain.getClientUseProxyAndBasicAuthor(AuthorName,
		          		  AuthorPassword).executeMethod(method);
		      } catch (IOException e) {
		            e.printStackTrace();
		      }
		      return method;
		}
	
	//httpPost
	//页面请求的状态值，分别有：200请求成功、303重定向、400请求错误、401未授权、403禁止访问、404文件未找到、500服务器错误
	public static int httpPost(String userName, String passWord, String restUrl,String postJSONString) {
	      HttpResponse response = getPostMethodWithAuthorPostJson(userName, passWord, restUrl,postJSONString);
	      System.out.println("111111111111111111111111111111111");
	      if (response != null) {
	          if (response.getStatusLine().getStatusCode() >= 200 
	        		  && response.getStatusLine().getStatusCode() <= 300) {
	        	  System.out.println("222222222222222222222222222222222");
	        	  System.out.println(response.getStatusLine().getStatusCode());
	        	  return 1;
	          } else{
	        	  System.out.println("3333333333333333333333333333333333333");
	        	  System.out.println(response.getStatusLine().getStatusCode());
	        	  return 0;
	          } 
	      } else{
	    	  System.out.println("null444444444444444444444444444444");
	      return 0;}
	}

	public static HttpResponse getPostMethodWithAuthorPostJson(String AuthorName, String AuthorPassword, String url, String postJSONString) {
		  BaseHttpDomain baseHttpDomain =  new BaseHttpDomain();
	      HttpPost httpPost = baseHttpDomain.getDefaultPostMethodWithAuthorPostJson(AuthorName, AuthorPassword, url, postJSONString);
	      HttpResponse httpResponse = null;
	      try {
	          httpResponse = baseHttpDomain.getDefaultHttpClient().execute(httpPost);
	      }  catch (IOException e) {
	          e.printStackTrace();
	      }
	      return httpResponse;
	}
	
	
	//httpPost without AuthorPostJson,without response body
		//页面请求的状态值，分别有：200请求成功、303重定向、400请求错误、401未授权、403禁止访问、404文件未找到、500服务器错误
		public static int httpPost(String userName, String passWord, String restUrl) {
		      HttpResponse response = getPostMethodWithAuthorPostJson(userName, passWord, restUrl);
		      System.out.println("111111111111111111111111111111111");
		      if (response != null) {
		          if (response.getStatusLine().getStatusCode() >= 200 
		        		  && response.getStatusLine().getStatusCode() <= 300) {
		        	  System.out.println("222222222222222222222222222222222");
		        	  System.out.println(response.getStatusLine().getStatusCode());
		              return 1;
		          } else{
		        	  System.out.println("3333333333333333333333333333333333333");
		        	  System.out.println(response.getStatusLine().getStatusCode());
		        	  return 0;
		          } 
		      } else{
		    	  System.out.println("null444444444444444444444444444444");
		      return 0;
		      }
		}
		
		//httpPost without AuthorPostJson,has response body
				//页面请求的状态值，分别有：200请求成功、303重定向、400请求错误、401未授权、403禁止访问、404文件未找到、500服务器错误
		public static String httpPostWithresponseBody(String userName, String passWord, String restUrl) throws IOException {
				      HttpResponse response = getPostMethodWithAuthorPostJson(userName, passWord, restUrl);
				      System.out.println("111111111111111111111111111111111");
				      if (response != null) {
				          if (response.getStatusLine().getStatusCode() >= 200 
				        		  && response.getStatusLine().getStatusCode() <= 300) {
				        	  System.out.println("222222222222222222222222222222222");
				        	  System.out.println(response.getStatusLine().getStatusCode());
				        	  String result = "";
				        	  BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				              String line;
				              while ((line = in.readLine()) != null) {
				                  result += line;
				            }
				              
				              System.out.println("result:"+result);   
		 	  
				              return result;
				          } else{
				        	  System.out.println("3333333333333333333333333333333333333");
				        	  System.out.println(response.getStatusLine().getStatusCode());
				        	  return null;
				          } 
				      } else{
				    	  System.out.println("null444444444444444444444444444444");
				      return null;
				      }
				}


		public static HttpResponse getPostMethodWithAuthorPostJson(String AuthorName, String AuthorPassword, String url) {
			  BaseHttpDomain baseHttpDomain =  new BaseHttpDomain();
		      HttpPost httpPost = baseHttpDomain.getDefaultPostMethodWithAuthorPostJson(AuthorName, AuthorPassword, url);
		      HttpResponse httpResponse = null;
		      try {
		          httpResponse = baseHttpDomain.getDefaultHttpClient().execute(httpPost);
		          
		      }  catch (IOException e) {
		          e.printStackTrace();
		      }
		      return httpResponse;
		}
	
	
	 //httpDelete
	 public static int httpDelete(String userName, String passWord, String restUrl) {
			HttpResponse response = getDeleteMethod(userName, passWord, restUrl);
	        if (response != null) {
	            if (response.getStatusLine().getStatusCode() >= 200 && response.getStatusLine().getStatusCode() <= 300) {
	                return 1;
	            } else return 0;
	        } else return 0;
	 }

	 public static HttpResponse getDeleteMethod(String AuthorName, String AuthorPassword, String url) {
	    	BaseHttpDomain baseHttpDomain=  new BaseHttpDomain();
	        HttpDelete httpDelete=null;
	        HttpResponse httpResponse=null;
	        try {
	            httpDelete = baseHttpDomain.getHttpDelete(url, AuthorName, AuthorPassword);
	            httpResponse= baseHttpDomain.getDefaultHttpClient().execute(httpDelete);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return httpResponse;
	 }
}
