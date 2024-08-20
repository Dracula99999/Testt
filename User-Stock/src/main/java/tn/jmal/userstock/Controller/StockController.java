package tn.jmal.userstock.Controller;
import tn.jmal.userstock.Entities.Stock;
import tn.jmal.userstock.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        Optional<Stock> stock = stockService.getStockById(id);
        return stock.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Stock createStock(@RequestBody Stock stock) {
        return stockService.saveStock(stock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody Stock stock) {
        if (!stockService.getStockById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        stock.setId(id);
        return ResponseEntity.ok(stockService.saveStock(stock));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        if (!stockService.getStockById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}