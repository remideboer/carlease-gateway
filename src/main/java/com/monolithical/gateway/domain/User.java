package com.monolithical.gateway.domain;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String hash;

    public String getUsername() {
        return username;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
