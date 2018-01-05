package com.jira.r4j;

import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class Copy {
	/*//方式一  
	*//** 
	   * Creates a JSONDynaBean from a JSONObject. 
	   *//*  
	  public static Object toBean( JSONObject jsonObject ){}   
	//返回的数据类型明显不是我们常用的数据类型  
	     
	//方式二  
	*//** 
	   * Creates a bean from a JSONObject, with a specific target class.<br> 
	   *//*  
	public static Object toBean( JSONObject jsonObject, Class beanClass ) {}  
	  
	//方式三（常用）  
	*//** 
	   * Creates a bean from a JSONObject, with a specific target class.<br> 
	   * If beanClass is null, this method will return a graph of DynaBeans. Any 
	   * attribute that is a JSONObject and matches a key in the classMap will be 
	   * converted to that target class.<br> 
	   * The classMap has the following conventions: 
	   * <ul> 
	   * <li>Every key must be an String.</li> 
	   * <li>Every value must be a Class.</li> 
	   * <li>A key may be a regular expression.</li> 
	   * </ul> 
	   *//*  
	  public static Object toBean( JSONObject jsonObject, Class beanClass, Map classMap ){
		  
	  }
	  
	  
	//方式四  
	*//** 
	   * Creates a bean from a JSONObject, with the specific configuration. 
	   *//*  
	  public static Object toBean( JSONObject jsonObject, JsonConfig jsonConfig ){}  
	//方式2其实最终调用的就是方式四，看来jsonConfig对象很重要，决定了最后返回的数据类型，当然还远不至于这些。  
	//方式3也最终调用的是方式4  
	  
	  
	//方式五（常用）  
	*//** 
	   * Creates a bean from a JSONObject, with the specific configuration. 
	   *//*  
	  public static Object toBean( JSONObject jsonObject, Object root, JsonConfig jsonConfig ){}  
	//直接对已有对象的处理，把json的数据写入到已有对象中。  
	  
	//比较常用的方式三与方式五  
	//例子:接着bean to json的代码  
	  
	//二 json to object  
	       
	     JSONObject jsonObject2 = JSONObject.fromObject(returnString);  
	     Object returnObject = null;  
	     //办法一 class+map config的方式三  
	     Map config = new  HashMap();  
	     config.put("addresses", Address.class);  
	     config.put("sameTest", Person.class);  
	     returnObject = JSONObject.toBean(jsonObject, Person.class,config);  
	     System.out.println(returnObject);  
	       
	     //办法二 object+JsonConfig方式五  
	     p = new Person();  
	     JsonConfig jc = new JsonConfig();  
	     jc.setClassMap(config);  
	     jc.setNewBeanInstanceStrategy(new NewBeanInstanceStrategy() {  
	        @Override  
	        public Object newInstance(Class target, JSONObject source)  
	                throws InstantiationException, IllegalAccessException,  
	                SecurityException, NoSuchMethodException, InvocationTargetException {  
	              if( target != null ){  
	                    if(target.getName().equals(Map.class.getName())){  
	                        return new HashMap();  
	                    }  
	                    return NewBeanInstanceStrategy.DEFAULT.newInstance(target, source);  
	                 }  
	              
	            return null;  
	        }  
	    });  
	     JSONObject.toBean(jsonObject, p, jc);  
	     System.out.println(p.getAddresses().get(0).getAddress());  */
}
