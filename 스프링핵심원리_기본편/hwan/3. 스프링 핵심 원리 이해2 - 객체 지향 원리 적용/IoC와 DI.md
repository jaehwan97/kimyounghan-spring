# OCP, DIP 위반 예제

- 주문과 할인 정책
    - 회원은 상품을 주문할 수 있다.
    - 회원 등급에 따라 할인 정책을 적용할 수 있다.
    - 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용할 수 있다.
    - 할인 정책은 변경 가능성이 높기 때문에 서비스 시작 직전까지 고민을 미루고 싶다.

할인 정책은 변경 가능성이 높기 때문에 서비스를 시작하기 전에 '**고정할인정책**'과 '**정률할인정책**'을 미리 구현해놓았다.

&nbsp;

### ⚠️ 문제발생!

DiscountPolicy에 대해서 FixDiscountPolicy와 RateDiscountPolicy을 역할과 구현으로 충실하게 분리했다고 가정한다.

&nbsp;

주문 서비스가 시작되었고, 정해진 할인 정책은 고정할인정책으로 채택되었다. 그런데 시간이 지나고 정률할인정책으로 변경하자는 명령이 내려지고 빠르게 변경해야 하는 상황이 닥쳤다. 

그래서 개발자는 아래와 같은 방식으로 변경하였다.

```java
public class OrderServiceImpl implements OrderService {
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
		private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
}
```

&nbsp;

엥? 나는 OOP를 잘 준수했고 역할과 구현을 잘 분리한 줄 알았는데 OCP, DIP 원칙이 깨지는 건 기분탓인가?

- **OCP 원칙은 변경에 대해 닫혀있어야함!** (정률할인정책으로 바꾸려면 Fix~에서 Rate~로 변경해야됨.)

- **DIP 원칙은 구현 클래스를 의존해선 안된다.** (위 코드는 Fix~또는 Rate~를 의존하고 있음.)

&nbsp;

**할인 정책을 바꾸려면 코드의 변경이 불가피한데 도대체 어쩌라는거지…?**

&nbsp;

DIP 원칙을 지키기 위해 인터페이스에만 의존하도록 변경해야 한다.
 그러면 코드의 변경도 필요없어질테니 OCP 원칙도 지킬 수 있게 된다.

```java
public class OrderServiceImpl implements OrderService {
		private DiscountPolicy discountPolicy;
}
```

하지만 구현체가 없으면 코드를 실행할 수 없기 때문에 NPE(null pointer exception)이 발생하게 될 것이다.

&nbsp;

**이 문제를 해결하려면 누군가가 구현체를 대신 생성하고 주입해야 한다!**

그런데 누가..?

&nbsp;

# 관심사 분리

이 문제를 해결하려면 ‘누군가’가 구현체를 대신 생성하고 연결시켜줘야 한다.

애플리케이션의 전체 동작 방식을 구성(config)하기 위해, **구현 객체를 생성**하고, **연결**하는 책임을 가지는 별도의
설정 클래스(AppConfig)를 만든다.

&nbsp;

- AppConfig에서 구현 객체 생성

```java
public class AppConfig {

 public OrderService orderService() {
		 return new OrderServiceImpl(
				 new MemoryMemberRepository(),
				 new FixDiscountPolicy());
		 }
}
```

&nbsp;

- 구현체에서 생성자 주입

```java
public class OrderServiceImpl implements OrderService {

		private final MemberRepository memberRepository;
		private final DiscountPolicy discountPolicy;
		
		public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
				this.memberRepository = memberRepository;
				this.discountPolicy = discountPolicy;
		}
		
		...
}
```

한 곳에서 관리해주니까 변경이 있을 때마다 AppConfig에서만 수정하면 되고, 의존관계를 하나하나 찾아가면서 손 댈 필요도 없으니 너무 좋은 방식이다.

&nbsp;

# IoC와 DI

### 제어의 역전 (IoC: Inversion of Control)

스프링의 중요한 개념 중 하나인 IoC이다.

원래 클라이언트 구현체에서 객체를 생성하고, 연결하고, 실행하여 스스로 통제해왔지만 AppConfig가 등장하면서 **외부에서 대신 관리**해주니 우린 이것을 '**IoC**'라고 부르기로 했다.

&nbsp;

### 의존관계 주입 (DI: Dependency Injection)

의존관계 주입은 런타임에 동작한다.

&nbsp;

서버가 실행되고, AppConfig에서 구현 객체를 생성해주면 클라이언트 구현 객체로 생성한 객체를 전달 받아 의존관계를 연결지어준다.

이러한 일련의 과정을 ‘**DI**’라고 부르기로 했다.

&nbsp;

DI를 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다.