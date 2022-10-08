package edu.school21.cinema.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@ComponentScan("edu.school21.cinema")
@PropertySource("application.properties")
public class MyConfiguration {
    @Value("${db.driver.name}")
    private String driverClassName;
    @Value("${db.password}")
    private String password;
    @Value("${db.username}")
    private String username;
    @Value("${db.url}")
    private String url;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setPassword(password);
        hikariConfig.setUsername(username);
        hikariConfig.setJdbcUrl(url);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
