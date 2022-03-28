package quotation.management.domain.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "stock_quote")
public class StockQuote {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "", updatable = false, nullable = false, unique = true)
    private String id;

    @Column(name = "stock_id")
    private String stockId;

    @ElementCollection
    @CollectionTable(name="quote")
    @Column(name="value")
    @MapKeyColumn(name="date")
    private Map<String, String> quotes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Map<String, String> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, String> quotes) {
        this.quotes = quotes;
    }
}
