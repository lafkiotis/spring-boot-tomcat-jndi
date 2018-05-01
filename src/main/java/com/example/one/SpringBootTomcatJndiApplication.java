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
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})
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
                sakilaContextResource.setProperty("driverClassName", "com.mysql.jdbc.Driver");
                sakilaContextResource.setProperty("url", "jdbc:mysql://localhost/sakila");
                sakilaContextResource.setProperty("username", "cdvdis");
                sakilaContextResource.setProperty("password", "Masouridis50");
                context.getNamingResources().addResource(sakilaContextResource);

                ContextResource matrixContextResource = new ContextResource();
                matrixContextResource.setName("matrixDataSource");
                matrixContextResource.setType(DataSource.class.getName());
                matrixContextResource.setProperty("driverClassName", "com.mysql.jdbc.Driver");
                matrixContextResource.setProperty("url", "jdbc:mysql://localhost/matrix");
                matrixContextResource.setProperty("username", "cdvdis");
                matrixContextResource.setProperty("password", "Masouridis50");
                context.getNamingResources().addResource(matrixContextResource);

                ContextResource securityContextResource = new ContextResource();
                securityContextResource.setName("securityDataSource");
                securityContextResource.setType(DataSource.class.getName());
                securityContextResource.setProperty("driverClassName", "com.mysql.jdbc.Driver");
                securityContextResource.setProperty("url", "jdbc:mysql://localhost/security_database");
                securityContextResource.setProperty("username", "cdvdis");
                securityContextResource.setProperty("password", "Masouridis50");
                context.getNamingResources().addResource(securityContextResource);


            }
        };
    }
}
