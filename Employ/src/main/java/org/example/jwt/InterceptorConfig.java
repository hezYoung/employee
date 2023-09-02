//package org.example.config;
//
//import org.example.interceptors.Jwtinterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * Created with IntelliJ IDEA.
// *
// * @Author: He Zhiyang
// * @Date: 2023/04/21/23:22
// * @Description:
// */
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new Jwtinterceptor()).
//                addPathPatterns("/employ/*")
//                .excludePathPatterns("/employ/singu")
//                .excludePathPatterns("/employ/login");
//
//    }
//}
