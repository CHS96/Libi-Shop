package com.myservice.config;

import com.myservice.web.interceptor.LoginCheckInterceptor;
import com.myservice.web.interceptor.ManagerCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/" , "/login", "/logout",
                        "/css/**", "/*.ico", "/error"
                );

        registry.addInterceptor(new ManagerCheckInterceptor())
                .order(2)
                .addPathPatterns("/items/manager/**")
                .excludePathPatterns(
                        "/" , "/login", "/logout",
                        "/css/**", "/*.ico", "/error"
                );
    }
}
