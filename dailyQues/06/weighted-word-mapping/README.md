# Weighted Word Mapping

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `String` `Simulation`  
**Time:** O(N * L)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder sb = new StringBuilder("");
        for(String word : words){
            int res = 0;
            for(char c : word.toCharArray()) res += weights[c-'a'];
            res %= 26;
            sb.append((char)('z'-res));
        }
        return sb.toString();
    }
}
```

---

---
## Quick Revision
This problem asks to map each word to a character based on its weighted sum of characters. The sum is then taken modulo 26 and used to derive the mapped character.

## Intuition
The core idea is to process each word individually. For each word, we need to calculate a "weight" based on the provided `weights` array. The `weights` array maps each lowercase letter ('a' through 'z') to an integer value. The sum of these values for all characters in a word gives us a raw score. This score needs to be normalized. The problem statement implies a mapping back to a character, and the modulo 26 operation suggests a cyclic mapping, similar to how alphabets work. The specific mapping `('z' - res)` implies that a higher `res` value should map to an earlier letter in the alphabet (closer to 'a'), and a lower `res` value should map to a later letter (closer to 'z').

## Algorithm
1. Initialize an empty `StringBuilder` to store the resulting mapped string.
2. Iterate through each `word` in the input `words` array.
3. For each `word`, initialize an integer variable `res` to 0. This variable will accumulate the weighted sum of characters in the current word.
4. Iterate through each character `c` in the current `word`.
5. For each character `c`, calculate its corresponding index in the `weights` array by subtracting the ASCII value of 'a' (i.e., `c - 'a'`).
6. Add the weight at this index (`weights[c - 'a']`) to `res`.
7. After processing all characters in the `word`, take `res` modulo 26 (`res %= 26`). This normalizes the sum to a value between 0 and 25.
8. Convert the normalized `res` value back to a character. The formula `('z' - res)` maps the result: if `res` is 0, it becomes 'z'; if `res` is 25, it becomes 'a'.
9. Append this calculated character to the `StringBuilder`.
10. After iterating through all `words`, convert the `StringBuilder` to a `String` and return it.

## Concept to Remember
*   **Character to Integer Mapping:** Understanding how to convert characters to their corresponding indices (e.g., `c - 'a'`) for array lookups.
*   **Modulo Arithmetic:** Applying the modulo operator (`%`) for cyclic operations and normalization.
*   **String Manipulation:** Efficiently building strings using `StringBuilder`.
*   **ASCII Values:** Implicit use of ASCII values for character arithmetic.

## Common Mistakes
*   **Incorrect Indexing:** Forgetting to subtract `'a'` when accessing the `weights` array, leading to `ArrayIndexOutOfBoundsException` or incorrect weight lookups.
*   **Modulo Operation Errors:** Applying modulo incorrectly or not at all, resulting in out-of-bounds character calculations.
*   **Character Conversion Logic:** Misinterpreting the mapping from the modulo result to the final character (e.g., using `'a' + res` instead of `'z' - res`).
*   **Inefficient String Concatenation:** Using `+` for string concatenation inside a loop, which is less efficient than `StringBuilder`.

## Complexity Analysis
*   **Time:** O(N * L), where N is the number of words and L is the average length of a word. We iterate through each word, and for each word, we iterate through its characters.
*   **Space:** O(N), for the `StringBuilder` which stores the result string of length N.

## Commented Code
```java
class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        // Initialize a StringBuilder to efficiently build the result string.
        StringBuilder sb = new StringBuilder("");
        // Iterate through each word in the input array of words.
        for(String word : words){
            // Initialize a variable to store the cumulative weight for the current word.
            int res = 0;
            // Iterate through each character of the current word.
            for(char c : word.toCharArray()){
                // Calculate the index for the weights array by subtracting 'a' from the character's ASCII value.
                // This maps 'a' to 0, 'b' to 1, and so on.
                // Add the corresponding weight to the cumulative result.
                res += weights[c-'a'];
            }
            // Take the cumulative result modulo 26 to normalize it within the range [0, 25].
            res %= 26;
            // Convert the normalized result back to a character.
            // 'z' - 0 = 'z', 'z' - 1 = 'y', ..., 'z' - 25 = 'a'.
            // This maps the result cyclically to a character from 'a' to 'z'.
            sb.append((char)('z'-res));
        }
        // Convert the StringBuilder to a String and return it.
        return sb.toString();
    }
}
```

## Interview Tips
*   **Clarify the Mapping:** Before coding, ask the interviewer to confirm the exact mapping logic from the modulo result to the final character. The problem implies `'z' - res`, but it's good to be sure.
*   **Edge Cases:** Consider edge cases like empty input arrays (`words` or `weights`), empty strings within `words`, or words containing characters outside 'a'-'z' (though the problem implies lowercase English letters).
*   **Explain Modulo:** Clearly articulate why the modulo operation is used and how it helps normalize the weights.
*   **StringBuilder Efficiency:** Highlight the use of `StringBuilder` for efficient string construction in loops.

## Revision Checklist
- [ ] Understand the problem statement and the weighted mapping logic.
- [ ] Implement character-to-index conversion (`c - 'a'`).
- [ ] Correctly apply the modulo 26 operation.
- [ ] Implement the character mapping from the modulo result (`'z' - res`).
- [ ] Use `StringBuilder` for efficient string building.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Caesar Cipher
*   Vigenere Cipher
*   Character Replacement

## Tags
`Array` `Hash Map`
