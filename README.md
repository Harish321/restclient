# Malved REST Java Client

## How to build

Clone

```bash
git clone http://
```

Build

```bash
cd malved-rest-client
mvn clean install
```

## How to setup client

In order to use malved rest client you should use built-in fluent api:

```java
final MalvedClient malvedClient = MalvedClient.builder()
            .hostname("malved.com")
            .port(10080)
            .build();
```

HTTP Basic authentication can be configured as follows:

```java
 final MalvedClient malvedClient = MalvedClient.builder()
            .hostname("malved.com")
            .port(10080)
            .credentials(new Credentials("name", "password"))
            .build();
```
HTTPS configuration:

```java
final MalvedClient malvedClient = MalvedClient.builder()
            .hostname("malved.com")
            .port(10443)
            .credentials(new Credentials("name", "password"))
            .sslCertificate(Paths.get("assets/malved.cer"))
            .build();
```

## How to use

Create user:

```java
final User user = malvedClient
            .users()
            .create(new User("name", "password"));
```

Get user by id:

```java
final User user = malvedClientWithAuthentication
            .users()
            .get(12L);
```

Get all users:

```java
final List<User> users = malvedClient
            .users()
            .list();
```



