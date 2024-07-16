package IRepository;

import entity.User;

import java.util.List;

public interface IUserRepository extends Repository<User, Long>{
    public List<User> getAll();
    public User getById(Long id);
    public User create(User entity);
    public User update(User entity);
    public User delete(User entity);
    public List<User> getByUsername(String username);
}
