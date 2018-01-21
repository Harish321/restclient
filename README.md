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

## Examples of usage

Create user:

```java
final User user = malvedClient
            .users()
            .create(new User("name", "password"));
```

Get user by id:

```java
final User user = malvedClient
            .users()
            .delete(12L);
```

Get all groups:

```java
final List<Group> groups = malvedClient
            .groups()
            .list();
```

Update group

```java
final Group group = malvedClient
            .groups()
            .update(3L, new Group("name"));
```



