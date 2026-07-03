# Course Schedule

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Depth-First Search` `Breadth-First Search` `Graph Theory` `Topological Sort`  
**Time:** O(numCourses + edges)  
**Space:** O(numCourses + edges)

---

## Solution (java)

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length==0) return true;
        int[] needed = new int[numCourses];
        List<List<Integer>> preqs = new ArrayList<>();
        for(int i=0;i<numCourses;i++) preqs.add(new ArrayList<>());
        for(int[] pair : prerequisites){
            needed[pair[0]]++;
            preqs.get(pair[1]).add(pair[0]);
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<numCourses;i++) if(needed[i] == 0) q.add(i);
        int completed = 0;
        
        while(!q.isEmpty()){
            int curr = q.poll();
            completed++;
            for(int neigh : preqs.get(curr)){
                needed[neigh]--;
                if(needed[neigh]==0) q.add(neigh);
            }
        }
        return completed == numCourses;
    }
}
```

---

---

## Quick Revision
The problem is to determine whether a set of courses can be finished given their prerequisites. We solve it by using graph traversal and checking if all courses have been completed.

## Intuition
The key insight here is that we can represent the courses and their prerequisites as a directed graph, where each course is a node, and the prerequisites are directed edges from one node to another. If there's a cycle in this graph, it means there's a prerequisite chain that leads back to itself, implying that the course cannot be finished.

## Algorithm
1. Create an adjacency list representation of the graph using `preqs`.
2. Initialize a counter `needed` for each course to keep track of its prerequisites.
3. Add all courses with no prerequisites (i.e., `needed[i] == 0`) to a queue `q`.
4. While the queue is not empty, pop a course from the queue and increment the count of completed courses.
5. For each neighbor of the popped course, decrement the corresponding `needed` value.
6. If a neighbor's `needed` value reaches zero, add it to the queue if it's not already present.

## Concept to Remember
* **Topological sorting**: A linear ordering of vertices in a directed acyclic graph (DAG) such that for every edge (u,v), vertex u comes before v in the ordering.
* **Graph traversal**: Algorithms that visit each node in a graph once, often used for traversing tree-like data structures.

## Common Mistakes
* Failing to recognize the problem as a classic graph problem and attempting a brute-force approach instead of using an efficient algorithm like topological sorting or DFS/BFS.
* Not properly initializing the `needed` array and `preqs` list, leading to incorrect counts and potential NullPointerExceptions.
* Misunderstanding the role of cycles in the graph and incorrectly assuming that the absence of a cycle guarantees course completion.

## Complexity Analysis
- Time: O(numCourses + edges) - We traverse each course once (numCourses) and its neighbors (edges).
- Space: O(numCourses + edges) - We use an adjacency list representation of the graph, which requires additional space proportional to the number of courses and their edges.

## Commented Code
```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Check if there are no prerequisites (i.e., all courses can be finished)
        if (prerequisites.length == 0) return true;

        // Initialize needed array and preqs list
        int[] needed = new int[numCourses]; // count of prerequisites for each course
        List<List<Integer>> preqs = new ArrayList<>(); // adjacency list representation of graph

        // Populate preqs list and needed array
        for (int i = 0; i < numCourses; i++) {
            preqs.add(new ArrayList<>()); // initialize empty list for each course
        }
        for (int[] pair : prerequisites) { // iterate over prerequisites
            // increment count of prerequisites for course at index pair[0]
            needed[pair[0]]++;
            // add pair[0] to the adjacency list of course at index pair[1]
            preqs.get(pair[1]).add(pair[0]);
        }

        // Initialize queue q with courses having no prerequisites
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (needed[i] == 0) { // if course has no prerequisites, add it to the queue
                q.add(i);
            }
        }

        int completed = 0; // count of completed courses

        // Perform graph traversal using a loop and decrement needed values as we visit each neighbor
        while (!q.isEmpty()) {
            int curr = q.poll(); // pop course from queue
            completed++; // increment count of completed courses
            for (int neigh : preqs.get(curr)) { // iterate over neighbors of popped course
                needed[neigh]--; // decrement count of prerequisites for neighbor
                if (needed[neigh] == 0) { // if neighbor has no more prerequisites, add it to the queue
                    q.add(neigh);
                }
            }
        }

        return completed == numCourses; // all courses have been completed if and only if the number of completed courses is equal to the total number of courses
    }
}
```

## Interview Tips
* Familiarize yourself with graph traversal algorithms, including topological sorting.
* Practice solving problems on platforms like LeetCode to develop your skills in coding under time pressure.
* Review the common mistakes and pitfalls mentioned above to avoid making similar errors during an interview.

## Revision Checklist
- [ ] Implement a correct graph representation using adjacency lists or matrices.
- [ ] Initialize needed array and preqs list correctly.
- [ ] Perform graph traversal efficiently using a loop and proper queue operations.
- [ ] Ensure that all courses have been visited and counted in the completed count.

## Similar Problems
* LeetCode 207. Course Schedule II (given two arrays, find if we can finish all courses with at most k free slots)
* LeetCode 210. Course Schedule II (find a valid ordering for the courses such that no course is taken after its prerequisite)

## Tags
`Graph Traversal`, `Topological Sorting`, `Array`, `Hash Map`
