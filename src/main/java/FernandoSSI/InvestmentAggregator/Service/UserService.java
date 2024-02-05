package FernandoSSI.InvestmentAggregator.Service;

import FernandoSSI.InvestmentAggregator.entity.Account;
import FernandoSSI.InvestmentAggregator.entity.BillingAddress;
import FernandoSSI.InvestmentAggregator.entity.Dto.AccountResponseDto;
import FernandoSSI.InvestmentAggregator.entity.Dto.CreateAccountDto;
import FernandoSSI.InvestmentAggregator.entity.Dto.UpdateUserDto;
import FernandoSSI.InvestmentAggregator.entity.Dto.UserDto;
import FernandoSSI.InvestmentAggregator.entity.User;
import FernandoSSI.InvestmentAggregator.repository.AccountRepository;
import FernandoSSI.InvestmentAggregator.repository.BillingAddressRepository;
import FernandoSSI.InvestmentAggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BillingAddressRepository billingAddressRepository;

    public UUID createUser(UserDto userDto){

        var entity = new User(
                UUID.randomUUID(),
                userDto.username(),
                userDto.email(),
                userDto.password(),
                Instant.now(),
                null);

        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId){
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public void UpdateUser(String userId, UpdateUserDto updateUserDto){

        var id = UUID.fromString(userId);
        var userEntity = userRepository.findById(id);

        if(userEntity.isPresent()){
            var user = userEntity.get();

            if(updateUserDto.username() != null){
               user.setUsername(updateUserDto.username());
            }

            if(updateUserDto.password() != null){
                user.setPassword(updateUserDto.password());
            }

            userRepository.save(user);
        }
    }

    public void deleteById (String userId){
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(id);

        if(userExists){
            userRepository.deleteById(id);
        }
    }

    public void createAccount(String userId, CreateAccountDto createAccountDto) {

        var user = userRepository.findById(UUID.fromString(userId)).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var account = new Account(
                UUID.randomUUID(),
                createAccountDto.description(),
                new ArrayList<>(),
                null,
                user
        );

        var accountCreated = accountRepository.save(account);

        var billingAddress = new BillingAddress(
                accountCreated.getAccountId(),
                account,
                createAccountDto.street(),
                createAccountDto.number()
        );

        billingAddressRepository.save(billingAddress);
    }

    public List<AccountResponseDto> listAccounts(String userId) {

        var user = userRepository.findById(UUID.fromString(userId)).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return user.getAccounts()
                .stream()
                .map(ac ->
                new AccountResponseDto(
                        ac.getAccountId().toString(),
                        ac.getDescription()))
                .toList();


    }
}
