# Path Existence Queries In A Graph I

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Array` `Graph` `Greedy`  
**Time:** O(N + Q)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int j=0;
        int[] comp = new int[n];
        comp[0] = 0;
        boolean ans[] = new boolean[queries.length];
        for(int i=1;i<n;i++){
            if(Math.abs(nums[i]-nums[i-1]) > maxDiff) j++;
            comp[i] = j;
        }
        for(int i=0;i<queries.length;i++){
            int u = queries[i][0];
            int v = queries[i][1];
            ans[i] = comp[u]==comp[v];
        }
        return ans;
    }
}
```

---

---
## Quick Revision
This problem asks if a path exists between two nodes in a graph where edges are implicitly defined by a difference constraint on adjacent array elements. We solve it by partitioning the array into connected components based on the `maxDiff` constraint.

## Intuition
The core idea is that if two elements `nums[i]` and `nums[i-1]` have an absolute difference greater than `maxDiff`, they cannot be directly connected, and thus they belong to different "components" or disconnected subgraphs. If we can assign a unique component ID to each such disconnected segment, then two nodes `u` and `v` are connected if and only if they belong to the same component.

## Algorithm
1. Initialize an array `comp` of size `n` to store the component ID for each index.
2. Initialize `comp[0]` to `0`, representing the first component.
3. Initialize a variable `j` (component counter) to `0`.
4. Iterate through the `nums` array from index `1` to `n-1`.
5. For each index `i`, check if the absolute difference between `nums[i]` and `nums[i-1]` is greater than `maxDiff`.
6. If the difference is greater than `maxDiff`, increment `j` to start a new component.
7. Assign the current value of `j` to `comp[i]`.
8. Initialize a boolean array `ans` of the same length as `queries` to store the results.
9. Iterate through each query in `queries`.
10. For each query `[u, v]`, retrieve the component IDs `comp[u]` and `comp[v]`.
11. If `comp[u]` is equal to `comp[v]`, set `ans[i]` to `true` (path exists).
12. Otherwise, set `ans[i]` to `false` (path does not exist).
13. Return the `ans` array.

## Concept to Remember
*   **Graph Connectivity:** Understanding how to determine if two nodes are in the same connected component.
*   **Disjoint Set Union (DSU) / Union-Find (Implicit):** Although not explicitly using a DSU data structure, the partitioning of elements into components based on a condition is conceptually similar to how DSU merges sets.
*   **Array Partitioning:** The problem leverages the linear structure of the array to define connectivity.

## Common Mistakes
*   **Incorrectly defining connectivity:** Assuming connectivity based on value alone, rather than the difference constraint.
*   **Off-by-one errors:** Mishandling array indices when comparing adjacent elements or accessing component IDs.
*   **Not handling edge cases:** Forgetting to initialize the first component or process queries correctly.
*   **Inefficient component assignment:** Using a more complex approach than necessary when a simple linear scan suffices.

## Complexity Analysis
- Time: O(N + Q) - reason: We iterate through the `nums` array once to build the component array (O(N)), and then we iterate through all queries once (O(Q)).
- Space: O(N) - reason: We use an auxiliary array `comp` of size N to store component IDs.

## Commented Code
```java
class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Initialize an array to store the component ID for each index.
        int[] comp = new int[n];
        // Initialize the component counter.
        int j = 0;
        // The first element always belongs to the first component.
        comp[0] = 0;
        // Initialize the result array for queries.
        boolean ans[] = new boolean[queries.length];

        // Iterate through the nums array starting from the second element.
        for (int i = 1; i < n; i++) {
            // If the absolute difference between the current and previous element exceeds maxDiff,
            // it means a new disconnected component starts.
            if (Math.abs(nums[i] - nums[i - 1]) > maxDiff) {
                // Increment the component counter to assign a new ID.
                j++;
            }
            // Assign the current component ID to the current index.
            comp[i] = j;
        }

        // Iterate through each query to determine path existence.
        for (int i = 0; i < queries.length; i++) {
            // Get the start and end nodes of the current query.
            int u = queries[i][0];
            int v = queries[i][1];
            // If both nodes belong to the same component, a path exists.
            // Otherwise, no path exists.
            ans[i] = comp[u] == comp[v];
        }
        // Return the array of boolean results for each query.
        return ans;
    }
}
```

## Interview Tips
*   **Clarify the graph structure:** Explicitly state that the graph is implicitly defined by adjacent elements in the array and the `maxDiff` constraint.
*   **Explain the component idea:** Clearly articulate why partitioning into components based on the `maxDiff` is the key to solving the problem.
*   **Walk through an example:** Use a small `nums` array and `maxDiff` to demonstrate how the `comp` array is built and how queries are answered.
*   **Discuss time/space complexity:** Be prepared to justify the O(N+Q) time and O(N) space complexity.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the implicit graph structure.
- [ ] Develop the component-based partitioning strategy.
- [ ] Implement the component assignment logic correctly.
- [ ] Implement the query processing logic.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (e.g., n=1, empty queries).

## Similar Problems
*   Path with Maximum Minimum Value
*   Number of Provinces
*   Friend Circles
*   Redundant Connection

## Tags
`Array` `Graph` `Greedy`
