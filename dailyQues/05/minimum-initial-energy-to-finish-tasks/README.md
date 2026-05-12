# Minimum Initial Energy To Finish Tasks

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Greedy` `Sorting`  
**Time:** O(N log N)  
**Space:** O(log N)

---

## Solution (java)

```java
class Solution {
    public int minimumEffort(int[][] tasks) {
        int min_diff =  Integer.MAX_VALUE;
        int sum = 0;
        int max_needed = Integer.MIN_VALUE;
        int m = tasks.length;
        Arrays.sort(tasks, (a,b) -> (b[1]-b[0])-(a[1]-a[0]));
        int ans = tasks[0][1]; //12 + 9 + 8 + 3 + 1
        int rem = ans - tasks[0][0];
        for(int i = 1;i<m;i++){
            int needed = tasks[i][1];
            if(needed > rem){
                ans += needed-rem;
                rem = needed;
            }
            rem -= tasks[i][0];
            
            // sum+=tasks[i][0];
            // max_needed = Math.max(tasks[i][1],max_needed);
            // int curr = tasks[i][1]-tasks[i][0];
            // min_diff =  Math.min(curr,min_diff);
        }
        // System.out.print(sum+" "+min_diff);
        return ans;
        // return sum>max_nedded ? sum+min_diff: max_nedded; sum = 6 max 5 min 100
    } 
}
```

---

---
## Quick Revision
This problem asks for the minimum initial energy required to complete a set of tasks, each with an actual cost and a minimum required energy.
We solve it by sorting tasks based on their energy difference and greedily accumulating the required energy.

## Intuition
The key insight is that tasks with a larger difference between their minimum required energy and their actual cost should be performed *later*. This is because performing them earlier would "waste" more potential energy if we have excess. By performing tasks with smaller differences first, we maximize the chance that our current energy will be sufficient for subsequent tasks. If we encounter a task that requires more energy than we currently have available (after accounting for the minimum required), we must increase our initial energy. The amount we need to increase is precisely the deficit.

## Algorithm
1. **Sort the tasks:** Sort the `tasks` array in descending order based on the difference between the minimum required energy and the actual energy cost (`task[1] - task[0]`). This prioritizes tasks that are "easier" to complete in terms of energy difference.
2. **Initialize variables:**
   - `ans`: This will store the minimum initial energy required. Initialize it with the minimum required energy of the first task after sorting (`tasks[0][1]`). This is the baseline energy needed for the first task.
   - `rem`: This represents the remaining energy we have *after* completing the current task, considering the minimum required energy. Initialize it with the energy remaining after completing the first task: `ans - tasks[0][0]`.
3. **Iterate through the remaining tasks:** For each task from the second one onwards (index `i` from 1 to `m-1`):
   - Let `needed` be the minimum required energy for the current task (`tasks[i][1]`).
   - **Check for deficit:** If `needed` is greater than `rem` (meaning our current remaining energy is not enough to meet the minimum requirement for this task), we need to increase our initial energy.
     - The additional energy needed is `needed - rem`. Add this to `ans`.
     - Update `rem` to `needed`, as this is now the new minimum energy we must have *before* starting this task to meet its requirement.
   - **Update remaining energy:** Subtract the actual energy cost of the current task from `rem`: `rem -= tasks[i][0]`.
4. **Return `ans`:** The final value of `ans` is the minimum initial energy required to complete all tasks.

## Concept to Remember
*   **Greedy Approach:** Making locally optimal choices at each step to achieve a globally optimal solution.
*   **Sorting Strategy:** The choice of sorting criteria is crucial for the greedy algorithm's success. Sorting by `task[1] - task[0]` in descending order is key.
*   **Energy Management:** Carefully tracking available energy and required energy to identify deficits.

## Common Mistakes
*   **Incorrect Sorting:** Sorting by actual cost (`task[0]`) or minimum required energy (`task[1]`) individually, or in the wrong order, will lead to incorrect results.
*   **Mismanaging `rem`:** Not correctly updating `rem` to reflect the minimum required energy when a deficit is encountered can cause errors.
*   **Off-by-one errors:** Incorrectly handling the first task or loop boundaries.
*   **Integer Overflow:** While not explicitly an issue with the given constraints, in similar problems, one might need to consider using `long` for energy sums.

## Complexity Analysis
- Time: O(N log N) - due to sorting the `tasks` array, where N is the number of tasks. The subsequent loop is O(N).
- Space: O(log N) or O(N) - depending on the sorting algorithm used by `Arrays.sort()`. In Java, it's typically O(log N) for primitive types and O(N) for objects if a copy is made.

## Commented Code
```java
class Solution {
    public int minimumEffort(int[][] tasks) {
        // Get the number of tasks.
        int m = tasks.length;

        // Sort tasks. The custom comparator sorts in descending order of (b[1]-b[0]) - (a[1]-a[0]).
        // This means tasks with a larger difference between minimum required energy and actual cost
        // will come later in the sorted array. This is the core greedy strategy.
        Arrays.sort(tasks, (a,b) -> (b[1]-b[0])-(a[1]-a[0]));

        // Initialize 'ans' with the minimum required energy for the first task.
        // This is our initial guess for the minimum initial energy.
        int ans = tasks[0][1];

        // Initialize 'rem' with the energy remaining after completing the first task.
        // 'rem' represents the energy we have *after* accounting for the minimum required energy
        // for the tasks already processed.
        int rem = ans - tasks[0][0];

        // Iterate through the remaining tasks, starting from the second task.
        for(int i = 1; i < m; i++){
            // 'needed' is the minimum energy required to start the current task.
            int needed = tasks[i][1];

            // If the current remaining energy 'rem' is less than the minimum energy 'needed' for this task,
            // it means we don't have enough energy to even meet the minimum requirement.
            if(needed > rem){
                // We need to increase our initial energy 'ans'.
                // The amount to increase is the deficit: 'needed - rem'.
                ans += needed - rem;
                // After increasing our initial energy, the new minimum energy we must have *before*
                // starting this task is 'needed'. So, we update 'rem' to 'needed'.
                rem = needed;
            }
            // After ensuring we have enough energy to meet the minimum requirement,
            // we perform the task. The remaining energy is reduced by the actual cost of the task.
            rem -= tasks[i][0];
        }

        // 'ans' now holds the minimum initial energy required to complete all tasks.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Sorting Rationale:** Clearly articulate *why* sorting by `task[1] - task[0]` in descending order is the correct greedy strategy. This is the most critical part of the explanation.
*   **Trace with an Example:** Walk through a small example manually to demonstrate how `ans` and `rem` are updated. This shows your understanding of the state transitions.
*   **Discuss Edge Cases:** Consider what happens if there's only one task, or if all tasks have the same energy difference.
*   **Clarify `rem`'s Meaning:** Be precise about what `rem` represents at each step – it's not just raw energy, but energy available *after* satisfying the minimum requirement of previous tasks.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the greedy choice property.
- [ ] Determine the correct sorting order.
- [ ] Implement the iteration and energy update logic correctly.
- [ ] Test with provided examples and edge cases.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [Minimum Number of Arrows to Burst Balloons](https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/)
*   [Gas Station](https://leetcode.com/problems/gas-station/)
*   [Merge Intervals](https://leetcode.com/problems/merge-intervals/)

## Tags
`Array` `Greedy` `Sorting`
