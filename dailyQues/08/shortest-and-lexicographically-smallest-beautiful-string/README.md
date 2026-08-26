# Shortest And Lexicographically Smallest Beautiful String

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Sliding Window`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
      int n = s.length();
      int r = 0;
      int l=0;
      int cnt=0;
      String ans = "";
      while(r<n){
        if(s.charAt(r) == '1')cnt++;
        if(cnt > k){
          while(s.charAt(l) == '0') l++;
          l++;cnt--;
        }
        if(cnt==k){
         while(l<r && s.charAt(l)=='0') l++;
          int currLength = r-l+1;
          String curr = s.substring(l,r+1);
          if(ans.isEmpty() || currLength < ans.length() || (currLength==ans.length()&&curr.compareTo(ans)<0)) ans = new String(curr);
        }
        r++;
      }
      return ans;
    }
}
```

---

---
## Quick Revision
Find the shortest substring containing exactly `k` ones.
If multiple shortest substrings exist, return the lexicographically smallest one.

## Intuition
The problem asks for the "best" substring that meets a specific criterion (exactly `k` ones). This suggests a sliding window approach. We want to expand the window until it contains `k` ones, then try to shrink it from the left while maintaining the `k` ones count. Simultaneously, we need to keep track of the shortest and lexicographically smallest valid substring found so far.

## Algorithm
1. Initialize two pointers, `l` (left) and `r` (right), both to 0.
2. Initialize a counter `cnt` to 0, representing the number of '1's in the current window.
3. Initialize a string `ans` to an empty string, which will store the best beautiful substring found.
4. Iterate with the `r` pointer from the beginning to the end of the string `s`:
    a. If `s.charAt(r)` is '1', increment `cnt`.
    b. If `cnt` becomes greater than `k`:
        i. While `s.charAt(l)` is '0', increment `l` to shrink the window from the left.
        ii. Increment `l` one more time (to remove the '1' that made `cnt > k`).
        iii. Decrement `cnt`.
    c. If `cnt` is exactly equal to `k`:
        i. While `l` is less than `r` and `s.charAt(l)` is '0', increment `l` to ensure the substring starts with a '1' (or the first character of the window). This step is crucial for lexicographical comparison.
        ii. Calculate the current substring's length: `currLength = r - l + 1`.
        iii. Extract the current substring: `curr = s.substring(l, r + 1)`.
        iv. Compare `curr` with `ans`:
            - If `ans` is empty, set `ans = curr`.
            - If `currLength` is less than `ans.length()`, update `ans = curr`.
            - If `currLength` is equal to `ans.length()` and `curr` is lexicographically smaller than `ans`, update `ans = curr`.
    d. Increment `r` to expand the window.
5. Return `ans`.

## Concept to Remember
*   **Sliding Window Technique:** Efficiently processing contiguous subarrays/substrings by maintaining a window and adjusting its boundaries.
*   **Lexicographical Comparison:** Understanding how strings are ordered alphabetically, crucial for selecting the "smallest" among equally sized valid substrings.
*   **Two Pointers:** Using multiple pointers to traverse and manipulate data structures, often in conjunction with other techniques like sliding window.

## Common Mistakes
*   **Incorrectly shrinking the window:** Not properly handling the case where the leftmost character is '0' when shrinking, leading to invalid substrings or incorrect counts.
*   **Missing lexicographical comparison:** Only focusing on length and not comparing strings alphabetically when lengths are equal.
*   **Off-by-one errors:** Incorrectly calculating substring boundaries or window lengths.
*   **Not handling the initial empty `ans` state:** Failing to assign the first valid substring to `ans`.

## Complexity Analysis
*   Time: O(N) - The `r` pointer traverses the string once. The `l` pointer also traverses at most once. Substring operations and comparisons take time proportional to the substring length, but in total, across all operations, it's bounded by O(N).
*   Space: O(N) - In the worst case, the `substring` operation might create a new string of length up to N. The `ans` string can also be up to N.

## Commented Code
```java
class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
      int n = s.length(); // Get the length of the input string.
      int r = 0; // Initialize the right pointer of the sliding window.
      int l = 0; // Initialize the left pointer of the sliding window.
      int cnt = 0; // Initialize a counter for the number of '1's in the current window.
      String ans = ""; // Initialize the answer string to store the best beautiful substring found so far.

      // Iterate through the string using the right pointer.
      while(r < n){
        // If the current character is '1', increment the count of '1's.
        if(s.charAt(r) == '1') cnt++;

        // If the count of '1's exceeds k, we need to shrink the window from the left.
        if(cnt > k){
          // Move the left pointer past any '0's to find the next '1'.
          while(s.charAt(l) == '0') l++;
          // Move the left pointer past the '1' that caused the count to exceed k.
          l++;
          // Decrement the count of '1's as we've removed one from the window.
          cnt--;
        }

        // If the count of '1's is exactly k, we have a potential beautiful substring.
        if(cnt == k){
          // Ensure the window starts at the first '1' (or the beginning of the window if it's all '1's)
          // This is important for lexicographical comparison.
          while(l < r && s.charAt(l) == '0') l++;

          // Calculate the length of the current beautiful substring.
          int currLength = r - l + 1;
          // Extract the current substring.
          String curr = s.substring(l, r + 1);

          // Compare the current substring with the best one found so far.
          // If ans is empty, or current is shorter, or current is same length but lexicographically smaller.
          if(ans.isEmpty() || currLength < ans.length() || (currLength == ans.length() && curr.compareTo(ans) < 0)) {
            // Update ans with the current substring.
            ans = new String(curr);
          }
        }
        // Move the right pointer to expand the window.
        r++;
      }
      // Return the shortest and lexicographically smallest beautiful substring found.
      return ans;
    }
}
```

## Interview Tips
*   Clearly explain the sliding window approach and why it's suitable for this problem.
*   Emphasize the two conditions for updating the `ans`: shortest length, and then lexicographically smallest for equal lengths.
*   Walk through an example with the pointers and `cnt` to demonstrate the logic.
*   Be prepared to discuss edge cases like `k=0` (though not applicable here as `k` is at least 1), or strings with no '1's.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the sliding window pattern.
- [ ] Implement the window expansion and contraction logic.
- [ ] Correctly handle the count of '1's.
- [ ] Implement the logic for updating the best substring based on length and lexicographical order.
- [ ] Test with various inputs, including edge cases.

## Similar Problems
*   Longest Substring Without Repeating Characters
*   Minimum Window Substring
*   Substring with Concatenation of All Words
*   Find All Anagrams in a String

## Tags
`String` `Sliding Window` `Two Pointers`
