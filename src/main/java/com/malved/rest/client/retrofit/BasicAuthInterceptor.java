package com.malved.rest.client.retrofit;

import lombok.SneakyThrows;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class BasicAuthInterceptor implements Interceptor {

    private String credentials;

    public BasicAuthInterceptor(final String user, final String password) {
        this.credentials = Credentials.basic(user, password);
    }

    @Override
    @SneakyThrows(IOException.class)
    public Response intercept(Chain chain) {
        final Request authenticatedRequest = chain.request()
            .newBuilder()
            .header("Authorization", credentials)
            .build();
        return chain.proceed(authenticatedRequest);
    }

}
