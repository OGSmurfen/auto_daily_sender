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
    public List<User> findAllByUsername(String username){
        return userRepository.getByUsername(username);
    }
}
