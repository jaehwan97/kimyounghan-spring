# Spring 스터디
김영한님의 Spring 완정 정복 로드맵을 따라 공부합니다.


## 학습 순서
|  순서   | 강의명                         |강의 시작 날짜|
|:-----:|-----------------------------|:---:|
|   1   | [스프링 핵심 원리 - 기본편](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%95%B5%EC%8B%AC-%EC%9B%90%EB%A6%AC-%EA%B8%B0%EB%B3%B8%ED%8E%B8)             |2023.10.16|
|   2   | [모든 개발자를 위한 HTTP 웹 기본 지식](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC)     ||
|   3   | [스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-mvc-1) ||
|   4   | [스프링 MVC 2편 - 백엔드 웹 개발 활용 기술](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-mvc-2) ||
|   5   | [스프링 DB 1편 - 데이터 접근 핵심 원리](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-db-1)    ||
|   6   | [스프링 DB 2편 - 데이터 접근 활용 기술](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-db-2)    ||
|   7   | [스프링 핵심 원리 - 고급편](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%95%B5%EC%8B%AC-%EC%9B%90%EB%A6%AC-%EA%B3%A0%EA%B8%89%ED%8E%B8)             ||


## 학습 규칙

1. 매주 일요일까지 한 주간 학습 내용을 커밋하고, 챌린저스에 인증합니다.
2. 공부 중 발생한 문제, 추가 학습 내용은 issue를 통해 공유하고 같이 해결 및 학습합니다.


## 진행 상황
|주차| 강의명             |섹션|
|--|-----------------|--|
|1주차| 스프링 핵심 원리 - 기본편 |섹션 1. 객체 지향 설계와 스프링 <br/>~ 섹션 3. 스프링 핵심 원리 이해2 - 객체 지향 원리 적용|
|2주차||섹션 4. 스프링 컨테이너와 스프링 빈 <br/>~ 섹션 6. 컴포넌트 스캔|

   
## 커밋 규칙

1. 본인의 저장소로 Fork 합니다.
2. Fork로 생성한 자신의 로컬 저장소를 clone합니다.
3. 원격 저장소를 확인합니다.
   ```
   git remote -v

   origin  https://github.com/{본인 계정}/kimyounghan-spring.git (fetch)
   origin  https://github.com/{본인 계정}/kimyounghan-spring.git (push)
   ```
 4. upstream으로 원본 저장소를 등록합니다.
    ```
    git remote add upstream https://github.com/inflearn-spring-study/kimyounghan-spring.git
    ```
5. 공부한 내용을 자신의 로컬 저장소에 Commit, Push 합니다.
   ```
   git add .
   git commit -m "[{해당 강의]]: {주차} - {섹션명} // [기본편]: 1주차 - 섹션1. 객체 지향 설계와 스프링
   ```
7. 한 주간 공부한 내역으로 원본 저장소에서 Pull Request를 작성합니다. (원본 저장소 <- 본인의 로컬 저장소)
8. Merge 합니다.
9. 본인의 로컬 저장소 sync를 확인합니다.
