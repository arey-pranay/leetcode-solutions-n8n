# Ipo

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Greedy` `Sorting` `Heap (Priority Queue)`  
**Time:** O(N log N + K log N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> b.profit - a.profit);
        for(int i =0; i<profits.length;i++) pq.add(new Node(profits[i],capital[i]));
        int taken = 0;
        Queue<Node> q = new LinkedList<>();
        while(taken < k && !pq.isEmpty()){
            Node curr = pq.poll();
            if(curr.capital <= w){
                w += curr.profit;
                taken++;
                while(!q.isEmpty())pq.offer(q.poll());
            }
            else q.add(curr);
        }
        return w;
    }
    class Node{
        int profit;
        int capital;
        Node(int p, int c){
            this.profit = p;
            this.capital = c;
        }
    }
}
```

---

---
## Quick Revision
This problem asks to maximize capital by completing at most k projects, given initial capital and project requirements.
We solve this by greedily picking the most profitable projects that we can afford at each step.

## Intuition
The core idea is to always pick the most profitable project that we can currently afford. If we can afford multiple projects, picking the one with the highest profit will give us the most capital to potentially afford even more projects later. This suggests a greedy approach. However, simply sorting projects by profit or capital won't work directly because our available capital changes. We need a way to efficiently find the best affordable project at each step. A max-heap for profits of affordable projects, combined with a way to manage projects that are not yet affordable, seems like a good fit.

## Algorithm
1. Create a `Node` class to store `profit` and `capital` for each project.
2. Create a max-heap (`pq`) that prioritizes nodes by their `profit` in descending order.
3. Create a min-heap (`availableProjects`) that prioritizes nodes by their `capital` in ascending order. This will help us efficiently find projects that become affordable.
4. Populate `availableProjects` with all projects, storing their profit and capital.
5. Initialize `currentCapital` to `w`.
6. Iterate `k` times (for each project we can undertake):
    a. While `availableProjects` is not empty and the project at the top of `availableProjects` has a `capital` less than or equal to `currentCapital`:
        i. Remove the project from `availableProjects`.
        ii. Add this project to the `pq` (max-heap of affordable projects).
    b. If `pq` is empty, it means no more projects can be afforded, so break the loop.
    c. Otherwise, take the project with the maximum profit from `pq` (poll from `pq`).
    d. Add the profit of this project to `currentCapital`.
7. Return `currentCapital`.

## Concept to Remember
*   Greedy Algorithms: Making locally optimal choices to achieve a globally optimal solution.
*   Priority Queues (Heaps): Efficiently managing ordered collections, particularly for finding min/max elements.
*   Data Structures for Dynamic Sets: Using multiple data structures (e.g., two heaps) to manage elements that change their eligibility based on a dynamic condition.

## Common Mistakes
*   Not handling the changing `currentCapital` correctly: A static sort won't work.
*   Using a single data structure inefficiently: Trying to find affordable projects by iterating through all projects repeatedly.
*   Incorrectly prioritizing projects: Forgetting to prioritize by profit once a project becomes affordable.
*   Off-by-one errors in the loop for `k` projects.
*   Not considering the case where no more projects can be afforded.

## Complexity Analysis
- Time: O(N log N + K log N) - reason: N projects are initially added to a min-heap (O(N log N)). In the worst case, each of the K projects involves polling from the min-heap (up to N times in total across all K iterations) and adding to a max-heap (O(log N)), and then polling from the max-heap (O(log N)). So, O(N log N) for initial setup and O(K log N) for the K project selections.
- Space: O(N) - reason: To store all N projects in the priority queues.

## Commented Code
```java
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    // Define a helper class to store project details
    class Node {
        int profit; // The profit of the project
        int capital; // The capital required to start the project

        // Constructor for the Node class
        Node(int p, int c) {
            this.profit = p; // Initialize profit
            this.capital = c; // Initialize capital
        }
    }

    // Main function to find the maximized capital
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // PriorityQueue to store projects that are currently affordable, ordered by profit (max-heap)
        // The comparator (a, b) -> b.profit - a.profit ensures that the project with the highest profit is at the top.
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.profit - a.profit);

        // PriorityQueue to store all projects, ordered by capital required (min-heap)
        // This helps us efficiently find projects that become affordable as our capital increases.
        // The comparator (a, b) -> a.capital - b.capital ensures that the project with the lowest capital requirement is at the top.
        PriorityQueue<Node> availableProjects = new PriorityQueue<>(Comparator.comparingInt(node -> node.capital));

        // Populate the availableProjects min-heap with all projects
        for (int i = 0; i < profits.length; i++) {
            availableProjects.offer(new Node(profits[i], capital[i])); // Add each project as a Node
        }

        int currentCapital = w; // Initialize current capital with the starting capital 'w'
        int projectsCompleted = 0; // Counter for the number of projects completed

        // Loop to select up to 'k' projects
        while (projectsCompleted < k) {
            // Move all projects that are now affordable (capital <= currentCapital) from availableProjects to pq
            while (!availableProjects.isEmpty() && availableProjects.peek().capital <= currentCapital) {
                pq.offer(availableProjects.poll()); // Poll from min-heap and offer to max-heap
            }

            // If the pq is empty, it means no more projects can be afforded with the current capital.
            if (pq.isEmpty()) {
                break; // Exit the loop as no more projects can be undertaken
            }

            // Get the most profitable project from the pq (which contains affordable projects)
            Node bestProject = pq.poll(); // Poll the project with the highest profit

            // Add the profit of the selected project to the current capital
            currentCapital += bestProject.profit;

            // Increment the count of completed projects
            projectsCompleted++;
        }

        // Return the final maximized capital
        return currentCapital;
    }
}
```

## Interview Tips
*   Explain the greedy choice: Clearly articulate why picking the most profitable affordable project at each step is optimal.
*   Discuss data structure choices: Justify using two priority queues and their respective ordering (min-heap for capital, max-heap for profit).
*   Handle edge cases: Mention what happens if `k` is 0, if there are no projects, or if no projects can ever be afforded.
*   Walk through an example: Use a small example to demonstrate how the algorithm progresses.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the greedy strategy.
- [ ] Choose appropriate data structures (priority queues).
- [ ] Implement the two-heap approach correctly.
- [ ] Handle the condition where no more projects can be afforded.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
*   Task Scheduler
*   Merge K Sorted Lists
*   Furthest Building You Can Reach

## Tags
`Greedy` `Heap` `Priority Queue` `Array`
