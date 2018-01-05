package com.jira.r4j;

import static com.jira.tool.GeneralStaticFields.*;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.jira.tool.HttpMethod;

public class CreateCustomFields {

	public static final String Api = "/rest/api/2/field";
	
	public static void main(String[] args) {

		//The Excel which need to parse
		File file = new File("e:/CustomField.xlsx");
		try {
			//Create Excel，read details
			XSSFWorkbook workbook = 
				new XSSFWorkbook(FileUtils.openInputStream(file));
			//get the first sheet-->method one
            //HSSFSheet sheet = workbook.getSheet("Sheet0");
			//get the first sheet-->method two
			XSSFSheet sheet = workbook.getSheetAt(0);
			int firstRowNum = 0;
			//get last row number of the sheet
			int lastRowNum = sheet.getLastRowNum();
			for (int i = firstRowNum; i <=lastRowNum; i++) {
				XSSFRow row = sheet.getRow(i);
				//get the last cell number of the row
				int lastCellNum = row.getLastCellNum();
				String name = null;
				String type = null;
				String searcherKey = null;
				for (int j = 0; j < lastCellNum; j++) {
					XSSFCell cell = row.getCell(j);
					String value = cell.getStringCellValue();
					if (j==0) {
						name = value;
					} else if (j==1) {
						type = value;
					} else {
						searcherKey = value;
					}
				}
				/*{
				    "name": "NLT required",
				    "type": "com.atlassian.jira.plugin.system.customfieldtypes:select",
				    "searcherKey": "com.atlassian.jira.plugin.system.customfieldtypes:multiselectsearcher"
				}*/
				StringBuffer sb = new StringBuffer();
				sb.append("{\"name\":")
				  .append("\"").append(name).append("\",")
				  .append("\"type\":\"")
				  .append("com.atlassian.jira.plugin.system.customfieldtypes:").append(type).append("\",")
				  .append("\"searcherKey\":\"")
				  .append("com.atlassian.jira.plugin.system.customfieldtypes:").append(searcherKey).append("\"}");
				System.out.println(sb);
				
				HttpMethod.httpPost(CDCADMIN, CDCADMIN_PWD , CORP_JIRA_INT + Api , sb.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
