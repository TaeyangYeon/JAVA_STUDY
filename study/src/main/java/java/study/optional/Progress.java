package java.study.optional;

import lombok.Data;

import java.time.Duration;

@Data
public class Progress {

  private Duration studyDuration;
  private boolean finished;
}
