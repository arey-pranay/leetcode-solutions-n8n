# Maximum Alternating Sum Of Squares

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Greedy` `Sorting`  
**Time:** O(N log N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        
        long arr[] = new long[n];
        for(int i = 0;i<n;i++) arr[i] = nums[i]*nums[i];
        Arrays.sort(arr);
        
        long ans =0;
        for(int i=0;i<n;i++) if(i<n/2) ans -= arr[i]; else ans += arr[i];
        return ans;
    }
}
```

---

---
## Quick Revision
The problem asks to find the maximum alternating sum of squares of elements in an array.
This is solved by sorting the squares and then applying the alternating sum to the smallest and largest squares.

## Intuition
The core idea is that to maximize an alternating sum (e.g., `a - b + c - d + ...`), we want the positive terms to be as large as possible and the negative terms to be as small as possible. Since we are dealing with squares, which are always non-negative, squaring the numbers preserves their relative order for positive numbers. To maximize the sum, we should assign the largest squares to the positive positions and the smallest squares to the negative positions.

## Algorithm
1. Create a new array `squares` of the same size as `nums`.
2. Iterate through `nums` and populate `squares` with the square of each element (`nums[i] * nums[i]`).
3. Sort the `squares` array in ascending order.
4. Initialize a variable `maxSum` to 0.
5. Iterate through the sorted `squares` array.
6. If the current index `i` is less than `n/2` (where `n` is the length of the array), subtract `squares[i]` from `maxSum`. These will be the smallest squares, contributing negatively.
7. If the current index `i` is greater than or equal to `n/2`, add `squares[i]` to `maxSum`. These will be the largest squares, contributing positively.
8. Return `maxSum`.

## Concept to Remember
*   **Greedy Approach:** Making locally optimal choices (assigning smallest squares to negative terms, largest to positive) leads to a globally optimal solution.
*   **Sorting:** Essential for identifying the smallest and largest elements efficiently.
*   **Alternating Sum Properties:** Understanding how to maximize such sums by strategically assigning values.

## Common Mistakes
*   Not squaring the numbers before sorting and applying the alternating sum.
*   Incorrectly assigning positive and negative signs in the alternating sum (e.g., assigning largest to negative terms).
*   Off-by-one errors when dividing the sorted array into "positive" and "negative" halves.
*   Integer overflow if intermediate square calculations are not handled with `long`.

## Complexity Analysis
- Time: O(N log N) - reason: Dominated by the sorting step of the squares array.
- Space: O(N) - reason: For storing the `squares` array.

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for sorting functionality

class Solution {
    public long maxAlternatingSum(int[] nums) { // Define the method to calculate the maximum alternating sum of squares
        int n = nums.length; // Get the number of elements in the input array
        
        long arr[] = new long[n]; // Declare a long array 'arr' to store the squares of numbers, using long to prevent overflow
        for(int i = 0; i < n; i++) { // Iterate through the input array 'nums'
            arr[i] = (long)nums[i] * nums[i]; // Calculate the square of the current number and store it in 'arr', casting to long
        }
        Arrays.sort(arr); // Sort the 'arr' array in ascending order
        
        long ans = 0; // Initialize a long variable 'ans' to store the final alternating sum
        for(int i = 0; i < n; i++) { // Iterate through the sorted squares array
            if(i < n / 2) { // If the current index is in the first half of the array (i.e., smaller squares)
                ans -= arr[i]; // Subtract the square from 'ans' (these will be the negative terms in the alternating sum)
            } else { // If the current index is in the second half of the array (i.e., larger squares)
                ans += arr[i]; // Add the square to 'ans' (these will be the positive terms in the alternating sum)
            }
        }
        return ans; // Return the calculated maximum alternating sum of squares
    }
}
```

## Interview Tips
*   Clearly explain your greedy strategy: why assigning smallest squares to negative terms and largest to positive terms maximizes the sum.
*   Mention the potential for integer overflow when squaring numbers and how using `long` mitigates this.
*   Walk through a small example (e.g., `[1, 2, 3]`) to demonstrate how the algorithm works.
*   Be prepared to discuss the time and space complexity of your solution.

## Revision Checklist
- [ ] Understand the problem statement: maximize `s1^2 - s2^2 + s3^2 - s4^2 ...`
- [ ] Realize squares are always non-negative.
- [ ] Identify that largest squares should be positive terms, smallest should be negative.
- [ ] Implement squaring of all elements.
- [ ] Sort the squared elements.
- [ ] Apply alternating sum correctly to sorted squares.
- [ ] Handle potential integer overflow with `long`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Maximum Alternating Sum of Subsequence
*   Sort Array By Parity II
*   Partition Array Into Two Arrays to Minimize Sum Difference

## Tags
`Array` `Sorting` `Greedy` `Math`
