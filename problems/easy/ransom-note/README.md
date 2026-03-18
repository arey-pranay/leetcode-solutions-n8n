# Ransom Note

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `String` `Counting`  
**Time:** O(n + m)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] freq = new int[26];
        for(char c : ransomNote.toCharArray()) freq[c-'a']++;
        for(char c : magazine.toCharArray()) freq[c-'a']--;
        for(char c : ransomNote.toCharArray()) if(freq[c-'a'] > 0) return false;
        return true;
    }
}
```

---

---
## Quick Revision
We need to determine if a given `ransomNote` can be constructed from the words in `magazine`. We solve this problem by counting the frequency of characters in both strings and checking if we have enough characters in `magazine` to form `ransomNote`.

## Intuition
The key insight is that we only need to check if each character in `ransomNote` has a sufficient count in `magazine`, without actually constructing the ransom note.

## Algorithm

1. Create an array `freq` of size 26 to store the frequency of characters in both strings.
2. Count the frequency of characters in `ransomNote`.
3. Subtract the frequency of each character in `magazine` from the corresponding count in `freq`.
4. Iterate through `ransomNote` again and return `false` if any character has a positive count left in `freq`.
5. If we reach this point, return `true`.

## Concept to Remember

* Frequency counting: Counting the occurrence of each item (in this case, characters) in an array or string.
* Array manipulation: Modifying elements in an array based on conditions.

## Common Mistakes

* Forgetting to initialize `freq` correctly.
* Not checking for sufficient counts before returning `true`.
* Confusing character codes and ASCII values.

## Complexity Analysis
- Time: O(n + m) - n is the length of `ransomNote` and m is the length of `magazine`, because we iterate through both strings twice.
- Space: O(1) - The space complexity is constant, as we only use a fixed-size array to store frequencies.

## Commented Code
```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        // Create an array of size 26 to store character frequencies
        int[] freq = new int[26];
        
        // Count the frequency of characters in ransomNote
        for(char c : ransomNote.toCharArray()) 
            freq[c-'a']++; // Increment count by converting char to index (c-'a')
        
        // Subtract the frequency of each character in magazine from freq
        for(char c : magazine.toCharArray()) 
            freq[c-'a']--; // Decrement count
        
        // Check if all characters have sufficient counts
        for(char c : ransomNote.toCharArray()) 
            if(freq[c-'a'] > 0) // If a character has a positive count, return false
                return false;
        
        // If we reach this point, return true
        return true;
    }
}
```

## Interview Tips

* Pay attention to the constraints: The problem statement specifies that we have enough space to store all frequencies.
* Don't overcomplicate: We can solve this problem using a simple frequency counting approach.

## Revision Checklist
- [ ] Review frequency counting concepts.
- [ ] Practice solving similar problems with different inputs.

## Similar Problems

* LeetCode 151: `String` `RansomNote`
* LeetCode 299: `Array` `Bulls and Cows`

## Tags
`Array` `Hash Map` `Frequency Counting`

## My Notes
character array for ransom Note
