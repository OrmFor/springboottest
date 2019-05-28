package com.test.springboot;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
* @Author wwy
* @Description 继承SpringBootServletInitializer是用于打war
 *             若打包成war包,则需要继承
 *             org.springframework.boot.context.web.SpringBootServletInitializer类,
 *             覆盖其config(SpringApplicationBuilder)方法
 *
 *             EmbeddedServletContainerCustomizer 改接口是用来修改配置，能覆盖application.yml配置
* @Date 14:57 2018/12/5
* @Param
* @return
**/
//@EnableAutoConfiguration
//@EnableTransactionManagement//(order = 10) //开启事务，并设置order值，默认是Integer的最大值
//@ComponentScan(basePackages={"com.test.springboot"})
@MapperScan(basePackages = {"com.test.springboot.dao"})
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args){

        SpringApplication.run(Application.class,args);
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(8088);
    }


 /*   @Bean(name="transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager(DataSource dataSource) {
        PlatformTransactionManager dataSourceTransactionManager =  new DataSourceTransactionManager(dataSource);
        ((DataSourceTransactionManager) dataSourceTransactionManager).setNestedTransactionAllowed(true);
        ((DataSourceTransactionManager) dataSourceTransactionManager).setRollbackOnCommitFailure(true);

        return dataSourceTransactionManager;
    }*/

}
