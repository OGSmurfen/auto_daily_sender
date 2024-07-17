package Services;

import RepositoryImplementation.UserRepositoryImpl;
import entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UserService {
    @Inject
    private UserRepositoryImpl userRepository;

    public void createUser(User user){
        userRepository.create(user);
    }
    public List<User> getAllByUsername(String username){
        return userRepository.getAllByUsername(username);//really there shouldn't be more than 1 with same username
    }
    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }
    public List<User> getAll(){
        return userRepository.getAll();
    }
}
