//A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

//Return a deep copy of the list.

//Use map to store the nodes and then assign next and random pointers.
//自己的想法是第一遍把node.label拷贝好，然后random还是连回去。接下来两重循环找到当前node在新链表里对应的random，将node的random连到新链表中的node来。但这样复杂度就上去了。
public RandomListNode copyRandomList(RandomListNode head) {
  if (head == null) return null;
  
  Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
  
  // loop 1. copy all the nodes
  RandomListNode node = head;
  while (node != null) {
    map.put(node, new RandomListNode(node.label));
    node = node.next;
  }
  
  // loop 2. assign next and random pointers
  node = head;
  while (node != null) {
    map.get(node).next = map.get(node.next);
    map.get(node).random = map.get(node.random);
    node = node.next;
  }
  
  return map.get(head);
}