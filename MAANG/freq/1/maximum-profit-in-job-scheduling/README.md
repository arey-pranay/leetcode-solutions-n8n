# Maximum Profit In Job Scheduling

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Dynamic Programming` `Sorting`  
**Time:** O(n log n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    int[][] jobs;
    int[] memo;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        jobs = new int[n][3];
        memo = new int[n];
        Arrays.fill(memo,-1);
        for(int i=0;i<n;i++){
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        Arrays.sort(jobs,(a,b)->a[0]-b[0]);
        return func(0);
    }
    private int func(int i){
        if(i==jobs.length) return 0;
        if(memo[i] != -1) return memo[i];
        int skip = func(i+1);
        int take = jobs[i][2] + func(upperBound(jobs[i][1])); // jb ye job khtm hogi, uske baad start hone wali first job
        return memo[i] = Math.max(skip,take);
    }
    private int upperBound(int target){
        int s = 0;
        int e = jobs.length;
        while(s<e){
            int m = s + (e-s)/2;
            if(jobs[m][0] < target) s = m+1;
            else e = m;
        }
        return s;
    }
}
```

---

---
## Quick Revision
The problem is to find the maximum profit that can be achieved by selecting a subset of jobs such that no two jobs have overlapping start times.
We solve this problem using dynamic programming and binary search.

## Intuition
The key insight here is to realize that we can solve this problem by treating it as a 0/1 knapsack problem, where each job is a 1-0 item and the capacity is the maximum end time of the jobs. We use a memoization approach to avoid recalculating the maximum profit for each subset of jobs.

## Algorithm
1. Sort the jobs based on their start times.
2. Initialize a memoization table `memo` of size `n`, where `n` is the number of jobs.
3. Iterate over each job `i` and calculate the maximum profit by considering two options:
	* Skip the current job and move to the next one: `func(i+1)`.
	* Take the current job and add its profit to the maximum profit of the jobs that start after its end time: `jobs[i][2] + func(upperBound(jobs[i][1]))`.
4. Use binary search to find the upper bound of the jobs that start after the end time of the current job.

## Concept to Remember
* Dynamic programming: breaking down the problem into smaller subproblems and storing the results to avoid recalculations.
* Binary search: finding the upper bound of a sorted array in logarithmic time.
* Memoization: storing the results of expensive function calls to avoid recalculations.

## Common Mistakes
* Not initializing the memoization table correctly.
* Not using binary search to find the upper bound of the jobs that start after the end time of the current job.
* Not considering the correct options when calculating the maximum profit.

## Complexity Analysis
- Time: O(n log n) - reason: sorting the jobs takes O(n log n) time, and the binary search takes O(log n) time per job.
- Space: O(n) - reason: we need to store the memoization table of size n.

## Commented Code
```java
class Solution {
    int[][] jobs;
    int[] memo;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        jobs = new int[n][3];
        memo = new int[n];
        Arrays.fill(memo,-1);
        // Initialize jobs array
        for(int i=0;i<n;i++){
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        // Sort jobs based on start time
        Arrays.sort(jobs,(a,b)->a[0]-b[0]);
        return func(0);
    }
    private int func(int i){
        // Base case: if we have processed all jobs, return 0
        if(i==jobs.length) return 0;
        // If memoization table has result for current job, return it
        if(memo[i] != -1) return memo[i];
        // Calculate maximum profit by considering two options
        int skip = func(i+1); // Skip current job
        int take = jobs[i][2] + func(upperBound(jobs[i][1])); // Take current job
        return memo[i] = Math.max(skip,take);
    }
    private int upperBound(int target){
        int s = 0;
        int e = jobs.length;
        while(s<e){
            int m = s + (e-s)/2;
            if(jobs[m][0] < target) s = m+1;
            else e = m;
        }
        return s;
    }
}
```

## Interview Tips
* Make sure to initialize the memoization table correctly.
* Use binary search to find the upper bound of the jobs that start after the end time of the current job.
* Consider the correct options when calculating the maximum profit.

## Revision Checklist
- [ ] Initialize memoization table correctly.
- [ ] Use binary search to find upper bound of jobs that start after current job's end time.
- [ ] Consider correct options when calculating maximum profit.

## Similar Problems
* 1036. Monitor Has Been Muted
* 1044. Longest Duplicate Substring
* 1221. Split a String in Balanced Strings

## Tags
`Array` `Hash Map` `Dynamic Programming` `Binary Search` `Memoization`
