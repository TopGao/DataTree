package com.jira.until;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import com.jira.tool.HttpMethod;
import static com.jira.tool.GeneralStaticFields.*;

public class ExportGroupUsersToExcel {

	public static final String GROUP_API = "/rest/api/2/group?groupname=";
	public static final String QueryParameter = "&expand=users%5B0%3A49%5D";
	
	public static void main(String[] args) {
		//define which group users need to export
		String[] groups = {"fnms","fnms-ia","fnms-to"};
		for (int g = 0 ; g < groups.length;g++) {
			String group = groups[g];
			String[] title = {"CSL","FullName","Email"};
			List<String> Jira_Users = new ArrayList<String>();
			Map<String,String> FullName = new HashMap<String,String>();
			Map<String,String> Email = new HashMap<String,String>();
			String restUrl = "";
			int j = 0;
			while( j<3 ){
				j = j +1;
				int intMax = 50;
				if (j==1){
					restUrl = CORP_JIRA_PROD+GROUP_API+group+
					          "&expand=users%5B"+((j-1)*intMax)+"%3A"+(j*intMax-1)+"%5D";
				}else{
					restUrl = CORP_JIRA_PROD+GROUP_API+group+
					          "&expand=users%5B"+((j-1)*intMax)+"%3A"+(j*intMax-1)+"%5D";
				}		
				
			GetMethod response = HttpMethod.httpGet(CDCADMIN, CDCADMIN_PWD, restUrl);
			
			if (response != null) {
	            if (response.getStatusLine().getStatusCode() >= 200 && 
	            		response.getStatusLine().getStatusCode() <= 300) {
	            	try {
	            		String responseBody = response.getResponseBodyAsString();
							JSONObject	json= new JSONObject(responseBody);
							JSONArray jsonArray = json.getJSONObject("users").getJSONArray("items");
							//iterate jsonArray
							for(int i=0;i<jsonArray.length();i++){  
								JSONObject jsonissue=(JSONObject) jsonArray.get(i);  
								String csl = jsonissue.getString("name");	
								String fullName = jsonissue.getString("displayName");
								String email = jsonissue.getString("emailAddress");
								Jira_Users.add(csl);
								FullName.put(csl, fullName);
								Email.put(csl, email);
							}
	            	}catch (Exception e) {
						e.printStackTrace();
					} finally{
						 if (response != null) {
							 response.releaseConnection();
				            }
					}
	            }
	        }
		  } //end "while"
			
		  System.out.println(group+" contains "+Jira_Users.size()+" users");
		  
		            //export group users to excel file
					  HSSFWorkbook workbook = new HSSFWorkbook();
					  HSSFSheet sheet = workbook.createSheet();
					  HSSFRow row = sheet.createRow(0);
					  HSSFCell cell = null;
					  
					  for (int i = 0; i < title.length; i++) {
						  cell = row.createCell(i);
						  cell.setCellValue(title[i]);
					  }
					  
					  for (int i = 1; i <= Jira_Users.size(); i++) {
							HSSFRow nextrow = sheet.createRow(i);
							HSSFCell cell2 = nextrow.createCell(0);
							cell2.setCellValue(Jira_Users.get(i-1));
							cell2 = nextrow.createCell(1);
							cell2.setCellValue(FullName.get(Jira_Users.get(i-1)));
							cell2 = nextrow.createCell(2);
							cell2.setCellValue(Email.get(Jira_Users.get(i-1)));
					  }
					  
					  Date d = new Date();
					  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
					  String time = f.format(d.getTime());
					 // System.out.println(time);
					  File file = new File("d:/"+group+time+".xls");
					  try {
					    	file.createNewFile();
							FileOutputStream stream = FileUtils.openOutputStream(file);
							workbook.write(stream);
							stream.close();
					  } catch (IOException e) {
							e.printStackTrace();
					  }
		} //end for			  
	}   
}

