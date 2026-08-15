package repository.db;

import java.sql.Connection;
import repository.Repository;

public interface DBRepository<T> extends Repository<T> {
    default void connect() throws Exception {
        DBConnectionFactory.getInstance().requireConnection();
    }

    default void disconnect() throws Exception {
        Connection c = DBConnectionFactory.getInstance().requireConnection();
        c.close();
    }

    default void commit() throws Exception {
        Connection c = DBConnectionFactory.getInstance().requireConnection();
        c.commit();
    }

    default void rollback() throws Exception {
        Connection c = DBConnectionFactory.getInstance().requireConnection();
        c.rollback();
    }
}
