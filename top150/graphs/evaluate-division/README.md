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
This problem asks to evaluate division queries given a set of known variable divisions. We can solve this by modeling the variables and their relationships as a graph and performing a graph traversal.

## Intuition
The core idea is to represent the division relationships as a graph where variables are nodes and the division value is the weight of the edge. If we have `a / b = 2.0`, we can think of an edge from `a` to `b` with weight `2.0` and an edge from `b` to `a` with weight `1/2.0`. To answer a query like `x / y`, we need to find a path from `x` to `y` in this graph. The product of edge weights along this path will give us `x / y`. Breadth-First Search (BFS) or Depth-First Search (DFS) are suitable for finding such paths.

## Algorithm
1.  **Build the Graph:**
    *   Create an adjacency list representation of the graph. A `HashMap<String, ArrayList<Pair>>` is suitable, where the key is a variable (String) and the value is a list of `Pair` objects. Each `Pair` will store a neighboring variable and the division value (double).
    *   Iterate through the `equations` and `values` arrays. For each equation `[A, B]` and its corresponding value `val` (meaning `A / B = val`):
        *   Add an edge from `A` to `B` with weight `val`.
        *   Add an edge from `B` to `A` with weight `1/val`.
    *   Keep track of all unique variables encountered in a `HashSet` to quickly check if a query variable exists in the graph.

2.  **Process Queries:**
    *   Initialize a `double[]` array to store the results for each query.
    *   Iterate through each `query` in the `queries` list. Let the query be `[C, D]`.
    *   **Handle Unknown Variables:** If either `C` or `D` is not present in the set of known variables, the result for this query is `-1.0`.
    *   **Perform Graph Traversal (BFS):** If both variables are known, call a traversal function (e.g., `bfs`) starting from `C` to find `D`.
        *   The traversal function will use a queue to store `Pair` objects, where each `Pair` contains the current variable and the accumulated product of division values from the source to this variable.
        *   Maintain a `HashSet` to keep track of visited nodes to avoid cycles and redundant computations.
        *   Start with `(C, 1.0)` in the queue.
        *   While the queue is not empty:
            *   Dequeue a `Pair` (current variable, current product).
            *   For each neighbor of the current variable:
                *   Calculate the new product: `new_product = current_product * edge_weight`.
                *   If the neighbor is the `dest` variable (`D`), return `new_product`.
                *   If the neighbor has not been visited:
                    *   Mark it as visited.
                    *   Enqueue `(neighbor_variable, new_product)`.
        *   If the traversal completes without finding the `dest` variable, it means there's no path, so return `-1.0`.
    *   Store the result of the traversal in the `ans` array for the current query.

3.  **Return Results:** Return the `ans` array.

## Concept to Remember
*   **Graph Representation:** Modeling relationships as nodes and edges, specifically using an adjacency list for sparse graphs.
*   **Graph Traversal Algorithms:** BFS or DFS for finding paths and accumulating values along paths.
*   **Handling Cycles and Redundancy:** Using a visited set to ensure efficiency and correctness.
*   **Floating-Point Arithmetic:** Being mindful of potential precision issues with double-precision floating-point numbers.

## Common Mistakes
*   **Forgetting Inverse Edges:** Not adding the reciprocal edge (e.g., `B/A = 1/(A/B)`) when building the graph, making it impossible to traverse in both directions.
*   **Not Handling Unknown Variables:** Failing to check if query variables exist in the graph, leading to errors or incorrect `-1.0` results.
*   **Infinite Loops:** Not using a `visited` set in the BFS/DFS, which can lead to infinite loops in graphs with cycles.
*   **Incorrect Product Calculation:** Miscalculating the accumulated product along the path, especially when dealing with multiple edges.
*   **Floating-Point Precision:** Relying on exact equality checks for doubles, which can be problematic. While not explicitly a major issue in this problem's typical test cases, it's a general concern.

## Complexity Analysis
*   **Time:** O(E + V + Q * (V + E)) where E is the number of equations, V is the number of unique variables, and Q is the number of queries.
    *   Building the graph takes O(E) time.
    *   Each query involves a BFS/DFS traversal, which in the worst case visits all V nodes and E edges. So, Q queries take O(Q * (V + E)).
    *   In the worst case, V can be up to 2*E. So, the time complexity can be simplified to O(E + Q * (E + V)).
*   **Space:** O(V + E) for storing the graph (adjacency list) and the visited set during traversal.

## Commented Code
```java
class Solution {
    // Main method to calculate division equations
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        
        // graph: Adjacency list to represent the relationships between variables.
        // Key: variable name (String)
        // Value: ArrayList of Pair objects, where each Pair represents a neighbor and the division value.
        HashMap<String,ArrayList<Pair>> graph = new HashMap<>();
        
        // variables: A set to store all unique variable names encountered. Used for quick lookups.
        HashSet<String> variables = new HashSet<>();
        
        // i: Index for iterating through the values array.
        int i=0;
        
        // Iterate through each equation to build the graph.
        for(List<String> eq : equations){
            
            // Get the two variables from the current equation.
            String var1 = eq.get(0);
            String var2 = eq.get(1);
            
            // Get the corresponding division value (var1 / var2 = val).
            double val = values[i++];
            
            // Add edge for var1 -> var2 with weight 'val'.
            // graph.getOrDefault(var1, new ArrayList<>()) retrieves the list for var1, or creates a new one if it doesn't exist.
            ArrayList<Pair> arr = graph.getOrDefault(var1, new ArrayList<>());
            // Add a new Pair representing the neighbor var2 and the division value 'val'.
            arr.add(new Pair(var2,val));
            // Put the updated list back into the graph for var1.
            graph.put(var1,arr);
            
            // Add edge for var2 -> var1 with weight '1/val' (since var2 / var1 = 1 / val).
            arr = graph.getOrDefault(var2, new ArrayList<>());
            arr.add(new Pair(var1,1/val));
            graph.put(var2,arr);
            
            // Add both variables to the set of known variables.
            variables.add(var1);
            variables.add(var2);
        }
        
        // ans: Array to store the results for each query.
        double[] ans = new double[queries.size()];
        
        // i: Reset index for iterating through queries.
        i = 0;
        
        // Process each query.
        for(List<String> query : queries){
            
            // Get the numerator and denominator for the current query.
            String var1 = query.get(0);
            String var2 = query.get(1);
            
            // Check if both variables in the query exist in our graph.
            // If either variable is unknown, the result is -1.0.
            if(! (variables.contains(var1) && variables.contains(var2)) ) {
                ans[i] = -1.0; // Assign -1.0 if variables are not in the graph.
            } else {
                // If both variables are known, perform BFS to find the division result.
                ans[i] = bfs(graph, var1, var2); // Call BFS to get the result.
            }
            // Move to the next query result.
            i++;
        }
        
        // Return the array of results.
        return ans;
    }
    
    // Breadth-First Search (BFS) function to find the division result between src and dest.
    public double bfs(HashMap<String, ArrayList<Pair>> graph, String src, String dest){
        
        // q: Queue for BFS. Stores Pair objects (variable, accumulated product from src).
        Queue<Pair> q = new LinkedList<>();
        // Add the source node to the queue with an initial product of 1.0.
        q.add(new Pair(src,1));
        
        // vis: Set to keep track of visited nodes during BFS to avoid cycles and redundant work.
        HashSet<String> vis = new HashSet<>();
        // Mark the source node as visited.
        vis.add(src);
        
        // Continue BFS as long as there are nodes in the queue.
        while(!q.isEmpty()){
            // Dequeue the current node and its accumulated product.
            Pair curr = q.poll();
            
            // Iterate through all neighbors of the current node.
            for(Pair neigh : graph.get(curr.var)){
                // Calculate the product to reach the neighbor: current product * edge weight.
                double cost = curr.dist * neigh.dist;
                
                // If the neighbor is the destination, we've found the path. Return the calculated product.
                if(neigh.var.equals(dest)) return cost;
                
                // If the neighbor has already been visited, skip it to avoid cycles and redundant processing.
                if(vis.contains(neigh.var)) continue;
                
                // If the neighbor has not been visited:
                // Add the neighbor to the queue with the new accumulated product.
                q.add(new Pair(neigh.var,cost));
                // Mark the neighbor as visited.
                vis.add(neigh.var);
            }
        }
        // If the loop finishes and the destination is not found, it means there's no path. Return -1.0.
        return -1;
    }
    
    // Helper class to store a variable name and its associated distance/product.
    class Pair{
        String var; // The variable name.
        double dist; // The accumulated division product from the source.
        
        // Constructor for the Pair class.
        Pair(String var, double dist){
            this.var = var;
            this.dist = dist;
        }
        
        // Override toString for easier debugging (optional but good practice).
        @Override
        public String toString(){
            return this.var + " -> " + this.dist;
        }
    }
}
```

## Interview Tips
1.  **Graph Modeling:** Clearly explain how you're modeling the problem as a graph. Mention nodes (variables) and weighted edges (division values).
2.  **Traversal Choice:** Justify your choice of BFS (or DFS). BFS is often preferred for finding shortest paths in unweighted graphs, but here it's used to find *any* path and accumulate values. Explain that it systematically explores layer by layer.
3.  **Edge Cases:** Discuss how you handle cases where variables in a query are not present in the input equations. Also, consider what happens if there's no path between two variables.
4.  **Data Structures:** Be ready to explain why you chose `HashMap` for the adjacency list and `HashSet` for visited nodes, highlighting their time complexities for lookups and insertions.
5.  **Product Accumulation:** Emphasize how the product is accumulated along the path, rather than summing weights (which would be for shortest path problems with additive costs).

## Revision Checklist
- [ ] Understand the problem: Given equations `A/B = val`, evaluate queries `X/Y`.
- [ ] Graph representation: Variables as nodes, division as weighted edges.
- [ ] Bidirectional edges: Add `A->B` with `val` and `B->A` with `1/val`.
- [ ] Traversal algorithm: BFS or DFS to find paths.
- [ ] Product accumulation: Multiply edge weights along the path.
- [ ] Handling unknown variables: Return -1.0 if a variable isn't in the graph.
- [ ] Handling no path: Return -1.0 if BFS/DFS doesn't find the destination.
- [ ] Visited set: Prevent cycles and redundant computations.
- [ ] Complexity analysis: Time and Space.

## Similar Problems
*   Course Schedule (Graph, Topological Sort)
*   Find the Town Judge (Graph, Degree Analysis)
*   Redundant Connection (Graph, Union-Find)
*   Keys and Rooms (Graph, BFS/DFS)

## Tags
`Graph` `Breadth-First Search` `Depth-First Search` `Hash Map` `Array`

## My Notes
amazing
