package com.liveus.config;

import com.liveus.common.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {
    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //通过addResourceHandler添加映射路径，然后通过addResourceLocations来指定路径。
        registry.addResourceHandler("/my/**").addResourceLocations("classpath:/my/");
    }

    /**
    * @Desc:  视图解析
    * @author: shenliqiang
    * @Time: 2019/11/6 10:18
    * @param registry
    * @return
    */

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/toLogin").setViewName("login");
    }

    private CorsConfiguration corsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        return corsConfiguration;
    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig());
        return new CorsFilter(source);
    }

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(myInterceptor()).addPathPatterns("/**").excludePathPatterns(
                "/**/*.png", "/**/*.jpg", "/**/*.jpeg","/**/*.txt",
                "/user/toLogin","/user/login",
                "/user/ex","/json","/500",
                "/index");
    }
    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }
}