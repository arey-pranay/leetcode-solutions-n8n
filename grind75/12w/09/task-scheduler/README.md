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
Solve the Task Scheduler problem by counting the frequency of each task and calculating the maximum number of idle slots required between frequent tasks.
The solution involves using a hashmap to count the frequency of each task, then calculating the maximum number of idle slots required.

## Intuition
This approach works because we can think of the problem as a scheduling problem where we need to assign tasks to threads with a given constraint (n) that there is an idle time between two same task threads.
We can use a hashmap to count the frequency of each task, then calculate the maximum number of idle slots required.

## Algorithm
1. Initialize a hashmap `freq` to store the frequency of each task.
2. Iterate through the tasks array and increment the corresponding value in `freq` for each task.
3. Find the maximum frequency (`max`) and the number of tasks with this frequency (`maxWale`).
4. Calculate the maximum number of idle slots required by multiplying the number of tasks with the maximum frequency minus one by (n+1) and adding the number of tasks with the maximum frequency.

## Concept to Remember
* Use a hashmap to count the frequency of each task.
* Think about the problem as a scheduling problem.
* Calculate the maximum number of idle slots required between frequent tasks.

## Common Mistakes
* Not initializing the hashmap correctly, leading to incorrect frequencies.
* Misunderstanding the concept of idle slots and how they are calculated.
* Not considering the case when multiple tasks have the same maximum frequency.

## Complexity Analysis
- Time: O(N) - reason: iterating through the tasks array once
- Space: O(1) - reason: using a fixed-size hashmap

## Commented Code
```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        // Initialize hashmap to count frequencies
        int[] freq = new int[26];
        
        // Count the frequency of each task
        for (char c : tasks) freq[c - 'A']++;
        
        // Find maximum frequency and number of tasks with this frequency
        int maxWale = 0;
        int max = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > max) {
                maxWale = 1;
                max = freq[i];
            } else if (freq[i] == max) {
                maxWale++;
            }
        }
        
        // Calculate maximum number of idle slots required
        return Math.max(tasks.length, (max - 1) * (n + 1) + maxWale);
    }
}
```

## Interview Tips
* Practice solving similar scheduling problems.
* Pay attention to the details in the problem statement.
* Use a hashmap to count frequencies when dealing with character arrays.

## Revision Checklist
- [ ] Review and understand the problem statement carefully.
- [ ] Practice solving similar scheduling problems.
- [ ] Use a hashmap to count frequencies when dealing with character arrays.

## Similar Problems
* 452. Useless Currency
* 622. Design Circular Queue

## Tags
`Array` `Hash Map` `Scheduling`
