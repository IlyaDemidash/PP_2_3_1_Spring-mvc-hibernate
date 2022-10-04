package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    /*
    Аннотация @PersistenceContext внедряет прокси,
    который выполняет открытие и закрытие EntityManager автоматически.
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void updateUser(int id, User updUser) {
        User modifiedUser = entityManager.find(User.class, id);
        modifiedUser.setName(updUser.getName());
        modifiedUser.setEmail(updUser.getEmail());
        modifiedUser.setAge(updUser.getAge());
        entityManager.merge(modifiedUser);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUserList() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }
}
