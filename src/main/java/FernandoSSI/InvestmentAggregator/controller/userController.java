package FernandoSSI.InvestmentAggregator.controller;

import FernandoSSI.InvestmentAggregator.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class userController {

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody String body){

        return null;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){

        return null;
    }
}
