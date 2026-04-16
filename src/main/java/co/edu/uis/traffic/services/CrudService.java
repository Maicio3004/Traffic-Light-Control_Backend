package co.edu.uis.traffic.services;

import java.util.List;

public interface CrudService<E> {

    E create(E e);
    void delete(E e);
    List<E> findAll();
    E findById(Number id);

}
