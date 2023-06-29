package java.study.developedInterfaceAtJava8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

public class App {

  public void app1() {

    Foo foo = new DefaultFoo("Taeyang");
    foo.printName(); // Taeyang
    foo.printNameUpperCase(); // TAEYANG

    Foo.printAnything(); // Foo

  }

  public void app2() {

    List<String> names = new ArrayList<>();
    names.add("Yeon");
    names.add("Tae");
    names.add("Yang");

    /**
     * Iterable의 기본 메서드
     * forEach()
     * spliterator()
     */
    names.forEach(name -> System.out.println(name));
    //메서드레퍼런스화
    names.forEach(System.out::println);

    Spliterator<String> spliterator1 = names.spliterator();
    //trySplit메서드로 기존 spliterator1에서 반을 나눠서 spliterator2와 구분
    Spliterator<String> spliterator2 = spliterator1.trySplit();
    //tryAddvance메서드를 이용해서 함수를 담을 수 있음
    while (spliterator1.tryAdvance(System.out::println));
    System.out.println("---------------------------");
    while (spliterator2.tryAdvance(System.out::println));

    /**
     * Collection의 기본 메서드
     * stream(), parallelStream()
     * removeIf(Predicate)
     * spliterator()
     */
    //"T"로 시작하는 요소를 삭제
    names.removeIf(word -> word.startsWith("T"));

    /**
     * Comparator의 기본 메서드
     * reversed()
     * thenComparing()
     * static reverseOrder() / natureOrder()
     * static nullsFirst() / nullsLast()
     * static comparing()
     */
    //문자열 값을 받아서 정렬하는 객체 생성
    Comparator<String> comparatorToIgnoreCase = String::compareToIgnoreCase;
    //정렬을 반전
    //정렬 방식의 추가를 원할 시 thenComparing()으로 이후 정렬 추가 가능
    names.sort(comparatorToIgnoreCase.reversed());


  }
}
