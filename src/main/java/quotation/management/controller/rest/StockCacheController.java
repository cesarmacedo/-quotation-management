package quotation.management.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quotation.management.externalprovider.StockManagerClient;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/stockcache")
public class StockCacheController {
    private Logger logger = LoggerFactory.getLogger(StockCacheController.class);
    private StockManagerClient stockManagerClient;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    public StockCacheController(StockManagerClient theStockManagerClient) {
        stockManagerClient = theStockManagerClient;
    }

    @DeleteMapping("/")
    void deleteCache() {
        for (String name : cacheManager.getCacheNames()) {
            cacheManager.getCache(name).clear();
        }
        logger.debug("Deleted stocks cache.");
    }

    @PostConstruct
    void postConstruct() {
        stockManagerClient.registerToStockManager();
    }
}
