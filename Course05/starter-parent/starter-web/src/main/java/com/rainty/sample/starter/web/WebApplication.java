package com.rainty.sample.starter.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.rainty.sample.starter.config.ApplicationConfig;
import com.rainty.sample.starter.config.H2DatabaseConfig;
import com.rainty.sample.starter.web.config.WebConfig;

public class WebApplication implements WebApplicationInitializer {

	@Override
	public void onStartup(final ServletContext servletContext) throws ServletException {
		final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WebConfig.class, ApplicationConfig.class, H2DatabaseConfig.class);
		servletContext.addListener(new ContextLoaderListener(context));

		final ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		registration.setLoadOnStartup(1);
		registration.addMapping("/spring/*");
	}

}
