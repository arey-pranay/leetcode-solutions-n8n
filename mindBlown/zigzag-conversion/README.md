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
        int[] y = new int[2];
        
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
Convert a string into a zigzag pattern across a specified number of rows.
The solution calculates the indices for each row and appends characters accordingly.

## Intuition
The core idea is to observe the pattern of character placement in the zigzag. For a given number of rows, the characters form diagonal lines. We can determine the indices of characters that belong to each row by analyzing the "cycle" length of the zigzag. The cycle length is the distance between two characters that fall on the same vertical line in the zigzag pattern.

For `numRows = 3`, the pattern is:
`P   A   H`
`A P L S`
`Y   I   R`

Indices:
`0   4   8`
`1 3 5 7 9`
`2   6   10`

The distance between `0` and `4` is `4`. The distance between `1` and `3` is `2`, and between `3` and `5` is `4`.
The full cycle length is `2 * numRows - 2`. For `numRows = 3`, this is `2 * 3 - 2 = 4`.
For `numRows = 4`, the pattern is:
`P     I    N`
`A   L S  G`
`Y A   H R`
`P     O`

Indices:
`0     6     12`
`1   5 7   11 13`
`2 4   8 10`
`3     9`

The cycle length is `2 * 4 - 2 = 6`.
The characters in row `i` are at indices `j`, `j + cycle_length`, `j + 2 * cycle_length`, etc.
Additionally, for rows other than the first and last, there's a character on the "upward" diagonal. The distance to this character from the previous one in the same row is `cycle_length - 2 * i`.

## Algorithm
1. Handle the edge case where `numRows` is 1. In this case, the string remains unchanged, so return `s`.
2. Calculate the "cycle length" of the zigzag pattern. This is `2 * numRows - 2`. This represents the distance between characters that align vertically in the full zigzag.
3. Initialize an empty `StringBuilder` to store the result.
4. Iterate through each row from `0` to `numRows - 1`.
5. For each row `i`:
    a. Initialize a `current_index` to `i`.
    b. While `current_index` is less than the length of the string `s`:
        i. Append the character at `s.charAt(current_index)` to the `StringBuilder`.
        ii. If the current row `i` is not the first row (`i != 0`) and not the last row (`i != numRows - 1`):
            - Calculate the index of the character on the upward diagonal: `diagonal_index = current_index + cycle_length - 2 * i`.
            - If `diagonal_index` is within the bounds of the string `s`, append `s.charAt(diagonal_index)` to the `StringBuilder`.
        iii. Move `current_index` to the next character in the same row, which is `current_index + cycle_length`.
6. Convert the `StringBuilder` to a `String` and return it.

## Concept to Remember
*   **String Manipulation:** Efficiently building strings using `StringBuilder` to avoid repeated object creation.
*   **Pattern Recognition:** Identifying and generalizing the arithmetic progression of indices based on the zigzag structure.
*   **Edge Case Handling:** Properly addressing scenarios like `numRows = 1`.

## Common Mistakes
*   **Incorrect Cycle Length Calculation:** Miscalculating `2 * numRows - 2` or not handling `numRows = 1` where the cycle length would be 0.
*   **Off-by-One Errors in Indexing:** Errors in calculating the `diagonal_index` or in the main `current_index` increment.
*   **Not Checking Bounds for Diagonal Characters:** Appending characters from `diagonal_index` without verifying if it's within the string's length.
*   **Using `String` concatenation instead of `StringBuilder`:** This leads to poor performance due to excessive object creation.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the string `s` once to append characters to the `StringBuilder`. Each character is visited and appended a constant number of times.
- Space: O(N) - reason: The `StringBuilder` stores the converted string, which can be up to the length of the original string `s`.

## Commented Code
```java
class Solution {
    public String convert(String s, int numRows) {
        // Calculate the distance between characters that start a new "column" in the zigzag pattern.
        // For numRows = 3, this is 2*3 - 2 = 4. (e.g., indices 0, 4, 8...)
        // For numRows = 4, this is 2*4 - 2 = 6. (e.g., indices 0, 6, 12...)
        int cycleLen = 2 * numRows - 2;

        // If numRows is 1, the zigzag pattern is just a straight line, so return the original string.
        // Also handles cases where numRows is 0 or negative, though problem constraints usually prevent this.
        if (cycleLen == 0) { // This condition is equivalent to numRows == 1
            return s;
        }

        // Use StringBuilder for efficient string concatenation.
        StringBuilder sb = new StringBuilder();

        // Iterate through each row of the zigzag pattern.
        for (int i = 0; i < numRows; i++) {
            // For each row 'i', we will append characters that fall on this row.
            // 'j' represents the index of the character in the original string 's'.
            // We start with the first character in this row, which is at index 'i'.
            int j = i;

            // Loop as long as the current index 'j' is within the bounds of the string 's'.
            while (j < s.length()) {
                // Append the character at the current index 'j' to our result.
                sb.append(s.charAt(j));

                // For rows that are not the first (i=0) or the last (i=numRows-1),
                // there's an additional character on the "upward" diagonal.
                if (i != 0 && i != numRows - 1) {
                    // Calculate the index of this diagonal character.
                    // It's 'cycleLen' steps away from the previous character in the same row,
                    // minus '2*i' because the diagonal character is '2*i' steps "up" from the next vertical character.
                    int diagonalIndex = j + cycleLen - 2 * i;

                    // Check if this diagonal index is still within the bounds of the string.
                    if (diagonalIndex < s.length()) {
                        // If it is, append this diagonal character.
                        sb.append(s.charAt(diagonalIndex));
                    }
                }

                // Move to the next character that belongs to this row.
                // This is done by adding the 'cycleLen' to the current index 'j'.
                // This jumps to the next character on the "downward" diagonal.
                j += cycleLen;
            }
        }

        // Convert the StringBuilder to a String and return it.
        return sb.toString();
    }
}
```

## Interview Tips
*   **Visualize the Pattern:** Before coding, draw out the zigzag pattern for small `numRows` (e.g., 3 or 4) and a sample string. This helps in understanding the index relationships.
*   **Explain the Cycle Length:** Clearly articulate how `2 * numRows - 2` is derived and why it's crucial for calculating character positions.
*   **Handle Middle Rows Carefully:** Emphasize the logic for adding the diagonal characters in the intermediate rows, as this is a common point of confusion.
*   **Discuss Alternative Approaches (Optional but good):** Briefly mention that a row-by-row simulation approach (creating `numRows` strings and appending characters) is also possible, but the current index-based approach is more efficient in terms of space.

## Revision Checklist
- [ ] Understand the zigzag pattern and how characters are placed.
- [ ] Correctly calculate the cycle length: `2 * numRows - 2`.
- [ ] Handle the `numRows = 1` edge case.
- [ ] Implement the logic for iterating through rows.
- [ ] Implement the logic for calculating the main character index for each row.
- [ ] Implement the logic for calculating and appending the diagonal character for intermediate rows.
- [ ] Ensure all index calculations are within string bounds.
- [ ] Use `StringBuilder` for efficient string construction.

## Similar Problems
*   [14. Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix/) (Different, but involves string pattern analysis)
*   [58. Length of Last Word](https://leetcode.com/problems/length-of-last-word/) (Simple string manipulation)
*   [6. ZigZag Conversion](https://leetcode.com/problems/zigzag-conversion/) (This is the problem itself)

## Tags
`String` `Math`
