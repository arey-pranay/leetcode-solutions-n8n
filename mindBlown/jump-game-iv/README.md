# Jump Game Iv

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Breadth-First Search`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return 0;
        }
    
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        }

        List<Integer> curs = new LinkedList<>(); // store current layer
        curs.add(0);
        Set<Integer> visited = new HashSet<>();
        int step = 0;

        // when current layer exists
        while (!curs.isEmpty()) {
            List<Integer> nex = new LinkedList<>();

            // iterate the layer
            for (int node : curs) {
                // check if reached end
                if (node == n - 1) {
                    return step;
                }

                // check same value
                for (int child : graph.get(arr[node])) {
                    if (!visited.contains(child)) {
                        visited.add(child);
                        nex.add(child);
                    }
                }

                // clear the list to prevent redundant search
                graph.get(arr[node]).clear();

                // check neighbors
                if (node + 1 < n && !visited.contains(node + 1)) {
                    visited.add(node + 1);
                    nex.add(node + 1);
                }
                if (node - 1 >= 0 && !visited.contains(node - 1)) {
                    visited.add(node - 1);
                    nex.add(node - 1);
                }
            }

            curs = nex;
            step++;
        }

        return -1;
    }
}
// BFS Memory Limit exceeded
// class Solution {
//     HashSet<Integer> checking;
//     HashMap<Integer,HashSet<Integer>> hm;
//     Integer[][] memo;
    
//     public int minJumps(int[] arr) {
//         checking = new HashSet<>();
//         hm = new HashMap<>();
//         memo = new Integer[arr.length][50001];
//         for(int i=0;i<arr.length;i++){
//             HashSet<Integer> temp =  hm.getOrDefault(arr[i], new HashSet<>());
//             temp.add(i);
//             hm.put(arr[i], temp);
//         }
//         checking.add(0);
//         int ans = func(0,arr, 0);
//         return ans;
//     }
    
//     public int func(int i, int[] arr, int tillNow){
//         int n = arr.length;
//         if(i<0 || i>=n) return Integer.MAX_VALUE;
//         if(i == n-1) return 0;
//         if(memo[i][tillNow] != null) return memo[i][tillNow];
        
//         int a = Integer.MAX_VALUE;
//         int b = Integer.MAX_VALUE;
//         int c = Integer.MAX_VALUE;
        
//         for(int j : hm.get(arr[i])){
//             if(i != j){ 
//                 if(checking.contains(j)) continue; 
//                 checking.add(j);
//                 System.out.println(i + " -> "+j);
//                 a = Math.min(a,func(j,arr,tillNow+1));
//                 checking.remove(j);
//             }
//         }
        
//         if(!checking.contains(i+1)){
//             checking.add(i+1);
//             System.out.println(i + " -> "+ (i+1));
//             b =  func(i+1,arr,tillNow+1);
//             checking.remove(i+1);
//         }
//          if(!checking.contains(i-1)){
//             checking.add(i-1);
//             System.out.println(i + " -> "+ (i-1));
//             c =  func(i-1,arr,tillNow+1);
//             checking.remove(i-1);
//         }

//         int min = Math.min(a,Math.min(b,c));
//         return memo[i][tillNow] = min == Integer.MAX_VALUE ? Integer.MAX_VALUE: 1 + min;
//     }
// }
```

---

---
## Quick Revision
This problem asks for the minimum number of steps to reach the end of an array.
We can solve this by treating it as a graph problem and using Breadth-First Search (BFS).

## Intuition
The problem can be modeled as finding the shortest path in a graph. The array indices are nodes. An edge exists between index `i` and `i+1`, `i-1`, and any index `j` where `arr[i] == arr[j]`. Since we want the minimum number of steps, BFS is the natural choice. The key insight is to efficiently explore all possible jumps, especially those involving same-valued elements.

## Algorithm
1.  **Graph Representation**: Create an adjacency list (HashMap) where keys are the values in the array, and values are lists of indices where that value appears. This allows quick access to all indices with the same number.
2.  **Initialization**:
    *   Use a queue for BFS, initially containing the starting index `0`.
    *   Use a `visited` set to keep track of visited indices to avoid cycles and redundant processing. Add `0` to `visited`.
    *   Initialize `steps` to `0`.
3.  **BFS Traversal**:
    *   While the queue is not empty:
        *   Process all nodes at the current level. Get the size of the queue for the current level.
        *   For each `currentNode` dequeued:
            *   If `currentNode` is the last index (`n-1`), return `steps`.
            *   **Explore same-value jumps**: For each `neighborIndex` in `graph.get(arr[currentNode])`:
                *   If `neighborIndex` has not been visited:
                    *   Mark `neighborIndex` as visited.
                    *   Enqueue `neighborIndex`.
            *   **Clear same-value list**: After processing all same-value jumps for `arr[currentNode]`, clear `graph.get(arr[currentNode])`. This is a crucial optimization to prevent re-exploring the same set of same-value neighbors multiple times, which can lead to Time Limit Exceeded.
            *   **Explore adjacent jumps**:
                *   Check `currentNode + 1`: If it's within bounds and not visited, mark it visited and enqueue it.
                *   Check `currentNode - 1`: If it's within bounds and not visited, mark it visited and enqueue it.
        *   Increment `steps` after processing all nodes at the current level.
4.  **Return**: If the loop finishes without reaching the end, it means the end is unreachable (though this problem guarantees reachability). Return `-1` or handle as per problem constraints.

## Concept to Remember
*   **Breadth-First Search (BFS)**: Ideal for finding the shortest path in an unweighted graph.
*   **Graph Representation**: Using a HashMap to store adjacency lists for efficient lookups.
*   **Optimization Techniques**: Clearing processed adjacency lists to avoid redundant computations.
*   **State Management**: Using a `visited` set to prevent cycles and repeated work.

## Common Mistakes
*   **Not handling same-value jumps efficiently**: Iterating through all indices with the same value repeatedly without optimization can lead to TLE.
*   **Incorrectly managing visited states**: Forgetting to add nodes to `visited` or removing them prematurely.
*   **Off-by-one errors**: In boundary checks for `i+1` and `i-1`.
*   **Not clearing the adjacency list for same values**: This is the most common reason for TLE in this problem.
*   **Using recursion without memoization or proper pruning**: The provided recursive solution without effective memoization or pruning will likely time out.

## Complexity Analysis
*   **Time**: O(N) - Each index is visited and processed at most a constant number of times. The graph construction takes O(N). The BFS explores each node and edge once. Clearing the adjacency lists for same values ensures that each value's list is processed only once across all its occurrences.
*   **Space**: O(N) - For the HashMap to store the graph (at most N entries, each list can contain up to N indices in total), the queue (at most N elements), and the visited set (at most N elements).

## Commented Code
```java
import java.util.*;

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length; // Get the length of the input array.
        if (n <= 1) { // If the array has 0 or 1 element, we are already at the end.
            return 0; // So, 0 jumps are needed.
        }
    
        // Create a HashMap to store the graph.
        // Key: the value of an element in arr.
        // Value: a list of indices where this value appears in arr.
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) { // Iterate through the array to build the graph.
            // For each element arr[i], add its index 'i' to the list associated with its value.
            // computeIfAbsent ensures that if the key (arr[i]) doesn't exist, a new LinkedList is created for it.
            graph.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        }

        // Queue for BFS. 'curs' stores the nodes (indices) at the current level.
        List<Integer> curs = new LinkedList<>(); 
        curs.add(0); // Start BFS from index 0.
        
        // Set to keep track of visited indices to avoid cycles and redundant processing.
        Set<Integer> visited = new HashSet<>();
        visited.add(0); // Mark the starting index as visited.
        
        int step = 0; // Initialize the number of steps taken.

        // BFS loop: continues as long as there are nodes to process in the current level.
        while (!curs.isEmpty()) {
            // 'nex' will store the nodes (indices) for the next level of BFS.
            List<Integer> nex = new LinkedList<>();

            // Iterate through all nodes (indices) in the current level.
            for (int node : curs) {
                // Check if we have reached the last index of the array.
                if (node == n - 1) {
                    return step; // If yes, return the current number of steps.
                }

                // Explore jumps to indices with the same value as arr[node].
                // graph.get(arr[node]) gives us all indices 'child' where arr[child] == arr[node].
                for (int child : graph.get(arr[node])) {
                    // If this 'child' index has not been visited yet.
                    if (!visited.contains(child)) {
                        visited.add(child); // Mark it as visited.
                        nex.add(child);     // Add it to the list for the next level.
                    }
                }

                // CRUCIAL OPTIMIZATION: Clear the list of indices for arr[node] in the graph.
                // This prevents re-exploring the same set of same-value neighbors multiple times
                // from different nodes that have the same value. This is key to achieving O(N) time.
                graph.get(arr[node]).clear();

                // Explore jump to the next index (node + 1).
                // Check if it's within array bounds and hasn't been visited.
                if (node + 1 < n && !visited.contains(node + 1)) {
                    visited.add(node + 1); // Mark as visited.
                    nex.add(node + 1);     // Add to the next level.
                }
                
                // Explore jump to the previous index (node - 1).
                // Check if it's within array bounds and hasn't been visited.
                if (node - 1 >= 0 && !visited.contains(node - 1)) {
                    visited.add(node - 1); // Mark as visited.
                    nex.add(node - 1);     // Add to the next level.
                }
            }

            // After processing all nodes at the current level, update 'curs' to 'nex' for the next iteration.
            curs = nex;
            // Increment the step count as we move to the next level.
            step++;
        }

        // If the loop finishes and we haven't reached the end, it means the end is unreachable.
        // (Problem constraints usually guarantee reachability, but this is a safe fallback).
        return -1; 
    }
}
```

## Interview Tips
*   **Explain the Graph Analogy**: Clearly articulate how the array can be viewed as a graph and why BFS is suitable for finding the shortest path.
*   **Highlight the Optimization**: Emphasize the importance of clearing the adjacency list for same-valued elements (`graph.get(arr[node]).clear()`) and explain *why* it's necessary for performance. This is the most critical part of the solution.
*   **Discuss Edge Cases**: Mention handling `n <= 1` and the possibility of unreachable end states (though not applicable here).
*   **Walk Through an Example**: Use a small array example to trace the BFS process, showing how `curs`, `nex`, `visited`, and `step` evolve.

## Revision Checklist
- [ ] Understand the problem: minimum jumps to reach the end.
- [ ] Recognize BFS as the optimal approach for shortest path.
- [ ] Implement graph construction using a HashMap.
- [ ] Correctly manage visited nodes using a HashSet.
- [ ] Implement the BFS level-by-level traversal.
- [ ] Crucially, implement the optimization of clearing same-value adjacency lists.
- [ ] Handle boundary conditions for adjacent jumps.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Jump Game (LeetCode 55)
*   Jump Game II (LeetCode 45)
*   Shortest Path in Binary Matrix (LeetCode 1091)
*   Word Ladder (LeetCode 127)

## Tags
`Array` `Hash Map` `Breadth-First Search` `Graph`
