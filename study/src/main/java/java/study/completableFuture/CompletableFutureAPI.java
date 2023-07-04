package java.study.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureAPI {

  public void CompleteableFuture1() throws InterruptedException {

    /**
     * Thread 활용
     * Thread의 sleep을 활용하여 쓰레드를 일시 정지 시킬 수 있음
     *
     * "Hello" 먼저 실행 된 후 "Thread'가 실행 됨
     */
    Thread thread1 = new Thread(() -> {
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Thread1 : " + Thread.currentThread().getName());
    });
    thread1.start();
    System.out.println("Hello1 : " + Thread.currentThread().getName());


    /**
     * Interrupt를 활용한 쓰레드 깨우기
     */
    Thread thread2 = new Thread(() -> {
      while(true) {
        System.out.println("Thread2 : " + Thread.currentThread().getName());
        try {
          Thread.sleep(1000L);
        } catch (InterruptedException e) {
          System.out.println("exit!");
          return;
        }
      }
    });
    thread2.start();
    System.out.println("Hello2 : " + Thread.currentThread().getName());
    Thread.sleep(3000L);
    thread2.interrupt();


    /**
     * join을 활용한 쓰래드 대기
     */
    Thread thread3 = new Thread(() -> {
      System.out.println("Thread : " + Thread.currentThread().getName());
      try {
        Thread.sleep(3000l);
      } catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }
    });
    thread3.start();
    System.out.println("Hello3 : " + Thread.currentThread().getName());
    thread3.join();
    System.out.println(thread3 + " is finished");
  }

  public void CompletableFuture2() {
    /**
     * ExecutorService 객체를 활용하여 쓰레드풀을 생성하여 관리할 수 있음
     */
    ExecutorService executorService1 = Executors.newSingleThreadExecutor();
    executorService1.submit(() -> System.out.println("Thread : " + Thread.currentThread().getName()));
    // 현재 진행 중인 작업을 마친후 종료시킨다.
    executorService1.shutdown();
    // 현재 진행 중인 작업에 상관없이 바로 종료시킨다.
    executorService1.shutdownNow();


    // newFixedThreadPool룰 이용하여 여러개의 쓰레드를 생성
    ExecutorService executorService2 = Executors.newFixedThreadPool(2);
    executorService2.submit(getRunnable("Hello"));
    executorService2.submit(getRunnable("Taeyang"));
    executorService2.submit(getRunnable("The"));
    executorService2.submit(getRunnable("Java"));
    executorService2.submit(getRunnable("Thread"));
    executorService2.shutdown();


    // ScheduledExecutorService
    ScheduledExecutorService executorService3 = Executors.newSingleThreadScheduledExecutor();
    executorService3.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);
    executorService3.shutdown();
  }

  public void completableFuture3() throws ExecutionException, InterruptedException {
    /**
     * Future에서 get을 사용시 해당 위치에서 submit에 정의된 부분이 실행되며 하위 라인은 실행을 대기한다
     * 블로킹 콜
     */
    ExecutorService executorService1 = Executors.newSingleThreadExecutor();
    Callable<String> hello1 = () -> {
      Thread.sleep(2000L);
      return "hello";
    };
    Future<String> helloFuture1 = executorService1.submit(hello1);
    helloFuture1.get();
    executorService1.shutdown();

    /**
     * isDone메서드르 활용하여 Future의 상태를 알 수 있다.
     * 끝났으면 true, 아니면 false
     */
    ExecutorService executorService2 = Executors.newSingleThreadExecutor();
    Callable<String> hello2 = () -> {
      Thread.sleep(2000L);
      return "hello";
    };
    Future<String> helloFuture2 = executorService2.submit(hello2);
    helloFuture2.isDone(); // false
    helloFuture2.get();
    helloFuture2.isDone(); // true
    executorService2.shutdown();

    /**
     * cancel메서드 활용하여 Future의 상태를 종료시킬 수 있다.
     * 종료를 원하면 true, 아니면 false
     * get메서드를 같이 사용하면 예외가 발생한다. (취소 시킨 것을 가져오려는 행위)
     */
    ExecutorService executorService3 = Executors.newSingleThreadExecutor();
    Callable<String> hello3 = () -> {
      Thread.sleep(2000L);
      return "hello";
    };
    Future<String> helloFuture3 = executorService2.submit(hello3);
    helloFuture3.isDone(); // false
    helloFuture3.cancel(true);
    helloFuture3.isDone(); // true
    executorService3.shutdown();

    /**
     * invokeAll메서드를 활용하여 동시에 실행시킬 수 있다.
     * 각각 쓰레드의 sleep시간을 대기 한 뒤 동시에 전체 실행
     */
    ExecutorService executorService4 = Executors.newSingleThreadExecutor();
    Callable<String> helloAll = () -> {
      Thread.sleep(2000L);
      return "Hello";
    };
    Callable<String> javaAll = () -> {
      Thread.sleep(3000L);
      return "Java";
    };
    Callable<String> taeyangAll = () -> {
      Thread.sleep(1000L);
      return "Taeyang";
    };
    List<Future<String>> futures = executorService4.invokeAll(Arrays.asList(helloAll, javaAll, taeyangAll));
    for (Future<String> future : futures) {
      System.out.println(future.get());
    }
    executorService4.shutdown();

    /**
     * invokeAny메서드를 활용하여 동시에 실행시킬 수 있다.
     * 각각 쓰레드 중 가장 먼저 리턴된 값을 가진다.
     */
    ExecutorService executorService5 = Executors.newFixedThreadPool(3);
    Callable<String> helloAny = () -> {
      Thread.sleep(2000L);
      return "Hello";
    };
    Callable<String> javaAny = () -> {
      Thread.sleep(3000L);
      return "Java";
    };
    Callable<String> taeyangAny = () -> {
      Thread.sleep(1000L);
      return "Taeyang";
    };
    String any = executorService5.invokeAny(Arrays.asList(helloAny, javaAny, taeyangAny));
    System.out.println(any); // Taeyang
    executorService5.shutdown();
  }

  public void completableFuture4() throws ExecutionException, InterruptedException {
    // complete에 대한 두가지 선언 방식
    CompletableFuture<String> future1 = new CompletableFuture<>();
    future1.complete("taeyang");
    CompletableFuture<String> future2 = CompletableFuture.completedFuture("taeyang");

    // runAsync메서드를 활용해서 리턴타입이 없는 경우의 메서드를 작성할 수 있다.
    CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
    });
    future3.get();

    // supplyAsync메서드를 활용해서 리턴타입을 가진 메서드를 작성할 수 있다.
    // thenApply메서드를 활용해서 결과 값에 리턴이 있는 함수를 적용할 수 있다. (콜백)
    CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    }).thenApply(string -> {
      System.out.println(Thread.currentThread().getName());
      return string.toUpperCase();
    });
    System.out.println(future4.get());

    // thenAccept메서드를 활용해서 결과 값에 리턴이 없는 함수를 적용할 수 있다. (콜백)
    CompletableFuture<Void> future5 = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    }).thenAccept(string -> {
      System.out.println(Thread.currentThread().getName());
      System.out.println(string.toUpperCase());
    });
    future5.get();

    // thenAccept메서드를 활용해서 결과 값에 관계 없는 함수를 적용할 수 있다. (콜백)
    CompletableFuture<Void> future6 = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    }).thenRun(() -> {
      System.out.println(Thread.currentThread().getName());
    });
    future6.get();

    /**
     * ExecutorService 생성 없을시 ForkJoinPool의 commonPool의 Thread를 사용하고
     * 생성시 ExecutorService의 ThreadPool을 사용한다.
     * thenRunAsync를 사용하면 ThreadPool을 추가할 수 있다.
     */
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    CompletableFuture<Void> future7 = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    }, executorService).thenRunAsync(() -> {
      System.out.println(Thread.currentThread().getName());
    }, executorService);
    future7.get();
    executorService.shutdown();
  }

  public void completableFuture5() throws ExecutionException, InterruptedException {
    // thenCompose를 통해서 두 작업이 서로 이어서 실행되도록 할 수 있다.
    CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    });
    CompletableFuture<String> future2 = future1.thenCompose(this::getWorld);
    System.out.println(future2.get());

    // thenCombine메서를 활용해서 두 작업을 독립적으로 실행하고 두 작업이 다 종료됐을 때 콜백받을 수 있다.
    CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    });
    CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
      System.out.println("World : " + Thread.currentThread().getName());
      return "World";
    });
    CompletableFuture<String> future5 = future3.thenCombine(future4, (result1, result2) -> {
      return result1 + " " + result2;
    });
    System.out.println(future5.get());

    // allOF로 두 작업을 다 실행하고 다른 메서드가 호출될 수 있지만
    // 결과에 대한 보장이 되지 않기 때문에 null을 반환
    // 리턴값을 가지는 두 작업을 배열로 만들어 allOf하여 독립적인 실행결과를 다시 리스트로 받아 반환할 수 있음
    CompletableFuture<String> future6 = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    });
    CompletableFuture<String> future7 = CompletableFuture.supplyAsync(() -> {
      System.out.println("World : " + Thread.currentThread().getName());
      return "World";
    });
    CompletableFuture<Void> future8 = CompletableFuture.allOf(future6, future7)
            .thenAccept(System.out::println);
    System.out.println(future8.get());

    List<CompletableFuture<String>> futures = Arrays.asList(future6, future7);
    CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);
    CompletableFuture<List<String>> futuresResult = CompletableFuture.allOf(futuresArray)
        .thenApply(value -> futures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList()));
    futuresResult.get().forEach(System.out::println);

    // anyOf메서드는 결과 값에 대한 보증이 필요하지 않아서 바로 사용 가능 (결과가 무조건 하나)
    CompletableFuture<String> future9 = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    });
    CompletableFuture<String> future10 = CompletableFuture.supplyAsync(() -> {
      System.out.println("World : " + Thread.currentThread().getName());
      return "World";
    });
    CompletableFuture<Void> future11 = CompletableFuture.anyOf(future9, future10).thenAccept(System.out::println);
    future11.get();

    // exceptionally메서드를 활용해서 CompletableFuture의 예외를 처리할 수 있다.
    boolean throwError1 = true;
    CompletableFuture<String> future12 = CompletableFuture.supplyAsync(() -> {
      if (throwError1) {
        throw new IllegalArgumentException();
      }
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    }).exceptionally(exception -> {
      System.out.println(exception);
      return "Error!";
    });
    System.out.println(future12.get());

    // handle메서드를 활용해서 결과가 실행될 때와 예외 상황에 대해서 분기하여 처리할 수 있다.
    boolean throwError2 = true;
    CompletableFuture<String> future13 = CompletableFuture.supplyAsync(() -> {
      if (throwError2) {
        throw new IllegalArgumentException();
      }
      System.out.println("Hello : " + Thread.currentThread().getName());
      return "Hello";
    }).handle((result, exception) -> {
      if (exception != null) {
        System.out.println(exception);
        return "Error!";
      }
      return result;
    });
    System.out.println(future13.get());

  }

  private Runnable getRunnable(String message) {
    return () -> System.out.println(message + Thread.currentThread().getName());
  }

  private CompletableFuture<String> getWorld(String message) {
    return CompletableFuture.supplyAsync(() -> {
      System.out.println("World : " + Thread.currentThread().getName());
      return message + " World";
    });
  }
}
