# Maximum Product Of Two Digits

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Math` `Sorting`  
**Time:** O(log n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxProduct(int n) {
        int maxNum=0,maxProd=0;
        while(n!=0){
            int rem = n%10;
            maxProd = Math.max(maxProd,maxNum*rem);
            maxNum = Math.max(maxNum,rem);
            n/=10;
        }
        return maxProd;
    }
}
```

---

---

## Quick Revision
The problem asks to find the maximum product of two digits in a given integer.
This can be solved by iterating through each digit and keeping track of the maximum and second-maximum numbers.

## Intuition
The key insight here is that when we multiply two numbers, we want to maximize both the larger number (in this case `maxNum`) and its "complement" or smaller counterpart (`rem`). This way, we ensure that our product is maximized because multiplying by a smaller number will always yield a smaller result than multiplying by a larger one.

## Algorithm
1. Initialize variables: `maxNum` to store the maximum number seen so far, and `maxProd` to store the maximum product found.
2. Iterate through each digit of the input number:
	* Get the remainder (`rem`) when dividing by 10 (i.e., extract the last digit).
	* Update `maxProd` with the maximum of its current value and the product of `maxNum` and `rem`.
	* Update `maxNum` with the maximum of its current value and `rem`.
3. Return `maxProd`.

## Concept to Remember
* Multiplication properties: when multiplying numbers, maximizing both values will yield the largest result.
* Digit extraction: extracting individual digits from an integer is a common technique in number-related problems.

## Common Mistakes
* Failing to consider both maximum and second-maximum values separately (e.g., always assuming `maxNum` is the largest).
* Not properly updating `maxProd` when switching between the two maximum numbers.
* Ignoring edge cases where one of the digits is zero (in which case the product would be 0).

## Complexity Analysis
- Time: O(log n) - reason: iterating through each digit of the input number, and each operation takes constant time.
- Space: O(1) - reason: only a few extra variables are used.

## Commented Code
```java
class Solution {
    public int maxProduct(int n) {
        // Initialize variables to track maximum numbers
        int maxNum = 0; // maximum number seen so far
        int maxProd = 0; // maximum product found

        // Iterate through each digit of the input number
        while (n != 0) {
            // Get the remainder when dividing by 10 (i.e., extract the last digit)
            int rem = n % 10;

            // Update maxProd with the maximum of its current value and the product of maxNum and rem
            maxProd = Math.max(maxProd, maxNum * rem);

            // Update maxNum with the maximum of its current value and rem
            maxNum = Math.max(maxNum, rem);

            // Remove the last digit from n
            n /= 10;
        }

        // Return the maximum product found
        return maxProd;
    }
}
```

## Interview Tips
* Pay attention to both maximum and second-maximum values.
* Update `maxProd` correctly when switching between the two maximum numbers.
* Test edge cases, such as input with zeros.

## Revision Checklist
- [ ] Review multiplication properties and digit extraction techniques.
- [ ] Practice iterating through digits of an integer using remainder operations.
- [ ] Verify that maxProd is updated correctly in each iteration.

## Similar Problems
* Maximum Sum Of Two Digits (LeetCode)
* Maximum Product of Three Numbers (HackerRank)

## Tags
`Array` `Hash Map`
