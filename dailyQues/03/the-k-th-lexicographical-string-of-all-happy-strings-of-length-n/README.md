# The K Th Lexicographical String Of All Happy Strings Of Length N

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Backtracking`  
**Time:** O(3^N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    char[] abc;
    int count;
    public String getHappyString(int n, int k) {
        abc = new char[] { 'a', 'b', 'c' };
        StringBuilder str = new StringBuilder();
        count = 0;
        return func(str,n,k);
    }

    public String func(StringBuilder str,int N, int K) {
        if (str.length() == N) { 
            count++;
            if (count == K) return str.toString(); 
            return "";
        }
        for (int i = 0; i < 3; i++) {
            if (!str.isEmpty() && str.charAt(str.length() - 1) == abc[i]) continue;
            str.append(abc[i]); 
            String temp = func(str,N,K);
            if(temp.length()>0) return temp;
            str.deleteCharAt(str.length() - 1);
        }
        return "";
    }

}
```

---

---
## Quick Revision
This problem asks for the K-th lexicographically smallest "happy string" of length N. A happy string is one where no two adjacent characters are the same.
We solve this by generating happy strings recursively in lexicographical order and stopping when we find the K-th one.

## Intuition
The core idea is to generate all possible happy strings in lexicographical order. Since we need the K-th one, we can think of this as a search problem. A recursive backtracking approach is natural here. We build the string character by character. At each step, we try appending 'a', 'b', or 'c', ensuring the "happy string" condition (no adjacent duplicates) is met. By trying 'a' first, then 'b', then 'c', we naturally explore strings in lexicographical order. We maintain a counter to track how many valid happy strings we've generated. When the counter reaches K, we've found our target string.

## Algorithm
1. Initialize an array `abc` with characters 'a', 'b', 'c'.
2. Initialize a global or class-level counter `count` to 0.
3. Create a recursive helper function `func` that takes a `StringBuilder` (current string being built), the target length `N`, and the target rank `K`.
4. **Base Case:** If the `StringBuilder`'s length equals `N`:
    a. Increment `count`.
    b. If `count` equals `K`, return the `StringBuilder` converted to a `String`.
    c. Otherwise, return an empty string (this path didn't lead to the K-th string).
5. **Recursive Step:** Iterate through each character in `abc` (from 'a' to 'c'):
    a. **Constraint Check:** If the `StringBuilder` is not empty AND the last character appended is the same as the current character `abc[i]`, skip this character (continue to the next iteration).
    b. **Append:** Append `abc[i]` to the `StringBuilder`.
    c. **Recurse:** Call `func` with the updated `StringBuilder`, `N`, and `K`.
    d. **Check Result:** If the recursive call returns a non-empty string (meaning it found the K-th string), immediately return that string.
    e. **Backtrack:** If the recursive call did not find the K-th string, remove the last appended character from the `StringBuilder` (backtrack) to explore other possibilities.
6. If the loop finishes without finding the K-th string, return an empty string.
7. In the main `getHappyString` function, call `func` with an empty `StringBuilder`, `n`, and `k`, and return its result.

## Concept to Remember
*   **Backtracking:** A general algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point in time.
*   **Lexicographical Order:** The order in which words or strings appear in a dictionary. This is achieved by trying characters in alphabetical order ('a', then 'b', then 'c') at each step of the generation.
*   **Recursion:** A programming technique where a function calls itself to solve smaller instances of the same problem.

## Common Mistakes
*   **Not handling the base case correctly:** Forgetting to increment the counter or return the correct string when the target length is reached.
*   **Incorrect backtracking:** Failing to remove the last appended character from the `StringBuilder` after a recursive call returns, leading to incorrect string constructions.
*   **Not checking adjacent characters:** Forgetting the "happy string" constraint, leading to invalid strings being generated.
*   **Inefficiently generating all strings:** If `k` is very large, generating all possible strings and then picking the k-th one might be too slow. The current approach stops early.
*   **Off-by-one errors with `k`:** Misunderstanding whether `k` is 0-indexed or 1-indexed (LeetCode problems usually use 1-indexed `k`).

## Complexity Analysis
*   **Time:** O(3^N) - In the worst case, we might explore all possible happy strings. At each of the N positions, we have at most 3 choices for the character (or 2 if it's adjacent to the previous character).
*   **Space:** O(N) - This is due to the recursion depth, which can go up to N levels, and the `StringBuilder` which also stores up to N characters.

## Commented Code
```java
class Solution {
    // Array to hold the possible characters 'a', 'b', 'c'
    char[] abc;
    // Counter to keep track of how many happy strings we've generated so far
    int count;

    // Main function to find the k-th happy string
    public String getHappyString(int n, int k) {
        // Initialize the character array
        abc = new char[] { 'a', 'b', 'c' };
        // Use StringBuilder for efficient string manipulation
        StringBuilder str = new StringBuilder();
        // Reset the counter for each new call
        count = 0;
        // Start the recursive generation process
        return func(str,n,k);
    }

    // Recursive helper function to generate happy strings
    public String func(StringBuilder str,int N, int K) {
        // Base Case: If the current string has reached the target length N
        if (str.length() == N) {
            // Increment the count of valid happy strings found
            count++;
            // If this is the K-th happy string we're looking for
            if (count == K) return str.toString(); // Return it as a String
            // Otherwise, this path didn't lead to the K-th string, return empty
            return "";
        }

        // Recursive Step: Iterate through each possible character ('a', 'b', 'c')
        for (int i = 0; i < 3; i++) {
            // Constraint Check: Ensure no two adjacent characters are the same
            // If the string is not empty AND the last character is the same as the current character we're considering
            if (!str.isEmpty() && str.charAt(str.length() - 1) == abc[i]) continue; // Skip this character and try the next one

            // Append the current character to the string being built
            str.append(abc[i]);
            // Recursively call func to build the rest of the string
            String temp = func(str,N,K);
            // If the recursive call returned a non-empty string, it means the K-th string was found
            if(temp.length()>0) return temp; // Propagate the found string up the call stack

            // Backtrack: If the K-th string was not found down this path, remove the last appended character
            str.deleteCharAt(str.length() - 1);
        }
        // If no character could be appended to form the K-th string from this state, return empty
        return "";
    }
}
```

## Interview Tips
*   **Explain your approach clearly:** Start by describing the backtracking strategy and why it's suitable for generating strings in lexicographical order.
*   **Walk through an example:** Use a small `n` (e.g., `n=2`, `k=3`) to demonstrate how the recursion and backtracking work step-by-step.
*   **Discuss constraints:** Mention the "happy string" constraint and how you handle it in your code.
*   **Analyze complexity:** Be prepared to explain the time and space complexity of your solution.
*   **Consider edge cases:** Think about `n=1`, `k=1`, or `k` being larger than the total number of happy strings.

## Revision Checklist
- [ ] Understand the definition of a "happy string".
- [ ] Recognize that lexicographical order implies trying characters 'a', 'b', 'c' in sequence.
- [ ] Implement a recursive backtracking approach.
- [ ] Correctly handle the base case (string length equals N).
- [ ] Implement the constraint check for adjacent characters.
- [ ] Ensure proper backtracking (removing characters).
- [ ] Use a counter to find the K-th string.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Combinations
*   Permutations
*   Generate Parentheses
*   Letter Combinations of a Phone Number

## Tags
`Backtracking` `Recursion` `String`

## My Notes
Beats 100%
