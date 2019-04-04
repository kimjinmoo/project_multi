package com.sample.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HelloWorldInitializer implements WebApplicationInitializer {

  private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

  @Override
  public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
    registerDispatcherServlet(servletContext);
  }

  private void registerDispatcherServlet(ServletContext servletContext) {
    AnnotationConfigWebApplicationContext dispatcherContext = createContext(HelloWorldConfig.class);
    ServletRegistration.Dynamic dispatcher;

    dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(dispatcherContext));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");
  }

  private AnnotationConfigWebApplicationContext createContext(final Class<?>... annotatedClasses) {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(annotatedClasses);
    return context;
  }
}
