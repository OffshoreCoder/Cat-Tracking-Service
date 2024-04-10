package org.example.DAO;

public interface CatFriendDao {
    void addFriendship(Long catId, Long friendId);
    void removeFriendship(Long catId, Long friendId);
}
