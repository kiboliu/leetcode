//Given a singly linked list L: L0?L1?…?Ln-1?Ln,
//reorder it to: L0?Ln?L1?Ln-1?L2?Ln-2?…
//You must do this in-place without altering the nodes' values.

//First step: reverse the second half of the linked list.
//Second step: concat the first half to the second half.

public void reorderList(ListNode head) {
    if(head == null || head.next == null) return;
    ListNode h1 = head;
    ListNode h2 = head;
    //Find the middle ListNode h1.
    while(h2.next != null && h2.next.next != null){
        h1 = h1.next;
        h2 = h2.next.next;
    }
    ListNode middle = h1;
    ListNode current = h1.next;
    //Reverse the second half of linked list.
    while(current.next != null){
        ListNode temp = current.next;
        current.next = temp.next;
        temp.next = middle.next;
        middle.next = temp;
    }
    h1 = head;
    h2 = middle.next;
    //Concat the first half to the second half.
    while(h1 != middle){
        middle.next = h2.next;
        h2.next = h1.next;
        h1.next = h2;
        h1 = h2.next;
        h2 = middle.next;
    }
}