package quotation.management.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Controller;
import quotation.management.domain.DTO.StockDTO;
import quotation.management.services.CacheService;

@Controller
public class CacheServiceImpl implements CacheService {

    @Autowired
    private Cache cache;

    public void save(String key, StockDTO stock) {
        cache.put(key, stock);
    }

    public StockDTO find(String key) {
        return cache.get(key, StockDTO.class);
    }
}
