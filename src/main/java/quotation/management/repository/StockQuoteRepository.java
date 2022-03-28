package quotation.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quotation.management.domain.entity.StockQuote;

public interface StockQuoteRepository extends JpaRepository<StockQuote, String> {
    StockQuote findByStockId(String stockId);
}
