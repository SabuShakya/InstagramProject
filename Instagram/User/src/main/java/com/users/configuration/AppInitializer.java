package com.users.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import static com.sun.prism.shape.ShapeRep.InvalidationType.LOCATION;
import static sun.font.CreatedFontTracker.MAX_FILE_SIZE;

@Configuration
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfiguration.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfiguration.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
