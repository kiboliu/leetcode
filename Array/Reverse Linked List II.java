//Reverse a linked list from position m to n. Do it in-place and in one-pass.
//For example:
//Given 1->2->3->4->5->NULL, m = 2 and n = 4,
//return 1->4->3->2->5->NULL.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//自己的解法，用两个新建的节点，找到m和n的位置后，再用头插法将m～n的链表反转。但是新建了链表，没有in-place
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head.next == null) return head;
        ListNode usehead = new ListNode(0);
        usehead.next = head;
        ListNode help1 = usehead;
        ListNode help2 = usehead;
        int i = 0;
        int j = 0;
        label:
        while(help1 != null){
            if(i + 1 == m){
                while(help2 != null){
                    if(j == n){
                        ListNode previous = help2.next;
                        ListNode start = help1.next;
                        ListNode newhead = help2;
                        while(start != help2.next){
                            newhead = new ListNode(start.val);
                            newhead.next = previous;
                            previous = newhead;
                            start = start.next;
                        }
                        help1.next = previous;
                        break label;
                    }
                    help2 = help2.next;
                    j++;
                }
            }
            help1 = help1.next;
            i++;
        }
        return usehead.next;
    }
}

//链表反转的方法：
//1. 借助数组逆序反转：
/**
 * 使用数组拷贝的方式反转单链表
 */
public static ListNode reverseList1(ListNode head){
    if(head == null) return null;
    //转移到数组存储(这里用ArrayList方便些)
    List<ListNode> nodeList = new ArrayList<ListNode>();
    while (head != null) {
        nodeList.add(head);
        head = head.next;
    }
    int startIndex = nodeList.size() - 1;
    for (int i = startIndex; i >= 0; i--) {
        ListNode node = nodeList.get(i);
        if (i == 0) {
            node.next = null;
        } else {
            node.next = nodeList.get(i - 1);
        }
    }
    // 现在头结点是原来的尾节点
    head = nodeList.get(startIndex);
    return head;
}
//2. 头插法新建链表来反转
/**
 * 头插法建立新链表，达到反转目的
 */
public static ListNode reverseList2(ListNode head){
    if(head == null) return null;
    ListNode previous = null;   //记录上个节点
    ListNode newHead = null;    //头插法的新头节点
    while(head != null){
        newHead = new ListNode(head.val);   //新建头结点
        newHead.next = previous;
        previous = newHead;
        head = head.next;   //下一个节点
    }
    return newHead;
}

//3. 三个指针就地反转
/**
 * 使用三个指针原地反转单链表
 */
public static ListNode reverseList(ListNode head){
    if(head == null) return null;
    ListNode a = head;      //当前节点A
    ListNode b = head.next; //下个节点B
    ListNode temp;          //下下个节点C
    //头结点的指针先清空
    head.next = null;
    //有可能链表只有一个节点，所以需要看b是否为null
    while(b != null){
        temp = b.next;  // 记录C节点
        b.next = a;     // a->b 反向
        if(temp == null){
            break;
        }
        a = b;      //移动到下一个节点
        b = temp;
    }
    return b == null ? a : b;
}