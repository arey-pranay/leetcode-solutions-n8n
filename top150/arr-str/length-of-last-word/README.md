# Length Of Last Word

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `String`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int j = s.length()-1;
        while(j>=0 && s.charAt(j) == ' ') j--;
        int temp = j;
        while(j>=0 && s.charAt(j) != ' ')j--;
        return temp-j;
    }
}
```

---

---
## Quick Revision
Find the length of the last word in a given string, ignoring leading and trailing spaces.

We solve this problem by iterating through the input string from right to left, counting characters until we encounter a space or reach the beginning of the string.

## Intuition
The key insight is that since we're looking for the last word, we can start at the end of the string. We only need to iterate backwards once, because if there's no word (only spaces), the result should be 0.

## Algorithm
1. Initialize two pointers: `j` at the end of the string and `temp` at the same position.
2. Iterate from `j` to the beginning of the string while `s.charAt(j) == ' '`:
   - Decrement `j`.
3. Once a non-space character is found, store the current value of `j` in `temp`.
4. Continue iterating until `s.charAt(j) != ' '`, decrementing `j` each time.
5. Return `temp-j`.

## Concept to Remember
• **Time complexity**: O(n), where n is the length of the string.
• **Space complexity**: O(1), as we only use a constant amount of space.

## Common Mistakes
• Failing to handle leading and trailing spaces separately, instead trying to iterate through them as part of the word.
• Misunderstanding the concept of "last word" and incorrectly calculating indices.
• Not initializing `temp` before the second while loop iteration.

## Complexity Analysis
- Time: O(n), where n is the length of the string, since we're potentially iterating through all characters once.
- Space: O(1), as we only use a constant amount of space to store pointers and temporary variables.

## Commented Code
```java
class Solution {
    public int lengthOfLastWord(String s) {
        // Initialize j at the end of the string, decrementing until a non-space character is found.
        int j = s.length()-1;
        while(j>=0 && s.charAt(j) == ' ') { // Ignore leading spaces
            j--;
        }
        
        // Store the current index in temp before finding the start of the last word.
        int temp = j;
        
        // Decrement from temp until a space is encountered, giving us the length of the last word.
        while(j>=0 && s.charAt(j) != ' ') {
            j--;
        }
        
        return temp-j; // Return the difference between temp and j
    }
}
```

## Interview Tips
• Make sure to understand the problem statement carefully before diving into code.
• Practice iterating through strings from both ends (left-to-right and right-to-left) for different problems.
• Use temporary variables effectively to store key indices or values.

## Revision Checklist
- [ ] Understand problem requirements clearly.
- [ ] Implement iteration from end of string correctly.
- [ ] Store `temp` before finding start of last word.
- [ ] Check complexity analysis.

## Similar Problems
* 58. Length Of Last Word (Easy)
* 434. Number of Steps to Reduce a Height (Medium)
