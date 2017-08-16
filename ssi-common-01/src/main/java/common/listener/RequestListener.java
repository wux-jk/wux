package common.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextListener;

public class RequestListener extends RequestContextListener{
	
	//初始化创建一个request请求
	@Override
	public void requestInitialized(ServletRequestEvent requestEvent) {
		HttpServletRequest httpRequest=(HttpServletRequest) requestEvent.getServletRequest();
		/*System.out.println("监听监听器"+httpRequest.getRequestURI());*/
	}
	
	
	

}
