# Thymeleaf

### 라이브러리 가져오기

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    ...
}
```

### Thymeleaf 사용하기

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <title>Title</title>
  <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
</head>
<body>
	<p th:text="'안녕하세요.' + ${data}" >안녕하세요. 손님</p>
</body>
</html>
```

- 글자 토글

```jsx
th:text="${LoggedInMember} ? ${LoggedInMember.username}:'UNKNOWN'"
```

- th:each

```html
<tr th:each="post, i : ${postList}">
	<td th:text="${postList.totalElements - postList.number * postList.size - i.count}"></td>
	<td th:text="${post.member.id}"></td>
	<td ><a th:href="@{community/read/ + post.id}" th:text="${post.title}"></a></td>
	<td th:text="${post.updatedAt}"></td>
	<td th:text="${post.viewCount}"></td>
</tr>
```

&nbsp;

## 서버 재시작 없이 HTML 변경 사항 적용하기

IntelliJ+ Spring+ **Thymeleaf** 프로젝트에서 resources 파일(HTML, CSS 등)의 변경 사항을 빠르게 확인하는 방법을 소개한다.

### 1. application.yaml 수정

```yaml
spring:
	thymeleaf:
		cache: false
```

**cache=false** 설정은 이미 생성된 결과를 서버에서 계속 보관할 것인지에 대한 설정이다.

### Run Configuration 수정

 * (2023.09.03 기준)

- Run → Edit Configurations → modify options →
    1. On ‘Update’ action → Update classes and resources
    2. On frame deactivation → Update classes and resources