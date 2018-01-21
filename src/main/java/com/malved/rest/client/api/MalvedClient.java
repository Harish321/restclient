package com.malved.rest.client.api;

import com.malved.rest.client.groups.Groups;
import com.malved.rest.client.groups.GroupsRetrofitClient;
import com.malved.rest.client.retrofit.OkHttpClient;
import com.malved.rest.client.users.Users;
import com.malved.rest.client.users.UsersRetrofitClient;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Objects;

@RequiredArgsConstructor
@Builder
public class MalvedClient {

    private final Retrofit retrofit;
    private String hostname;
    private Integer port;
    private Credentials credentials;
    private Path sslCertificate;

    public static class MalvedClientBuilder {
        public MalvedClient build() {
            final OkHttpClient.OkHttpClientBuilder okHttpClientBuilder = OkHttpClient.builder();
            okHttpClientBuilder.trustedHosts(Collections.singletonList(hostname));
            if (Objects.nonNull(credentials)) {
                okHttpClientBuilder.credentials(
                    new OkHttpClient.Credentials(credentials.username, credentials.password)
                );
            }
            String scheme;
            if (Objects.nonNull(sslCertificate)) {
                okHttpClientBuilder.certificatePath(sslCertificate);
                scheme = "https";
            } else {
                scheme = "http";
            }
            retrofit = new Retrofit.Builder()
                .baseUrl(String.format("%s://%s:%d", scheme, hostname, port))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build()).build();
            return new MalvedClient(retrofit);
        }
    }

    @Data
    public static class Credentials {
        private final String username;
        private final String password;
    }

    public final Users users() {
        return new Users(retrofit.create(UsersRetrofitClient.class));
    }

    public final Groups groups() {
        return new Groups(retrofit.create(GroupsRetrofitClient.class));
    }
}
