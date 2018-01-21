package com.malved.rest.client.users;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import java.io.IOException;


@AllArgsConstructor
public class Users {

    private final UsersRetrofitClient usersRetrofitClient;

    @SneakyThrows(IOException.class)
    public User create(final User user) {
        return usersRetrofitClient.create(user).execute().body();
    }

    @SneakyThrows(IOException.class)
    public User get(final Long id) {
        return usersRetrofitClient.get(id).execute().body();
    }

    @SneakyThrows(IOException.class)
    public User update(final Long id, final User user) {
        return usersRetrofitClient.update(id, user).execute().body();
    }

    @SneakyThrows(IOException.class)
    public User delete(final Long id){
        return usersRetrofitClient.delete(id).execute().body();
    }

    @SneakyThrows(IOException.class)
    public List<User> list(){
        return usersRetrofitClient.list().execute().body();
    }
}