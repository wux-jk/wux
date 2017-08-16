/** 
 * <pre>项目名称:ssi-common-01 
 * 文件名称:JsonUtil.java 
 * 包名:common.util 
 * 创建日期:2017年7月28日上午10:02:50 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * <pre>Project_Name：ssi-common-01    
 * Type_Name：JsonUtil    
 * Type_Descrition：    
 * Founder：吴茜
 * Found_time：2017年7月28日 下午4:36:19    
 * Updater：吴茜   
 * Update_Time：2017年7月28日 下午4:36:19    
 * Update_Remark：       
 * @version </pre>
 */
public class JsonUtil {
	
	private static Gson gson = new Gson();
	
	public static String toJsonString(Object obj) {
		if (null == obj) {
			throw new NullPointerException();
		}
		String json = gson.toJson(obj);
		return json;
	}
	/**
	 * <pre>fromJson(这里用一句话描述这个方法的作用)   
	 * Founder：吴茜     
	 * Found_time：2017年7月28日 下午4:36:30    
	 * Updater：吴茜       
	 * Update_Time：2017年7月28日 下午4:36:30    
	 * Update_Remark： 
	 * @param json
	 * @param t
	 * @return</pre>
	 */
	public static <T> T fromJson(String json, Class<T> t) {
		if (null == json) {
			throw new NullPointerException();
		}
		T obj = gson.fromJson(json, new TypeToken<T>(){}.getType());
		return obj;
	}

}
