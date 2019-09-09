package com.xf.algorithm.dac;

/**
 * @author xfwaaang
 * @create 2019-05-08 17:24
 */
public class main {
    public static void main(String[] args){
        Solution solution = new Solution();
//        [[1,4,5],[1,3,4],[2,6]]
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        l1.next.next.next = null;

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        l2.next.next.next = null;

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);
        l3.next.next = null;

        ListNode[] lists = new ListNode[3];
        lists[0] = l1;
        lists[1] = l2;
        lists[2] = l3;

        solution.mergeKLists(lists);
    }
}
