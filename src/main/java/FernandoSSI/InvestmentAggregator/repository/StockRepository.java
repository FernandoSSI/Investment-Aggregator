package FernandoSSI.InvestmentAggregator.repository;

import FernandoSSI.InvestmentAggregator.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
}
