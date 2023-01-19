package ma.enset.tp_jdbc.dao;
import java.util.List;

public interface Dao <T>{
    List<T> findAll();
    T findById(int id);
    T save(T o);
    boolean delete(T o);
    T update(T o);
}
