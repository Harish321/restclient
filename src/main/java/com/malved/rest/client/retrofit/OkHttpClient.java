package com.malved.rest.client.retrofit;

import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.List;
import java.util.Objects;

@Builder
public class OkHttpClient {

    private List<String> trustedHosts;
    private Credentials credentials;
    private Path certificatePath;

    public static class OkHttpClientBuilder {
        public okhttp3.OkHttpClient build() {
            okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder()
                .hostnameVerifier((hostname, session) -> trustedHosts.contains(hostname));
            if(Objects.nonNull(credentials)){
                builder.addInterceptor(new BasicAuthInterceptor(credentials.username, credentials.password));
            }
            if(Objects.nonNull(certificatePath)){
                builder.sslSocketFactory(setupSslContext(certificatePath).getSocketFactory());
            }
            return builder.build();
        }
    }

    @Data
    public static class Credentials {
        private final String username;
        private final String password;
    }

    @SneakyThrows({
        CertificateException.class, IOException.class, KeyStoreException.class,
        NoSuchAlgorithmException.class, KeyManagementException.class
    })
    private static SSLContext setupSslContext(final Path certificatePath){
        try (InputStream certResource = Files.newInputStream(certificatePath)) {
            final CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            final Certificate certificate = certificateFactory.generateCertificate(certResource);

            final String keyStoreType = KeyStore.getDefaultType();
            final KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", certificate);

            final String trustManagerFactoryAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            final TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(trustManagerFactoryAlgorithm);
            trustManagerFactory.init(keyStore);

            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
            return sslContext;
        }
    }
}
