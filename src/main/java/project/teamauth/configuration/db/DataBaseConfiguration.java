package project.teamauth.configuration.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import project.teamauth.models.Role;
import project.teamauth.repositories.RoleRepository;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "pg.data-source")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @DependsOn("initDb")
    public Role defaultRole(RoleRepository repository) {
        return repository.findByName("ROLE_USER").get();
    }
}