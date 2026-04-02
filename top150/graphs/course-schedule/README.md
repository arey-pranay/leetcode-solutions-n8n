# Course Schedule

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Depth-First Search` `Breadth-First Search` `Graph Theory` `Topological Sort`  
**Time:** O(V + E)  
**Space:** O(V + E)

---

## Solution (java)

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length==0) return true;
        
        ArrayList<ArrayList<Integer>> preqs = new ArrayList<>();
        for(int i=0;i<numCourses;i++) preqs.add(new ArrayList<>());
        int[] needed = new int[numCourses];
        for(int[] pair : prerequisites){
            needed[pair[0]]++;
            preqs.get(pair[1]).add(pair[0]);
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<numCourses;i++) if(needed[i]==0) q.add(i);
        int finished = 0;
        if(q.isEmpty()) return false;
        
        while(!q.isEmpty()){
            int course = q.remove();
            finished++;
            for(int i : preqs.get(course)){
                needed[i]--;
                if(needed[i] == 0) q.add(i);
            }
        }
        return finished == numCourses;
    }
}

```

---

---
## Quick Revision
This problem asks if it's possible to complete all courses given their prerequisites.
It can be solved by detecting cycles in a directed graph representing course dependencies.

## Intuition
The core idea is that if we can find courses with no prerequisites, we can "take" them. Once taken, they might satisfy prerequisites for other courses. We can repeat this process. If, at any point, we can't find any more courses to take but haven't finished all of them, it means there's a circular dependency, and we can't finish all courses. This process naturally leads to a topological sort.

## Algorithm
1.  **Represent the courses and prerequisites as a directed graph:**
    *   Each course is a node.
    *   A prerequisite `[a, b]` means course `a` depends on course `b`. This translates to a directed edge from `b` to `a` (meaning `b` must be taken before `a`).
2.  **Calculate in-degrees:** For each course, count how many prerequisites it has (its "in-degree").
3.  **Initialize a queue:** Add all courses with an in-degree of 0 to a queue. These are courses that can be taken immediately.
4.  **Process the queue (Kahn's Algorithm for Topological Sort):**
    *   While the queue is not empty:
        *   Dequeue a course. This course is now considered "finished". Increment a counter for finished courses.
        *   For each neighbor (course that has the dequeued course as a prerequisite):
            *   Decrement the neighbor's in-degree.
            *   If the neighbor's in-degree becomes 0, enqueue it (it can now be taken).
5.  **Check for completion:** If the total number of finished courses equals the total number of courses (`numCourses`), then it's possible to finish all courses. Otherwise, a cycle exists, and it's not possible.

## Concept to Remember
*   **Directed Graphs:** Representing relationships where direction matters (e.g., dependencies).
*   **Topological Sort:** Ordering nodes in a directed acyclic graph (DAG) such that for every directed edge `u -> v`, node `u` comes before node `v` in the ordering.
*   **Cycle Detection in Directed Graphs:** A topological sort is only possible if the graph is a DAG. If a cycle exists, a topological sort cannot be completed.
*   **Kahn's Algorithm:** An algorithm for topological sorting using a queue and in-degrees.

## Common Mistakes
*   **Incorrect Graph Representation:** Confusing the direction of the prerequisite edge (e.g., `[a, b]` means `a` depends on `b`, so edge `b -> a`).
*   **Not Handling Disconnected Components:** Ensuring all courses are considered, even those with no prerequisites and no courses depending on them.
*   **Infinite Loop on Cycles:** Not correctly identifying and breaking out of a cycle, leading to an infinite loop if not careful with the termination condition.
*   **Off-by-One Errors:** Incorrectly initializing in-degrees or the finished course count.

## Complexity Analysis
-   **Time:** O(V + E) - where V is the number of courses (vertices) and E is the number of prerequisites (edges). We visit each vertex and each edge at most once.
-   **Space:** O(V + E) - for storing the adjacency list (graph) and the in-degree array. The queue can also store up to V vertices in the worst case.

## Commented Code
```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // If there are no prerequisites, all courses can be finished.
        if(prerequisites.length==0) return true;
        
        // Adjacency list to represent the graph. preqs.get(i) will store a list of courses that have course 'i' as a prerequisite.
        ArrayList<ArrayList<Integer>> preqs = new ArrayList<>();
        // Initialize the adjacency list with empty lists for each course.
        for(int i=0;i<numCourses;i++) preqs.add(new ArrayList<>());
        
        // Array to store the in-degree of each course. needed[i] is the number of prerequisites for course 'i'.
        int[] needed = new int[numCourses];
        
        // Build the graph and calculate in-degrees.
        for(int[] pair : prerequisites){
            // pair[0] is the course that has a prerequisite.
            // pair[1] is the prerequisite course.
            // So, there's an edge from pair[1] to pair[0].
            
            // Increment the in-degree of the course that has a prerequisite.
            needed[pair[0]]++;
            // Add pair[0] to the adjacency list of pair[1], indicating pair[0] depends on pair[1].
            preqs.get(pair[1]).add(pair[0]);
        }
        
        // Queue to store courses that have no pending prerequisites (in-degree of 0).
        Queue<Integer> q = new LinkedList<>();
        // Initialize the queue with all courses that have an in-degree of 0.
        for(int i=0;i<numCourses;i++) if(needed[i]==0) q.add(i);
        
        // Counter for the number of courses that have been successfully "finished".
        int finished = 0;
        
        // If the queue is initially empty, it means no course can be taken, implying a cycle or no courses.
        // If numCourses > 0 and q is empty, it means all courses have prerequisites and none can be started.
        if(q.isEmpty()) return false;
        
        // Process the queue using Kahn's algorithm for topological sort.
        while(!q.isEmpty()){
            // Dequeue a course that can be taken.
            int course = q.remove();
            // Increment the count of finished courses.
            finished++;
            
            // Iterate through all courses that have the current 'course' as a prerequisite.
            for(int neighbor : preqs.get(course)){
                // Decrement the in-degree of the neighbor course because one of its prerequisites is now met.
                needed[neighbor]--;
                // If the neighbor's in-degree becomes 0, it means all its prerequisites are met, so add it to the queue.
                if(needed[neighbor] == 0) q.add(neighbor);
            }
        }
        
        // If the number of finished courses equals the total number of courses, then all courses can be finished.
        // Otherwise, a cycle exists, and not all courses can be finished.
        return finished == numCourses;
    }
}
```

## Interview Tips
*   **Explain the Graph Analogy:** Clearly articulate how courses and prerequisites form a directed graph and why cycle detection is key.
*   **Walk Through an Example:** Use a small example (e.g., 3 courses, 2 prerequisites) to trace the algorithm's execution, showing how the queue and in-degrees change.
*   **Discuss Edge Cases:** Mention what happens with `numCourses = 0`, `prerequisites = []`, or a graph with cycles.
*   **Mention Topological Sort:** If you know the term, use it. It shows you're familiar with standard graph algorithms.

## Revision Checklist
- [ ] Understand the problem statement: can all courses be finished?
- [ ] Recognize the problem as cycle detection in a directed graph.
- [ ] Implement graph representation (adjacency list).
- [ ] Calculate in-degrees for each node.
- [ ] Use a queue for Kahn's algorithm.
- [ ] Correctly update in-degrees and enqueue nodes.
- [ ] Verify the final count of finished courses against `numCourses`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Course Schedule II
*   Alien Dictionary
*   Parallel Courses
*   Find Eventual Safe States

## Tags
`Array` `Hash Map` `Depth First Search` `Breadth First Search` `Graph` `Topological Sort` ` Kahn's Algorithm`

## My Notes
topological sorting
