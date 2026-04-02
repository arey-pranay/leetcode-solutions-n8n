# Evaluate Division

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `String` `Depth-First Search` `Breadth-First Search` `Union-Find` `Graph Theory` `Shortest Path`  
**Time:** O(E + V + Q * (V + E)  
**Space:** O(V + E)

---

## Solution (java)

```java
class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        
        HashMap<String,ArrayList<Pair>> graph = new HashMap<>();
        HashSet<String> variables = new HashSet<>();
        int i=0;
        
        for(List<String> eq : equations){
            
            String var1 = eq.get(0);
            String var2 = eq.get(1);
            double val = values[i++];
            
            ArrayList<Pair> arr = graph.getOrDefault(var1, new ArrayList<>());
            arr.add(new Pair(var2,val));
            graph.put(var1,arr);
            
            arr = graph.getOrDefault(var2, new ArrayList<>());
            arr.add(new Pair(var1,1/val));
            graph.put(var2,arr);
            
            variables.add(var1);
            variables.add(var2);
        }
        
        double[] ans = new double[queries.size()];
        i = 0;
        for(List<String> query : queries){
            String var1 = query.get(0);
            String var2 = query.get(1);
            if(! (variables.contains(var1) && variables.contains(var2)) ) ans[i] = -1.0;
            else ans[i] = bfs(graph, var1, var2);
            i++;
        }
        
        return ans;
    }
    public double bfs(HashMap<String, ArrayList<Pair>> graph, String src, String dest){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(src,1));
        HashSet<String> vis = new HashSet<>();
        vis.add(src);
        while(!q.isEmpty()){
            Pair curr = q.poll();
            for(Pair neigh : graph.get(curr.var)){
                double cost = curr.dist * neigh.dist;
                if(neigh.var.equals(dest)) return cost;
                if(vis.contains(neigh.var)) continue;
                q.add(new Pair(neigh.var,cost));
                vis.add(neigh.var);
            }
        }
        return -1;
    }
    class Pair{
        String var;
        double dist;
        Pair(String var, double dist){
            this.var = var;
            this.dist = dist;
        }
        
        @Override
        public String toString(){
            return this.var + " -> " + this.dist;
        }
    }
}
```

---

---
## Quick Revision
This problem asks to evaluate division queries given a set of known variable divisions. We solve it by modeling the variables and their relationships as a graph and performing a graph traversal.

## Intuition
The problem can be thought of as finding a path between two variables in a network where edges represent division relationships. If we have `a / b = 2` and `b / c = 3`, then `a / c` can be found by multiplying these values: `(a / b) * (b / c) = a / c`, so `2 * 3 = 6`. This suggests a graph where variables are nodes and divisions are weighted edges. A Breadth-First Search (BFS) or Depth-First Search (DFS) can find the product of edge weights along a path.

## Algorithm
1.  **Build the Graph:**
    *   Create an adjacency list representation of the graph. A `HashMap<String, ArrayList<Pair>>` is suitable, where the key is a variable (String) and the value is a list of `Pair` objects. Each `Pair` stores a neighboring variable and the division value (e.g., if `a / b = 2`, add `(b, 2)` to `a`'s list and `(a, 1/2)` to `b`'s list).
    *   Maintain a `HashSet<String>` to keep track of all unique variables encountered.
2.  **Process Queries:**
    *   For each query `(numerator, denominator)`:
        *   Check if both `numerator` and `denominator` exist in the set of variables. If not, the result is `-1.0`.
        *   If they exist, perform a graph traversal (BFS or DFS) starting from `numerator` to find `denominator`.
3.  **Graph Traversal (BFS):**
    *   Initialize a queue for BFS, adding the starting node (`numerator`) with a value of `1.0` (representing `numerator / numerator`).
    *   Initialize a `HashSet` to keep track of visited nodes to avoid cycles and redundant computations.
    *   While the queue is not empty:
        *   Dequeue a `Pair` (current variable, current accumulated value).
        *   For each neighbor of the current variable:
            *   Calculate the new accumulated value by multiplying the current value with the edge weight (division value).
            *   If the neighbor is the `denominator`, return the new accumulated value.
            *   If the neighbor has not been visited, mark it as visited and enqueue it with the new accumulated value.
    *   If the `denominator` is not reached after the traversal, return `-1.0`.

## Concept to Remember
*   **Graph Representation:** Modeling relationships as a graph (nodes and weighted edges).
*   **Graph Traversal Algorithms:** BFS/DFS for pathfinding and accumulating values.
*   **Adjacency List:** Efficient way to represent sparse graphs.
*   **Handling Cycles:** Using a visited set to prevent infinite loops.

## Common Mistakes
*   **Forgetting Inverse Relationships:** Not adding the inverse division (e.g., if `a/b = 2`, forgetting to add `b/a = 1/2`).
*   **Handling Non-existent Variables:** Not checking if query variables are present in the graph, leading to errors or incorrect `-1.0` results.
*   **Incorrect Value Accumulation:** Multiplying values incorrectly during traversal, especially with floating-point numbers.
*   **Not Handling Disconnected Components:** If two variables are in different disconnected components of the graph, the traversal will fail to find a path, correctly returning `-1.0`.
*   **Integer Division:** Using integer division where floating-point division is required.

## Complexity Analysis
*   **Time:** O(E + V + Q * (V + E)) where V is the number of variables, E is the number of equations, and Q is the number of queries.
    *   Building the graph takes O(E) time.
    *   Each query involves a BFS, which in the worst case visits all nodes and edges in a connected component, taking O(V + E) time.
    *   Therefore, for Q queries, the total time is O(E + Q * (V + E)).
*   **Space:** O(V + E) for storing the graph (adjacency list) and the visited set during BFS.

## Commented Code
```java
class Solution {
    // Main method to calculate division equations
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        
        // HashMap to represent the graph: variable -> list of adjacent variables with their division values
        HashMap<String,ArrayList<Pair>> graph = new HashMap<>();
        // HashSet to store all unique variables encountered
        HashSet<String> variables = new HashSet<>();
        // Index for iterating through values array
        int i=0;
        
        // Iterate through each equation to build the graph
        for(List<String> eq : equations){
            
            // Get the numerator and denominator variables from the current equation
            String var1 = eq.get(0);
            String var2 = eq.get(1);
            // Get the corresponding division value
            double val = values[i++];
            
            // Add the edge for var1 / var2 = val
            // Get the current list of neighbors for var1, or create a new one if it doesn't exist
            ArrayList<Pair> arr = graph.getOrDefault(var1, new ArrayList<>());
            // Add a pair representing var2 and the value 'val' (var1 / var2 = val)
            arr.add(new Pair(var2,val));
            // Put the updated list back into the graph
            graph.put(var1,arr);
            
            // Add the inverse edge for var2 / var1 = 1/val
            // Get the current list of neighbors for var2, or create a new one if it doesn't exist
            arr = graph.getOrDefault(var2, new ArrayList<>());
            // Add a pair representing var1 and the value '1/val' (var2 / var1 = 1/val)
            arr.add(new Pair(var1,1/val));
            // Put the updated list back into the graph
            graph.put(var2,arr);
            
            // Add both variables to the set of all variables
            variables.add(var1);
            variables.add(var2);
        }
        
        // Array to store the results for each query
        double[] ans = new double[queries.size()];
        // Reset index for iterating through queries
        i = 0;
        // Process each query
        for(List<String> query : queries){
            // Get the numerator and denominator for the current query
            String var1 = query.get(0);
            String var2 = query.get(1);
            
            // Check if both variables exist in our graph. If not, the division is undefined.
            if(! (variables.contains(var1) && variables.contains(var2)) ) {
                // Set the result to -1.0 if either variable is not found
                ans[i] = -1.0;
            } else {
                // If both variables exist, perform BFS to find the division value
                ans[i] = bfs(graph, var1, var2);
            }
            // Move to the next query result
            i++;
        }
        
        // Return the array of results
        return ans;
    }
    
    // Breadth-First Search (BFS) to find the division value between two variables
    public double bfs(HashMap<String, ArrayList<Pair>> graph, String src, String dest){
        // Queue for BFS, storing pairs of (variable, accumulated division value)
        Queue<Pair> q = new LinkedList<>();
        // Add the source node to the queue with an initial value of 1.0 (src / src = 1)
        q.add(new Pair(src,1));
        // HashSet to keep track of visited variables to avoid cycles and redundant computations
        HashSet<String> vis = new HashSet<>();
        // Mark the source node as visited
        vis.add(src);
        
        // Continue BFS as long as the queue is not empty
        while(!q.isEmpty()){
            // Dequeue the current node (variable and its accumulated value)
            Pair curr = q.poll();
            
            // Iterate through all neighbors of the current variable
            // graph.get(curr.var) will not be null because we already checked if variables exist
            for(Pair neigh : graph.get(curr.var)){
                // Calculate the new accumulated value: (current_value * neighbor_division_value)
                // This represents (src / curr.var) * (curr.var / neigh.var) = src / neigh.var
                double cost = curr.dist * neigh.dist;
                
                // If the neighbor is the destination, we have found the path and its value
                if(neigh.var.equals(dest)) {
                    // Return the calculated division value
                    return cost;
                }
                
                // If the neighbor has not been visited yet
                if(vis.contains(neigh.var)) {
                    // Skip this neighbor if it's already visited to avoid cycles
                    continue;
                }
                
                // Enqueue the neighbor with the new accumulated value
                q.add(new Pair(neigh.var,cost));
                // Mark the neighbor as visited
                vis.add(neigh.var);
            }
        }
        // If the BFS completes without finding the destination, it means there's no path
        // or the destination is unreachable from the source in the graph.
        return -1; // Return -1.0 as per problem statement for undefined divisions
    }
    
    // Helper class to store a variable and its associated division value (distance from source)
    class Pair{
        String var; // The variable name
        double dist; // The accumulated division value to reach this variable from the source
        
        // Constructor for the Pair class
        Pair(String var, double dist){
            this.var = var;
            this.dist = dist;
        }
        
        // Override toString for easier debugging (optional but good practice)
        @Override
        public String toString(){
            return this.var + " -> " + this.dist;
        }
    }
}
```

## Interview Tips
*   **Explain the Graph Analogy:** Clearly articulate how the problem can be modeled as a graph where variables are nodes and divisions are weighted edges.
*   **Justify BFS/DFS Choice:** Explain why BFS (or DFS) is suitable for finding paths and accumulating values. Mention the use of a visited set.
*   **Edge Cases:** Discuss how you handle cases where variables are not present in the input equations or when there's no path between queried variables.
*   **Data Structures:** Be prepared to explain the choice of `HashMap` for the adjacency list and `HashSet` for visited nodes, and why they are efficient.

## Revision Checklist
- [ ] Understand the problem: Evaluate division queries based on given equations.
- [ ] Graph modeling: Represent variables as nodes and divisions as weighted edges.
- [ ] Graph construction: Implement adjacency list with inverse edges.
- [ ] Traversal algorithm: Choose BFS or DFS.
- [ ] Visited set: Implement to prevent cycles and redundant work.
- [ ] Value accumulation: Correctly multiply edge weights along the path.
- [ ] Handle unknown variables: Return -1.0 if query variables are not in the graph.
- [ ] Handle unreachable variables: Return -1.0 if no path exists.
- [ ] Complexity analysis: Time and space for graph building and queries.
- [ ] Code implementation: Write clean, commented code.

## Similar Problems
*   Course Schedule (Graph, Topological Sort)
*   Find the Town Judge (Graph, Degree Analysis)
*   Pacific Atlantic Water Flow (Graph, BFS/DFS)
*   Word Ladder (Graph, BFS)

## Tags
`Graph` `Breadth-First Search` `Depth-First Search` `Hash Map` `Union-Find`

## My Notes
amazing
