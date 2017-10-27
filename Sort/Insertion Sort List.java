//Sort a linked list using insertion sort.

class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null) return null;
        ListNode helper = new ListNode(0);
        ListNode cur = head;
        ListNode next = null;
        ListNode pre = helper;
        while (cur != null) {
            next = cur.next;
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            cur.next = pre.next;
            pre.next = cur;
            pre = helper;
            cur = next;
        }
        return helper.next;
    }
}

//IT CAN BE BETTER TO COPY LINKEDLIST TO ARRAY AND THEN USE QUICK SORT
//BECAUSE ARRAY HAS A MUCH BETTER CACHE PERFORMANCE THAN LINKEDLIST