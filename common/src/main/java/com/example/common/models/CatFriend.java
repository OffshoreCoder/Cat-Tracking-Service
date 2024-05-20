package com.example.common.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "cat_friends")
public class CatFriend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat cat;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private Cat friendCat;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Cat getFriendCat() {
        return friendCat;
    }

    public void setFriendCat(Cat friendCat) {
        this.friendCat = friendCat;
    }
}
