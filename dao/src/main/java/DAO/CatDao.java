package DAO;

import Entities.Cat;
import Entities.Owner;

import java.util.List;

public interface CatDao {
    List findAll();
    Cat findById(Long id);
    void save(Cat cat);
    void deleteById(Long id);
    List findCatsByOwner(Owner owner);
    List findFriendsByCatId(Long id);
}
