package quotation.management.services;

import quotation.management.domain.entity.StockQuote;

import java.util.List;

public interface StockQuoteServices {

    StockQuote save(StockQuote stockQuote);

    List<StockQuote> findAll();

    StockQuote findByStockId(String stockId);

}
