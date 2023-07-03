package java.study.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 오직 값 한 개가 들어있을 수도 있고, 없을 수도 있는 컨테이너
 * 리턴값으로만 쓰기를 권장 (메소드 매개변수 타입, 맵의 키 타입, 인스턴스 필트 타입으로 쓰기 X)
 * Optional을 리턴하는 메소드에서 null을 리턴 X
 * 프리미티브 타입용 Optional은 따로 있다. OprionalInt, OptionalLong...
 * Collection, Map, Stream Array, Optional은 Optional로 감싸지 말 것. (비어 있는지 아닌지 확인 가능한 인스턴스에 사용 필요 X)
 */
public class OptionalAPI {

  public void OptionalAPI1() {

    List<OnlineClass> springClasses = new ArrayList<>();
    springClasses.add(new OnlineClass(1, "spring boot", true));
    springClasses.add(new OnlineClass(2, "spring data jpa", true));
    springClasses.add(new OnlineClass(3, "spring mvc", false));
    springClasses.add(new OnlineClass(4, "spring core", false));
    springClasses.add(new OnlineClass(5, "rest api development", false));

    Optional<OnlineClass> springClass = springClasses.stream()
        .filter(onlineClass -> onlineClass.getTitle().startsWith("spring"))
        .findFirst();

    // isPresent 메서드를 활용하여 Optional 변수에 값이 있는지 확인
    boolean present = springClass.isPresent();
    System.out.println(present);

    // ifPresent 메서드를 활용하여 Optional 변수에 값이 있는 경우의 조건을 생성
    springClass.ifPresent(onlineClass -> System.out.println(onlineClass.getTitle()));

    // createNewClasses 메서드가 무조건 동작하고 조건에 충족한 값을 출력
    // 상수로 선언된 값을 이용할 때 적합
    OnlineClass onlineClass1 = springClass.orElse(createNewClasses());

    // 조건에 값이 있으면 해당 값을 출력, 없을때만 createNewClasses 호출
    // 동적으로 동작해야 할 때 적합
    OnlineClass onlineClass2 = springClass.orElseGet(this::createNewClasses);

    // 생성할 수 없을 경우 예외 발생
    OnlineClass onlineClass3 = springClass.orElseThrow();

    // filter를 사용하면 기본적으로 Optional 타입으로 선언됨
    Optional<OnlineClass> onlineClass4 = springClass.filter(onlineClass -> !onlineClass.isClosed());

    // map을 사용하면 기본적으로 Optional 타입으로 선언됨
    Optional<Integer> onlineClass5 = springClass.map(OnlineClass::getId);

    // getProgress가 Optional로 선언 되었기 때문에 Optional 안에 Optional을 선언한 사례가 발생할 수 있음
    // flatMap을 이용하여 안에 OnlineClass 안에 Progress를 직접 선언
    Optional<Progress> onlineClass6 = springClass.flatMap(OnlineClass::getProgress);
  }

  private OnlineClass createNewClasses() {
    return new OnlineClass(10, "New Class", false);
  }
}
