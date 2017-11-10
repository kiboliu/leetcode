//Write a program to find the node at which the intersection of two singly linked lists begins.

//Also use the concept of linked list cycle. Concat the linked list A 's tail to B's head, get a new linked list newA.
//If they have intersection, the newA will have a cycle. 
//The cycle starting point is also the intersection node.
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if(headA == null || headB == null) return null;
    ListNode h1 = headA;
    while(h1.next != null){
        h1 = h1.next;
    }
    h1.next = headB;
    ListNode tail = h1;
    h1 = headA;
    headB = headA;
    while(headA.next != null && headA.next.next != null){
        headA = headA.next.next;
        headB = headB.next;
        if(headA == headB){
            headA = h1;
            while(headA != headB){
                headA = headA.next;
                headB = headB.next;
            }
            tail.next = null;
            return headA;
        }
    }
    tail.next = null;
    return null;
}