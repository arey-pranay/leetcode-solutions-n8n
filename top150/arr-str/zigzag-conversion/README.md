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
The core idea is to observe the pattern of character placement in the zigzag. For a given number of rows, the characters are placed in a repeating cycle. The first row gets characters at indices 0, `cycleLen`, `2*cycleLen`, etc. The last row gets characters at indices `numRows-1`, `numRows-1 + cycleLen`, etc. Intermediate rows get characters at two positions within each cycle: one going down and one going up. By calculating the length of this cycle and the offsets for each row, we can directly construct the converted string.

## Algorithm
1. Handle the edge case where `numRows` is 1. In this case, the string remains unchanged, so return `s`.
2. Calculate the `cycleLen`, which is the distance between characters in the same column on the first row. This is `2 * numRows - 2`.
3. Initialize an empty `StringBuilder` to store the result.
4. Iterate through each row from `0` to `numRows - 1`.
5. For each row `i`:
    a. Iterate through the string `s` with a step of `cycleLen`, starting from index `i`. Append `s.charAt(j)` to the `StringBuilder`.
    b. If the current row `i` is not the first or the last row (i.e., `i > 0` and `i < numRows - 1`), calculate the index of the character on the "upward" diagonal. This index is `j + cycleLen - 2 * i`.
    c. If this upward index is within the bounds of the string `s`, append `s.charAt(upwardIndex)` to the `StringBuilder`.
6. Return the `StringBuilder` converted to a `String`.

## Concept to Remember
*   **Pattern Recognition:** Identifying the repeating cycle and offsets in the zigzag pattern is crucial.
*   **String Manipulation:** Efficiently building the result string using `StringBuilder`.
*   **Index Calculation:** Carefully deriving the correct indices for characters in intermediate rows.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly calculating `cycleLen` or the upward diagonal index.
*   **Handling edge rows:** Forgetting to treat the first and last rows differently, as they only have characters in one "direction" per cycle.
*   **Inefficient string concatenation:** Using `+` operator repeatedly instead of `StringBuilder`.
*   **Not checking bounds:** Appending characters from indices that are out of the string's length.

## Complexity Analysis
- Time: O(N) - where N is the length of the input string `s`. We iterate through the string once to append characters.
- Space: O(N) - for the `StringBuilder` to store the result. In the worst case, the result string has the same length as the input string.

## Commented Code
```java
class Solution {
    public String convert(String s, int numRows) {
        // If numRows is 1, the zigzag pattern is just a straight line, so return the original string.
        if (numRows == 1) {
            return s;
        }

        // Calculate the length of one full cycle in the zigzag pattern.
        // This is the distance between characters that start a new "downward" stroke.
        // For example, with numRows = 3, the pattern is:
        // 0   4   8
        // 1 3 5 7 9
        // 2   6   10
        // The cycle length is 2 * numRows - 2 = 2 * 3 - 2 = 4.
        int cycleLen = 2 * numRows - 2;

        // Use a StringBuilder for efficient string construction.
        StringBuilder sb = new StringBuilder();

        // Iterate through each row to collect characters for that row.
        for (int i = 0; i < numRows; i++) {
            // Iterate through the string, picking characters that fall on the "downward" stroke for the current row 'i'.
            // 'j' represents the starting index of each downward stroke for the current row.
            for (int j = 0; j + i < s.length(); j += cycleLen) {
                // Append the character at the current downward stroke position.
                sb.append(s.charAt(j + i));

                // For intermediate rows (not the first or the last), there's an additional character on the "upward" stroke.
                // This character's index is calculated relative to the start of the current cycle ('j').
                // The offset for the upward character is (cycleLen - 2 * i).
                // Example: numRows = 4, cycleLen = 6.
                // Row 1 (i=1):
                // Downward: j + 1
                // Upward: j + (6 - 2*1) = j + 4
                // Indices: 0, 6, 12... (downward)
                //          1, 7, 13... (downward)
                //          2, 8, 14... (downward)
                //          3, 9, 15... (downward)
                //
                // Row 1 (i=1):
                // j=0: append s.charAt(0+1) -> s.charAt(1)
                //      upward index: 0 + (6 - 2*1) = 4. If 4 < s.length(), append s.charAt(4).
                // j=6: append s.charAt(6+1) -> s.charAt(7)
                //      upward index: 6 + (6 - 2*1) = 10. If 10 < s.length(), append s.charAt(10).
                if (i != 0 && i != numRows - 1) {
                    int upwardIndex = j + cycleLen - 2 * i;
                    // Check if the calculated upward index is within the bounds of the string.
                    if (upwardIndex < s.length()) {
                        // Append the character at the upward stroke position.
                        sb.append(s.charAt(upwardIndex));
                    }
                }
            }
        }

        // Convert the StringBuilder to a String and return it.
        return sb.toString();
    }
}
```

## Interview Tips
*   **Visualize the pattern:** Draw out the zigzag for small examples (`s = "PAYPALISHIRING"`, `numRows = 3` and `numRows = 4`) to understand the index relationships.
*   **Explain the `cycleLen`:** Clearly articulate why `2 * numRows - 2` is the correct cycle length and how it relates to the up and down movements.
*   **Handle edge cases:** Emphasize how you've considered `numRows = 1` and the first/last rows.
*   **Discuss alternative approaches:** Briefly mention that a simulation approach (creating `numRows` strings and appending characters) is also possible but less efficient.

## Revision Checklist
- [ ] Understand the zigzag pattern and how characters are placed.
- [ ] Correctly calculate `cycleLen`.
- [ ] Implement the loop for collecting characters for each row.
- [ ] Correctly calculate and append characters for intermediate rows (upward stroke).
- [ ] Handle edge cases (`numRows = 1`, first/last row).
- [ ] Use `StringBuilder` for efficiency.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Convert Sorted Array to Binary Search Tree
*   Spiral Matrix
*   Text Justification

## Tags
`String` `Math`
