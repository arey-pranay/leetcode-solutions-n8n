# Split Array Largest Sum

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Dynamic Programming` `Greedy` `Prefix Sum`  
**Time:** O(n log maxSum)  
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
Split an array into k non-empty subarrays such that the largest sum is minimized.
We solve this problem using a binary search approach to find the minimum largest sum.

## Intuition
The key insight here is that if we can break an array into k non-empty subarrays with the largest sum being `maxSum`, then we can also break it into `k-1` subarrays with the largest sum being `maxSum+1`. This is because we can always merge two subarrays with the largest sum being `maxSum+1` to get a single subarray with the largest sum being `maxSum`. Therefore, the minimum largest sum is the smallest number that can be broken into k non-empty subarrays.

## Algorithm
1. Initialize the low and high values for the binary search, where low is the maximum possible largest sum and high is the total sum of the array.
2. Calculate the low and high values based on the array and the number of subarrays k.
3. Perform a binary search between low and high to find the minimum largest sum.
4. In each iteration, calculate the mid value and check if it is possible to break the array into k non-empty subarrays with the largest sum being mid.
5. If it is possible, update the high value to mid-1. Otherwise, update the low value to mid+1.

## Concept to Remember
* Binary search: a fast algorithm for finding an item from a large data set
* Min/Max problems: problems where we need to find the minimum or maximum value of a function

## Common Mistakes
* Not understanding the concept of binary search and its application in this problem
* Not considering the case where the array can be broken into k non-empty subarrays with a larger sum than the minimum largest sum
* Not updating the low and high values correctly in the binary search loop

## Complexity Analysis
- Time: O(n log maxSum) where n is the number of elements in the array
- Space: O(1) as we only use a constant amount of space

## Commented Code
```java
class Solution {
    public int splitArray(int[] nums, int k) {
        // Calculate the low and high values based on the array and the number of subarrays k
        int low = 0, high = 0;
        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }
        int res = high;

        // Perform a binary search between low and high to find the minimum largest sum
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if it is possible to break the array into k non-empty subarrays with the largest sum being mid
            if (canBreak(nums, k, mid)) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return res;
    }

    public boolean canBreak(int[] nums, int k, int maxSum) {
        // Initialize the sum and the number of subarrays
        int sum = 0;
        int count = 0;

        // Iterate through the array and check if it is possible to break the array into k non-empty subarrays with the largest sum being maxSum
        for (int num : nums) {
            sum += num;
            if (sum > maxSum) {
                sum = num;
                count++;
                if (count == k) return false;
            }
        }

        return true;
    }
}
```

## Interview Tips
* Make sure to understand the problem and the concept of binary search before starting to code
* Use a clear and concise code structure to make it easy to understand
* Practice solving similar problems to improve your skills and confidence

## Revision Checklist
- [ ] Understand the problem and the concept of binary search
- [ ] Write a clear and concise code structure
- [ ] Practice solving similar problems to improve skills and confidence

## Similar Problems
* 410. Split Array Largest Sum (Medium)
* 535. Uno (Medium)
* 1046. Delete String to Make Substrings Unique (Medium)

## Tags
`Array` `Hash Map` `Binary Search` `Greedy Algorithm` `Dynamic Programming`
