package quotation.management.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quotation.management.domain.entity.StockQuote;
import quotation.management.services.StockQuoteServices;

import java.util.List;

@RestController
@RequestMapping("/stockQuotes")
public class StockQuoteController {

    @Autowired
    private StockQuoteServices service;

    @PostMapping(value = "/")
    public StockQuote save (@RequestBody StockQuote stockQuotes) {
        return service.save(stockQuotes);
    }

    @GetMapping(value = "/")
    public List<StockQuote> getAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{stockId}")
    public StockQuote getById(@PathVariable String stockId) {
        return service.findByStockId(stockId);
    }

}
