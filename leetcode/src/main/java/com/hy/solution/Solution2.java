package com.hy.solution;

import com.hy.common.ListNode;

/**
 * Description: T2
 * Author: yhong
 * Date: 2023/12/14
 */
public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode cur = res;
        int c = 0;
        while (l1 != null || l2 != null) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            cur.next = new ListNode((a + b + c) % 10);
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            cur = cur.next;
            c = (a + b + c) / 10;
        }
        if (c != 0) {
            cur.next = new ListNode(1);
        }
        return res.next;
    }
}
