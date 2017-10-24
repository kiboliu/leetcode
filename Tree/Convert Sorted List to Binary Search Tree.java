//Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

//不用求出linkedlist长度的方法。利用slow&fast指针求中间值，变量为首尾的ListNode。

public class Solution{
	public TreeNode sortedListToBST (ListNode head) {
		if (head == null) return null;
		return toBST (head, null);
	}
	public TreeNode toBST (ListNode head, ListNode tail) {
		ListNode slow = head;
		ListNode fast = head;
		if (head == tail) return null;
		//求中间值，即为最终得到的slow.
		while (fast != tail && fast.next != tail) {
			fast = fast.next.next;
			slow = slow.next;
		}
		TreeNode thead = new TreeNode(slow.val);
		thead.left = toBST(head, slow);
		thead.right = toBST(slow.next, tail);
		return thead;
	}
}