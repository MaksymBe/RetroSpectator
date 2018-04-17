//package com.dimax.retrospectator;
//
//import com.dimax.retrospectator.Entity.*;
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//import org.apache.commons.dbcp.BasicDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@PropertySource({"classpath:application.properties"})
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "com.dimax.retrospectator")
//public class DataSourceConfig {
//
//
//    private final Environment env;
//
//    @Autowired
//    public DataSourceConfig(Environment env) {
//        this.env = env;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        return new JpaTransactionManager(entityManagerFactory().getObject());
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        localContainerEntityManagerFactoryBean.setDataSource(getDataSource());
//        localContainerEntityManagerFactoryBean.setPackagesToScan("com.dimax.retrospectator");
//        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
//        localContainerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());
//        return localContainerEntityManagerFactoryBean;
//    }
//
//    private Properties hibernateProperties() {
//        return new Properties() {
//            {
//                setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
//                setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
//                setProperty("hibernate.naming.strategy", env.getProperty("spring.jpa.hibernate.naming.physical-strategy"));
//            }
//        };
//    }
//
//    @Bean
//    public DataSource getDataSource() {
//        DataSource dataSource = new MysqlDataSource();
//        ((MysqlDataSource) dataSource).setURL(env.getProperty("spring.datasource.url"));
//        ((MysqlDataSource) dataSource).setUser(env.getProperty("spring.datasource.username"));
//        ((MysqlDataSource) dataSource).setPassword(env.getProperty("spring.datasource.password"));
//        return dataSource;
//    }
//
//}
