# Maximum Product Of Three Numbers

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Math` `Sorting`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maximumProduct(int[] nums) {
        int a = -1001, b = a, c = b;
        int X = 1001, Y=X;
        for(int num : nums){
            int p1 = a, p2 = b, p3 = X;
            a = Math.max(a,num);
            b = Math.max(b,Math.min(p1,num));
            c = Math.max(c,Math.min(p2,num)); 
            X = Math.min(X,num);
            Y = Math.min(Y,Math.max(p3,num));
        }
        return Math.max(a*b*c, a*X*Y);
    }
}

```

---

---
## Quick Revision
Given an array of integers, find the maximum product achievable by multiplying any three numbers from the array.
The solution involves finding the three largest numbers and the two smallest numbers to cover all possible maximum product scenarios.

## Intuition
The maximum product of three numbers can arise from two main scenarios:
1. The product of the three largest positive numbers.
2. The product of the two smallest (most negative) numbers and the largest positive number. This is because the product of two negative numbers is positive, and if these negative numbers are very small (large in absolute value), their product could be very large.

## Algorithm
1. Initialize variables to track the three largest numbers (`a`, `b`, `c`) and the two smallest numbers (`X`, `Y`). Set `a`, `b`, `c` to a very small negative number (e.g., -1001, as per constraints) and `X`, `Y` to a very large positive number (e.g., 1001).
2. Iterate through the input array `nums`.
3. For each number `num`:
    a. Update the three largest numbers:
        - If `num` is greater than `a`, then `c` becomes `b`, `b` becomes `a`, and `a` becomes `num`.
        - Else if `num` is greater than `b`, then `c` becomes `b`, and `b` becomes `num`.
        - Else if `num` is greater than `c`, then `c` becomes `num`.
    b. Update the two smallest numbers:
        - If `num` is smaller than `X`, then `Y` becomes `X`, and `X` becomes `num`.
        - Else if `num` is smaller than `Y`, then `Y` becomes `num`.
4. After iterating through all numbers, calculate two potential maximum products:
    a. `a * b * c` (product of the three largest numbers).
    b. `a * X * Y` (product of the largest number and the two smallest numbers).
5. Return the maximum of these two products.

## Concept to Remember
*   **Handling Negative Numbers:** The product of two negative numbers is positive. This is crucial for identifying the second potential maximum product.
*   **Extremes Matter:** The maximum product will always involve the largest positive numbers or a combination of the largest positive and smallest negative numbers.
*   **Single Pass Optimization:** It's possible to find the required numbers in a single pass through the array without explicit sorting.

## Common Mistakes
*   **Forgetting the negative number case:** Only considering the product of the three largest numbers and ignoring the possibility of two small negative numbers yielding a larger product.
*   **Incorrectly updating tracking variables:** Mishandling the logic for updating the largest/smallest numbers, leading to incorrect values being stored.
*   **Not initializing tracking variables properly:** Using default values that might be within the input range, leading to incorrect comparisons.
*   **Off-by-one errors in comparisons:** Using `<` instead of `<=` or vice-versa when updating tracking variables.

## Complexity Analysis
- Time: O(n) - The algorithm iterates through the input array `nums` exactly once. The operations inside the loop (comparisons and assignments) take constant time.
- Space: O(1) - The algorithm uses a fixed number of variables (`a`, `b`, `c`, `X`, `Y`, `p1`, `p2`, `p3`) regardless of the input array size.

## Commented Code
```java
class Solution {
    public int maximumProduct(int[] nums) {
        // Initialize variables to track the three largest numbers.
        // 'a' will store the largest, 'b' the second largest, 'c' the third largest.
        // Initialize to a value smaller than any possible input number according to constraints.
        int a = -1001, b = a, c = b;

        // Initialize variables to track the two smallest numbers.
        // 'X' will store the smallest, 'Y' the second smallest.
        // Initialize to a value larger than any possible input number according to constraints.
        int X = 1001, Y=X;

        // Iterate through each number in the input array.
        for(int num : nums){
            // Store current values of a, b, and X before potential updates.
            // This is a common pattern to correctly shift values when finding top/bottom K elements.
            int p1 = a, p2 = b, p3 = X;

            // Update the three largest numbers:
            // If the current number 'num' is greater than the current largest 'a'.
            if(num > a){
                // Shift 'a' to 'b', 'b' to 'c', and 'a' becomes 'num'.
                c = b;
                b = a;
                a = num;
            }
            // Else if 'num' is not the largest but is greater than the second largest 'b'.
            else if(num > b){
                // Shift 'b' to 'c', and 'b' becomes 'num'. 'a' remains unchanged.
                c = b;
                b = num;
            }
            // Else if 'num' is not the largest or second largest, but is greater than the third largest 'c'.
            else if(num > c){
                // 'c' becomes 'num'. 'a' and 'b' remain unchanged.
                c = num;
            }

            // Update the two smallest numbers:
            // If the current number 'num' is smaller than the current smallest 'X'.
            if(num < X){
                // Shift 'X' to 'Y', and 'X' becomes 'num'.
                Y = X;
                X = num;
            }
            // Else if 'num' is not the smallest but is smaller than the second smallest 'Y'.
            else if(num < Y){
                // 'Y' becomes 'num'. 'X' remains unchanged.
                Y = num;
            }
        }
        // The maximum product can be either:
        // 1. The product of the three largest numbers (a * b * c).
        // 2. The product of the largest number and the two smallest numbers (a * X * Y),
        //    which is relevant if X and Y are negative, making their product positive and large.
        // Return the maximum of these two possibilities.
        return Math.max(a*b*c, a*X*Y);
    }
}
```

## Interview Tips
*   **Explain the two scenarios:** Clearly articulate why you need to consider both the three largest numbers and the largest number with the two smallest numbers.
*   **Discuss edge cases:** Mention what happens with arrays containing all positive numbers, all negative numbers, or a mix.
*   **Justify your variable initialization:** Explain why you chose specific initial values for your tracking variables (e.g., -1001 and 1001 based on constraints).
*   **Walk through an example:** Use a small array like `[-1, -2, -3, 4, 5]` to demonstrate how your variables are updated.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the two key scenarios for maximum product.
- [ ] Implement logic to track the three largest and two smallest numbers efficiently.
- [ ] Handle initialization of tracking variables correctly.
- [ ] Compare the two potential maximum products.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the intuition and algorithm.

## Similar Problems
*   [164. Maximum Gap](https://leetcode.com/problems/maximum-gap/)
*   [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)
*   [15. 3Sum](https://leetcode.com/problems/3sum/)

## Tags
`Array` `Math` `Sorting`
