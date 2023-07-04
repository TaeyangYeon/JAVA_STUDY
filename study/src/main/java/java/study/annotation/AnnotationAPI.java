package java.study.annotation;

/**
 * 애노테이션
 *
 * 타입 선언부
 *  - 제네릭 타입
 *  - 변수 타입
 *  - 매개변수 타입
 *  - 예외 타입
 *  - .......
 *
 * 타입을 사용할 수 있으려면
 *  - TYPE_PARAMETER : 타입의 변수에만 사용할 수 있다.
 *  - TYPE_USE : 타입 변수를 포함해서 모든 타입 선언부에 사용할 수 있다.
 *
 * 중복 사용할 수 있는 애노테이션을 만들기
 *  - 중복 사용할 애노테이션 만들기
 *  - 중복 애노테이션 컨테이너 만들기
 *    * 컨테이너 애노테이션은 중복 애노테이션과 @Retention 및 @Target이 같거나 더 넓어야 한다.
 */
public class AnnotationAPI {

  // @Chicken을 TYPE_PARAMETER로 선언했기 때문에 해당 자리에만 가능하다.
  // 타입을 가지는 모든 곳에 사용하고 싶으면 TYPE_USE를 사용할 수 있다.
  // Repeatable을 통해서 중복선언 할 수 있다.
  class FeelsLikeChicken<@Chicken("양념")@Chicken("후라이드") T> {
    public <@Chicken("간장") T> void print(T t) {
      System.out.println(t);
    }
  }
}
