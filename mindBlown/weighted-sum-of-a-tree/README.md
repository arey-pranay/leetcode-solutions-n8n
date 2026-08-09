# Weighted Sum Of A Tree

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Breadth-First Search` `Graph` `Depth-First Search` `Array`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public long weightedSum(int[] parent, int[] nums) {
        long ans = 0;
        long sum = 0;
        int n = nums.length;
        Queue<Integer> q = new LinkedList<>();
        List<List<Integer>> adj= new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int i=0;i<n;i++) if(parent[i]!=-1)adj.get(parent[i]).add(i);
        q.add(0);
        int depth = 0;
        while(!q.isEmpty()){
          int sz = q.size();
          depth++;
          for(int i=0;i<sz;i++){
            int curr = q.poll();
            sum += (long)nums[curr]*depth;
            for(int neigh : adj.get(curr)) q.add(neigh);
          }
        }
        for(int i=0;i<n;i++) ans+= (long)nums[i]*(depth+1);
        return ans-sum;
    }
}
```

---

---
## Quick Revision
This problem asks for the sum of node values multiplied by their depth, but with a twist on how depth is defined. We solve it by building an adjacency list and performing a Breadth-First Search (BFS) to calculate depths and sums.

## Intuition
The core idea is that the problem statement defines depth in a slightly unusual way: the root is at depth 1, its children at depth 2, and so on. A standard BFS naturally explores level by level, allowing us to track this depth. The problem also hints at a potential calculation for *all* nodes at the maximum depth, and then subtracting the sum of nodes that are *not* at the maximum depth but were incorrectly included in the initial "all nodes" sum. This suggests a two-pass approach or a clever adjustment.

The provided solution uses a clever trick: it calculates the sum of `value * depth` for *all* nodes using a BFS, where depth starts at 1. Then, it calculates a hypothetical sum where *all* nodes are considered to be at the maximum depth (`depth + 1`). By subtracting the first sum from the second, it effectively isolates the contribution of nodes at the maximum depth.

Let's break down the logic:
1.  `sum += (long)nums[curr]*depth;` This part calculates the weighted sum where `depth` is the actual depth of `curr` from the root (starting at 1).
2.  `for(int i=0;i<n;i++) ans+= (long)nums[i]*(depth+1);` This part calculates a sum where *every* node `i` is weighted by `depth + 1`. Here, `depth` is the depth of the *last processed level* in the BFS. This means `depth + 1` is effectively the depth of the *next* level, which would be the depth of any hypothetical children of the nodes at the current maximum depth. So, this line calculates the sum of `value * (max_actual_depth + 1)` for all nodes.
3.  `return ans - sum;`
    *   `ans` = sum of `nums[i] * (max_actual_depth + 1)` for all `i`.
    *   `sum` = sum of `nums[i] * actual_depth(i)` for all `i`.
    *   `ans - sum` = sum of `nums[i] * (max_actual_depth + 1 - actual_depth(i))` for all `i`.

    This is equivalent to:
    Sum over all nodes `i`: `nums[i] * (max_depth - actual_depth(i) + 1)`
    This is precisely the definition of the weighted sum where depth is measured from the leaves (depth 1 for leaves, depth 2 for their parents, etc.).

## Algorithm
1.  Initialize `ans` and `sum` to 0.
2.  Get the number of nodes `n` from `nums.length`.
3.  Create an adjacency list `adj` to represent the tree. Initialize it with `n` empty lists.
4.  Iterate through the `parent` array. For each node `i` (from 0 to `n-1`):
    *   If `parent[i]` is not -1 (meaning it's not the root), add `i` to the adjacency list of `parent[i]`.
5.  Create a queue `q` for BFS and add the root node (node 0) to it.
6.  Initialize `depth` to 0.
7.  Start a BFS loop that continues as long as the queue is not empty:
    *   Get the current size of the queue, `sz`. This represents the number of nodes at the current level.
    *   Increment `depth`. This `depth` will be the level number (1 for root, 2 for its children, etc.).
    *   Loop `sz` times to process all nodes at the current level:
        *   Dequeue a node `curr`.
        *   Add `nums[curr] * depth` to `sum`.
        *   For each neighbor `neigh` of `curr` in the adjacency list:
            *   Enqueue `neigh`.
8.  After the BFS, `depth` holds the maximum depth of the tree.
9.  Iterate through all nodes from 0 to `n-1`:
    *   Add `nums[i] * (depth + 1)` to `ans`. This calculates the sum where every node is weighted by one more than the maximum actual depth.
10. Return `ans - sum`.

## Concept to Remember
*   **Tree Representation:** Using an adjacency list is a standard way to represent trees (or graphs) for traversal algorithms.
*   **Breadth-First Search (BFS):** BFS is ideal for level-order traversal and finding shortest paths or depths in unweighted graphs/trees.
*   **Depth Calculation:** Understanding how to track depth during BFS, especially when the definition of depth might differ from standard conventions.
*   **Mathematical Manipulation:** The problem can be solved by cleverly manipulating sums to arrive at the desired weighted sum.

## Common Mistakes
*   **Incorrect Depth Initialization:** Starting depth at 0 instead of 1 for the root, or miscalculating the final depth.
*   **Off-by-One Errors:** In the second summation loop (`ans += ...`), using `depth` instead of `depth + 1` or vice-versa, leading to an incorrect final result.
*   **Not Handling Root:** Forgetting that `parent[i] == -1` indicates the root node.
*   **Building Adjacency List Incorrectly:** Swapping parent and child in the adjacency list creation.
*   **Integer Overflow:** Using `int` for sums when `long` is required, especially with large node values or depths.

## Complexity Analysis
*   **Time:** O(N) - We build the adjacency list in O(N) time. The BFS visits each node and edge once, which is O(N + E). In a tree, E = N-1, so it's O(N). The final loop to calculate `ans` is also O(N). Therefore, the total time complexity is O(N).
*   **Space:** O(N) - The adjacency list `adj` stores all the edges, which takes O(N) space for a tree. The queue `q` in BFS can store up to O(W) nodes, where W is the maximum width of the tree. In the worst case (a complete binary tree), W can be O(N). Thus, the space complexity is O(N).

## Commented Code
```java
class Solution {
    public long weightedSum(int[] parent, int[] nums) {
        // Initialize the final answer variable to 0.
        long ans = 0;
        // Initialize a variable to store the sum of (value * actual_depth) for all nodes.
        long sum = 0;
        // Get the total number of nodes in the tree.
        int n = nums.length;
        // Create a queue for Breadth-First Search (BFS).
        Queue<Integer> q = new LinkedList<>();
        // Create an adjacency list to represent the tree structure.
        // adj.get(i) will store a list of children of node i.
        List<List<Integer>> adj= new ArrayList<>();
        // Initialize the adjacency list with empty lists for each node.
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        // Build the adjacency list by iterating through the parent array.
        // If parent[i] is not -1, it means node i has a parent, so add i to the parent's adjacency list.
        for(int i=0;i<n;i++) if(parent[i]!=-1)adj.get(parent[i]).add(i);
        // Add the root node (node 0) to the queue to start the BFS.
        q.add(0);
        // Initialize the depth counter. The root is at depth 1.
        int depth = 0;
        // Start the BFS loop. It continues as long as there are nodes to process.
        while(!q.isEmpty()){
          // Get the number of nodes at the current level. This is important for level-order processing.
          int sz = q.size();
          // Increment the depth for the current level.
          depth++;
          // Process all nodes at the current level.
          for(int i=0;i<sz;i++){
            // Dequeue the current node.
            int curr = q.poll();
            // Add the weighted value of the current node to 'sum'.
            // The weight is the current 'depth'.
            sum += (long)nums[curr]*depth;
            // Enqueue all children of the current node for processing in the next level.
            for(int neigh : adj.get(curr)) q.add(neigh);
          }
        }
        // After BFS, 'depth' holds the maximum depth of the tree.
        // Now, calculate a hypothetical sum where every node is weighted by (max_depth + 1).
        // This is equivalent to weighting nodes from the leaves upwards.
        for(int i=0;i<n;i++) ans+= (long)nums[i]*(depth+1);
        // The final result is 'ans' (sum of value * (max_depth + 1)) minus 'sum' (sum of value * actual_depth).
        // This difference effectively calculates sum of value * (max_depth + 1 - actual_depth),
        // which is the weighted sum from the leaves.
        return ans-sum;
    }
}
```

## Interview Tips
1.  **Clarify Depth Definition:** Explicitly ask the interviewer to confirm the definition of depth (root at 1 or 0, leaf at what depth). This problem uses root at 1.
2.  **Explain the "Aha Moment":** Clearly articulate the intuition behind the `ans - sum` calculation. Explain how `depth + 1` in the second loop relates to leaf-level weighting.
3.  **Walk Through an Example:** Use a small tree example to trace the BFS and the calculation of `sum` and `ans` to demonstrate your understanding.
4.  **Discuss Edge Cases:** Mention handling the root node (parent is -1) and potential empty trees (though constraints usually prevent this).
5.  **Consider Alternatives:** Briefly mention that a DFS approach could also work, but BFS is often more intuitive for level-based problems.

## Revision Checklist
- [ ] Understand the problem statement and the specific definition of weighted sum.
- [ ] Implement tree construction using an adjacency list.
- [ ] Implement BFS for level-order traversal.
- [ ] Correctly track and use node depths during BFS.
- [ ] Understand and implement the `ans - sum` logic for leaf-weighted sum.
- [ ] Handle potential integer overflows by using `long`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)
*   [103. Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/)
*   [515. Find Largest Value in Each Tree Row](https://leetcode.com/problems/find-largest-value-in-each-tree-row/)
*   [116. Populating Next Right Pointers in Each Node](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/)

## Tags
`Tree` `Breadth-First Search` `Graph` `Depth-First Search` `Array`
