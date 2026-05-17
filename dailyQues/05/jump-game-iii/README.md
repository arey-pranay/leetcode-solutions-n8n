# Jump Game Iii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Depth-First Search` `Breadth-First Search`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    Boolean[] memo;
    HashSet<Integer> checking;
    public boolean canReach(int[] arr, int start) {
        HashSet<Integer> zeroes = new HashSet<>();
        checking = new HashSet<>();
        int n = arr.length;
        memo = new Boolean[n];
        for(int i=0;i<n;i++)if(arr[i]==0)zeroes.add(i);
        return func(arr, start, zeroes);
    }
    public boolean func(int[] arr, int start, HashSet<Integer> zeroes){
        if(zeroes.contains(start)) return true;
        int n = arr.length;
        if(checking.contains(start) || start < 0 || start >= n) return false;
        if(memo[start] != null) return memo[start];
        checking.add(start);
        memo[start] = func(arr,start-arr[start],zeroes) || func(arr,start+arr[start],zeroes);
        checking.remove(start);
        return memo[start];
    }
}
```

---

---
## Quick Revision
Given an array of non-negative integers and a starting index, determine if you can reach an index with value 0 by jumping forward or backward by the value at the current index.
This problem can be solved using graph traversal algorithms like Breadth-First Search (BFS) or Depth-First Search (DFS) with memoization or a visited set to avoid cycles.

## Intuition
The problem can be modeled as a graph where each index in the array is a node. From a node `i`, we can transition to `i - arr[i]` or `i + arr[i]`. The goal is to find if there's a path from the `start` node to any node containing the value 0. This is a classic reachability problem in a graph. We need to explore possible paths and keep track of visited nodes to prevent infinite loops.

## Algorithm
1. **Initialization**:
    * Create a `HashSet` called `zeroes` to store all indices where the array value is 0.
    * Create a `HashSet` called `checking` (or `visited`) to keep track of indices currently being explored in the recursion stack to detect cycles.
    * Create a `Boolean` array `memo` of the same size as `arr` to store the results of subproblems (memoization). Initialize all entries to `null`.
    * Get the length of the array `n`.
2. **Populate `zeroes`**: Iterate through the input array `arr` and add the index `i` to `zeroes` if `arr[i]` is 0.
3. **Start DFS/Recursion**: Call a helper function (e.g., `func` or `dfs`) with the array `arr`, the `start` index, and the `zeroes` set.
4. **Helper Function (`func`)**:
    * **Base Case 1 (Target Reached)**: If the current `start` index is present in the `zeroes` set, return `true`.
    * **Base Case 2 (Invalid/Visited)**: If the current `start` index is out of bounds (`< 0` or `>= n`) or if it's already in the `checking` set (meaning we're in a cycle), return `false`.
    * **Memoization Check**: If `memo[start]` is not `null`, return the stored result `memo[start]`.
    * **Mark as Visiting**: Add the current `start` index to the `checking` set.
    * **Recursive Calls**:
        * Calculate the next possible indices: `next_forward = start + arr[start]` and `next_backward = start - arr[start]`.
        * Recursively call `func` for both `next_backward` and `next_forward`.
        * The result for the current `start` is `true` if *either* of the recursive calls returns `true`.
    * **Backtrack**: Remove the current `start` index from the `checking` set (important for DFS to explore other paths correctly).
    * **Store and Return**: Store the computed result in `memo[start]` and return it.

## Concept to Remember
*   **Graph Traversal**: Understanding how to model problems as graphs and apply BFS/DFS.
*   **Recursion with Memoization**: Using dynamic programming to store results of overlapping subproblems to avoid redundant computations.
*   **Cycle Detection**: Identifying and handling cycles in graph traversal to prevent infinite loops.
*   **State Management**: Keeping track of visited nodes or current recursion path.

## Common Mistakes
*   **Not handling cycles**: Failing to use a `visited` set or `checking` set can lead to infinite recursion if the jumps form a cycle.
*   **Incorrect memoization key/logic**: Storing results incorrectly or not checking the memoization table before computation.
*   **Off-by-one errors in bounds checking**: Mishandling array index boundaries (`< 0` or `>= n`).
*   **Forgetting to backtrack**: In DFS, not removing the current node from the `checking` set after exploring its children can incorrectly mark paths as invalid.
*   **Inefficiently finding zeroes**: Iterating through the array multiple times to find zeroes instead of doing it once at the start.

## Complexity Analysis
*   **Time**: O(N) - Each index (node) is visited at most once due to memoization and the `checking` set. For each visited index, we perform constant time operations (array access, set operations, arithmetic).
*   **Space**: O(N) - For the recursion stack (in the worst case, a path can be as long as N), the `memo` array, and the `checking` set.

## Commented Code
```java
class Solution {
    // Boolean array for memoization: stores whether an index can reach a zero.
    // null: not computed, true: can reach, false: cannot reach.
    Boolean[] memo;
    // HashSet to keep track of indices currently in the recursion stack to detect cycles.
    HashSet<Integer> checking;
    
    public boolean canReach(int[] arr, int start) {
        // HashSet to store all indices where the array value is 0.
        HashSet<Integer> zeroes = new HashSet<>();
        // Initialize the set for tracking current recursion path.
        checking = new HashSet<>();
        // Get the length of the array.
        int n = arr.length;
        // Initialize the memoization array with nulls.
        memo = new Boolean[n];
        
        // Iterate through the array to find all indices with value 0.
        for(int i=0; i<n; i++) {
            // If the value at index i is 0, add the index to the zeroes set.
            if(arr[i]==0) {
                zeroes.add(i);
            }
        }
        
        // Start the recursive (DFS) function from the given start index.
        return func(arr, start, zeroes);
    }
    
    // Recursive helper function to perform DFS and check reachability.
    public boolean func(int[] arr, int start, HashSet<Integer> zeroes){
        // Base Case 1: If the current index is one of the target zeroes, we've reached it.
        if(zeroes.contains(start)) {
            return true;
        }
        
        // Get the length of the array.
        int n = arr.length;
        // Base Case 2: If the current index is out of bounds OR already being checked in the current path (cycle detected).
        if(checking.contains(start) || start < 0 || start >= n) {
            return false;
        }
        
        // Memoization Check: If the result for this index has already been computed, return it.
        if(memo[start] != null) {
            return memo[start];
        }
        
        // Mark the current index as being checked in the current recursion path.
        checking.add(start);
        
        // Recursive Step: Explore both possible jumps (backward and forward).
        // The result for the current index is true if EITHER the backward jump OR the forward jump can reach a zero.
        memo[start] = func(arr,start-arr[start],zeroes) || func(arr,start+arr[start],zeroes);
        
        // Backtrack: Remove the current index from the checking set as we are done exploring its subtree.
        checking.remove(start);
        
        // Return the computed result for the current index.
        return memo[start];
    }
}
```

## Interview Tips
*   **Explain the Graph Analogy**: Clearly articulate how the array indices and jump values form a graph and that the problem is about finding a path.
*   **Discuss Trade-offs**: Mention BFS vs. DFS. BFS would use a queue and a visited set, while DFS uses recursion (or an explicit stack) and a visited set/memoization. The provided solution uses DFS with memoization.
*   **Highlight Cycle Detection**: Emphasize the importance of the `checking` set (or a similar mechanism) to prevent infinite loops, which is a common pitfall.
*   **Clarify Memoization**: Explain how `memo` stores results to avoid recomputing for the same index, leading to O(N) time complexity.

## Revision Checklist
- [ ] Understand the problem: reach a 0 from `start` by jumping `arr[i]` steps.
- [ ] Model as a graph: indices are nodes, jumps are edges.
- [ ] Choose a traversal: DFS or BFS.
- [ ] Implement cycle detection: use a `visited` set or `checking` set.
- [ ] Implement memoization: store results of subproblems.
- [ ] Handle boundary conditions: `start < 0` or `start >= n`.
- [ ] Correctly identify target nodes: indices where `arr[i] == 0`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Jump Game (LeetCode 55)
*   Jump Game II (LeetCode 45)
*   Word Search (LeetCode 79) - Similar DFS with visited set.
*   Number of Islands (LeetCode 200) - Graph traversal on a grid.

## Tags
`Array` `Depth-First Search` `Breadth-First Search` `Recursion` `Dynamic Programming` `Graph`
