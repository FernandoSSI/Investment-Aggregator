package FernandoSSI.InvestmentAggregator.repository;

import FernandoSSI.InvestmentAggregator.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, String> {
}
