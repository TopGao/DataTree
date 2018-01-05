package com.jira.r4j;


import static com.jira.tool.GeneralStaticFields.*;
import static com.jira.r4j.GetTreeInfo.R4J_API_1_0;
import static com.jira.r4j.GetTreeInfo.Tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.methods.GetMethod;

import com.jira.tool.HttpMethod;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
//VR Data tree jsonInfo length:3713433
public class MyTest {
	
	public static R4JFolder getR4JFolderFromJson(String jsonString){
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("folders",R4JFolder.class);		
		classMap.put("issues",R4JIssue.class);
		//classMap.put("issue",R4JIssue.class);
		//Map<String, Class<?>> classMap2 = new HashMap<String, Class<?>>();
		classMap.put("childReqs",R4JChildReq.class);
		JSONObject listObject=JSONObject.fromObject(jsonString);  
		System.out.println("java object to"+listObject.toString());
		R4JFolder projectFolder=(R4JFolder)JSONObject.toBean(listObject ,R4JFolder.class,classMap);
		//R4JIssue issue=(R4JIssue)JSONObject.toBean(listObject.getJSONObject(childReqs) ,R4JIssue.class,classMap2);
		if(projectFolder != null){	
			System.out.println("folders"+ projectFolder.toString());
			return projectFolder;
		}
		return null;
		
	}
	

	public static void main(String[] args) throws IOException {
		
		//String str="jinjin.gao.ext@nokia.com";
//		String str="Jinjin.gao.ext@nokia.com";
//		String str2="BU0005800";
//		if(str.indexOf("@")!=-1 && str.indexOf(".com")!=-1){
//		   System.out.println("包含");
//		  }else{
//		   System.out.println("不包含");
//		   
//		  }
//		
//		if (str2.charAt(0) >= 'A' && str.charAt(0) <= 'Z' && str2.length()==9){	  
//			  System.out.println("yes");
//		  }else{
//			  System.out.println("no");
//		  }
//		if(str2.length()==9 && str2.indexOf(" ") == -1 && str2.indexOf("-") ==-1 && str2.charAt(3) >= '0' && str2.charAt(3) <= '9')
//		{
//			System.out.println("Y");
//		}
//		else{
//			System.out.println("N");
//			   
//		}
		//String string= "https://greenhopper-qa.app.alcatel-lucent.com/rest/api/2/group/user?groupname=Advocate";
		
		//System.out.println(string.charAt(85));
		//String jsonStr= "{\"id\":-1,\"folders\":[{\"name\":\"basura\",\"description\":\"\",\"id\":4,\"name_display\":\"basura\",\"parent\":-1,\"folders\":[],\"issues\":[],\"position\":1},{\"name\":\"The Java Init Foler\",\"description\":\"the Java Init Foler Description\",\"id\":6,\"name_display\":\"The Java Init Foler\",\"parent\":-1,\"folders\":[],\"issues\":[],\"position\":2},{\"name\":\"The Java Init Foler 3\",\"description\":\"the Java Init Foler Description 3\",\"id\":15,\"name_display\":\"The Java Init Foler 3\",\"parent\":-1,\"folders\":[],\"issues\":[],\"position\":3}],\"issues\":[]}";
		//String jsonStr= "{\"name\":\"basura\",\"description\":\"\",\"id\":4,\"name_display\":\"basura\",\"parent\":-1,\"folders\":[],\"issues\":[],\"position\":1},{\"name\":\"The Java Init Foler\",\"description\":\"the Java Init Foler Description\",\"id\":6,\"name_display\":\"The Java Init Foler\",\"parent\":-1,\"folders\":[],\"issues\":[],\"position\":2},{\"name\":\"The Java Init Foler 3\",\"description\":\"the Java Init Foler Description 3\",\"id\":15,\"name_display\":\"The Java Init Foler 3\",\"parent\":-1,\"folders\":[],\"issues\":[],\"position\":3}";
		//System.out.println(jsonStr.length());
		
	/*	String projectKey="VR";
		System.out.println("projectKey:"+projectKey);
		String postUrl =CORP_JIRA_QA + R4J_API_1_0 +Tree+projectKey;
		int result=HttpMethod.httpGet2(CDCADMIN, CDCADMIN_PWD,postUrl);
		if(result == 1){
			GetMethod info=HttpMethod.getMethodUseProxyAndBasicAuthorByJson(CDCADMIN, CDCADMIN_PWD,postUrl);
			System.out.println(info.getStatusLine());  
		     //打印返回的信息  
			String jsonInfo=info.getResponseBodyAsString();
		    System.out.println("jsonInfo"+jsonInfo.length());
		}*/
		//String postUrl="https://greenhopper-qa.app.alcatel-lucent.com/rest/com.easesolutions.jira.plugins.requirements/1.0/tree/VR/folder/481?name=Integrate+Technicolor+STB+UIW4026TCH+into+Movistar++IPTV+service+for+DEMO&description=Initiative+to+integrate+a+Technicolor+STB+into+Movistar++IPTV+service,+provided+trough+MiViewTV+product,+by+Telefónica+de+España,+S.A.The+set-top+box+model+for+this+integration+is+Technicolor+UIW4026TCH+based+on+BCM72406.The+aim+is+porting+Nokia+MyView+software+stack+into+Technicolor+hardware+in+order+to+allow+Technicolor+will+be+able+to+demonstrate+this+at+Telefonica+and+for+this+reason,+Nokia+will+integrate+the+STB+as+easily+as+possible.+The+features+related+with+DRM+won´t+be+integrated.";
	//	String jsonStr = "{\"id\":-1,\"folders\":[{\"name\":\"TestFolder2\",\"description\":\"\",\"id\":34,\"name_display\":\"TestFolder\",\"parent\":-1,\"folders\":[{\"name\":\"Child test folder\",\"description\":\"\",\"id\":35,\"name_display\":\"Child test folder\",\"parent\":34,\"folders\":[],\"issues\":[{\"id\":104,\"issueId\":895000,\"key\":\"VR-1\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-1\",\"summary\":\"Test R4J 1\",\"description\":\"Test R4J\",\"rootfolderdescription\":\"\",\"description_html\":\"<p>Test R4J</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=19092&avatarType=issuetype\",\"issueType\":\"Assumption\",\"position\":1,\"childReqs\":{\"childReq\":[{\"issueId\":895002,\"key\":\"VR-3\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-3\",\"summary\":\"Test R4J 3\",\"description\":\"Test R4J 3\",\"description_html\":\"<p>Test R4J 3</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=19092&avatarType=issuetype\",\"issueType\":\"Assumption\",\"position\":1,\"childReqs\":{\"childReq\":[{\"issueId\":895007,\"key\":\"VR-6\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-6\",\"summary\":\"Test R4J 6\",\"description_html\":\"\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=14400&avatarType=issuetype\",\"issueType\":\"Improvement\",\"position\":1}]}},{\"issueId\":895005,\"key\":\"VR-4\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-4\",\"summary\":\"Test R4J 4\",\"description\":\"Test R4J 4\",\"description_html\":\"<p>Test R4J 4</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=17690&avatarType=issuetype\",\"issueType\":\"Customer Requirement\",\"position\":2},{\"issueId\":895006,\"key\":\"VR-5\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-5\",\"summary\":\"Test R4J 5\",\"description\":\"Test R4J 5\",\"description_html\":\"<p>Test R4J 5</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=14400&avatarType=issuetype\",\"issueType\":\"Improvement\",\"position\":3}]}},{\"id\":105,\"issueId\":895001,\"key\":\"VR-2\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-2\",\"summary\":\"Test R4J 2\",\"description\":\"Test R4J?\",\"rootfolderdescription\":\"\",\"description_html\":\"<p>Test R4J?</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=19092&avatarType=issuetype\",\"issueType\":\"Assumption\",\"position\":2,\"childReqs\":{\"childReq\":[]}}],\"position\":1},{\"name\":\"Child test folder 2\",\"description\":\"\",\"id\":36,\"name_display\":\"Child test folder 2\",\"parent\":34,\"folders\":[{\"name\":\"Third chid folder\",\"description\":\"\",\"id\":486,\"name_display\":\"Third chid folder\",\"parent\":36,\"folders\":[],\"issues\":[],\"position\":1},{\"name\":\"Third chid folder 2\",\"description\":\"\",\"id\":487,\"name_display\":\"Third chid folder 2\",\"parent\":36,\"folders\":[{\"name\":\"Fourth child folder\",\"description\":\"\",\"id\":488,\"name_display\":\"Fourth child folder\",\"parent\":487,\"folders\":[],\"issues\":[],\"position\":1},{\"name\":\"Fourth child folder 2\",\"description\":\"\",\"id\":489,\"name_display\":\"Fourth child folder 2\",\"parent\":487,\"folders\":[],\"issues\":[],\"position\":2},{\"name\":\"Fourth child folder 3\",\"description\":\"\",\"id\":490,\"name_display\":\"Fourth child folder 3\",\"parent\":487,\"folders\":[],\"issues\":[],\"position\":3},{\"name\":\"Fourth child folder 4\",\"description\":\"\",\"id\":491,\"name_display\":\"Fourth child folder 4\",\"parent\":487,\"folders\":[],\"issues\":[],\"position\":4}],\"issues\":[],\"position\":2}],\"issues\":[],\"position\":2}],\"issues\":[],\"position\":1}],\"issues\":[]}";
		//String jsonStr2 = "{\"id\":-1,\"folders\":[{\"name\":\"TestFolder\",\"description\":\"\",\"id\":34,\"name_display\":\"TestFolder\",\"parent\":-1,\"folders\":[{\"name\":\"Child test folder\",\"description\":\"\",\"id\":35,\"name_display\":\"Child test folder\",\"parent\":34,\"folders\":[],\"issues\":[{\"id\":104,\"issueId\":895000,\"key\":\"VR-1\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-1\",\"summary\":\"Test R4J 1\",\"description\":\"Test R4J\",\"rootfolderdescription\":\"\",\"description_html\":\"<p>Test R4J</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=19092&avatarType=issuetype\",\"issueType\":\"Assumption\",\"position\":1,\"issues\":{\"issue\":[{\"issueId\":895002,\"key\":\"VR-3\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-3\",\"summary\":\"Test R4J 3\",\"description\":\"Test R4J 3\",\"description_html\":\"<p>Test R4J 3</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=19092&avatarType=issuetype\",\"issueType\":\"Assumption\",\"position\":1,\"issues\":{\"issue\":[{\"issueId\":895007,\"key\":\"VR-6\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-6\",\"summary\":\"Test R4J 6\",\"description_html\":\"\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=14400&avatarType=issuetype\",\"issueType\":\"Improvement\",\"position\":1}]}},{\"issueId\":895005,\"key\":\"VR-4\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-4\",\"summary\":\"Test R4J 4\",\"description\":\"Test R4J 4\",\"description_html\":\"<p>Test R4J 4</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=17690&avatarType=issuetype\",\"issueType\":\"Customer Requirement\",\"position\":2},{\"issueId\":895006,\"key\":\"VR-5\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-5\",\"summary\":\"Test R4J 5\",\"description\":\"Test R4J 5\",\"description_html\":\"<p>Test R4J 5</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=14400&avatarType=issuetype\",\"issueType\":\"Improvement\",\"position\":3}]}},{\"id\":105,\"issueId\":895001,\"key\":\"VR-2\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-2\",\"summary\":\"Test R4J 2\",\"description\":\"Test R4J?\",\"rootfolderdescription\":\"\",\"description_html\":\"<p>Test R4J?</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=19092&avatarType=issuetype\",\"issueType\":\"Assumption\",\"position\":2,\"issues\":{\"issue\":[]}}],\"position\":1},{\"name\":\"Child test folder 2\",\"description\":\"\",\"id\":36,\"name_display\":\"Child test folder 2\",\"parent\":34,\"folders\":[{\"name\":\"Third chid folder\",\"description\":\"\",\"id\":486,\"name_display\":\"Third chid folder\",\"parent\":36,\"folders\":[],\"issues\":[],\"position\":1},{\"name\":\"Third chid folder 2\",\"description\":\"\",\"id\":487,\"name_display\":\"Third chid folder 2\",\"parent\":36,\"folders\":[{\"name\":\"Fourth child folder\",\"description\":\"\",\"id\":488,\"name_display\":\"Fourth child folder\",\"parent\":487,\"folders\":[],\"issues\":[],\"position\":1},{\"name\":\"Fourth child folder 2\",\"description\":\"\",\"id\":489,\"name_display\":\"Fourth child folder 2\",\"parent\":487,\"folders\":[],\"issues\":[],\"position\":2},{\"name\":\"Fourth child folder 3\",\"description\":\"\",\"id\":490,\"name_display\":\"Fourth child folder 3\",\"parent\":487,\"folders\":[],\"issues\":[],\"position\":3},{\"name\":\"Fourth child folder 4\",\"description\":\"\",\"id\":491,\"name_display\":\"Fourth child folder 4\",\"parent\":487,\"folders\":[],\"issues\":[],\"position\":4}],\"issues\":[],\"position\":2}],\"issues\":[],\"position\":2}],\"issues\":[],\"position\":1}],\"issues\":[]}";
		//R4JFolder parentFolder = getR4JFolderFromJson(jsonStr);
		
		
		//readLine()方法。
		//Desktop.getDesktop().open(new File("D:/my local/测试用例.xls"));
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:/userdata/jigao/Desktop/Error2.txt")));
		//String data = br.readLine();
		String data=null;
		String stringBuffer="";
		while((data = br.readLine())!=null)
		{
		System.out.println(data); 
		stringBuffer=stringBuffer+data+"\n";
		}
		System.out.println(stringBuffer); 
	}
}
