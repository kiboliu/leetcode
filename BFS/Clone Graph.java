/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/

 /**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

 //DFS，一直递归往下做就好了，注意退出的条件是map里已有这个node
public class Solution {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
	    if (node == null) return null;
	    return cloneGraph(node, new HashMap<>());
	}

	private UndirectedGraphNode cloneGraph(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> visited) {
	    if (visited.containsKey(node)) return visited.get(node);
	    
	    UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
	    
	    visited.put(node, clone);
	    
	    for (UndirectedGraphNode neighbor : node.neighbors)
	        clone.neighbors.add(cloneGraph(neighbor, visited));
	    
	    return clone;
	}
}

//BFS
public class Solution {
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null) return null;
		UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
		HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
		map.put(newNode.label, newNode);
		LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
		queue.add(node);

		while(!queue.isEmpty()) {
			UndirectedGraphNode n = queue.pop();
			for (UndirectedGraphNode neighbor : n.neighbors) {
				if (!map.containsKey(neighbor.label)) {
					map.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
					queue.add(neighbor);
				}
				// add neighbor to new created nodes.
				map.get(n.label).neighbors.add(map.get(neighbor.label));
			}
		}

		return newNode;
	}
}