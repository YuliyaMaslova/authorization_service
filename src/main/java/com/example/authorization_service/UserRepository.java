package com.example.authorization_service;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<String, User> users = Map.of(
            "yulia", new User("123", List.of(Authorities.READ)),
            "dmitrii", new User("321", List.of(Authorities.WRITE, Authorities.DELETE)),
            "dmitrii2", new User("321", List.of())
    );

    public List<Authorities> getUserAuthorities(String username, String password) {
        User user = users.get(username);

        if (user == null || !user.password.equals(password)) {
            throw new InvalidCredentials("Credentials are wrong");
        }

        return user.authorities;
    }

    private static class User {
        String password;
        List<Authorities> authorities;


        public User(String password, List<Authorities> authorities) {
            this.password = password;
            this.authorities = authorities;
        }
    }
}
