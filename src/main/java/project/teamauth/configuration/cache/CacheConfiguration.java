package project.teamauth.configuration.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.teamauth.dtos.PartnerLoginCacheDto;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfiguration {

    @Bean
    public Cache<String, PartnerLoginCacheDto> cachePartnerLogin() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build();
    }
}