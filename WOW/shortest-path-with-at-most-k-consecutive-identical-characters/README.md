# Shortest Path With At Most K Consecutive Identical Characters

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Graph Theory` `Heap (Priority Queue)` `Shortest Path`  
**Time:** O(K \cdot (E \log(V \cdot K)  
**Space:** O(K \cdot (E \log(V \cdot K)

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
Find the shortest path from start to end node under the constraint that no more than $K$ identical characters can appear consecutively.
Solve this using Dijkstra's algorithm with a state-space expansion where the state is defined by `(node, consecutive_count)`.

## Intuition
Standard shortest path algorithms like Dijkstra only track the current node to find the optimal distance. However, when path validity depends on history (like consecutive identical character limits), the state space must be expanded. By tracking both the current node and the number of consecutive identical characters accumulated so far as part of our state, we can use Dijkstra's algorithm to safely explore all valid paths while pruning invalid ones or suboptimal paths for the same state.

## Algorithm
1. Build an adjacency list representation of the graph using the given edges and weights.
2. Initialize a Min-Priority Queue for Dijkstra's algorithm to store states containing `(current_node, accumulated_distance, consecutive_count)`.
3. Initialize a 2D distance array `dists[n][K+1]` with infinity to store the minimum distance to reach each node with a specific consecutive identical character count.
4. Set the distance for the starting node `0` with an initial consecutive count of `1` to `0`, and push this state into the priority queue.
5. While the priority queue is not empty, pop the state with the smallest distance.
6. If the popped distance is greater than the recorded best distance for that state, skip it. If the current node is the destination `n-1`, return the distance.
7. Iterate through all neighbors of the current node. Calculate the new consecutive count based on whether the label of the current node matches the neighbor's label.
8. If the new consecutive count exceeds $K$, or if the path is not strictly better than the previously recorded distance for that neighbor state, skip it.
9. Otherwise, update the distance table, push the new state into the priority queue, and repeat until the queue is empty or the destination is reached.
10. Return `-1` if the priority queue becomes empty without reaching the destination node.

## Concept to Remember
- State-Space Graph Traversal: Expanding nodes into richer states `(node, extra_parameter)` to handle complex path constraints.
- Modified Dijkstra's Algorithm: Applying greedy shortest-path search when edge weights are non-negative and state dimensions are manageable.
- Constraint Propagation: Carrying forward contextual information (like consecutive matching characters) during graph expansion.

## Common Mistakes
- Failing to expand the state space, leading to incorrect validation of the $K$ consecutive characters constraint.
- Forgetting to reset the consecutive count to `1` when transitioning to a node with a different label.
- Incorrectly sizing the distance tracking table, which can cause `ArrayIndexOutOfBoundsException` when the consecutive count reaches $K+1$.
- Omitting the state check `dist > dists[u][cons]`, leading to redundant and inefficient re-processing of states.

## Complexity Analysis
- Time: $O(K \cdot (E \log(V \cdot K) + V \cdot K)$ - The state space has $V \times K$ states, and each state can be pushed/popped from the priority queue with degree-based transitions.
- Space: $O(V \cdot K + E)$ - Required to store the graph adjacency list and the 2D distance tracking table of size $N \times (K+1)$.

## Commented Code
```java
class Solution {
    
    public int shortestPath(int n, int[][] edges, String labels, int K) {
        // Create an adjacency list to represent the graph where each entry stores a list of {neighbor, weight}
        List<List<int[]>> adj  = new ArrayList<>(); 
        // Initialize an empty adjacency list for each of the n nodes
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());
        
        // Populate the adjacency list using the given edge definitions
        for(int i = 0; i < edges.length; i++){
            int[] arr = edges[i];
            int u = arr[0], v = arr[1], w = arr[2];
            // Add directed edge from u to v with weight w
            adj.get(u).add(new int[] {v, w});
        }
        
        // Initialize a min-priority queue ordered by accumulated distance (index 1 of the state array)
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        // Push the starting state: node 0, distance 0, and initial consecutive count 1
        q.add(new int[]{0, 0, 1});
        
        // 2D distance table: tracks the minimum distance to reach a given node with a specific consecutive character count
        int[][] dists = new int[n][K + 1]; 
        // Fill the distance table with infinity values initially
        for(int[] arr : dists) Arrays.fill(arr, Integer.MAX_VALUE);
        // Base case: reaching node 0 with 1 occurrence costs a distance of 0
        dists[0][1] = 0; 
        
        // Standard Dijkstra's loop processing states from the priority queue
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int u = curr[0], dist = curr[1], cons = curr[2];
            
            // Skip processing if we have already found a strictly better way to reach this state
            if(dist > dists[u][cons]) continue;
            // If we have reached the target node n-1, return the accumulated shortest distance
            if(u == n - 1) return dist;
            
            // Iterate over all outgoing neighbors from the current node
            for(int[] neigh : adj.get(u)){
                int cons2 = cons;
                int v = neigh[0], w = neigh[1];
                
                // If the current node and neighbor share the same label, increment the consecutive count; otherwise reset to 1
                if(labels.charAt(u) == labels.charAt(v)) cons2++; 
                else cons2 = 1;
                
                // Prune paths that exceed the maximum allowed consecutive count K or are not better than existing recorded paths
                if(cons2 > K || dists[u][cons] + w >= dists[v][cons2]) continue;
                
                // Update the minimum distance to reach neighbor v with consecutive count cons2
                dists[v][cons2] = dists[u][cons] + w;
                // Push the new state into the priority queue for further exploration
                q.add(new int[]{v, dists[v][cons2], cons2});
            }
        }
        
        // Return -1 if the target node is unreachable within the constraints
        return -1;
    }
}
```

## Interview Tips
- Clarify whether the graph is directed or undirected right at the beginning to avoid edge-case traversal bugs.
- Explicitly explain why a standard 1D distance array is insufficient and why a 2D state-space expansion `(node, constraint)` is necessary.
- Discuss how state-space graph transformation converts constrained problems into standard shortest-path graph problems.
- Analyze the time and space complexity clearly, emphasizing how the multiplier $K$ impacts overall performance.

## Revision Checklist
- [ ] Understand why standard Dijkstra fails under state-dependent path constraints.
- [ ] Practice modeling secondary tracking parameters into graph states.
- [ ] Write out the state-transition conditions for resetting or incrementing constraints cleanly.
- [ ] Verify that distance pruning checks correctly prevent infinite loops and redundant work.

## Similar Problems
- `Cheapest Flights Within K Stops`
- `Path With Minimum Effort`
- `Minimum Cost to Reach Destination in Time`

## Tags
`Graph` `Shortest Path` `Dijkstra` `Priority Queue` `State Space Search`
