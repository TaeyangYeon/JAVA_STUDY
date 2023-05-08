package variable;

/**
 * '형변환'이란?
 * 서로 다른 타입에 대한 연산을 위해 타입을 일치시키는 것
 */
public class Casting {

  /**
   * 기본형의 형변환 방법
   * (type) 값;
   */
  double num1 = 1.32d;
  int num2 = (int) num1; //num = 1
  double num3 = 1.3 + num2; //num2에 대해서 double로 자동 형변환
  char ch = 1000; //숫자 1000에 해당하는 문자값

  /**
   * 참조형의 형변환 방법
   * '객체.valueOf()'와 같은 객체 내에 형변환 메서드를 호출하여 형변환
   */
  Double num4 = 1.3D;
  Integer num5 = num4.intValue(); //int형으로 값을 반환하는 메서드

  String str = "1234";
  Integer num6 = Integer.valueOf(str); //값을 10진수로 반환하는 메서드. 문자가 있을 시 NumberFormatException 발생


}

