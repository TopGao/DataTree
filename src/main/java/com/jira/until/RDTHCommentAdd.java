package com.jira.until;


import com.jira.po.JiraUser;
import com.jira.tool.HttpMethod;

import static com.jira.tool.GeneralStaticFields.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.httpclient.methods.GetMethod;



public class RDTHCommentAdd {
	
	public static final String Api = "/rest/api/2/user";
	public static final String GROUP_USER = "/rest/api/2/group/user?groupname=";
	public static final String Jira_USER = "/rest/api/2/user?username=";
	public static final String Jira_GROUP = "/rest/api/2/group?groupname=";
	public static final String CROWD_API = "/rest/usermanagement/latest";
	public static final String DELETE_USER = "&username=";
	public static final String Issue = "/rest/api/2/issue/";
	public static final String Comment ="/comment?";
	
	
public static void main(String[] args) throws UnsupportedEncodingException {
//	 String name="abidemib";
//     String emailAddress="Abidemi BELLO";
//     String displayName="abidemi.bello@nokia.com";
//     HttpMethod.httpPost(CDCADMIN, CDCADMIN_PWD, CORP_JIRA_PROD+Api,sb.toString());
//     
	/*JiraUser jiraUser=new JiraUser();
	jiraUser.setCsl("abidemib");
	jiraUser.setEmail("abidemi.bello@nokia.com");
	jiraUser.setFullName("Abidemi BELLO");*/
	System.out.println("start........");
	ArrayList<String> groupList=new ArrayList<String>();
	groupList.add("jira-developers");
	groupList.add("jira-users");
	//groupList.add("jira-administrators");
	groupList.add("Advocate");
	groupList.add("S3G");
	ArrayList<String> failedAddList= new ArrayList<String>();
	ArrayList<String> validUserList=new ArrayList<String>();
	validUserList.add("jigao");
	validUserList.add("ergao");
	System.out.println("看看有几个组在里面："+groupList.size());
	if(validUserList !=null && groupList !=null){
		for (String validUser : validUserList){
			String addGroupResult = RDTHCommentAdd.addJiraGroup(validUser, groupList);
			if(!addGroupResult.equalsIgnoreCase("")){
				failedAddList.add(validUser+" already in these groups:"+addGroupResult+"no need to add again!");
				System.out.println(validUser+" already in these groups:"+addGroupResult+"no need to add again!");
			}else{
			System.out.println("All already success");
			}
		}
		
	}
	
}

//查看组在jira中是否存在
public static int CheckInputGroup(String oneGroupInfo){
		
		int result=HttpMethod.httpGet2(CDCADMIN, CDCADMIN_PWD, CORP_JIRA_PROD+Jira_GROUP+oneGroupInfo);
		/*int result=HttpMethod.httpGet2(CDCADMIN, CDCADMIN_PWD, CORP_JIRA_PROD+Jira_GROUP+oneGroupInfo);
		{issueIdOrKey}/comment?expand*/
		
		return result;
	}

//查看组在jira中是否存在
public static int CheckInputUser(String oneUserInfo){
			
		int result=HttpMethod.httpGet2(CDCADMIN, CDCADMIN_PWD, CORP_JIRA_PROD+Jira_USER+oneUserInfo);
			
		return result;
	}
	
public static String addJiraGroup(String validUser,ArrayList<String> groupList) throws UnsupportedEncodingException {
	    //add user to certain group if necessary
	    //HashSet<String> Jira_Users = new HashSet<String>();
	              String failGroup="";
				  StringBuffer sb = new StringBuffer();
				  sb.append("{\"name\":\"").append(validUser).append("\"}");
				  for(String group :groupList){
					  System.out.println("group:"+group);
					  group = URLEncoder.encode(group, "UTF-8").replace("%0D","");
					  System.out.println("group:"+group);
				  int a = HttpMethod.httpPost(CDCADMIN,CDCADMIN_PWD,CORP_JIRA_PROD + GROUP_USER + group, sb.toString());
						  //(CORP_JIRA_QA + GROUP_USER + "RDC_Users" , sb.toString());
				  if(a==1){
					  System.out.println("add "+validUser+" to"+ group);					  
				  }
				  else{
					  failGroup=failGroup+group+",";
				  }
			}	
	return failGroup;
}

public static String addJiraUser(JiraUser jiraUser) {
		String info=null;
		HashSet<String> Jira_Users = new HashSet<String>();
			//generate post Json body
		
		       /* String name="abidemib";
		        String emailAddress="abidemi.bello@nokia.com";
		        String displayName="Abidemi BELLO";*/
				String name=jiraUser.getCsl();
				String emailAddress=jiraUser.getEmail();
				String displayName=jiraUser.getFullName();
				String inputInfo=jiraUser.getInputInfo();
				
				StringBuffer sb = new StringBuffer();
				sb.append("{\"name\":\"").append(name).append("\",")
				  .append("\"password\":\"").append(name).append("\",")
				  .append("\"emailAddress\":\"").append(emailAddress).append("\",")
				  .append("\"displayName\":\"").append(displayName).append("\",")
				  .append("\"applicationKeys\":[\"jira-core\"]")
				  .append("}");
				System.out.println(sb);
				
				int a = HttpMethod.httpPost(CDCADMIN, CDCADMIN_PWD, CORP_JIRA_PROD+Api,sb.toString());
				
				if (a == 0) {
					System.out.println("add "+inputInfo+" failed");
					//info="添加 "+name+"/"+displayName+"/"+emailAddress+"失败，已经存在于jira系统中！";
					info="Add "+ inputInfo.trim() +" failed, already exists in the jira system!";
					return info;	
				}
		
				//System.out.println("Group jira-users contains "+Jira_Users.size()+" users");
				/*
				    Avnet Factory
				    Eu-Factory
				    Flextronics Factory
				    fnms-tpc-users
				    LSD-GC
				    LSD-Lab
				    LSD-Mgmt
				    LSD-NIIT
				    LSD-UPK
				    Sanmina Tatabanya Factory
				    TPC_AccentureVMS
				    TPC_ALTEN
				    TPC_TATA_MYPLE
				 */
					//If httpPost success, add the user to group(for further use)
					System.out.println("add "+inputInfo+" success");
					Jira_Users.add(name);
					//delete user from group(according to global permission)
					String[] groups = {"Avnet%20Factory","Eu-Factory","Flextronics%20Factory","fnms-tpc-users","LSD-GC",
							           "LSD-Lab","LSD-Mgmt","LSD-NIIT","LSD-UPK","Sanmina%20Tatabanya%20Factory",
							           "TPC_AccentureVMS","TPC_ALTEN","TPC_TATA_MYPLE"};
					for (int m = 0 ; m < groups.length ; m++) {
						  String group = groups[m];
							 for(String user : Jira_Users){
								 String curUser = user.replace(" ", "%20");
								 int b = HttpMethod.httpDelete(CDCADMIN, CDCADMIN_PWD,CORP_JIRA_PROD + GROUP_USER + group + DELETE_USER + curUser); 
								 if (b == 0) {
									 System.out.println("delete "+user+" from "+group+" failed" );
								 } 
							 }
					}//end "for"
					return "1";
	
		//add user to certain group if necessary
		/*for(String u : Jira_Users){
			  StringBuffer sb = new StringBuffer();
			  sb.append("{\"name\":\"").append(u).append("\"}");
			  int a = httpPost(CorpJiraProd + GROUP_USER + "RDC_Users" , sb.toString());
			  System.out.println("add "+u+" to group RDC_Users "+a);
		}*/
		
	}
}

