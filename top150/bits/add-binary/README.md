# Add Binary

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Math` `String` `Bit Manipulation` `Simulation`  
**Time:** O(max(N, M)  
**Space:** O(max(N, M)

---

## Solution (java)

```java
class Solution {
    public String addBinary(String a, String b) {
        int i = a.length()-1;
        int j = b.length()-1;
        int carry = 0;
        StringBuilder sb = new StringBuilder("");
        while(i>=0 || j>=0){
            int A = i>=0 ? a.charAt(i--) - '0' : 0;
            int B = j>=0 ? b.charAt(j--) - '0' : 0;
            int sum = A+B+carry;
            sb.append(sum%2);
            carry = sum > 1 ? 1 : 0;
        }   
        if(carry!=0) sb.append(carry);
        return sb.reverse().toString();     
    }
}

```

---

---
## Quick Revision
Given two binary strings, return their sum as a binary string.
Solve by simulating manual binary addition with a carry.

## Intuition
The problem is essentially asking us to perform addition just like we do with decimal numbers, but in base-2. When adding two digits, we get a sum and potentially a carry-over to the next more significant bit. We can process the strings from right to left (least significant bit to most significant bit), keeping track of the carry.

## Algorithm
1. Initialize two pointers, `i` and `j`, to the last characters of strings `a` and `b` respectively.
2. Initialize a `carry` variable to 0.
3. Initialize an empty `StringBuilder` called `sb` to store the result.
4. Loop while `i` is greater than or equal to 0 OR `j` is greater than or equal to 0.
   a. Get the integer value of the current digit from `a` (if `i` is valid, otherwise 0). Decrement `i`.
   b. Get the integer value of the current digit from `b` (if `j` is valid, otherwise 0). Decrement `j`.
   c. Calculate the `sum` of the two digits and the `carry`.
   d. Append the least significant bit of the `sum` (i.e., `sum % 2`) to `sb`.
   e. Update the `carry` to 1 if `sum` is greater than 1, otherwise set it to 0.
5. After the loop, if `carry` is not 0, append it to `sb`.
6. Reverse `sb` and convert it to a `String` to get the final result.

## Concept to Remember
*   **Binary Representation:** Understanding how numbers are represented in base-2.
*   **Manual Addition Simulation:** Mimicking the process of adding numbers column by column, including handling carries.
*   **String Manipulation:** Efficiently building and reversing strings.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly handling the loop conditions or pointer decrements.
*   **Forgetting the final carry:** Not appending the carry if it remains after processing all digits.
*   **Incorrectly converting characters to integers:** Forgetting to subtract '0' from the character.
*   **Reversing the result too early or not at all:** The `StringBuilder` appends digits in reverse order of significance.

## Complexity Analysis
*   Time: O(max(N, M)) - where N and M are the lengths of strings `a` and `b`. We iterate through each character of both strings at most once.
*   Space: O(max(N, M)) - The `StringBuilder` can grow up to the length of the longer input string plus one for a potential carry.

## Commented Code
```java
class Solution {
    public String addBinary(String a, String b) {
        // Initialize pointer for string a to its last character
        int i = a.length()-1;
        // Initialize pointer for string b to its last character
        int j = b.length()-1;
        // Initialize carry to 0, as there's no carry initially
        int carry = 0;
        // Use StringBuilder for efficient string construction, as we'll be appending
        StringBuilder sb = new StringBuilder("");
        // Loop as long as there are digits in either string or there's a carry
        while(i>=0 || j>=0){
            // Get the integer value of the current digit from string a. If i is out of bounds, use 0. Decrement i.
            int A = i>=0 ? a.charAt(i--) - '0' : 0;
            // Get the integer value of the current digit from string b. If j is out of bounds, use 0. Decrement j.
            int B = j>=0 ? b.charAt(j--) - '0' : 0;
            // Calculate the sum of the current digits from a and b, plus the carry from the previous step
            int sum = A+B+carry;
            // The current digit of the result is the remainder when sum is divided by 2 (the LSB)
            sb.append(sum%2);
            // The carry for the next step is 1 if the sum is greater than 1, otherwise it's 0
            carry = sum > 1 ? 1 : 0;
        }
        // If there's a remaining carry after processing all digits, append it to the result
        if(carry!=0) sb.append(carry);
        // The StringBuilder has digits in reverse order, so reverse it and convert to a String
        return sb.reverse().toString();
    }
}
```

## Interview Tips
*   Clearly explain your approach of simulating manual addition.
*   Walk through an example like "11" + "1" to demonstrate the carry logic.
*   Be mindful of edge cases like empty strings (though constraints usually prevent this) or strings of different lengths.
*   Discuss the time and space complexity of your solution.

## Revision Checklist
- [ ] Understand the problem statement for adding binary strings.
- [ ] Implement the logic for iterating from right to left.
- [ ] Correctly handle the carry-over mechanism.
- [ ] Manage cases where one string is longer than the other.
- [ ] Ensure the final carry is included.
- [ ] Reverse the result before returning.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/) (Linked Lists)
*   [Multiply Strings](https://leetcode.com/problems/multiply-strings/)

## Tags
`String` `Math` `Simulation` `Two Pointers`
