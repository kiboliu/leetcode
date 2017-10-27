//拓展:Merge K sorted lists, 利用mergeTwoLists的方法，不断的拆分成两个的。（divide and conquer）
class Solution{
	public ListNode mergeKLists(ListNode[] lists){
		return partion(lists, 0, lists.length - 1);
	}
	public ListNode partion(ListNode[] lists, int start, int end) {
		if (start == enmd) return lists[start];
		if (start < end) {
			int mid = (start + end) / 2;
			ListNode l1 = partion(lists, start, mid);
			ListNode l2 = partion(lists, mid + 1, end);
			return merge(l1, l2);
		} else {
			return null;
		}
	}
	public ListNode merge(ListNode l1, ListNode l2){
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
//法二： 利用priority queue
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        List<ListNode> list=new ArrayList<ListNode>();
        for(int i=0;i<lists.length;i++)
            list.add(lists[i]);
        if (list==null||list.size()==0) return null;
        
        //实现PriorityQueue的Comparator，Comparator产生的值越小，即为PriorityQueue的头
        PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(list.size(),new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1,ListNode o2){
                if (o1.val<o2.val)
                    return -1;
                else if (o1.val==o2.val)
                    return 0;
                else 
                    return 1;
            }
        });
        
        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;
        
        for (ListNode node:list)
            if (node!=null)
                queue.add(node);
            
        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;
            
            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
    }
}