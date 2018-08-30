package algorithm;

import java.util.stream.IntStream;

/**
 * Created by hui10.yang on 18/8/30.
 */
public class RemoveElem {
    public static void main(String[] args) {
        int[] a = new int[]{0,1,2,2,3, 0, 4, 2};
        System.out.println(removeElemFunction(a));
    }

    private static long removeElemFunction(int[] a) {
        return IntStream.of(a).distinct().count();
    }
}
