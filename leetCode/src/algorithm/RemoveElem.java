package algorithm;

import java.util.stream.IntStream;

/**
 * Created by hui10.yang on 18/8/30.
 */
public class RemoveElem {
    public static void main(String[] args) {
        int[] a = new int[]{0,1,2,2,3, 0, 4, 2};
        System.out.println(removeElemFunction(a,2));
        System.out.println(removeElem(a,2));
    }

    /**
     * 移除元素
     * @param a
     * @return
     */
    private static long removeElemFunction(int[] a,int var) {
        return IntStream.of(a).filter(n -> n != var).count();
    }

    private static long removeElem(int[] b,int var) {
        long length=0;
        for (int i = 0; i < b.length; i++) {
            if (b[i] == var) {
                continue;
            }
            length++;
        }
        return length;
    }

}
