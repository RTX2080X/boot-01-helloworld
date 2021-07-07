package com.boot.config;

import ch.qos.logback.core.db.DBHelper;
import com.boot.bean.Pet;
import com.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 1. 配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
 * 2. 配置类本身也是组件
 * 3. proxyBeanMethods:代理Bean的方法
 * Full(proxyBeanMethods = true):保证每个@Bean方法被调用多少次返回的组件都是单实例的
 * Lite(proxyBeanMethods = false):返回的组件都是新创建的，增加系统运行速度
 * 4. @Import({User.class,DBHelper.class):给容器中自动创建这两个类新的组件、默认组件名称就是全类名
 */
@Import({User.class, DBHelper.class})
@Configuration() // 告诉SpringBoot这是一个配置类 == 配置文件
@ConditionalOnMissingBean(name = "tom")
@ImportResource("classpath:beans.xml")
public class MyConfig {
    @Bean("user01")
    public User user01() {
        User user = new User("zhangsan", 19);
        // User 组件依赖了Pet组件
        user.setPet(tom());
        return user;
    }

    @Bean("tom")
    public Pet tom() {
        return new Pet("tomcat");
    }
}
