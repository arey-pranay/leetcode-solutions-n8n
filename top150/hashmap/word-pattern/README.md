# Word Pattern

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `String`  
**Time:** O(N)  
**Space:** O(K)

---

## Solution (java)

```java
class Solution {
    public boolean wordPattern(String pattern, String s) {
        HashMap<Character,String> hm = new HashMap<>();
        String[] arr = s.split(" ");
        if(pattern.length() != arr.length) return false;
        
        for(int i=0;i<pattern.length();i++){
            char a = pattern.charAt(i);
            String b = arr[i];
            if(!hm.containsKey(a)){
                if(hm.containsValue(b)) return false;
                hm.put(a,b);
            }
            else{
                if(!hm.get(a).equals(b)) return false;
            }
        }
      
        return true;
    }
}
```

---

---
## Quick Revision
Given a pattern string and a string of words, determine if the pattern matches the words.
This is solved by mapping characters in the pattern to words in the string and ensuring a consistent one-to-one mapping.

## Intuition
The core idea is to establish a consistent mapping between characters in the `pattern` and words in the string `s`. If a character in `pattern` has already been mapped to a word, the current word must be the same as the previously mapped word. Conversely, if a word has already been mapped to a character, the current character must be the same as the previously mapped character. If at any point this consistency is broken, the pattern does not match.

## Algorithm
1. Split the input string `s` into an array of words using spaces as delimiters.
2. Check if the length of the `pattern` string is equal to the number of words in the array. If not, return `false` immediately, as a mismatch in counts means no valid pattern can exist.
3. Initialize a HashMap to store the mapping from characters in `pattern` to words in `s`.
4. Iterate through the `pattern` string and the array of words simultaneously using an index `i`.
5. For each character `a` from `pattern` and corresponding word `b` from `arr`:
    a. If the character `a` is not yet a key in the HashMap:
        i. Check if the word `b` is already a value in the HashMap. If it is, it means another character has already been mapped to this word, violating the one-to-one mapping. Return `false`.
        ii. If `b` is not a value, add the mapping `(a, b)` to the HashMap.
    b. If the character `a` is already a key in the HashMap:
        i. Retrieve the word previously mapped to `a` from the HashMap.
        ii. Compare this retrieved word with the current word `b`. If they are not equal, the mapping is inconsistent. Return `false`.
6. If the loop completes without returning `false`, it means a consistent one-to-one mapping was found. Return `true`.

## Concept to Remember
*   **Hash Maps (Dictionaries):** Efficiently storing and retrieving key-value pairs, crucial for maintaining mappings.
*   **One-to-One Mapping (Bijection):** The problem requires a strict one-to-one correspondence between pattern characters and words.
*   **String Manipulation:** Splitting strings and accessing characters.
*   **Edge Case Handling:** Checking for length mismatches early.

## Common Mistakes
*   **Forgetting to check for duplicate word mappings:** Only checking if a character is already mapped, but not if the *word* is already mapped to a *different* character.
*   **Incorrectly handling the initial mapping:** Not adding the mapping when a character is encountered for the first time.
*   **Off-by-one errors in loops or array indexing.**
*   **Not handling the case where `s` is empty or `pattern` is empty.** (Though the provided solution implicitly handles some of these due to length checks).

## Complexity Analysis
- Time: O(N) - where N is the length of the `pattern` string (and also the number of words in `s`). We iterate through the pattern once, and `split` operation takes time proportional to the length of `s`. HashMap operations (put, get, containsKey, containsValue) are O(1) on average.
- Space: O(K) - where K is the number of unique characters in the `pattern` (at most 26 for English alphabet) or the number of unique words in `s`, whichever is smaller. This is for storing the mappings in the HashMap.

## Commented Code
```java
class Solution {
    public boolean wordPattern(String pattern, String s) {
        // Initialize a HashMap to store mappings from characters in 'pattern' to words in 's'.
        HashMap<Character,String> hm = new HashMap<>();
        // Split the input string 's' into an array of words using space as a delimiter.
        String[] arr = s.split(" ");

        // If the number of characters in 'pattern' does not match the number of words in 's',
        // a pattern match is impossible. Return false.
        if(pattern.length() != arr.length) return false;

        // Iterate through the pattern and the array of words using a single index 'i'.
        for(int i=0;i<pattern.length();i++){
            // Get the current character from the pattern.
            char a = pattern.charAt(i);
            // Get the current word from the split string array.
            String b = arr[i];

            // Check if the current character 'a' is NOT already in our mapping HashMap.
            if(!hm.containsKey(a)){
                // If 'a' is new, we must ensure that the word 'b' has not been mapped to ANY other character before.
                // If 'b' is already a value in the HashMap, it means a different character is already mapped to this word, violating the one-to-one rule.
                if(hm.containsValue(b)) return false;
                // If 'b' is also new (not a value for any other key), then establish the new mapping from 'a' to 'b'.
                hm.put(a,b);
            }
            // If the current character 'a' IS already in our mapping HashMap.
            else{
                // Retrieve the word that 'a' was previously mapped to.
                // Check if this previously mapped word is NOT equal to the current word 'b'.
                // If they are not equal, the pattern is inconsistent. Return false.
                if(!hm.get(a).equals(b)) return false;
            }
        }

        // If the loop completes without finding any inconsistencies, it means the pattern matches.
        return true;
    }
}
```

## Interview Tips
*   **Clarify the "one-to-one" requirement:** Ensure you understand that both directions of mapping must be unique (char -> word, and word -> char).
*   **Explain your HashMap strategy:** Clearly articulate why a HashMap is suitable and how you'll use it to track mappings and detect violations.
*   **Discuss edge cases:** Mention what happens if `pattern` or `s` is empty, or if `s` has leading/trailing spaces or multiple spaces between words (though `split(" ")` handles some of this).
*   **Consider an alternative approach:** Briefly mention using two HashMaps (one for char->word, one for word->char) or using `Collections.frequency` if allowed, to show breadth of knowledge.

## Revision Checklist
- [ ] Understand the problem statement: pattern string vs. word string.
- [ ] Recognize the need for a consistent mapping.
- [ ] Implement string splitting correctly.
- [ ] Handle length mismatch between pattern and words.
- [ ] Use a HashMap to store character-to-word mappings.
- [ ] Crucially, check for duplicate word mappings (word already mapped to a different character).
- [ ] Verify existing character mappings against current words.
- [ ] Consider time and space complexity.
- [ ] Test with examples: "abba", "dog cat cat dog" (true); "abba", "dog cat cat fish" (false); "aaaa", "dog cat cat dog" (false).

## Similar Problems
*   Isomorphic Strings
*   Group Anagrams
*   Find And Replace in String

## Tags
`Array` `Hash Map` `String` `Two Pointers`
