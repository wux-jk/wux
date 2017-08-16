package common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * 
 * <pre>Project_Name：ssi-01    
 * Type_Name：LoginInterceptor    
 * Type_Descrition：    
 * Founder：吴茜
 * Found_time：2017年7月19日 下午2:04:50    
 * Updater：吴茜   
 * Update_Time：2017年7月19日 下午2:04:50    
 * Update_Remark：       
 * @version </pre>
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取一个session 
		HttpSession session = request.getSession();
		System.out.println(session.getId());
	
		//判断用户有没有登录
		if(null!=session.getAttribute("userInfo")){
			return true;
			
		}else{
			//未登录，重定向页面到登陆页面
			//判断是否是ajax请求
			
			String type = request.getHeader("X-Requested-With");// XMLHttpRequest
			// 重定向
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
			// 转发
			if (StringUtils.equals("XMLHttpRequest", type)) {
				// ajax请求
				response.setHeader("SESSIONSTATUS", "TIMEOUT");
				response.setHeader("CONTEXTPATH", basePath+"login2.jsp");
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}else{
				//常规的重定向
				response.sendRedirect(request.getContextPath()+"/login2.jsp");
			}
		}
		
		
		
		return false;
	}
}
