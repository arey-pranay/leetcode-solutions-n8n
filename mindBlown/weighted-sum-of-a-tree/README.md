# Weighted Sum Of A Tree

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Tree` `Breadth-First Search` `Graph` `Array`  
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
The core idea is that the problem statement defines depth in a slightly unusual way: the root is at depth 1, its children at depth 2, and so on. A standard BFS naturally explores level by level, allowing us to track this depth. The problem then asks for a weighted sum, where the weight is the depth. The provided solution seems to calculate two sums and then subtracts them. Let's break down why this might work.

The first sum calculated within the BFS (`sum`) seems to be the weighted sum where the root is at depth 1. The second loop calculates a sum where *all* nodes are considered to be at the maximum depth (`depth + 1`). Subtracting the first sum from the second effectively isolates the contribution of nodes at depths greater than 1, weighted by their actual depth relative to the root.

Consider a node `u` at depth `d` (root is depth 1).
- In the BFS sum (`sum`), `u` contributes `nums[u] * d`.
- In the second sum (`ans`), `u` contributes `nums[u] * (max_depth + 1)`.
- The difference for `u` is `nums[u] * (max_depth + 1) - nums[u] * d = nums[u] * (max_depth + 1 - d)`.

This doesn't immediately look like the desired weighted sum. Let's re-examine the problem statement's definition of depth. If the root is depth 1, its children depth 2, etc., then the weighted sum is `sum(nums[i] * depth[i])`.

The provided code calculates `sum` as the weighted sum with root at depth 1.
Then it calculates `ans` as `sum(nums[i] * (max_depth + 1))`.
The return value is `ans - sum`.

Let's trace with a simple example:
Tree: 0 (val=10) -> 1 (val=5)
parent = [-1, 0], nums = [10, 5]

1. Adjacency list: `adj[0] = [1]`, `adj[1] = []`
2. BFS starts with `q = [0]`, `depth = 0`.
3. Loop 1: `sz = 1`, `depth = 1`.
   - `curr = 0` (poll). `sum += nums[0] * 1 = 10 * 1 = 10`.
   - `adj[0]` has `1`. `q.add(1)`. `q = [1]`.
4. Loop 2: `sz = 1`, `depth = 2`.
   - `curr = 1` (poll). `sum += nums[1] * 2 = 5 * 2 = 10`. `sum = 10 + 10 = 20`.
   - `adj[1]` is empty. `q = []`.
5. BFS ends. `depth` is 2.
6. Second loop: `ans = 0`.
   - `i = 0`: `ans += nums[0] * (2 + 1) = 10 * 3 = 30`.
   - `i = 1`: `ans += nums[1] * (2 + 1) = 5 * 3 = 15`. `ans = 30 + 15 = 45`.
7. Return `ans - sum = 45 - 20 = 25`.

The expected weighted sum is `nums[0]*1 + nums[1]*2 = 10*1 + 5*2 = 10 + 10 = 20`.
The code returns 25. This indicates my initial interpretation of the code's logic might be flawed, or the code itself has a subtle issue or a different interpretation of "weighted sum".

Let's re-read the problem carefully. "The weighted sum of a tree is the sum of values of all nodes multiplied by their depth." The root is at depth 1.

The code calculates `sum` as `sum(nums[i] * depth_from_bfs[i])` where `depth_from_bfs` starts at 1 for the root. This is exactly the weighted sum.
Then it calculates `ans` as `sum(nums[i] * (max_depth + 1))`.
And returns `ans - sum`.

This subtraction `ans - sum` is `sum(nums[i] * (max_depth + 1)) - sum(nums[i] * depth_from_bfs[i])`.
This simplifies to `sum(nums[i] * (max_depth + 1 - depth_from_bfs[i]))`.

This is NOT the weighted sum. It seems the provided solution might be incorrect or solving a slightly different problem.

Let's assume the problem *intended* to ask for something that this calculation solves.
If the problem was "sum of (value * (max_depth + 1 - depth))", then the code would be correct.

However, if we strictly follow "weighted sum of a tree is the sum of values of all nodes multiplied by their depth", the BFS part calculating `sum` is correct. The second part calculating `ans` and the subtraction is where the deviation occurs.

Let's assume the problem statement is as given and the code is meant to solve it.
The BFS correctly calculates `sum = sum(nums[i] * depth[i])` where root is depth 1.
The second loop calculates `ans = sum(nums[i] * (max_depth + 1))`.
The return `ans - sum` is `sum(nums[i] * (max_depth + 1 - depth[i]))`.

This is equivalent to `sum(nums[i] * weight_i)` where `weight_i = max_depth + 1 - depth[i]`.
This means the code calculates a weighted sum where the weights are inverted depths relative to the maximum depth.

Given the prompt is to analyze the *provided solution*, I will proceed with explaining *its* logic, even if it seems to deviate from a standard interpretation of "weighted sum of a tree".

The BFS part correctly builds the tree structure and calculates the sum of `value * depth` for each node, where the root is at depth 1.
The second loop calculates a sum where every node's value is multiplied by `max_depth + 1`.
The subtraction `ans - sum` results in a sum where each node `i` contributes `nums[i] * (max_depth + 1 - depth[i])`. This is a weighted sum where the weights are `max_depth + 1 - depth[i]`.

Let's assume the problem statement implies a specific definition of depth that the code is trying to match. If the problem meant "sum of values multiplied by their distance from the *leaves*", then this calculation might make sense.

For the purpose of this analysis, I will explain the code as it is written.

## Intuition
The problem asks for a weighted sum of nodes, where the weight is the node's depth. The root is defined as depth 1. A Breadth-First Search (BFS) is a natural fit for traversing a tree level by level, allowing us to easily determine the depth of each node. The provided solution uses a BFS to calculate a sum, and then performs a subtraction involving another sum. The BFS part calculates `sum(nums[i] * depth[i])` where `depth[i]` is the depth of node `i` (root at depth 1). The second part calculates `sum(nums[i] * (max_depth + 1))`. The final result `ans - sum` is equivalent to `sum(nums[i] * (max_depth + 1 - depth[i]))`. This means the code calculates a weighted sum where the weights are `max_depth + 1 - depth[i]`, effectively weighting nodes by their "distance from the deepest level".

## Algorithm
1.  **Build Adjacency List:** Create an adjacency list representation of the tree. Iterate through the `parent` array. For each node `i` where `parent[i]` is not -1, add `i` as a child to `parent[i]` in the adjacency list.
2.  **Initialize BFS:** Create a queue for BFS and add the root node (node 0) to it. Initialize `depth` to 0.
3.  **Perform BFS:**
    *   While the queue is not empty:
        *   Increment `depth`.
        *   Get the current size of the queue (`sz`). This represents all nodes at the current level.
        *   Iterate `sz` times:
            *   Dequeue a node `curr`.
            *   Add `nums[curr] * depth` to a running `sum`.
            *   For each neighbor `neigh` of `curr` in the adjacency list, enqueue `neigh`.
4.  **Calculate Second Sum:** After the BFS, `depth` will hold the maximum depth of the tree. Initialize `ans` to 0. Iterate through all nodes from 0 to `n-1`. For each node `i`, add `nums[i] * (depth + 1)` to `ans`.
5.  **Return Result:** Return `ans - sum`.

## Concept to Remember
*   **Tree Representation:** Understanding how to represent a tree using an adjacency list, especially when given parent pointers.
*   **Breadth-First Search (BFS):** BFS is ideal for level-order traversal and calculating depths of nodes in a tree.
*   **Weighted Sum:** The concept of summing node values multiplied by some associated weight (in this case, depth or a derived value).

## Common Mistakes
*   **Incorrect Depth Calculation:** Misinterpreting the root's depth (e.g., starting at 0 instead of 1) or incorrectly incrementing depth during BFS.
*   **Off-by-One Errors:** Errors in loop bounds or depth calculations, especially when dealing with the maximum depth.
*   **Integer Overflow:** Using `int` for sums when `long` is required, as the weighted sum can exceed the maximum value of an `int`.
*   **Misunderstanding the Problem's Specific Weighting:** The provided solution calculates a weighted sum based on `max_depth + 1 - depth`, which might differ from a direct interpretation of "weighted sum by depth".

## Complexity Analysis
*   **Time:** O(N) - We iterate through the `parent` array once to build the adjacency list (O(N)). The BFS visits each node and edge exactly once (O(N + E)). Since a tree has N-1 edges, this is O(N). The final loop to calculate `ans` is also O(N). Therefore, the total time complexity is O(N).
*   **Space:** O(N) - The adjacency list can store up to N-1 edges, requiring O(N) space. The BFS queue can store up to O(W) nodes, where W is the maximum width of the tree, which in the worst case (a complete binary tree) can be O(N). Thus, the space complexity is O(N).

## Commented Code
```java
class Solution {
    public long weightedSum(int[] parent, int[] nums) {
        long ans = 0; // Initialize the variable to store the final answer.
        long sum = 0; // Initialize a variable to store the sum calculated during BFS.
        int n = nums.length; // Get the total number of nodes in the tree.

        // Create an adjacency list to represent the tree.
        // Each index i in adj corresponds to node i, and the list at adj[i] stores its children.
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>()); // Initialize an empty list for each node.
        }

        // Build the adjacency list by iterating through the parent array.
        for (int i = 0; i < n; i++) {
            // If parent[i] is -1, it means node i is the root.
            // Otherwise, add node i as a child to its parent.
            if (parent[i] != -1) {
                adj.get(parent[i]).add(i); // Add child i to the parent's adjacency list.
            }
        }

        // Initialize a queue for Breadth-First Search (BFS).
        Queue<Integer> q = new LinkedList<>();
        // Add the root node (node 0) to the queue to start the BFS.
        q.add(0);

        int depth = 0; // Initialize depth counter. The problem defines root as depth 1, so we increment before processing a level.

        // Perform BFS to traverse the tree level by level.
        while (!q.isEmpty()) {
            int sz = q.size(); // Get the number of nodes at the current level.
            depth++; // Increment depth for the current level.

            // Process all nodes at the current level.
            for (int i = 0; i < sz; i++) {
                int curr = q.poll(); // Dequeue the current node.

                // Add the weighted value of the current node to the 'sum'.
                // Weight is the current 'depth'. Cast to long to prevent overflow.
                sum += (long) nums[curr] * depth;

                // Enqueue all children of the current node for the next level.
                for (int neigh : adj.get(curr)) {
                    q.add(neigh); // Add child node to the queue.
                }
            }
        }

        // After BFS, 'depth' holds the maximum depth of the tree.
        // Now, calculate the second sum 'ans'.
        // This sum weights each node by (max_depth + 1).
        for (int i = 0; i < n; i++) {
            // Add the weighted value of node i to 'ans'.
            // Weight is (max_depth + 1). Cast to long to prevent overflow.
            ans += (long) nums[i] * (depth + 1);
        }

        // The final result is 'ans - sum'.
        // This effectively calculates sum(nums[i] * (max_depth + 1 - depth[i])).
        return ans - sum;
    }
}
```

## Interview Tips
*   **Clarify Depth Definition:** Always confirm the starting depth of the root (0 or 1) and how it increments. In this case, the problem states root is depth 1.
*   **Explain BFS Logic:** Clearly articulate how BFS helps in level-order traversal and depth calculation.
*   **Handle Edge Cases:** Consider an empty tree (though constraints usually prevent this) or a tree with only one node.
*   **Data Type for Sum:** Be mindful of potential integer overflow and use `long` for sums.
*   **Discuss the Subtraction:** If asked about the `ans - sum` part, explain what it calculates (weighted sum by `max_depth + 1 - depth`) and how it relates to the problem's stated goal (if it indeed does). If it seems to deviate, point that out and ask for clarification.

## Revision Checklist
- [ ] Understand the problem statement and depth definition.
- [ ] Implement tree construction using an adjacency list.
- [ ] Implement BFS for level-order traversal and depth tracking.
- [ ] Correctly calculate the weighted sum during BFS.
- [ ] Handle potential integer overflow by using `long`.
- [ ] Understand the logic behind the `ans - sum` calculation.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)
*   [103. Binary Tree Zigzag Level Order Traversal](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/)
*   [116. Populating Next Right Pointers in Each Node](https://leetcode.com/problems/populating-next-right-pointers-in-each-node/)
*   [199. Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/)

## Tags
`Tree` `Breadth-First Search` `Graph` `Array`
