package org.pet.groupchat.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "name", nullable = false)
    private String name;
}
