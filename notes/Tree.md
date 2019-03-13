### Tree
#### 外观
[101. Symmetric Tree]
```java
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
```

[110. Balanced Binary Tree]
```java
    public boolean isBalanced(TreeNode root) {
        if (root == null)   return true;
        int leftH = height_re(root.left);
        int rightH = height_re(root.right);
        return Math.abs(leftH - rightH) < 2 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 求树的高度
     * recursive
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
```
[111. Minimum Depth of Binary Tree]
```java
    public int minDepth(TreeNode root) {
        if(root == null)    return 0;
        
        int lh = minDepth(root.left);
        int rh = minDepth(root.right);
        
        if(lh == 0 || rh == 0){
            return Math.max(lh, rh) + 1;
        }
        
        return Math.min(lh, rh) + 1;
    }
```
#### 遍历
[107. Binary Tree Level Order Traversal II]
```java
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
```


#### 搜索
[501. Find Mode in Binary Search Tree]
```java
    public int[] findMode(TreeNode root) {
        if (root == null)   return new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        preOrder(root, map);
        int m = Collections.max(map.values());
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == m){
                list.add(entry.getKey());
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void preOrder(TreeNode root, Map<Integer, Integer> map) {
        if (root != null){
            if(!map.containsKey(root.val)){
                map.put(root.val, 0);
            }
            map.put(root.val, map.get(root.val) + 1);
            preOrder(root.left, map);
            preOrder(root.right, map);
        }
    }

```

[572. Subtree of Another Tree]()
```java
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
        }else if (s != null && t == null){
            return false;
        }else if(s == null && t != null){
            return false;
        }
    
        return true;
    }
```
#### 构造或更新
[654. Maximum Binary Tree]()
```java
    public TreeNode constructMaximumBinaryTree(int[] nums) {
            return constructMaximumBinaryTreeHelper(nums, 0, nums.length - 1);
        }
    
    public TreeNode constructMaximumBinaryTreeHelper(int[] nums, int s, int e) {
       if(s > e)   return null;
    
       int m = s;
       for (int i=s+1; i<=e; i++){
           if (nums[m] < nums[i])   m = i;
       }
    
       TreeNode node = new TreeNode(nums[m]);
       node.left = constructMaximumBinaryTreeHelper1(nums, s, m - 1);
       node.right = constructMaximumBinaryTreeHelper1(nums, m + 1, e);
       return node;
    }
```

[701. Insert into a Binary Search Tree]()
```java
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
```
[814. Binary Tree Pruning]
```java
/**
     * 814. Binary Tree Pruning
     * pass
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
        if (root != null){
            if (root.val == 1){
                return false;
            }
            return pruneTreeHelper(root.left) && pruneTreeHelper(root.right);
        }
        return true;
    }
```

[1008. Construct Binary Search Tree from Preorder Traversal](https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/)
```java
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
```
