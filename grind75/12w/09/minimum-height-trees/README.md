# Minimum Height Trees

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Depth-First Search` `Breadth-First Search` `Graph Theory` `Topological Sort`  
**Time:** O(n + m)  
**Space:** O(n)

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
The problem is to find the minimum height trees in a graph, where each node has a degree (number of edges connected to it). The solution uses a breadth-first search (BFS) approach to traverse the graph and identify the roots of the minimum height trees.

## Intuition
The key insight here is that the minimum height tree must have at least two nodes with the maximum degree, as these nodes will be the roots of the tree. We can use a BFS traversal to find these nodes and then use another BFS to construct the tree from these roots.

## Algorithm
1. Create an adjacency list representation of the graph using a HashMap.
2. Perform a BFS traversal starting from each node with maximum degree (n-1) to identify the root(s) of the minimum height trees.
3. Construct the tree by performing another BFS traversal starting from the identified root(s).
4. Return the nodes at the center of the constructed tree, which will be the roots of the minimum height trees.

## Concept to Remember
* Breadth-first search (BFS) traversal algorithm
* Graph theory concepts: adjacency list representation, degree of a node

## Common Mistakes
* Not correctly identifying the maximum degree nodes.
* Not performing the BFS traversals correctly.
* Not returning the correct nodes at the center of the tree.

## Complexity Analysis
- Time: O(n + m), where n is the number of nodes and m is the number of edges, as we perform two BFS traversals.
- Space: O(n), for storing the adjacency list representation of the graph.

## Commented Code
```java
class Solution {
    int minHeight = Integer.MAX_VALUE;
    HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Create an empty adjacency list for each node
        for (int i = 0; i < n; i++) {
            adj.put(i, new ArrayList<>());
        }

        // Add edges to the adjacency list representation of the graph
        for (int[] temp : edges) {
            int src = temp[0];
            int dest = temp[1];
            adj.get(src).add(dest);
            adj.get(dest).add(src);
        }

        // Perform BFS traversal to find the root(s) of the minimum height trees
        int end = bfs(0, n, null);

        // Store the parent nodes for each node in the tree
        int[] parent = new int[n];

        // Perform another BFS traversal to construct the tree from the identified root(s)
        end = bfs(end, n, parent);

        // Find the center of the constructed tree (root(s) of minimum height trees)
        List<Integer> path = new ArrayList<>();
        for (int i : parent) {
            while (end != -1) {
                path.add(end);
                end = parent[end];
            }
        }

        // Return the nodes at the center of the constructed tree
        List<Integer> ans = new ArrayList<>();
        int sz = path.size();
        ans.add(path.get((sz - 1) / 2));
        if (sz % 2 == 0) {
            ans.add(path.get(sz / 2));
        }
        return ans;
    }

    // Helper function to perform BFS traversal
    public int bfs(int start, int n, int[] parent) {
        boolean[] vis = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        vis[start] = true;
        if (parent != null) {
            parent[start] = -1;
        }
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int i : adj.get(curr)) {
                if (!vis[i]) {
                    vis[i] = true;
                    if (parent != null) {
                        parent[i] = curr;
                    }
                    q.add(i);
                }
            }
        }
        return last;
    }
}
```

## Interview Tips
* Make sure to understand the problem statement and requirements clearly.
* Use a systematic approach to solve the problem, such as BFS traversal.
* Pay attention to the constraints of the problem (e.g., number of nodes, edges).
* Practice solving similar problems to improve your skills.

## Revision Checklist
- [ ] Understand the problem statement and requirements.
- [ ] Review graph theory concepts and algorithms (BFS, DFS).
- [ ] Practice solving similar problems.
- [ ] Review time and space complexity analysis.
- [ ] Test code with sample inputs.

## Similar Problems
* LeetCode: 1665. Minimum Number of Swaps to Make the Binary String Beautiful
* LeetCode: 1128. Number of Equivalent Domino Pairs

## Tags
`Graph Theory`, `Breadth-First Search (BFS)`, `Minimum Height Trees`
