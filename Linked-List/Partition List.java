//Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
//You should preserve the original relative order of the nodes in each of the two partitions.

//For example,
//Given 1->4->3->2->5->2 and x = 3,
//return 1->2->2->4->3->5.

//The first one stores all nodes with val less than x , and the second queue stores all the rest nodes. Then concat these two queues. 
public ListNode partition(ListNode head, int x) {
    ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
    ListNode curr1 = dummy1, curr2 = dummy2;      //current tails of the two queues;
    while (head!=null){
        if (head.val<x) {
            curr1.next = head;
            curr1 = head;
        }else {
            curr2.next = head;
            curr2 = head;
        }
        head = head.next;
    }
    curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
    curr1.next = dummy2.next;
    return dummy1.next;
}

//Personal Idea: store the nodes more than x in another linkedlist, and correct the current linkedlist to remain nodes less than x. Much slower.
public ListNode partition(ListNode head, int x) {
    if(head == null || head.next == null) return head;
    ListNode temp = new ListNode(0);
    ListNode help = new ListNode(0);
    help.next = head;
    ListNode tail = temp;
    ListNode help2 = help;
    ListNode res = help;
    while(help.next != null){
        if(help.next.val >= x){
            while(help2.next.val != help.next.val){
                help2 = help2.next;
            }
            while(help.next != null && help.next.val >= x){
                temp.next = new ListNode(help.next.val);
                temp = temp.next;
                help = help.next;
                help2.next = help.next;
            }
            help2 = help2.next;
        }else{
            help = help.next;
        }
    }
    ListNode fin = res;
    while(res.next != null){
        res = res.next;
    }
    res.next = tail.next;
    return fin.next;
}