# Remove Methods From Project

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Depth-First Search` `Breadth-First Search` `Graph Theory`  
**Time:** O(n + m)  
**Space:** O(n + m)

---

## Solution (java)

```java
class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        int[] indeg = new int[n];
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int arr[]:invocations){indeg[arr[1]]++; adj.get(arr[0]).add(arr[1]);}
        HashSet<Integer> sus = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(k);
        sus.add(k);
        while(!q.isEmpty()){
            int curr = q.remove();
            for(int neigh : adj.get(curr)){indeg[neigh]--; if(!sus.contains(neigh)){q.add(neigh); sus.add(neigh);}}
        }
        boolean canRemoveAll = true;
        List<Integer> rem = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(sus.contains(i) && indeg[i]>0){canRemoveAll = false; break;} else if(!sus.contains(i)) rem.add(i);
        }
        if(!canRemoveAll){
            List<Integer>ans = new ArrayList<>();
            for(int i=0;i<n;i++)ans.add(i);
            return ans;
        }
        return rem;

    }
}
```

---

---
## Quick Revision
The problem is to determine which methods can be removed from a project given their invocation relationships and a target method. The solution involves finding strongly connected components using topological sorting.

## Intuition
The intuition behind this approach is that if all methods in a strongly connected component are invoked, then the component itself can be removed as a whole. We use topological sorting to find these components.

## Algorithm
1. Initialize an array `indeg` to keep track of the indegree of each method (i.e., how many times it's invoked).
2. Create an adjacency list `adj` to represent the invocation relationships.
3. Iterate over the invocations and update the indegree array and the adjacency list accordingly.
4. Perform a BFS traversal starting from the target method `k`, marking all visited methods in a set `sus`.
5. If any unvisited method has an out-degree greater than 0, it means there's a cycle, and we cannot remove all methods. Otherwise, we continue to find strongly connected components.
6. Finally, return the list of methods that can be removed.

## Concept to Remember
* **Topological sorting**: An ordering of vertices in a directed acyclic graph (DAG) such that for every edge (u,v), vertex u comes before v in the ordering.
* **Strongly connected component**: A subgraph that is strongly connected, meaning there is a path from every vertex to every other vertex.

## Common Mistakes
* Failing to handle cycles correctly, which would prevent all methods from being removed.
* Not initializing the `indeg` array properly before updating it.
* Misunderstanding the concept of topological sorting and its application in this problem.

## Complexity Analysis
- Time: O(n + m) - reason: We perform two passes over the graph: one to update the indegree array and adjacency list, and another for BFS traversal.
- Space: O(n + m) - reason: We need to store the adjacency list and the set of visited methods.

## Commented Code
```java
class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Initialize indegree array
        int[] indeg = new int[n];
        
        // Create adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        // Update indegree array and adjacency list
        for (int[] arr : invocations) {
            indeg[arr[1]]++;
            adj.get(arr[0]).add(arr[1]);
        }

        // Set to keep track of visited methods
        HashSet<Integer> sus = new HashSet<>();

        // Queue for BFS traversal
        Queue<Integer> q = new LinkedList<>();
        q.offer(k);
        sus.add(k);

        // Perform BFS traversal
        while (!q.isEmpty()) {
            int curr = q.remove();
            for (int neigh : adj.get(curr)) {
                indeg[neigh]--;
                if (!sus.contains(neigh)) {
                    q.add(neigh);
                    sus.add(neigh);
                }
            }
        }

        // Check if all methods can be removed
        boolean canRemoveAll = true;
        List<Integer> rem = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (sus.contains(i) && indeg[i] > 0) {
                canRemoveAll = false;
                break;
            } else if (!sus.contains(i)) {
                rem.add(i);
            }
        }

        // Return list of methods that can be removed
        if (!canRemoveAll) {
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                ans.add(i);
            }
            return ans;
        }
        return rem;
    }
}
```

## Interview Tips

* Make sure to understand the problem statement carefully, especially when dealing with graph-related problems.
* Familiarize yourself with common graph algorithms like topological sorting and BFS traversal.
* Practice implementing these algorithms from scratch, as it's essential for coding interviews.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Review common graph algorithms and data structures.
- [ ] Implement a topological sorting algorithm from scratch.
- [ ] Test the implementation on example inputs.

## Similar Problems

* LeetCode 210. Course Schedule II (find all possible schedules)
* LeetCode 269. Alien Dictionary (build an ordering of words based on their relationships)

## Tags
`Graph`, `Topological Sorting`, `BFS Traversal`, `Strongly Connected Component`
