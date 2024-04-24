package org.example.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CatFriends")
public class CatFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Long id;

    @Column(name = "friend_id")
    private Long friendId;


    public CatFriend() {}

    public CatFriend(Long catId, Long friendId) {
        this.id = catId;
        this.friendId = friendId;
    }
}
