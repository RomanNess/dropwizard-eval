package dropwizard.eval.service;

import dropwizard.eval.dao.UserDao;
import dropwizard.eval.dao.model.User;

import java.util.List;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(Long userId) {
        return userDao.findUserById(userId).orElse(null);
    }

    public List<User> getAllUsers() {
        return userDao.findAllUsers();
    }

    public User saveUser(User user) {
        return userDao.saveUser(user);
    }

    public User updateUser(Long userId, User user) {
        user.setId(userId);
        return userDao.updateUser(user);
    }

    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);
    }
}
