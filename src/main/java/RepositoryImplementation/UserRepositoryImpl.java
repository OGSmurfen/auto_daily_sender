package RepositoryImplementation;

import IRepository.IUserRepository;
import entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserRepositoryImpl implements IUserRepository {
    @PersistenceContext //injected entity manager
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public User create(User entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public User delete(User entity) {
        return null;
    }

    @Override
    public List<User> getAllByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        try{
            List<User> retrievedUsers = query.getResultList();
            return retrievedUsers;
        }catch (NoResultException e){
            return null;
        }
    }
    @Override
    public User getUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        try{
            User retrievedUser = query.getSingleResult();
            return retrievedUser;
        }catch (NoResultException e){
            return null;
        }
    }
}
