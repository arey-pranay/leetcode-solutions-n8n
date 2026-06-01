# Happy Number

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `Math` `Two Pointers`  
**Time:** O(log n)  
**Space:** O(log n)

---

## Solution (java)

```java
class Solution {
    public boolean isHappy(int n) {
        HashSet<Integer> found = new HashSet<>();
        while(n!=1){
            int sum = 0;
            while(n>0){
                sum += (n%10)*(n%10);
                n = n/10;
            }
            n = sum;
            if(found.contains(sum)) return false;
            found.add(sum);
        }
        return true;
    }
}
```

---

---
## Quick Revision
A happy number is a number defined by the process of replacing the number by the sum of the squares of its digits.
We solve this by detecting cycles using a HashSet to store previously seen numbers.

## Intuition
The core idea is that if a number is "happy," it will eventually reach 1. If it's not happy, it will enter a cycle that does not include 1. The "aha moment" is realizing that we can detect these cycles by keeping track of the numbers we've already encountered during the sum-of-squares process. If we see a number again, we're in a loop, and it's not a happy number.

## Algorithm
1. Initialize a `HashSet` called `seenNumbers` to store numbers encountered during the process.
2. Start a `while` loop that continues as long as the current number `n` is not equal to 1.
3. Inside the loop, check if the current number `n` is already present in `seenNumbers`.
    a. If it is, we've found a cycle, so return `false` (the number is not happy).
    b. If it's not, add `n` to `seenNumbers`.
4. Calculate the sum of the squares of the digits of `n`:
    a. Initialize a `sumOfSquares` variable to 0.
    b. Start an inner `while` loop that continues as long as `n` is greater than 0.
    c. Get the last digit of `n` using the modulo operator (`n % 10`).
    d. Square this digit and add it to `sumOfSquares`.
    e. Remove the last digit from `n` by integer division (`n / 10`).
    f. After the inner loop finishes, update `n` to be `sumOfSquares`.
5. If the outer `while` loop terminates (meaning `n` became 1), return `true` (the number is happy).

## Concept to Remember
*   **Cycle Detection:** Recognizing that repeated calculations can lead to cycles and how to detect them.
*   **Hash Sets:** Efficiently storing and checking for the existence of elements.
*   **Digit Manipulation:** Extracting and processing individual digits of a number.
*   **Iterative Algorithms:** Designing a step-by-step process that repeats until a condition is met.

## Common Mistakes
*   Not handling the cycle detection properly, leading to infinite loops for unhappy numbers.
*   Incorrectly calculating the sum of squares of digits.
*   Forgetting to add the current number to the `seenNumbers` set *before* calculating the next sum, or adding it after the check.
*   Assuming all numbers will eventually reach 1 without considering cycles.

## Complexity Analysis
*   **Time:** O(log n) - The number of steps is related to the number of digits, which grows logarithmically with n. In the worst case, the sequence of numbers generated before reaching 1 or a cycle is relatively short. The maximum sum of squares for a 3-digit number (999) is 3 * 81 = 243. For a 4-digit number (9999), it's 4 * 81 = 324. The numbers tend to decrease or stay within a bounded range.
*   **Space:** O(log n) - The space complexity is determined by the size of the `HashSet`. In the worst case, the number of unique values stored before a cycle is detected or 1 is reached is proportional to the number of digits, which is logarithmic with respect to the input number `n`.

## Commented Code
```java
class Solution {
    public boolean isHappy(int n) {
        // Initialize a HashSet to store numbers encountered during the process.
        // This is crucial for detecting cycles.
        HashSet<Integer> found = new HashSet<>();

        // Continue the process as long as the current number 'n' is not 1.
        // If 'n' becomes 1, it's a happy number.
        while(n != 1){
            // Initialize a variable to store the sum of squares of digits for the current 'n'.
            int sum = 0;

            // This inner loop processes the digits of the current number 'n'.
            while(n > 0){
                // Get the last digit of 'n' using the modulo operator.
                int digit = n % 10;
                // Square the digit and add it to the sum.
                sum += digit * digit;
                // Remove the last digit from 'n' using integer division.
                n = n / 10;
            }

            // After processing all digits, update 'n' to be the calculated sum of squares.
            n = sum;

            // Check if this new sum ('n') has been encountered before.
            // If it has, we are in a cycle and the number is not happy.
            if(found.contains(sum)) {
                // Return false immediately if a cycle is detected.
                return false;
            }
            // Add the current sum ('n') to the set of found numbers.
            // This marks it as visited for future checks.
            found.add(sum);
        }

        // If the loop terminates, it means 'n' has reached 1.
        // Therefore, the original number is a happy number.
        return true;
    }
}
```

## Interview Tips
*   Clearly explain the concept of happy numbers and why cycle detection is necessary.
*   Walk through an example (e.g., 19) to illustrate the process and how the `HashSet` works.
*   Discuss the time and space complexity, justifying your reasoning.
*   Be prepared to discuss alternative approaches, like Floyd's Cycle-Finding Algorithm (tortoise and hare), if asked.

## Revision Checklist
- [ ] Understand the definition of a happy number.
- [ ] Implement the sum of squares of digits calculation correctly.
- [ ] Use a `HashSet` to detect cycles.
- [ ] Handle the base case where `n` is already 1.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Linked List Cycle
*   Linked List Cycle II
*   Detect Capital
*   Palindrome Number

## Tags
`Math` `Hash Table` `Two Pointers`
