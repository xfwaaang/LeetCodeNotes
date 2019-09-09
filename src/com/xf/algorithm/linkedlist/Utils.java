package com.xf.algorithm.linkedlist;

/**
 * @author xfwaaang
 * @create 2019-05-23 15:54
 */
public class Utils {
    /**
     * 反转链表
     * @param h
     * @return
     */
    public static ListNode reverseLinkedList(ListNode h) {
        if (h.next == null) return h;

        ListNode l1 = h, l2 = h.next;
        ListNode tmp = l2.next;
        l2.next = l1;
        l1.next = null;
        l1 = l2;
        l2 = tmp;

        while (l1 != null && l2 != null){
            tmp = l2.next;
            l2.next = l1;
            l1 = l2;
            l2 = tmp;
        }

        return l1;
    }
}
