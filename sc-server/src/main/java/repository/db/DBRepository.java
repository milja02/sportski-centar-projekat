package repository.db;

import repository.Repository;

public interface DBRepository<T> extends Repository<T> {
    default void connect() throws Exception {
        DBConnectionFactory.getInstance().getConnection();
    }

    default void disconnect() throws Exception {
        DBConnectionFactory.getInstance().getConnection().close();
    }

    default void commit() throws Exception {
        DBConnectionFactory.getInstance().getConnection().commit();
    }

    default void rollback() throws Exception {
        DBConnectionFactory.getInstance().getConnection().rollback();
    }
}
