# Process String With Special Operations Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Simulation`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public char processStr(String s, long k) {
        long len=0;
        for(char c : s.toCharArray()){
            if(c=='*'){if(len>0) len--;}
            else if(c=='#') len*=2;
            else if(c=='%') continue;
            else len++;
        }
        if(k>len-1) return '.';
        for(int i=s.length()-1;i>=0;i--){
            char c = s.charAt(i);
            if(c=='*')len++;
            else if(c=='#'){
                if(k>= len/2) k-=len/2; 
                len = len/2;
            }
            else if(c=='%') k = len-1 -k;
            else {if(k == len-1) return c; len--;}
        }
        return '.';
    }
}
```

---

---
## Quick Revision
The problem asks to find the k-th character of a string after applying special operations.
We solve this by simulating the operations in reverse to find the original index of the k-th character.

## Intuition
The key insight is that the operations modify the effective length of the string and the indices of characters. Simulating forward to find the k-th character would be inefficient if the string becomes very long. However, simulating backward allows us to determine which original character corresponds to the k-th position in the *final* processed string. The operations like '*' and '#' have a direct impact on the length and index mapping, while '%' is a positional transformation.

## Algorithm
1. **Forward Pass (Calculate Final Length):**
   - Initialize `len` to 0.
   - Iterate through the input string `s` from left to right.
   - If the character is '*': decrement `len` if `len > 0`.
   - If the character is '#': multiply `len` by 2.
   - If the character is '%': skip the character (it doesn't affect length directly).
   - If the character is any other character: increment `len`.
2. **Check k Validity:**
   - If `k` is greater than or equal to the final `len`, it means the k-th character does not exist, so return '.'.
3. **Backward Pass (Find Original Character):**
   - Iterate through the input string `s` from right to left (index `i` from `s.length() - 1` down to 0).
   - Get the current character `c`.
   - If `c` is '*': increment `len` (this character would have added 1 in the forward pass).
   - If `c` is '#':
     - If `k` is less than `len / 2`, it means the k-th character is in the left half of the doubled segment, so `k` remains the same.
     - Otherwise, the k-th character is in the right half, so subtract `len / 2` from `k` and update `len` to `len / 2`.
   - If `c` is '%':
     - This operation swaps the effective index. The new `k` becomes `len - 1 - k`.
   - If `c` is any other character:
     - If `k` is equal to `len - 1`, this is the character we are looking for. Return `c`.
     - Otherwise, decrement `len` (this character would have contributed 1 to the length).
4. **Return Default:**
   - If the loop finishes without finding the character, return '.'.

## Concept to Remember
*   **String Manipulation with Special Characters:** Understanding how each special character modifies the string's effective length and character positions.
*   **Two-Pass Algorithm:** Using a forward pass to determine overall properties (like final length) and a backward pass to pinpoint a specific element based on those properties.
*   **Index Transformation:** How operations like '%' can invert or shift the target index `k`.
*   **Greedy Approach (Implicit):** In the backward pass, we make decisions about `k` and `len` based on the current character and the remaining effective string length.

## Common Mistakes
*   **Off-by-one errors with `k` and `len`:** Incorrectly handling `k` when it represents an index (0-based) versus a count.
*   **Misinterpreting the '%' operation:** Forgetting that '%' effectively maps `k` to `len - 1 - k` relative to the current segment's length.
*   **Incorrectly handling the '#' operation:** Not properly dividing `k` and `len` when the k-th character falls into the right half of a doubled segment.
*   **Not handling edge cases for `k`:** Forgetting to check if `k` is out of bounds after the forward pass.
*   **Incorrectly updating `len` in the backward pass:** Forgetting to account for characters that were skipped or modified in the forward pass.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the string twice, once forward and once backward. Each character is processed a constant number of times.
- Space: O(1) - reason: We only use a few variables (`len`, `k`, `i`, `c`) to store state, regardless of the input string size.

## Commented Code
```java
class Solution {
    public char processStr(String s, long k) {
        long len = 0; // Initialize the effective length of the string after operations.

        // First pass: Calculate the final effective length of the string.
        for (char c : s.toCharArray()) { // Iterate through each character in the string.
            if (c == '*') { // If the character is '*':
                if (len > 0) len--; // It effectively removes the last character, so decrement length if it's not empty.
            } else if (c == '#') { // If the character is '#':
                len *= 2; // It doubles the effective length.
            } else if (c == '%') { // If the character is '%':
                continue; // This character does not affect the length directly, so skip it.
            } else { // For any other character (a regular character):
                len++; // It adds one to the effective length.
            }
        }

        // Check if the requested k-th character is out of bounds for the final string.
        if (k > len - 1) return '.'; // If k is greater than or equal to the final length, it's invalid.

        // Second pass: Iterate backward to find the original character corresponding to the k-th position.
        for (int i = s.length() - 1; i >= 0; i--) { // Iterate from the end of the string to the beginning.
            char c = s.charAt(i); // Get the current character.

            if (c == '*') { // If the character is '*':
                len++; // In the forward pass, it decremented length. In reverse, it adds 1 to the effective length.
            } else if (c == '#') { // If the character is '#':
                // This operation doubled the length. We need to determine if k falls in the first or second half.
                if (k >= len / 2) { // If k is in the second half (indices len/2 to len-1):
                    k -= len / 2; // Adjust k by subtracting the length of the first half.
                }
                len = len / 2; // The effective length is now halved.
            } else if (c == '%') { // If the character is '%':
                // This operation effectively mirrors the index.
                k = len - 1 - k; // The new k is the mirror image relative to the current effective length.
            } else { // For any other character (a regular character):
                if (k == len - 1) { // If the current k matches the position of this character (len-1 is the last position):
                    return c; // This is the k-th character we are looking for.
                }
                len--; // This character contributed 1 to the length in the forward pass, so decrement length in reverse.
            }
        }

        return '.'; // If the loop finishes without finding the character (should not happen if k is valid), return '.'.
    }
}
```

## Interview Tips
*   **Clarify Operations:** Before coding, ensure you fully understand how each special character affects the string's length and character indices. Ask for examples if needed.
*   **Explain the Two-Pass Approach:** Clearly articulate why a forward pass is needed to determine the final length and why a backward pass is more efficient for finding the specific character.
*   **Trace with Examples:** Be prepared to walk through a small example string and `k` value, showing how `len` and `k` change in both passes. This demonstrates your understanding of the logic.
*   **Handle `k` as 0-indexed:** Remember that `k` is typically 0-indexed, so comparisons should be `k == len - 1` for the last element, not `k == len`.

## Revision Checklist
- [ ] Understand the effect of '*' on length.
- [ ] Understand the effect of '#' on length.
- [ ] Understand the effect of '%' on index.
- [ ] Implement the forward pass to calculate final length.
- [ ] Implement the backward pass to find the original character.
- [ ] Handle `k` out of bounds.
- [ ] Correctly update `k` and `len` in the backward pass for each character type.
- [ ] Test with edge cases (empty string, `k=0`, `k` at boundaries, strings with only special characters).

## Similar Problems
*   Process String With Special Operations I
*   Decode Ways
*   Decode String
*   String Compression

## Tags
`String` `Simulation` `Two Pointers` `Greedy`
