# 3sum

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Sorting`  
**Time:** O(n^2)  
**Space:** O(log n)

---

## Solution (java)

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for(int i=0;i<n;i++){
            if(i>0 && nums[i]==nums[i-1]) continue;
            int j = i+1;
            int k = n-1;
            while(j<k){
                int sum = nums[i] + nums[j] + nums[k];
                if(sum > 0) k--;
                else if(sum < 0) j++;
                else {
                    List<Integer> temp  = new ArrayList<>();
                    temp.add(nums[i]);temp.add(nums[j++]);temp.add(nums[k--]);
                    ans.add(temp);
                    while(j<k && nums[k]==nums[k+1]) k--;
                }
                
            }
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Find all unique triplets in an array that sum up to zero.
Solve by sorting the array and using a two-pointer approach with a fixed element.

## Intuition
The core idea is to reduce the 3Sum problem to a 2Sum problem. If we fix one number `nums[i]`, we then need to find two other numbers `nums[j]` and `nums[k]` such that `nums[j] + nums[k] = -nums[i]`. Sorting the array is crucial because it allows us to efficiently find these pairs using the two-pointer technique and also helps in handling duplicates. The two-pointer approach (one starting from `i+1` and the other from the end) works because if the sum is too large, we need a smaller number (decrement the right pointer), and if the sum is too small, we need a larger number (increment the left pointer).

## Algorithm
1. Initialize an empty list `ans` to store the resulting triplets.
2. Sort the input array `nums` in ascending order. This is essential for the two-pointer approach and duplicate handling.
3. Iterate through the sorted array with a pointer `i` from the beginning up to `n-2` (where `n` is the length of the array).
    a. **Duplicate Handling for `nums[i]`**: If `i > 0` and `nums[i]` is the same as the previous element `nums[i-1]`, skip this iteration to avoid duplicate triplets.
    b. Initialize two pointers: `j` to `i + 1` (the element immediately after `nums[i]`) and `k` to `n - 1` (the last element of the array).
    c. Start a `while` loop that continues as long as `j < k`.
        i. Calculate the `sum` of `nums[i]`, `nums[j]`, and `nums[k]`.
        ii. If `sum > 0`: The sum is too large. To decrease it, move the right pointer `k` one step to the left (`k--`).
        iii. If `sum < 0`: The sum is too small. To increase it, move the left pointer `j` one step to the right (`j++`).
        iv. If `sum == 0`: A triplet is found!
            - Create a new list `temp` and add `nums[i]`, `nums[j]`, and `nums[k]` to it.
            - Add `temp` to the `ans` list.
            - **Duplicate Handling for `nums[j]` and `nums[k]`**: To avoid duplicate triplets involving `nums[j]` and `nums[k]`, increment `j` while `j < k` and `nums[j]` is equal to the next element `nums[j+1]`. Similarly, decrement `k` while `j < k` and `nums[k]` is equal to the previous element `nums[k-1]`.
            - After handling duplicates, increment `j` and decrement `k` to continue searching for new triplets.
4. Return the `ans` list.

## Concept to Remember
*   **Sorting**: Essential for efficient searching and duplicate removal.
*   **Two-Pointer Technique**: Efficiently finds pairs in a sorted array that sum to a target.
*   **Duplicate Handling**: Crucial for ensuring unique triplets in the output.
*   **Reducing Problem Complexity**: Transforming a 3-element problem into a 2-element problem.

## Common Mistakes
*   Not sorting the array, leading to inefficient searching and difficulty in handling duplicates.
*   Failing to handle duplicates correctly for `nums[i]`, `nums[j]`, and `nums[k]`, resulting in duplicate triplets in the output.
*   Incorrectly updating the `j` and `k` pointers after finding a valid triplet (e.g., not moving them at all or moving them too much).
*   Off-by-one errors in loop conditions or pointer initializations.
*   Not considering edge cases like empty arrays or arrays with fewer than three elements.

## Complexity Analysis
*   **Time**: O(n^2) - The dominant part is the nested loop structure. The outer loop runs `n` times, and the inner `while` loop (two pointers) runs at most `n` times for each iteration of the outer loop. Sorting takes O(n log n), but it's dominated by O(n^2).
*   **Space**: O(log n) or O(n) - This depends on the sorting algorithm used by the language's standard library. In Java, `Arrays.sort()` for primitives uses a dual-pivot quicksort, which has an average space complexity of O(log n) for the recursion stack. If we consider the space for the output list, it can be up to O(n) in the worst case (though for unique triplets, it's usually less).

## Commented Code
```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // Initialize the list to store the resulting triplets.
        List<List<Integer>> ans = new ArrayList<>();
        // Get the length of the input array.
        int n = nums.length;
        // Sort the array. This is crucial for the two-pointer approach and duplicate handling.
        Arrays.sort(nums);
        // Iterate through the array with the first pointer 'i'.
        // We only need to go up to n-2 because we need at least two more elements (j and k).
        for(int i = 0; i < n; i++){
            // Skip duplicate elements for 'nums[i]'.
            // If 'i' is not the first element and the current element is the same as the previous one,
            // we've already considered all triplets starting with this value, so continue.
            if(i > 0 && nums[i] == nums[i-1]) continue;
            // Initialize the second pointer 'j' to the element right after 'i'.
            int j = i + 1;
            // Initialize the third pointer 'k' to the last element of the array.
            int k = n - 1;
            // Use a two-pointer approach to find 'j' and 'k' such that nums[i] + nums[j] + nums[k] == 0.
            while(j < k){
                // Calculate the sum of the three elements.
                int sum = nums[i] + nums[j] + nums[k];
                // If the sum is greater than 0, it means the sum is too large.
                // To decrease the sum, we need a smaller number, so move the right pointer 'k' to the left.
                if(sum > 0) k--;
                // If the sum is less than 0, it means the sum is too small.
                // To increase the sum, we need a larger number, so move the left pointer 'j' to the right.
                else if(sum < 0) j++;
                // If the sum is exactly 0, we have found a triplet.
                else {
                    // Create a new list to store the current triplet.
                    List<Integer> temp  = new ArrayList<>();
                    // Add the three numbers to the temporary list.
                    temp.add(nums[i]);
                    // Add nums[j] and then increment 'j' to move to the next potential element.
                    temp.add(nums[j++]);
                    // Add nums[k] and then decrement 'k' to move to the next potential element.
                    temp.add(nums[k--]);
                    // Add the found triplet to the result list.
                    ans.add(temp);
                    // Skip duplicate elements for 'nums[k]' to avoid duplicate triplets.
                    // While 'j' is still less than 'k' and the current 'nums[k]' is the same as the next element,
                    // decrement 'k' to move past duplicates.
                    while(j < k && nums[k] == nums[k+1]) k--;
                    // Note: Duplicate handling for nums[j] is implicitly handled by the outer loop's duplicate check for nums[i]
                    // and by the fact that we increment j after adding a triplet. If we wanted to be explicit here,
                    // we could add: while(j < k && nums[j] == nums[j-1]) j++;
                    // However, the current logic is sufficient because the outer loop handles nums[i] duplicates,
                    // and the inner loop's advancement of j and k naturally moves past duplicates after a triplet is found.
                }
            }
        }
        // Return the list of unique triplets that sum to zero.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Sorting Rationale**: Clearly articulate why sorting is necessary for efficiency and duplicate handling.
*   **Walk Through Two Pointers**: Verbally explain how the `j` and `k` pointers move and why, especially the conditions for incrementing/decrementing them.
*   **Address Duplicates Explicitly**: Be prepared to explain your strategy for skipping duplicate elements for `nums[i]`, `nums[j]`, and `nums[k]` to ensure unique triplets.
*   **Consider Edge Cases**: Mention how your solution handles empty arrays or arrays with fewer than three elements (though the current code implicitly handles this by loop conditions).

## Revision Checklist
- [ ] Understand the problem: find unique triplets summing to zero.
- [ ] Recognize the reduction to 2Sum.
- [ ] Implement sorting.
- [ ] Implement the outer loop for the first element.
- [ ] Implement duplicate skipping for the first element.
- [ ] Implement the two-pointer approach for the remaining two elements.
- [ ] Correctly handle sum > 0, sum < 0, and sum == 0 cases.
- [ ] Implement duplicate skipping for the second and third elements after finding a triplet.
- [ ] Ensure correct pointer movement after finding a triplet.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
*   1. Two Sum
*   15. 3Sum
*   16. 3Sum Closest
*   18. 4Sum

## Tags
`Array` `Two Pointers` `Sorting` `Hash Table`
