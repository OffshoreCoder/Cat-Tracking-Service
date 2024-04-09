package DAO;

import Entities.Owner;

import java.util.List;

public interface OwnerDao {
    List findAll();
    Owner findById(Long id);
    void save(Owner owner);
    void deleteById(Long id);
}
