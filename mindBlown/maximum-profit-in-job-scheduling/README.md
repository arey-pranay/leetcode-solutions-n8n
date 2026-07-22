# Maximum Profit In Job Scheduling

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Dynamic Programming` `Sorting`  
**Time:** O(N log N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    int[][] jobs;
    int[] memo;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit){
      int n = startTime.length;
      jobs = new int[n][3];
      memo = new int[n];
      for(int i=0;i<n;i++) {jobs[i][0] = startTime[i];jobs[i][1]=endTime[i];jobs[i][2]=profit[i];}
      Arrays.sort(jobs,(a,b)->a[0]-b[0]);
      Arrays.fill(memo,-1);
      return func(0);
    }
    public int func(int i){
        if(i==jobs.length) return 0;
        if(memo[i]!=-1) return memo[i];
        int skip = func(i+1);
        int nextIndex = findNext(jobs[i][1]);
        int take = jobs[i][2] + func(nextIndex);
        return memo[i] = Math.max(skip,take);
    }
    public int findNext(int endTime){
        int i =0 ;
        int j =jobs.length;
        while(i<j){
            int m = i+ (j-i)/2;
            if(jobs[m][0]<endTime) i = m+1;
            else j = m;
        }
        return i;
    }
}
```

---

---
## Quick Revision
Given a list of jobs with start times, end times, and profits, find the maximum profit by scheduling non-overlapping jobs.
This problem can be solved using dynamic programming with memoization and binary search.

## Intuition
The core idea is to make a decision for each job: either take it or skip it. If we take a job, we gain its profit, but we can only consider subsequent jobs that start *after* the current job ends. If we skip a job, we move on to consider the next job. This recursive structure with overlapping subproblems suggests dynamic programming. To efficiently find the next non-overlapping job, sorting the jobs by start time and using binary search is crucial.

## Algorithm
1.  **Combine and Sort Jobs**: Create a 2D array `jobs` where each row represents a job `[startTime, endTime, profit]`. Sort this `jobs` array based on the `startTime` in ascending order. This ensures we process jobs in a chronological order.
2.  **Initialize Memoization**: Create a memoization array `memo` of the same size as the number of jobs, initialized with -1. `memo[i]` will store the maximum profit achievable starting from job `i`.
3.  **Recursive Function `func(i)`**:
    *   **Base Case**: If `i` reaches the end of the `jobs` array (`i == jobs.length`), it means there are no more jobs to consider, so return 0 profit.
    *   **Memoization Check**: If `memo[i]` is not -1, it means the result for this subproblem has already been computed, so return `memo[i]`.
    *   **Option 1: Skip Current Job**: Calculate the profit obtained by skipping the current job `i`. This is `func(i + 1)`.
    *   **Option 2: Take Current Job**:
        *   Find the index of the *next* job that can be scheduled *after* the current job `i` finishes. This is done using binary search (`findNext(jobs[i][1])`) on the sorted `jobs` array, looking for the first job whose start time is greater than or equal to the current job's end time.
        *   Calculate the profit obtained by taking the current job `i` and then recursively finding the maximum profit from the `nextIndex`. This is `jobs[i][2] + func(nextIndex)`.
    *   **Store and Return**: The maximum profit for the subproblem starting at `i` is the maximum of the "skip" and "take" options. Store this result in `memo[i]` and return it.
4.  **Binary Search Function `findNext(endTime)`**:
    *   This function performs a binary search on the `jobs` array (which is sorted by start time) to find the smallest index `m` such that `jobs[m][0] >= endTime`.
    *   Initialize `i = 0` and `j = jobs.length`.
    *   While `i < j`:
        *   Calculate the middle index `m`.
        *   If `jobs[m][0] < endTime`, it means the job at `m` starts too early, so we need to search in the right half: `i = m + 1`.
        *   Otherwise (`jobs[m][0] >= endTime`), the job at `m` might be the first valid one, so we search in the left half (including `m`): `j = m`.
    *   Return `i` (which will be the index of the first job that starts at or after `endTime`).
5.  **Initial Call**: Call `func(0)` to start the process from the first job.

## Concept to Remember
*   **Dynamic Programming (DP)**: Problems with overlapping subproblems and optimal substructure are good candidates for DP. Here, the maximum profit from a subset of jobs depends on the maximum profit from smaller subsets.
*   **Memoization**: Storing the results of expensive function calls and returning the cached result when the same inputs occur again to avoid redundant computations.
*   **Binary Search**: Efficiently finding an element or a position in a sorted array. Used here to quickly find the next non-overlapping job.
*   **Greedy Approach vs. DP**: While a greedy approach might seem tempting (e.g., picking the job with the earliest end time), it doesn't guarantee the optimal solution. DP explores all valid combinations.

## Common Mistakes
*   **Incorrect Binary Search Logic**: Off-by-one errors or incorrect boundary conditions in the binary search can lead to selecting the wrong next job or infinite loops.
*   **Not Sorting Jobs**: The algorithm relies heavily on jobs being sorted by start time for both the DP state transitions and the binary search to work correctly.
*   **Forgetting Memoization**: Without memoization, the recursive solution would have exponential time complexity due to recomputing the same subproblems repeatedly.
*   **Incorrect DP State Definition**: Defining the DP state incorrectly (e.g., based on end times instead of start times or indices) can make the transitions difficult or impossible.
*   **Handling Overlapping Jobs Incorrectly**: Misunderstanding how to find the *next* valid job after taking a current job.

## Complexity Analysis
-   **Time**: O(N log N) - reason: Sorting the jobs takes O(N log N). The DP function `func` is called at most N times (once for each job index). Inside `func`, the `findNext` binary search takes O(log N). Therefore, the total time for DP is O(N log N). The dominant factor is sorting.
-   **Space**: O(N) - reason: The `memo` array stores results for N subproblems. The recursion depth can also go up to N in the worst case, contributing to the call stack space.

## Commented Code
```java
class Solution {
    // Declare a 2D array to store job details: [startTime, endTime, profit]
    int[][] jobs;
    // Declare a memoization array to store computed maximum profits for subproblems
    int[] memo;

    // Main function to schedule jobs and find maximum profit
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit){
      // Get the total number of jobs
      int n = startTime.length;
      // Initialize the jobs array with the given start times, end times, and profits
      jobs = new int[n][3];
      // Initialize the memoization array with -1, indicating no results are computed yet
      memo = new int[n];
      // Populate the jobs array
      for(int i=0;i<n;i++) {
          jobs[i][0] = startTime[i]; // Store start time
          jobs[i][1] = endTime[i];   // Store end time
          jobs[i][2] = profit[i];    // Store profit
      }
      // Sort the jobs array based on their start times in ascending order
      // This is crucial for the DP approach and binary search
      Arrays.sort(jobs,(a,b)->a[0]-b[0]);
      // Fill the memoization array with -1
      Arrays.fill(memo,-1);
      // Start the recursive DP calculation from the first job (index 0)
      return func(0);
    }

    // Recursive function to calculate maximum profit starting from job index 'i'
    public int func(int i){
        // Base case: If we have considered all jobs, return 0 profit
        if(i==jobs.length) return 0;
        // Memoization check: If the result for job 'i' is already computed, return it
        if(memo[i]!=-1) return memo[i];

        // Option 1: Skip the current job 'i'
        // Recursively calculate the maximum profit by moving to the next job (i+1)
        int skip = func(i+1);

        // Option 2: Take the current job 'i'
        // Find the index of the next job that does not overlap with the current job
        // 'jobs[i][1]' is the end time of the current job
        int nextIndex = findNext(jobs[i][1]);
        // Calculate the profit by taking the current job's profit plus the max profit from the next non-overlapping job
        int take = jobs[i][2] + func(nextIndex);

        // Store the maximum of the two options (skip or take) in the memoization array for job 'i'
        // This result will be used if func(i) is called again
        return memo[i] = Math.max(skip,take);
    }

    // Binary search function to find the index of the first job that starts at or after 'endTime'
    public int findNext(int endTime){
        // Initialize search boundaries: 'i' is the lower bound, 'j' is the upper bound (exclusive)
        int i =0 ;
        int j =jobs.length;
        // Perform binary search
        while(i<j){
            // Calculate the middle index to avoid potential integer overflow
            int m = i+ (j-i)/2;
            // If the start time of the job at 'm' is less than 'endTime',
            // it means this job and all before it overlap. So, we need to search in the right half.
            if(jobs[m][0]<endTime) i = m+1;
            // Otherwise, the job at 'm' starts at or after 'endTime'.
            // This job might be the first valid one, so we search in the left half (including 'm').
            else j = m;
        }
        // 'i' will point to the index of the first job whose start time is >= endTime
        return i;
    }
}
```

## Interview Tips
*   **Explain the DP State**: Clearly articulate what `dp[i]` (or `memo[i]` in this case) represents. In this problem, it's the maximum profit achievable considering jobs from index `i` onwards.
*   **Justify Binary Search**: Explain *why* binary search is used and how it efficiently finds the next non-overlapping job after sorting. Emphasize that without sorting, binary search wouldn't work.
*   **Walk Through an Example**: Use a small example (3-4 jobs) to trace the execution of the `func` and `findNext` methods, showing how decisions are made and memoization is used.
*   **Discuss Trade-offs**: Briefly mention why a purely greedy approach might fail and why DP is necessary.

## Revision Checklist
- [ ] Understand the problem statement: Maximize profit from non-overlapping jobs.
- [ ] Combine job details into a single structure.
- [ ] Sort jobs by start time.
- [ ] Define DP state: `memo[i]` = max profit from job `i` onwards.
- [ ] Implement recursive `func(i)` with base case and memoization.
- [ ] Implement "skip" option: `func(i+1)`.
- [ ] Implement "take" option: `profit[i] + func(next_valid_job_index)`.
- [ ] Implement `findNext` using binary search.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (e.g., no jobs, all jobs overlap).

## Similar Problems
*   1235. Maximum Profit In Job Scheduling (This problem)
*   45. Jump Game II
*   376. Wiggle Subsequence
*   646. Maximum Length of Pair Chain
*   931. Minimum Falling Path Sum

## Tags
`Array` `Dynamic Programming` `Binary Search` `Sorting` `Memoization`
