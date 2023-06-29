package java.study.fanctional;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodReference {

  public void methodReference1() {

    /**
     * 콜론 두개를 사용하면 메서드레퍼런스
     */
    UnaryOperator<String> reference1 = Greeting::hi;
    reference1.apply("Taeyang");

    /**
     * 입력값이 없는 레퍼런스를 사용할 때
     * 객체를 생성할 때 : new를 사용하여 객체를 생성
     */
    Supplier<Greeting> reference2 = Greeting::new;
    Greeting newGreeting1 = reference2.get();
    //UnaryOperator<String> reference3 = newGreeting1::hi;

    /**
     * 입력값과 리턴값이 다를 때
     */
    Function<String, Greeting> reference4 = Greeting::new;
    Greeting newGreeting2 = reference4.apply("Taeyang");
    newGreeting2.getName();

    /**
     * 임의 객체의 인스턴스 메서드 참조
     */
    String[] names = {"Yeon", "Tae", "Yang"};
    Arrays.sort(names, String::compareToIgnoreCase); // {Tae, Yang, Yeon}

  }
}
