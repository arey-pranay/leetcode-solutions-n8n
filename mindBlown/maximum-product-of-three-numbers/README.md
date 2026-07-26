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
        int a = -1001, b = a, c = b; //largest 3 numbers
        int X = 1001, Y=X; // smallest 2 numbers
        for(int num : nums){
            int pa = a, pb = b, px = X;//previous values of a,b, and X. for comparison
            a = Math.max(a,num);
            b = Math.max(b,Math.min(pa,num));
            c = Math.max(c,Math.min(pb,num)); 
            
            X = Math.min(X,num);
            Y = Math.min(Y,Math.max(px,num));
        }
        return Math.max(a*b*c, a*X*Y);
    }
}

```

---

---
## Quick Revision
Given an array of integers, find the maximum product achievable by multiplying any three numbers from the array.
The solution involves finding the three largest numbers and the two smallest numbers to cover all potential maximum product scenarios.

## Intuition
The maximum product of three numbers can arise from two main scenarios:
1. The product of the three largest positive numbers.
2. The product of the two smallest (most negative) numbers and the largest positive number. This is because the product of two negative numbers is positive, and if these negative numbers have large absolute values, their product could be significant.

## Algorithm
1. Initialize variables to track the three largest numbers (`a`, `b`, `c`) and the two smallest numbers (`X`, `Y`). Set `a`, `b`, `c` to a very small negative number (e.g., -1001, as per constraints) and `X`, `Y` to a very large positive number (e.g., 1001).
2. Iterate through the input array `nums`.
3. For each `num` in `nums`:
    a. **Update largest numbers:**
        i. If `num` is greater than `a`, then `c` becomes `b`, `b` becomes `a`, and `a` becomes `num`.
        ii. Else if `num` is greater than `b`, then `c` becomes `b`, and `b` becomes `num`.
        iii. Else if `num` is greater than `c`, then `c` becomes `num`.
    b. **Update smallest numbers:**
        i. If `num` is smaller than `X`, then `Y` becomes `X`, and `X` becomes `num`.
        ii. Else if `num` is smaller than `Y`, then `Y` becomes `num`.
4. After iterating through all numbers, calculate two potential maximum products:
    a. Product of the three largest numbers: `a * b * c`
    b. Product of the two smallest numbers and the largest number: `a * X * Y`
5. Return the maximum of these two products.

## Concept to Remember
*   **Handling Negative Numbers:** The presence of negative numbers significantly impacts the maximum product. The product of two negative numbers is positive.
*   **Extremes Matter:** For finding maximum or minimum products, the numbers with the largest magnitudes (both positive and negative) are crucial.
*   **Edge Cases:** Consider arrays with all positive, all negative, or mixed numbers.

## Common Mistakes
*   **Only considering the three largest numbers:** This fails when the two smallest (most negative) numbers multiplied by the largest positive number yield a greater product.
*   **Incorrectly updating the smallest/largest numbers:** Off-by-one errors or incorrect logic when shifting values can lead to wrong tracking.
*   **Not initializing variables properly:** Using default values like 0 might not work if the array contains only negative numbers or numbers close to zero.
*   **Integer Overflow:** While not explicitly an issue with the given constraints and return type, in general, be mindful of potential overflow when multiplying large numbers.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the array once.
- Space: O(1) - reason: We use a constant amount of extra space for variables regardless of the input size.

## Commented Code
```java
class Solution {
    public int maximumProduct(int[] nums) {
        // Initialize variables to store the three largest numbers.
        // Set to a value smaller than any possible input number (-1000 is the constraint).
        int a = -1001, b = a, c = b; // a will be the largest, b the second largest, c the third largest.

        // Initialize variables to store the two smallest numbers.
        // Set to a value larger than any possible input number (1000 is the constraint).
        int X = 1001, Y = X; // X will be the smallest, Y the second smallest.

        // Iterate through each number in the input array.
        for (int num : nums) {
            // Store previous values of a, b, and X to correctly update them.
            int pa = a, pb = b, px = X;

            // Update the three largest numbers.
            // If the current number is greater than the largest (a),
            // shift a to b, b to c, and set a to the current number.
            if (num > a) {
                c = b;
                b = a;
                a = num;
            }
            // Else if the current number is greater than the second largest (b) but not the largest,
            // shift b to c and set b to the current number.
            else if (num > b) {
                c = b;
                b = num;
            }
            // Else if the current number is greater than the third largest (c) but not the top two,
            // set c to the current number.
            else if (num > c) {
                c = num;
            }

            // Update the two smallest numbers.
            // If the current number is smaller than the smallest (X),
            // shift X to Y and set X to the current number.
            if (num < X) {
                Y = X;
                X = num;
            }
            // Else if the current number is smaller than the second smallest (Y) but not the smallest,
            // set Y to the current number.
            else if (num < Y) {
                Y = num;
            }
        }

        // The maximum product can be either the product of the three largest numbers
        // or the product of the two smallest (most negative) numbers and the largest number.
        // Return the maximum of these two possibilities.
        return Math.max(a * b * c, a * X * Y);
    }
}
```

## Interview Tips
*   **Explain the two scenarios:** Clearly articulate why you need to consider both the three largest and the two smallest with the largest.
*   **Discuss edge cases:** Mention how your initialization handles arrays with all positive, all negative, or mixed numbers.
*   **Trace an example:** Walk through a small example array (e.g., `[-100, -2, -3, 1, 2, 3]`) to demonstrate how your variables are updated.
*   **Ask about constraints:** If not provided, ask about the range of numbers and array size to confirm your approach and consider potential overflow issues.

## Revision Checklist
- [ ] Understand the problem statement.
- [ ] Identify the two key scenarios for maximum product.
- [ ] Implement logic to track the three largest numbers.
- [ ] Implement logic to track the two smallest numbers.
- [ ] Handle initialization correctly for extreme values.
- [ ] Compare the two potential maximum products.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Maximum Product Subarray
*   Find Minimum in Rotated Sorted Array
*   Third Maximum Number

## Tags
`Array` `Math` `Sorting`
