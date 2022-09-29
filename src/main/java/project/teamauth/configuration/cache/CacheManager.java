package project.teamauth.configuration.cache;

import com.google.common.cache.Cache;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.teamauth.dtos.PartnerLoginCacheDto;

@Service
@AllArgsConstructor
public class CacheManager {

    private final Cache<String, PartnerLoginCacheDto> cache;

    //Method to fetch previously stored record using record key
    public PartnerLoginCacheDto get(String key) {
        return cache.getIfPresent(key);
    }

    //Method to put a new record in Cache Store with record key
    public void add(String key, PartnerLoginCacheDto value) {
        if (key != null && value != null) {
            cache.put(key, value);
        }
    }
}