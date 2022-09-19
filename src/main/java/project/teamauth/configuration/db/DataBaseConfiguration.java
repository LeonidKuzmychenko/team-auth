package project.teamauth.configuration.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

    @Bean
    @Profile("local")
    @ConfigurationProperties(prefix = "pg.data-source")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

//    @Bean
//    @Profile("local")
//    @ConfigurationProperties(prefix = "auth.db")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
}
