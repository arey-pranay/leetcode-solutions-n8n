# 3sum

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Sorting`  
**Time:** O(n^2)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for(int i =0 ; i<n;i++){
            if(i>0 && nums[i]==nums[i-1])continue;
            int j = i+1;
            int k = n-1;
            while(j<k){
                int sum = nums[i]+nums[j]+nums[k];
                if(sum>0) k--;
                else if(sum < 0)j++;
                else{
                    ans.add(List.of(nums[i],nums[j++],nums[k--]));
                    while(j<k && nums[k]==nums[k+1])k--; // j aur k m se koi ek hi chalega check k liye
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
The problem is to find all unique triplets in the given array that sum up to zero. The solution involves sorting the array and using a two-pointer technique to find pairs of numbers that add up to the negative of the current number.

## Intuition
This approach works because by sorting the array, we can easily identify potential triplets that could add up to zero. We then use two pointers, one starting from the next element of the current number and one from the end of the array, to find pairs of numbers that sum up to the negative of the current number.

## Algorithm
1. Sort the given array in ascending order.
2. Initialize an empty list to store the unique triplets.
3. Iterate through the sorted array using three pointers: `i`, `j`, and `k`.
4. For each iteration, check if the current triplet is valid (sums up to zero).
5. If the sum is greater than 0, decrement the end pointer (`k`).
6. If the sum is less than 0, increment the start pointer (`j`).
7. If the sum is equal to 0, add the triplet to the result list and move both pointers.
8. Skip duplicates by checking if the current number is the same as the previous one.

## Concept to Remember
* Sorting can be used to simplify complex problems by bringing similar elements together.
* Two-pointer technique can be used to find pairs of numbers that meet a certain condition.
* Be careful with duplicate values and skip them accordingly.

## Common Mistakes
* Failing to sort the array, leading to incorrect results.
* Incorrectly implementing the two-pointer technique, resulting in inefficient solutions.
* Not skipping duplicates, causing unnecessary iterations.

## Complexity Analysis
- Time: O(n^2) - The time complexity is quadratic because we are using a nested loop to iterate through the array.
- Space: O(1) - We are only using a constant amount of space to store the pointers and the result list.

## Commented Code

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // Sort the array in ascending order
        Arrays.sort(nums);

        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();

        // Iterate through the sorted array using three pointers: i, j, and k
        for (int i = 0; i < n; i++) {
            // Skip duplicates by checking if the current number is the same as the previous one
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                // Calculate the sum of the current triplet
                int sum = nums[i] + nums[j] + nums[k];

                // If the sum is greater than 0, decrement the end pointer (k)
                if (sum > 0) k--;

                // If the sum is less than 0, increment the start pointer (j)
                else if (sum < 0) j++;

                // If the sum is equal to 0, add the triplet to the result list and move both pointers
                else {
                    ans.add(List.of(nums[i], nums[j++], nums[k--]));

                    // Skip duplicates by checking if the current number is the same as the next one (k)
                    while (j < k && nums[k] == nums[k + 1]) k--;
                }
            }
        }

        return ans;
    }
}
```

## Interview Tips
* Make sure to sort the array and use a two-pointer technique to find pairs of numbers that add up to the negative of the current number.
* Be careful with duplicate values and skip them accordingly.
* Practice implementing this solution multiple times to become more comfortable with it.

## Revision Checklist
- [ ] Sort the array in ascending order.
- [ ] Use a two-pointer technique to find pairs of numbers that sum up to the negative of the current number.
- [ ] Skip duplicates by checking if the current number is the same as the previous one or the next one.

## Similar Problems
* Two Sum: Find all unique pairs of numbers in an array that add up to a given target value.
* Four Sum: Find all unique quadruplets in an array that sum up to a given target value.
