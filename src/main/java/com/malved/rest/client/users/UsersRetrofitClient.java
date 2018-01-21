package com.malved.rest.client.users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface UsersRetrofitClient {

    @POST("users")
    Call<User> create(@Body final User user);

    @GET("users/{id}")
    Call<User> get(@Path("id") final Long id);

    @PUT("users/{id}")
    Call<User> update(@Path("id") final Long id, @Body final User user);

    @DELETE("users/{id}")
    Call<User> delete (@Path("id") final Long id);

    @GET("users")
    Call<List<User>> list();
}