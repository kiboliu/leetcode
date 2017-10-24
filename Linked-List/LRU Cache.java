//Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
//get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
//put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

//利用哈希表和双向链表。哈希表保存每个节点的值，可以基本保证O（1）时间查找节点；
//双向链表插入和删除的效率高；
//越靠近链表的头部，表示距离上次访问时间越短，尾部则表示距离上次访问时间最长；
//访问节点时，若该节点存在，则把该节点交换到链表的头部，同时更新哈希表中该节点的地址；
//插入节点时，如果cache的size达到了capacity，则删除尾部节点，同时也要删除哈希表里对应的项。新的节点插入头部

class LRUCache {
	//双向链表
    class DoubleLinkNode{
        int key;
        int val;
        DoubleLinkNode pre;
        DoubleLinkNode next;
    }
    //新节点一律插入头部
    private void addNode(DoubleLinkNode node){
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    private void removeNode(DoubleLinkNode node){
        DoubleLinkNode pre = node.pre;
        DoubleLinkNode next = node.next;
        pre.next = next;
        next.pre = pre;
    }
    //当get了某个节点时，应将其移至头部表示最近使用过了
    private void moveToHead(DoubleLinkNode node){
        this.removeNode(node);
        this.addNode(node);
    }
    //得到尾部节点
    private DoubleLinkNode popTail(){
        DoubleLinkNode res = tail.pre;
        this.removeNode(res);
        return res;
    }
    private HashMap<Integer, DoubleLinkNode> cache = new HashMap<Integer, DoubleLinkNode>();
    private int count;
    private int capacity;
    //有head和tail分别在双向链表的前后是为了不用判断null的情况
    private DoubleLinkNode head, tail;
    
    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;
        head = new DoubleLinkNode();
        head.pre = null;
        tail = new DoubleLinkNode();
        tail.next = null;
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        DoubleLinkNode node = cache.get(key);
        if(node == null){
            return -1;
        }
        this.moveToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        DoubleLinkNode node = cache.get(key);
        if(node == null){
            DoubleLinkNode newnode = new DoubleLinkNode();
            newnode.key = key;
            newnode.val = value;
            this.cache.put(key,newnode);
            this.addNode(newnode);
            count++;
            if(count > capacity){
                DoubleLinkNode tail = this.popTail();
                this.cache.remove(tail.key);
                count--;
            }
        }else{
            node.val = value;
            this.moveToHead(node);
        }
    }
}