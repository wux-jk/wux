package common.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener{

	//创建
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		/*String id = se.getSession().getId();*/
		/*System.out.println("创建了一个session会话"+id);*/
	}

	//销毁
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

}
