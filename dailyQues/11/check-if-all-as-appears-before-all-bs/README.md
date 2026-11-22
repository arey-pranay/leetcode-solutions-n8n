# Check If All As Appears Before All Bs

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `String`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean checkString(String s) {
        boolean bFound = false;
        for(char c : s.toCharArray()){
            if(c=='b') bFound=true;
            if(bFound && c=='a') return false;
        }
        return true;
    }
}
```

---

---

## Quick Revision
Check if all 'a's appear before all 'b's in a given string.
Solve by iterating through the string and keeping track of whether we've seen a 'b' before an 'a'.

## Intuition
The key insight here is that if we've seen a 'b' and then an 'a', it means that all 'a's must have appeared before all 'b's, so we can return `true`. If we haven't seen a 'b' before an 'a', then we know that all 'a's haven't appeared before all 'b's, so we can return `false`.

## Algorithm
1. Initialize a flag `bFound` to `false`.
2. Iterate through each character `c` in the input string `s`.
3. If `c` is 'b', set `bFound` to `true`.
4. If `bFound` is `true` and `c` is 'a', return `false`.
5. If we've iterated through the entire string and haven't returned `false`, return `true`.

## Concept to Remember
* Using flags to keep track of important information
* Iterating through a string character by character

## Common Mistakes
* Not initializing the `bFound` flag correctly
* Not checking if `c` is 'a' when `bFound` is `true`
* Not returning `true` when the loop finishes and no `false` has been returned

## Complexity Analysis
- Time: O(n) - where n is the length of the input string, as we're iterating through the string once
- Space: O(1) - we're using a constant amount of space to store the `bFound` flag

## Commented Code
```java
class Solution {
    public boolean checkString(String s) {
        // Initialize a flag to keep track of whether we've seen a 'b'
        boolean bFound = false;
        
        // Iterate through each character in the input string
        for(char c : s.toCharArray()) {
            // If the current character is 'b', set bFound to true
            if(c == 'b') bFound = true;
            
            // If we've seen a 'b' and the current character is 'a', return false
            if(bFound && c == 'a') return false;
        }
        
        // If we've iterated through the entire string and haven't returned false, return true
        return true;
    }
}
```

## Interview Tips
* Make sure to initialize flags correctly
* Pay attention to the order of operations when iterating through a string
* Practice solving problems with flags and iteration to improve your skills

## Revision Checklist
- [ ] Understand the problem statement and requirements
- [ ] Identify the key insight or "aha moment" for the solution
- [ ] Write out the algorithm from scratch
- [ ] Check for common mistakes and edge cases
- [ ] Review the complexity analysis and code

## Similar Problems
* LeetCode 42: Trapping Rain Water
* LeetCode 159: Longest Substring with At Least K Repeating Characters

## Tags
`Array` `Hash Map` `String` `Flags`
