package com.boot;

import com.boot.bean.Pet;
import com.boot.bean.User;
import com.boot.config.MyConfig;
import com.fasterxml.jackson.core.json.PackageVersion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * 主程序类
 * <p>
 * SpringBootApplication：这是一个SpringBoot应用
 * ------------------------------------------------
 * import({PackageVersion.class, User.class})
 * - 给容器中自动创建出这两个类型的组件,默认组件名是全类名
 * -
 */
@Import({PackageVersion.class, User.class})
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        // 1。返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        // 2。查看容器组件
        for (String beanDefinitionName : run.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        // 3。从容器中获取组件,注册的组件 默认都是单实例的
        Pet tom01 = run.getBean("tom11", Pet.class);
        Pet tom02 = run.getBean("tom11", Pet.class);
        System.out.println("tom01: " + tom01);
        System.out.println("tom02: " + tom02);
        System.out.println("组件： " + (tom01 == tom02));


        // 4. com.boot.config.MyConfig$$EnhancerBySpringCGLIB$$d3d22001@2c30b71f
        MyConfig bean = run.getBean(MyConfig.class); // MyConfig 被EnhancerBySpringCGLIB增强了
        System.out.println(bean);

        // 5. SpringBoor 会保持
        User user = bean.user01();
        User user1 = bean.user01();
        System.out.println(user == user1);

        // import 获取组件
        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        System.out.println("===");
        for (String s : beanNamesForType) {
            System.out.println(s);
        }
        PackageVersion bean1 = run.getBean(PackageVersion.class);
        System.out.println(bean1); // com.fasterxml.jackson.core.json.PackageVersion@44f9779c
    }
}
