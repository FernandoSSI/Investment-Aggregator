package FernandoSSI.InvestmentAggregator.controller;

import FernandoSSI.InvestmentAggregator.Service.StockService;
import FernandoSSI.InvestmentAggregator.entity.Dto.CreatedStockDto;
import FernandoSSI.InvestmentAggregator.entity.Dto.UserDto;
import FernandoSSI.InvestmentAggregator.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/v1/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody CreatedStockDto createdStockDto){
        stockService.createStock(createdStockDto);
        return ResponseEntity.ok().build();
    }
}
