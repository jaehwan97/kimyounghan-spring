# [mariaDB 설치](https://mariadb.org/download/?t=mariadb&p=mariadb&r=11.1.2&os=windows&cpu=x86_64&pkg=msi&m=blendbyte)

기존에 사용하던 mariaDB를 삭제하고 재설치했다.

삭제 프로그램으로 mariaDB가 제대로 삭제가 되지 않자 무작정 mariaDB 관련 폴더만 삭제했더니 재설치 과정에서 **`a service with the same name already exists`** 오류가 발생했다.

아래와 같이 sc(service controller)로 서비스를 삭제해주니 설치가 정상적으로 되었다.
cmd는 관리자 권한으로 실행해야 한다.

```bash
# Windows 기준
sc delete MariaDB
```

&nbsp;
# Spring+mariaDB 연동

**JDBC**는 자바에서 DB에 접속할 수 있도록 하는 자바 API이다. JDBC는 데이터베이스에서 자료를 query하거나 update하는 방법을 제공한다.

- dependency 추가 (gradle 기준)

```groovy
// implementation 'org.springframework.boot:spring-boot-starter-jdbc'
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
runtimeOnly 'org.mariadb.jdbc:mariadb-java-client'
```

- application.yaml 수정

```yaml
spring:
	datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://localhost:3306/{DB명}
    username: root
    password: root
```