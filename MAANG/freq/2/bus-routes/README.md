# Bus Routes

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Breadth-First Search`  
**Time:** O(N^2 * S + N * S)  
**Space:** O(N * S + N^2)

---

## Solution (java)

```java
class Solution {
    public int numBusesToDestination(int[][] ipRoutes, int source, int target) {
        
        if(source==target) return 0;
        
        // the idea is => we start from a stop and need to reach another stop, switching as less buses as possible
        // so this is like a shortestPath graph problem, where we need to use buses as our nodes
        // but the source stop can be a stop of multiple buses, and same is true for the target stop
        // we are fine as long as any source-containing bus is able to reach any target-containing bus
        // We are using BFS, so the first answer will be the shortest always.
        // so now it is multi-eligible source and multi-eligible distance shortestPathBFS
        
        // we've established that have a lot of buses, which can be said to connected if they share a stop
        // so create a graph of connected buses. then run a bfs of all sourceBuses to any of targetBuses
        // when we reach any target, we can just return the levels or switches made till that point.
        
        
        int n = ipRoutes.length;
        HashSet<Integer>[] stopsSet = new HashSet[n]; // converting ip [][] to HashSet[] so that we can check .contains
        
        List<List<Integer>> graph = new ArrayList<>();
        
        HashSet<Integer> sourceBuses = new HashSet<>();
        HashSet<Integer> targetBuses = new HashSet<>();
        
        for(int i=0;i<n;i++) {
            stopsSet[i] = new HashSet<>();
            for(int j : ipRoutes[i]) {
                stopsSet[i].add(j);
                
                if(j==source) sourceBuses.add(i);
                if(j==target) targetBuses.add(i);
            }
            graph.add(new ArrayList<>());
        }
        
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(!Collections.disjoint(stopsSet[i],stopsSet[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        return shortestPathBFS(graph,sourceBuses,targetBuses);
    }
    public int shortestPathBFS(List<List<Integer>> graph, HashSet<Integer> sourceBuses, HashSet<Integer> targetBuses){
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[graph.size()];
        
        for(int source : sourceBuses){
            q.add(source);
            vis[source] = true;
        }
        
        int level = 1;
        
        while(!q.isEmpty()){
            int sz = q.size();
            for(int i=0; i<sz;i++){
                int curr = q.poll();
                if(targetBuses.contains(curr)) return level;
                for(int neigh : graph.get(curr)){
                    if(!vis[neigh]){
                        q.add(neigh);
                        vis[neigh] = true;
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
```

---

---
## Quick Revision
This problem asks for the minimum number of buses to take to travel from a source stop to a target stop.
We can model this as a shortest path problem on a graph where buses are nodes and an edge exists if two buses share a stop.

## Intuition
The core idea is to find the minimum number of "bus changes" required. This sounds like a shortest path problem. However, the "nodes" in our graph aren't the bus stops themselves, but rather the *bus routes*. Two bus routes are connected if they share at least one common stop. Once we have this graph of bus routes, we can perform a Breadth-First Search (BFS) starting from all bus routes that serve the `source` stop, and find the shortest path to any bus route that serves the `target` stop. BFS naturally finds the shortest path in terms of the number of edges (bus changes).

## Algorithm
1.  **Handle Base Case:** If `source` is equal to `target`, return `0` as no buses are needed.
2.  **Data Structures Initialization:**
    *   Create an array of `HashSet<Integer>` called `stopsSet`, where `stopsSet[i]` will store all stops served by bus `i`.
    *   Create an adjacency list `graph` where `graph.get(i)` will store a list of bus indices that share a stop with bus `i`.
    *   Create two `HashSet<Integer>`: `sourceBuses` to store indices of buses serving the `source` stop, and `targetBuses` to store indices of buses serving the `target` stop.
3.  **Populate Data Structures:**
    *   Iterate through each bus route `ipRoutes[i]`.
    *   For each stop `j` in `ipRoutes[i]`:
        *   Add `j` to `stopsSet[i]`.
        *   If `j` is the `source`, add bus index `i` to `sourceBuses`.
        *   If `j` is the `target`, add bus index `i` to `targetBuses`.
    *   Initialize `graph.add(new ArrayList<>())` for each bus.
4.  **Build Bus Graph:**
    *   Iterate through all pairs of bus routes `(i, j)` where `i < j`.
    *   If `stopsSet[i]` and `stopsSet[j]` have at least one common stop (i.e., `!Collections.disjoint(stopsSet[i], stopsSet[j])`), add an edge between bus `i` and bus `j` in the `graph` (add `j` to `graph.get(i)` and `i` to `graph.get(j)`).
5.  **Perform BFS:**
    *   Implement a `shortestPathBFS` helper function.
    *   Initialize a queue `q` and a boolean array `vis` to keep track of visited buses.
    *   Add all buses from `sourceBuses` to the queue `q` and mark them as visited.
    *   Initialize `level` to `1` (representing the first bus taken).
    *   While the queue is not empty:
        *   Process all buses at the current `level`.
        *   For each `curr` bus dequeued:
            *   If `curr` is in `targetBuses`, return `level`.
            *   For each `neigh` bus connected to `curr`:
                *   If `neigh` has not been visited, enqueue it and mark it as visited.
        *   Increment `level`.
6.  **Return Result:** If the BFS completes without reaching the target, return `-1`.

## Concept to Remember
*   **Graph Representation:** Modeling relationships between entities (buses) as nodes and connections (shared stops) as edges.
*   **Breadth-First Search (BFS):** Optimal algorithm for finding the shortest path in an unweighted graph, measured by the number of edges.
*   **Set Operations:** Efficiently checking for common elements between collections (e.g., `Collections.disjoint`).
*   **Multi-Source BFS:** Starting BFS from multiple initial nodes simultaneously.

## Common Mistakes
*   **Confusing Stops and Buses:** Incorrectly treating bus stops as nodes in the graph instead of bus routes.
*   **Inefficient Stop Checking:** Using nested loops to check for common stops between buses, leading to a high time complexity. Using `HashSet` and `Collections.disjoint` is crucial.
*   **Not Handling Source/Target Buses Correctly:** Forgetting to initialize the BFS with *all* buses that serve the source stop, or not checking against *all* buses that serve the target stop.
*   **Off-by-One Error in Level Counting:** Miscounting the number of bus transfers (e.g., starting level at 0 instead of 1 for the first bus).

## Complexity Analysis
*   **Time:** O(N^2 * S + N * S) where N is the number of bus routes and S is the maximum number of stops in a route.
    *   Building `stopsSet`: O(N * S)
    *   Building the bus graph: O(N^2 * S) in the worst case if `Collections.disjoint` takes O(S) for hash sets.
    *   BFS: O(N + E) where E is the number of edges in the bus graph. E can be at most O(N^2). So, O(N^2).
    *   The dominant factor is building the bus graph.
*   **Space:** O(N * S + N^2)
    *   `stopsSet`: O(N * S) to store all stops.
    *   `graph`: O(N^2) in the worst case for the adjacency list.
    *   BFS queue and visited array: O(N).

## Commented Code
```java
class Solution {
    // Main function to find the minimum number of buses to reach the target from the source.
    public int numBusesToDestination(int[][] ipRoutes, int source, int target) {
        
        // If the source and target stops are the same, no buses are needed.
        if(source==target) return 0;
        
        // The problem can be modeled as a shortest path problem on a graph.
        // The nodes of this graph are the bus routes themselves.
        // An edge exists between two bus routes if they share at least one common stop.
        // We want to find the shortest path (minimum bus changes) from any bus serving the source
        // to any bus serving the target. BFS is suitable for this.
        
        int n = ipRoutes.length; // Get the total number of bus routes.
        
        // Create an array of HashSets to store stops for each bus route.
        // This allows for efficient checking of stop existence and common stops.
        HashSet<Integer>[] stopsSet = new HashSet[n]; 
        
        // Create an adjacency list to represent the graph of bus routes.
        // graph.get(i) will store indices of buses connected to bus i.
        List<List<Integer>> graph = new ArrayList<>();
        
        // HashSets to store the indices of buses that serve the source and target stops.
        HashSet<Integer> sourceBuses = new HashSet<>();
        HashSet<Integer> targetBuses = new HashSet<>();
        
        // First pass: Populate stopsSet, identify sourceBuses and targetBuses, and initialize graph.
        for(int i=0;i<n;i++) { // Iterate through each bus route.
            stopsSet[i] = new HashSet<>(); // Initialize a new HashSet for the current bus route.
            for(int j : ipRoutes[i]) { // Iterate through each stop served by the current bus route.
                stopsSet[i].add(j); // Add the stop to the current bus's stops set.
                
                if(j==source) sourceBuses.add(i); // If this stop is the source, add the bus index to sourceBuses.
                if(j==target) targetBuses.add(i); // If this stop is the target, add the bus index to targetBuses.
            }
            graph.add(new ArrayList<>()); // Initialize an empty adjacency list for the current bus route.
        }
        
        // Second pass: Build the graph of connected buses.
        // Two buses are connected if they share at least one stop.
        for(int i=0;i<n;i++){ // Iterate through each bus route.
            for(int j=i+1;j<n;j++){ // Iterate through subsequent bus routes to avoid duplicate checks and self-loops.
                // Check if bus i and bus j share any common stops.
                // Collections.disjoint returns true if the two sets have no elements in common.
                if(!Collections.disjoint(stopsSet[i],stopsSet[j])) { 
                    // If they share a stop, add an edge between them in the graph.
                    graph.get(i).add(j); // Add bus j to bus i's adjacency list.
                    graph.get(j).add(i); // Add bus i to bus j's adjacency list (undirected graph).
                }
            }
        }
        
        // Perform BFS to find the shortest path from any source bus to any target bus.
        return shortestPathBFS(graph,sourceBuses,targetBuses);
    }
    
    // Helper function to perform BFS on the bus graph.
    public int shortestPathBFS(List<List<Integer>> graph, HashSet<Integer> sourceBuses, HashSet<Integer> targetBuses){
        Queue<Integer> q = new LinkedList<>(); // Queue for BFS. Stores bus indices.
        boolean[] vis = new boolean[graph.size()]; // Visited array to keep track of visited bus routes.
        
        // Initialize the BFS queue with all buses that serve the source stop.
        for(int source : sourceBuses){
            q.add(source); // Add the bus index to the queue.
            vis[source] = true; // Mark the bus as visited.
        }
        
        int level = 1; // Initialize the level counter. Level 1 means taking the first bus.
        
        // Standard BFS loop.
        while(!q.isEmpty()){
            int sz = q.size(); // Get the number of nodes at the current level.
            for(int i=0; i<sz;i++){ // Process all nodes at the current level.
                int curr = q.poll(); // Dequeue the current bus index.
                
                // If the current bus serves the target stop, we've found the shortest path.
                if(targetBuses.contains(curr)) return level; // Return the current level (number of buses taken).
                
                // Explore neighbors (connected buses).
                for(int neigh : graph.get(curr)){ // Iterate through all buses connected to the current bus.
                    if(!vis[neigh]){ // If the neighbor bus has not been visited yet.
                        q.add(neigh); // Enqueue the neighbor bus.
                        vis[neigh] = true; // Mark the neighbor bus as visited.
                    }
                }
            }
            level++; // Increment the level for the next set of buses.
        }
        
        // If the queue becomes empty and we haven't reached the target, it's impossible.
        return -1;
    }
}
```

## Interview Tips
1.  **Clarify the Graph:** Explicitly state that you're building a graph where *buses* are nodes, not stops. This is a key insight.
2.  **Explain BFS Choice:** Justify why BFS is the correct algorithm for finding the *minimum number* of transfers.
3.  **Edge Cases:** Discuss the `source == target` case and the case where the target is unreachable.
4.  **Data Structure Rationale:** Explain why `HashSet` is used for stops (efficient lookups) and why an adjacency list is used for the graph.
5.  **Complexity Breakdown:** Be prepared to walk through the time and space complexity, especially the O(N^2 * S) part for graph construction.

## Revision Checklist
- [ ] Understand the problem: minimum bus transfers.
- [ ] Identify the graph structure: buses as nodes, shared stops as edges.
- [ ] Choose BFS for shortest path.
- [ ] Implement data structures: `stopsSet`, `graph`, `sourceBuses`, `targetBuses`.
- [ ] Correctly build the bus graph by checking for common stops.
- [ ] Implement multi-source BFS.
- [ ] Handle base case `source == target`.
- [ ] Handle unreachable target.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Shortest Path in Binary Matrix
*   Shortest Path in a Grid with Obstacles Elimination
*   Cheapest Flights Within K Stops
*   Rotting Oranges
*   Word Ladder

## Tags
`Array` `Hash Map` `Breadth-First Search` `Graph`
