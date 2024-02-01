package FernandoSSI.InvestmentAggregator.Service;

import FernandoSSI.InvestmentAggregator.entity.Dto.UpdateUserDto;
import FernandoSSI.InvestmentAggregator.entity.Dto.UserDto;
import FernandoSSI.InvestmentAggregator.entity.User;
import FernandoSSI.InvestmentAggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public UUID createUser(UserDto userDto){

        var entity = new User(
                UUID.randomUUID(),
                userDto.username(),
                userDto.email(),
                userDto.password(),
                Instant.now(),
                null);

        var userSaved = repo.save(entity);
        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId){
        return repo.findById(UUID.fromString(userId));
    }

    public List<User> listUsers(){
        return repo.findAll();
    }

    public void UpdateUser(String userId, UpdateUserDto updateUserDto){

        var id = UUID.fromString(userId);
        var userEntity = repo.findById(id);

        if(userEntity.isPresent()){
            var user = userEntity.get();

            if(updateUserDto.username() != null){
               user.setUsername(updateUserDto.username());
            }

            if(updateUserDto.password() != null){
                user.setPassword(updateUserDto.password());
            }

            repo.save(user);
        }
    }

    public void deleteById (String userId){
        var id = UUID.fromString(userId);
        var userExists = repo.existsById(id);

        if(userExists){
            repo.deleteById(id);
        }
    }
}
