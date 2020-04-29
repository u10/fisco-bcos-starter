# spring-boot-fisco-bcos-starter

This a fisco bcos starter for Spring Boot.

## How to use

In Application class:

```java
import io.github.u10.fiscobcosstarter.annotation.EnableFiscoBcos;

@SpringBootApplication
@EnableFiscoBcos
```

In your application.yml:

```yml
fisco-bcos:
  connect:
    connect-seconds: 30
    connect-sleep-per-millis: 1
    timeout: 30000

  gas:
    gas-price: 100000000
    gas-limit: 100000000

  encrypt-type:
    encrypt-type: 0

  group-channel-connections-config:
    all-channel-connections:
      - group-id: 1
        connections-str:
          - 1.2.3.4:12345  # node: listen_ip:channel_listen_port
          - 1.2.3.4:12345

  channel-service:
    group-id: 1
    agency-name: base

  accounts:
    # pem-file: 'classpath:pemFile/in/resources/dir'
    # p12-file: 'classpath:p12File/in/resources/dir'
    # p12-file: '/absolute/path/to/p12File'
    p12-file: 'p12File/in/application/dir'
    password: 'yourP12FilePassword'

```
