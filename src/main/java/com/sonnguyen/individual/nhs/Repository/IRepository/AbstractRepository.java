package com.sonnguyen.individual.nhs.Repository.IRepository;


import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T,ID> {
    public List<T> findAll();
    public Optional<T> findById(ID id);
    public void deleteById(ID id);
    public T insert(T object);
    public T update(T object,ID id);
}
