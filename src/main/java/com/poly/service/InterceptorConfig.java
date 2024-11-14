package com.poly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	GlobalInterceptor global;

	@Autowired
	AuthInterceptor auth;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(global).addPathPatterns("/**").excludePathPatterns("/assets/**");
		registry.addInterceptor(auth).addPathPatterns("/account/edit", "/account/chgpwd", "/order/**", "/admin/**")
				.excludePathPatterns("/assets/**", "/admin/home/index");
	}
}
