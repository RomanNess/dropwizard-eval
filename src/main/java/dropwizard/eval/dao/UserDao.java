package dropwizard.eval.dao;

import dropwizard.eval.dao.mapper.UserMapper;
import dropwizard.eval.dao.model.User;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;
import java.util.Optional;

@RegisterRowMapper(UserMapper.class)
public interface UserDao {
    @SqlUpdate("drop table if exists eval_user")
    void dropTable();

    @SqlUpdate("create table eval_user (id int primary key, username varchar(100))")
    void createTable();

    @SqlUpdate("insert into eval_user (id, username) values (:id, :username)")
    void insert(@Bind("id") Long id, @Bind("username") String username);

    @SqlQuery("select max(id) from eval_user")
    Optional<Long> getMaxId();

    @SqlQuery("select * from eval_user order by id asc")
    List<User> findAllUsers();

    @SqlQuery("select id, username from eval_user where id = :id")
    Optional<User> findUserById(@Bind("id") Long id);

    @SqlUpdate("update eval_user set username = :username where id = :id")
    void updateUser(@Bind("id") Long id, @Bind("username") String username);

    @SqlUpdate("delete from eval_user where id = :id")
    void deleteUser(@Bind("id") Long userId);

    @Transaction
    default void resetTable() {
        dropTable();
        createTable();
    }

    @Transaction
    default User saveUser(User user) {
        Long newId = getMaxId().orElse(0L) + 1; // fixme poor man's index
        insert(newId, user.getUsername());
        return findUserById(newId).orElse(null);
    }

    @Transaction
    default User updateUser(User user) {
        Optional<User> persistedUser = findUserById(user.getId());
        if (persistedUser.isPresent()) {
            updateUser(user.getId(), user.getUsername());
            return user;
        } else {
            return saveUser(user);
        }
    }
}
