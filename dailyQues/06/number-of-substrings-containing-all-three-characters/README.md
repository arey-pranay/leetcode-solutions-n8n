# Number Of Substrings Containing All Three Characters

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `String` `Sliding Window`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        if (n < 3) return 0;

        int ans = 0;

        // sliding window counts
        int[] cnt = new int[3];
        int left = 0;

        for (int right = 0; right < n; right++) {
            cnt[s.charAt(right) - 'a']++;

            // If window has all 3 characters
            while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
                // all substrings starting at left and ending at right..n-1 are valid
                ans += n - right;

                // shrink from left
                cnt[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return ans;
    }
}
```

---

---

## Quick Revision
The problem asks to count the number of substrings in a given string that contain all three unique characters.
This can be solved by using a sliding window approach with two pointers and an array to keep track of character counts.

## Intuition
The key insight here is to realize that for any substring containing all three characters, there must exist a contiguous subarray within the original string where each character appears exactly once. This allows us to use a sliding window approach to efficiently count the number of such substrings.

## Algorithm

1. Initialize an array `cnt` of size 3 to keep track of character counts.
2. Initialize two pointers, `left` and `right`, to the start of the string.
3. Iterate through the string with the `right` pointer, incrementing the count of the current character in `cnt`.
4. If all three characters are present in the window (i.e., `cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0), then we know that every substring starting at `left` and ending at `right` is a valid substring.
5. Update the answer by adding the number of substrings, which is equal to the length of the string minus the current right pointer position.
6. Shrink the window from the left by decrementing the count of the character at the `left` pointer position in `cnt`, and incrementing `left`.
7. Repeat steps 3-6 until all three characters are no longer present in the window.

## Concept to Remember
• **Sliding Window**: a technique used to efficiently process large datasets by maintaining a "window" of relevant data.
• **Character Counting**: keeping track of the frequency of each character in an array or other data structure.
• **Contiguous Subarrays**: arrays where each element appears exactly once.

## Common Mistakes
• Forgetting to handle edge cases (e.g., strings with less than 3 unique characters).
• Not initializing the `cnt` array properly, leading to incorrect counts.
• Misunderstanding the sliding window approach and incorrectly updating the answer.

## Complexity Analysis
- Time: O(n) - where n is the length of the string, as we make a single pass through the string.
- Space: O(1) - we use a constant amount of space to store the `cnt` array and pointers.

## Commented Code
```java
class Solution {
    public int numberOfSubstrings(String s) {
        // Initialize variables
        int n = s.length();
        if (n < 3) return 0; // edge case: fewer than 3 unique characters

        // Initialize character count array and pointers
        int[] cnt = new int[3];
        int left = 0;

        int ans = 0;
        for (int right = 0; right < n; right++) {
            // Increment count of current character in `cnt`
            cnt[s.charAt(right) - 'a']++;

            while (cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
                // Update answer with number of substrings
                ans += n - right;

                // Shrink window from left
                cnt[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return ans;
    }
}
```

## Interview Tips

* Make sure to understand the problem clearly and ask for clarification if needed.
* Use a sliding window approach whenever possible, as it can significantly reduce time complexity.
* Practice explaining your thought process and solution to others.

## Revision Checklist
- [ ] Understand the problem statement
- [ ] Review and practice the sliding window technique
- [ ] Implement and test the solution in Java
- [ ] Review edge cases and corner cases

## Similar Problems
* "Minimum Window Substring" (LeetCode #76)
* "Substring with Concatenation of All Words" (LeetCode #438)

## Tags
`Array` `Hash Map`
