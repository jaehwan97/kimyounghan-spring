# Spring 3.x 버전 오류

스프링 학습을 위해  3.1.3 버전을 내려받았더니 실행도 하기 전에 버전 충돌이 일어났다. 

자세히 보면 java sourceCompatibility 속성 값이 17로 고정되어 있는 것을 발견할 수 있었다.

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.1.3'
    id 'io.spring.dependency-management' version '1.1.3'
}

group = 'test'
version = '0.0.1-SNAPSHOT'

java {
    sourceCompatibility = '17'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

&nbsp;

## Spring 2.7.x 다운그레이드

지금 사용하고 있는 jdk 11 버전으로 변경하고  sourceCompatibility 값을 11로 수정했지만 여전히 오류가 발생했다.

그래서 오류 관련해서 구글링을 했더니 **스프링 부트 3.0 버전 이상부터는 Java 17 이상을 지원**한다고 하더라…

Java 11을 사용할 수 있는 스프링 부트 버전으로 변경하여 실행했더니 잘 동작했다. (현재 시점에선 2.7.15만 있는 것 같다.)

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '2.7.15'
    id 'io.spring.dependency-management' version '1.0.15.RELEASE'
}

group = 'test'
version = '0.0.1-SNAPSHOT'

java {
    sourceCompatibility = '11'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

&nbsp;

## Spring 3.x 사용하고 싶은데…

Java 17은 Java 11 이후의 새로운 LTS(장기 지원) 릴리스를 지원한다고 한다.

시간이 지날 수록 세대 교체가 될텐데 지금 미리 알아둬야 할 것 같아서 Spring 3.x 버전에서 의 환경설정 시 변경사항을 정리하고자 한다.

1. Java 17 이상인지 확인하기
2. javax 패키지 이름이 jakarta로 변경되어있는지 확인하기
    - 오라클과 자바 라이센스 문제로 모든 javax 패키지를 jakarta로 변경하기로 했다고 한다.
    - `**javax**.persistence.Entity` → `**jakarta**.persistence.Entity`
    - `**javax**.annotation.PostConstruct` → `**jakarta**.annotation.PostConstruct`
    - `**javax**.validation` → `**jakarta**.validation`

&nbsp;

[**jakarta가 뭔데?**](https://www.samsungsds.com/kr/insights/java_jakarta.html)