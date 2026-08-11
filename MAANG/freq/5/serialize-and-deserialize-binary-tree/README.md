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
}


// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));

```

---

---
## Quick Revision
This problem asks to convert a binary tree into a string and vice-versa.
We can solve this using a level-order traversal (BFS) for serialization and then reconstructing the tree from the serialized string.

## Intuition
The core idea is to represent the tree's structure and node values in a linear format (a string) that can be easily transmitted or stored, and then reverse this process to rebuild the exact same tree. A level-order traversal is a natural fit because it processes the tree layer by layer, making it straightforward to reconstruct. By including markers for null nodes, we preserve the tree's shape.

## Algorithm
**Serialization:**
1. Initialize an empty `StringBuilder` to store the serialized string.
2. Initialize a `Queue` for level-order traversal and add the `root` node.
3. While the queue is not empty:
    a. Dequeue a `TreeNode`.
    b. If the node is `null`, append a special marker (e.g., ' ') to the `StringBuilder`.
    c. If the node is not `null`, append its `val` to the `StringBuilder`. Then, enqueue its `left` and `right` children (even if they are `null`).
    d. Append a delimiter (e.g., ',') after each node's representation.
4. Return the `StringBuilder`'s string.

**Deserialization:**
1. Handle the edge case of an empty or null input string.
2. Split the input `data` string by the delimiter to get an array of node values/markers.
3. Create the `root` node from the first element of the array.
4. Initialize a `Queue` for level-order reconstruction and add the `root` node.
5. Initialize an index `i` to 1 (to start processing from the second element of the array).
6. While the queue is not empty and `i` is within the bounds of the array:
    a. Dequeue the current node.
    b. If the element at `arr[i]` is not the null marker:
        i. Create a left child node from `arr[i]`.
        ii. Assign it to `curr.left`.
        iii. Enqueue the left child.
    c. Increment `i`.
    d. If the element at `arr[i]` is not the null marker:
        i. Create a right child node from `arr[i]`.
        ii. Assign it to `curr.right`.
        iii. Enqueue the right child.
    e. Increment `i`.
7. Return the `root` node.

## Concept to Remember
*   **Binary Tree Traversal:** Understanding Breadth-First Search (BFS) or Level-Order Traversal is crucial for both serialization and deserialization.
*   **Tree Reconstruction:** The ability to build a tree from a linear representation, maintaining parent-child relationships.
*   **Handling Null Nodes:** Explicitly representing `null` children is essential to preserve the tree's exact structure.
*   **String Manipulation:** Efficiently converting between string representations and node values, and using delimiters.

## Common Mistakes
*   **Not handling null nodes:** Failing to append a marker for `null` children will lead to an ambiguous serialization and incorrect deserialization.
*   **Incorrect delimiter usage:** Using a delimiter that might appear within node values (if values were strings) or not using a delimiter at all can cause parsing errors.
*   **Off-by-one errors in indexing:** Mismanaging the index when iterating through the split string during deserialization.
*   **Not clearing the queue properly:** In some recursive approaches, not properly managing the state of the queue or recursion stack.
*   **Integer parsing errors:** Assuming all parts of the string are valid integers without checking for the null marker.

## Complexity Analysis
*   **Time:** O(N) - reason: Both serialization and deserialization visit each node and process each element in the serialized string exactly once. N is the number of nodes in the tree.
*   **Space:** O(N) - reason: In the worst case (a complete binary tree), the queue can hold up to N/2 nodes during BFS. The `StringBuilder` also stores N node values and N null markers, leading to O(N) space.

## Commented Code
```java
// Definition for a binary tree node.
// public class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;
//     TreeNode(int x) { val = x; }
// }

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // Use StringBuilder for efficient string concatenation.
        StringBuilder sb = new StringBuilder();
        // Use a Queue for Level Order Traversal (BFS).
        Queue<TreeNode> q = new LinkedList<>();
        // Start the traversal from the root.
        q.add(root);

        // Continue as long as there are nodes to process in the queue.
        while(!q.isEmpty()){
            // Get the next node from the front of the queue.
            TreeNode temp = q.poll();
            // Check if the current node is null.
            if(temp == null){
                // Append a space character to represent a null node.
                sb.append(' ');
            }
            else{
                // Append the value of the current node.
                sb.append(temp.val);
                // Add the left child to the queue, even if it's null.
                q.add(temp.left);
                // Add the right child to the queue, even if it's null.
                q.add(temp.right);
            }
            // Append a comma as a delimiter after each node's representation.
            sb.append(',');
        }
        // Return the final serialized string.
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
       // If the data is empty or starts with a null marker, return null.
       if(data.isEmpty() || data.charAt(0)==' ') return null;
       // Split the input string by the comma delimiter into an array of strings.
       String[] arr = data.split(",");
       // Create the root node from the first element of the array.
       TreeNode root = createNode(arr[0]);
       // Use a Queue for Level Order Reconstruction.
        Queue<TreeNode> q = new LinkedList<>();
        // Add the root node to the queue to start reconstruction.
        q.add(root);
        // Initialize an index to iterate through the array, starting from the second element.
        int i=1;
        // Continue as long as there are nodes in the queue and elements in the array to process.
        while(!q.isEmpty() && i<arr.length){
            // Get the current parent node from the front of the queue.
            TreeNode curr = q.poll();
            // Check if the next element in the array represents a valid node (not a null marker).
            if(!arr[i].equals(" ")){
                // Create the left child node.
                curr.left = createNode(arr[i]);
                // Add the newly created left child to the queue for its children to be processed later.
                q.add(curr.left);
            }
            // Move to the next element in the array for the right child.
            i++;
            // Check if the next element in the array represents a valid node (not a null marker).
            if(!arr[i].equals(" ")){
                // Create the right child node.
                curr.right = createNode(arr[i]);
                // Add the newly created right child to the queue for its children to be processed later.
                q.add(curr.right);
            }
            // Move to the next element in the array for the next node's children.
            i++;
        }
       // Return the reconstructed root node.
       return root;
    }

    // Helper function to create a TreeNode from a string value.
    public TreeNode createNode(String s){
        // Convert the string to an integer and create a new TreeNode.
        return new TreeNode(Integer.parseInt(s));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
```

## Interview Tips
*   **Explain your traversal choice:** Clearly articulate why BFS (level-order) is suitable for this problem, emphasizing its systematic layer-by-layer processing.
*   **Discuss null handling:** Highlight how you represent and handle `null` nodes, as this is critical for preserving tree structure.
*   **Walk through an example:** Use a small binary tree (e.g., 3 nodes) to demonstrate the serialization and deserialization process step-by-step.
*   **Consider edge cases:** Mention how you handle an empty tree or a tree with only a root node.
*   **Clarify the delimiter:** Ensure the chosen delimiter is unambiguous and won't conflict with potential node values if they were strings.

## Revision Checklist
- [ ] Understand the problem: serialize to string, deserialize from string.
- [ ] Choose a traversal strategy (BFS is common and effective).
- [ ] Implement serialization with null markers and delimiters.
- [ ] Implement deserialization, parsing the string and reconstructing the tree.
- [ ] Handle edge cases (empty tree, empty string).
- [ ] Analyze time and space complexity.
- [ ] Practice with different tree structures.

## Similar Problems
*   LeetCode 297: Serialize and Deserialize Binary Tree (This problem)
*   LeetCode 102: Binary Tree Level Order Traversal
*   LeetCode 103: Binary Tree Zigzag Level Order Traversal
*   LeetCode 116: Populating Next Right Pointers in Each Node
*   LeetCode 117: Populating Next Right Pointers in Each Node II

## Tags
`Tree` `Depth-First Search` `Breadth-First Search` `String` `Design` `Binary Tree`
