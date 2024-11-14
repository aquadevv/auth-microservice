package ru.journalplus.journalplus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfig {

    private final Environment env;
    private final DataSource dataSource;

    @Autowired
    public HibernateConfig(Environment env, DataSource dataSource) {
        this.env = env;
        this.dataSource = dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        hibernateProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));

        sessionFactory.setHibernateProperties(hibernateProperties);

        System.out.println("Hibernate dialect: " + hibernateProperties.getProperty("hibernate.dialect"));
        System.out.println("Hibernate hbm2ddl.auto: " + hibernateProperties.getProperty("hibernate.hbm2ddl.auto"));

        return sessionFactory;
    }
}