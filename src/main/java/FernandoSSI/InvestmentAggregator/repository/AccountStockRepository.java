package FernandoSSI.InvestmentAggregator.repository;

import FernandoSSI.InvestmentAggregator.entity.AccountStock;
import FernandoSSI.InvestmentAggregator.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
