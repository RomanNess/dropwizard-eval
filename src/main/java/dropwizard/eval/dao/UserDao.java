package dropwizard.eval.dao;

import dropwizard.eval.dao.mapper.UserMapper;
import dropwizard.eval.dao.model.User;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

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

    @SqlQuery("select id, username from eval_user where id = :id")
    Optional<User> findNameById(@Bind("id") Long id);

    @Transaction
    default void resetTable() {
        dropTable();
        createTable();
    }

    @Transaction
    default User saveUser(User user) {
        Long newId = getMaxId().orElse(0L) + 1; // fixme poor man's index
        insert(newId, user.getUsername());
        return findNameById(newId).orElse(null);
    }
}
