//Given a linked list, remove the nth node from the end of list and return its head.
//To do this in one pass.

//设置fast和slow两个指针，让fast先走n步，然后fast和slow一起走，当fast走到头是，slow就是要移除的那个点。
public ListNode removeNthFromEnd(ListNode head, int n) {

    ListNode start = new ListNode(0);
    ListNode slow = start, fast = start;
    slow.next = head;

    //Move fast in front so that the gap between slow and fast becomes n
    for(int i=1; i<=n+1; i++)   {
        fast = fast.next;
    }
    //Move fast to the end, maintaining the gap
    while(fast != null) {
        slow = slow.next;
        fast = fast.next;
    }
    //Skip the desired node
    slow.next = slow.next.next;
    return start.next;
}