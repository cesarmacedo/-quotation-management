package quotation.management.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import quotation.management.domain.entity.StockQuote;
import quotation.management.services.impl.StockQuoteServicesImpl;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StockQuoteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockQuoteServicesImpl stockQuoteServices;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testInsertStockQuotes() throws Exception {
        StockQuote stockQuote = createStockQuote();
        whenFindByStockId();
        whenCreateStockQuote(stockQuote);
        String json = mapper.writeValueAsString(stockQuote);

        mockMvc.perform(post("/stockQuotes/").
                        contentType(MediaType.APPLICATION_JSON).
                        characterEncoding("utf-8").
                        content(json).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }

    private void whenFindByStockId(){
        when(stockQuoteServices.findByStockId(anyString())).thenReturn(new StockQuote());
    }

    private void whenCreateStockQuote(StockQuote stockQuote) {
        when(stockQuoteServices.save(any())).thenReturn(stockQuote);
    }

    private StockQuote createStockQuote() {
        StockQuote stockQuote = new StockQuote();
        stockQuote.setId("1q2w3e4r5t");
        stockQuote.setStockId("petr4");
        Map<String, String> quote = new HashMap<>();
        quote.put("2022-03-26", "777");
        stockQuote.setQuotes(quote);
        return stockQuote;
    }

}
