# Serialize And Deserialize Binary Tree

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Tree` `Depth-First Search` `Breadth-First Search` `Design` `Binary Tree`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
public class Codec {
    public String serialize(TreeNode root) { //server converts to string and sends to client
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            if(temp == null){
                sb.append(' ');
            }
            else{
                sb.append(temp.val);
                q.add(temp.left);
                q.add(temp.right);
            }
            sb.append(',');
        }
        return sb.toString();
    }
    public TreeNode deserialize(String data) {
       if(data.isEmpty() || data.charAt(0)==' ') return null;
       String[] arr = data.split(",");
       TreeNode root = createNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i=1;
        while(!q.isEmpty() && i<arr.length){
            TreeNode curr = q.poll();
            if(!arr[i].equals(" ")){
                curr.left = createNode(arr[i]);
                q.add(curr.left);
            }
            i++;
            if(!arr[i].equals(" ")){
                curr.right = createNode(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
       return root;
    }
    public TreeNode createNode(String s){
        return new TreeNode(Integer.parseInt(s));
    }
    // public void buildTree(TreeNode curr, String[] arr, int i){
    //    if(curr==null) return;
    //    int leftIndex = 2*i+1;
    //    int rightIndex = leftIndex+1;       
    //    if(leftIndex < arr.length && !arr[leftIndex].equals(" "))
    //         curr.left = new TreeNode(Integer.parseInt(arr[leftIndex]));
    //    if(rightIndex < arr.length && !arr[rightIndex].equals(" "))
    //         curr.right = new TreeNode(Integer.parseInt(arr[rightIndex]));
    //    buildTree(curr.left,arr,leftIndex);
    //    buildTree(curr.right,arr,rightIndex);
    // }
}


// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));

```

---

---
## Quick Revision
Serialize a binary tree into a string and deserialize it back into the original tree.

The solution uses pre-order traversal to serialize the tree and a combination of pre-order and level order traversal to deserialize it.

## Intuition
The key insight is that by using pre-order traversal, we can reconstruct the tree from the serialized string. Pre-order traversal visits the root node first, followed by its left subtree, and then its right subtree. This ordering allows us to reconstruct the tree by creating nodes in the same order they were visited.

## Algorithm

1. In `serialize()`, use pre-order traversal to serialize the tree:
	* Create a StringBuilder to store the serialized string.
	* Use a queue to perform level-order traversal of the tree.
	* For each node, append its value and a comma to the StringBuilder.
2. In `deserialize()`, use a combination of pre-order and level order traversal to deserialize the tree:
	* Split the input string into an array of strings.
	* Create the root node from the first string in the array.
	* Use a queue to perform level-order traversal of the tree, creating nodes as you go.

## Concept to Remember
• **Pre-order traversal**: visits the root node before its subtrees.
• **Level order traversal**: visits all nodes at a given level before moving on to the next level.
• **Tree serialization and deserialization**: converting a tree into a string and back again.

## Common Mistakes

* Forgetting to handle null values when serializing the tree.
* Not using a queue to perform level-order traversal in the deserialize() method.
* Failing to handle edge cases, such as an empty input string or a tree with only one node.

## Complexity Analysis
- Time: O(N) - reason: we visit each node once during serialization and deserialization.
- Space: O(N) - reason: we store the serialized string in memory.

## Commented Code
```java
public class Codec {
    public String serialize(TreeNode root) { 
        // Create a StringBuilder to store the serialized string
        StringBuilder sb = new StringBuilder();
        
        // Use a queue to perform level-order traversal of the tree
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            
            // If the node is null, append a space and a comma to the StringBuilder
            if(temp == null){
                sb.append(' ');
            }
            else{
                // Append the node's value and a comma to the StringBuilder
                sb.append(temp.val);
                
                // Add the left and right children of the current node to the queue
                q.add(temp.left);
                q.add(temp.right);
            }
            
            // Append a comma to the StringBuilder
            sb.append(',');
        }
        
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        // If the input string is empty or starts with a space, return null
        if(data.isEmpty() || data.charAt(0)==' ') return null;
        
        // Split the input string into an array of strings
        String[] arr = data.split(",");
        
        // Create the root node from the first string in the array
        TreeNode root = createNode(arr[0]);
        
        // Use a queue to perform level-order traversal of the tree, creating nodes as you go
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i=1;
        
        while(!q.isEmpty() && i<arr.length){
            TreeNode curr = q.poll();
            
            // If the current node has a left child, create it and add it to the queue
            if(!arr[i].equals(" ")){
                curr.left = createNode(arr[i]);
                q.add(curr.left);
            }
            i++;
            
            // If the current node has a right child, create it and add it to the queue
            if(!arr[i].equals(" ")){
                curr.right = createNode(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
        
        return root;
    }

    public TreeNode createNode(String s){
        // Create a new TreeNode from the given string
        return new TreeNode(Integer.parseInt(s));
    }
}
```

## Interview Tips

* Make sure to handle null values when serializing and deserializing the tree.
* Use a queue to perform level-order traversal in the deserialize() method.
* Test your solution with edge cases, such as an empty input string or a tree with only one node.

## Revision Checklist
- [ ] Handle null values when serializing and deserializing the tree.
- [ ] Use a queue to perform level-order traversal in the deserialize() method.
- [ ] Test your solution with edge cases.

## Similar Problems

* LeetCode 297: Serialize and Deserialize Binary Tree ( Mirror of this problem)
* LeetCode 105: Construct Binary Tree from Preorder Traversal
* LeetCode 106: Construct Binary Tree from Inorder Traversal
