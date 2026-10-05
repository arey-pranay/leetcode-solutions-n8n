# Bus Routes

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Breadth-First Search`  
**Time:** O(N^2 * S + N * E)  
**Space:** O(N * S + N + E)

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
We solve this by modeling bus routes as nodes in a graph and finding the shortest path using Breadth-First Search (BFS).

## Intuition
The core idea is that we want to minimize the number of *bus changes*. This suggests a shortest path problem. However, the "nodes" in our search aren't individual bus stops, but rather the *buses themselves*. Two buses are "connected" if they share at least one common stop. We can then perform a BFS starting from all buses that serve the source stop, aiming to reach any bus that serves the target stop. The number of "levels" in the BFS will correspond to the minimum number of bus rides.

## Algorithm
1.  **Handle Base Case:** If the source and target stops are the same, return 0 as no buses are needed.
2.  **Data Structures:**
    *   Create an array of `HashSet<Integer>` called `stopsSet`, where `stopsSet[i]` stores all stops served by bus `i`.
    *   Create an adjacency list `graph` where `graph.get(i)` stores a list of bus indices that share a stop with bus `i`.
    *   Create two `HashSet<Integer>`: `sourceBuses` to store indices of buses serving the `source` stop, and `targetBuses` for buses serving the `target` stop.
3.  **Populate Data Structures:**
    *   Iterate through each bus route `ipRoutes[i]`.
    *   For each stop `j` in `ipRoutes[i]`:
        *   Add `j` to `stopsSet[i]`.
        *   If `j` is the `source`, add bus index `i` to `sourceBuses`.
        *   If `j` is the `target`, add bus index `i` to `targetBuses`.
    *   Initialize `graph.add(new ArrayList<>())` for each bus.
4.  **Build Bus Graph:**
    *   Iterate through all pairs of buses `(i, j)` where `i < j`.
    *   If `stopsSet[i]` and `stopsSet[j]` have any common elements (i.e., they are not disjoint), add an edge between bus `i` and bus `j` in the `graph` (add `j` to `graph.get(i)` and `i` to `graph.get(j)`).
5.  **BFS for Shortest Path:**
    *   Initialize a queue `q` for BFS.
    *   Initialize a boolean array `vis` of size `n` (number of buses) to keep track of visited buses.
    *   Add all buses from `sourceBuses` to the queue `q` and mark them as visited in `vis`.
    *   Initialize `level = 1` (representing the first bus ride).
    *   While the queue is not empty:
        *   Get the current size of the queue (`sz`).
        *   Process all nodes at the current level:
            *   Dequeue a bus index `curr`.
            *   If `curr` is in `targetBuses`, return `level` (this is the minimum number of bus rides).
            *   For each neighbor `neigh` of `curr` in the `graph`:
                *   If `neigh` has not been visited (`!vis[neigh]`):
                    *   Enqueue `neigh`.
                    *   Mark `neigh` as visited.
        *   Increment `level` for the next bus ride.
6.  **No Path Found:** If the BFS completes without reaching any target bus, return -1.

## Concept to Remember
*   **Graph Representation:** Modeling relationships between entities (buses) as nodes and connections (shared stops) as edges.
*   **Breadth-First Search (BFS):** An algorithm for traversing or searching tree or graph data structures. It explores all of the neighbor nodes at the present depth prior to moving on to the nodes at the next depth level. Ideal for finding the shortest path in an unweighted graph.
*   **Set Operations:** Efficiently checking for common elements between collections using `Collections.disjoint` or similar set intersection logic.

## Common Mistakes
*   **Confusing Stops and Buses:** Treating individual stops as nodes in the BFS instead of the buses themselves.
*   **Inefficient Stop Checking:** Using nested loops to check for shared stops between buses instead of using `HashSet` for O(1) average time lookups.
*   **Not Handling Multiple Source/Target Buses:** Forgetting to initialize the BFS queue with *all* buses that serve the source stop and to check against *all* buses that serve the target stop.
*   **Incorrect Graph Construction:** Missing edges between buses that share stops, or incorrectly adding edges.

## Complexity Analysis
*   **Time:** O(N^2 * S + N * E) where N is the number of bus routes, S is the maximum number of stops in a route, and E is the total number of edges in the bus graph.
    *   Populating `stopsSet` and finding `sourceBuses`/`targetBuses`: O(N * S) because we iterate through all stops of all routes.
    *   Building the bus graph: O(N^2 * S) in the worst case if we iterate through all pairs of buses and for each pair, check for disjoint sets. If we optimize this by mapping stops to buses, it can be improved. The provided solution has O(N^2 * S) for graph building.
    *   BFS: O(N + E), where N is the number of buses (nodes) and E is the number of connections between buses (edges). In the worst case, E can be O(N^2).
    *   The dominant factor is graph construction, leading to O(N^2 * S).
*   **Space:** O(N * S + N + E) where N is the number of bus routes, S is the maximum number of stops in a route, and E is the total number of edges in the bus graph.
    *   `stopsSet`: O(N * S) to store all stops for all buses.
    *   `graph`: O(N + E) for the adjacency list.
    *   `sourceBuses`, `targetBuses`, `q`, `vis`: O(N) in the worst case.
    *   The dominant factor is `stopsSet`, leading to O(N * S).

## Commented Code
```java
class Solution {
    // Main function to find the minimum number of buses to reach the target from the source.
    public int numBusesToDestination(int[][] ipRoutes, int source, int target) {
        
        // If the source and target stops are the same, no buses are needed.
        if(source==target) return 0;
        
        // The problem can be modeled as a shortest path problem on a graph where nodes are buses.
        // Two buses are connected if they share at least one stop.
        // We use BFS to find the shortest path (minimum number of bus rides).
        
        int n = ipRoutes.length; // Get the total number of bus routes.
        HashSet<Integer>[] stopsSet = new HashSet[n]; // Array to store stops for each bus route. Using HashSet for efficient lookups.
        
        List<List<Integer>> graph = new ArrayList<>(); // Adjacency list to represent the graph of buses.
        
        HashSet<Integer> sourceBuses = new HashSet<>(); // Set to store indices of buses that serve the source stop.
        HashSet<Integer> targetBuses = new HashSet<>(); // Set to store indices of buses that serve the target stop.
        
        // First pass: Populate stopsSet, identify sourceBuses and targetBuses, and initialize the graph.
        for(int i=0;i<n;i++) { // Iterate through each bus route.
            stopsSet[i] = new HashSet<>(); // Initialize a new HashSet for the current bus route.
            for(int j : ipRoutes[i]) { // Iterate through each stop served by the current bus route.
                stopsSet[i].add(j); // Add the stop to the set for this bus.
                
                if(j==source) sourceBuses.add(i); // If this stop is the source, add the bus index to sourceBuses.
                if(j==target) targetBuses.add(i); // If this stop is the target, add the bus index to targetBuses.
            }
            graph.add(new ArrayList<>()); // Initialize an empty adjacency list for the current bus.
        }
        
        // Second pass: Build the graph of buses. Two buses are connected if they share a stop.
        for(int i=0;i<n;i++){ // Iterate through each bus route.
            for(int j=i+1;j<n;j++){ // Iterate through subsequent bus routes to avoid duplicate checks and self-loops.
                // Check if bus i and bus j share any common stops.
                // Collections.disjoint returns true if the two sets have no elements in common.
                // So, if they are NOT disjoint, they share at least one stop.
                if(!Collections.disjoint(stopsSet[i],stopsSet[j])) {
                    graph.get(i).add(j); // Add an edge from bus i to bus j.
                    graph.get(j).add(i); // Add an edge from bus j to bus i (undirected graph).
                }
            }
        }
        
        // Perform BFS starting from all source buses to find the shortest path to any target bus.
        return shortestPathBFS(graph,sourceBuses,targetBuses);
    }
    
    // Helper function to perform BFS on the bus graph.
    public int shortestPathBFS(List<List<Integer>> graph, HashSet<Integer> sourceBuses, HashSet<Integer> targetBuses){
        Queue<Integer> q = new LinkedList<>(); // Queue for BFS. Stores bus indices.
        boolean[] vis = new boolean[graph.size()]; // Visited array to keep track of visited buses.
        
        // Initialize the queue with all buses that serve the source stop.
        for(int source : sourceBuses){
            q.add(source); // Add the bus index to the queue.
            vis[source] = true; // Mark the bus as visited.
        }
        
        int level = 1; // Initialize the level (number of bus rides) to 1.
        
        // Standard BFS loop.
        while(!q.isEmpty()){
            int sz = q.size(); // Get the number of buses at the current level.
            // Process all buses at the current level.
            for(int i=0; i<sz;i++){
                int curr = q.poll(); // Dequeue the current bus index.
                
                // If the current bus serves the target stop, we've found the shortest path.
                if(targetBuses.contains(curr)) return level;
                
                // Explore neighbors (buses connected to the current bus).
                for(int neigh : graph.get(curr)){
                    // If the neighbor bus has not been visited yet.
                    if(!vis[neigh]){
                        q.add(neigh); // Enqueue the neighbor bus.
                        vis[neigh] = true; // Mark the neighbor bus as visited.
                    }
                }
            }
            level++; // Increment the level for the next set of bus rides.
        }
        
        // If the queue becomes empty and we haven't reached the target, it means there's no path.
        return -1;
    }
}
```

## Interview Tips
1.  **Clarify the Goal:** Emphasize that the objective is to minimize bus *transfers*, not necessarily the number of stops visited. This leads to thinking about buses as nodes.
2.  **Graph Modeling:** Clearly explain how you're modeling the problem as a graph where buses are nodes and shared stops create edges. This is the crucial insight.
3.  **BFS Justification:** Explain why BFS is the correct algorithm for finding the *minimum* number of bus rides (shortest path in an unweighted graph).
4.  **Edge Cases:** Discuss the `source == target` case and the scenario where no path exists (returning -1).
5.  **Optimization Discussion:** If time permits, briefly mention potential optimizations for graph construction (e.g., using a map from stop to list of buses) to improve the time complexity from O(N^2 * S) to something closer to O(N*S + TotalStops).

## Revision Checklist
- [ ] Understand the problem: minimum bus rides from source to target.
- [ ] Recognize the problem as a shortest path on a graph.
- [ ] Model buses as nodes and shared stops as edges.
- [ ] Implement BFS starting from all source-serving buses.
- [ ] Handle multiple source and target buses correctly.
- [ ] Use efficient data structures (HashSet for stops, adjacency list for graph).
- [ ] Consider edge cases (source == target, no path).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Shortest Path in Binary Matrix
*   Shortest Bridge
*   Cheapest Flights Within K Stops
*   Rotting Oranges
*   Walls and Gates

## Tags
`Array` `Hash Map` `Breadth-First Search` `Graph`
