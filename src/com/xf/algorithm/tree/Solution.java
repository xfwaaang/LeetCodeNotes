package com.xf.algorithm.tree;

import java.util.*;

/**
 * @author xfwaaang
 * @create 2019-03-08 19:16
 *
 * 94. 二叉树中序遍历      中序遍历
 * 101. 判断二叉树是否对称，包括val     对称
 * 107. 二叉树层次遍历，逆序输出        层次遍历   逆序
 * 110. 判断平衡二叉树     树的高度   平衡
 * 111. 二叉树最小深度     树的深度
 * 112. 是否具有一条路径使得根节点到叶子结点的权值和为sum      路径  权值和
 * 199. 二叉树右视图从上到下的结点       层次遍历，每层从右往左遍历
 * 257. 二叉树根节点到叶子结点的所有路径        路径
 * 437. 路径（起始节点不需要为根节点）权值和为sum的个数       路径  权值和
 * 501. 二叉搜索树出现最多的结点值     中序遍历   二叉搜索树--中序即有序
 * 572. 树t是否是树s的一部分      子树
 * 654. 建立大顶堆       根节点值最大  大根树
 * 671. 求二叉树倒数第二小的权值        每个结点只有零个或两个孩子结点  父结点权值为孩子结点权值的较小值
 * 687. Longest Univalue Path       最长路径（路径上结点权值相同）
 * 701. 向二叉搜索树中插入一个结点       二叉搜索树  插入
 * 814. 清除子树权值全为0的子树        删除子树
 */
public class Solution {

    /**
     * 94. Binary Tree Inorder Traversal
     * 二叉树中序遍历
     * pass     100%    50%
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversalHelper(root, res);
        return res;
    }

    private void inorderTraversalHelper(TreeNode root, List<Integer> res) {
        if (root != null){
            inorderTraversalHelper(root.left, res);
            res.add(root.val);
            inorderTraversalHelper(root.right, res);
        }
    }


    /**
     * 101. Symmetric Tree
     * 判断二叉树是否对称
     * recursive
     * pass
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if(root == null)    return true;
        return isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode left, TreeNode right){
        if(left != null && right != null){
            if(left.val != right.val){
                return false;
            }
            return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
        }else if (left == null && right == null){
            return true;
        }

        return false;
    }

    /**
     * 101. Symmetric Tree
     * todo iterative
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {

        return true;
    }

    /**
     * 107. Binary Tree Level Order Traversal II
     * 二叉树层次遍历，逆序输出
     * pass    96%  100%
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> help = new LinkedList<>();
        help.offer(root);
        while (!help.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int n = help.size();
            for (int i = 0; i < n; i++) {
                TreeNode front = help.poll();
                list.add(front.val);
                if (front.left != null) help.offer(front.left);
                if (front.right != null) help.offer(front.right);
            }
            res.add(list);
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * 110. Balanced Binary Tree
     * 判断平衡二叉树
     * pass
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null)   return true;
        int leftH = height_re(root.left);
        int rightH = height_re(root.right);
        return Math.abs(leftH - rightH) < 2 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 求树的高度
     * recursive
     * 递归求解
     * @param root
     * @return
     */
    private int height_re(TreeNode root) {
        if (root == null)   return 0;
        return Math.max(height_re(root.left), height_re(root.right)) + 1;
    }

    /**
     * 求树的高度
     * iterative
     * 层次遍历
     * @param root
     * @return
     */
    private int height_iter(TreeNode root){
        if (root == null)   return 0;
        int h = 0;
        Queue<TreeNode> help = new LinkedList<>();
        help.offer(root);
        while (!help.isEmpty()){
            h++;
            for (int i = 0, n = help.size(); i < n; i++) {
                TreeNode node = help.poll();
                if (node.left != null)    help.offer(node.left);
                if (node.right != null)     help.offer(node.right);
            }
        }
        return h;
    }

    /**
     * 111. Minimum Depth of Binary Tree
     * 二叉树最小深度
     * pass
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if(root == null)    return 0;
        int lh = minDepth(root.left);
        int rh = minDepth(root.right);
        if(lh == 0 || rh == 0){
            return Math.max(lh, rh) + 1;
        }
        return Math.min(lh, rh) + 1;
    }

    /**
     * 112. Path Sum
     * 是否具有一条路径使得根节点到叶子结点的权值和为sum
     * pass     100%    68%
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root != null){
            if (root.left == null && root.right == null && root.val == sum)    return true;
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
        return false;
    }

    /**
     * 199. Binary Tree Right Side View
     * 二叉树右视图从上到下的结点
     * pass     99%  100%
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)   return list;
        Queue<TreeNode> help = new LinkedList<>();
        help.offer(root);
        while (!help.isEmpty()){
            for (int i = 0, n = help.size(); i < n; i++) {
                TreeNode node = help.poll();
                if (i == 0){
                    list.add(node.val);
                }

                if (node.right != null) help.offer(node.right);
                if (node.left != null)  help.offer(node.left);
            }
        }
        return list;
    }

    /**
     * 257. Binary Tree Paths
     * 二叉树根节点到叶子结点的所有路径
     * pass     100%  100%
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root != null)   binaryTreePathsHelper(root, "", res);
        return res;
    }

    private void binaryTreePathsHelper(TreeNode root, String path, List<String> res) {
        if (root.left == null && root.right == null)    res.add(path + root.val);
        if (root.left != null)  binaryTreePathsHelper(root.left, path + root.val + "->", res);
        if (root.right != null) binaryTreePathsHelper(root.right, path + root.val + "->", res);
    }

    /**
     * 437. Path Sum III
     * 路径（起始节点不需要为根节点）权值和为sum的个数
     * pass
     * 1. 求以根节点为起始的路径个数
     * 2. 递归求左右子树的满足条件的路径个数
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null)   return 0;
        return pathSumHelper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private int pathSumHelper(TreeNode root, int sum) {
        if (root == null)   return 0;
        return (root.val == sum ? 1 : 0) + pathSumHelper(root.left, sum - root.val) + pathSumHelper(root.right, sum - root.val);
    }

    /**
     * 501. Find Mode in Binary Search Tree
     * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
     * Assume a BST is defined as follows:
     * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
     * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
     * Both the left and right subtrees must also be binary search trees.
     * For example:
     * Given BST [1,null,2,2],
     *    1
     *     \
     *      2
     *     /
     *    2
     * return [2].
     * Note: If a tree has more than one mode, you can return them in any order.
     * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
     * pass     99%  100%
     * 二叉搜索树出现最多的结点值     中序遍历   二叉搜索树-》中序即有序
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        if (root == null)   return new int[0];

        List<Integer> list = new ArrayList<>();
        findModeHelper(root, list);

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++)   res[i] = list.get(i);

        return res;
    }

    int preVal = -1, currCnt = 0, maxCnt = 0;
    private void findModeHelper(TreeNode root, List<Integer> list) {
        if(root == null)    return;

        findModeHelper(root.left, list);

        if(root.val == preVal){
            currCnt++;
        }else {
            preVal = root.val;
            currCnt = 1;
        }
        if(currCnt > maxCnt){
            list.clear();
            list.add(preVal);
            maxCnt = currCnt;
        }else if (currCnt == maxCnt && preVal != -1){
            list.add(preVal);
        }

        findModeHelper(root.right, list);
    }


    /**
     * 572. Subtree of Another Tree
     * t是否是树s的一部分
     * 1. s==null && t==null  return true
     * 2. s!=null && t==null  return true
     * 3. s==null && t!=null  return false
     * 4. s!=null && t!=null
     *      a. s == t   return true
     *      b. t is the subtree of s.left   return true
     *      c. t is the subtree of s.right  return true
     * pass 92%  95%
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(t == null)   return true;
        if(s == null)   return false;
        return isEqual(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

//    判断两棵树是否相等，节点和对应的值均相等
    private boolean isEqual(TreeNode s, TreeNode t) {
        if(s != null && t != null){
            if (s.val != t.val){
                return false;
            }
            return isEqual(s.left, t.left) && isEqual(s.right, t.right);
        }else if (s == null && t == null){
            return true;
        }

        return false;
    }

    /**
     * 654. Maximum Binary Tree
     * 建立大顶堆
     * medium
     * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
     * The root is the maximum number in the array.
     * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
     * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
     * Construct the maximum tree by the given array and output the root node of this tree.
     * Example 1:
     * Input: [3,2,1,6,0,5]
     * Output: return the tree root node representing the following tree:
     *
     *       6
     *     /   \
     *    3     5
     *     \    /
     *      2  0
     *        \
     *         1
     * Note:
     * The size of the given array will be in the range [1,1000].
     * pass     99%  100%
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTreeHelper(nums, 0, nums.length - 1);
    }

    public TreeNode constructMaximumBinaryTreeHelper(int[] nums, int s, int e) {
       if(s > e)   return null;

       int m = s;
       for (int i=s+1; i<=e; i++)   if (nums[m] < nums[i])   m = i;

       TreeNode node = new TreeNode(nums[m]);
       node.left = constructMaximumBinaryTreeHelper(nums, s, m - 1);
       node.right = constructMaximumBinaryTreeHelper(nums, m + 1, e);
       return node;
    }

    /**
     * 671. Second Minimum Node In a Binary Tree
     * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes. More formally, the property root.val = min(root.left.val, root.right.val) always holds.
     * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
     * If no such second minimum value exists, output -1 instead.
     * Example 1:
     * Input:
     *     2
     *    / \
     *   2   5
     *      / \
     *     5   7
     *
     * Output: 5
     * Explanation: The smallest value is 2, the second smallest value is 5.
     * Example 2:
     * Input:
     *     2
     *    / \
     *   2   2
     *
     * Output: -1
     * Explanation: The smallest value is 2, but there isn't any second smallest value.
     * 给定一个二叉树，其所有结点只有零个或两个孩子结点，且所有父结点的权值为两个孩子结点权值的较小值
     * 求出倒数第二小的权值
     * 思路：树的根结点权值为最小权值
     * pass     100%  100%
     * @param root
     * @return
     */
    int minVal;
    long res = Long.MAX_VALUE;
    public int findSecondMinimumValue(TreeNode root ) {
        minVal = root.val;
        findSecondMinimumValueHelper(root);
        return res < Long.MAX_VALUE  ? (int)res : -1;
    }

    private void findSecondMinimumValueHelper(TreeNode root) {
        if (root != null){
            if (root.val > minVal && res > root.val){
                res = root.val;
            }else if (root.val == minVal){
                findSecondMinimumValueHelper(root.left);
                findSecondMinimumValueHelper(root.right);
            }
        }
    }

    /**
     * 687. Longest Univalue Path
     * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
     * The length of path between two nodes is represented by the number of edges between them.
     * Example 1:
     * Input:
     *               5
     *              / \
     *             4   5
     *            / \   \
     *           1   1   5
     * Output: 2
     * Example 2:
     * Input:
     *               1
     *              / \
     *             4   5
     *            / \   \
     *           4   4   5
     * Output: 2
     * Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
     * pass  75%  100%
     * todo read
     * @param root
     * @return
     */
    int longestUnivaluePathRet;
    public int longestUnivaluePath(TreeNode root) {
        longestUnivaluePathRet = 0;
        longestUnivaluePathHelper(root);
        return longestUnivaluePathRet;
    }

    private int longestUnivaluePathHelper(TreeNode root) {
        if (root == null)   return 0;

        int left = longestUnivaluePathHelper(root.left);
        int right = longestUnivaluePathHelper(root.right);

        int arrowLeft = 0, arrowRight = 0;
        if (root.left != null && root.left.val == root.val) {
            arrowLeft += left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            arrowRight += right + 1;
        }

        longestUnivaluePathRet = Math.max(longestUnivaluePathRet, arrowLeft + arrowRight);

        return Math.max(arrowLeft, arrowRight);
    }


    /**
     * 701. Insert into a Binary Search Tree
     * 向二叉搜索树中插入一个结点
     * 1. 判断val是否可以插入根节点
     * 2. 如果可以，则作为根节点的子节点插入，直接返回根节点
     * 3. 如果不可以，则将其插入根节点的左子树或者右子树
     * pass     100%  100%
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)   return null;

        if (val < root.val){
            if (root.left == null){
                TreeNode node1 = new TreeNode(val);
                root.left = node1;
            }else {
                insertIntoBST(root.left, val);
            }
        }else {
            if (root.right == null){
                TreeNode node2 = new TreeNode(val);
                root.right = node2;
            }else {
                insertIntoBST(root.right, val);
            }
        }

        return root;
    }

    /**
     * 814. Binary Tree Pruning
     * We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.
     * Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.
     * (Recall that the subtree of a node X is X, plus every node that is a descendant of X.)
     * Example 1:
     * Input: [1,null,0,0,1]
     * Output: [1,null,0,null,1]
     * Explanation:
     * Only the red nodes satisfy the property "every subtree not containing a 1".
     * The diagram on the right represents the answer.
     * Example 2:
     * Input: [1,0,1,0,0,0,1]
     * Output: [1,null,1,null,1]
     * Example 3:
     * Input: [1,1,0,1,1,0,1,0]
     * Output: [1,1,0,1,1,null,1]
     * Note:
     * The binary tree will have at most 100 nodes.
     * The value of each node will only be 0 or 1.
     * pass     100%  100%
     * 清除子树权值全为0的子树
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        if(pruneTreeHelper(root))   return null;
        if (pruneTreeHelper(root.left))     root.left = null;
        if (pruneTreeHelper(root.right))    root.right = null;
        pruneTree(root.left);
        pruneTree(root.right);
        return root;
    }

    /**
     * 判断树的节点值是否全部为0
     * @param root
     * @return
     */
    private boolean pruneTreeHelper(TreeNode root) {
        if (root == null)   return true;

        if (root.val == 1)   return false;

        return pruneTreeHelper(root.left) && pruneTreeHelper(root.right);
    }

    /**
     * 894. All Possible Full Binary Trees
     * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
     * Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.
     * Each node of each tree in the answer must have node.val = 0.
     * You may return the final list of trees in any order.
     * 完整二叉树是所有结点只有零个或两个孩子结点，返回给定结点数目的所有完整二叉树
     * todo
     * @param N
     * @return
     */
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        allPossibleFBTHelper(res, N);
        return res;
    }

    private TreeNode allPossibleFBTHelper(List<TreeNode> list, int n) {
        return null;
    }

    /**
     * 951. Flip Equivalent Binary Trees
     * pass
     * 1. 判断两棵树根节点的对应的孩子节点是否相等（值相等）
     * 2. 若不相等，则交换root1的左右孩子节点，若相等，则无需交换
     * 3. 判断root1和root2的对应子树是否满足flipEquiv，取两者之与
     * @param root1
     * @param root2
     * @return
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;

        if (root1 != null && root2 != null){
            if (root1.val != root2.val){
                return false;
            }
            if (!flipEquivHelper(root1, root2)){
                TreeNode tmp = root1.left;
                root1.left = root1.right;
                root1.right = tmp;
            }
            return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
        }

        return false;
    }

    /**
     * 判断孩子节点是否相等
     * @param root1
     * @param root2
     * @return
     */
    private boolean flipEquivHelper(TreeNode root1, TreeNode root2) {
//        if (root1.left != null && root1.right != null){
//            if (root2.left != null && root2.right != null){
//                if (root1.left.val == root2.left.val && root1.right.val == root2.right.val){
//                    return true;
//                }
//            }
//        }else if (root1.left != null && root1.right == null){
//            if (root2.left != null && root2.right == null){
//                if (root1.left.val == root2.left.val){
//                    return true;
//                }
//            }
//        }else if (root1.left == null && root1.right != null){
//            if (root2.left == null && root2.right != null){
//                if (root1.right.val == root2.right.val){
//                    return true;
//                }
//            }
//        }

        int leftv1 = root1.left == null ? -1 : root1.left.val;
        int righv1 = root1.right == null ? -1 : root1.right.val;
        int leftv2 = root2.left == null ? -1 : root2.left.val;
        int righv2 = root2.right == null ? -1 : root2.right.val;

        return leftv1 == leftv2 && righv1 == righv2 ? true : false;
    }

    /**
     * 1008. Construct Binary Search Tree from Preorder Traversal
     * pass
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorderHelper(preorder, 0, preorder.length-1);
    }

    private TreeNode bstFromPreorderHelper(int[] preorder, int s, int e) {
        if (e < s)  return null;
        if (e == s)     return new TreeNode(preorder[s]);

        TreeNode root = new TreeNode(preorder[s]);

//        i 起始值为 s + 1
        int i = s + 1;
        while (i <= e && preorder[i] < preorder[s])     i++;

        if (i == e + 1){
            root.left = bstFromPreorderHelper(preorder, s+1, e);
        }else {
            root.left = bstFromPreorderHelper(preorder, s+1, i-1);
            root.right = bstFromPreorderHelper(preorder, i, e);
        }

        return root;
    }

}
