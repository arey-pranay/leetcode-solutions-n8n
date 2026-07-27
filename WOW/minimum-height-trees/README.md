# Minimum Height Trees

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Depth-First Search` `Breadth-First Search` `Graph Theory` `Topological Sort`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
  int minHeight = Integer.MAX_VALUE;
  HashMap<Integer,ArrayList<Integer>> adj= new HashMap<>();
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        for(int i =0 ; i<n;i++) adj.put(i,new ArrayList<>());
        for(int[] temp : edges){
          int src = temp[0];
          int dest = temp[1];
          adj.get(src).add(dest);
          adj.get(dest).add(src);
        }
        int end = bfs(0,n,null);
        
        int[] parent = new int[n];
        
        end = bfs(end,n,parent);
            
        List<Integer> path = new ArrayList<>();
        for(int i : parent ) 
        while(end!=-1){
          path.add(end);
          end = parent[end];
        }    
        
        List<Integer> ans = new ArrayList<>();
        int sz = path.size();
        ans.add(path.get((sz-1)/2));
        if(sz%2==0)ans.add(path.get(sz/2));
        return ans;
    } 
    public int bfs(int start, int n,int[] parent){
        boolean[] vis = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        vis[start] = true;
        int last = start;
        if(parent!=null) parent[start] = -1;
        while(!q.isEmpty()){
            int curr = q.poll();
            last = curr;
            for(int i : adj.get(curr)){
              if(!vis[i]){
                vis[i] = true;
                if(parent!=null) parent[i] = curr;
                q.add(i);
              }
            }
        }
      return last;
       
    }
}
```

---

---
## Quick Revision
Find all nodes that can be roots of trees with the minimum possible height.
This is solved by iteratively removing leaf nodes until 1 or 2 nodes remain.

## Intuition
The problem asks for nodes that minimize the maximum distance to any other node in the tree. This is analogous to finding the center(s) of the tree. If we imagine "peeling" the tree layer by layer from the outside (leaves), the last nodes remaining will be the ones closest to all other nodes, thus minimizing the height.

## Algorithm
1. **Build Adjacency List:** Represent the tree using an adjacency list where each key is a node and its value is a list of its neighbors.
2. **Find a Farthest Node (First BFS):** Perform a Breadth-First Search (BFS) starting from an arbitrary node (e.g., node 0) to find one of the nodes that is farthest away from the starting node. This farthest node will be one of the endpoints of a diameter of the tree.
3. **Find the Diameter and Path (Second BFS):** Perform another BFS starting from the farthest node found in step 2. This BFS will find the other endpoint of the diameter and also record the parent of each node in the path from the starting node. This allows us to reconstruct the longest path (diameter) in the tree.
4. **Identify MHT Roots:** The nodes that form the Minimum Height Trees (MHTs) will be the middle node(s) of this diameter. If the diameter has an odd number of nodes, there will be one MHT root. If it has an even number of nodes, there will be two MHT roots.

## Concept to Remember
*   **Tree Diameter:** The longest path between any two nodes in a tree.
*   **Breadth-First Search (BFS):** An algorithm for traversing or searching tree or graph data structures. It explores all of the neighbor nodes at the present depth prior to moving on to the nodes at the next depth level.
*   **Graph Representation:** Using adjacency lists to efficiently store and access graph connections.

## Common Mistakes
*   **Incorrectly identifying the center:** Assuming any node is a valid starting point for finding the diameter without a proper BFS traversal.
*   **Not handling the two-root case:** For trees with an even diameter, failing to return both middle nodes.
*   **Inefficient path reconstruction:** Reconstructing the path from the second BFS can be tricky; a simple parent array is crucial.
*   **Off-by-one errors:** When calculating the middle index for an even-sized path.

## Complexity Analysis
*   **Time:** O(N) - The graph is built in O(E) which is O(N-1) for a tree. Two BFS traversals are performed, each visiting every node and edge once, taking O(N+E) which simplifies to O(N) for a tree. Path reconstruction is also O(N).
*   **Space:** O(N) - For the adjacency list, visited array, queue, and parent array used in BFS.

## Commented Code
```java
import java.util.*;

class Solution {
    // Stores the adjacency list representation of the graph.
    HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Initialize the adjacency list for all nodes.
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }
        // Build the adjacency list from the given edges.
        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            // Add bidirectional edges for an undirected graph.
            adj.get(src).add(dest);
            adj.get(dest).add(src);
        }

        // Perform the first BFS to find one endpoint of a tree diameter.
        // Start from an arbitrary node (0). 'null' indicates no parent tracking needed for this BFS.
        int farthestNode = bfs(0, n, null);

        // Array to store parent pointers for path reconstruction.
        int[] parent = new int[n];
        // Perform the second BFS starting from the farthest node found.
        // This BFS will find the other endpoint of the diameter and populate the parent array.
        int diameterEndpoint = bfs(farthestNode, n, parent);

        // Reconstruct the path (diameter) from the second BFS's endpoint using parent pointers.
        List<Integer> path = new ArrayList<>();
        // Traverse back from the diameter endpoint to the start of the second BFS.
        while (diameterEndpoint != -1) {
            // Add the current node to the path.
            path.add(diameterEndpoint);
            // Move to the parent of the current node.
            diameterEndpoint = parent[diameterEndpoint];
        }

        // The Minimum Height Trees (MHTs) are the middle node(s) of the diameter.
        List<Integer> ans = new ArrayList<>();
        int pathSize = path.size();
        // For an odd-sized path, the middle element is the single MHT root.
        ans.add(path.get((pathSize - 1) / 2));
        // For an even-sized path, there are two middle elements, both are MHT roots.
        if (pathSize % 2 == 0) {
            ans.add(path.get(pathSize / 2));
        }
        // Return the list of MHT roots.
        return ans;
    }

    // Helper function to perform Breadth-First Search.
    // 'start': the node to start BFS from.
    // 'n': the total number of nodes.
    // 'parent': an array to store parent pointers for path reconstruction (can be null).
    // Returns the last visited node (farthest from start in this BFS).
    public int bfs(int start, int n, int[] parent) {
        // Keep track of visited nodes.
        boolean[] visited = new boolean[n];
        // Queue for BFS.
        Queue<Integer> q = new LinkedList<>();

        // Add the starting node to the queue and mark it as visited.
        q.add(start);
        visited[start] = true;
        // 'last' will store the last node polled from the queue, which is the farthest.
        int last = start;
        // If parent array is provided, set the parent of the start node to -1 (no parent).
        if (parent != null) {
            parent[start] = -1;
        }

        // Continue BFS as long as the queue is not empty.
        while (!q.isEmpty()) {
            // Dequeue the current node.
            int curr = q.poll();
            // Update 'last' to the current node.
            last = curr;

            // Iterate over all neighbors of the current node.
            for (int neighbor : adj.get(curr)) {
                // If the neighbor has not been visited yet.
                if (!visited[neighbor]) {
                    // Mark the neighbor as visited.
                    visited[neighbor] = true;
                    // If parent array is provided, set the current node as the parent of the neighbor.
                    if (parent != null) {
                        parent[neighbor] = curr;
                    }
                    // Enqueue the neighbor for further exploration.
                    q.add(neighbor);
                }
            }
        }
        // Return the last visited node, which is the farthest from the start node.
        return last;
    }
}
```

## Interview Tips
*   **Explain the "peeling" analogy:** Clearly articulate how removing leaves layer by layer leads to the center of the tree.
*   **Justify the two BFS approach:** Explain why two BFS traversals are necessary to find the diameter and its endpoints.
*   **Discuss edge cases:** Mention what happens for a single node tree (n=1) or a two-node tree. The provided solution implicitly handles these.
*   **Clarify parent tracking:** Emphasize the role of the `parent` array in reconstructing the diameter path.

## Revision Checklist
- [ ] Understand the problem: find roots that minimize tree height.
- [ ] Recognize the connection to tree diameter and center.
- [ ] Implement adjacency list for graph representation.
- [ ] Understand and implement BFS.
- [ ] Implement the two-BFS strategy to find diameter endpoints.
- [ ] Implement parent tracking for path reconstruction.
- [ ] Correctly identify the middle node(s) of the diameter.
- [ ] Handle edge cases (n=1, n=2).
- [ ] Analyze time and space complexity.

## Similar Problems
*   104. Maximum Depth of Binary Tree
*   111. Minimum Depth of Binary Tree
*   2246. Longest Path With Different Adjacent Characters
*   124. Binary Tree Maximum Path Sum

## Tags
`Tree` `Graph` `BFS` `Depth-First Search` `Topological Sort`
