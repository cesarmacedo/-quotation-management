package quotation.management.services;

import quotation.management.domain.DTO.StockDTO;

public interface CacheService {
     void save(String key, StockDTO stock);

     StockDTO find(String key);
}
