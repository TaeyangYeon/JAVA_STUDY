package ifSwitch;
/**
 * if 조건문
 * 프로그램 실행 흐름을 변경하기 위해 작성하는 구문
 */
public class If {

  private void ifTest() {

    int num1 = 1;
    int num2 = 2;

    /**
     * 조건이 참일 때
     * 조건식 내에 연산을 실행
     * test
     */
    if (num2 > num1) {
      num1 += 1; // 1 + 1 실행
    }

    /**
     * 조건식 거짓일 때
     * 조건식을 건너뜀
     * test
     */
    if (num2 < num1) {
      num1 -= 1; //실행되지 않음
    }

    /**
     * if + else if
     * 조건의 구분이 필요할 때
     */
    if (num1 > 1) {
      num1 += 1; // 실행되지 않음
    } else if (num1 > 5) {
      num1 -= 1; // 1-1 실행
    }

    /**
     * if + else
     * 조건이 참일 때와 기본일 때를 구분
     * 'else'는 'if' 조건이 거짓일 경우 이기 때문에 조건을 작성하지 않는다
     */
    if (num1 > 1) {
      num1 -= 1;
    } else {
      num1 += 1;
    }

  }

  /**
   * 예제
   * 8시간 이상 근무하면 초과시간은 1.5배가 된다
   */
  private int salary(int time) {

    final int salaryOfTime = 10000;
    int total = 0;

    if (time > 8) {
      total = salaryOfTime * 8;
      total += (time - 8) * salaryOfTime * 1.5;
    } else {
      total = salaryOfTime * time;
    }

    return total;
  }

}
