package com.liveus.utils;

import com.liveus.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //通过addResourceHandler添加映射路径，然后通过addResourceLocations来指定路径。
        registry.addResourceHandler("/my/**").addResourceLocations("classpath:/my/");
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/toLogin").setViewName("login");
        super.addViewControllers(registry);
    }

    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new MyInterceptor()).excludePathPatterns(
                "/**/*.png", "/**/*.jpg", "/**/*.jpeg",
                "/user/toLogin","/user/login",
                "/index",
                "/blog/allBlogs","/blog/searchTitle","/blog/getBlogById/**","/blog/submit","/blog/uploadSource","/blog/uploadBlogSource",
                "/blogClass/getAll","/blogClass/newClass");
        super.addInterceptors(registry);
    }

}