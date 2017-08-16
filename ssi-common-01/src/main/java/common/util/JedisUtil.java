/** 
 * <pre>项目名称:ssi-common-01 
 * 文件名称:JedisUtil.java 
 * 包名:common.util 
 * 创建日期:2017年7月28日上午10:26:55 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package common.util;

import redis.clients.jedis.Jedis;

/**
 * 
 * <pre>Project_Name：ssi-common-01    
 * Type_Name：JedisUtil    
 * Type_Descrition：    
 * Founder：吴茜
 * Found_time：2017年7月28日 下午5:00:55    
 * Updater：吴茜   
 * Update_Time：2017年7月28日 下午5:00:55    
 * Update_Remark：       
 * @version </pre>
 */
public class JedisUtil {
	
	private static Jedis jedis = null;
	
	private static Jedis getJedis() {
		if (null == jedis) {
			synchronized (JedisUtil.class) {
				if (null == jedis) {
					jedis = new Jedis("192.168.186.128", 6379);
					jedis.auth("123456");
				}
			}
		}
		return jedis;
	}
/**
 * <pre>setString(这里用一句话描述这个方法的作用)   
 * Founder：吴茜     
 * Found_time：2017年7月28日 下午5:00:49    
 * Updater：吴茜       
 * Update_Time：2017年7月28日 下午5:00:49    
 * Update_Remark： 
 * @param k
 * @param v
 * @param seconds 设置过期的时间
 * @return</pre>
 */
	public static String setString(String k, String v, int seconds) {
		Jedis jedis = getJedis();
		String s = jedis.set(k, v);
		if (0 <= seconds) {
			jedis.expire(k, seconds);
		}
		return s;
	}
	
	/**
	 * <pre>getString(这里用一句话描述这个方法的作用)   
	 * Founder：吴茜     
	 * Found_time：2017年8月7日 上午9:30:37    
	 * Updater：吴茜       
	 * Update_Time：2017年8月7日 上午9:30:37    
	 * Update_Remark： 
	 * @param k
	 * @return</pre>
	 */
	public static String getString(String k) {
		Jedis jedis = getJedis();
		String v = jedis.get(k);
		return v;
	}

}
