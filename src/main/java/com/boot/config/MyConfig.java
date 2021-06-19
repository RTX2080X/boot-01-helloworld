package com.boot.config;

import com.boot.bean.Pet;
import com.boot.bean.User;
import com.fasterxml.jackson.core.json.PackageVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Bean 默认单实例
 * 1.使用@Bean给方法注册成为组件，单实例
 * 2.配置累本身也是组件
 * 3.@Configuration 默认属性 proxyBeanMethods() = true ：代理Bean的方法
 *
 */
@Import({PackageVersion.class, User.class})
@Configuration(proxyBeanMethods = true) // 这是一个配置类 == 配置文件
public class MyConfig {
    /**
     * 配置类内部方法不论被外部调用都少次，调用都是容器内同一个实例
     * @return
     */
    @Bean
    public User user01() { // 给容器中添加组件，方法名是组件ID（也可以在 @Bean 里自定义），返回值是组件的实例
        return new User("zhangsan", 18);
    }

    @Bean("tom11")
    public Pet cat() {
        return new Pet("mytomcat");
    }
}
