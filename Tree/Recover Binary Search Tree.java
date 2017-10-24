//Two elements of a binary search tree (BST) are swapped by mistake.
//Recover the tree without changing its structure.

class Solution {
    TreeNode first = null;
    TreeNode second = null;
    TreeNode successorfirst = null;
    // The reason for this initialization is to avoid null pointer exception in the first comparison when prevElement has not been initialized
    TreeNode preNode = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        
        if( root == null ) return;
    
        traverseTree(root);
    
        int tmp = first.val;
        if( second == null ) {
            first.val = successorfirst.val;
            successorfirst.val = tmp;
        }else {
          first.val = second.val;
          second.val = tmp;   
        }
    }
    // use the inorder traversal
    public void traverseTree( TreeNode node ) {
    
        if( node == null ) return;
    
        traverseTree( node.left ); 
    
        if( preNode.val > node.val ) {
          if( first == null ) {
              first = preNode;
              successorfirst = node;
          }else {
                // the condition that two adjacent elements swap, 123456 -> 124356. So there just a pair of digits that the preNode is larger than current Node.
              if( preNode == successorfirst ) {
                second = node;  
              }else if( preNode.val > successorfirst.val  ) {
                second = node;    
              }else {
                second = preNode;
              }
          }
        }
        preNode = node;

        traverseTree( node.right );
    }
}