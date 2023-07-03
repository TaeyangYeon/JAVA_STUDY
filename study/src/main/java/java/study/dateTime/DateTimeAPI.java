package java.study.dateTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeAPI {

  public void dateTimeAPI1() {

    // 기준시 UTC, GMT
    Instant instant = Instant.now();
    System.out.println(instant); // yyyy-MM-ddThh:mm:ss.ssssssZ

    // 기준시를 시스템 기본설정으로 변경
    ZoneId zone = ZoneId.systemDefault();
    System.out.println(zone); // country/city
    ZonedDateTime zonedDateTime = instant.atZone(zone);
    System.out.println(zonedDateTime); // yyyy-MM-ddThh:mm:ss.ssssss-hh:mm[country/city]

    // 시스템 정보 설정을 기반으로 시간을 반환
    LocalDateTime now = LocalDateTime.now();
    System.out.println(now); // yyyy-MM-ddThh:mm:ss.ssssss

    // 특정 시간을 생성
    LocalDateTime birthday = LocalDateTime.of(1994, Month.SEPTEMBER, 22, 0, 0, 0);
    System.out.println(birthday);

    // 특정 위치의 시간을 조회
    // Instant에서 atZone으로 시간 생성시 ZonedDateTime으로 생성됨
    ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    System.out.println(nowInKorea);
    ZonedDateTime zonedDateTimeConst = instant.atZone(ZoneId.of("Asia/Seoul"));
    System.out.println(zonedDateTimeConst);

    // 기간을 생성
    // LocalDate에서 until로 기간을 생성시 Period로 생성됨
    LocalDate today = LocalDate.now();
    LocalDate thisYearBirthday = LocalDate.of(2023, Month.SEPTEMBER, 22);
    Period period = Period.between(today, thisYearBirthday);
    System.out.println(period.getDays());
    Period until = today.until(thisYearBirthday);
    System.out.println(until.get(ChronoUnit.DAYS)); // getDays() 와 동일한 결과를 얻음

    // instant를 가지고 기간을 생성
    Instant plus = instant.plus(10, ChronoUnit.SECONDS);
    Duration between = Duration.between(instant, plus);
    System.out.println(between.getSeconds());

    // 현재 시간을 포맷화
    // parse을 사용해서 원하는 날짜 선언 타입을 변환 가능
    DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    System.out.println(MMddyyyy);
    LocalDate parse = LocalDate.parse("09/22/1994", MMddyyyy);
    System.out.println(parse);

    //
  }
}
