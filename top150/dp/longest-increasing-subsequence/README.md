# Longest Increasing Subsequence

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Dynamic Programming`  
**Time:** O(n^2)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    int[] memo;
    public int lengthOfLIS(int[] nums) {
        int ans = Integer.MIN_VALUE;
        memo = new int[nums.length];
        Arrays.fill(memo,-1);
        for(int i = 0 ; i<nums.length;i++) ans = Math.max(ans,func(nums,i));
        return ans;
    }
    public int func(int[] nums, int i){
        if(memo[i] != -1) return memo[i];
        int max = 1;
        for(int j=0;j<i;j++){
            if(nums[j] < nums[i]){
                int temp = 1 + memo[j];
                max = Math.max(max,temp);
            }
        }
        return memo[i] = max;
    }
}
```

---

---

## Quick Revision
Find the length of the longest increasing subsequence in an array.
Solve it using dynamic programming with memoization.

## Intuition
The problem can be solved by considering each element as a potential ending point for the subsequence. We keep track of the maximum length of increasing subsequence ending at each position, and finally return the maximum value seen so far. This approach works because the subproblem of finding the longest increasing subsequence ending at any given index is independent of other indices.

## Algorithm
1. Initialize a memo array to store the maximum length of increasing subsequence ending at each index.
2. Fill the memo array with -1, indicating that no result has been computed yet.
3. Iterate through the input array from left to right.
4. For each element, recursively call `func` to find the maximum length of increasing subsequence ending at this position.
5. Update the answer (maximum length seen so far) if the current maximum is greater than the previous best.

## Concept to Remember
* **Memoization**: storing the results of expensive function calls and reusing them when the same inputs occur again.
* **Dynamic Programming**: breaking down a problem into smaller subproblems, solving each one only once, and storing their solutions to avoid redundant computation.

## Common Mistakes
* Not properly initializing memo array, leading to incorrect results.
* Incorrectly implementing recursive call in `func`, resulting in infinite loops or incorrect maximum lengths.
* Forgetting to update the answer variable with the new maximum length found.

## Complexity Analysis
- Time: O(n^2) - where n is the length of input array, due to nested loops in `func`.
- Space: O(n) - for memoization array.

## Commented Code
```java
class Solution {
    int[] memo; // memoization array

    public int lengthOfLIS(int[] nums) { // main function
        int ans = Integer.MIN_VALUE; // initialize answer to min possible value
        memo = new int[nums.length]; // create memo array of size n
        Arrays.fill(memo, -1); // fill with -1 to indicate no result computed yet

        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, func(nums, i)); // update answer if current max is greater
        }
        return ans;
    }

    public int func(int[] nums, int i) { // recursive function to find max length
        if (memo[i] != -1) return memo[i]; // check if result already computed

        int max = 1; // initialize max length to 1 (base case)

        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) { // if current element is larger than previous
                int temp = 1 + memo[j]; // compute new max length by adding 1 and previous max
                max = Math.max(max, temp); // update max length if necessary
            }
        }

        return memo[i] = max; // store result in memo array and return it
    }
}
```

## Interview Tips
* Pay attention to the `memo` initialization step, as incorrect initializations can lead to wrong results.
* Use descriptive variable names and comments to explain your code's logic.
* Practice solving this problem on a whiteboard or paper before attempting it in an interview setting.

## Revision Checklist
- [ ] Implement memoization correctly.
- [ ] Ensure recursive call in `func` is implemented accurately.
- [ ] Verify space complexity of the solution (O(n) for memo array).

## Similar Problems
* 72. Edit Distance (LeetCode)
* 746. Minimum Number of Arrows to Burst Balloons (LeetCode)

## Tags
`Array`, `Hash Map`, `Dynamic Programming`, `Memoization`
