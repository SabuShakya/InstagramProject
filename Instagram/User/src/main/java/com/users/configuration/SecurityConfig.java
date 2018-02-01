//package com.users.configuration;
//
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.logging.Logger;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable();
//        http.cors().and();
//    }
//
////    @Bean
////    public CorsConfigurationSource corsConfigurationSource() {
////        final CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowedOrigins(ImmutableList.of("*"));
////        configuration.setAllowedMethods(ImmutableList.of("HEAD",
////                "GET", "POST", "PUT", "DELETE", "PATCH"));
////        // setAllowCredentials(true) is important, otherwise:
////        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
////        configuration.setAllowCredentials(true);
////        // setAllowedHeaders is important! Without it, OPTIONS preflight request
////        // will fail with 403 Invalid CORS request
////        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
////        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", configuration);
////        return source;
////    }
////    CorsConfigurationSource corsConfigurationSource() {
////        CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowedOrigins(Arrays.asList("https://localhost:8080"));
////        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", configuration);
////        return source;
////    }
////    public CorsFilter corsFilter() {
////
////    }
//    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    public void doFilter(ServletRequest req, ServletResponse resp,
//                         FilterChain chain) throws IOException, ServletException {
//        logger.info("> doFilter");
//
//        HttpServletResponse response = (HttpServletResponse) resp;
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
//        //response.setHeader("Access-Control-Allow-Credentials", "true");
//        chain.doFilter(req, resp);
//
//        logger.info("< doFilter");
//    }
//}
