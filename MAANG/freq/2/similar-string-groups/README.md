# Similar String Groups

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Depth-First Search` `Breadth-First Search` `Union-Find`  
**Time:** O(N^2 * L)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int numSimilarGroups(String[] strs) {
        int groups = 0;
        int n = strs.length;
        boolean[] vis= new boolean[n];
        for(int i=0;i<n;i++){
            if(vis[i]) continue;
            dfs(i,strs,vis);
            groups++;
        }
        return groups;
    }
    public void dfs(int i, String[] strs, boolean[] vis){
        vis[i] = true;
        for(int j=0;j<strs.length;j++){
            if(!vis[j] && isNeigh(strs[i],strs[j])){
                dfs(j,strs,vis);
            }
        }
    }
    public boolean isNeigh(String s1, String s2){
        int count = 0;
        for(int i=0; i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)) count++;
            if(count>2) return false;
        }
        return count==0 || count==2; // 0 or 1 pair of mismatch is found, so similar
    }
}

// tars
// rats
// a,b
// b,c

// a,b,c
```

---

---
## Quick Revision
This problem asks to find the number of distinct groups of strings where strings within a group are similar. Two strings are similar if they are anagrams and can be made identical by swapping at most two characters.

The solution uses Depth First Search (DFS) to traverse connected components in a graph where strings are nodes and an edge exists between similar strings.

## Intuition
The core idea is to model this problem as a graph. Each string in the input array `strs` can be considered a node. An edge exists between two nodes (strings) if they are "similar". Two strings are similar if they are anagrams and differ by at most two character positions (meaning they can be made identical by at most one swap). Once we have this graph representation, the problem reduces to finding the number of connected components in this graph. Each connected component represents a group of similar strings. We can use a standard graph traversal algorithm like DFS or BFS to count these components.

The provided solution uses DFS. It iterates through each string. If a string hasn't been visited yet, it means we've found a new group. We then start a DFS from this string to visit all other strings that are similar to it (directly or indirectly). All strings visited during this DFS belong to the same group. We increment the group count and continue the process until all strings are visited.

## Algorithm
1. Initialize `groups` to 0.
2. Create a boolean array `vis` of the same size as `strs` to keep track of visited strings, initialized to `false`.
3. Iterate through each string `strs[i]` from `i = 0` to `n-1` (where `n` is the length of `strs`).
4. If `vis[i]` is `true`, continue to the next string (it's already part of a group).
5. If `vis[i]` is `false`:
    a. Increment `groups` by 1 (we've found a new group).
    b. Call a DFS function `dfs(i, strs, vis)` to mark all strings in this new group as visited.
6. The `dfs(currentIndex, strs, vis)` function:
    a. Mark `vis[currentIndex]` as `true`.
    b. Iterate through all other strings `strs[j]` from `j = 0` to `n-1`.
    c. If `vis[j]` is `false` and `strs[currentIndex]` is similar to `strs[j]` (checked by `isNeigh` function):
        i. Recursively call `dfs(j, strs, vis)`.
7. The `isNeigh(s1, s2)` function:
    a. Initialize a `count` to 0.
    b. Iterate through the characters of `s1` and `s2` from `i = 0` to `s1.length()-1`.
    c. If `s1.charAt(i)` is not equal to `s2.charAt(i)`, increment `count`.
    d. If `count` becomes greater than 2 at any point, return `false` (they are not similar).
    e. After iterating through all characters, return `true` if `count` is 0 (strings are identical) or `count` is 2 (strings differ by exactly one swap). Otherwise, return `false`.
8. Return the final `groups` count.

## Concept to Remember
*   **Graph Traversal (DFS/BFS):** Understanding how to explore connected components in a graph is fundamental.
*   **Disjoint Set Union (DSU):** An alternative and often more efficient approach for finding connected components.
*   **String Manipulation and Comparison:** Efficiently checking for similarity between strings, specifically identifying anagrams and character differences.

## Common Mistakes
*   **Incorrect Similarity Check:** Misinterpreting the "similar" condition. It's not just about being anagrams, but also about the number of differing characters (at most 2).
*   **Not Handling Visited Nodes:** Failing to use a `visited` array or set, leading to infinite recursion or recounting groups.
*   **Inefficient Graph Construction:** Building an explicit adjacency list for all pairs can be too slow if `n` is large. The DFS approach implicitly builds the graph as it traverses.
*   **Off-by-one Errors in Character Comparison:** Incorrectly counting differences or returning the wrong boolean value in the `isNeigh` function.

## Complexity Analysis
*   **Time:** O(N^2 * L), where N is the number of strings and L is the length of each string.
    *   The outer loop iterates N times.
    *   The DFS function, in the worst case, might visit all N strings.
    *   Inside DFS, the inner loop iterates N times.
    *   The `isNeigh` function takes O(L) time to compare two strings.
    *   Therefore, the total time complexity is roughly N * (N * L) for the DFS traversal and comparisons.
*   **Space:** O(N) for the `vis` array and the recursion stack depth in DFS. In the worst case, the recursion depth can be N if all strings form a single connected component.

## Commented Code
```java
class Solution {
    // Main function to calculate the number of similar string groups.
    public int numSimilarGroups(String[] strs) {
        int groups = 0; // Initialize the count of groups to 0.
        int n = strs.length; // Get the total number of strings.
        boolean[] vis = new boolean[n]; // Create a boolean array to track visited strings.

        // Iterate through each string in the input array.
        for (int i = 0; i < n; i++) {
            // If the current string has already been visited, skip it as it belongs to an already counted group.
            if (vis[i]) continue;

            // If the string hasn't been visited, it signifies the start of a new group.
            // Perform DFS starting from this string to find all connected similar strings.
            dfs(i, strs, vis);
            // Increment the group count because we've identified a new distinct group.
            groups++;
        }
        // Return the total number of distinct similar string groups found.
        return groups;
    }

    // Depth First Search function to explore connected components (groups) of similar strings.
    public void dfs(int i, String[] strs, boolean[] vis) {
        // Mark the current string as visited.
        vis[i] = true;

        // Iterate through all other strings to find potential neighbors (similar strings).
        for (int j = 0; j < strs.length; j++) {
            // Check if the string at index 'j' has not been visited yet AND
            // if the string at index 'i' is similar to the string at index 'j'.
            if (!vis[j] && isNeigh(strs[i], strs[j])) {
                // If both conditions are true, recursively call DFS on the neighbor string 'j'.
                // This explores the connected component further.
                dfs(j, strs, vis);
            }
        }
    }

    // Helper function to check if two strings are "similar".
    // Two strings are similar if they are anagrams and differ by at most 2 characters.
    public boolean isNeigh(String s1, String s2) {
        int count = 0; // Initialize a counter for differing characters.

        // Iterate through the characters of both strings.
        for (int i = 0; i < s1.length(); i++) {
            // If characters at the current position are different, increment the count.
            if (s1.charAt(i) != s2.charAt(i)) count++;
            // If the count of differing characters exceeds 2, they cannot be made similar by one swap.
            // So, return false immediately.
            if (count > 2) return false;
        }
        // After checking all characters:
        // Return true if the count is 0 (strings are identical) OR
        // if the count is exactly 2 (meaning they differ by exactly one pair of characters, which can be swapped).
        // Otherwise, return false.
        return count == 0 || count == 2;
    }
}
```

## Interview Tips
1.  **Clarify "Similar":** Ensure you fully understand the definition of "similar strings" (anagrams, at most 2 character differences). Ask for examples if unsure.
2.  **Graph Analogy:** Frame the problem as finding connected components in a graph. This helps in choosing the right traversal algorithm (DFS/BFS) or data structure (DSU).
3.  **Optimization:** Discuss the time complexity. If N is very large, mention that an explicit graph construction might be too slow and that the implicit graph traversal (like DFS/BFS) or DSU is preferred.
4.  **Edge Cases:** Consider edge cases like an empty input array, an array with one string, or an array where all strings are identical.

## Revision Checklist
- [ ] Understand the definition of "similar strings" (anagrams, max 2 diffs).
- [ ] Recognize the problem as finding connected components in a graph.
- [ ] Implement DFS or BFS for graph traversal.
- [ ] Implement a correct `isSimilar` (or `isNeigh`) helper function.
- [ ] Use a `visited` array to avoid redundant computations and infinite loops.
- [ ] Analyze time and space complexity.
- [ ] Consider alternative approaches like Disjoint Set Union (DSU).

## Similar Problems
*   Number of Provinces
*   Friend Circles
*   Accounts Merge
*   Graph Valid Tree

## Tags
`Array` `Depth-First Search` `Breadth-First Search` `Union-Find` `String`
