# Serialize And Deserialize Binary Tree

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Tree` `Depth-First Search` `Breadth-First Search` `Design` `Binary Tree`  
**Time:**   
**Space:** 

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
This problem asks to convert a binary tree into a string and reconstruct it from the string.
We use a level-order traversal (BFS) for serialization and deserialization, marking null nodes with a special character.

## Intuition
The core idea is to represent the tree's structure and node values in a linear format (a string) that can be easily parsed back. A level-order traversal is a natural fit because it processes the tree layer by layer, making it straightforward to reconstruct the parent-child relationships. By using a delimiter (like a comma) and a placeholder for null nodes (like a space), we can uniquely represent the tree.

## Algorithm
**Serialization:**
1. Initialize an empty `StringBuilder` to store the serialized string.
2. Initialize a `Queue` for level-order traversal and add the `root` node.
3. While the queue is not empty:
    a. Dequeue a `TreeNode`.
    b. If the node is `null`, append a placeholder character (e.g., ' ') to the `StringBuilder`.
    c. If the node is not `null`, append its `val` to the `StringBuilder`, and then enqueue its `left` and `right` children.
    d. Append a delimiter (e.g., ',') after each node's value or placeholder.
4. Return the `StringBuilder`'s string representation.

**Deserialization:**
1. If the input `data` string is empty or starts with the null placeholder, return `null`.
2. Split the `data` string by the delimiter into an array of strings.
3. Create the `root` node from the first element of the array.
4. Initialize a `Queue` for level-order reconstruction and add the `root` node.
5. Initialize an index `i` to 1 (to start processing from the second element of the array).
6. While the queue is not empty and `i` is within the bounds of the array:
    a. Dequeue the current node.
    b. If the element at `arr[i]` is not the null placeholder:
        i. Create a new `TreeNode` from `arr[i]`.
        ii. Set it as the `left` child of the current node.
        iii. Enqueue this new left child.
    c. Increment `i`.
    d. If `i` is within bounds and the element at `arr[i]` is not the null placeholder:
        i. Create a new `TreeNode` from `arr[i]`.
        ii. Set it as the `right` child of the current node.
        iii. Enqueue this new right child.
    e. Increment `i`.
7. Return the `root` node.

## Concept to Remember
*   **Binary Tree Traversal:** Understanding level-order traversal (BFS) is crucial for both serialization and deserialization.
*   **Data Serialization/Deserialization:** The process of converting data structures into a format that can be stored or transmitted and then reconstructing them.
*   **Queue Data Structure:** Essential for implementing BFS efficiently.
*   **String Manipulation:** Parsing and building strings are key operations.

## Common Mistakes
*   **Handling Null Nodes:** Failing to explicitly represent `null` children in the serialized string leads to ambiguity and incorrect reconstruction.
*   **Delimiter Issues:** Choosing a delimiter that might appear in node values (if values were strings) or not handling consecutive delimiters correctly.
*   **Index Management in Deserialization:** Off-by-one errors or incorrect incrementing of the index when processing the array of string representations.
*   **Empty Tree/Invalid Input:** Not handling edge cases like an empty tree (`root == null`) or an empty/malformed input string.
*   **Integer Parsing Errors:** Assuming all string parts can be directly parsed into integers without checking for the null placeholder.

## Complexity Analysis
*   **Time:**
    *   **Serialization:** O(N), where N is the number of nodes in the tree. Each node is visited and processed exactly once.
    *   **Deserialization:** O(N), where N is the number of nodes in the tree. Each element in the serialized string is processed once to create nodes and add them to the queue.
*   **Space:**
    *   **Serialization:** O(N) in the worst case (a complete binary tree) for the queue and the `StringBuilder`.
    *   **Deserialization:** O(N) in the worst case for the queue and the reconstructed tree.

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
        // Start the traversal with the root node.
        q.add(root);

        // Continue as long as there are nodes to process in the queue.
        while(!q.isEmpty()){
            // Get the next node from the front of the queue.
            TreeNode temp = q.poll();

            // If the current node is null, append a placeholder.
            if(temp == null){
                sb.append(' '); // Using ' ' as a placeholder for null nodes.
            }
            // If the current node is not null, append its value.
            else{
                sb.append(temp.val); // Append the integer value of the node.
                // Add its left and right children to the queue for processing.
                // Even if children are null, they will be handled in the next iteration.
                q.add(temp.left);
                q.add(temp.right);
            }
            // Append a delimiter after each node's representation (value or placeholder).
            sb.append(',');
        }
        // Return the final serialized string.
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
       // Handle edge cases: empty string or string representing a null root.
       if(data.isEmpty() || data.charAt(0)==' ') return null;

       // Split the serialized string by the delimiter to get an array of node representations.
       String[] arr = data.split(",");
       // Create the root node from the first element of the array.
       TreeNode root = createNode(arr[0]);
       // Use a Queue for Level Order Reconstruction.
       Queue<TreeNode> q = new LinkedList<>();
       // Add the root node to the queue to start reconstruction.
       q.add(root);
       // Initialize an index to iterate through the array of node representations, starting from the second element.
       int i=1;

       // Continue as long as there are nodes in the queue to process and elements in the array.
       while(!q.isEmpty() && i<arr.length){
           // Get the current parent node from the queue.
           TreeNode curr = q.poll();

           // Process the left child.
           // Check if the current array element is not the null placeholder.
           if(!arr[i].equals(" ")){
               // Create the left child node.
               curr.left = createNode(arr[i]);
               // Add the newly created left child to the queue for its children to be processed later.
               q.add(curr.left);
           }
           // Move to the next element in the array for the right child.
           i++;

           // Process the right child.
           // Check if the current array element is not the null placeholder and we haven't run out of array elements.
           if(i<arr.length && !arr[i].equals(" ")){
               // Create the right child node.
               curr.right = createNode(arr[i]);
               // Add the newly created right child to the queue.
               q.add(curr.right);
           }
           // Move to the next element in the array for the next parent's children.
           i++;
       }
       // Return the reconstructed root of the binary tree.
       return root;
    }

    // Helper function to create a TreeNode from a string value.
    public TreeNode createNode(String s){
        // Parse the string to an integer and create a new TreeNode.
        return new TreeNode(Integer.parseInt(s));
    }

    // The commented out buildTree function below shows an alternative recursive approach for deserialization,
    // which is less common for this specific problem compared to the iterative BFS approach used above.
    // It would typically be used with a pre-order or in-order traversal serialization.
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
```

## Interview Tips
1.  **Explain your traversal choice:** Clearly articulate why BFS (level-order) is suitable for serialization/deserialization, especially for handling nulls and reconstructing the structure.
2.  **Handle nulls explicitly:** Emphasize how you represent `null` nodes in the string and how this is crucial for correct deserialization.
3.  **Walk through an example:** Use a small binary tree (e.g., 3 nodes) to demonstrate the serialization and deserialization process step-by-step.
4.  **Discuss edge cases:** Mention how you handle an empty tree, a single-node tree, and potential issues with the input string format.
5.  **Clarify the delimiter and null marker:** Ensure you and the interviewer are on the same page about what characters are used and why.

## Revision Checklist
- [ ] Understand the problem: serialize to string, deserialize from string.
- [ ] Choose a traversal: BFS (level-order) is common and effective.
- [ ] Implement `serialize`: use a queue, append values and null markers, use a delimiter.
- [ ] Implement `deserialize`: split string, use a queue, reconstruct nodes, handle nulls.
- [ ] Handle edge cases: empty tree, empty input string.
- [ ] Analyze time and space complexity.
- [ ] Practice writing the code without looking.

## Similar Problems
*   Convert Sorted Array to Binary Search Tree
*   Binary Tree Level Order Traversal
*   Serialize and Deserialize N-ary Tree

## Tags
`Tree` `Depth-First Search` `Breadth-First Search` `String` `Binary Tree`
