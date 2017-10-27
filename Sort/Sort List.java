//Sort a linked list in O(n log n) time using constant space complexity.

//divide and conquer. 将list的不断的分开，再merge
//常数空间且 O(nlogn)，单链表适合用归并排序，双向链表适合用快速排序。
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        // step 1. cut the list to two halves
        ListNode prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
          prev = slow;
          slow = slow.next;
          fast = fast.next.next;
        }

        prev.next = null;

        // step 2. sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // step 3. merge l1 and l2
        return merge(l1, l2);
    }
    public ListNode merge(ListNode l1, ListNode l2) {
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = merge(l1.next, l2);
			return l1;
		} else{
			l2.next = merge(l1, l2.next);
			return l2;
		}
    }
}