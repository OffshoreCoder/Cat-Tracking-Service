package org.example.DAO;

import org.example.Entities.Owner;

import java.util.List;

public interface OwnerDao {
    List findAll();
    Owner findById(Long id);
    void save(Owner owner);
    void deleteById(Long id);
}
