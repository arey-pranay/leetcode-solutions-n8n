# Shortest Path With At Most K Consecutive Identical Characters

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Graph Theory` `Heap (Priority Queue)` `Shortest Path`  
**Time:** O(E \cdot K \log(V \cdot K)  
**Space:** O(V \cdot K + E)

---

## Solution (java)

```java
class Solution {
    
    public int shortestPath(int n, int[][] edges, String labels, int K) {
        List<List<int[]>> adj  = new ArrayList<>(); 
        for(int i =0;i<n;i++) adj.add(new ArrayList<>());
        
        for(int i=0; i<edges.length;i++){
            int[] arr = edges[i];
            int u = arr[0], v = arr[1], w=arr[2];
            adj.get(u).add(new int[] {v,w});
        }
        
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->a[1]-b[1]);
        q.add(new int[]{0,0,1});
        int[][] dists = new int[n][K+1]; // given node pe, given consecutive occurences leke, humara best dist kitna hai
        for(int[] arr : dists)Arrays.fill(arr,Integer.MAX_VALUE);
        dists[0][1] = 0; // 0th node ke 1 occurence se start kara, uska dist covered is 0
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int u = curr[0], dist = curr[1], cons = curr[2];
            if(dist > dists[u][cons]) continue;
            if(u==n-1) return dist;
            for(int[] neigh : adj.get(u)){
                int cons2 = cons;
                int v = neigh[0], w=neigh[1];
                if(labels.charAt(u)==labels.charAt(v)) cons2++; else cons2 = 1;
                if(cons2>K || dists[u][cons]+w >= dists[v][cons2]) continue;
                dists[v][cons2] = dists[u][cons]+w;
                q.add(new int[]{v,dists[v][cons2],cons2});
            }
        }
        
        return -1;
    }
}
```

---

---
## Quick Revision
Find the shortest path from start to end node in a weighted graph under the constraint that we cannot traverse more than $K$ identical consecutive labels.
Solve it using Dijkstra's algorithm modified to track both the current node and the consecutive identical character count in the state.

## Intuition
Standard shortest path algorithms like Dijkstra only track the current node to find the optimal path. However, when path validity depends on history (in this case, consecutive identical labels along the path), the state space must be expanded. The "aha moment" is realizing that the state of our graph traversal is not just the node $u$, but the tuple `(node, consecutive_count)`. By treating each unique node-count pair as a distinct state in Dijkstra's algorithm, we can safely explore paths while respecting the $K$ consecutive identical character limit.

## Algorithm
1. Build an adjacency list representation of the graph using the provided edges and weights.
2. Initialize a Min-Priority Queue for Dijkstra's algorithm that stores states in the format `[node, current_distance, consecutive_count]`.
3. Create a 2D distance table `dists[n][K+1]` initialized to infinity to track the minimum distance to reach each node with a specific consecutive identical character count.
4. Set the starting state `dists[0][1] = 0` (assuming node 0 starts with a count of 1 for its initial label) and push `[0, 0, 1]` to the priority queue.
5. While the priority queue is not empty, extract the state with the minimum distance `(u, dist, cons)`.
6. If the extracted distance is greater than the recorded best distance for this state, or if we have reached the target node $n-1$, handle appropriately (return if target reached).
7. Iterate through all neighbors of the current node $u$. Calculate the new consecutive count based on whether the label of $u$ matches the label of neighbor $v$.
8. If the new consecutive count exceeds $K$, skip the neighbor. Otherwise, if the new path offers a strictly better distance, update the distance table and push the new state to the priority queue.
9. If the priority queue empties without reaching the destination, return -1.

## Concept to Remember
- **State Space Expansion:** Modifying standard graph search algorithms by augmenting the state with additional constraints (like remaining budget or history).
- **Modified Dijkstra's Algorithm:** Utilizing a priority queue combined with a multi-dimensional distance lookup array to guarantee shortest paths with state-dependent weights and rules.
- **Path Constraints:** Handling transition rules where edge validity depends on node attributes or traversal history.

## Common Mistakes
- Failing to expand the distance tracking array to include the constraint dimension, leading to incorrect overwrites of optimal paths.
- Forgetting to reset the consecutive count to 1 when transitioning to a neighbor with a different label.
- Incorrectly checking the $K$ boundary condition (e.g., allowing `cons2 > K` instead of pruning).
- Omitting the state pruning check (`dist > dists[u][cons]`), which can result in TLE due to redundant processing of suboptimal states.

## Complexity Analysis
- Time: $O(E \cdot K \log(V \cdot K))$ - In the worst case, each node has $K$ possible states, making the total number of states $V \cdot K$. Dijkstra processes each state and its edges $E \cdot K$ times with priority queue operations taking logarithmic time relative to the number of states.
- Space: $O(V \cdot K + E)$ - The adjacency list takes $O(V + E)$ space, and the 2D distance tracking table alongside the priority queue requires $O(V \cdot K)$ space to store all valid state combinations.

## Commented Code
```java
class Solution {
    
    public int shortestPath(int n, int[][] edges, String labels, int K) {
        // Initialize an adjacency list to represent the graph
        List<List<int[]>> adj  = new ArrayList<>(); 
        // Populate the adjacency list with empty lists for each node
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());
        
        // Iterate through all edges and populate the adjacency list with target and weight
        for(int i = 0; i < edges.length; i++){
            int[] arr = edges[i];
            int u = arr[0], v = arr[1], w = arr[2];
            adj.get(u).add(new int[] {v, w});
        }
        
        // Min-Priority Queue to process states based on the shortest accumulated distance
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        // Push the initial state: start node 0, distance 0, and initial consecutive count 1
        q.add(new int[]{0, 0, 1});
        
        // 2D distance table: tracks best distance to reach a given node with a specific consecutive character count
        int[][] dists = new int[n][K + 1]; 
        // Fill the distance table with infinity initially
        for(int[] arr : dists) Arrays.fill(arr, Integer.MAX_VALUE);
        // Base case: starting at node 0 with 1 occurrence takes 0 distance
        dists[0][1] = 0; 
        
        // Standard Dijkstra loop
        while(!q.isEmpty()){
            // Extract the state with the minimum distance
            int[] curr = q.poll();
            int u = curr[0], dist = curr[1], cons = curr[2];
            
            // If this state's distance is worse than already recorded, skip it
            if(dist > dists[u][cons]) continue;
            // If we reached the destination node, return the accumulated distance
            if(u == n - 1) return dist;
            
            // Iterate over all neighbors of the current node
            for(int[] neigh : adj.get(u)){
                int cons2 = cons;
                int v = neigh[0], w = neigh[1];
                
                // If current node and neighbor have the same label, increment consecutive count; otherwise reset to 1
                if(labels.charAt(u) == labels.charAt(v)) cons2++; 
                else cons2 = 1;
                
                // Skip if consecutive count exceeds K or if the path is not strictly better than previously recorded
                if(cons2 > K || dists[u][cons] + w >= dists[v][cons2]) continue;
                
                // Update the distance for the new state
                dists[v][cons2] = dists[u][cons] + w;
                // Push the new state into the priority queue for further exploration
                q.add(new int[]{v, dists[v][cons2], cons2});
            }
        }
        
        // Return -1 if the destination node is unreachable under the given constraints
        return -1;
    }
}
```

## Interview Tips
- Clarify whether the consecutive character count resets or accumulates when revisiting nodes.
- Discuss alternative approaches like BFS (if unweighted) or dynamic programming, and justify why Dijkstra is optimal for weighted graphs with constraints.
- Mention space-time tradeoffs when expanding state spaces in graph problems.
- Walk through a small test case with a dry run to demonstrate how the `cons` parameter prevents invalid paths.

## Revision Checklist
- [ ] Understand why standard Dijkstra fails without state expansion.
- [ ] Know how to define the state tuple `(node, constraint)`.
- [ ] Implement 2D distance array initialization correctly.
- [ ] Handle condition checks for constraint violation before queue insertion.
- [ ] Analyze time and space complexity with respect to $K$.

## Similar Problems
- `Path With Minimum Effort`
- `Cheapest Flights Within K Stops`
- `Shortest Path in a Grid with Obstacles Elimination`

## Tags
`Graph` `Dijkstra` `Shortest Path` `Priority Queue` `State Space Search`

## My Notes
wow, mindblowing, nice, dijkstra
