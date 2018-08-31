package algorithm;

import java.util.stream.IntStream;

/**
 * Created by hui10.yang on 18/8/30.
 */
public class RemoveElem {
    /**
     * 移除元素
     * @param a
     * @return
     */
    public   long removeElemFunction(int[] a,int var) {
        return IntStream.of(a).filter(n -> n != var).count();
    }

    public long removeElem(int[] b,int var) {
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
