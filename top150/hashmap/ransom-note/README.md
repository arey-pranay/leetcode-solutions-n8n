# Ransom Note

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `String` `Counting`  
**Time:** O(M + N)  
**Space:** O(K)

---

## Solution (java)

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        //we can do this also via using frequency array.
        HashMap<Character,Integer> available = new HashMap<>();
        for(char c : magazine.toCharArray()) available.put(c,available.getOrDefault(c,0)+1);
        for(char c : ransomNote.toCharArray()){
            int rem = available.getOrDefault(c,0)-1;
            if(rem < 0) return false;
            available.put(c,rem);
        }
        return true;
    }
}
```

---

---
## Quick Revision
Given two strings, determine if the ransom note can be constructed from the magazine.
We solve this by counting character frequencies in the magazine and then decrementing them as we check the ransom note.

## Intuition
The core idea is that to construct the ransom note, we must have at least as many of each character available in the magazine as required by the ransom note. If at any point we need a character that isn't available (or we've already used up all instances of it), we cannot construct the note. A frequency map (or array) is a natural way to keep track of character counts.

## Algorithm
1. Create a frequency map (e.g., a HashMap or an array of size 26 for lowercase English letters) to store the counts of characters in the `magazine` string.
2. Iterate through each character in the `magazine` string. For each character, increment its count in the frequency map.
3. Iterate through each character in the `ransomNote` string.
4. For each character in `ransomNote`:
    a. Decrement its count in the frequency map.
    b. If the count for that character becomes negative (meaning we don't have enough of it in the magazine), return `false` immediately.
5. If we successfully iterate through all characters in `ransomNote` without returning `false`, it means we have enough characters, so return `true`.

## Concept to Remember
*   **Frequency Counting:** Efficiently tracking the occurrences of elements within a collection.
*   **Hash Maps (or Arrays as Frequency Tables):** Data structures suitable for mapping keys (characters) to values (counts).
*   **Greedy Approach:** Making the locally optimal choice at each step (checking character availability) to achieve a global optimum (can construct the note).

## Common Mistakes
*   **Incorrectly handling missing characters:** Not checking if a character from `ransomNote` even exists in the `magazine`'s frequency map before decrementing. `getOrDefault` helps here.
*   **Off-by-one errors in counts:** Mismanaging the decrementing process, leading to incorrect checks for character availability.
*   **Not handling case sensitivity:** Assuming all characters are lowercase when they might not be (though this problem usually implies lowercase English letters).
*   **Inefficient data structure choice:** Using a less optimal structure than a hash map or frequency array for character counts.

## Complexity Analysis
*   **Time:** O(M + N) - where M is the length of `magazine` and N is the length of `ransomNote`. We iterate through `magazine` once to build the frequency map and then through `ransomNote` once to check availability.
*   **Space:** O(K) - where K is the number of unique characters in the `magazine` (or the size of the alphabet if using a fixed-size array). In the worst case, K can be up to 26 for lowercase English letters.

## Commented Code
```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        // Create a HashMap to store the frequency of characters available in the magazine.
        // The key is the character, and the value is its count.
        HashMap<Character,Integer> available = new HashMap<>();

        // Iterate through each character in the magazine string.
        for(char c : magazine.toCharArray()){
            // For each character, increment its count in the 'available' map.
            // getOrDefault(c, 0) retrieves the current count of 'c', or 0 if it's not present yet.
            // Then, we add 1 to this count and put it back into the map.
            available.put(c,available.getOrDefault(c,0)+1);
        }

        // Iterate through each character in the ransomNote string.
        for(char c : ransomNote.toCharArray()){
            // Get the current count of character 'c' from the 'available' map.
            // If 'c' is not in the map, getOrDefault returns 0.
            // We then subtract 1 because we are trying to use one instance of this character.
            int rem = available.getOrDefault(c,0)-1;

            // Check if the remaining count is less than 0.
            // This means we needed this character but either it wasn't in the magazine at all,
            // or we've already used up all available instances of it.
            if(rem < 0) {
                // If we don't have enough of this character, we cannot construct the ransom note.
                return false;
            }

            // Update the count of character 'c' in the 'available' map with the new remaining count.
            available.put(c,rem);
        }

        // If we have successfully iterated through all characters in the ransomNote
        // without returning false, it means we have enough characters from the magazine.
        return true;
    }
}
```

## Interview Tips
*   **Clarify Constraints:** Ask about the character set (e.g., lowercase English letters, ASCII, Unicode) and string lengths. This might influence whether a `HashMap` or a fixed-size array is better.
*   **Explain the Frequency Map:** Clearly articulate why a frequency map is the chosen data structure and how it helps solve the problem efficiently.
*   **Walk Through an Example:** Use a small example like `ransomNote = "aab"`, `magazine = "aabbc"` to demonstrate your algorithm step-by-step.
*   **Consider Edge Cases:** Discuss what happens with empty strings for `ransomNote` or `magazine`.

## Revision Checklist
- [ ] Understand the problem statement: Can `ransomNote` be formed using characters from `magazine`?
- [ ] Identify the core constraint: Character availability.
- [ ] Choose an appropriate data structure for frequency counting (HashMap or array).
- [ ] Implement the frequency counting for `magazine`.
- [ ] Implement the character checking/decrementing for `ransomNote`.
- [ ] Handle cases where a character is needed but not available.
- [ ] Analyze time and space complexity.
- [ ] Test with edge cases (empty strings, identical strings, impossible cases).

## Similar Problems
*   Group Anagrams
*   Valid Anagram
*   Two Sum (uses hash map for lookups)
*   Check Permutation

## Tags
`Array` `Hash Map` `String`
