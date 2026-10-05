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
        
        //disjoint sirf set p use kr skte hai , aur isse actual disjo
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
We can model this as a graph problem where buses are nodes and an edge exists between two buses if they share a common stop, then perform a Breadth-First Search (BFS).

## Intuition
The core idea is to find the shortest path in terms of bus "switches". If we think of each bus as a node in a graph, an edge exists between two buses if they share at least one common stop. This is because if two buses share a stop, we can transfer from one to the other at that stop. The problem then becomes finding the shortest path from any bus that serves the source stop to any bus that serves the target stop. BFS is the natural choice for finding the shortest path in an unweighted graph.

## Algorithm
1.  **Handle Base Case**: If the source and target stops are the same, return 0 as no buses are needed.
2.  **Data Structures**:
    *   Create an array of `HashSet<Integer>` where each `HashSet` stores the stops served by a particular bus route. This allows for efficient checking of stop presence.
    *   Create an adjacency list `List<List<Integer>>` to represent the graph where nodes are bus indices. An edge exists between two bus indices if they share a common stop.
    *   Use `HashSet<Integer>` to store the indices of buses that serve the `source` stop and `target` stop.
3.  **Preprocessing**:
    *   Iterate through each bus route (`ipRoutes`).
    *   For each bus, populate its corresponding `HashSet` with all its stops.
    *   While populating, check if the current stop is the `source` or `target`. If it is, add the bus index to `sourceBuses` or `targetBuses` respectively.
4.  **Build Bus Graph**:
    *   Iterate through all pairs of bus routes (i, j) where i < j.
    *   Check if `stopsSet[i]` and `stopsSet[j]` have any common elements using `Collections.disjoint()`.
    *   If they share a stop (i.e., `!Collections.disjoint(...)`), add an edge between bus `i` and bus `j` in the `graph` adjacency list (add `j` to `graph.get(i)` and `i` to `graph.get(j)`).
5.  **BFS for Shortest Path**:
    *   Initialize a queue `q` for BFS.
    *   Initialize a boolean array `vis` of the same size as the number of buses to keep track of visited buses.
    *   Add all buses in `sourceBuses` to the queue and mark them as visited.
    *   Initialize `level` to 1 (representing the first bus taken).
    *   While the queue is not empty:
        *   Get the size of the current level (`sz`).
        *   Process all nodes at the current level:
            *   Dequeue a bus index `curr`.
            *   If `curr` is present in `targetBuses`, return `level` (this is the minimum number of buses).
            *   For each neighbor `neigh` of `curr` in the `graph`:
                *   If `neigh` has not been visited, enqueue it and mark it as visited.
        *   Increment `level` for the next level of buses.
6.  **No Path**: If the BFS completes without reaching any target bus, return -1.

## Concept to Remember
*   **Graph Representation**: Modeling relationships between entities (buses) as nodes and connections (shared stops) as edges.
*   **Breadth-First Search (BFS)**: An algorithm for traversing or searching tree or graph data structures. It explores all of the neighbor nodes at the present depth prior to moving on to the nodes at the next depth level. Ideal for finding the shortest path in unweighted graphs.
*   **Set Operations**: Efficiently checking for common elements between collections using `HashSet` and `Collections.disjoint()`.

## Common Mistakes
*   **Incorrect Graph Construction**: Building a graph where stops are nodes instead of buses, or incorrectly defining edges between buses.
*   **Not Handling Multiple Source/Target Buses**: Forgetting that the source and target stops can be served by multiple bus routes, and not initializing BFS with all relevant source buses or checking against all relevant target buses.
*   **Inefficient Stop Checking**: Using lists instead of sets to store stops for each bus, leading to O(N) lookups instead of O(1) on average.
*   **Off-by-One Error in BFS Level**: Incorrectly initializing or incrementing the `level` counter in BFS, leading to an incorrect bus count.

## Complexity Analysis
*   **Time**: O(N^2 * S + N * E) where N is the number of bus routes, S is the maximum number of stops in a route, and E is the number of edges in the bus graph.
    *   Preprocessing (converting routes to sets and finding source/target buses): O(N * S) where N is the number of routes and S is the max stops per route.
    *   Building the bus graph: O(N^2 * S) in the worst case if `Collections.disjoint` takes O(S) and we compare all pairs of N routes.
    *   BFS: O(N + E) where N is the number of bus nodes and E is the number of edges in the bus graph. In the worst case, E can be O(N^2).
    *   The dominant factor is building the graph, so it's roughly O(N^2 * S).
*   **Space**: O(N * S + N + E) where N is the number of bus routes, S is the maximum number of stops in a route, and E is the number of edges in the bus graph.
    *   `stopsSet`: O(N * S) to store all stops for all routes.
    *   `graph`: O(N + E) for the adjacency list.
    *   Queue and `vis` array in BFS: O(N).
    *   The dominant factor is storing the stops, so it's O(N * S).

## Commented Code
```java
class Solution {
    public int numBusesToDestination(int[][] ipRoutes, int source, int target) {
        
        // If the source and target stops are the same, no buses are needed.
        if(source==target) return 0;
        
        // The problem can be modeled as finding the shortest path in a graph where buses are nodes.
        // An edge exists between two buses if they share a common stop, allowing for transfers.
        // We use BFS to find the minimum number of bus transfers (shortest path).
        
        // The number of bus routes.
        int n = ipRoutes.length;
        // Array of HashSets to store stops for each bus route. HashSet provides O(1) average time complexity for contains() checks.
        HashSet<Integer>[] stopsSet = new HashSet[n]; 
        
        // Adjacency list to represent the graph of buses. graph.get(i) will store a list of bus indices connected to bus i.
        List<List<Integer>> graph = new ArrayList<>();
        
        // HashSet to store indices of buses that serve the source stop.
        HashSet<Integer> sourceBuses = new HashSet<>();
        // HashSet to store indices of buses that serve the target stop.
        HashSet<Integer> targetBuses = new HashSet<>();
        
        // Initialize stopsSet, graph, and identify source/target buses.
        for(int i=0;i<n;i++) {
            // Initialize the HashSet for the current bus route.
            stopsSet[i] = new HashSet<>();
            // Iterate through all stops of the current bus route.
            for(int j : ipRoutes[i]) {
                // Add the stop to the HashSet for this bus.
                stopsSet[i].add(j);
                
                // If the stop is the source, add this bus index to sourceBuses.
                if(j==source) sourceBuses.add(i);
                // If the stop is the target, add this bus index to targetBuses.
                if(j==target) targetBuses.add(i);
            }
            // Initialize the adjacency list for the current bus index.
            graph.add(new ArrayList<>());
        }
        
        // Build the graph of connected buses.
        // Iterate through all unique pairs of bus routes.
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                // Check if bus i and bus j share any common stops.
                // Collections.disjoint() returns true if the two sets have no elements in common.
                // So, if they are NOT disjoint, they share at least one stop.
                if(!Collections.disjoint(stopsSet[i],stopsSet[j])) {
                    // If they share a stop, add an edge between bus i and bus j in the graph.
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        
        // Perform BFS starting from all source buses to find the shortest path to any target bus.
        return shortestPathBFS(graph,sourceBuses,targetBuses);
    }
    
    // Helper function to perform BFS on the bus graph.
    public int shortestPathBFS(List<List<Integer>> graph, HashSet<Integer> sourceBuses, HashSet<Integer> targetBuses){
        // Queue for BFS, storing bus indices.
        Queue<Integer> q = new LinkedList<>();
        // Boolean array to keep track of visited bus indices.
        boolean[] vis = new boolean[graph.size()];
        
        // Initialize the BFS queue with all buses that serve the source stop.
        for(int source : sourceBuses){
            q.add(source);
            // Mark these initial buses as visited.
            vis[source] = true;
        }
        
        // Initialize the level counter. Level 1 means taking the first bus.
        int level = 1;
        
        // Standard BFS loop.
        while(!q.isEmpty()){
            // Get the number of nodes at the current level.
            int sz = q.size();
            // Process all nodes at the current level.
            for(int i=0; i<sz;i++){
                // Dequeue the current bus index.
                int curr = q.poll();
                
                // If the current bus serves the target stop, we have found the shortest path.
                // Return the current level, which represents the minimum number of buses taken.
                if(targetBuses.contains(curr)) return level;
                
                // Explore neighbors (connected buses) of the current bus.
                for(int neigh : graph.get(curr)){
                    // If the neighbor bus has not been visited yet.
                    if(!vis[neigh]){
                        // Enqueue the neighbor bus.
                        q.add(neigh);
                        // Mark the neighbor bus as visited.
                        vis[neigh] = true;
                    }
                }
            }
            // Increment the level for the next set of buses to explore.
            level++;
        }
        
        // If the BFS completes without reaching any target bus, it means there's no path.
        return -1;
    }
}
```

## Interview Tips
1.  **Clarify the Goal**: Emphasize that the goal is to minimize the *number of buses taken*, not the number of stops or distance. This directly leads to thinking about bus transfers as the "cost".
2.  **Graph Modeling**: Clearly explain your thought process for modeling the problem as a graph. Specifically, why buses are nodes and how shared stops create edges. This is a crucial part of the intuition.
3.  **BFS Justification**: Explain why BFS is the appropriate algorithm for finding the shortest path in this scenario (unweighted graph, minimizing number of edges/transfers).
4.  **Edge Cases**: Be prepared to discuss edge cases like `source == target`, no possible route, and scenarios with many overlapping bus routes.
5.  **Complexity Trade-offs**: Discuss the time and space complexity, and how using `HashSet` for stop lookups is essential for efficiency.

## Revision Checklist
- [ ] Understand the problem: minimum bus rides, not stops.
- [ ] Model buses as nodes in a graph.
- [ ] Define edges: shared stops between buses.
- [ ] Use BFS for shortest path in the bus graph.
- [ ] Handle multiple source and target buses correctly.
- [ ] Optimize stop lookups using HashSets.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (source == target, no path).

## Similar Problems
*   Shortest Path in Binary Matrix
*   Shortest Bridge
*   Word Ladder
*   Rotting Oranges
*   Parallel Courses

## Tags
`Array` `Hash Map` `Breadth-First Search` `Graph`
