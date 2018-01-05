package com.jira.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jira.po.JiraUser;
import com.jira.until.CreateCorpJiraUsers;
import com.jira.until.LdapCheck;

import io.swagger.annotations.Api;

@Api("Demo测试")
@Controller
@RequestMapping("/cdc")
public class DemoController {
	@Autowired
	
	private Logger logger = Logger.getLogger(DemoController.class);

	@RequestMapping("/myTest")
	public String loginToIndexPage2(HttpServletRequest request) {
	 logger.info("myTest....");
	 request.getSession().invalidate();

		return "myTest";
	}
	
	@RequestMapping("/homePage")
	public String loginToIndexPage3(HttpServletRequest request) {
		logger.info("/homePage....");
		//request.getSession().setAttribute("account","cdcadmin");
		/*request.getSession().removeAttribute("totalNum");
		 request.getSession().removeAttribute("successNum");
		 request.getSession().removeAttribute("failedTotalNum");
		 request.getSession().removeAttribute("failedList");
		 request.getSession().removeAttribute("noLdapInfo");*/
		 request.getSession().invalidate();

		
		return "/homePage";
	}
	
	@RequestMapping("/homePage2")
	public String loginToIndexPage4(HttpServletRequest request) {
		logger.info("/homePage2....");
		
		return "/homePage2";
	}
	
	@RequestMapping("/checkInfo")
	public String checkInfo(HttpServletRequest request) {
		request.getSession().invalidate();
		logger.info("/Start Check....");	
		String textInfo =request.getParameter("addInfo");
		System.out.println(textInfo);
		if(textInfo !=null){
		String[] trimInfo = textInfo.split("\n");//用split()函数直接分割
        System.out.println(trimInfo.length);
        
        //添加的总数
 		 int totalNum=0;
 		 
        //成功的数目
  		 int successNum=0;
  		 
  		 //添加失败的信息，已经是Jira用户或者在Deactivated的组
  		 ArrayList<String> failedList= new ArrayList<String>();
  		 
  		 //在Ldap不存在的用户，请用户查看信息是否正确
  		 ArrayList<String> nonexistList= new ArrayList<String>();
  		//初始化ldap
    		LdapCheck.initLdap();
    	//整理输入的数据，计算有效数据	
  		for (String string : trimInfo) {
        	//System.out.print(string.trim()+",");
        	if(!string.trim().equals("")){       		
            System.out.print(string.trim());
            totalNum=totalNum+1;
        //先去Ldap查询是否有此人
   		JiraUser jiraUser= LdapCheck.GetADInfo("inetorgperson",string);
   		//如果在Ldap查有此人，进行添加用户
   		if(jiraUser != null){
   			String addResult=CreateCorpJiraUsers.addJiraUser(jiraUser);
   			System.out.println("");
   			if(addResult.equals("1")){
   				//如果返回"1",说明添加成功，成功数加1
   				successNum=successNum+1;
   				
   			}else{
   				//因为已经存在于jira系统中，失败数加1
   				failedList.add(addResult);
   				
   			}
   			
   		}else{
   			//在Ldap没有有此人，添加到不存在Ldap列表中
   			String noLdapInfo=string.trim () +" is not in Ldap, please check the information is correct!";
   			String lastNoLdapInfo=	noLdapInfo.replace("\n","");
   			nonexistList.add(lastNoLdapInfo);
   			
   			System.out.println(lastNoLdapInfo);
   		}
            }
		
        }
  	    //关闭ldap
   		LdapCheck.closeLdap();
		
   		//存放相关信息，便于前台使用
        request.getSession().setAttribute("totalNum","The total number of input data is "+ totalNum); 
        if(successNum >1){
    		request.getSession().setAttribute("successNum","Successfully created "+ successNum +" accounts to Jira system");
    	}else{
    		request.getSession().setAttribute("successNum","Successfully created "+ successNum +" account to Jira system");
    	}
		if(totalNum-successNum >1){
			request.getSession().setAttribute("failedTotalNum","Failed to create "+ (totalNum-successNum) +" accounts");
		}
		else if(totalNum-successNum >0){
			request.getSession().setAttribute("failedTotalNum","Failed to create "+ (totalNum-successNum) +" account");
		}
		
		/*if(!failedList.isEmpty() && failedList!=null){
			String failedListSize ="";
			request.getSession().setAttribute("failedListSize",failedListSize);
			
		}
		if(!nonexistList.isEmpty() && nonexistList!=null){
			String nonexistListSize ="";
			request.getSession().setAttribute("nonexistListSize",nonexistListSize);
			
		}*/
		
		request.getSession().setAttribute("failedList",failedList);
		
		request.getSession().setAttribute("noLdapInfo",nonexistList);
		}
		return "/homePage";
		
	}
	
	@RequestMapping("/addToGroupPage")
	public String addToGroup(HttpServletRequest request) {
		request.getSession().invalidate();
		logger.info("/addToGroupPage....");
		//request.getSession().setAttribute("account","cdcadmin");
		
		return "/addToGroup";
	}
	
	@RequestMapping("/addUserToGroup")
	public String addUserToGroup(HttpServletRequest request) throws UnsupportedEncodingException {
		 request.getSession().invalidate();
		logger.info("/Start Check groupInfo....");	
		String userInfo =request.getParameter("addUserInfo");
		String groupInfo =request.getParameter("addGroupInfo");
		System.out.println(userInfo);
		System.out.println(groupInfo);
		
		if(userInfo !=null && groupInfo !=null){
			//输入组的数据
			int totalGroupNum=0;
			//先处理组，筛选出在jira存在的组
			String[] trimInfo2 = groupInfo.split("\n");//用split()函数直接分割
			
			ArrayList<String> validGroupList= new ArrayList<String>();
			ArrayList<String> invalidGroupList= new ArrayList<String>();
			int totalInputGroupNum= trimInfo2.length;
			System.out.println("总长度："+totalInputGroupNum);
			for (String string : trimInfo2) {
	        	//System.out.print(string.trim()+",");
	        	if(!string.trim().replace("\n","").equals("")){
	        		int result=CreateCorpJiraUsers.CheckInputGroup(string);
	        		if(result==1){
	        			validGroupList.add(string);
	        			System.out.println("有效的组："+string);
		        	}else{
		        		invalidGroupList.add(string.trim () +" is not this group in JIRA system, please check the group information is correct!");
		        		System.out.println("无效的组："+string);
		        	}  			        			        	
	        	}else{
	        		totalInputGroupNum--;
	        	}
			}
			//System.out.println("欲添加有效的组："+validGroupList.size());
			logger.info("/Start Check userInfo....");
			//先处理人，筛选出在jira存在的人
			String[] trimInfo = userInfo.split("\n");//用split()函数直接分割
			ArrayList<String> validUserList= new ArrayList<String>();
			ArrayList<String> noExistJiraUserList= new ArrayList<String>();
			ArrayList<String> invalidUserList= new ArrayList<String>();
			int totalInputUserNum= trimInfo.length;
			LdapCheck.initLdap();
			for (String string : trimInfo) {
	        	//System.out.print(string.trim()+",");
	        	if(!string.trim().equals("")){
	        		JiraUser jiraUser=LdapCheck.GetADInfo("inetorgperson",string);
	        		if(jiraUser!=null){
	        			int result=CreateCorpJiraUsers.CheckInputUser(jiraUser.getCsl());
	        			if(result==1){
	        				validUserList.add(jiraUser.getCsl());
	        				System.out.println("存在于jira的用户："+string);
			        	}else{
			        		noExistJiraUserList.add(string.trim () +" is not in JIRA system, please create the user first!");
			        		System.out.println("只在LDAP,还未在Jira的用户："+string);
			        	}  			 
	        			
		        	}else{
		        		invalidUserList.add(string.trim () +" is not in Ldap, please check the user information is correct!");
		        		System.out.println("无效的用户："+string);
		        	}  			        			        	
	        	}else{
	        		totalInputUserNum--;
	        	}
			}
			
			//System.out.println("欲添加有效的人："+validGroupList.size());
			LdapCheck.closeLdap();
			//开始添加组
			ArrayList<String> failedAddList= new ArrayList<String>();
			//System.out.println("看看有几个组在里面："+validGroupList.size());
			if(validUserList !=null && validGroupList !=null){
				for (String validUser : validUserList){
					String addGroupResult = CreateCorpJiraUsers.addJiraGroup(validUser, validGroupList);
					if(!addGroupResult.equalsIgnoreCase("")){
						failedAddList.add(validUser+" already in these groups:"+addGroupResult+"no need to add!");
					}					
				}
				
			}

			//存放相关信息，便于前台使用
	        request.getSession().setAttribute("totalInputUserNum","The total number of input User data is "+ totalInputUserNum);
	        request.getSession().setAttribute("totalInputGroupNum","The total number of input Group data is "+ totalInputGroupNum);
            if(validUserList != null && !validUserList.isEmpty()){
            	request.getSession().setAttribute("validUserNum","The total number of valid User is "+ validUserList.size());
			}else{
				request.getSession().setAttribute("validUserNum","The total number of valid User is 0,so All failed!");
			}
	        if(validGroupList != null && !validGroupList.isEmpty()){
	        	//System.out.println("validGroupList.size()="+validGroupList.size());
	        	request.getSession().setAttribute("validGroupNum","The total number of valid group is "+ validGroupList.size());
			}else{
				request.getSession().setAttribute("validGroupNum","The total number of valid group is 0，so All failed!");
			}
	        if(validUserList != null && !validUserList.isEmpty() && validGroupList != null && !validGroupList.isEmpty()){
	        	request.getSession().setAttribute("TotalSuccessNum","Total successed num is "+validUserList.size());
	        }else{
	        	request.getSession().setAttribute("TotalSuccessNum","Total successed num is 0!");
	        }
	        request.getSession().setAttribute("invalidGroupList",invalidGroupList);
	        request.getSession().setAttribute("failedAddList",failedAddList);
	        request.getSession().setAttribute("noExistJiraUserList",noExistJiraUserList);
	        request.getSession().setAttribute("invalidUserList",invalidUserList);
	      
	    
		}
		return "/addToGroup";
		
	}
	
	@RequestMapping("/checkInfo2")
    @ResponseBody
    public String addJiraResult(HttpServletRequest request,HttpServletResponse response) {
		logger.info("/Start Check....");
		 response.setHeader("Cache-Control", "no-cache");
	     response.setHeader("Connection", "keep-alive");
	     logger.info("/Start Check....");	
			String textInfo =request.getParameter("addInfo");
			System.out.println(textInfo);
			String[] trimInfo = textInfo.split("\n");//用split()函数直接分割
	        System.out.println(trimInfo.length);
	        
	        //添加的总数
	 		 int totalNum=0;
	 		 
	        //成功的数目
	  		 int successNum=0;
	  		 
	  		 //添加失败的信息，已经是Jira用户或者在Deactivated的组
	  		 ArrayList<String> failedList= new ArrayList<String>();
	  		 
	  		 //在Ldap不存在的用户，请用户查看信息是否正确
	  		 ArrayList<String> nonexistList= new ArrayList<String>();
	  		//初始化ldap
	    		LdapCheck.initLdap();
	    	//整理输入的数据，计算有效数据	
	  		for (String string : trimInfo) {
	        	//System.out.print(string.trim()+",");
	        	if(!string.trim().equals("")){       		
	            System.out.print(string.trim());
	            totalNum=totalNum+1;
	        //先去Ldap查询是否有此人
	   		JiraUser jiraUser= LdapCheck.GetADInfo("inetorgperson",string);
	   		//如果在Ldap查有此人，进行添加用户
	   		if(jiraUser != null){
	   			String addResult=CreateCorpJiraUsers.addJiraUser(jiraUser);
	   			System.out.println("");
	   			if(addResult.equals("1")){
	   				//如果返回"1",说明添加成功，成功数加1
	   				successNum=successNum+1;
	   				
	   			}else{
	   				//因为已经存在于jira系统中，失败数加1
	   				failedList.add(addResult);
	   				
	   			}
	   			
	   		}else{
	   			//在Ldap没有有此人，添加到不存在Ldap列表中
	   			String noLdapInfo= string.trim () +"is not in Ldap, please check the information is correct!";
	   			String lastNoLdapInfo=	noLdapInfo.replace("\n","");
	   			nonexistList.add(lastNoLdapInfo);
	   			
	   			System.out.println(lastNoLdapInfo);
	   		}
	            }
			
	        }
	  	    //关闭ldap
	   		LdapCheck.closeLdap();
			
	   		//存放相关信息，便于前台使用
	        request.getSession().setAttribute("totalNum","The total number of input data is "+ totalNum); 
	        if(successNum >1){
	    		request.getSession().setAttribute("successNum","Successfully created "+ successNum +" accounts to Jira system");
	    	}else{
	    		request.getSession().setAttribute("successNum","Successfully created "+ successNum +" account to Jira system");
	    	}
			if(totalNum-successNum >1){
				request.getSession().setAttribute("failedTotalNum","Failed to create "+ (totalNum-successNum) +" accounts");
			}
			else if(totalNum-successNum >0){
				request.getSession().setAttribute("failedTotalNum","Failed to create "+ (totalNum-successNum) +" account");
			}
			
			if(!failedList.isEmpty() && failedList!=null){
				String failedListSize ="";
				request.getSession().setAttribute("failedListSize",failedListSize);
				
			}
			if(!nonexistList.isEmpty() && nonexistList!=null){
				String nonexistListSize ="";
				request.getSession().setAttribute("nonexistListSize",nonexistListSize);
				
			}
			
			request.getSession().setAttribute("failedList",failedList);
			
			request.getSession().setAttribute("noLdapInfo",nonexistList);
			
			return textInfo;
			
    
    }

	
}
