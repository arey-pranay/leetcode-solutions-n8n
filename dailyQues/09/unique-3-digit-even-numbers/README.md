# Unique 3 Digit Even Numbers

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Recursion` `Enumeration`  
**Time:** O(N^3)  
**Space:** O(1000)

---

## Solution (java)

```java
class Solution {
    public int totalNumbers(int[] digits) {
        boolean[] done = new boolean[1000];
        int n = digits.length;
        int ans = 0;
        for(int i = 0;i<n;i++){
            if(digits[i]==0) continue;
            for(int j = 0;j<n;j++){
                if(j==i) continue;
                for(int k = 0;k<n;k++){
                    if((digits[k]%2!=0)||(k ==i)|| (k==j)) continue;
                    int num =(digits[i]*100)+(digits[j]*10)+digits[k];
                    if(done[num]) continue;
                    done[num]=true;ans++;
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
Given an array of digits, find the count of unique 3-digit even numbers that can be formed using these digits.
We iterate through all possible combinations of three digits, check for uniqueness and evenness, and count them.

## Intuition
The core idea is to systematically generate all possible 3-digit numbers using the given digits and then filter them based on the problem's constraints: uniqueness and being even. Since we need unique numbers, a way to keep track of already formed numbers is crucial. A boolean array or a set can serve this purpose. For even numbers, the last digit must be even.

## Algorithm
1. Initialize a boolean array `done` of size 1000 to keep track of unique numbers formed. All elements are initially `false`.
2. Initialize a counter `ans` to 0, which will store the total count of unique 3-digit even numbers.
3. Get the length of the input `digits` array, `n`.
4. Iterate through the `digits` array with index `i` for the hundreds place.
   - If `digits[i]` is 0, skip this iteration as a 3-digit number cannot start with 0.
5. Inside the first loop, iterate through the `digits` array with index `j` for the tens place.
   - If `j` is equal to `i`, skip this iteration to avoid using the same digit for different places.
6. Inside the second loop, iterate through the `digits` array with index `k` for the units place.
   - Check if `digits[k]` is odd. If it is, skip this iteration as the number must be even.
   - Check if `k` is equal to `i` or `j`. If it is, skip this iteration to avoid using the same digit for different places.
7. If all conditions are met (hundreds digit is non-zero, digits are distinct, and units digit is even), form the 3-digit number: `num = (digits[i] * 100) + (digits[j] * 10) + digits[k]`.
8. Check if `done[num]` is already `true`. If it is, this number has already been counted, so skip to the next iteration.
9. If `done[num]` is `false`, mark it as `true` (`done[num] = true`) and increment the `ans` counter.
10. After all iterations, return the final `ans`.

## Concept to Remember
*   **Permutations/Combinations:** Understanding how to generate all possible arrangements of elements from a set.
*   **Handling Uniqueness:** Employing data structures like boolean arrays or hash sets to efficiently track and avoid duplicate entries.
*   **Number Properties:** Knowledge of divisibility rules, specifically for even numbers (last digit is even).
*   **Array Indexing and Iteration:** Proficient use of nested loops and array indices to access and manipulate elements.

## Common Mistakes
*   **Not handling leading zeros:** Forgetting to skip combinations where the hundreds digit is 0.
*   **Not ensuring distinct digits:** Using the same digit for multiple places (hundreds, tens, units) within a single number.
*   **Incorrect even number check:** Misunderstanding or incorrectly implementing the condition for a number to be even.
*   **Not handling uniqueness properly:** Counting the same 3-digit number multiple times if it can be formed in different ways from the input digits.
*   **Off-by-one errors in loops or array access:** Incorrect loop bounds or array indexing leading to missed or extra elements.

## Complexity Analysis
- Time: O(N^3) - reason: We have three nested loops, each iterating up to N times, where N is the number of digits in the input array.
- Space: O(1000) which simplifies to O(1) - reason: We use a boolean array of fixed size 1000 to store whether a number has been seen. This size is constant and does not depend on the input array size.

## Commented Code
```java
class Solution {
    public int totalNumbers(int[] digits) {
        // Initialize a boolean array to keep track of unique numbers formed.
        // The size 1000 covers all possible 3-digit numbers (000 to 999).
        boolean[] done = new boolean[1000];
        // Get the number of digits in the input array.
        int n = digits.length;
        // Initialize a counter for the total unique 3-digit even numbers.
        int ans = 0;

        // Outer loop for the hundreds digit.
        for(int i = 0; i < n; i++){
            // If the current digit is 0, it cannot be the hundreds digit of a 3-digit number.
            if(digits[i] == 0) continue;

            // Middle loop for the tens digit.
            for(int j = 0; j < n; j++){
                // Ensure the tens digit is different from the hundreds digit.
                if(j == i) continue;

                // Inner loop for the units digit.
                for(int k = 0; k < n; k++){
                    // Check if the units digit is odd. If it is, the number won't be even.
                    // Also, ensure the units digit is different from the hundreds and tens digits.
                    if((digits[k] % 2 != 0) || (k == i) || (k == j)) continue;

                    // Form the 3-digit number using the selected digits.
                    int num = (digits[i] * 100) + (digits[j] * 10) + digits[k];

                    // If this number has already been formed and counted, skip it.
                    if(done[num]) continue;

                    // Mark this number as seen.
                    done[num] = true;
                    // Increment the count of unique 3-digit even numbers.
                    ans++;
                }
            }
        }
        // Return the total count of unique 3-digit even numbers.
        return ans;
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask about the range of digits in the input array and if there are any duplicates in the input itself. This can sometimes lead to optimizations.
*   **Explain the `done` array:** Clearly articulate why a boolean array of size 1000 is used and how it efficiently handles uniqueness for numbers up to 999.
*   **Edge Cases:** Discuss edge cases like an input array with only one digit, or an array containing only odd digits, or an array with many zeros.
*   **Alternative Approaches:** Briefly mention how a `HashSet<Integer>` could also be used to track unique numbers, and discuss its trade-offs (e.g., potentially higher constant factor for space/time but more flexible if number range was unknown or very large).

## Revision Checklist
- [ ] Understand the problem: form unique 3-digit even numbers.
- [ ] Identify constraints: leading zeros, distinct digits, even number.
- [ ] Choose a method for uniqueness tracking (boolean array or set).
- [ ] Implement nested loops for digit selection (hundreds, tens, units).
- [ ] Add checks for leading zero.
- [ ] Add checks for distinct digits.
- [ ] Add check for even units digit.
- [ ] Form the number and check/update uniqueness tracker.
- [ ] Increment count.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Permutations II (LeetCode 47)
*   Next Permutation (LeetCode 31)
*   Generate Parentheses (LeetCode 22)
*   Combinations (LeetCode 77)

## Tags
`Array` `Backtracking` `Math`
