### linked list

#### 外观形态

##### [141. Linked List Cycle]()

设置快指针与慢指针
若存在环，快指针一定能再次追上慢指针

```java
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow)   return true;
        }

        return false;
    }
```

##### [234. Palindrome Linked List]()
> time: o(n) o(1)  

找到链表中间位置的节点，反转右半边的一半链表  
左半部分链表与右半部分链表一一比较
```java
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)   return true;

//        计算链表节点数
        int n = 0;
        ListNode p = head;
        while (p != null){
            n++;
            p = p.next;
        }

//        获得链表中间位置的节点
        ListNode center = head;
        for (int i=1; i<n/2; i++){
            center = center.next;
        }

//        区分链表数目奇偶性
        ListNode p1 = n % 2 == 0 ? center.next : center.next.next;
        ListNode p2 = p1.next;

//        截断左右两个链表
        center.next = null;

//        反转右半部分链表
        ListNode t;
        if (p2 != null){
            t = p2.next;
            p2.next = p1;
            p1.next = null;
            p1 = p2;
            p2 = t;
        }
        while (p2 != null){
            t = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = t;
        }

//        比较得到的两个链表
        while (head != null && p1 != null){
            if (head.val != p1.val)    return false;
            head = head.next;
            p1 = p1.next;
        }

        return true;
    }
```
> time: o(n) space: o(n)
```java
    public boolean isPalindrome_2(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }

        for (int i=0; i<list.size()/2; i++){
            if (list.get(i).intValue() != list.get(list.size()-i-1).intValue())  return false;
        }

        return true;
    }
```

#### 删除

##### [83. Remove Duplicates from Sorted List]()

> 给定一个已排序的链接列表，删除所有重复项，使每个元素只出现一次。

p1：前指针，p2：后指针
移动p2，直到两个数不相等，此时p2指向第一个不同值

```java
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p1 = head, p2 = head;
        while (p2 != null){
            while (p2.next != null && p2.val == p2.next.val) p2 = p2.next;
            p2 = p2.next;
            p1.next = p2;
            p1 = p2;
        }
        return head;
    }
```

##### [203. Remove Linked List Elements]()
> Input:  1->2->6->3->4->5->6, val = 6  
  Output: 1->2->3->4->5
```java
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)   return null;

//        先删除后面的节点，执行后，值为val的节点只会连续存在链表的前部
        ListNode p1 = head, p2 = head.next;
        while (p1 != null && p2 != null){
            if (p2.val == val){
                p1.next = p2.next;
                p2 = p2.next;
            }else {
                p1 = p1.next;
                p2 = p2.next;
            }
        }

//        删除前面值为val的节点
        while (head != null && head.val == val)    head = head.next;

        return head;
    }
```

#### 查找

##### [160. Intersection of Two Linked Lists]()

使用set，time O(n), space O(n)
```java
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> help = new HashSet<>();

        while (headA != null){
            help.add(headA);
            headA = headA.next;
        }
        while (headB != null){
            if (help.contains(headB))   return headB;
            headB = headB.next;
        }

        return null;
    }
    
```
暴力，time O(nm), space O(1)

```java
    public ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
        ListNode p = headB;
        while (headA != null){
            p = headB;
            while (p != null){
                if (headA == p) return p;
                p = p.next;
            }
            headA = headA.next;
        }
        return null;
    }
```

#### 多链表

合并
##### [21. Merge Two Sorted Lists]()

借鉴归并排序的思想

```java
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        head.next = null;
        ListNode p = head;
        ListNode p1 = l1, p2 = l2;

        while (p1 != null && p2 != null){
            ListNode tmp = new ListNode(0);
            tmp.next = null;
            if (p1.val < p2.val){
                tmp.val = p1.val;
                p1 = p1.next;
            }else {
                tmp.val = p2.val;
                p2 = p2.next;
            }
            p.next = tmp;
            p = p.next;
        }

        if (p1 != null)  p.next = p1;
        else if (p2 != null)  p.next = p2;

        return head.next;
    }
```