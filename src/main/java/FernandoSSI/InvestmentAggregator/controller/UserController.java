package FernandoSSI.InvestmentAggregator.controller;

import FernandoSSI.InvestmentAggregator.Service.UserService;
import FernandoSSI.InvestmentAggregator.entity.Dto.*;
import FernandoSSI.InvestmentAggregator.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        var userId = service.createUser(userDto);
        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
        var user = service.getUserById(userId);

        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers(){
        var users = service.listUsers();

        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
        service.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable String userId, @RequestBody UpdateUserDto updateUser){
        service.UpdateUser(userId, updateUser);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<List<Void>> CreateAccount(@PathVariable String userId,
                                                                       @RequestBody CreateAccountDto createAccountDto){
        service.createAccount(userId, createAccountDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDto>> listAccounts(@PathVariable String userId){
        var accounts = service.listAccounts(userId);

        return ResponseEntity.ok(accounts);
    }
}
