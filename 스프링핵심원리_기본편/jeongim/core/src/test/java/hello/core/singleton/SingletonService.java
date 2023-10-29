package hello.core.singleton;

public class SingletonService {

    // 자기 자신을 내부에 static으로 하나 가지고 있음 => 이렇게 선언하면 클래스 레벨에 올라가기 때문에 딱 하나만 존재함
    // 자바 static 영역에 대하여 알아두기
    private static final SingletonService instance = new SingletonService();

    // 객체 인스턴스가 필요하면 이 static 메소드를 통해서만 조회가 가능
    public static SingletonService getInstance() {
        return instance;
    }

    // private 생성자를 통해 외부에서 객체 인스턴스가 생성되는 것을 막음
    private SingletonService() {   }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
