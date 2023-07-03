package java.study.optional;

import lombok.Data;

import java.util.Optional;

@Data
public class OnlineClass {

  private Integer id;
  private String title;
  private boolean closed;

  public Progress progress;

  public OnlineClass(Integer id, String title, boolean closed) {
    this.id = id;
    this.title = title;
    this.closed = closed;
  }

  public Optional<Progress> getProgress() {
    return Optional.ofNullable(progress);
  }

}
