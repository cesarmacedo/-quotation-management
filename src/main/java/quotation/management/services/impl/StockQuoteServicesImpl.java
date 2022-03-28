package quotation.management.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import quotation.management.domain.entity.StockQuote;
import quotation.management.externalprovider.StockManagerClient;
import quotation.management.repository.StockQuoteRepository;
import quotation.management.services.StockQuoteServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StockQuoteServicesImpl implements StockQuoteServices {

    @Autowired
    private StockQuoteRepository repository;

    @Autowired
    private StockManagerClient stockManagerClient;

    public StockQuote save(StockQuote stockQuote) {
        validateStock(stockQuote);
        return saveStock(stockQuote);
    }

    public List<StockQuote> findAll() {
        return repository.findAll();
    }

    public StockQuote findByStockId(String stockId) {
        return repository.findByStockId(stockId);
    }

    private StockQuote saveStock(StockQuote stockQuote) {
        StockQuote savedStockQuote = findByStockId(stockQuote.getStockId());
        if (savedStockQuote == null) {
            return repository.save(stockQuote);
        }
        filterNewQuotes(savedStockQuote, stockQuote);
        return repository.save(savedStockQuote);
    }

    private void validateStock(StockQuote stockQuote) {
        stockManagerClient.validateStock(stockQuote.getStockId());
    }

    private void filterNewQuotes(StockQuote savedStockQuote, StockQuote stockQuote) {
        Map<String, String> savedQuotes = savedStockQuote.getQuotes();
        Map<String, String> incomingQuotes = stockQuote.getQuotes();
        Map<String, String> quotesToBeSaved = new HashMap<>();
        incomingQuotes.forEach((key, value) -> {
            if (!savedQuotes.containsKey(key)) {
                quotesToBeSaved.put(key, value);
            }
        });
        savedStockQuote.getQuotes().putAll(quotesToBeSaved);
    }

}
