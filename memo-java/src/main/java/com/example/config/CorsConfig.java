//package com.example.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 解决跨域问题
// */
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
////                设置运行跨域请求的域名
//                .allowedOriginPatterns("*")
////                设置运行的方法
//                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
////                是否允许证书
//                .allowCredentials(true)
////                允许跨域的时间
//                .maxAge(3600)
//                .allowedHeaders("*");
//    }
//}