import algorithm.KSEinBST;
import algorithm.RandomOrder;
import algorithm.RemoveElem;

/**
 * Created by hui10.yang on 18/8/30.
 */
public class Main {
    public static void main(String[] args) {
        RemoveElem removeElem = new RemoveElem();
        int[] a = new int[]{0,1,2,2,3, 0, 4, 2};
        System.out.println(removeElem.removeElemFunction(a,2));
        System.out.println(removeElem.removeElem(a,2));
        RandomOrder randomOrder = new RandomOrder();
        System.out.println(randomOrder.orderChar("3Z4"));
        KSEinBST ksEinBST = new KSEinBST();
        Object[] b = new Object[]{5,3,6,2, 4, null,null,1};
        System.out.println(ksEinBST.kthSmallest(b,3));
    }
}
