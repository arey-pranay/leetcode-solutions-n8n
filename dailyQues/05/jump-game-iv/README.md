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
We can solve it using Breadth-First Search (BFS) on a graph where nodes are array indices and edges represent possible jumps.

## Intuition
The problem can be modeled as finding the shortest path in a graph. The array indices are the nodes. From an index `i`, we can jump to `i+1`, `i-1`, or any other index `j` where `arr[i] == arr[j]`. Since we want the minimum number of steps, BFS is the natural choice. The key insight is to efficiently handle the jumps to indices with the same value. Instead of iterating through all possible `j`'s every time, we can pre-process and store all indices for each value in a hash map. This allows us to quickly access all potential jump targets for a given value.

## Algorithm
1. **Graph Construction:** Create a hash map where keys are the values in the array and values are lists of indices where that value appears. This allows for O(1) lookup of all indices with a specific value.
2. **BFS Initialization:**
   - Use a queue to store the indices to visit in the current BFS layer. Initialize it with the starting index `0`.
   - Use a set to keep track of visited indices to avoid cycles and redundant processing. Add `0` to the visited set.
   - Initialize a `step` counter to `0`.
3. **BFS Traversal:**
   - While the queue is not empty:
     - Get the size of the current queue (this represents the number of nodes in the current layer).
     - For each node in the current layer:
       - If the current node is the last index (`n-1`), return the current `step`.
       - **Explore same-value jumps:** For all indices `j` in the list associated with `arr[current_node]` in the hash map:
         - If `j` has not been visited:
           - Mark `j` as visited.
           - Add `j` to the queue for the next layer.
       - **Clear same-value list:** After processing all same-value jumps for `arr[current_node]`, clear the list in the hash map for `arr[current_node]`. This is a crucial optimization to prevent re-exploring these same-value jumps from other nodes with the same value in later layers, which can lead to Time Limit Exceeded.
       - **Explore adjacent jumps:**
         - If `current_node + 1` is within bounds and not visited, mark it as visited and add it to the queue.
         - If `current_node - 1` is within bounds and not visited, mark it as visited and add it to the queue.
     - Increment the `step` counter.
4. **No Path Found:** If the queue becomes empty and the end index is not reached, return -1 (though in this problem, a path is guaranteed if `n > 0`).

## Concept to Remember
*   **Breadth-First Search (BFS):** Ideal for finding the shortest path in an unweighted graph.
*   **Graph Representation:** Using a hash map to store adjacency lists for efficient neighbor lookup.
*   **Optimization Techniques:** Clearing processed adjacency lists to avoid redundant computations.
*   **State Management:** Using a `visited` set to prevent cycles and ensure each node is processed at most once per BFS level.

## Common Mistakes
*   **Inefficient Same-Value Jump Handling:** Iterating through the entire array to find indices with the same value for each jump, leading to O(N^2) or worse complexity.
*   **Not Clearing Processed Value Lists:** Failing to clear the list of indices for a value after processing them can lead to redundant work and TLE.
*   **Incorrect Visited Set Management:** Not adding nodes to the visited set before adding them to the queue, or removing them prematurely, can lead to infinite loops or incorrect shortest paths.
*   **Off-by-One Errors:** Incorrectly handling boundary conditions for `i+1` and `i-1` jumps.
*   **Forgetting the Base Case:** Not handling the `n <= 1` case correctly.

## Complexity Analysis
- **Time:** O(N) - Each index is visited and processed at most a constant number of times. The graph construction takes O(N). The BFS explores each node and edge at most once. Clearing the lists for a value ensures that the same-value jumps are processed only once across all nodes with that value.
- **Space:** O(N) - For the hash map to store indices for each value, the queue, and the visited set. In the worst case, all elements could be distinct, or all elements could be the same, leading to O(N) space.

## Commented Code
```java
class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length; // Get the length of the input array.
        if (n <= 1) { // Base case: If the array has 0 or 1 element, no jumps are needed.
            return 0;
        }
    
        // Create a hash map to store indices for each value in the array.
        // Key: the value from the array.
        // Value: a list of indices where this value appears.
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) { // Iterate through the array to populate the graph.
            // computeIfAbsent: if the key (arr[i]) is not present, create a new LinkedList for it.
            // Then, add the current index 'i' to the list associated with arr[i].
            graph.computeIfAbsent(arr[i], v -> new LinkedList<>()).add(i);
        }

        // 'curs' stores the nodes (indices) to visit in the current BFS layer.
        List<Integer> curs = new LinkedList<>(); 
        curs.add(0); // Start BFS from index 0.

        // 'visited' set keeps track of indices that have already been added to the queue or processed.
        Set<Integer> visited = new HashSet<>();
        visited.add(0); // Mark the starting index as visited.

        int step = 0; // Initialize the step counter. This represents the number of jumps.

        // Perform BFS as long as there are nodes in the current layer to process.
        while (!curs.isEmpty()) {
            // 'nex' will store the nodes for the next BFS layer.
            List<Integer> nex = new LinkedList<>();

            // Iterate through all nodes in the current layer ('curs').
            for (int node : curs) {
                // Check if we have reached the last index of the array.
                if (node == n - 1) {
                    return step; // If so, return the current number of steps.
                }

                // Explore jumps to indices with the same value as the current node.
                // Get the list of all indices that have the same value as arr[node].
                for (int child : graph.get(arr[node])) {
                    // If this 'child' index has not been visited yet:
                    if (!visited.contains(child)) {
                        visited.add(child); // Mark it as visited.
                        nex.add(child);     // Add it to the list for the next layer.
                    }
                }

                // IMPORTANT OPTIMIZATION: Clear the list of indices for arr[node] in the graph.
                // This prevents redundant processing of same-value jumps from other nodes
                // that might have the same value later in the BFS. Once we've explored
                // all jumps from 'node' to indices with value arr[node], we don't need
                // to consider these specific indices again for this value.
                graph.get(arr[node]).clear();

                // Explore jump to the next index (node + 1).
                // Check if 'node + 1' is within the array bounds and has not been visited.
                if (node + 1 < n && !visited.contains(node + 1)) {
                    visited.add(node + 1); // Mark as visited.
                    nex.add(node + 1);     // Add to the next layer.
                }
                // Explore jump to the previous index (node - 1).
                // Check if 'node - 1' is within the array bounds and has not been visited.
                if (node - 1 >= 0 && !visited.contains(node - 1)) {
                    visited.add(node - 1); // Mark as visited.
                    nex.add(node - 1);     // Add to the next layer.
                }
            }

            // After processing all nodes in the current layer, update 'curs' to 'nex' for the next iteration.
            curs = nex;
            // Increment the step counter as we move to the next level of BFS.
            step++;
        }

        // If the loop finishes without reaching the end, it means the end is unreachable.
        // (This problem guarantees reachability for n > 0, so this return is technically not needed for valid inputs).
        return -1; 
    }
}
```

## Interview Tips
1.  **Explain BFS Clearly:** Start by explaining why BFS is suitable for finding the shortest path. Walk through the graph model and the different types of edges.
2.  **Highlight the Optimization:** Emphasize the importance of the hash map for efficient same-value jumps and, crucially, the `graph.get(arr[node]).clear()` step. Explain how this prevents TLE.
3.  **Discuss Edge Cases:** Mention the base case `n <= 1` and how boundary checks for `i+1` and `i-1` are handled.
4.  **Trace an Example:** Be prepared to trace a small example array (e.g., `[100, -23, -23, 404, 100, 23, 23, 23, 3, 404]`) to demonstrate how the BFS progresses and how the `visited` set and `clear()` operation work.

## Revision Checklist
- [ ] Understand the problem: minimum steps to reach the end.
- [ ] Recognize BFS as the shortest path algorithm.
- [ ] Implement graph construction using a hash map for value-to-indices mapping.
- [ ] Correctly initialize BFS queue and visited set.
- [ ] Handle three types of jumps: `i+1`, `i-1`, and same-value indices.
- [ ] Implement the crucial optimization of clearing processed value lists.
- [ ] Manage visited nodes correctly to avoid cycles.
- [ ] Handle base cases and boundary conditions.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Jump Game
*   Jump Game II
*   Shortest Path in Binary Matrix
*   Word Ladder

## Tags
`Array` `Hash Map` `Breadth-First Search` `Graph`
