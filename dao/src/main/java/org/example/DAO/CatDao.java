package org.example.DAO;

import org.example.Entities.Cat;

import java.util.List;
import java.util.Set;

public interface CatDao {
    List findAll();
    Cat findById(Long id);
    void update(Cat cat);
    void save(Cat cat);
    void deleteById(Long id);
    Set<Cat> findFriendsByCatId(Long id);
}
