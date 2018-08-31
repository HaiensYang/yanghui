package algorithm;

/**
 * Created by hui10.yang on 18/8/31.
 */
public class KSEinBST {


    /**
     * 二叉搜索树中的第K小的元素
     * @param root
     * @param k
     * @return
     */
    public Object kthSmallest(Object[] root, int k) {
        TreeNode rootNode=buildTree(root, 0);
        return kthSmallest(rootNode, k);
    }

    private Object kthSmallest(TreeNode node, int k) {
        int cnt = count(node.left);
        if (k <= cnt) {
            return kthSmallest(node.left, k);
        } else if (k > cnt + 1) {
            return kthSmallest(node.right, k - cnt - 1);
        }
        return node.val;
    }

    private TreeNode buildTree(Object[] root,int index) {
        TreeNode tn = null;
        if (index < root.length) {
            Object value = root[index];
            if (value==null) {
                return null;
            }
            tn = new TreeNode(value);
            tn.left = buildTree(root, 2 * index + 1);
            tn.right = buildTree(root, 2 * index + 2);
        }
        return tn;
    }

    private int count(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + count(node.left) + count(node.right);
    }

}
class TreeNode{
    Object val;
    TreeNode left;
    TreeNode right;

    public TreeNode(Object val) {
        this.val = val;
    }
}
