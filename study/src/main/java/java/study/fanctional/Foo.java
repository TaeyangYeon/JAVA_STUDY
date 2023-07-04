package java.study.fanctional;

import java.util.function.*;

public class Foo {

  public void foo1() {

    /**
     * 과거의 인터페이스 클래스 구련 방식
     * 익명의 내부 클래스 anonymous inner class
     */
    FunctionalInterface functionalInterface1 = new FunctionalInterface() {

      @Override
      public int doIt(int number) {
        return number + 10;
      }

    };

    /**
     * 람다 표현식
     * 구현하는 코드가 여러줄일땐 중괄호를 이용
     */
    FunctionalInterface functionalInterface2 = (number) -> {
      number += 10;
      return number;
    };
    /**
     * 람다 표현식에 중괄호 및 return 제거
     */
    FunctionalInterface functionalInterface3 = (number) -> number + 10;
    /**
     * 람다 표현식에 소괄호 제거
     */
    FunctionalInterface functionalInterface4 = number -> number + 10;

    /**
     * 메서드 실행
     */
    functionalInterface1.doIt(1);
    functionalInterface2.doIt(1);
    functionalInterface3.doIt(1);
    functionalInterface4.doIt(1);
  }

  public void foo2() {

    /**
     * Fanction클래스를 재정의한 fooMethod를 생성하여 사용
     */
    FooMethod fooMethod1 = new FooMethod();
    fooMethod1.apply(1);

    Function<Integer, Integer> fooMethod2 = integer -> integer + 10;
    fooMethod2.apply(1);

    /**
     * compose를 활용하여 두개의 메서드를 통합
     * compose시킨 fooMethod3를 먼저 실행 후 fooMethod2를 실행
     */
    Function<Integer, Integer> fooMethod3 = integer -> integer * 2;
    Function<Integer, Integer> composeFooMethod = fooMethod2.compose(fooMethod3);
    composeFooMethod.apply(2); // ( 2 * 2 ) + 10 = 14

    /**
     * andThen을 활용하여 두개의 메서드를 통합
     * fooMethod2를 먼저 실행 후 andThen으로 fooMethod3을 실행
     */
    fooMethod2.andThen(fooMethod3).apply(2); // ( 2 + 10 ) * 2 = 24

    /**
     * 리턴 값이 없는 Consumer 객체
     */
    Consumer<Integer> fooMethod4 = integer -> System.out.println(integer);
    fooMethod4.accept(2);

    /**
     * 입력값 없이 정의 된 값을 가지는 Supplier 객체
     * 입력값이 없을 때는 소괄호 생략 불가
     */
    Supplier<Integer> fooMethod5 = () -> 10;
    System.out.println(fooMethod5);

    /**
     * Boolean 값을 리턴하는 Predicate 객체
     */
    Predicate<Integer> fooMethod6 = integer -> integer > 0;
    fooMethod6.test(1);

    /**
     * Function에서 입력값과 리턴값의 타입이 같을 때 사용하는 UnaryOperator 객체
     */
    UnaryOperator<Integer> fooMethod7 = integer -> integer + 10;
    fooMethod7.apply(1);

    /**
     * 두개의 값을 입력 받는 BIFunction 객체
     * 입력값 두개와 리턴값이 모두 다를 것을 가장
     * 람다 표현식에서 입력값이 두 개 이상일 땐 소괄호 생략 불가
     */
    BiFunction<String, Integer, String> fooMethod8 = (String string, Integer integer) -> string + integer;
    fooMethod8.apply("number=", 2);

    /**
     * BiFunction에서 입력값과 리턴값이 모두 같은 때 사용하는 BinaryOperator 객체
     * ...그 외에도 BiConsumer, BiPredicate, ... 등 객체가 있으나 Function객체와 같고, 입력값만 두개
     */
    BinaryOperator<Integer> fooMethod9 = (integer1, integer2) -> integer1 + integer2;
    fooMethod9.apply(1, 2);

  }

}
