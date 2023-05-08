package variable;

/**
 * '변수'란?
 * 값을 저장할 수 있는 메모리상의 공간
 * 값의 변경이 가능하기 때문에 '변수'
 */
public class Variable {

  /**
   * 변수의 이름 규칙
   * 1. 대소문자 구분하며 길이의 제한이 없다
   * 2. 예약어를 사용해서는 안된다 (ex. true, this, 등...)
   * 3. 숫자로 시작 불가
   * 4. 특수문자는 '_'과 '$'만 허용 (가급적 사용 안함)
   *
   * 권장 방식
   * 1. 스네이크 표기법 사용 (class는 첫글자 upper로)
   * 2. 상수선언은 전체 uppercase로 (구분은 '-'를 사용)
   */

  /**
   * 기본형과 참조형의 차이
   * 기본형 : 실제 값(data)을 저장. 자동으로 기본값 할당
   * 참조형 : 값이 저장된 메모리 주소(memory address)를 값으로 저장. 값이 지정되지 않은 경우 'null'
   */

  /**
   * 기본형 변수 타입
   */

  // 1. char : 2byte를 사용하는 하나의 문자
  char character = 'A';

  // 2. boolean : 'true', 'false' 두개의 값만 표회되는 1byte의 논리형 변수
  boolean result = false;

  // 3. byte : 1byte 크기의 정수 _ 2의 7승
  byte num1 = 127;

  // 4. short : 2byte 크기의 정수 _ 2의 15승 (c언어와 호환을 위해 사용)
  short num2 = 32767;

  // 5. int : 4byte 크기의 정수 _ 2의 31승
  int num3 = 2147483647;

  // 6. long : 8byte 크기의 정수 _ 2의 63승 (숫자 사이에 '_'를 넣어서 읽기 편하게 작성 가능)
  long num4 = 9_223_372_036_854_775_807L;

  // 7. float : 4byte 크기의 실수
  float num5 = 3.14f;

  // 8. double : 8byte 크기의 실수
  double num6 = 1.618d;

  /**
   * 참조형 변수 타입
   */

  // 1. char의 참조형 Character
  Character ch = 'A';

  // 2. boolean의 참조형 Boolean
  Boolean rs = false;

  // 3. byte의 참조형 Byte
  Byte number1 = 127;

  // 4. short의 참조형 Short
  Short number2 = 32767;

  // 5. int의 참조형 Integer
  Integer number3 = 2147483647;

  // 6. long의 참조형 Long
  Long number4 = 9_223_372_036_854_775_807L;

  // 7. float의 참조형 Float
  Float number5 = 3.14f;

  // 8. double의 참조형 Double
  Double number6 = 1.618d;

  // 9. 문자열 (문자의 배열)
  String str = "ABC";

}