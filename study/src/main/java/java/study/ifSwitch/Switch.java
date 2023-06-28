package ifSwitch;

/**
 * switch 조건문
 * 조건식이 많을 때 코드를 간결하게 하기 위해 사용
 */
public class Switch {

  int num1 = 1;
  int num2 = 10;

  private void switchTest() {
    /**
     * 조건이 참일 때 해당 구문 실행
     * 'break문' 또는 'switch문'의 끝을 만나면 조건식을 빠져나간다
     */
    switch (num1) {
      case 1 : //num1의 초기화 값이 '1'이기 때문에 해당 위치에서 조건 실행
        num1 -= 1; // num1 = 1 - 1
        break; // switch문 종료
      case 2 :
        num1 -= 2;
        break;
      case 3 :
        num1 -= 3;
        break; // 마지막 문장엔 'break' 생략 가능
    }

    /**
     * 'default'를 사용하여 기본일 때 조건 설정
     */
    switch (num2) {
      case 1 :
        num2 -= 1;
        break;
      case 2 :
        num2 -= 2;
        break;
      default : //num2의 초기화 값이 '10'이기 때문에 해당 위치에서 조건 실행
        num2 -= 10; // num2 = 10 - 10
    }

  }

  /**
   * 예제
   * 성적에 때른 등급
   */
  private char gradeByPoint(int point) {
    char grade = 0;

    /**
     * 1~100 사이의 점수
     * 10으로 나눠서 점수의 범위를 구분. (ex. 94 / 10 = 9.4, 9.4를 정수로 반환하면 9)
     */
    switch (point / 10) {
      case 10:
        grade = 'A';
        break;
      case 9 :
        grade = 'B';
        break;
      case 8 :
        grade = 'C';
        break;
      case 7 :
        grade = 'D';
        break;
      default :
        grade = 'F';
    }

    return grade;
  }
}
