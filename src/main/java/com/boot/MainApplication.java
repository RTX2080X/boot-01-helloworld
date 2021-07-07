package com.boot;

import ch.qos.logback.core.db.DBHelper;
import com.boot.bean.Pet;
import com.boot.bean.User;
import com.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        // 1. 返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        // 2. 查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        // 3. 从组件中获取组件
        Pet tom01 = run.getBean("tom", Pet.class);
        Pet tom02 = run.getBean("tom", Pet.class);
        System.out.println("组件：" + (tom01 == tom02));
        // 4.
        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);
        // 如果使用 proxyBeanMethods == true 代理对象调用方法
        // SpringBoot总会检查这个组件是否在容器中存在
        // 并保持组件单实例
        User user = bean.user01();
        User user1 = bean.user01();
        System.out.println(user == user1);

        User user01 = run.getBean("user01", User.class);
        Pet tom = run.getBean("tom", Pet.class);

        System.out.println("用户的宠物：" + (user01.getPet() == tom));

        // 5. 获取组件
        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        System.out.println("==========================");
        for (String s : beanNamesForType) {
            System.out.println(s);
        }
        DBHelper bean1 = run.getBean(DBHelper.class);
        System.out.println(bean1);

        // @ImportResource
        System.out.println("=========--=-=-===-=======");
        boolean hhhh = run.containsBean("hhhh");
        boolean tom123123 = run.containsBean("tom123123");
        System.out.println("hhhh：" + hhhh);//true
        System.out.println("tom123123：" + tom123123);//true
    }
}
