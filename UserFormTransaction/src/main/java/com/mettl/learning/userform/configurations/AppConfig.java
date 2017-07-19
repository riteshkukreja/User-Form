/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mettl.learning.userform.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author admin
 */
@Configuration
@EnableWebMvc
@PropertySource("WEB-INF/base.properties")
@ComponentScan("com.mettl.learning")
@EnableTransactionManagement(proxyTargetClass = true)
public class AppConfig extends WebMvcConfigurerAdapter {
    
    @Value("${driver}")
    private String driverClassName;
    
    @Value("${url}")
    private String url;
    
    @Value("${db.user}")
    private String username;
    
    @Value("${password}")
    private String password;
    
    @Bean
    public DriverManagerDataSource ds() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driverClassName);
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(username);
        driverManagerDataSource.setPassword(password);
        
        return driverManagerDataSource;
    }
    
    @Bean 
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jTemplate = new JdbcTemplate();
        jTemplate.setDataSource(ds());
        return jTemplate;
    }
    
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("style/**").addResourceLocations("static/style/");
    }
    
    @Override 
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/", ".jsp");
    } 
    
    @Bean
    public Validator localValidatorFactoryBean() {
       return new LocalValidatorFactoryBean();
    }
    
    @Override
    public Validator getValidator() {
        return localValidatorFactoryBean();
    }
    
    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(ds());
        System.out.println("creating transactionManager");
        return dataSourceTransactionManager;
    }
}
