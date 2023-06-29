package java.study.fanctional;

import java.util.function.Function;

public class FooMethod implements Function<Integer, Integer> {
  @Override
  public Integer apply(Integer integer) {
    return integer + 10;
  }
}
