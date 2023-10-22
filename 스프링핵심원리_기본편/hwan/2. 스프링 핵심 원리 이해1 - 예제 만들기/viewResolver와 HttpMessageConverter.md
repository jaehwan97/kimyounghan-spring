# viewResolver

Spring은 가장 먼저 **ViewResolver**에게 hello.html을 찾도록 명령한다. (hello.html로 반환해도 됨.)

```java
@GetMapping("hello")
public String hello() {
    return "hello";   // hello.html
}
```

&nbsp;

만약 존재하지 않는 정적 컨텐츠를 반환하게 되면 어떻게 될까?

```java
@GetMapping("hello")
public String hello() {
    return "hello2";   // hello2.html
}
```

IntelliJ는 똑똑하게도 viewResolver가 똑바로 수행하지 못 할 것이라고 경고를 해준다.

Spring은 기본적으로 정적 컨텐츠를 찾아내도록 설계되어 있다.

&nbsp;

# @ResponseBody

하지만 우리는 다른 다양한 데이터 타입으로 반환하고 싶을 때가 있다.

**@ResponseBody**를 사용하면 HTTP 요청의 body에 반환 값을 매핑하여 클라이언트로 전송해준다.

```java
@GetMapping("hello")
@ResponseBody
public String helloString(@RequestParam("name") String name) {
    return "hello " + name;     // "hello {name}"
}
```

즉, viewResolver에 의해 정적 컨텐츠가 반한되지 않기 때문에 브라우저에서 소스 코드를 확인하면 HTML 구조를 확인할 수 없을 것이다. 

그래서 오직 문자열만 응답하는 것을 확인할 수 있다.

&nbsp;

# HttpMessageConverter

@ResponseBody가 추가된 순간부터는 viewResolver를 거치지 않고 **HttpMessageConverter**가 동작한다.

HttpMessageConverter는 다양한 객체 타입을 HTTP body에 매핑하여 응답으로 보낼 수 있다.

아래 순서대로 우선순위가 존재하고 대상 클래스 타입과 미디어 타입을 체크해서 사용 여부를 판단한다. 만약 만족하지 않으면 다음 우선순위로 넘어간다.

&nbsp;

### ByteArrayHttpMessageConverter

모든 종류의 HTTP 요청 메세지 본문을 byte 배열로 가지고 올 수 있다.

- 클래스 타입: byte[]
- 미디어타입: */*,
- @RequestBody **byte[]** data
- @ResponseBody return **{0x66, 0x67, 0x68, 0x69}**;
- Content-Type: **application/octect-stream**

&nbsp;

### StringHttpMessageConverter

HTTP 요청의 본문을 그대로 스트링 타입으로 가져올 수 있다.

- 클래스 타입: String
- 미디어타입: */*
- @RequestBody **String** data
- @ResponseBody return **“hello”**
- Content-Type: **text/plain**

&nbsp;

### FormHttpMessageConverter

정의된 form 데이터를 주고받을 때 사용할 수 있다.

- 클래스 타입: MultiValueMap<String, String>
- 미디어 타입: application/x-www-form-urlencoded

&nbsp;

### MappingJackson2HttpMessageConverter

Jackson ObjectMapper를 이용하여 Java object를 JSON로 자동으로 변환해준다.

- 클래스 타입: 객체 또는 HashMap
- 미디어타입: application/json
- @RequestBody **Hello** data
- @ResponseBody return **Hello**
- Content-Type: **application/json**

&nbsp;

### ResourceHttpMessageConverter

모든 유형의 octet stream에 대해 org.springframework.core.io.Resource로 변환해준다.