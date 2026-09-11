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
We use three nested loops to pick digits for hundreds, tens, and units place, ensuring uniqueness and evenness.

## Intuition
The core idea is to systematically generate all possible 3-digit numbers using the given digits and then filter them based on the problem's constraints: uniqueness and being even. Since we need unique numbers, a way to keep track of already formed numbers is essential. A boolean array or a Set can serve this purpose. The even constraint means the units digit must be even. The 3-digit constraint means the hundreds digit cannot be zero.

## Algorithm
1. Initialize a boolean array `done` of size 1000 to keep track of unique numbers formed. All elements are initially `false`.
2. Initialize a counter `ans` to 0, which will store the count of unique 3-digit even numbers.
3. Iterate through the `digits` array with index `i` for the hundreds place.
4. Inside the first loop, iterate through the `digits` array with index `j` for the tens place.
5. Inside the second loop, iterate through the `digits` array with index `k` for the units place.
6. Inside the third loop, apply the following conditions:
    a. The digit at `digits[k]` must be even (`digits[k] % 2 == 0`).
    b. The indices `i`, `j`, and `k` must be distinct to ensure unique digit usage for each position in the 3-digit number.
    c. The digit at `digits[i]` cannot be 0, as it's the hundreds place of a 3-digit number. (This check is implicitly handled by the `if(digits[i]==0) continue;` at the beginning of the outer loop).
7. If all conditions are met, form the 3-digit number: `num = (digits[i] * 100) + (digits[j] * 10) + digits[k]`.
8. Check if `done[num]` is `true`. If it is, this number has already been counted, so `continue` to the next iteration.
9. If `done[num]` is `false`, mark it as `true` (`done[num] = true`) and increment `ans`.
10. After all loops complete, return `ans`.

## Concept to Remember
*   **Permutations/Combinations:** Understanding how to generate unique combinations of elements from a set.
*   **Boolean Flags/Set for Uniqueness:** Using auxiliary data structures to efficiently track seen items and avoid duplicates.
*   **Number Formation from Digits:** Converting individual digits into a multi-digit number.
*   **Modulo Operator for Even/Odd Check:** Using `% 2` to determine if a number is even or odd.

## Common Mistakes
*   **Not handling leading zeros:** Forgetting to exclude numbers where the hundreds digit is 0.
*   **Not ensuring digit uniqueness:** Allowing the same digit to be used multiple times in the same 3-digit number (e.g., 112). The problem statement implies unique digits for each position.
*   **Not handling duplicate numbers:** If the input `digits` array has duplicates, simply iterating might generate the same 3-digit number multiple times. The `done` array is crucial here.
*   **Incorrect even number check:** Misapplying the modulo operator or checking the wrong digit for evenness.
*   **Off-by-one errors in loops or array indexing.**

## Complexity Analysis
*   Time: O(N^3) - reason: Three nested loops iterate through the `digits` array of size N. Inside the loops, operations are constant time.
*   Space: O(1000) which is O(1) - reason: The `done` boolean array has a fixed size of 1000, independent of the input array size.

## Commented Code
```java
class Solution {
    public int totalNumbers(int[] digits) {
        // Initialize a boolean array to keep track of unique numbers formed.
        // The size 1000 covers all possible 3-digit numbers (000 to 999).
        boolean[] done = new boolean[1000];
        // Get the length of the input digits array.
        int n = digits.length;
        // Initialize the counter for unique 3-digit even numbers.
        int ans = 0;

        // Outer loop: iterate through each digit for the hundreds place.
        for(int i = 0; i < n; i++){
            // If the digit for the hundreds place is 0, skip it, as it won't form a 3-digit number.
            if(digits[i] == 0) continue;

            // Middle loop: iterate through each digit for the tens place.
            for(int j = 0; j < n; j++){
                // Skip if the index for the tens place is the same as the hundreds place, ensuring unique digits.
                if(j == i) continue;

                // Inner loop: iterate through each digit for the units place.
                for(int k = 0; k < n; k++){
                    // Check if the digit for the units place is odd, or if the indices are the same as hundreds or tens place.
                    // If any of these conditions are true, skip this combination.
                    if((digits[k] % 2 != 0) || (k == i) || (k == j)) continue;

                    // Form the 3-digit number using the selected digits.
                    int num = (digits[i] * 100) + (digits[j] * 10) + digits[k];

                    // Check if this number has already been formed and counted.
                    if(done[num]) continue;

                    // Mark this number as done (formed and counted).
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
*   **Clarify Constraints:** Ask about the range of digits, whether they are unique in the input array, and if the formed number must use unique digits from the input array for each position. (The provided solution assumes unique digits for each position).
*   **Edge Cases:** Discuss what happens if the input array is empty, has fewer than 3 digits, or contains only odd digits.
*   **Optimization:** While O(N^3) is acceptable for small N, briefly mention if there are ways to optimize if N were very large (e.g., using frequency maps if digit repetition in the input was allowed and the problem was about forming numbers with available counts).
*   **Data Structure Choice:** Justify the use of the `boolean[] done` array over a `HashSet` for tracking seen numbers, highlighting the efficiency for a fixed range of numbers.

## Revision Checklist
- [ ] Understand the problem: form unique 3-digit even numbers.
- [ ] Identify constraints: hundreds digit cannot be 0, units digit must be even.
- [ ] Choose a method to track uniqueness (boolean array or Set).
- [ ] Implement three nested loops for digit selection.
- [ ] Add checks for leading zero, even units digit, and unique digit positions.
- [ ] Form the number and update the count if unique.
- [ ] Analyze time and space complexity.
- [ ] Test with examples.

## Similar Problems
*   [1010. Pairs of Songs With Total Durations Divisible by 60](https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/) (Uses modulo arithmetic and frequency maps)
*   [129. Sum Root to Leaf Numbers](https://leetcode.com/problems/sum-root-to-leaf-numbers/) (Number formation from path)
*   [216. Combination Sum III](https://leetcode.com/problems/combination-sum-iii/) (Finding unique combinations)

## Tags
`Array` `Backtracking` `Boolean Array`
