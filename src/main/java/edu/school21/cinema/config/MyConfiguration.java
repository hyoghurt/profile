package edu.school21.cinema.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@ComponentScan("edu.school21.cinema")
@PropertySource("file:${webapp.root}/WEB-INF/application.properties")
@RequiredArgsConstructor
public class MyConfiguration {

    private final Environment env;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(env.getRequiredProperty("db.driver.name"));
        hikariConfig.setPassword(env.getRequiredProperty("db.password"));
        hikariConfig.setUsername(env.getRequiredProperty("db.username"));
        hikariConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
