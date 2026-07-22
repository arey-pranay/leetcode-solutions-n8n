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
This problem asks for the maximum profit achievable by scheduling non-overlapping jobs.
We solve it using dynamic programming with memoization and binary search to find compatible jobs.

## Intuition
The core idea is to consider each job and decide whether to take it or skip it. If we take a job, we gain its profit and then need to find the maximum profit we can get from jobs that start *after* the current job finishes. If we skip a job, we simply move on to consider the next job. This recursive structure, combined with the need to avoid recomputing results for the same subproblems, points towards dynamic programming. To efficiently find the next compatible job, sorting the jobs by start time and using binary search is a natural optimization.

## Algorithm
1.  **Combine and Sort Jobs**: Create a 2D array `jobs` where each row represents a job with `[startTime, endTime, profit]`. Sort this `jobs` array based on the `startTime` in ascending order. This is crucial for the DP approach.
2.  **Initialize DP Table**: Create a memoization array `memo` of the same size as the number of jobs, initialized with -1 (or any indicator that the value hasn't been computed yet). `memo[i]` will store the maximum profit achievable considering jobs from index `i` onwards.
3.  **Recursive Function `func(i)`**:
    *   **Base Case**: If `i` reaches the end of the `jobs` array (`i == jobs.length`), it means there are no more jobs to consider, so return 0 profit.
    *   **Memoization Check**: If `memo[i]` is not -1, it means the result for this subproblem has already been computed, so return `memo[i]`.
    *   **Option 1: Skip Current Job**: Calculate the maximum profit by skipping the current job `jobs[i]`. This is simply `func(i + 1)`.
    *   **Option 2: Take Current Job**:
        *   Find the index of the *next* job that can be scheduled *after* the current job `jobs[i]` finishes. This means finding the first job whose `startTime` is greater than or equal to `jobs[i][1]` (the current job's `endTime`). Use binary search (`findNext`) for this.
        *   Calculate the profit if we take the current job: `jobs[i][2]` (current job's profit) + `func(nextIndex)` (maximum profit from compatible subsequent jobs).
    *   **Store and Return**: The maximum profit for the subproblem starting at `i` is the maximum of the "skip" and "take" options. Store this result in `memo[i]` and return it.
4.  **Binary Search Function `findNext(endTime)`**:
    *   This function takes an `endTime` (from a previously scheduled job) and returns the index of the first job in the sorted `jobs` array whose `startTime` is greater than or equal to `endTime`.
    *   It performs a standard binary search on the `jobs` array (specifically on `jobs[m][0]`, the start times). The goal is to find the "insertion point" for `endTime`.
5.  **Initial Call**: Call `func(0)` to start the process from the first job.

## Concept to Remember
*   **Dynamic Programming (DP)**: Breaking down a problem into overlapping subproblems and storing their solutions to avoid redundant computations.
*   **Memoization**: A top-down DP approach where results of function calls are cached.
*   **Binary Search**: Efficiently searching for an element in a sorted array, crucial here for finding the next compatible job.
*   **Greedy vs. DP**: While a greedy approach might seem tempting (e.g., picking the job with the earliest end time), it doesn't guarantee optimality. DP explores all valid combinations.

## Common Mistakes
*   **Incorrectly finding the next job**: Not using binary search or implementing it incorrectly can lead to `O(N^2)` complexity or wrong results.
*   **Forgetting memoization**: Without memoization, the recursive solution will have exponential time complexity.
*   **Sorting criteria**: Not sorting by start time, or sorting by end time, will break the DP logic.
*   **Off-by-one errors in binary search**: Incorrectly handling the `low`, `high`, and `mid` pointers or the return value of binary search.
*   **Base case issues**: Not handling the `i == jobs.length` case correctly in the recursive function.

## Complexity Analysis
- Time: O(N log N) - reason: Sorting takes O(N log N). The DP function `func` is called at most N times (due to memoization). Inside `func`, `findNext` (binary search) takes O(log N). Thus, the total time is O(N log N + N * log N) = O(N log N).
- Space: O(N) - reason: The `memo` array takes O(N) space. The recursion depth can also go up to O(N) in the worst case, contributing to the call stack space.

## Commented Code
```java
class Solution {
    // Declare a 2D array to store job details: [startTime, endTime, profit]
    int[][] jobs;
    // Declare a memoization array to store results of subproblems.
    // memo[i] will store the maximum profit considering jobs from index i onwards.
    int[] memo;

    // Main function to schedule jobs and find maximum profit.
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // Get the total number of jobs.
        int n = startTime.length;
        // Initialize the jobs array with the given start times, end times, and profits.
        jobs = new int[n][3];
        // Initialize the memoization array with -1, indicating no results are computed yet.
        memo = new int[n];
        // Populate the jobs array.
        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i]; // Store start time
            jobs[i][1] = endTime[i];   // Store end time
            jobs[i][2] = profit[i];    // Store profit
        }
        // Sort the jobs array based on their start times in ascending order.
        // This is crucial for the dynamic programming approach.
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        // Fill the memoization array with -1.
        Arrays.fill(memo, -1);
        // Start the recursive DP calculation from the first job (index 0).
        return func(0);
    }

    // Recursive function to calculate maximum profit starting from job index 'i'.
    public int func(int i) {
        // Base case: If we have considered all jobs, no more profit can be made.
        if (i == jobs.length) return 0;
        // Memoization check: If the result for this index 'i' is already computed, return it.
        if (memo[i] != -1) return memo[i];

        // Option 1: Skip the current job 'jobs[i]'.
        // The maximum profit in this case is whatever we can get from the next job onwards.
        int skip = func(i + 1);

        // Option 2: Take the current job 'jobs[i]'.
        // First, find the index of the next job that can be scheduled after the current job finishes.
        // 'jobs[i][1]' is the end time of the current job.
        int nextIndex = findNext(jobs[i][1]);
        // The profit if we take the current job is its profit plus the maximum profit from the next compatible job onwards.
        int take = jobs[i][2] + func(nextIndex);

        // The maximum profit for the subproblem starting at index 'i' is the maximum of skipping or taking the current job.
        // Store this result in the memoization array before returning.
        return memo[i] = Math.max(skip, take);
    }

    // Binary search function to find the index of the first job whose start time
    // is greater than or equal to the given 'endTime'.
    public int findNext(int endTime) {
        // Initialize binary search pointers. 'i' is the lower bound, 'j' is the upper bound (exclusive).
        int i = 0;
        int j = jobs.length; // 'j' is one past the last index.

        // Perform binary search.
        while (i < j) {
            // Calculate the middle index to avoid potential integer overflow.
            int m = i + (j - i) / 2;
            // If the start time of the middle job is less than the required 'endTime',
            // it means this job and all jobs before it cannot be taken.
            // So, we search in the right half (from m+1 onwards).
            if (jobs[m][0] < endTime) {
                i = m + 1;
            }
            // If the start time of the middle job is greater than or equal to 'endTime',
            // this job is a potential candidate, or an earlier job might be.
            // So, we search in the left half (including 'm').
            else {
                j = m;
            }
        }
        // 'i' will be the index of the first job whose start time >= endTime.
        // This is the index of the next compatible job.
        return i;
    }
}
```

## Interview Tips
*   **Explain the DP State**: Clearly articulate what `memo[i]` represents (maximum profit from job `i` onwards).
*   **Justify Sorting**: Explain why sorting by start time is essential for the DP approach and for efficient binary search.
*   **Walk Through Binary Search**: Be prepared to explain how `findNext` works and why it correctly identifies the next compatible job. Trace an example if needed.
*   **Discuss Trade-offs**: Briefly mention why a greedy approach might fail and why DP is necessary.

## Revision Checklist
- [ ] Understand the problem: Maximize profit from non-overlapping jobs.
- [ ] Combine job details into a single structure.
- [ ] Sort jobs by start time.
- [ ] Implement DP with memoization.
- [ ] Define the recursive function `func(i)`.
- [ ] Handle base case `i == jobs.length`.
- [ ] Implement memoization check.
- [ ] Calculate profit for skipping the current job.
- [ ] Implement binary search (`findNext`) to find the next compatible job.
- [ ] Calculate profit for taking the current job.
- [ ] Take the maximum of skipping and taking.
- [ ] Store result in memo table.
- [ ] Understand time and space complexity.

## Similar Problems
*   1235. Maximum Profit In Job Scheduling (This problem)
*   45. Jump Game II
*   55. Jump Game
*   376. Wiggle Subsequence
*   646. Maximum Length of Pair Chain

## Tags
`Array` `Dynamic Programming` `Binary Search` `Sorting` `Memoization`
