package java.study.completableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CompletableFuture {

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

  private Runnable getRunnable(String message) {
    return () -> System.out.println(message + Thread.currentThread().getName());
  }
}
