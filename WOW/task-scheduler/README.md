# Task Scheduler

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Greedy` `Sorting` `Heap (Priority Queue)` `Counting`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int maxWale = 0;
        int max = 0;
        int[] freq= new int[26];
        int distinct = 0;
        for(char c : tasks) freq[c-'A']++;
        for(int i=0;i<26;i++) if(freq[i] > max){maxWale = 1; max = freq[i];} else if(freq[i]==max) maxWale++;
        return Math.max(tasks.length,(max-1)*(n+1)+maxWale);
    }
}
```

---

---
## Quick Revision
Given a list of tasks and a cooldown period, find the minimum time to complete all tasks.
The solution involves calculating the time based on the most frequent task and the cooldown.

## Intuition
The bottleneck in scheduling tasks is the most frequent task. If a task 'A' appears `max` times and the cooldown is `n`, we need at least `(max - 1) * (n + 1)` slots to accommodate these 'A's and their cooldowns. However, if there are multiple tasks with the same maximum frequency, they can fill some of these cooldown slots. The total time will be the maximum of the total number of tasks and the calculated time based on the most frequent task and cooldown.

## Algorithm
1. Count the frequency of each task.
2. Find the maximum frequency (`max`) among all tasks.
3. Count how many tasks have this maximum frequency (`maxWale`).
4. Calculate the minimum time required based on the most frequent task and cooldown: `(max - 1) * (n + 1) + maxWale`. This formula accounts for the `max` occurrences of the most frequent task, each followed by `n` cooldown slots, and then adds the `maxWale` tasks that share the maximum frequency to fill the last slots.
5. The final answer is the maximum of the total number of tasks (`tasks.length`) and the calculated time from step 4. This is because even if the cooldown calculation suggests a shorter time, we still need to execute all tasks.

## Concept to Remember
*   Greedy Approach: Making the locally optimal choice at each step to achieve a global optimum.
*   Frequency Analysis: Understanding the distribution of elements is key to optimization.
*   Mathematical Modeling: Deriving a formula to represent the constraints and find the minimum.

## Common Mistakes
*   Forgetting to consider the case where `tasks.length` is greater than the calculated time based on cooldown.
*   Incorrectly calculating the slots needed for the most frequent task and its cooldown.
*   Not accounting for multiple tasks sharing the maximum frequency.
*   Off-by-one errors in the formula for calculating time.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the tasks once to count frequencies and then iterate through the 26 possible task types (constant time) to find the maximum frequency and count.
- Space: O(1) - reason: We use a fixed-size array of 26 to store task frequencies, which is constant space.

## Commented Code
```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        // Initialize maxWale to store the count of tasks with the maximum frequency.
        int maxWale = 0;
        // Initialize max to store the maximum frequency of any task.
        int max = 0;
        // Create an array to store the frequency of each task (A-Z).
        int[] freq= new int[26];
        // Iterate through each task in the input array.
        for(char c : tasks) {
            // Increment the frequency count for the current task.
            // 'c'-'A' converts the character to an index (0 for 'A', 1 for 'B', etc.).
            freq[c-'A']++;
        }
        // Iterate through the frequency array to find the maximum frequency and count tasks with that frequency.
        for(int i=0;i<26;i++) {
            // If the current task's frequency is greater than the current maximum frequency.
            if(freq[i] > max){
                // Reset maxWale to 1 because we found a new highest frequency task.
                maxWale = 1;
                // Update the maximum frequency.
                max = freq[i];
            }
            // If the current task's frequency is equal to the current maximum frequency.
            else if(freq[i]==max) {
                // Increment maxWale as another task shares the maximum frequency.
                maxWale++;
            }
        }
        // Calculate the minimum time.
        // (max - 1) * (n + 1) accounts for the intervals needed for the most frequent task and its cooldowns.
        // maxWale adds the tasks that share the maximum frequency to fill the last slots.
        // Math.max(tasks.length, ...) ensures we don't return a time less than the total number of tasks.
        return Math.max(tasks.length,(max-1)*(n+1)+maxWale);
    }
}
```

## Interview Tips
*   Explain your thought process clearly, starting with the bottleneck (most frequent task).
*   Walk through an example to illustrate how the formula `(max - 1) * (n + 1) + maxWale` is derived.
*   Discuss edge cases, such as when `n` is 0 or when all tasks are unique.
*   Be prepared to discuss alternative approaches (like priority queues) and why this mathematical approach is more efficient.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the bottleneck: the most frequent task.
- [ ] Derive the formula for time based on the most frequent task and cooldown.
- [ ] Account for tasks with the same maximum frequency.
- [ ] Consider the total number of tasks as a lower bound.
- [ ] Analyze time and space complexity.
- [ ] Practice coding the solution from scratch.

## Similar Problems
`Top K Frequent Elements`
`Merge Intervals`
`Furthest Building You Can Reach`

## Tags
`Array` `Hash Map` `Greedy` `Math` `Priority Queue`
