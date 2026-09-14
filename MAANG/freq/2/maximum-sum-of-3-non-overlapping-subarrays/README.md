# Maximum Sum Of 3 Non Overlapping Subarrays

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Sliding Window` `Prefix Sum`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        
        int sum =0;
        int n = nums.length;
        for(int i=0;i<k;i++) sum += nums[i];
        int[] sums = new int[n-k+1];
        sums[0] = sum;
        int index = 1;
        for(int i=k;i<n;i++){
            sums[index] = sums[index-1]+nums[i]-nums[i-k];
            index++;
        } // har index se start krke k length ka group bnane ka sum
        int m = sums.length;
        int[] bestLeft = new int[m]; // ki current index se left me dekhne pe highest sum ka index kaunsa hai
        int[] bestRight = new int[m]; // ki current index se right me dekhne pe highest sum ka index kaunsa hai
                
        bestLeft[0] = 0;
        for(int i=1; i<m;i++){
            if(sums[i] > sums[bestLeft[i-1]]) bestLeft[i] = i;
            else bestLeft[i] = bestLeft[i-1];
        }
        bestRight[m-1] = m-1;
        for(int i=m-2; i>=0;i--){
            if(sums[i] >= sums[bestRight[i+1]]) bestRight[i] = i;
            else bestRight[i] = bestRight[i+1];
        }
        int[] ans = new int[3];
        int maxSum = 0;
        for(int mid = k; mid<m-k; mid++){ // 3 index choose krne hai, jo middle index hai wo k se m-k ke beech hoga, let's try all values of it
        
            // mid se mid-k aur mid+k ke index unusable hai, kyuki non-overlapping hona zaruri hai.
            int l = bestLeft[mid-k]; 
            int r = bestRight[mid+k];
            int currSum = sums[mid] + sums[l] + sums[r];
            if(currSum > maxSum){
                ans[0] = l;ans[1] = mid;ans[2]=r; maxSum = currSum;
            }
        }
        return ans;
    }
    // func(sums,0, new ArrayList<>(), 0);
    // public void func(int[] nums, int index, List<Integer> list, int sum){
    //     if(list.size()==3){
    //         if(sum > maxSum){
    //             maxSum = sum;
    //             for(int i=0;i<list.size();i++) ans[i] = list.get(i);
    //         }
    //         return;
    //     }
        
    //     if(index >= nums.length) return;
        
    //     list.add(index);
    //     sum+=nums[index];
    //     func(nums, index+minGap, list, sum);//take 
    //     sum-=nums[index];
    //     list.remove(list.size()-1);
    //     func(nums, index+1, list, sum);//notTake
        
    // }
}
// 1005 1 1004 1 1003 1 999 999 999 999 999 999
```

---

---
## Quick Revision
The problem asks to find three non-overlapping subarrays of a fixed length `k` that maximize their total sum. This is solved by pre-calculating sums of all possible subarrays of length `k` and then efficiently finding the best left, middle, and right subarrays.

## Intuition
The core idea is to break down the problem into finding the best possible left subarray, the best possible middle subarray, and the best possible right subarray, ensuring they don't overlap. If we fix the middle subarray, the problem of finding the best left and right subarrays becomes independent. We can pre-compute the best possible left subarray ending before a certain point and the best possible right subarray starting after a certain point. This allows us to iterate through all possible middle subarrays and, for each, quickly find the optimal left and right ones.

## Algorithm
1.  **Precompute Prefix Sums (or Sliding Window Sums):** Create an array `sums` where `sums[i]` stores the sum of the subarray of length `k` starting at index `i` in the original `nums` array. This can be done efficiently using a sliding window.
    *   Calculate the sum of the first `k` elements.
    *   For subsequent elements, slide the window: subtract the element leaving the window and add the element entering the window.
2.  **Precompute Best Left Subarray Indices:** Create an array `bestLeft` where `bestLeft[i]` stores the starting index of the subarray of length `k` with the maximum sum among all subarrays starting at or before index `i` in the `sums` array.
    *   Initialize `bestLeft[0]` to `0`.
    *   Iterate from `i = 1` to `m-1` (where `m` is the length of `sums`). If `sums[i]` is greater than `sums[bestLeft[i-1]]`, then `bestLeft[i] = i`. Otherwise, `bestLeft[i] = bestLeft[i-1]`.
3.  **Precompute Best Right Subarray Indices:** Create an array `bestRight` where `bestRight[i]` stores the starting index of the subarray of length `k` with the maximum sum among all subarrays starting at or after index `i` in the `sums` array.
    *   Initialize `bestRight[m-1]` to `m-1`.
    *   Iterate from `i = m-2` down to `0`. If `sums[i]` is greater than or equal to `sums[bestRight[i+1]]`, then `bestRight[i] = i`. Otherwise, `bestRight[i] = bestRight[i+1]`. (Using `>=` here is important to get the lexicographically smallest result if sums are equal).
4.  **Find the Optimal Triplet:** Iterate through all possible starting indices `mid` for the middle subarray. The middle subarray must start at an index `mid` such that there's enough space for a left subarray before it and a right subarray after it. This means `k <= mid <= m - 1 - k`.
    *   For each `mid`, the left subarray must start at an index `l` such that `l <= mid - k`. The best such `l` is given by `bestLeft[mid - k]`.
    *   Similarly, the right subarray must start at an index `r` such that `r >= mid + k`. The best such `r` is given by `bestRight[mid + k]`.
    *   Calculate the total sum: `sums[l] + sums[mid] + sums[r]`.
    *   Keep track of the maximum total sum found so far and the corresponding indices `l`, `mid`, and `r`.
5.  **Return the Indices:** Return the array containing the starting indices of the three subarrays that yield the maximum sum.

## Concept to Remember
*   **Sliding Window Technique:** Efficiently calculating sums of contiguous subarrays of a fixed size.
*   **Dynamic Programming (Precomputation):** Using `bestLeft` and `bestRight` arrays to store optimal subproblem solutions, enabling O(1) lookups.
*   **Greedy Approach (Implicit):** By precomputing the best left and right subarrays, we make locally optimal choices that lead to a globally optimal solution.
*   **Index Management:** Carefully handling array indices and ensuring non-overlapping conditions.

## Common Mistakes
*   **Incorrectly calculating `sums` array:** Off-by-one errors or incorrect sliding window logic.
*   **Overlapping subarrays:** Not correctly enforcing the `k` distance requirement between the start indices of the chosen subarrays.
*   **Lexicographical order:** Not handling ties in sums correctly when filling `bestRight` (using `>=` is crucial for the smallest indices).
*   **Boundary conditions for `mid`:** Incorrectly defining the range for the middle subarray's starting index.
*   **Not precomputing `bestLeft` and `bestRight`:** Trying to find the best left/right subarrays on the fly for each `mid`, leading to a much slower solution.

## Complexity Analysis
*   **Time:** O(N) - The initial calculation of `sums` takes O(N). The `bestLeft` and `bestRight` arrays are computed in O(N). The final loop to find the maximum sum iterates through possible middle subarray start indices, which is also O(N). Therefore, the total time complexity is O(N).
*   **Space:** O(N) - We use three auxiliary arrays: `sums`, `bestLeft`, and `bestRight`, each of size approximately N.

## Commented Code
```java
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        
        int sum = 0; // Initialize sum for the first window
        int n = nums.length; // Get the total number of elements in the input array
        
        // Calculate the sum of the first window of size k
        for(int i = 0; i < k; i++) {
            sum += nums[i];
        }
        
        // sums[i] will store the sum of the subarray of length k starting at index i
        int[] sums = new int[n - k + 1]; 
        sums[0] = sum; // Store the sum of the first window
        
        int index = 1; // Index for the sums array
        // Use a sliding window to calculate sums for all possible subarrays of length k
        for(int i = k; i < n; i++) {
            // Update sum by subtracting the element leaving the window and adding the element entering the window
            sum = sums[index - 1] + nums[i] - nums[i - k]; 
            sums[index] = sum; // Store the calculated sum
            index++; // Move to the next position in the sums array
        }
        
        int m = sums.length; // Number of possible subarrays of length k
        
        // bestLeft[i] stores the starting index of the subarray with the maximum sum among subarrays starting from index 0 up to index i in the 'sums' array.
        int[] bestLeft = new int[m]; 
        bestLeft[0] = 0; // The best left subarray up to index 0 is at index 0 itself.
        // Iterate from the second element to find the best left subarray index for each position
        for(int i = 1; i < m; i++) {
            // If the current subarray sum is greater than the sum of the best left subarray found so far, update bestLeft[i] to the current index.
            if(sums[i] > sums[bestLeft[i - 1]]) {
                bestLeft[i] = i;
            } else {
                // Otherwise, the best left subarray up to this point is the same as the best one found at the previous index.
                bestLeft[i] = bestLeft[i - 1];
            }
        }
        
        // bestRight[i] stores the starting index of the subarray with the maximum sum among subarrays starting from index i up to index m-1 in the 'sums' array.
        int[] bestRight = new int[m]; 
        bestRight[m - 1] = m - 1; // The best right subarray from the last index is at the last index itself.
        // Iterate backwards from the second to last element to find the best right subarray index for each position
        for(int i = m - 2; i >= 0; i--) {
            // If the current subarray sum is greater than or equal to the sum of the best right subarray found so far, update bestRight[i] to the current index.
            // Using '>=' ensures we pick the lexicographically smallest index in case of ties.
            if(sums[i] >= sums[bestRight[i + 1]]) {
                bestRight[i] = i;
            } else {
                // Otherwise, the best right subarray from this point onwards is the same as the best one found at the next index.
                bestRight[i] = bestRight[i + 1];
            }
        }
        
        int[] ans = new int[3]; // Array to store the starting indices of the three subarrays
        int maxSum = 0; // Variable to store the maximum total sum found so far
        
        // Iterate through all possible starting indices 'mid' for the middle subarray.
        // The middle subarray must start at an index 'mid' such that there's enough space for a left subarray (k elements before) and a right subarray (k elements after).
        // So, mid must be at least k and at most m - 1 - k.
        for(int mid = k; mid < m - k; mid++) { 
            // The left subarray must start at an index 'l' such that l <= mid - k.
            // We use bestLeft[mid - k] to get the index of the best possible left subarray.
            int l = bestLeft[mid - k]; 
            // The right subarray must start at an index 'r' such that r >= mid + k.
            // We use bestRight[mid + k] to get the index of the best possible right subarray.
            int r = bestRight[mid + k];
            
            // Calculate the total sum for the current triplet of subarrays (left, middle, right)
            int currSum = sums[mid] + sums[l] + sums[r];
            
            // If the current total sum is greater than the maximum sum found so far, update maxSum and the answer indices.
            if(currSum > maxSum) {
                maxSum = currSum; // Update the maximum sum
                ans[0] = l;       // Store the index of the best left subarray
                ans[1] = mid;     // Store the index of the middle subarray
                ans[2] = r;       // Store the index of the best right subarray
            }
        }
        
        return ans; // Return the array containing the starting indices of the three subarrays
    }
}
```

## Interview Tips
*   **Explain the Precomputation:** Clearly articulate why pre-calculating `sums`, `bestLeft`, and `bestRight` is crucial for an efficient solution.
*   **Handle Edge Cases and Constraints:** Discuss how `k` relates to `n` and how the loops for `mid` are bounded to ensure non-overlapping subarrays. Mention the importance of `>=` for `bestRight` for lexicographical order.
*   **Walk Through an Example:** Use a small example array and `k` to trace the `sums`, `bestLeft`, and `bestRight` arrays, and then the final loop. This demonstrates your understanding.
*   **Discuss Alternatives (Briefly):** You could briefly mention that a brute-force O(N^3) or O(N^2) approach exists but is too slow, highlighting the need for optimization.

## Revision Checklist
- [ ] Understand the problem: find 3 non-overlapping subarrays of length `k` with max sum.
- [ ] Precompute sums of all `k`-length subarrays using sliding window.
- [ ] Precompute `bestLeft` array for max sum subarray to the left.
- [ ] Precompute `bestRight` array for max sum subarray to the right.
- [ ] Iterate through possible middle subarray positions.
- [ ] Use `bestLeft` and `bestRight` to find optimal left and right subarrays for each middle.
- [ ] Track the maximum total sum and corresponding indices.
- [ ] Ensure non-overlapping conditions are met by loop bounds and index lookups.
- [ ] Consider lexicographical order for ties (using `>=` in `bestRight`).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Maximum Sum Subarray (Kadane's Algorithm)
*   Maximum Sum of Two Non-Overlapping Subarrays
*   Find K-th Smallest Sum of a Matrix With Sorted Rows

## Tags
`Array` `Dynamic Programming` `Sliding Window` `Greedy`
