# Create Grid With Exactly One Path

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Hash Map` `StringBuilder` `String` `Efficient String Generation`  
**Time:** O(m*n)  
**Space:** O(m*n)

---

## Solution (java)

```java
class Solution {
    public String[] createGrid(int m, int n) {
        String[] ans = new String[m];
        StringBuilder sb = new StringBuilder("");
        sb.repeat(".",n);
        ans[0] = sb.toString();
        for(int i=1;i<m;i++){
            sb = new StringBuilder("");
            sb.repeat("#",n-1);
            sb.append(".");
            ans[i] = sb.toString();
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Create a grid with `m` rows and `n` columns, where all cells are filled with `.` except for the last row which is filled with `#` and a `.` at the end.
We can solve this problem by generating the grid row by row, using a StringBuilder to efficiently create the string representation of each row.

## Intuition
The key insight here is to use a StringBuilder to efficiently create the string representation of each row, rather than concatenating strings using `+` operator, which can be expensive for large inputs. We can use the `repeat` method to create the desired string pattern.

## Algorithm
1. Initialize an array `ans` of size `m` to store the grid rows.
2. Initialize a StringBuilder `sb` to store the current row.
3. Repeat the following steps for each row `i` from 1 to `m-1`:
	* Initialize `sb` to a new StringBuilder.
	* Repeat `#` `n-1` times using `sb.repeat("#",n-1)`.
	* Append a `.` to `sb` using `sb.append(".")`.
	* Store the string representation of `sb` in `ans[i]`.
4. For the first row (row 0), repeat `.` `n` times using `sb.repeat(".",n)`.
5. Store the string representation of `sb` in `ans[0]`.
6. Return the `ans` array.

## Concept to Remember
* Using a StringBuilder to efficiently create string representations.
* The `repeat` method for repeated string operations.
* Understanding the trade-offs between concatenation using `+` and using a StringBuilder.

## Common Mistakes
* Failing to use a StringBuilder to efficiently create string representations.
* Misunderstanding the `repeat` method and its implications.
* Not considering the performance implications of concatenation using `+`.

## Complexity Analysis
- Time: O(m*n) - We are creating a string of length `n` for each of the `m` rows.
- Space: O(m*n) - We are storing a string of length `n` for each of the `m` rows.

## Commented Code
```java
class Solution {
    public String[] createGrid(int m, int n) {
        String[] ans = new String[m];
        // Initialize a StringBuilder to store the current row
        StringBuilder sb = new StringBuilder("");
        // Repeat '.' n times to create the first row
        sb.repeat(".", n);
        // Store the string representation of sb in ans[0]
        ans[0] = sb.toString();
        
        // Repeat for each row i from 1 to m-1
        for (int i = 1; i < m; i++) {
            // Initialize sb to a new StringBuilder
            sb = new StringBuilder("");
            // Repeat '#' n-1 times
            sb.repeat("#", n - 1);
            // Append a '.' to sb
            sb.append(".");
            // Store the string representation of sb in ans[i]
            ans[i] = sb.toString();
        }
        
        // Return the ans array
        return ans;
    }
}
```

## Interview Tips
* Practice generating strings efficiently using a StringBuilder.
* Understand the trade-offs between concatenation using `+` and using a StringBuilder.
* Be prepared to explain the `repeat` method and its implications.

## Revision Checklist
- [ ] Review the use of a StringBuilder to efficiently create string representations.
- [ ] Understand the implications of the `repeat` method.
- [ ] Practice generating strings efficiently using a StringBuilder.

## Similar Problems
* [LeetCode 1137](https://leetcode.com/problems/n-th-tribonacci-number/)
* [LeetCode 1221](https://leetcode.com/problems/split-a-string-in-segments-of-k-k-length/)

## Tags
`Array` `Hash Map` `StringBuilder` `String` `Efficient String Generation`
