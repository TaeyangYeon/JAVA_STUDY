package java.study.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 데이터를 담고 있는 저장소(collection)가 아니다
 * Functional In nature. 스트림이 처리하는 데이터 소스를 변경하지 않는다.
 * 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
 * 무제한일 수도 있다. (Short Circuit 메서드를 사용해서 제한할 수 있다.)
 * 중계 오퍼레이션은 근본적으로 kazy하다
 * 손쉽게 병렬 처리할 수 있다.
 */
public class StreamAPI {

  public void streamAPI1() {
    List<String> names = new ArrayList<>();
    names.add("Yeon");
    names.add("Tae");
    names.add("Yang");

    List<String> collect1 = names.stream().map(name -> {
      System.out.println(name);
      return name.toUpperCase();
    }).toList();

    collect1.forEach(System.out::println);

    List<String> collect2 = names.parallelStream().map(String::toUpperCase).toList();

    collect2.forEach(System.out::println);

  }

  public void streamAPI2() {
    List<OnlineClass> springClasses = new ArrayList<>();
    springClasses.add(new OnlineClass(1, "spring boot", true));
    springClasses.add(new OnlineClass(2, "spring data jpa", true));
    springClasses.add(new OnlineClass(3, "spring mvc", false));
    springClasses.add(new OnlineClass(4, "spring core", false));
    springClasses.add(new OnlineClass(5, "rest api development", false));

    List<OnlineClass> javaClasses = new ArrayList<>();
    javaClasses.add(new OnlineClass(6, "The Java, Test", true));
    javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
    javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

    List<List<OnlineClass>> taeyangEvents = new ArrayList<>();
    taeyangEvents.add(springClasses);
    taeyangEvents.add(javaClasses);

    System.out.println("spring 으로 시작하는 수업");
    springClasses.stream()
        .filter(onlineClass -> onlineClass.getTitle().startsWith("spring"))
        .forEach(onlineClass -> System.out.println(onlineClass.getId()));

    System.out.println("close 되지 않은 수업");
    springClasses.stream()
        .filter(Predicate.not(OnlineClass::isClosed)) // = onlineClass -> !onlineClass.isClosed;
        .forEach(onlineClass -> System.out.println(onlineClass.getId()));

    System.out.println("수업 이름만 모아서 스트림 만들기");
    springClasses.stream()
        .map(OnlineClass::getTitle)
        .forEach(System.out::println);


    System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
    taeyangEvents.stream()
        .flatMap(Collection::stream)
        .forEach(onlineClass -> System.out.println(onlineClass.getId()));

    System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
    Stream.iterate(10, integer -> integer + 1)
        .skip(10)
        .limit(10)
        .forEach(System.out::println);

    System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
    boolean test = javaClasses.stream()
        .anyMatch(onlineClass -> onlineClass.getTitle().contains("Test"));
    System.out.println(test);

    System.out.println("스프링 수업 중에 제목에 spring이 들어간 것만 모아서 List로 만들기");
    // filter를 먼저 사용시 해당 타입의 변형이 일어나지 않기 때문에 map의 OnlineClass에서 제목을 추출
    List<String> classNameIsSpring1 = springClasses.stream()
        .filter(onlineClass -> onlineClass.getTitle().contains("spring"))
        .map(OnlineClass::getTitle)
        .collect(Collectors.toList());

    // map을 먼저 사용시 타입이 변하기 때문에 해당 타입으로 filter에서 조건 작성 필요
    List<String> classNameIsSpring2 = springClasses.stream()
        .map(onlineClass -> onlineClass.getTitle())
        .filter(className -> className.contains("spring"))
        .collect(Collectors.toList());

    classNameIsSpring1.forEach(System.out::println);
    classNameIsSpring2.forEach(System.out::println);

  }


}
