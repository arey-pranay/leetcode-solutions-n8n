# Split Array Largest Sum

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Dynamic Programming` `Greedy` `Prefix Sum`  
**Time:** O(N * log(S)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int splitArray(int[] nums, int k) {
        // low  = 10 (max)
        // high = 32 (total)
        int low = 0, high = 0; // humaara ans will be the max number in case we divide all of them separately aur humara ans will be max if we put all of them in one group. we need to pick the correct answer between them.
        for(int num : nums){low = Math.max(low,num); high += num;}
        int res = high;
        while(low<=high){
            int mid = low + (high-low)/2;
            if(canBreak(nums,k,mid)){
                res = mid;
                high = mid - 1;
            } else {
                low = mid+1;
            }
        }
        return res;
    }
    public boolean canBreak(int[] nums, int k, int maxSum){
        // running sum lenge, aur jese hi sum maxSum se increase hua, sum reset and k--
        
        // kya hum nums ko k groups me tod skte hai, jisse har group ka sum maxSum se km ho
        
        // agr k extra bach gye to theek hai, we can obviously make more groups, but k khtm hogye to hum haar gye
         
        int sum = 0;
        for(int num : nums){
            sum += num;
            if(sum>maxSum){
                sum = num;
                k--;
                if(k==0) return false;
            }
        }
        return true;
    }
}
// 7 2,5,10,8 => 7,25
// 7,2 5,10,8 => 9,23
// 7,2,5 10,8 => 14,18
// 7,2,5,10,8 => 24,8


// 7     2     5,10,8
  
```

---

---
## Quick Revision
The problem asks to split an array into `k` subarrays such that the maximum sum of any subarray is minimized. This is solved using binary search on the possible answer for the maximum sum.

## Intuition
The core idea is that the minimum possible value for the largest sum of a subarray is the maximum element in the array (when `k` is equal to the array length). The maximum possible value is the sum of all elements (when `k` is 1). This range of possible answers suggests binary search. For any given `maxSum` we are testing, we can greedily check if it's possible to split the array into `k` or fewer subarrays, each with a sum less than or equal to `maxSum`. If it is possible, it means we might be able to achieve an even smaller `maxSum`, so we try the lower half of our search space. Otherwise, we need a larger `maxSum`, so we search the upper half.

## Algorithm
1.  **Determine Search Space:**
    *   Initialize `low` to the maximum element in `nums` (the smallest possible largest sum).
    *   Initialize `high` to the sum of all elements in `nums` (the largest possible largest sum).
2.  **Binary Search:**
    *   While `low <= high`:
        *   Calculate `mid = low + (high - low) / 2`. This `mid` is our candidate for the maximum allowed sum for any subarray.
        *   Call a helper function `canBreak(nums, k, mid)` to check if the array can be split into `k` or fewer subarrays, each with a sum less than or equal to `mid`.
        *   If `canBreak` returns `true`:
            *   It means `mid` is a feasible maximum sum. We store `mid` as a potential result (`res = mid`) and try to find an even smaller maximum sum by searching in the lower half: `high = mid - 1`.
        *   If `canBreak` returns `false`:
            *   It means `mid` is too small to be the maximum sum. We need to increase the allowed maximum sum, so we search in the upper half: `low = mid + 1`.
3.  **Return Result:** The final `res` will hold the minimum possible largest sum.

4.  **`canBreak(nums, k, maxSum)` Helper Function:**
    *   Initialize `currentSum = 0` and `subarraysNeeded = 1`.
    *   Iterate through each `num` in `nums`:
        *   Add `num` to `currentSum`.
        *   If `currentSum > maxSum`:
            *   This means the current subarray cannot accommodate `num` without exceeding `maxSum`.
            *   We must start a new subarray. Increment `subarraysNeeded`.
            *   Reset `currentSum` to `num` (as `num` will be the first element of the new subarray).
            *   If `subarraysNeeded > k`, it means we need more than `k` subarrays to satisfy the `maxSum` constraint, so return `false`.
    *   If the loop completes, return `true` (meaning it's possible to split into `k` or fewer subarrays).

## Concept to Remember
*   **Binary Search on Answer:** When the problem asks to minimize/maximize a value and there's a monotonic property (if a `maxSum` works, any larger `maxSum` also works), binary search on the answer is a powerful technique.
*   **Greedy Approach for Feasibility Check:** The `canBreak` function uses a greedy strategy to determine if a given `maxSum` is achievable. It tries to make each subarray as large as possible without exceeding `maxSum`.
*   **Range of Possible Answers:** Understanding the lower and upper bounds for the potential answer is crucial for setting up the binary search.

## Common Mistakes
*   **Incorrect Binary Search Range:** Not correctly identifying the minimum (`max(nums)`) and maximum (`sum(nums)`) possible values for the largest sum.
*   **Off-by-One Errors in `canBreak`:** Incorrectly handling the `k` count or resetting `currentSum` when a new subarray is started.
*   **Integer Overflow:** While less likely with standard integer types for typical LeetCode constraints, it's good practice to be mindful of potential overflows when summing large numbers, especially in languages like C++ or Java. Using `long` for sums can be a safeguard.
*   **Misunderstanding the `canBreak` Logic:** Thinking that if `k` subarrays are *exactly* formed, it's the only success condition. The condition is `k` *or fewer* subarrays.

## Complexity Analysis
- Time: O(N * log(S)) - reason: The binary search performs `log(S)` iterations, where `S` is the sum of all elements in `nums`. In each iteration, the `canBreak` function iterates through the `nums` array once, taking O(N) time.
- Space: O(1) - reason: The algorithm uses a constant amount of extra space for variables like `low`, `high`, `mid`, `res`, `sum`, and `k`.

## Commented Code
```java
class Solution {
    public int splitArray(int[] nums, int k) {
        // Initialize 'low' to the maximum element in nums. This is the smallest possible value for the largest sum (when each element is its own subarray).
        // Initialize 'high' to the sum of all elements in nums. This is the largest possible value for the largest sum (when there's only one subarray).
        int low = 0, high = 0;
        // Iterate through the array to find the maximum element and calculate the total sum.
        for(int num : nums){
            // 'low' will store the maximum single element.
            low = Math.max(low,num);
            // 'high' will accumulate the total sum of all elements.
            high += num;
        }
        // 'res' will store the minimum possible largest sum found so far. Initialize it to the maximum possible value.
        int res = high;
        // Perform binary search on the possible range of the largest sum.
        while(low<=high){
            // Calculate the middle value of the current search range. This is our candidate for the maximum allowed sum for any subarray.
            int mid = low + (high-low)/2;
            // Check if it's possible to split the array into 'k' or fewer subarrays, each with a sum less than or equal to 'mid'.
            if(canBreak(nums,k,mid)){
                // If it's possible, 'mid' is a feasible largest sum. We store it as a potential answer.
                res = mid;
                // Try to find an even smaller largest sum by searching in the lower half of the range.
                high = mid - 1;
            } else {
                // If it's not possible, 'mid' is too small. We need to increase the allowed largest sum.
                // Search in the upper half of the range.
                low = mid + 1;
            }
        }
        // Return the minimum possible largest sum found.
        return res;
    }

    // Helper function to check if the array 'nums' can be split into 'k' or fewer subarrays,
    // where the sum of each subarray does not exceed 'maxSum'.
    public boolean canBreak(int[] nums, int k, int maxSum){
        // Initialize the current sum of the subarray being formed.
        int sum = 0;
        // Iterate through each number in the array.
        for(int num : nums){
            // Add the current number to the running sum of the current subarray.
            sum += num;
            // If the current sum exceeds the allowed maximum sum for a subarray:
            if(sum>maxSum){
                // We must start a new subarray.
                // The current number 'num' becomes the first element of this new subarray.
                sum = num;
                // Decrement the count of available splits (or groups).
                k--;
                // If we have used up all 'k' splits and still need to make another split, it means 'maxSum' is too small.
                if(k==0) return false;
            }
        }
        // If we successfully iterated through all numbers without needing more than 'k' subarrays, return true.
        return true;
    }
}
```

## Interview Tips
*   **Explain the Binary Search Range:** Clearly articulate why `max(nums)` and `sum(nums)` are the correct bounds for your binary search.
*   **Walk Through `canBreak`:** Verbally explain the greedy logic of the `canBreak` function. Emphasize how it tries to pack as much as possible into each subarray.
*   **Edge Cases:** Discuss edge cases like `k=1` (answer is `sum(nums)`), `k=nums.length` (answer is `max(nums)`), and an empty `nums` array (though constraints usually prevent this).
*   **Monotonicity:** Highlight the monotonic property that makes binary search applicable: if an array can be split with a maximum sum `X`, it can also be split with any maximum sum `Y > X`.

## Revision Checklist
- [ ] Understand the problem: Split array into `k` subarrays, minimize the largest sum.
- [ ] Identify the search space for the answer: `[max(nums), sum(nums)]`.
- [ ] Implement binary search on this range.
- [ ] Develop a greedy `canBreak` function to check feasibility.
- [ ] Handle `canBreak` logic correctly: sum accumulation, new subarray creation, `k` decrement.
- [ ] Ensure correct return values for `canBreak` and the main function.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Minimize Maximum Pair Sum in Array
*   Capacity To Ship Packages Within D Days
*   Koko Eating Bananas
*   Minimum Number of Refueling Stops

## Tags
`Array` `Binary Search` `Greedy` `Dynamic Programming`
