package com.example.one;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})
@EnableCaching
public class SpringBootTomcatJndiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTomcatJndiApplication.class, args);
    }

    // if the JNDI datasource is defined by tomcat then there's no need to create this bean
    @Bean
    public TomcatServletWebServerFactory tomcatFactory() {
        return new TomcatServletWebServerFactory() {

            @Override
            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
                tomcat.enableNaming();
                return super.getTomcatWebServer(tomcat);
            }

            @Override
            protected void postProcessContext(Context context) {
                ContextResource sakilaContextResource = new ContextResource();
                sakilaContextResource.setName("sakilaDataSource");
                sakilaContextResource.setType(DataSource.class.getName());
                sakilaContextResource.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
                sakilaContextResource.setProperty("url", "jdbc:mysql://192.168.48.135/sakila?useSSL=false");
                sakilaContextResource.setProperty("username", "root");
                sakilaContextResource.setProperty("password", "password");
                context.getNamingResources().addResource(sakilaContextResource);

                ContextResource securityContextResource = new ContextResource();
                securityContextResource.setName("securityDataSource");
                securityContextResource.setType(DataSource.class.getName());
                securityContextResource.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
                securityContextResource.setProperty("url", "jdbc:mysql://192.168.48.135/security_database?useSSL=false");
                securityContextResource.setProperty("username", "root");
                securityContextResource.setProperty("password", "password");
                context.getNamingResources().addResource(securityContextResource);


            }
        };
    }
}
