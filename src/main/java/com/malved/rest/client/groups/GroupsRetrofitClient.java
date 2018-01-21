package com.malved.rest.client.groups;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GroupsRetrofitClient {

    @POST("groups")
    Call<Group> create(@Body final Group user);

    @GET("groups/{id}")
    Call<Group> get(@Path("id") final Long id);

    @PUT("groups/{id}")
    Call<Group> update(@Path("id") final Long id, @Body final Group group);

    @DELETE("groups/{id}")
    Call<Group> delete(@Path("id") final Long id);

    @GET("groups")
    Call<List<Group>> list();
}
