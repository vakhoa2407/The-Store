package com.poly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class AuthInterceptor implements HandlerInterceptor{
	
	@Autowired
	SessionService session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    String uri = request.getRequestURI();
	    User user = session.get("login");

	    // Kiểm tra nếu người dùng chưa đăng nhập
	    if (user == null) {
	        session.set("security-uri", uri);
	        response.sendRedirect("/login?error=Please_login!");
	        return false;
	    }

	    // Kiểm tra quyền truy cập
	    if (!user.isAdmin() && uri.startsWith("/admin/")) {
	        session.set("security-uri", uri);
	        response.sendRedirect("/login?error=Access_denied!");
	        return false;
	    }

	    return true;
	}

	
	
}
