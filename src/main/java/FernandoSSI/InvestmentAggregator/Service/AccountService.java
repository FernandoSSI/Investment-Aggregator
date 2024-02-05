package FernandoSSI.InvestmentAggregator.Service;

import FernandoSSI.InvestmentAggregator.client.BrapiClient;
import FernandoSSI.InvestmentAggregator.entity.AccountStock;
import FernandoSSI.InvestmentAggregator.entity.AccountStockId;
import FernandoSSI.InvestmentAggregator.entity.Dto.AccountStockResponseDto;
import FernandoSSI.InvestmentAggregator.entity.Dto.AssociateAccountStockDto;
import FernandoSSI.InvestmentAggregator.repository.AccountRepository;
import FernandoSSI.InvestmentAggregator.repository.AccountStockRepository;
import FernandoSSI.InvestmentAggregator.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Value("#{environment.TOKEN}")
    private String TOKEN;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private AccountStockRepository accountStockRepository;
    @Autowired
    private BrapiClient brapiClient;


    public void associateStock(String accountId, AssociateAccountStockDto dto) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        var stock = stockRepository.findById(dto.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var id = new AccountStockId(account.getAccountId(), stock.getStockId());
        var entity = new AccountStock(
                id,
                account,
                stock,
                dto.quantity()
        );

        accountStockRepository.save(entity);
    }

    public List<AccountStockResponseDto> listStocks(String accountId) {

        var account = accountRepository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

         return account.getAccountStocks().
                 stream().
                 map(as ->
                         new AccountStockResponseDto(
                                 as.getStock().getStockId(),
                                 as.getQuantity(),
                                 getTotal(as.getQuantity(), as.getStock().getStockId()))).
                 toList();
    }

    private double getTotal(Integer quantity, String stockId) {

        var response = brapiClient.getQuote(TOKEN, stockId);

        var price = response.results().get(0).regularMarketPrice();

        return quantity * price;
    }
}
