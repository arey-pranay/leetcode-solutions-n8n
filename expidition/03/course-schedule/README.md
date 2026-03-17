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
                if(needed[i] == 0){
                    q.add(i);
                }
            }
            
        }
        return finished == numCourses;
    }
}

```

---

---
## Quick Revision
This problem asks if it's possible to complete all courses given a set of prerequisites.
We can solve this by detecting cycles in the course dependency graph using Kahn's algorithm (topological sort).

## Intuition
The core idea is that if we can find a sequence of courses to take such that all prerequisites for each course are met before taking it, then we can finish all courses. This is only impossible if there's a circular dependency (e.g., Course A requires B, and Course B requires A). A topological sort naturally handles this by processing nodes with no incoming edges (no unmet prerequisites) first. If we can process all courses this way, there's no cycle.

## Algorithm
1.  **Build the Graph and In-degrees:**
    *   Create an adjacency list `preqs` where `preqs[i]` stores a list of courses that have `i` as a prerequisite.
    *   Create an array `needed` (or `inDegree`) where `needed[i]` stores the number of prerequisites for course `i`.
    *   Iterate through the `prerequisites` array: for each `[course, prereq]`, add `course` to `preqs[prereq]` and increment `needed[course]`.
2.  **Initialize the Queue:**
    *   Create a queue `q` to store courses that have no prerequisites (in-degree of 0).
    *   Iterate through all courses from 0 to `numCourses - 1`. If `needed[i]` is 0, add course `i` to the queue.
3.  **Process Courses (Kahn's Algorithm):**
    *   Initialize a counter `finished` to 0.
    *   If the queue is initially empty and there are courses (`numCourses > 0`), it means there's a cycle, so return `false`.
    *   While the queue is not empty:
        *   Dequeue a `course`.
        *   Increment `finished`.
        *   For each `neighbor` (a course that has the current `course` as a prerequisite) in `preqs[course]`:
            *   Decrement `needed[neighbor]`.
            *   If `needed[neighbor]` becomes 0, enqueue `neighbor`.
4.  **Check for Completion:**
    *   After the loop, if `finished` is equal to `numCourses`, it means all courses could be processed, and thus there's no cycle. Return `true`.
    *   Otherwise, there was a cycle, and not all courses could be finished. Return `false`.

## Concept to Remember
*   **Directed Graphs:** The problem can be modeled as a directed graph where courses are nodes and prerequisites are directed edges (prerequisite -> course).
*   **Cycle Detection:** The core of the problem is detecting cycles in a directed graph.
*   **Topological Sort (Kahn's Algorithm):** This algorithm finds a linear ordering of vertices such that for every directed edge `u -> v`, vertex `u` comes before `v` in the ordering. It's used here to detect cycles.
*   **In-degree:** The number of incoming edges to a node, representing the number of unmet prerequisites.

## Common Mistakes
*   **Not handling the case of no prerequisites:** If `prerequisites` is empty, all courses can be finished. The code should return `true`.
*   **Incorrectly building the graph:** Confusing the direction of the edge (e.g., `prereq -> course` vs. `course -> prereq`). The adjacency list should represent "what courses can be taken *after* this one".
*   **Not initializing the queue correctly:** Failing to add all courses with an initial in-degree of 0 to the queue.
*   **Returning `false` prematurely:** If the queue becomes empty but `finished` is less than `numCourses`, it indicates a cycle, but the loop condition itself handles this. The final check `finished == numCourses` is crucial.
*   **Off-by-one errors in array indexing or loop bounds.**

## Complexity Analysis
*   **Time:** O(V + E) - where V is the number of courses (`numCourses`) and E is the number of prerequisites. We visit each course (vertex) and each prerequisite (edge) at most once.
*   **Space:** O(V + E) - for the adjacency list (`preqs`) and the in-degree array (`needed`), and the queue. In the worst case, the adjacency list can store all edges, and the queue can store all vertices.

## Commented Code
```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // If there are no prerequisites, all courses can be finished.
        if(prerequisites.length==0) return true;
        
        // Initialize an adjacency list to represent the graph.
        // preqs.get(i) will store a list of courses that have course 'i' as a prerequisite.
        ArrayList<ArrayList<Integer>> preqs = new ArrayList<>();
        // For each course, create an empty list to store its dependents.
        for(int i=0;i<numCourses;i++) preqs.add(new ArrayList<>());
        
        // Initialize an array to store the in-degree (number of prerequisites) for each course.
        int[] needed = new int[numCourses];
        
        // Build the graph and populate the in-degree array.
        for(int[] pair : prerequisites){
            // pair[0] is the course, pair[1] is its prerequisite.
            // So, pair[0] depends on pair[1].
            // Add pair[0] to the list of courses that can be taken after pair[1].
            preqs.get(pair[1]).add(pair[0]);
            // Increment the in-degree of pair[0] because it has one more prerequisite.
            needed[pair[0]]++;
        }
        
        // Initialize a queue for Kahn's algorithm (topological sort).
        // This queue will hold courses that have no unmet prerequisites (in-degree of 0).
        Queue<Integer> q = new LinkedList<>();
        // Add all courses with an initial in-degree of 0 to the queue.
        for(int i=0;i<numCourses;i++) if(needed[i]==0) q.add(i);
        
        // Counter to keep track of the number of courses that have been "finished" (processed).
        int finished = 0;
        
        // If the queue is empty initially and there are courses, it means there's a cycle.
        // For example, if numCourses > 0 but no course has 0 prerequisites.
        if(q.isEmpty()) return false;
        
        // Process courses using Kahn's algorithm.
        while(!q.isEmpty()){
            // Dequeue a course that has no unmet prerequisites.
            int course = q.remove();
            // Increment the count of finished courses.
            finished++;
            
            // For each course that has the current 'course' as a prerequisite:
            for(int i : preqs.get(course)){
                // Decrement the in-degree of this dependent course, as one of its prerequisites is now met.
                needed[i]--;
                // If the in-degree of this dependent course becomes 0, it means all its prerequisites are met.
                if(needed[i] == 0){
                    // Add it to the queue to be processed later.
                    q.add(i);
                }
            }
        }
        
        // If the number of finished courses equals the total number of courses,
        // it means we were able to process all courses without encountering a cycle.
        return finished == numCourses;
    }
}
```

## Interview Tips
*   **Explain the Graph Model:** Clearly articulate how the problem can be represented as a directed graph and why cycle detection is key.
*   **Walk Through Kahn's Algorithm:** Describe the steps of Kahn's algorithm (in-degree, queue, processing) and how it helps identify cycles.
*   **Edge Cases:** Be prepared to discuss edge cases like `numCourses = 0`, `prerequisites` being empty, or a graph with no edges.
*   **Alternative Approaches:** Briefly mention DFS-based cycle detection as an alternative, but focus on the BFS (Kahn's) approach as it's often more intuitive for this specific problem.

## Revision Checklist
- [ ] Understand the problem statement: can all courses be finished?
- [ ] Recognize the problem as cycle detection in a directed graph.
- [ ] Implement graph representation (adjacency list).
- [ ] Implement in-degree calculation.
- [ ] Implement Kahn's algorithm (BFS-based topological sort).
- [ ] Handle the initial queue population correctly.
- [ ] Correctly update in-degrees and enqueue new courses.
- [ ] Verify the final condition (`finished == numCourses`).
- [ ] Consider edge cases (no prerequisites, no courses).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Course Schedule II
*   Alien Dictionary
*   Parallel Courses
*   Sequence Reconstruction

## Tags
`Array` `Hash Map` `Breadth-First Search` `Graph` `Topological Sort`

## My Notes
Kahn's Algorithm (Topological Sort)
