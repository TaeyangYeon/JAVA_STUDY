package java.study.fanctional;

@java.lang.FunctionalInterface
public interface FunctionalInterface {

  /**
   * 추상메서드가 하나만 있으면 "함수형 인터페이스"
   * 두 개는 안됨
   * static 메서드나 default 메서드를 정의 하여도 추상메서드는 하나이기 때문에 "함수형 인터페이스"로 구분
   */
  int doIt(int number);

}
