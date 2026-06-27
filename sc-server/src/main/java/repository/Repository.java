package repository;

import java.util.List;

public interface Repository<T> {
    List<T> getAll(T param, String uslov) throws Exception;
    void add(T param) throws Exception;

    default int addAndReturnId(T param) throws Exception {
        add(param);
        return -1;
    }

    void edit(T param) throws Exception;
    void delete(T param) throws Exception;
    List<T> getAll();
}
