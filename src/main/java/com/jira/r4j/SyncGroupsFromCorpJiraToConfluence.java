package com.jira.r4j;

import static com.jira.tool.GeneralStaticFields.*;
import java.io.IOException;
import java.util.HashSet;
import org.apache.commons.httpclient.methods.GetMethod;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.jira.tool.HttpMethod;

public class SyncGroupsFromCorpJiraToConfluence {
	
	public static final String GROUP_USER = "/group/user/direct?groupname=";
	public static final String GROUP_API = "/rest/api/2/group?";
	public static final String QROUP_NAME = "groupname=";
	public static final String QueryParameter = "&expand=users%5B0%3A49%5D";
    
    public static void main(String[] args) {
    	//define which group need to sync
    	//"fnms", "fnms-external", "fnms-ia", "fnms-to", "fnms-fo", "fnms-tpm", "fnms-po"
    	String[] Groups = {"fnms-external", "fnms-ia", "fnms-to", "fnms-fo", "fnms-tpm", "fnms-po"};
    	
    	for (int i=0 ; i< Groups.length ; i++) {
    		//export groups one by one
    		String group = Groups[i];
    		HashSet<String> Jira_Users = new HashSet<String>();
    		
    		String restUrl = "";
    		int j = 0;
    		while( j<4 ){
    			j = j +1;
    			int intMax = 50;
    			if (j==1){
    				restUrl = CORP_JIRA_PROD + GROUP_API + QROUP_NAME + group +
    				          "&expand=users%5B" + ((j-1)*intMax) + "%3A"+ (j*intMax-1) + "%5D";
    			}else{
    				restUrl = CORP_JIRA_PROD + GROUP_API + QROUP_NAME + group +
    				          "&expand=users%5B" + ((j-1)*intMax) + "%3A" + (j*intMax-1) + "%5D";
    			}		
    			
    		GetMethod response = HttpMethod.httpGet(CDCADMIN, CDCADMIN_PWD, restUrl);
    		
    		if (response != null) {
                if (response.getStatusLine().getStatusCode() >= 200 && 
                		response.getStatusLine().getStatusCode() <= 300) {
                	try {
                		String responseBody = response.getResponseBodyAsString();
    					try {
    						JSONObject	json= new JSONObject(responseBody);
    						JSONArray jsonArray = json.getJSONObject("users").getJSONArray("items");
    						
    						for(int m=0;m<jsonArray.length();m++){  
    							JSONObject jsonissue=(JSONObject) jsonArray.get(m);  
    							String Name = jsonissue.getString("name");	
    							//System.out.println(Name);
    							Jira_Users.add(Name);
    						}
    					}catch (JSONException e) {
    						e.printStackTrace();
    					}
                	}catch (IOException e) {
    					e.printStackTrace();
    				} finally{
    					 if (response != null) {
    						 response.releaseConnection();
    			            }
    				}
                }
            }
    	  } //end "while"
    		
    	  System.out.println("Group "+group+" contains "+Jira_Users.size()+" users");
    	  
    	    //add user to group
			for (String name : Jira_Users) {
    			StringBuffer sb = new StringBuffer();
         		             sb.append("{\"name\":\"").append(name).append("\"}");
         		    int a = HttpMethod.httpPost(CONFLUENCE_SERVICE_USERNAME, CONFLUENCE_SERVICE_PASSWORD, 
         		    		CROWD_HOST + CROWD_API + GROUP_USER + group , sb.toString());
         		    //if httpPost failed, print the csl
         		    if ( a == 0 ) {
         		    	System.out.println(name);
         		    }
			}
    	  
    	} //end for
    	
	}    
}
