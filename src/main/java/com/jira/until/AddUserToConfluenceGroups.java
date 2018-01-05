package com.jira.until;

import static com.jira.tool.GeneralStaticFields.*;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jira.tool.HttpMethod;


//Test Git push
public class AddUserToConfluenceGroups {

	public static final String GROUP_USER = "/group/user/direct?groupname=";
	
	public static void main(String[] args) {
		
		String url = CROWD_HOST+CROWD_API+GROUP_USER+"COPCILN-lannion";
		//where your excel file locates
		File file = new File("e:/CSL.xlsx");
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));
			XSSFSheet sheet = workbook.getSheetAt(0);
			int firstRowNum = 0;
			int lastRowNum = sheet.getLastRowNum();
			for (int i = firstRowNum; i <lastRowNum; i++) {
				  XSSFRow row = sheet.getRow(i);
				  XSSFCell csl = row.getCell(1);
				  //get csl
                  String name =csl.getStringCellValue();
                  //System.out.println(name);
                  //generate Json body
                  StringBuffer sb = new StringBuffer();
     		      sb.append("{\"name\":\"").append(name.trim()).append("\"}");
     		      //perform httpPost
	     		  int a = HttpMethod.httpPost(CONFLUENCE_SERVICE_USERNAME, CONFLUENCE_SERVICE_PASSWORD, url , sb.toString());
	     		  System.out.println(name + " " + a);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
