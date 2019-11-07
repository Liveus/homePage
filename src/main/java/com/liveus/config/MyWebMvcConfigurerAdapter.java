package com.liveus.config;

import com.liveus.common.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

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
        super.addViewControllers(registry);
    }

    /**
    * @Desc:  CROS配置
    * @author: shenliqiang
    * @Time: 2019/11/6 10:18
    * @param corsRegistry
    * @return
    */

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        // 允许跨域访问资源定义： /api/ 所有资源
        corsRegistry.addMapping("/api/**")
                // 只允许本地的9000端口访问
                .allowedOrigins("http://localhost:8080", "http://127.0.0.1:8080")
                // 允许发送Cookie
                .allowCredentials(true)
                // 允许所有方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD");
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
                "/**/*.png", "/**/*.jpg", "/**/*.jpeg","/**/*.txt",
                "/user/toLogin","/user/login",
                "/index",
                "/blog/**",
                "/blogClass/getAll","/blogClass/newClass");
        super.addInterceptors(registry);
    }

}