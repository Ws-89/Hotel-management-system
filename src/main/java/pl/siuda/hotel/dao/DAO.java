package pl.siuda.hotel.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    List<T> findAll();
    int create(T t);
    Optional<T> findById(Long id);
    int update(T t, Long id);
    int delete(Long id);



}
