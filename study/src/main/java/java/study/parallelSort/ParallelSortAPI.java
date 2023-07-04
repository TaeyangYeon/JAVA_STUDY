package java.study.parallelSort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Arrays.parallelSort()
 *  - Fork/Join 프레임워크를 사용해서 배열을 병렬로 정렬하는 기능을 제공한다.
 *
 * 병렬 정렬 알고리즘
 *  - 배열을 둘로 계속 쪼갠다.
 *  - 합치면서 정렬한다.
 */
public class ParallelSortAPI {

  public void parallelSortAPI() {
    int size = 1500;
    int[] numbers = new int[size];
    Random random = new Random();

    /**
     * 두 정렬의 알고리즘의 효율성은 똑같으나,
     * parallelSort의 경우 멀티쓰레드로 인해 정렬시간이 단축된다.
     */
    IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
    long start = System.nanoTime();
    Arrays.sort(numbers);
    System.out.println("serial sorting took " + (System.nanoTime() - start)); // serial sorting took 548957

    IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
    start = System.nanoTime();
    Arrays.parallelSort(numbers);
    System.out.println("parallel sorting took " + (System.nanoTime() - start)); // parallel sorting took 364074

  }
}
