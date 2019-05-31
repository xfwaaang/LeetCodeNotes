package com.xf.linkedlist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xfwaaang
 * @create 2019-05-11 11:49
 * Linked List
 */
public class Solution {
    /**
     * 21. Merge Two Sorted Lists
     * 合并两个有序链表
     * pass  80%  97%
     * 借鉴归并排序的思想
     * @param l1
     * @param l2
     * @return
     */
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

    /**
     * 23. Merge k Sorted Lists
     * pass  99%  56%
     * 分治法
     * 最终转化为合并两个有序链表，借鉴归并排序的思想，即：21. Merge Two Sorted Lists
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)  return null;
        return mergeKListsHelper(lists, 0, lists.length-1);
    }

    private ListNode mergeKListsHelper(ListNode[] lists, int s, int e) {
        if (s > e)  return null;
        if (s == e) return lists[s];

        int m = (s + e) / 2;

        ListNode l1 = mergeKListsHelper(lists, s, m);
        ListNode l2 = mergeKListsHelper(lists, m+1, e);

        return mergeKListsMerge(l1, l2);
    }

    /**
     * 合并两个有序链表
     * @param l1
     * @param l2
     * @return
     */
    private ListNode mergeKListsMerge(ListNode l1, ListNode l2) {
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

    /**
     * 24. Swap Nodes in Pairs
     * 交换相邻结点
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     * pass  100%  100%
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)   return head;

        // 交换前两个结点
        ListNode t1 = head.next;
        ListNode t2 = head.next.next;
        head.next.next = head;
        head.next = t2;
        head = t1;

        // 交换剩余结点
        ListNode p = head.next;     // p 指向当前 pair 的后一个结点
        ListNode p1, p2;
        while (p.next != null && p.next.next != null){
            p1 = p.next;
            p2 = p.next.next;
            p1.next = p2.next;
            p2.next = p1;
            p.next = p2;

            p = p.next.next;
        }

        return head;
    }

    /**
     * 61. Rotate List
     * Medium
     * Given a linked list, rotate the list to the right by k places, where k is non-negative.
     * Example 1:
     * Input: 1->2->3->4->5->NULL, k = 2
     * Output: 4->5->1->2->3->NULL
     * Explanation:
     * rotate 1 steps to the right: 5->1->2->3->4->NULL
     * rotate 2 steps to the right: 4->5->1->2->3->NULL
     * pass  65%  100%
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)   return null;

//        计算链表结点数目
        int n = 0;
        ListNode p = head;
        while (p != null){
            n++;
            p = p.next;
        }

//        考虑k大于n
        k = k % n;

        while (k-- > 0){
//            使p移动到倒数第二个结点
            p = head;
            while (p.next.next != null)    p = p.next;

            ListNode t = p.next;
            p.next = null;
            t.next = head;
            head = t;
        }

        return head;
    }

    /**
     * 61. Rotate List
     * Input: 1->2->3->4->5->NULL, k = 2
     * Output: 4->5->1->2->3->NULL
     * pass  100%  100%
     * 旋转操作可以通过 将链表后 k%n 个结点按原序移到头部 实现
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null)   return head;

//        计算链表结点数目
        int n = 0;
        ListNode p = head;
        while (p != null){
            n++;
            p = p.next;
        }

//        考虑k大于n
        k = k % n;

//        k == 0，直接返回head
        if(k == 0)  return head;

        int m = 0;
//        p1指向链表第 n-k 个结点，p2 指向链表尾结点
        ListNode p1 = head, p2 = head;
        p = head;
        while (p != null){
            m++;
            if (m == n - k){
                p1 = p;
            }else if (m == n){
                p2 = p;
            }
            p = p.next;
        }

//        将后 k 个结点按原序移到链表头部
        ListNode t = p1.next;
        p1.next = null;
        p2.next = head;
        head = t;

        return head;
    }

    /**
     * 82. Remove Duplicates from Sorted List II
     * Medium
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
     * Example 1:
     * Input: 1->2->3->3->4->4->4->5
     *  1. 0->1->2->3->3->4->4->4->5
     *  2. 0->1->2->4->4->4->5
     *  3. 0->1->2->5
     *  4. 1->2->5
     * Output: 1->2->5
     * Example 2:
     * Input: 1->1->1->2->3  ---  0->1->1->1->2->3
     * Output: 2->3
     * pass  62%  99%
     * 构造一个虚拟头结点，指向原来的头结点
     * 避免头结点存在重复项的情况
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null)  return head;

//        构造一个虚拟头结点，指向原来的头结点
        ListNode p = new ListNode(0);
        p.next = head;

//        依次指向前三个结点
        ListNode p1 = p, p2 = p.next, p3 = p.next.next;

//        当前是否存在重复项
        boolean flag;
        while (p3 != null){
            flag = false;
            while (p3 != null && p3.val == p2.val){
                flag = true;
                p3 = p3.next;
            }

            if (flag){
//                存在重复项，将其截断
                p1.next = p3;
                if (p3 == null)    break;
                p2 = p1.next;
                p3 = p2.next;
            }else {
                p1 = p1.next;
                p2 = p2.next;
                p3 = p3.next;
            }
        }

        return p.next;
    }

    /**
     * 83. Remove Duplicates from Sorted List
     * 给定一个已排序的链接列表，删除所有重复项，使每个元素只出现一次。
     * pass   100%  98%
     * p1：前指针，p2：后指针
     * 移动p2，直到两个数不相等，此时p2指向第一个不同值
     * @param head
     * @return
     */
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

    /**
     * 86. Partition List
     * Medium
     * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
     * You should preserve the original relative order of the nodes in each of the two partitions.
     * Example:
     * Input: head = 1->4->3->2->5->2, x = 3
     * Output: 1->2->2->4->3->5
     * todo
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {

        return head;
    }

    /**
     * 92. Reverse Linked List II
     * Medium
     * Reverse a linked list from position m to n. Do it in one-pass.
     * Note: 1 ≤ m ≤ n ≤ length of list.
     * Example:
     * Input: 1->2->3->4->5->NULL, m = 2, n = 4
     * Output: 1->4->3->2->5->NULL
     * pass   100%  100%
     * 只反转 m-n 部分结点链表，再重新连接形成一个链表，考虑 m=1 的特殊情况
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n)    return head;

        ListNode p1 = head, p2 = head, p = head;
        int k = 0;

//        使 p1 指向第 m-1 个结点，p2 指向第 n 个结点
        while (p != null){
            k++;
            if (k == m - 1){
                p1 = p;
            }else if (k == n){
                p2 = p;
                break;
            }
            p = p.next;
        }

//        t1 指向第 m 个结点，t2 指向第 n+1 个结点
        ListNode t1 = p1.next, t2 = p2.next;
        p2.next = null;     // 截断第n+1位的结点
        if (m == 1){
//            m=1时
            p = Utils.reverseLinkedList(head);
            head.next = t2;
            return p;
        }
//        p 为反转后子链表的头结点
        p = Utils.reverseLinkedList(t1);
//        衔接上反转后的链表
        p1.next = p;
        t1.next = t2;

        return head;
    }

    /**
     * 141. Linked List Cycle
     * pass  100%  99%
     * 设置快指针与慢指针
     * 若存在环，快指针一定能再次追上慢指针
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow)   return true;
        }

        return false;
    }

    /**
     * 142. Linked List Cycle II
     * Medium
     * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
     * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
     * Note: Do not modify the linked list.
     * Example 1:
     * Input: head = [3,2,0,-4], pos = 1
     * Output: tail connects to node index 1
     * Explanation: There is a cycle in the linked list, where tail connects to the second node.
     * todo
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        return null;
    }

    /**
     * 143. Reorder List
     * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
     * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
     * Given 1->2->3->4, reorder it to 1->4->2->3.
     * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
     * pass  100%  98%
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)   return;

        ListNode p = head;
        int n = 0;
        while (p != null){
            n++;
            p = p.next;
        }
        p = head;
//        得到后半部分链表
        for (int i=0; i<n/2; i++)   p = p.next;
//        反转后半部分链表
        ListNode head2 = Utils.reverseLinkedList(p.next);
        p.next = null;

        while (head2 != null){
            ListNode tmp = head2.next;
            head2.next = head.next;
            head.next = head2;
            head = head.next.next;
            head2 = tmp;
        }
    }

    /**
     * 147. Insertion Sort List
     * 采用插入排序的思想排序链表
     * Sort a linked list using insertion sort.
     * Input: 4->2->1->3
     *  2->4->1->3
     * Output: 1->2->3->4
     * pass  72%  86%
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null)   return null;

//        head 指向当前有序链表的头结点，rear 指向其尾结点
//        p1 为有序链表的移动指针，p2 指向当前待插入结点
        ListNode p1, p2 = head.next, rear = head;
        while (p2 != null){
            p1 = head;

//            若当前待插入结点小于头结点，将其插入头部作为头结点
            if (p2.val <= p1.val){
                rear.next = p2.next;
                p2.next = p1;
                head = p2;
                p2 = rear.next;
                continue;
            }

//            寻找待插入结点在有序链表中的位置，一般情况满足：p1.next >= p2 > p1
            while (p1 != p2 && !(p2.val > p1.val && p2.val <= p1.next.val))     p1 = p1.next;

//            若p1移动到尾结点，说明不用执行插入操作，直接将p2接在其尾部，就可以形成有序链表
            if (p1 == rear){
                rear = rear.next;
                p2 = p2.next;
                continue;
            }

//            一般情况，需要将p2插入到有序链表的中间位置
//            接上剩余链表
            rear.next = p2.next;

//            插入操作
            p2.next = p1.next;
            p1.next = p2;

            p2 = rear.next;
        }
        return head;
    }

    /**
     * 148. Sort List
     * Sort a linked list in O(n log n) time using constant space complexity.
     * Input: 4->2->1->3
     * Output: 1->2->3->4
     * pass  34%  99%
     * 使用归并排序的思想
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return sortListHelper(head);
    }

    private ListNode sortListHelper(ListNode head) {
        if (head != null && head.next != null){
            int n = 0;
            ListNode p = head;
            while (p != null){
                n++;
                p = p.next;
            }
            p = head;
            for (int i=1; i<n/2; i++)   p = p.next;
            ListNode head2 = p.next;
            p.next = null;

            ListNode h1 = sortListHelper(head);
            ListNode h2 = sortListHelper(head2);

            return mergeTwoLists(h1, h2);   // 合并两个有序链表
        }

        return head;
    }

    /**
     * 160. Intersection of Two Linked Lists
     * pass   16%  21%
     * 使用set，time O(n), space O(n)
     * @param headA
     * @param headB
     * @return
     */
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

    /**
     * 160. Intersection of Two Linked Lists
     * pass   5%  5%
     * 暴力，time O(nm), space O(1)
     * @param headA
     * @param headB
     * @return
     */
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

    /**
     * 160. Intersection of Two Linked Lists
     * todo，time O(n)  space O(1)
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode_3(ListNode headA, ListNode headB) {

        return null;
    }

    /**
     * 203. Remove Linked List Elements
     * Input:  1->2->6->3->4->5->6, val = 6
     * Output: 1->2->3->4->5
     * pass  99%  90%
     * @param head
     * @param val
     * @return
     */
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

    /**
     * 234. Palindrome Linked List
     * pass  95%  99%  o(n) o(1)
     * 找到链表中间位置的节点，反转右半边的一半链表
     * 左半部分链表与右半部分链表一一比较
     * @param head
     * @return
     */
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

    /**
     * 234. Palindrome Linked List
     * pass  time: o(n) space: o(n)
     * @param head
     * @return
     */
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

    /**
     * 328. Odd Even Linked List
     * Input: 1->2->3->4->5->NULL
     *  1. 1->2->3->4->5->NULL
     *  2. 1->3->2->4->5->NULL
     *  3. 1->3->5->2->4->NULL
     * Output: 1->3->5->2->4->NULL
     * pass  100%  99%
     * 设置 p1 指向当前排好序列最后一个奇数项，p2 指向其最后一个偶数项，如上面例子第二步：p1 指向 3，p2 指向 4
     * 将 p2 的后继节点插入到 p1 的后面，即将 val为5的结点插入到 val为3的后面
     * 完成插入操作，更新p1和p2，此时p1指向5，p2指向NULL
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null)  return null;

        ListNode p1 = head, p2 = head.next;
        while (p2 != null){
            ListNode t = p2.next;
            if (t == null)  break;

            p2.next = t.next;   // 接上t后面的链表
            t.next = p1.next;   // 将t插入到p1后面
            p1.next = t;

            p1 = p1.next;
            p2 = p2.next;
        }

        return head;
    }

    /**
     * 445. Add Two Numbers II
     * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 8 -> 0 -> 7
     * pass  99%  74%
     * 1. 将两条链表反转
     * 2. 依次求和，构造新链表，此过程有两种情况：
     *   a. 链表等长
     *   b. 链表不等长，最终只会有一条链表还有剩余，继续求和（考虑进位），构造链表
     *   注意：若最后进位不等于零，需要再新构造一个结点
     * 3. 将得到的链表反转
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode lr1 = Utils.reverseLinkedList(l1);
        ListNode lr2 = Utils.reverseLinkedList(l2);

        int sum, carry = 0;
        final int k = 10;
        ListNode head = new ListNode(0);
        ListNode p = head;

        while (lr1 != null && lr2 != null){
            sum = lr1.val + lr2.val + carry;
            carry = sum / k;

            ListNode tmp = new ListNode(sum % k);
            tmp.next = null;
            p.next = tmp;
            p = p.next;

            lr1 = lr1.next;
            lr2 = lr2.next;
        }

        while (lr1 != null){
            sum = lr1.val + carry;
            carry = sum / k;

            ListNode tmp = new ListNode(sum % k);
            tmp.next = null;
            p.next = tmp;
            p = p.next;

            lr1 = lr1.next;
        }

        while (lr2 != null){
            sum = lr2.val + carry;
            carry = sum / k;

            ListNode tmp = new ListNode(sum % k);
            tmp.next = null;
            p.next = tmp;
            p = p.next;

            lr2 = lr2.next;
        }

//        注意：最后进位不等于零，需要再构造一个新结点
        if (carry != 0){
            ListNode tmp = new ListNode(carry);
            tmp.next = null;
            p.next = tmp;
        }

        return Utils.reverseLinkedList(head.next);
    }

    /**
     * 725. Split Linked List in Parts
     * Input:
     * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
     * Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
     * Explanation:
     * The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
     * pass  100%  99%
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
//        计算链表结点总数
        int n = 0;
        ListNode p = root;
        while (p != null){
            n++;
            p = p.next;
        }

//        计算每个子链表的大致结点数目
        int x = n / k;
        int y = n % k;
        ListNode[] res = new ListNode[k];

        int j = 0;
        while (root != null){
            res[j] = root;
            p = root;
//            前面的y个子链表结点数目较多，它们均比后面子链表结点数目多一个
            int t = y > 0 ? 1 : 0;
            for (int i=1; i<x+t; i++){
                p = p.next;
            }
            y--;
            j++;
//            更新头结点，截断刚划分好的子链表
            root = p.next;
            p.next = null;
        }

        return res;
    }

    /**
     * 817. Linked List Components
     * Input:
     * head: 0->1->2->3->4
     * G = [0, 3, 1, 4]
     * Output: 2
     * Explanation:
     * 0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.
     * todo
     * @param head
     * @param G
     * @return
     */
    public int numComponents(ListNode head, int[] G) {
        int n = 0;

        return n;
    }

    /**
     * 1019. Next Greater Node In Linked List
     * pass  15%  99%
     * @param head
     * @return
     */
    public int[] nextLargerNodes(ListNode head) {
        ListNode p;

        List<Integer> list = new ArrayList<>();
        while (head != null){
            p = head.next;

            // 寻找第一个大于当前节点值的节点
            while (p != null && p.val <= head.val)  p = p.next;
            if (p == null)  list.add(0);
            else    list.add(p.val);

            head = head.next;
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
