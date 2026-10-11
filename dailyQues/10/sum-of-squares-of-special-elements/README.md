# Sum Of Squares Of Special Elements

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Enumeration`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int sumOfSquares(int[] nums) {
        int n =  nums.length;
        int sum=0;
        for(int i=0;i<n;i++) if(n%(i+1)==0) sum += nums[i]*nums[i];
        return sum;
    }
}
```

---

---
## Quick Revision
The problem asks to sum the squares of elements in an array `nums` where the element's index `i` (1-based) is a divisor of the array's length `n`. We iterate through the array and check the divisibility condition.

## Intuition
The core of the problem lies in identifying "special" elements. A special element is defined by its index (1-based) being a divisor of the array's length. Once identified, we simply need to square these elements and sum them up. The most straightforward way to do this is to iterate through the array, check the condition for each element's index, and accumulate the sum if the condition is met.

## Algorithm
1. Get the length of the input array `nums` and store it in a variable `n`.
2. Initialize a variable `sum` to 0. This variable will store the cumulative sum of squares of special elements.
3. Iterate through the array `nums` using a loop. The loop variable `i` will represent the 0-based index of the current element.
4. Inside the loop, for each element at index `i`, check if `n` is divisible by `(i + 1)`. The `(i + 1)` is used because the problem defines "special" elements based on their 1-based index.
5. If `n % (i + 1) == 0` (meaning `(i + 1)` is a divisor of `n`), then the element `nums[i]` is a special element.
6. Square the special element `nums[i]` (i.e., `nums[i] * nums[i]`) and add it to the `sum`.
7. After the loop finishes iterating through all elements, return the final `sum`.

## Concept to Remember
*   **Array Traversal:** Iterating through elements of an array to perform operations.
*   **Modulo Operator (`%`):** Used to find the remainder of a division, essential for checking divisibility.
*   **1-based vs. 0-based Indexing:** Understanding the difference and how to convert between them when problem statements use one convention and programming languages use another.

## Common Mistakes
*   **Off-by-one errors:** Forgetting to add 1 to the loop index `i` when checking the divisibility condition, leading to incorrect identification of special elements.
*   **Integer Overflow:** While unlikely for this specific problem given typical integer constraints, in general, squaring large numbers can lead to overflow if not handled with appropriate data types.
*   **Misinterpreting "special element":** Confusing the element's value with its index when applying the divisibility rule.

## Complexity Analysis
- Time: O(n) - The algorithm iterates through the array once, performing constant time operations for each element.
- Space: O(1) - The algorithm uses a constant amount of extra space for variables like `n` and `sum`, regardless of the input array size.

## Commented Code
```java
class Solution {
    public int sumOfSquares(int[] nums) {
        // Get the length of the input array.
        int n =  nums.length;
        // Initialize a variable to store the sum of squares of special elements.
        int sum=0;
        // Iterate through the array using a 0-based index 'i'.
        for(int i=0;i<n;i++) {
            // Check if the array length 'n' is divisible by the 1-based index (i+1).
            if(n%(i+1)==0) {
                // If it is divisible, the element nums[i] is special.
                // Square the special element and add it to the sum.
                sum += nums[i]*nums[i];
            }
        }
        // Return the total sum of squares of all special elements.
        return sum;
    }
}
```

## Interview Tips
*   **Clarify Indexing:** Explicitly ask the interviewer if the "index" in the problem refers to 0-based or 1-based indexing to avoid misinterpretation.
*   **Walk Through Example:** Before coding, walk through a small example array with the interviewer to demonstrate your understanding of the "special element" condition.
*   **Edge Cases:** Consider edge cases like an empty array or an array with a single element. The current code handles these gracefully.

## Revision Checklist
- [ ] Understand the definition of a "special element" based on index divisibility.
- [ ] Correctly implement the 1-based index check using `(i + 1)`.
- [ ] Ensure the sum is accumulated correctly by squaring the element.
- [ ] Verify time and space complexity.

## Similar Problems
*   Sum of Digits of String With Characters (similar in that it involves processing elements based on a condition)
*   Find the Winner of the Circular Game (involves index manipulation and divisibility concepts)

## Tags
`Array` `Math`
