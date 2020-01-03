package com.cloud.configuration;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.support.AbstractApplicationContext;


@SpringBootApplication(scanBasePackages = "com.cloud")
//@EnableScheduling//开启对计划任务的支持，然后在要执行计划任务的方法上注解@Scheduled，声明这是一个计划任务。
//@ImportResource(value = "spring/applicationContext.xml")
//@EnableApolloConfig()//配置中心 apollo 的注解
@EnableHystrix  //启用 Hystrix
@EnableHystrixDashboard //启用 Hystrix Dashboard，
@NacosPropertySource(dataId = "customer_config", autoRefreshed = true, groupId = "DEFAULT_GROUP")
//@EnableTurbine
public class Application extends SpringBootServletInitializer {
//    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    //    @Autowired
//    private RefreshScope refreshScope;
//    @ApolloConfig
//    private Config config;
    @Autowired
    AbstractApplicationContext c;

    @Autowired
    RefreshScope refreshScope;

    /**
     * 如果需要 war 运行，就需要继承SpringBootServletInitializer
     * 重写configure方法指定 Application类
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        //elasticseach 和redis底层都用了 Netty
        System.setProperty("es.set.netty.runtime.available.processors","false");
//    	check();
        SpringApplication app = new SpringApplication(Application.class);
//        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

//        ConfigurableApplicationContext contest = SpringApplication.run(Application.class);


    }
    //如果需要在Apollo配置变化时自动更新注入的值
//    @ApolloConfigChangeListener
//    public void onChange(ConfigChangeEvent changeEvent) {
//        boolean redisCacheKeysChanged = false;
//        for (String changedKey : changeEvent.changedKeys()) {
//            System.out.println("changedKey: "+changedKey +"   value: " +  config.getProperty(changedKey,""));
//            refreshScope.refreshAll();
//            if (changedKey.startsWith("redis.ip")) {
//
//                break;
//            }
//        }
//
////        refreshScope.refresh("sampleRedisConfig");
//    }


}
