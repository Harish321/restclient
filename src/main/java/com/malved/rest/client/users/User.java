package com.malved.rest.client.users;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {

    private final Long id;
    private final String type;
    private final String link;
    private final String created;
    private final String modified;
    private final String access;
    private final String name;
    private final String password;
    private final String secret;

    public User(final String name, final String password) {
        this(null, null, null, null, null, null, name, password, null);
    }
}
