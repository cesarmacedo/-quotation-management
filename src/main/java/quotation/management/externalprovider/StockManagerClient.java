package quotation.management.externalprovider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import quotation.management.domain.DTO.StockDTO;
import quotation.management.domain.DTO.StockNotificationDTO;
import quotation.management.exception.NoSuchElementFoundException;
import quotation.management.services.CacheService;

import java.util.List;

@Service
public class StockManagerClient {

    private Logger logger = LoggerFactory.getLogger(StockManagerClient.class);

    @Value("${server.port}")
    private String serverPort;

    @Value("${server.address}")
    private String serverAdress;

    @Value("${stock.manager.api}")
    private String stockManagerApiUrl;

    @Value("${stock.manager.notification}")
    private String stockManagerNotificationUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CacheService cacheService;

    public StockDTO validateStock(String stockId) {

        StockDTO stock = cacheService.find(stockId) != null ? cacheService.find(stockId) :
                restTemplate.getForObject(stockManagerApiUrl + stockId, StockDTO.class);

        if (stock == null) {
            throw new NoSuchElementFoundException(HttpStatus.PRECONDITION_FAILED, "stock id not found - " + stockId);
        }
        cacheService.save(stockId, stock);
        return stock;
    }

    public void registerToStockManager() {

        List<StockNotificationDTO> listResponse = restTemplate.postForObject(
                stockManagerNotificationUrl, new StockNotificationDTO(serverAdress, serverPort), List.class);

        if (listResponse != null) {
            logger.debug(listResponse.toString());
        }
    }

}
