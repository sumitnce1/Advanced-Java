package org.sumit.firstmvcapp.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext application) throws ServletException {
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.register(WebAppConfigurator.class);
        springContext.setServletContext(application);
        
        ServletRegistration.Dynamic mainController = application.addServlet("dispatcher", new DispatcherServlet(springContext));
        mainController.setLoadOnStartup(10);
        mainController.addMapping("/spring/*");
    }
}
