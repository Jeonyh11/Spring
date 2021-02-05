package kr.or.ddit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
			//���� �������� Ȯ���ϴ� ���� : session�� S_USER�Ӽ��� �ִ��� �˻�
		if(request.getSession().getAttribute("S_USER") == null) {
			response.sendRedirect("/login/view");
			return false;
		}
		return true;
	}
}
