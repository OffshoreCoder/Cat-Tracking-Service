package org.example.Entities.models;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "Owners")
public class Owner {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "owner_id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    public Owner() {
    }

    public Owner(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}
