package FernandoSSI.InvestmentAggregator.controller;

import FernandoSSI.InvestmentAggregator.Service.AccountService;
import FernandoSSI.InvestmentAggregator.entity.Dto.AccountStockResponseDto;
import FernandoSSI.InvestmentAggregator.entity.Dto.AssociateAccountStockDto;
import FernandoSSI.InvestmentAggregator.entity.Dto.CreateAccountDto;
import FernandoSSI.InvestmentAggregator.entity.Dto.CreatedStockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Void> associateStock(@PathVariable String accountId,
                                              @RequestBody AssociateAccountStockDto dto){
        accountService.associateStock(accountId, dto);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountStockResponseDto>> associateStock(@PathVariable("accountId") String accountId) {

        var stocks = accountService.listStocks(accountId);

        return ResponseEntity.ok(stocks);
    }
}

