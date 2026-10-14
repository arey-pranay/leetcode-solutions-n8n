# Maximize Sum Of Squares Of Digits

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Math` `Greedy`  
**Time:** O(1)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if(num*9 < sum) return "";
        
        StringBuilder ans = new StringBuilder("");
        int nines = sum/9; // since we need the biggest number, use as many 9s in the left as possible
        sum %= 9; // then append the remainder
        ans.append("9".repeat(nines));
        if(sum > 0) ans.append(sum);
        ans.append("0".repeat(num - ans.length())); // based on howmanyever digits are yet to be added, add 0s at the end
        
        return ans.toString();
    }
}


// start from left. take 9 if possible. otherwise take remNum
// if all are filled with 9 and rem>0
```

---

---
## Quick Revision
Maximize the sum of squares of digits of a given number by maximizing the number of 9's and minimizing the use of other digits.
We solve this problem by using the maximum number of 9's in the leftmost digits and then appending the remaining digits.

## Intuition
The idea is to maximize the sum of squares by using the largest possible digit, which is 9. Since we want to maximize the sum, we should use as many 9's as possible in the leftmost digits. The remaining digits can be any number between 0 and 9, and we should use the smallest possible number to minimize the sum.

## Algorithm
1. If the product of the number of digits and 9 is less than the given sum, it's impossible to achieve the sum, so return an empty string.
2. Calculate the number of 9's that can be used in the leftmost digits by dividing the sum by 9.
3. Append the calculated number of 9's to the result string.
4. Calculate the remaining sum by taking the modulus of the sum with 9.
5. If the remaining sum is greater than 0, append it to the result string.
6. Append the remaining digits (0's) to the result string to fill the total number of digits.

## Concept to Remember
* Greedy algorithm: make the locally optimal choice at each step to maximize the overall sum.
* Mathematical reasoning: use mathematical properties to derive the optimal solution.
* String manipulation: use StringBuilder to efficiently build the result string.

## Common Mistakes
* Failing to handle edge cases, such as when the sum is less than the product of the number of digits and 9.
* Not using the maximum number of 9's in the leftmost digits.
* Not appending the remaining digits correctly to fill the total number of digits.

## Complexity Analysis
- Time: O(1) - reason: constant-time operations, no loops or recursive calls.
- Space: O(n) - reason: we append characters to the result string, where n is the total number of digits.

## Commented Code
```java
class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // check if it's possible to achieve the sum
        if (num * 9 < sum) return "";

        // calculate the number of 9's that can be used in the leftmost digits
        int nines = sum / 9;

        // append the calculated number of 9's to the result string
        StringBuilder ans = new StringBuilder("9".repeat(nines));

        // calculate the remaining sum
        sum %= 9;

        // if the remaining sum is greater than 0, append it to the result string
        if (sum > 0) ans.append(sum);

        // append the remaining digits (0's) to fill the total number of digits
        ans.append("0".repeat(num - ans.length()));

        // return the result string
        return ans.toString();
    }
}
```

## Interview Tips
* Make sure to handle edge cases carefully.
* Use the maximum number of 9's in the leftmost digits to maximize the sum.
* Pay attention to the time and space complexity of your solution.

## Revision Checklist
- [ ] Review the edge cases and handle them correctly.
- [ ] Use the maximum number of 9's in the leftmost digits.
- [ ] Pay attention to the time and space complexity of the solution.

## Similar Problems
* [172. Factorial Trailing Zeroes](https://leetcode.com/problems/factorial-trailing-zeroes/)
* [168. Excel Sheet Column Title](https://leetcode.com/problems/excel-sheet-column-title/)
* [129. Sum Root to Leaf Numbers](https://leetcode.com/problems/sum-root-to-leaf-numbers/)

## Tags
`Greedy Algorithm` `String Manipulation` `Mathematical Reasoning`
