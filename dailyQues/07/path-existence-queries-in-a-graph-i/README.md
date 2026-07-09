# Path Existence Queries In A Graph I

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Array` `Graph` `Greedy` `Union-Find`  
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
This problem asks if a path exists between two nodes in a graph where edges are implicitly defined by adjacent elements in an array `nums` and a `maxDiff` constraint. The solution groups connected components based on this constraint.

## Intuition
The core idea is that if two adjacent elements in the `nums` array have a difference greater than `maxDiff`, they cannot be part of the same connected component. We can iterate through the array and assign a component ID to each element. If the difference between `nums[i]` and `nums[i-1]` is within `maxDiff`, they belong to the same component. Otherwise, `nums[i]` starts a new component. For any query `(u, v)`, a path exists if and only if `u` and `v` belong to the same component.

## Algorithm
1. Initialize an array `comp` of size `n` to store the component ID for each element.
2. Initialize a variable `j` to `0`, which will represent the current component ID.
3. Set `comp[0]` to `0`, as the first element always starts the first component.
4. Iterate from `i = 1` to `n-1`:
    a. Calculate the absolute difference between `nums[i]` and `nums[i-1]`.
    b. If the absolute difference is greater than `maxDiff`, increment `j` to start a new component.
    c. Assign the current component ID `j` to `comp[i]`.
5. Initialize a boolean array `ans` of the same length as `queries` to store the results.
6. Iterate through each query `i` from `0` to `queries.length - 1`:
    a. Get the two nodes `u` and `v` from `queries[i]`.
    b. Check if `comp[u]` is equal to `comp[v]`.
    c. If they are equal, set `ans[i]` to `true` (a path exists).
    d. Otherwise, set `ans[i]` to `false`.
7. Return the `ans` array.

## Concept to Remember
*   **Connected Components:** Understanding how to partition a graph or a sequence into disjoint sets where elements within a set are reachable from each other.
*   **Implicit Graph Representation:** Recognizing that a graph can be defined by relationships between elements in an array, not just explicit edge lists.
*   **Disjoint Set Union (DSU) / Union-Find (Conceptual):** While not explicitly implemented with DSU data structures here, the concept of grouping elements into sets based on connectivity is fundamental. This solution uses a simpler, linear-time approach for this specific problem's constraints.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly handling array indices when comparing adjacent elements or accessing `comp` array.
*   **Misinterpreting `maxDiff`:** Applying the difference check incorrectly (e.g., not using absolute difference).
*   **Not handling the first element:** Forgetting to initialize the component ID for the first element or starting the loop from `i=0` instead of `i=1` for comparisons.
*   **Inefficient query processing:** If the component assignment was not done upfront, processing each query by traversing the array would be too slow.

## Complexity Analysis
- Time: O(N + Q) - reason: We iterate through the `nums` array once to build the component array (O(N)), and then we iterate through all the queries once (O(Q)).
- Space: O(N) - reason: We use an auxiliary array `comp` of size N to store component IDs, and a boolean array `ans` of size Q for the results.

## Commented Code
```java
class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Initialize an array to store the component ID for each element in nums.
        int[] comp = new int[n];
        // Initialize the component ID counter.
        int j = 0;
        // The first element always belongs to the first component.
        comp[0] = 0;
        // Initialize the result array for all queries.
        boolean ans[] = new boolean[queries.length];

        // Iterate through the nums array starting from the second element to determine components.
        for (int i = 1; i < n; i++) {
            // Check if the absolute difference between the current and previous element exceeds maxDiff.
            if (Math.abs(nums[i] - nums[i - 1]) > maxDiff) {
                // If the difference is too large, start a new component.
                j++;
            }
            // Assign the current component ID to the current element.
            comp[i] = j;
        }

        // Process each query to determine path existence.
        for (int i = 0; i < queries.length; i++) {
            // Get the two nodes (indices) for the current query.
            int u = queries[i][0];
            int v = queries[i][1];
            // A path exists between u and v if and only if they belong to the same component.
            ans[i] = comp[u] == comp[v];
        }
        // Return the array of boolean results for each query.
        return ans;
    }
}
```

## Interview Tips
*   **Clarify Graph Definition:** Before coding, ensure you understand how the graph is implicitly defined by the array and `maxDiff`. Ask if the graph is directed or undirected (in this case, it's effectively undirected due to the symmetric nature of adjacency).
*   **Explain Component Logic:** Clearly articulate why grouping elements into components based on `maxDiff` is the correct approach for determining path existence.
*   **Discuss Edge Cases:** Consider what happens if `n` is 0 or 1, or if `queries` is empty. The current code handles `n=1` gracefully.
*   **Time/Space Trade-off:** Mention that this approach pre-processes the graph in O(N) time and O(N) space to answer queries in O(1) time each.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the implicit graph structure.
- [ ] Develop the component-based grouping logic.
- [ ] Implement the component assignment loop correctly.
- [ ] Implement the query processing loop correctly.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases.

## Similar Problems
*   Path with Maximum Gold
*   Number of Provinces
*   Friend Circles
*   Graph Connectivity
*   Checking if a graph is connected

## Tags
`Array` `Graph` `Greedy` `Union-Find`
