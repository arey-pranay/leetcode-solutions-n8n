# Zigzag Conversion

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public String convert(String s, int numRows) { 
        int x = numRows + numRows -2; // difference between indices starting a new column
        if(x==0) return s;
        
        StringBuilder sb = new StringBuilder();
        int[] y = new int[2]; // jumps array
        
        for(int i=0;i<numRows;i++){
            y[0] = x - 2*i;
            y[1] = x - y[0];
            if(y[1] == 0) y[1] = x; if(y[0] == 0) y[0] = x; // first and last row have gaps of x.
            int j = i;
            int temp = 0;
            while(j<s.length()){
                sb.append(s.charAt(j));
                j += y[temp];
                temp = temp ^ 1;
            }
        }
        
        return sb.toString();
    }
}

// +8 ...
// +6 +2
// +4 +4
// +2 +6
// +8 ..

// 0 - - - 8
// 1 - - 7 9 
// 2 - 6 - 10
// 3 5 - - 11
// 4 - - - 12

// +6 ...
// +4 +2...
// +2 +4....
// +6 .....


// +4....
// +2 +2 ...
// +4....

// 0 - 4 - 8
// 1 3 5 7 9
// 2 - 6 - 10


// 0 - - 6 -  -  12
// 1 - 5 7 -  11 13
// 2 4 - 8 10 -  -
// 3 - - 9 -  -  -

// 0 2 4 6 8  10 12
// 1 3 5 7 9  11 13
// 2 4 6 8 10 12 14
// 3 5 7 9 11 13 15
```

---

---
## Quick Revision
The problem asks to convert a given string into a zigzag pattern based on a specified number of rows. The solution involves calculating the indices of characters that fall on each row and concatenating them.

## Intuition
The core idea is to observe the pattern of character placement in the zigzag. For a given number of rows, the characters are placed in a downward diagonal, then an upward diagonal. The key insight is to identify the "cycle length" of this pattern and then, for each row, determine the indices of characters that belong to it by stepping through this cycle.

## Algorithm
1.  Handle the edge case where `numRows` is 1. In this case, the string remains unchanged, so return `s`.
2.  Calculate the cycle length of the zigzag pattern. This is `2 * numRows - 2`. This represents the distance between two characters that are in the same column and on the same "downward" stroke.
3.  Initialize an empty `StringBuilder` to store the result.
4.  Iterate through each row from `0` to `numRows - 1`.
5.  For each row `i`:
    *   Calculate the primary jump (downward stroke): `jump1 = cycleLength - 2 * i`.
    *   Calculate the secondary jump (upward stroke): `jump2 = 2 * i`.
    *   Initialize the current index `j` to `i` (the starting character for this row).
    *   Initialize a `temp` variable to `0` to alternate between `jump1` and `jump2`.
    *   While `j` is within the bounds of the string `s`:
        *   Append the character at index `j` to the `StringBuilder`.
        *   If `temp` is 0 (meaning we are on a downward stroke or the first character of a row), the next jump is `jump1`.
        *   If `temp` is 1 (meaning we are on an upward stroke), the next jump is `jump2`.
        *   Update `j` by adding the appropriate jump.
        *   Toggle `temp` using `temp = temp ^ 1` to switch between jumps.
6.  Return the string representation of the `StringBuilder`.

## Concept to Remember
*   **Pattern Recognition:** Identifying repeating patterns in sequences or grids is crucial for many algorithmic problems.
*   **Index Manipulation:** Carefully calculating and managing indices is key to correctly accessing elements in strings or arrays.
*   **Cycle Length:** Understanding the repeating unit or cycle in a pattern helps in devising efficient traversal strategies.
*   **Bitwise XOR for Toggling:** Using `temp = temp ^ 1` is an efficient way to toggle between 0 and 1.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly calculating the cycle length or the jump distances can lead to missing or extra characters.
*   **Handling edge rows:** The first and last rows have different jump patterns (only one type of jump effectively), which can be a source of errors if not handled separately or implicitly by the jump calculation.
*   **Incorrectly alternating jumps:** Failing to switch between the downward and upward jumps at the right times will result in an incorrect zigzag pattern.
*   **Not handling `numRows = 1`:** This is a common edge case that should be explicitly checked to avoid division by zero or incorrect calculations.
*   **Integer overflow:** While less likely with typical string lengths, for extremely long strings and large `numRows`, intermediate calculations could theoretically overflow if not careful (though Java's `int` is usually sufficient here).

## Complexity Analysis
- Time: O(N) - where N is the length of the input string `s`. We iterate through each character of the string exactly once to append it to the `StringBuilder`. The outer loop runs `numRows` times, but the inner `while` loop collectively processes all N characters.
- Space: O(N) - for the `StringBuilder` which stores the resulting string. In the worst case, the `StringBuilder` will hold all N characters.

## Commented Code
```java
class Solution {
    public String convert(String s, int numRows) {
        // Calculate the cycle length of the zigzag pattern.
        // This is the distance between two characters that are in the same column and on the same downward stroke.
        // For numRows = 3, cycle is 2*3 - 2 = 4. (e.g., 0, 4, 8...)
        // For numRows = 4, cycle is 2*4 - 2 = 6. (e.g., 0, 6, 12...)
        int cycleLen = numRows + numRows - 2;

        // If numRows is 1, the string doesn't change, so return it directly.
        // This also prevents division by zero if cycleLen becomes 0 (when numRows = 1).
        if (cycleLen == 0) {
            return s;
        }

        // Use a StringBuilder for efficient string concatenation.
        StringBuilder sb = new StringBuilder();

        // Iterate through each row from 0 to numRows - 1.
        for (int i = 0; i < numRows; i++) {
            // Calculate the primary jump (downward stroke).
            // For row 0, this is cycleLen. For row numRows-1, this is cycleLen.
            // For intermediate rows, this is cycleLen - 2*i.
            int jump1 = cycleLen - 2 * i;

            // Calculate the secondary jump (upward stroke).
            // This jump is only relevant for intermediate rows.
            // For row 0, this is 0. For row numRows-1, this is cycleLen.
            // For intermediate rows, this is 2*i.
            int jump2 = 2 * i;

            // Initialize the current index 'j' to the starting character of the current row 'i'.
            int j = i;
            // 'temp' is used to alternate between jump1 and jump2.
            // 0 means use jump1, 1 means use jump2.
            int temp = 0;

            // While the current index 'j' is within the bounds of the string 's'.
            while (j < s.length()) {
                // Append the character at the current index 'j' to the StringBuilder.
                sb.append(s.charAt(j));

                // Determine the next jump based on 'temp'.
                if (temp == 0) {
                    // If temp is 0, we are on a downward stroke (or first char of row).
                    // The next jump is jump1.
                    j += jump1;
                } else {
                    // If temp is 1, we are on an upward stroke.
                    // The next jump is jump2.
                    j += jump2;
                }
                // Toggle 'temp' to switch between jump1 and jump2 for the next iteration.
                // temp ^ 1 flips 0 to 1 and 1 to 0.
                temp = temp ^ 1;
            }
        }

        // Convert the StringBuilder to a String and return it.
        return sb.toString();
    }
}
```

## Interview Tips
1.  **Visualize the Pattern:** Before coding, draw out the zigzag pattern for a few examples (e.g., `s = "PAYPALISHIRING", numRows = 3` and `numRows = 4`). This helps in understanding the index relationships.
2.  **Explain the Cycle Length:** Clearly articulate how you derived the `cycleLen` (`2 * numRows - 2`) and why it's important for calculating jumps.
3.  **Handle Edge Cases:** Explicitly mention and handle the `numRows = 1` case. Also, discuss how the jump calculations implicitly handle the first and last rows correctly.
4.  **Step-by-Step Traversal:** Walk through the algorithm with an example, showing how `j` and `temp` change for each character appended. This demonstrates a clear understanding of the logic.
5.  **Alternative Approach (Row by Row):** Briefly mention that another approach is to create `numRows` separate `StringBuilder` objects, iterate through the string, and append characters to the correct `StringBuilder` based on the current row and direction. This shows breadth of knowledge.

## Revision Checklist
- [ ] Understand the zigzag pattern and how characters are placed.
- [ ] Calculate the cycle length correctly (`2 * numRows - 2`).
- [ ] Implement the logic for iterating through rows.
- [ ] Correctly calculate the two types of jumps (downward and upward).
- [ ] Use a mechanism (like `temp ^ 1`) to alternate between jumps.
- [ ] Handle the `numRows = 1` edge case.
- [ ] Ensure all characters are appended in the correct order.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Convert BST to Sorted Doubly Linked List
*   Spiral Matrix
*   Matrix Diagonal Traverse
*   Complex Number Multiplication

## Tags
`String` `Math` `Simulation`
