//Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
//For example,
//Given 1->2->3->3->4->4->5, return 1->2->5.
//Given 1->1->1->2->3, return 2->3.

//Use two listnode, cur is for going to the end of current duplicate list, and then connect pre with non-duplicate listnode.
public ListNode deleteDuplicates(ListNode head) {
    if(head==null) return null;
    ListNode FakeHead=new ListNode(0);
    FakeHead.next=head;
    ListNode pre=FakeHead;
    ListNode cur=head;
    while(cur!=null){
        while(cur.next!=null&&cur.val==cur.next.val){
            cur=cur.next;
        }
        if(pre.next==cur){
            pre=pre.next;
        }
        else{
            pre.next=cur.next;
        }
        cur=cur.next;
    }
    return FakeHead.next;
}

//Recursive method.
public ListNode deleteDuplicates(ListNode head) {
    if (head == null) return null;
    
    if (head.next != null && head.val == head.next.val) {
        while (head.next != null && head.val == head.next.val) {
            head = head.next;
        }
        return deleteDuplicates(head.next);
    } else {
        head.next = deleteDuplicates(head.next);
    }
    return head;
}
