package java.study.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
//해당 애노테이션이 반복이 가능하도록 애노테이션을 감싸는 컨테이너 애노테이션을 만들어 사용할 수 있다.
@Repeatable(ChickenContainer.class)
public @interface Chicken {
  String value();
}
