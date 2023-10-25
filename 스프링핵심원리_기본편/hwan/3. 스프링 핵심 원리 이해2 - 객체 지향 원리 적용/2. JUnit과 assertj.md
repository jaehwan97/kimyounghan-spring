# JUnit과 assertj
assertThat을 사용해야 하는데 Assertion.~로 시작하는 두 가지의 버전이 있었다.

```java
org.assertj.core.api.Assertions
org.junit.jupiter.api.Assertions
```

이전에는 JUnit을 많이 사용했지만, assertj가 등장한 이후로 assertj 버전을 많이 사용한다고 한다. 그 이유는 **assertj 버전이 가독성이 좋기 때문**!

assertj는 **메서드 체이닝**을 지원하기 때문에 문자와 숫자를 비교할 때에도 가독성을 높일 수 있다.

```java
assertEquals(expected, actual)  // JUnit

assertTaht(actual).isEqualTo(expected);  // asertj
```

Junit의 경우, 검사 대상과 기댓값의 관계를 한 눈에 파악하기 힘들다. 반면에, assertj는 메서드 체이닝 덕분에 검사 대상과 기댓값을 쉽게 알 수 있다.

&nbsp;

### Assertions.assertThat(A)

1. **isEqualTo(B)  ↔  isNotEqualTo(B)**

    A가 B의 **value(값)**와 같은지 확인한다.

    기댓값이 상수일 때 사용한다.

&nbsp;

2. **isSameAs(B)  ↔  isNotSameAs(B)**

    A가 B와 같은 **reference(주소값)**을 가지는지 확인한다.

&nbsp;

3. **isInstanceOf(B)**

    A가 **클래스 B의 인스턴스인지** 확인한다.

&nbsp;

4. **isNull(B)  ↔  isNotNull(B)**

    A가 **null인지** 확인한다.

&nbsp;

### Assertions.assertThrows(A)

assertThrows는 람다식을 실행했을 때, **특정 exception이 발생하는지** 확인한다.

```java
assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(Member.class));
```

ac.getBean(Member.class) 실행 중에 NoUniqueBeanDeifinitionException이 발생하면 성공한 테스트이다.

*NoUniqueBeanDeifinitionException은 빈으로 등록하지 않은 객체에 접근할 때 발생한다.