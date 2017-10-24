//Given a linked list, determine if it has a cycle in it.


//The key is that if the linked list has a cycle, a fast point(two steps once) must meet a slow point(one step once).

public boolean hasCycle(ListNode head) {
    if(head==null) return false;
    ListNode walker = head;
    ListNode runner = head;
    while(runner.next!=null && runner.next.next!=null) {
        walker = walker.next;
        runner = runner.next.next;
        if(walker==runner) return true;
    }
    return false;
}

//Update: Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
//Still find the cycle first, and now fast is equal to slow.
//Assume the linked list begin named a, cycle has a start named b, meet at a point named c.
//The fast walks (ab + bc + cb + bc), the slow walks (ab + bc).
//As the fast walk two times faster than slow, so (ab + bc + cb + bc) = 2*(ab + bc).
//Thus we know that ab = cb.
//So now we can have a pointer start from a and a pointer start from c
//They walk at the same speed.
//And their meeting point is b, which is also the answer.
public ListNode detectCycle(ListNode head) {
    if(head == null || head.next == null) return null;
    ListNode fast = head;
    ListNode slow = head;
    while(fast.next != null && fast.next.next != null){
        fast = fast.next.next;
        slow = slow.next;
        if(fast == slow){
            slow = head;
            while(slow != fast){
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
    }
    return null;
}