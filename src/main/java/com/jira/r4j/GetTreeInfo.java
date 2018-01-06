package com.jira.r4j;



import com.jira.tool.HttpMethod;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import static com.jira.tool.GeneralStaticFields.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.methods.GetMethod;






public class GetTreeInfo {
	
	public static final String CORP_JIRA_QA = "https://greenhopper-qa.app.alcatel-lucent.com";
	public static final String CORP_JIRA_DEV = "https://greenhopper-dev.app.alcatel-lucent.com";
	public static final String CORP_JIRA_DEV_8453 = "https://greenhopper-dev.app.alcatel-lucent.com:8453";
	
	public static final String Api = "/rest/api/2/user";
	
	public static final String R4J_API_1_0 = "/rest/com.easesolutions.jira.plugins.requirements/1.0";	
	public static final String Tree="/tree/";
	public static final String ChildLink ="/child-req/";
	//中间需要加project的key
	public static final String Folder="/folder";
	public static final String CreatedFolderName="?name=";
	public static final String FolderDescription ="&description=";
	public static final String AddRootIssue ="/rootissue";
	public static final String AddFolderIssue ="/folderissue";
	public static final String IssueKey ="?issuekey=";
	public static final String IssueChildKey="?childKey=";
	private static List<Integer> HistoryIdList = new ArrayList<Integer>();;
	private static int NewFolderId = 0;
	//用于 baseline 相关操作
	public static final String Baselines="/baselines";
	public static final String Project="/project/";
	

public static void main(String[] args) throws IOException {
	
	String projectKey="VR";
	String folderName= "The Java Init Foler7";
	String folderDescription="The Java Init Foler7\nTop test";
	//1.Test add a root folder in project folder,success
	//GetTreeInfo.addRootFolder(projectKey, folderName, folderDescription);
	//System.out.println("response result:"+result);
	
	//2.Test add a sub folder in Specified folder,success
	/*String folderName2= "Peticiones";
	int id = 42;
	GetTreeInfo.addSubFolder(projectKey, id, folderName2, folderDescription);*/
	
	//3.Test add a issue in project folder,success
	  /*String issueKey="VR-1";
	  GetTreeInfo.addRootIssue(projectKey, issueKey);*/ 
	
	//4.Test add a issue in Specified folder,success
	 /* int id = 14;
	  String issueKey="VR-4";
	  GetTreeInfo.addFolderIssue(projectKey, id, issueKey);*/
	
	//5.Test add a child issue in Specified parent issue,success
	  //String parentIssueKey="VR-4";
	  //String ChildIssueKey="VR-6";
	  //GetTreeInfo.addChildIssue(projectKey, parentIssueKey, ChildIssueKey);
	
	
	//6.Test get the tree data	 
	 //R4JFolder parentFolder= GetTreeInfo.getTreeData(projectKey);
	  //if( jsonStrResult !=null){
		  //jsonStrResult="["+jsonStrResult+"]";
	  
	   //Map classMap = new HashMap();
	   //classMap.put("issues",R4JIssue.class);
	  // List<R4JFolder> folders = JSONArray.toList(JSONArray.fromObject(result), R4JFolder.class);
	 //String jsonStr= "{\"id\":-1,\"folders\":[{\"name\":\"basura\",\"description\":\"\",\"id\":4,\"name_display\":\"basura\",\"parent\":-1,\"folders\":[],\"issues\":[],\"position\":1},{\"name\":\"The Java Init Foler\",\"description\":\"the Java Init Foler Description\",\"id\":6,\"name_display\":\"The Java Init Foler\",\"parent\":-1,\"folders\":[],\"issues\":[],\"position\":2},{\"name\":\"The Java Init Foler 3\",\"description\":\"the Java Init Foler Description 3\",\"id\":15,\"name_display\":\"The Java Init Foler 3\",\"parent\":-1,\"folders\":[],\"issues\":[],\"position\":3}],\"issues\":[]}";
	 //String jsonStr2= "[{\"name\":\"basura\",\"description\":\"\",\"id\":4,\"name_display\":\"basura\",\"parent\":-1,\"folders\":[],\"issues\":[],\"position\":1},{\"name\":\"The Java Init Foler\",\"description\":\"the Java Init Foler Description\",\"id\":6,\"name_display\":\"The Java Init Foler\",\"parent\":-1,\"folders\":[],\"issues\":[],\"position\":2},{\"name\":\"The Java Init Foler 3\",\"description\":\"the Java Init Foler Description 3\",\"id\":15,\"name_display\":\"The Java Init Foler 3\",\"parent\":-1,\"folders\":[],\"issues\":[],\"position\":3}]";
	 //方法一:
		 /* JSONObject listObject=JSONObject.fromObject(jsonStrResult);  
		  //List<R4JFolder> newsList =new ArrayList<R4JFolder>();  
		  R4JFolder projectFolder=(R4JFolder)JSONObject.toBean(listObject ,R4JFolder.class);
		  System.out.println("folders"+ projectFolder.toString());*/

	//方法二:
	//将JSON字符串转换成JSONArray 
	//打印返回的信息  
    //System.out.println("folders"+newsList.toString()); */
	//7.Auto-create folders
	//String jsonStr = "{\"id\":-1,\"folders\":[{\"name\":\"basura\",\"description\":\"\",\"id\":4,\"name_display\":\"basura\",\"parent\":-1,\"folders\":[{\"name\":\"Peticiones\",\"description\":\"\",\"id\":17,\"name_display\":\"Peticiones\",\"parent\":4,\"folders\":[],\"issues\":[],\"position\":1}],\"issues\":[],\"position\":1},{\"name\":\"The Java Init Foler\",\"description\":\"the Java Init Foler Description\",\"id\":6,\"name_display\":\"The Java Init Foler\",\"parent\":-1,\"folders\":[{\"name\":\"Child folder\",\"description\":\"\",\"id\":18,\"name_display\":\"Child folder\",\"parent\":6,\"folders\":[],\"issues\":[{\"id\":16,\"issueId\":895000,\"key\":\"VR-1\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-1\",\"summary\":\"Test R4J 1\",\"description\":\"Test R4J\",\"rootfolderdescription\":\"\",\"description_html\":\"<p>Test R4J</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=19092&avatarType=issuetype\",\"issueType\":\"Assumption\",\"position\":1,\"childReqs\":{\"childReq\":[{\"issueId\":895002,\"key\":\"VR-3\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-3\",\"summary\":\"Test R4J 3\",\"description\":\"Test R4J 3\",\"description_html\":\"<p>Test R4J 3</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=19092&avatarType=issuetype\",\"issueType\":\"Assumption\",\"position\":1},{\"issueId\":895005,\"key\":\"VR-4\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-4\",\"summary\":\"Test R4J 4\",\"description\":\"Test R4J 4\",\"description_html\":\"<p>Test R4J 4</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=17690&avatarType=issuetype\",\"issueType\":\"Customer Requirement\",\"position\":2}]}},{\"id\":17,\"issueId\":895001,\"key\":\"VR-2\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-2\",\"summary\":\"Test R4J 2\",\"description\":\"Test R4J \",\"rootfolderdescription\":\"\",\"description_html\":\"<p>Test R4J </p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=19092&avatarType=issuetype\",\"issueType\":\"Assumption\",\"position\":2,\"childReqs\":{\"childReq\":[]}}],\"position\":1}],\"issues\":[{\"id\":18,\"issueId\":895006,\"key\":\"VR-5\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-5\",\"summary\":\"Test R4J 5\",\"description\":\"Test R4J 5\",\"rootfolderdescription\":\"\",\"description_html\":\"<p>Test R4J 5</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=14400&avatarType=issuetype\",\"issueType\":\"Improvement\",\"position\":1,\"childReqs\":{\"childReq\":[]}}],\"position\":2},{\"name\":\"The Java Init Foler 2\",\"description\":\"the Java Init Foler Description 3\",\"id\":15,\"name_display\":\"The Java Init Foler 2\",\"parent\":-1,\"folders\":[],\"issues\":[],\"position\":3}],\"issues\":[]}";
	String jsonStr="{\"id\":-1,\"folders\":[{\"name\":\"TestFolder\",\"description\":\"\",\"id\":34,\"name_display\":\"TestFolder\",\"parent\":-1,\"folders\":[{\"name\":\"Child test folder\",\"description\":\"\",\"id\":35,\"name_display\":\"Child test folder\",\"parent\":34,\"folders\":[],\"issues\":[{\"id\":104,\"issueId\":895000,\"key\":\"VR-1\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-1\",\"summary\":\"Test R4J 1\",\"description\":\"Test R4J\",\"rootfolderdescription\":\"\",\"description_html\":\"<p>Test R4J</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=19092&avatarType=issuetype\",\"issueType\":\"Assumption\",\"position\":1,\"childReqs\":{\"childReq\":[{\"issueId\":895002,\"key\":\"VR-3\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-3\",\"summary\":\"Test R4J 3\",\"description\":\"Test R4J 3\",\"description_html\":\"<p>Test R4J 3</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=19092&avatarType=issuetype\",\"issueType\":\"Assumption\",\"position\":1,\"childReqs\":{\"childReq\":[{\"issueId\":895007,\"key\":\"VR-6\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-6\",\"summary\":\"Test R4J 6\",\"description_html\":\"\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=14400&avatarType=issuetype\",\"issueType\":\"Improvement\",\"position\":1}]}},{\"issueId\":895005,\"key\":\"VR-4\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-4\",\"summary\":\"Test R4J 4\",\"description\":\"Test R4J 4\",\"description_html\":\"<p>Test R4J 4</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=17690&avatarType=issuetype\",\"issueType\":\"Customer Requirement\",\"position\":2},{\"issueId\":895006,\"key\":\"VR-5\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-5\",\"summary\":\"Test R4J 5\",\"description\":\"Test R4J 5\",\"description_html\":\"<p>Test R4J 5</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=14400&avatarType=issuetype\",\"issueType\":\"Improvement\",\"position\":3}]}},{\"id\":105,\"issueId\":895001,\"key\":\"VR-2\",\"url\":\"https://greenhopper-qa.app.alcatel-lucent.com/browse/VR-2\",\"summary\":\"Test R4J 2\",\"description\":\"Test R4J?\",\"rootfolderdescription\":\"\",\"description_html\":\"<p>Test R4J?</p>\",\"icon_url\":\"https://greenhopper-qa.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=19092&avatarType=issuetype\",\"issueType\":\"Assumption\",\"position\":2,\"childReqs\":{\"childReq\":[]}}],\"position\":1},{\"name\":\"Child test folder 2\",\"description\":\"\",\"id\":36,\"name_display\":\"Child test folder 2\",\"parent\":34,\"folders\":[{\"name\":\"Third chid folder\",\"description\":\"\",\"id\":486,\"name_display\":\"Third chid folder\",\"parent\":36,\"folders\":[],\"issues\":[],\"position\":1},{\"name\":\"Third chid folder 2\",\"description\":\"\",\"id\":487,\"name_display\":\"Third chid folder 2\",\"parent\":36,\"folders\":[{\"name\":\"Fourth child folder\",\"description\":\"\",\"id\":488,\"name_display\":\"Fourth child folder\",\"parent\":487,\"folders\":[],\"issues\":[],\"position\":1},{\"name\":\"Fourth child folder 2\",\"description\":\"\",\"id\":489,\"name_display\":\"Fourth child folder 2\",\"parent\":487,\"folders\":[],\"issues\":[],\"position\":2},{\"name\":\"Fourth child folder 3\",\"description\":\"\",\"id\":490,\"name_display\":\"Fourth child folder 3\",\"parent\":487,\"folders\":[],\"issues\":[],\"position\":3},{\"name\":\"Fourth child folder 4\",\"description\":\"\",\"id\":491,\"name_display\":\"Fourth child folder 4\",\"parent\":487,\"folders\":[],\"issues\":[],\"position\":4}],\"issues\":[],\"position\":2}],\"issues\":[],\"position\":2}],\"issues\":[],\"position\":1}],\"issues\":[]}";
	//方法一：
	//R4JFolder parentFolder = getR4JFolderFromJson(jsonStr);
	 //R4JFolder parentFolder= GetTreeInfo.getTreeData(projectKey);
	 //dealData(projectKey,parentFolder,NewFolderId);
	
	//方法二：
	//String jsonStr = "{\"name\":\"offer_title\",\"description\":\"#OFFERT_DATE DD/MM/AAAA  // also can be current date \n#OFFERT_VERSION 1        // to define offert version format\n#OFFERT_ID  1            // to define offert id forma. Also could be Hierarchy number of the folder.\n#OFFERT_STATE // [OFFERT GENERATION (BORRADOR), OFFERT_PENDING_APPROVAL (PENDIENTE APROBACIóN CLIENTE), OFFER APPROVED (APROBADA POR EL CLIENTE)\n#OFFERT_DESC // not used at the moment in offert template\n\",\"id\":403,\"name_display\":\"offer_title\",\"parent\":402,\"folders\":[{\"name\":\"nombre_amigable_entrega\",\"description\":\"\",\"id\":404,\"name_display\":\"nombre_amigable_entrega\",\"parent\":403,\"folders\":[{\"name\":\"iniciativa\",\"description\":\"\",\"id\":405,\"name_display\":\"iniciativa\",\"parent\":404,\"folders\":[],\"issues\":[{\"id\":616,\"issueId\":883842,\"key\":\"VI-246\",\"url\":\"https://greenhopper-dev.app.alcatel-lucent.com/browse/VI-246\",\"summary\":\"GIlito\",\"rootfolderdescription\":\"\",\"description_html\":\"\",\"icon_url\":\"https://greenhopper-dev.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=14390&avatarType=issuetype\",\"issueType\":\"Offer\",\"position\":1,\"childReqs\":{\"childReq\":[]}},{\"id\":617,\"issueId\":883843,\"key\":\"VI-247\",\"url\":\"https://greenhopper-dev.app.alcatel-lucent.com/browse/VI-247\",\"summary\":\"la oferta que me manda\",\"rootfolderdescription\":\"\",\"description_html\":\"\",\"icon_url\":\"https://greenhopper-dev.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=14390&avatarType=issuetype\",\"issueType\":\"Offer\",\"position\":2,\"childReqs\":{\"childReq\":[]}}],\"position\":1}],\"issues\":[],\"position\":1}],\"issues\":[{\"id\":574,\"issueId\":883331,\"key\":\"VR-1856\",\"url\":\"https://greenhopper-dev.app.alcatel-lucent.com/browse/VR-1856\",\"summary\":\"titulo oferta\",\"rootfolderdescription\":\"\",\"description_html\":\"\",\"icon_url\":\"https://greenhopper-dev.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=14406&avatarType=issuetype\",\"issueType\":\"Task\",\"position\":1,\"childReqs\":{\"childReq\":[]}},{\"id\":580,\"issueId\":883391,\"key\":\"VI-238\",\"url\":\"https://greenhopper-dev.app.alcatel-lucent.com/browse/VI-238\",\"summary\":\"su\",\"rootfolderdescription\":\"\",\"description_html\":\"\",\"icon_url\":\"https://greenhopper-dev.app.alcatel-lucent.com/secure/viewavatar?size=xsmall&avatarId=14397&avatarType=issuetype\",\"issueType\":\"Epic\",\"position\":2,\"childReqs\":{\"childReq\":[]}}],\"position\":1}";
	//@SuppressWarnings("resource")
	/*BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:/userdata/jigao/Desktop/Error.txt")));
	String jsonStr="";
	String data=null;
	while((data = br.readLine())!=null)
	{
	System.out.println(data); 
	jsonStr=jsonStr+data+" ";
	}*/
	jsonStr=jsonStr.replaceAll("\n", " ");
	JSONObject parentObject = JSONObject.fromObject(jsonStr);
	 //JSONObject parentObject = getTreeData2(projectKey);
	 dealData2(projectKey,parentObject,NewFolderId );
	
	//}
	//8.Get all baseline of VR project
	//getOneProjectBaselines(projectKey);
	
}

//com.easesolutions.jira.plugins.requirements/1.0/baselines/project/prjKey
//Get all baseline of VR project
public static String getOneProjectBaselines(String projectKey) throws IOException{
	String postUrl =CORP_JIRA_QA + R4J_API_1_0 +Baselines+Project+projectKey;
	int result=HttpMethod.httpGet2(CDCADMIN, CDCADMIN_PWD,postUrl);
	if(result == 1){
		GetMethod info=HttpMethod.getMethodUseProxyAndBasicAuthorByJson(CDCADMIN, CDCADMIN_PWD,postUrl);
		String jsonInfo=info.getResponseBodyAsString();
		System.out.println("jsonInfo:"+jsonInfo);  
		
	    return jsonInfo;
	}	
	
	return null;
	
}

//Get:get R4J version
public static int CheckR4JVersion(){
	
		String version="version";
		int result = HttpMethod.httpGet2(CDCADMIN,CDCADMIN_PWD,CORP_JIRA_QA + R4J_API_1_0 +"/"+ version);
		
		return result;
	}

//Get: get the tree data
public static R4JFolder getTreeData(String projectKey) throws IOException{
	
	System.out.println("projectKey:"+projectKey);
	String postUrl =CORP_JIRA_QA + R4J_API_1_0 +Tree+projectKey;
	int result=HttpMethod.httpGet2(CDCADMIN, CDCADMIN_PWD,postUrl);
	if(result == 1){
		GetMethod info=HttpMethod.getMethodUseProxyAndBasicAuthorByJson(CDCADMIN, CDCADMIN_PWD,postUrl);
		String jsonInfo=info.getResponseBodyAsString();
		System.out.println("jsonInfo:"+jsonInfo);  
		R4JFolder projectFolder = getR4JFolderFromJson(jsonInfo);
	    return projectFolder;
	}	
	return null;
}

//Get: get the tree data
public static JSONObject getTreeData2(String projectKey) throws IOException{
	
	System.out.println("projectKey:"+projectKey);
	String postUrl =CORP_JIRA_QA + R4J_API_1_0 +Tree+projectKey;
	int result=HttpMethod.httpGet2(CDCADMIN, CDCADMIN_PWD,postUrl);
	if(result == 1){
		GetMethod info=HttpMethod.getMethodUseProxyAndBasicAuthorByJson(CDCADMIN, CDCADMIN_PWD,postUrl);
		String parentString=info.getResponseBodyAsString();
		//System.out.println("jsonInfo88:"+parentString);  	
		JSONObject parentObject = JSONObject.fromObject(parentString);
		//System.out.println("ObjectInfo:"+parentString);  	
	    return parentObject;
	}	
	return null;
}

//change the jsonString ->jsonObject ->javaOject
public static R4JFolder getR4JFolderFromJson(String jsonString){
	Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
	classMap.put("folders",R4JFolder.class);		
	classMap.put("issues",R4JIssue.class);	
	classMap.put("childReqs",R4JChildReqs.class);
	classMap.put("childReq",R4JChildReq.class);
	JSONObject listObject=JSONObject.fromObject(jsonString);  
	//System.out.println("java object to"+listObject.toString());
	R4JFolder projectFolder=(R4JFolder)JSONObject.toBean(listObject ,R4JFolder.class,classMap);
	
	if(projectFolder != null){	
		System.out.println("folders"+ projectFolder.toString());
		return projectFolder;
	}
	return null;
	
}

//Post:add a root folder in project folder
public static String addRootFolder(String projectKey,String folderName,String folderDescription) throws IOException {
	/*String createdFolder="/tree/VR/folder?";
	String folderName= "name=The+Java+Init+Foler+5";
	String folderDescription="&description=the+Java+Init+Foler+Description";*/
	
	folderName=folderName.replace(" ", "+");
	if(folderDescription != null || folderDescription != ""){
		folderDescription=folderDescription.replace(" ", "+");
	}	
	System.out.println("folderName:"+folderName);
	System.out.println("folderDescription:"+folderDescription);
	String result=HttpMethod.httpPostWithresponseBody(CDCADMIN, CDCADMIN_PWD,CORP_JIRA_QA + R4J_API_1_0 +Tree+projectKey+Folder+CreatedFolderName+folderName+FolderDescription+folderDescription);
	
	System.out.println("response result:"+result);
	
	return result;
}

//Post:add a sub folder in Specified folder
public static String addSubFolder(String projectKey,int id,String folderName,String folderDescription) throws IOException{
	/*String createdFolder="/tree/VR/folder?";
	String folderName= "name=The+Java+Init+Foler+5";
	String folderDescription="&description=the+Java+Init+Foler+Description";*/
	
	folderName=folderName.replace(" ", "+");
	if(folderDescription != null || folderDescription != ""){
		folderDescription=folderDescription.replaceAll(" ", "+");
		folderDescription=folderDescription.replaceAll("\n", "+");
	}	
	;
	System.out.println("folderName:"+folderName);
	System.out.println("folderDescription:"+folderDescription);
	String result=HttpMethod.httpPostWithresponseBody(CDCADMIN, CDCADMIN_PWD,CORP_JIRA_QA + R4J_API_1_0 + Tree +projectKey+Folder+"/"+id+CreatedFolderName+folderName+FolderDescription+folderDescription);
	
	return result;
}

//Post:add a issue in project folder
public static String addProjectIssue(String projectKey,String issueKey) throws IOException{
	/*String createdFolder="/tree/VR/folder?";
	String folderName= "name=The+Java+Init+Foler+5";
	String folderDescription="&description=the+Java+Init+Foler+Description";*/
	
	System.out.println("projectKey:"+projectKey);
	System.out.println("issueKey:"+issueKey);
	
	String result=HttpMethod.httpPostWithresponseBody(CDCADMIN, CDCADMIN_PWD,CORP_JIRA_QA + R4J_API_1_0 +Tree+projectKey+AddRootIssue+IssueKey+issueKey);
	
	System.out.println("response result:"+result);
	
	return result;
}

//Post:add a issue in Specified folder
public static int addFolderIssue(String projectKey,int id,String issueKey) throws IOException{	
	System.out.println("projectKey:"+projectKey);
	System.out.println("id:"+id);
	System.out.println("issueKey:"+issueKey);
	String postUrl =CORP_JIRA_QA + R4J_API_1_0 +Tree+projectKey+AddFolderIssue+"/"+id+IssueKey+issueKey;
	int result=HttpMethod.httpPost(CDCADMIN, CDCADMIN_PWD,postUrl);
	
	System.out.println("response result:"+result);
	
	return result;
}

//Post:add a child issue in Specified parent issue
public static int addChildIssue(String projectKey,String parentIssueKey,String ChildIssueKey) throws IOException{
	
	System.out.println("projectKey:"+projectKey);
	System.out.println("id:"+parentIssueKey);
	System.out.println("issueKey:"+ChildIssueKey);
	String postUrl =CORP_JIRA_QA + R4J_API_1_0 +ChildLink+projectKey+"/"+parentIssueKey+IssueChildKey+ChildIssueKey;
	int result=HttpMethod.httpPost(CDCADMIN, CDCADMIN_PWD,postUrl);
	
	System.out.println("response result:"+result);
	
	return result;
}

//create folders and issues according to the project R4JFolder Object
public static void dealData(String projectKey,R4JFolder parentFolder,int newFolderId) throws IOException{
	
	if(!HistoryIdList.contains(NewFolderId)){		
		HistoryIdList.add(NewFolderId);
	}
	for(int id : HistoryIdList){
		System.out.println("集合里面存的id: "+id);
	}
	//判断总的对象不为空，再进行下面操作
	if(parentFolder != null){
		System.out.println("NewFolderId:"+NewFolderId);
		//第一次执行肯定会到这里，
		  if(parentFolder.getParent() == 0){
			 System.out.println("This is "+projectKey+" Project root folder!");
		  }
		  //下面判断folders list是否为空，不为空开始创建 folders 和 issues
		  if(parentFolder.getFolders() !=null && !parentFolder.getFolders().isEmpty()){
			  System.out.println("size:"+parentFolder.getFolders().size());
			  //对folders进行遍历
			 for(int i=0;i<parentFolder.getFolders().size();i++){
				 System.out.println("NewFolderId:"+NewFolderId);
				 System.out.println("size:"+parentFolder.getFolders().size());
				 R4JFolder subFolder = parentFolder.getFolders().get(i);
				 
				 if(subFolder.getParent() == -1){
					    System.out.println("NewFolderId-1:"+NewFolderId);
						String jsonResult =addRootFolder(projectKey,subFolder.getName(),subFolder.getDescription());
						if(jsonResult != null){							
							//BusfolderId = getR4JFolderFromJson(jsonResult).id;
							NewFolderId = getR4JFolderFromJson(jsonResult).getId();
							System.out.println("NewFolderId-1:"+NewFolderId);
							dealData(projectKey,subFolder,NewFolderId);
						}						
				  }else if(subFolder.getParent() != 0 ){
					   System.out.println("NewFolderId0:"+NewFolderId);
					   String jsonResult =addSubFolder(projectKey,NewFolderId,subFolder.getName(),subFolder.getDescription());				  
					   //BusfolderId = getR4JFolderFromJson(jsonResult).id;
					   NewFolderId = getR4JFolderFromJson(jsonResult).getId();
					   System.out.println("NewFolderId0:"+NewFolderId);
					   
					   dealData(projectKey,parentFolder.getFolders().get(i),NewFolderId);
				 }
				 
			 }
			  
		  }
		  if(parentFolder.getIssues() != null && !parentFolder.getIssues().isEmpty()){
			  System.out.println("NewFolderId-issues:"+NewFolderId);
			 
			  for(int i=0;i<parentFolder.getIssues().size();i++){
				  R4JIssue issue = parentFolder.getIssues().get(i);
				  
				  String issueKey = issue.getKey();
			  //判断当前目录是否是根目录，因为是否在根目录调用方法不一样。
				  if( NewFolderId == 0){
					  addProjectIssue(projectKey,issueKey);
					  
					  dealChildData(projectKey,issueKey,issue.getChildReqs());
				  }else{
					  addFolderIssue(projectKey,NewFolderId,issueKey);
					  
					  dealChildData(projectKey,issueKey,issue.getChildReqs());
				  }
			  
			  }			  		  
		  }		  
		 
		  if( HistoryIdList.size()>0){
			  
		  HistoryIdList.remove(HistoryIdList.size()-1);
		  
		  if(HistoryIdList.size()>0){
			  NewFolderId = HistoryIdList.get(HistoryIdList.size()-1);
		  }else if(HistoryIdList.size() == 0){
			  NewFolderId = 0;
		  }
		  		  
		  System.out.println("回滚 NewFolderId:"+NewFolderId);
		 
		  }	  
		  		
	 }
	  
}
//
public static void dealChildData(String projectKey,String parentIssueKey,R4JChildReqs childReqs) throws IOException{
	/*if(!HistoryIssueKeyList.contains(HistoryIssueKeyList)){		
		HistoryIssueKeyList.add(NewParentIssueKey);
	}
	for(int issueKey : HistoryIssueKeyList){
		System.out.println("当前集合里面存的issueKey: "+issueKey);
	}*/
	//判断总的对象不为空，再进行下面操作
	if(childReqs != null){	
	List<R4JChildReq> childReqList = childReqs.getChildReq();
	System.out.println("当前的issueKey: " + parentIssueKey);
	if(childReqList != null && !childReqList.isEmpty()){
		System.out.println("childReqs size:"+childReqList.size());
		
		for(int i=0;i<childReqList.size();i++){
			
			R4JChildReq childReq = childReqList.get(i);
			addChildIssue(projectKey,parentIssueKey,childReq.getKey());
			
			dealChildData(projectKey,childReq.getKey(),childReq.getChildReqs());
		}
	}
	

/*
    if( HistoryIssueKeyList.size()>0){
	  
    	HistoryIssueKeyList.remove(HistoryIssueKeyList.size()-1);
    	
    	if(HistoryIssueKeyList.size()>0){
    		NewParentIssueKey = HistoryIssueKeyList.get(HistoryIssueKeyList.size()-1);
    	}else if(HistoryIssueKeyList.size() == 0){
    		NewParentIssueKey = 0;
    	}
	  		  
    	System.out.println("回滚 ParentIssueKey:"+NewParentIssueKey);
	 
	  	}	  */
	}
  }

//
public static void dealData2(String projectKey,JSONObject parentObject,int newFolderId) throws IOException{
	System.out.println("NewStart NewFolderId:"+NewFolderId);
	if(!HistoryIdList.contains(NewFolderId)){		
		HistoryIdList.add(NewFolderId);
	}
	for(int id : HistoryIdList){
		System.out.println("集合里面存的id: "+id);
	}
	//判断总的对象不为空，再进行下面操作
	if(parentObject != null){
		System.out.println("NewFolderId:"+NewFolderId);
		 //JSONObject parentObject = JSONObject.fromObject(parentString);
		  //下面判断folders list是否为空，不为空开始创建 folders 和 issues
		 JSONArray foldersList =parentObject.getJSONArray("folders");
		  if(foldersList !=null && !foldersList.isEmpty()){
			  System.out.println("size:"+foldersList.size());
			  //对folders进行遍历
			 for(int i=0;i<foldersList.size();i++){
				 System.out.println("NewFolderId:"+NewFolderId);				 
				 JSONObject subFolder = JSONObject.fromObject(foldersList.get(i));
				 //R4JFolder subFolder = parentFolder.folders.get(i);
				 
				 if(subFolder.getString("parent").equals("-1")){
					    System.out.println("NewFolderId-1:"+NewFolderId);
						String jsonResult =addRootFolder(projectKey,subFolder.getString("name"),subFolder.getString("description"));
						if(jsonResult != null){							
							//BusfolderId = getR4JFolderFromJson(jsonResult).id;
							//将http的response数据转化为string对象，因为数据是Json格式的数据
							JSONObject newCreateFolder = JSONObject.fromObject(jsonResult);
							String getStringId = newCreateFolder.getString("id");
							//将string类型的id变成int类型的id
							NewFolderId = Integer.parseInt(getStringId);
							System.out.println("NewFolderId-1:"+NewFolderId);
							dealData2(projectKey,subFolder,NewFolderId);
						}						
				  }else if(!subFolder.getString("parent").equals("0")){
					   System.out.println("NewFolderId0:"+NewFolderId);
					   String jsonResult =addSubFolder(projectKey,NewFolderId,subFolder.getString("name"),subFolder.getString("description"));				  
					   //将http的response数据转化为string对象，因为数据是Json格式的数据
					   JSONObject newCreateFolder = JSONObject.fromObject(jsonResult);
					   String getStringId = newCreateFolder.getString("id");
					   //将string类型的id变成int类型的id
					   NewFolderId = Integer.parseInt(getStringId);
					   System.out.println("NewFolderId0:"+NewFolderId);
					   
					   dealData2(projectKey,subFolder,NewFolderId);
				 }
				 
			 }
			  
		  }
		 JSONArray issueList =parentObject.getJSONArray("issues");
		 if(issueList != null && !issueList.isEmpty()){
			  System.out.println("NewFolderId-issues:"+NewFolderId);
			  //对issueList遍历
			  for(int i=0;i<issueList.size();i++){
				  JSONObject childIssue = JSONObject.fromObject(issueList.get(i));
				  //得到当前子issue的key
				  String issueKey = childIssue.getString("key");
				  
				  JSONObject childReqs = (JSONObject) childIssue.get("childReqs");
				  if(childReqs !=null){
					  
					  JSONArray childReqList = childReqs.getJSONArray("childReq");
				 
				 
				  
			  //判断当前目录是否是根目录，因为是否在根目录调用方法不一样。
				  if( NewFolderId == 0){
					  addProjectIssue(projectKey,issueKey);
					 					  
					  dealChildData2(projectKey,issueKey,childReqList);
				  }else{
					  addFolderIssue(projectKey,NewFolderId,issueKey);
					  
					  dealChildData2(projectKey,issueKey,childReqList);
				  }
			 }
		 }			  		  
	 }		  
		 
		  if( HistoryIdList.size()>0){
			  
		  HistoryIdList.remove(HistoryIdList.size()-1);
		  
		  if(HistoryIdList.size()>0){
			  NewFolderId = HistoryIdList.get(HistoryIdList.size()-1);
		  }else if(HistoryIdList.size() == 0){
			  NewFolderId = 0;
		  }
		  		  
		  System.out.println("回滚 NewFolderId:"+NewFolderId);
		 
		  }	  
		  		
	 }
	  
}

//
public static void dealChildData2(String projectKey,String parentIssueKey,JSONArray childReqs) throws IOException{
	/*if(!HistoryIssueKeyList.contains(HistoryIssueKeyList)){		
		HistoryIssueKeyList.add(NewParentIssueKey);
	}
	for(int issueKey : HistoryIssueKeyList){
		System.out.println("当前集合里面存的issueKey: "+issueKey);
	}*/
	//判断总的对象不为空，再进行下面操作
	System.out.println("当前的issueKey: " + parentIssueKey);
	if(childReqs != null && !childReqs.isEmpty()){
		System.out.println("childReqs size:"+childReqs.size());
		for(int i=0;i<childReqs.size();i++){
			//将childReqs里的childReq变为对象
			JSONObject childIssue = JSONObject.fromObject(childReqs.get(i));			
			//得到childIssuekey
			String childIssueKey = childIssue.getString("key");
			addChildIssue(projectKey,parentIssueKey,childIssueKey);
			
			 JSONObject childChildReqs = (JSONObject) childIssue.get("childReqs");
			 if(childChildReqs != null){
				 JSONArray childReqList = childChildReqs.getJSONArray("childReq");
				 dealChildData2(projectKey,childIssueKey,childReqList);
			 }
			
		}
	}
	

/*
    if( HistoryIssueKeyList.size()>0){
	  
    	HistoryIssueKeyList.remove(HistoryIssueKeyList.size()-1);
    	
    	if(HistoryIssueKeyList.size()>0){
    		NewParentIssueKey = HistoryIssueKeyList.get(HistoryIssueKeyList.size()-1);
    	}else if(HistoryIssueKeyList.size() == 0){
    		NewParentIssueKey = 0;
    	}
	  		  
    	System.out.println("回滚 ParentIssueKey:"+NewParentIssueKey);
	 
	  	}	  */

  	}




}


