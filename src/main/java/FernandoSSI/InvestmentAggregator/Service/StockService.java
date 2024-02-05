package FernandoSSI.InvestmentAggregator.Service;

import FernandoSSI.InvestmentAggregator.entity.Dto.CreatedStockDto;
import FernandoSSI.InvestmentAggregator.entity.Stock;
import FernandoSSI.InvestmentAggregator.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public void createStock(CreatedStockDto createdStockDto) {
        var stock = new Stock(
                createdStockDto.stockId(),
                createdStockDto.description()
        );

        stockRepository.save(stock);
    }
}
