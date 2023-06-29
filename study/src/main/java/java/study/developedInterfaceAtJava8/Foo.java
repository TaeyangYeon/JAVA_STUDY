package java.study.developedInterfaceAtJava8;

public interface Foo {

  void printName();

  /**
   * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 출력한다.
   * DefaultFoo클래스에서 재정의 가능
   */
  default void printNameUpperCase() {
    System.out.println(getName().toUpperCase());
  }

  static void printAnything() {
    System.out.println("Foo");
  }

  String getName();
}
