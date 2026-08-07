# Course Schedule Ii

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Array` `Hash Map` `Graph` `Topological Sort` `Breadth-First Search`  
**Time:** O(V + E)  
**Space:** O(V + E)

---

## Solution (java)

```java
class Solution {
    public int[] findOrder(int numCourses, int[][] preqs) {
        //topological sort
        int[] indegree = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<numCourses;i++) adj.add(new ArrayList<>());
        for(int[] pre : preqs){
          int a = pre[0];
          int b = pre[1];
          indegree[a]++; 
          adj.get(b).add(a);
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<numCourses;i++)if(indegree[i]==0) q.add(i);
        int[] ans = new int[numCourses];
        Arrays.fill(ans,-1);
        int index=0;
        boolean[] vis = new boolean[numCourses];
        while(!q.isEmpty()){
          int curr = q.poll();
          vis[curr] = true;
          ans[index++] = curr;
          for(int neigh : adj.get(curr)){
            indegree[neigh]--;
            if(!vis[neigh] && indegree[neigh]==0){q.add(neigh); vis[neigh]=true;}
          }
        }
        return index != numCourses ? new int[]{} : ans;
    }
}

```

---

---
## Quick Revision
This problem asks for a valid ordering of courses given prerequisites.
We solve it using Kahn's algorithm for topological sorting.

## Intuition
The core idea is that we can only take a course if all its prerequisites have been taken. This naturally leads to a directed graph where courses are nodes and prerequisites are edges (e.g., if course A is a prerequisite for course B, there's an edge from A to B). We need to find a linear ordering of these nodes such that for every directed edge from node U to node V, U comes before V in the ordering. This is precisely what topological sorting does.

Kahn's algorithm specifically works by identifying nodes with no incoming edges (courses with no prerequisites). These can be taken first. Once we take such a course, we can effectively "remove" it and its outgoing edges from the graph. This might make other courses now have no remaining prerequisites, allowing us to add them to our queue of courses that can be taken. We repeat this process until all courses are ordered or we detect a cycle (meaning no valid order exists).

## Algorithm
1.  **Represent the graph:** Create an adjacency list `adj` where `adj.get(i)` stores a list of courses that have `i` as a prerequisite. Also, create an `indegree` array where `indegree[i]` stores the number of prerequisites for course `i`.
2.  **Build the graph and indegrees:** Iterate through the `preqs` array. For each prerequisite `[course, prereq]`, add `course` to `adj.get(prereq)` and increment `indegree[course]`.
3.  **Initialize the queue:** Create a queue `q` and add all courses with an `indegree` of 0 to it. These are the courses that can be taken initially.
4.  **Process the queue:**
    *   Initialize an array `ans` to store the topological order and an `index` variable to track the current position in `ans`.
    *   While the queue is not empty:
        *   Dequeue a course `curr`.
        *   Add `curr` to the `ans` array at `index` and increment `index`.
        *   For each neighbor `neigh` of `curr` (i.e., courses that have `curr` as a prerequisite):
            *   Decrement `indegree[neigh]`.
            *   If `indegree[neigh]` becomes 0, it means all its prerequisites are now met, so enqueue `neigh`.
5.  **Check for cycles:** If `index` is not equal to `numCourses` after the loop, it means there was a cycle in the graph, and a valid topological order cannot be formed. In this case, return an empty array.
6.  **Return the order:** Otherwise, return the `ans` array.

## Concept to Remember
*   **Topological Sorting:** A linear ordering of vertices in a directed acyclic graph (DAG) such that for every directed edge from vertex `u` to vertex `v`, `u` comes before `v` in the ordering.
*   **Directed Acyclic Graph (DAG):** A directed graph that contains no directed cycles. This problem's constraints imply that if a solution exists, the prerequisite graph must be a DAG.
*   **Kahn's Algorithm:** An algorithm for topological sorting that uses a queue and indegrees to process nodes.
*   **Graph Representation:** Adjacency lists are efficient for representing sparse graphs, which is common in such problems.

## Common Mistakes
*   **Incorrectly building the graph:** Confusing the direction of the prerequisite edge (e.g., `[a, b]` means `b` is a prerequisite for `a`, so edge `b -> a`).
*   **Not handling cycles:** Failing to detect if a topological sort is impossible due to a cycle, leading to an incomplete or incorrect result.
*   **Modifying indegrees incorrectly:** Not decrementing the indegree of neighbors when a course is processed.
*   **Adding visited nodes to the queue again:** Ensuring that a node is only added to the queue if its indegree becomes zero *and* it hasn't been fully processed yet (though Kahn's algorithm naturally handles this by only adding when indegree hits zero). The provided solution uses a `vis` array which is slightly redundant if `indegree` is managed correctly, but it ensures a node isn't re-added if it was already processed and its indegree was reduced by multiple paths.

## Complexity Analysis
- Time: O(V + E) - We visit each vertex (course) and each edge (prerequisite) at most once. Building the graph takes O(E), initializing the queue takes O(V), and processing the queue involves visiting each vertex and edge once.
- Space: O(V + E) - For the adjacency list (O(V+E)), the indegree array (O(V)), the queue (O(V) in the worst case), and the result array (O(V)).

## Commented Code
```java
class Solution {
    public int[] findOrder(int numCourses, int[][] preqs) {
        // Initialize an array to store the in-degree of each course.
        // in-degree[i] will be the number of prerequisites for course i.
        int[] indegree = new int[numCourses];
        
        // Initialize an adjacency list to represent the course dependencies.
        // adj.get(i) will store a list of courses that have course i as a prerequisite.
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            // For each course, create an empty list to store its dependent courses.
            adj.add(new ArrayList<>());
        }
        
        // Populate the adjacency list and in-degree array based on the prerequisites.
        for(int[] pre : preqs){
          // 'a' is the course, 'b' is its prerequisite.
          // This means there's a directed edge from 'b' to 'a' (b -> a).
          int course = pre[0]; // The course that has a prerequisite.
          int prereq = pre[1]; // The prerequisite course.
          
          // Increment the in-degree of the course that has a prerequisite.
          indegree[course]++; 
          
          // Add the course to the adjacency list of its prerequisite.
          // This means 'course' depends on 'prereq'.
          adj.get(prereq).add(course);
        }
        
        // Initialize a queue for Kahn's algorithm (topological sort).
        // This queue will hold courses that have no pending prerequisites.
        Queue<Integer> q = new LinkedList<>();
        
        // Add all courses with an in-degree of 0 to the queue.
        // These are the courses that can be taken first.
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                q.add(i);
            }
        }
        
        // Initialize an array to store the resulting topological order.
        int[] ans = new int[numCourses];
        // Initialize it with -1 or any value indicating it's not yet filled.
        // This is useful for debugging or if we need to check if all courses were placed.
        Arrays.fill(ans, -1); 
        
        // 'index' tracks the current position in the 'ans' array where the next course will be placed.
        int index = 0;
        
        // A boolean array to keep track of visited nodes. This helps prevent re-adding nodes to the queue
        // if their indegree becomes 0 through multiple paths, though the indegree check is primary.
        boolean[] vis = new boolean[numCourses]; 
        
        // Process courses from the queue.
        while(!q.isEmpty()){
          // Dequeue a course that has no pending prerequisites.
          int curr = q.poll();
          
          // Mark the current course as visited (or processed).
          vis[curr] = true; 
          
          // Add the current course to the topological order.
          ans[index++] = curr;
          
          // Iterate through all neighbors of the current course.
          // Neighbors are courses that have 'curr' as a prerequisite.
          for(int neigh : adj.get(curr)){
            // Decrement the in-degree of the neighbor because 'curr' has now been taken.
            indegree[neigh]--;
            
            // If the neighbor's in-degree becomes 0 and it hasn't been visited/processed yet,
            // it means all its prerequisites are now met. So, add it to the queue.
            if(!vis[neigh] && indegree[neigh] == 0){
                q.add(neigh);
                // Mark the neighbor as visited immediately upon adding to queue to avoid duplicates.
                vis[neigh] = true; 
            }
          }
        }
        
        // If the number of courses added to the 'ans' array is not equal to the total number of courses,
        // it means there was a cycle in the graph, and a valid topological order cannot be formed.
        // In this case, return an empty array.
        return index != numCourses ? new int[]{} : ans;
    }
}
```

## Interview Tips
*   **Explain the graph analogy:** Clearly articulate how the problem can be modeled as a directed graph and why topological sort is the appropriate solution.
*   **Walk through Kahn's algorithm:** Describe the steps of Kahn's algorithm (using indegrees and a queue) and how it handles dependencies.
*   **Discuss cycle detection:** Emphasize how the final check (`index != numCourses`) is crucial for detecting cycles and returning an empty array when no valid order exists.
*   **Consider edge cases:** Mention what happens with `numCourses = 0`, `preqs` being empty, or a graph with no dependencies.

## Revision Checklist
- [ ] Understand the problem: find a valid course order given prerequisites.
- [ ] Recognize it as a topological sort problem.
- [ ] Implement graph representation (adjacency list and indegrees).
- [ ] Implement Kahn's algorithm (queue, indegree updates).
- [ ] Handle cycle detection correctly.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Course Schedule (LeetCode 207)
*   Alien Dictionary (LeetCode 269)
*   Parallel Courses (LeetCode 1136)
*   Sequence Reconstruction (LeetCode 444)

## Tags
`Array` `Hash Map` `Graph` `Topological Sort` `Breadth-First Search`
