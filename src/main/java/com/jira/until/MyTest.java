package com.jira.until;

import java.util.ArrayList;

public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.
		/*String st= "12345 67890 abcd";
		String s1=st.split(" ")[0];
		System.out.println(s1);
		System.out.println("s1.length:"+s1.length());
		String t3=st.substring(s1.length()+1);
		System.out.println(t3);
		System.out.println(t3+" "+s1);*/
		
		/*2.String s = "ab cde fg";
		String s2="jigao   zhanglang2                                            "
				+ "  abidemib";

        String[] re = s2.split(" ");//用split()函数直接分割
        System.out.println(re.length);
        for (String string : re) {
        	//System.out.print(string.trim()+",");
        	if(!string.trim().equals("")){
            System.out.print(string.trim()+",");
            }
		
        }*/
        
        
      /*  String test1="  ";
        String test2="      ";
        if(test1.equals(test2)){
        	System.out.println("1");
        }else{
        	System.out.println("2");
        	
        }*/
		
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
		/*ArrayList<String> list=new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.remove(2);
		System.out.println(list.size());
		System.out.println(list.get(2));*/
		
		String string= "https://greenhopper-qa.app.alcatel-lucent.com/rest/api/2/group/user?groupname=Advocate";
		
		System.out.println(string.charAt(85));
		
		
		
	}

}
