package common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppilcationListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/*System.out.println("监听appcilcation,启动项目就加载");*/
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
