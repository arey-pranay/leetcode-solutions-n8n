# Smallest Index With Digit Sum Equal To Index

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Math`  
**Time:** O(N * D)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int smallestIndex(int[] nums) {
        for(int i=0;i<nums.length;i++) if(digitSum(nums[i])==i) return i;
        return -1;
    }
    public int digitSum(int num){
        int sum=0;
        while(num>0){
            int rem = num%10;
            num/=10;
            sum += rem;
        }
        return sum;
    }
}
```

---

---
## Quick Revision
Find the smallest index `i` where the sum of digits of `nums[i]` equals `i`.
Iterate through the array, calculate the digit sum for each element, and return the first index that satisfies the condition.

## Intuition
The problem asks for a specific property to hold at a particular index: the index itself must be equal to the sum of the digits of the number stored at that index. This suggests a direct, brute-force approach. We can simply check every index one by one. Since we need the *smallest* such index, the first one we find during a linear scan from the beginning of the array will be our answer. If no such index exists, we should return -1.

## Algorithm
1. Initialize a loop that iterates through the input array `nums` from index `0` to `nums.length - 1`.
2. For each index `i`:
    a. Calculate the sum of the digits of the number `nums[i]`. This can be done by repeatedly taking the number modulo 10 to get the last digit, adding it to a running sum, and then dividing the number by 10 until the number becomes 0.
    b. Compare the calculated digit sum with the current index `i`.
    c. If the digit sum is equal to `i`, then we have found the smallest such index. Return `i` immediately.
3. If the loop completes without finding any index that satisfies the condition, it means no such index exists. Return `-1`.

## Concept to Remember
*   **Iterative Digit Sum Calculation:** Understanding how to extract and sum digits of an integer using modulo and division operations.
*   **Linear Scan for Smallest Value:** The strategy of iterating from the beginning and returning the first match guarantees finding the smallest index.
*   **Edge Case Handling:** Recognizing the need to return a specific value (like -1) when no solution is found.

## Common Mistakes
*   **Incorrect Digit Sum Logic:** Errors in the `while` loop condition, modulo, or division operations when calculating the digit sum.
*   **Off-by-One Errors:** Incorrect loop bounds or index comparisons.
*   **Forgetting the -1 Return Case:** Not handling the scenario where no index satisfies the condition.
*   **Inefficient Digit Sum:** While not a major issue for this problem's constraints, using string conversion for digit sum can be less efficient than arithmetic operations.

## Complexity Analysis
*   **Time:** O(N * D), where N is the number of elements in `nums` and D is the maximum number of digits in any number in `nums`. The outer loop runs N times, and the `digitSum` function takes time proportional to the number of digits in the number. In the worst case, D can be considered logarithmic with respect to the maximum value in `nums`.
*   **Space:** O(1), as we are only using a few variables to store the sum and loop counters, which do not depend on the input size.

## Commented Code
```java
class Solution {
    // This is the main method that finds the smallest index.
    public int smallestIndex(int[] nums) {
        // Iterate through each index 'i' of the input array 'nums'.
        for(int i = 0; i < nums.length; i++) {
            // For the current index 'i', calculate the sum of its digits.
            // Then, check if this digit sum is equal to the index 'i' itself.
            if(digitSum(nums[i]) == i) {
                // If the condition is met, we've found the smallest such index.
                // Return this index immediately.
                return i;
            }
        }
        // If the loop finishes without finding any index that satisfies the condition,
        // it means no such index exists. Return -1 as per the problem statement.
        return -1;
    }

    // This helper method calculates the sum of digits of a given number.
    public int digitSum(int num){
        // Initialize a variable 'sum' to store the sum of digits.
        int sum = 0;
        // Continue the loop as long as the number 'num' is greater than 0.
        while(num > 0){
            // Get the last digit of 'num' using the modulo operator (%).
            int rem = num % 10;
            // Add this last digit to our running 'sum'.
            sum += rem;
            // Remove the last digit from 'num' by integer division (/).
            num /= 10;
        }
        // Once all digits have been processed, return the total 'sum'.
        return sum;
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask about the range of numbers in `nums` and the size of the array. This helps in understanding if the O(N*D) time complexity is acceptable.
*   **Explain the Helper Function:** Clearly articulate the purpose and logic of the `digitSum` helper function.
*   **Walk Through Examples:** Use a small example array to trace the execution of your code, showing how you check each index and its digit sum.
*   **Discuss Edge Cases:** Mention how you handle the case where no such index is found (returning -1).

## Revision Checklist
- [ ] Understand the problem statement: find smallest index `i` where `digitSum(nums[i]) == i`.
- [ ] Implement a function to calculate the sum of digits of a number.
- [ ] Iterate through the array from index 0.
- [ ] For each index, call the digit sum function.
- [ ] Compare the digit sum with the current index.
- [ ] Return the index if the condition is met.
- [ ] Return -1 if no such index is found after checking all elements.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Sum of Digits in Base K (LeetCode 1837)
*   Count Number of Digits in Base K (LeetCode 2180)
*   Find the Difference Between Two Arrays (LeetCode 2006) - *conceptually similar in iterating and checking conditions*

## Tags
`Array` `Math` `Iteration`
