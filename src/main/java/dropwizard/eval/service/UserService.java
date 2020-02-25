package dropwizard.eval.service;

import dropwizard.eval.dao.UserDao;
import dropwizard.eval.dao.model.User;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(Long userId) {
        return userDao.findNameById(userId).orElse(null);
    }

    public User saveUser(User user) {
        return userDao.saveUser(user);
    }
}
