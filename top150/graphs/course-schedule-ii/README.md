# Course Schedule Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Depth-First Search` `Breadth-First Search` `Graph Theory` `Topological Sort`  
**Time:** O(V + E)  
**Space:** O(V + E)

---

## Solution (java)

```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        int pointer = 0;
        if(prerequisites.length==0){
            for(int i=0;i<numCourses;i++) ans[i] = i;
            return ans;
        }
        
        ArrayList<ArrayList<Integer>> preqs = new ArrayList<>();
        for(int i=0;i<numCourses;i++) preqs.add(new ArrayList<>());
        int[] needed = new int[numCourses];
        for(int[] pair : prerequisites){
            needed[pair[0]]++;
            preqs.get(pair[1]).add(pair[0]);
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<numCourses;i++) if(needed[i]==0){
            q.add(i);
            ans[pointer++] = i;
        }
        int finished = 0;
        if(q.isEmpty()) return new int[]{};
        
        while(!q.isEmpty()){
            int course = q.remove();
            finished++;
            for(int i : preqs.get(course)){
                needed[i]--;
                if(needed[i] == 0){
                    q.add(i);
                    ans[pointer++] = i;
                }
            }
        }
        return pointer == numCourses ? ans : new int[]{};
    }
}
```

---

---
## Quick Revision
This problem asks for a valid order to take courses given prerequisites.
We solve it by finding a topological sort of the course dependency graph.

## Intuition
The core idea is that we can only take a course if all its prerequisites are met. This naturally leads to a process where we start with courses that have no prerequisites, then take courses whose prerequisites are now met, and so on. This is exactly what a topological sort does: it orders nodes in a directed acyclic graph (DAG) such that for every directed edge from node A to node B, node A comes before node B in the ordering. If there's a cycle, a topological sort is impossible.

## Algorithm
1.  **Represent the graph:** Create an adjacency list where `adj[i]` stores a list of courses that have course `i` as a prerequisite. Also, create an `inDegree` array where `inDegree[i]` stores the number of prerequisites for course `i`.
2.  **Build the graph and in-degrees:** Iterate through the `prerequisites` array. For each `[course, prerequisite]` pair, add `course` to `adj[prerequisite]` and increment `inDegree[course]`.
3.  **Initialize the queue:** Create a queue and add all courses with an `inDegree` of 0 (i.e., courses with no prerequisites) to it. These are the starting points.
4.  **Perform Topological Sort (Kahn's Algorithm):**
    *   Initialize an empty list or array `result` to store the topological order.
    *   While the queue is not empty:
        *   Dequeue a course `u`.
        *   Add `u` to the `result`.
        *   For each neighbor `v` of `u` (i.e., courses that have `u` as a prerequisite):
            *   Decrement `inDegree[v]`.
            *   If `inDegree[v]` becomes 0, enqueue `v`.
5.  **Check for cycles:** If the number of courses in the `result` list is equal to `numCourses`, then a valid topological order was found. Return the `result`. Otherwise, a cycle exists, and it's impossible to finish all courses. Return an empty array.

## Concept to Remember
*   **Topological Sort:** An ordering of vertices in a directed acyclic graph (DAG) such that for every directed edge from vertex `u` to vertex `v`, `u` comes before `v` in the ordering.
*   **Directed Acyclic Graph (DAG):** A directed graph that contains no directed cycles.
*   **Kahn's Algorithm:** A common algorithm for topological sorting that uses a queue and in-degrees.
*   **Cycle Detection:** In a directed graph, a cycle prevents a valid topological sort.

## Common Mistakes
*   **Not handling the case of no prerequisites:** If `prerequisites` is empty, all courses can be taken in any order. The code should handle this by returning `[0, 1, ..., numCourses-1]`.
*   **Incorrectly building the adjacency list or in-degree array:** Swapping the course and prerequisite in the `prerequisites` array can lead to an incorrect graph representation.
*   **Not checking for cycles:** If the graph has a cycle, a topological sort is impossible. The algorithm must detect this and return an empty array.
*   **Off-by-one errors in array indexing or loop bounds.**
*   **Forgetting to initialize the queue with all starting nodes (in-degree 0).**

## Complexity Analysis
- Time: O(V + E) - We visit each vertex (course) and each edge (prerequisite) at most once. V is `numCourses` and E is the number of prerequisites.
- Space: O(V + E) - For the adjacency list, in-degree array, queue, and the result array.

## Commented Code
```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Initialize the result array to store the topological order.
        int[] ans = new int[numCourses];
        // Pointer to keep track of the next available slot in the 'ans' array.
        int pointer = 0;

        // Handle the edge case where there are no prerequisites.
        // In this case, any order is valid, so we return courses in ascending order.
        if(prerequisites.length == 0){
            for(int i = 0; i < numCourses; i++) ans[i] = i;
            return ans;
        }

        // Adjacency list to represent the graph.
        // preqs.get(i) will store a list of courses that have course 'i' as a prerequisite.
        ArrayList<ArrayList<Integer>> preqs = new ArrayList<>();
        // Initialize the adjacency list with empty lists for each course.
        for(int i = 0; i < numCourses; i++) preqs.add(new ArrayList<>());

        // Array to store the in-degree of each course.
        // needed[i] will store the number of prerequisites for course 'i'.
        int[] needed = new int[numCourses];

        // Build the graph and calculate in-degrees.
        for(int[] pair : prerequisites){
            // pair[0] is the course, pair[1] is its prerequisite.
            // Increment the in-degree of the course that has a prerequisite.
            needed[pair[0]]++;
            // Add the course to the adjacency list of its prerequisite.
            // This means 'pair[0]' depends on 'pair[1]'.
            preqs.get(pair[1]).add(pair[0]);
        }

        // Queue to store courses that have no prerequisites (in-degree is 0).
        Queue<Integer> q = new LinkedList<>();
        // Initialize the queue with all courses that have an in-degree of 0.
        for(int i = 0; i < numCourses; i++){
            if(needed[i] == 0){
                q.add(i);
                // Add these initial courses to our result array.
                ans[pointer++] = i;
            }
        }

        // Counter to track the number of courses successfully finished (added to the topological order).
        int finished = 0;
        // If the queue is empty initially, it means there are no courses with 0 in-degree,
        // which implies a cycle or an impossible scenario if numCourses > 0.
        if(q.isEmpty()) return new int[]{};

        // Process the queue to perform topological sort.
        while(!q.isEmpty()){
            // Dequeue a course that has no pending prerequisites.
            int course = q.remove();
            // Increment the count of finished courses.
            finished++;

            // Iterate through all courses that have 'course' as a prerequisite.
            for(int nextCourse : preqs.get(course)){
                // Decrement the in-degree of the next course because one of its prerequisites ('course') is now met.
                needed[nextCourse]--;
                // If the in-degree of 'nextCourse' becomes 0, it means all its prerequisites are met.
                if(needed[nextCourse] == 0){
                    // Add 'nextCourse' to the queue to be processed.
                    q.add(nextCourse);
                    // Add 'nextCourse' to our result array.
                    ans[pointer++] = nextCourse;
                }
            }
        }

        // If the number of finished courses equals the total number of courses,
        // then a valid topological order was found. Return the 'ans' array.
        // Otherwise, a cycle was detected, and it's impossible to finish all courses. Return an empty array.
        return pointer == numCourses ? ans : new int[]{};
    }
}
```

## Interview Tips
*   **Explain the graph analogy:** Clearly articulate how the problem can be modeled as a directed graph and why topological sort is the appropriate algorithm.
*   **Walk through Kahn's algorithm:** Describe the steps of Kahn's algorithm (using in-degrees and a queue) and how it handles dependencies.
*   **Discuss cycle detection:** Emphasize how the algorithm implicitly detects cycles by checking if all courses can be included in the topological sort.
*   **Handle edge cases:** Be prepared to discuss the case of no prerequisites and the case where a cycle makes it impossible to complete all courses.

## Revision Checklist
- [ ] Understand the problem: find a valid course order given prerequisites.
- [ ] Recognize it as a topological sort problem.
- [ ] Implement graph representation (adjacency list and in-degrees).
- [ ] Implement Kahn's algorithm using a queue.
- [ ] Handle the case of no prerequisites.
- [ ] Implement cycle detection logic.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Course Schedule
*   Alien Dictionary
*   Parallel Courses
*   Sequence Reconstruction

## Tags
`Array` `Hash Map` `Graph` `Topological Sort` `Breadth-First Search`
