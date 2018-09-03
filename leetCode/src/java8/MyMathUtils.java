package java8;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by hui10.yang on 18/8/31.
 */
public class MyMathUtils {
    public static Stream<Integer> primes(int n) {
        return Stream.iterate(2, integer -> integer+1).filter(MyMathUtils::isPrime).limit(n);

    }

    private static boolean isPrime(int candidate) {
        int candidateRoot =(int) Math.sqrt(candidate);
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);

    }
}
