package FernandoSSI.InvestmentAggregator.repository;

import FernandoSSI.InvestmentAggregator.entity.AccountStock;
import FernandoSSI.InvestmentAggregator.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
