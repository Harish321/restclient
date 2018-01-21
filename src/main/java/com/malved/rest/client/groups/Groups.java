package com.malved.rest.client.groups;

import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class Groups {

    private final GroupsRetrofitClient groupsRetrofitClient;

    @SneakyThrows(IOException.class)
    public Group create(final Group user){
        return groupsRetrofitClient.create(user).execute().body();
    }

    @SneakyThrows(IOException.class)
    public Group get(final Long id){
        return groupsRetrofitClient.get(id).execute().body();
    }

    @SneakyThrows(IOException.class)
    public Group update(final Long id, final Group group){
        return groupsRetrofitClient.update(id, group).execute().body();
    }

    @SneakyThrows(IOException.class)
    public Group delete(final Long id){
        return groupsRetrofitClient.delete(id).execute().body();
    }

    @SneakyThrows(IOException.class)
    public List<Group> list(){
        return groupsRetrofitClient.list().execute().body();
    }

}
