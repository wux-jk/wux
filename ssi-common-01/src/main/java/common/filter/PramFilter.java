package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class PramFilter implements Filter{

	//初始化
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	//执行的时候调用
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//通过强转获取session
		/*HttpServletRequest httpRequest=(HttpServletRequest) request;
		System.out.println(httpRequest.getSession().getId());*/
		System.out.println("初始化过滤器");
		//调用这个方法向下执行
		chain.doFilter(request, response);
	}

	//销毁
	@Override
	public void destroy() {
		
	}

}
