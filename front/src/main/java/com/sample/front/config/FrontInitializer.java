package com.sample.front.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;

public class FrontInitializer implements WebApplicationInitializer {

  private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

  @Override
  public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
    registerDispatcherServlet(servletContext);
  }

  private void registerDispatcherServlet(ServletContext servletContext) {
    AnnotationConfigWebApplicationContext dispatcherContext = createContext(FrontConfig.class);
    ServletRegistration.Dynamic dispatcher;

    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding("UTF-8");
    characterEncodingFilter.setForceEncoding(true);

    EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);

    FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("characterEncoding", characterEncodingFilter);
    characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");

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
