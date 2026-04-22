# Words Within Two Edits Of Dictionary

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `String` `Trie`  
**Time:** O(Q * D * L)  
**Space:** O(Q)

---

## Solution (java)

```java
class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> ans = new ArrayList<>();
        for(String query: queries){
            for(String dict: dictionary){
                if(match(query,dict)){
                    ans.add(query);
                    break;
                }
            }
        }
        return ans;
    }
    public boolean match(String a,String b){
        int n =a.length();
        if(n!=b.length()) return false;
        int i = 0;
        int mismatch =0 ;
        while(i<n){
            if(a.charAt(i)!=b.charAt(i)){
                mismatch++;
                if(mismatch>2) return false;
            }
            i++;
        }
        return true;
    }
}
```

---

---
## Quick Revision
Given a list of query words and a dictionary, find all query words that are at most two character edits away from any word in the dictionary.
This is solved by iterating through each query and comparing it against every dictionary word, counting character mismatches.

## Intuition
The core idea is to check for "similarity" between a query word and dictionary words. Similarity here is defined by a small number of character differences (at most 2). If a query word is similar enough to *any* word in the dictionary, it should be included in the result. The most straightforward way to check this similarity is by direct character-by-character comparison.

## Algorithm
1. Initialize an empty list `ans` to store the qualifying query words.
2. Iterate through each `query` word in the `queries` array.
3. For each `query`, iterate through each `dict` word in the `dictionary` array.
4. For the current `query` and `dict` pair, call a helper function `match(query, dict)` to determine if they are within two edits.
5. The `match` function:
    a. Checks if the lengths of the two strings are equal. If not, they cannot be within two edits (assuming edits are substitutions, insertions, or deletions, but the problem implies fixed length comparison based on the provided solution). Return `false`.
    b. Initializes a `mismatch` counter to 0.
    c. Iterates through the characters of both strings from index 0 to `n-1` (where `n` is the length of the strings).
    d. If the characters at the current index `i` are different, increment `mismatch`.
    e. If `mismatch` exceeds 2 at any point, return `false` immediately, as it's already too many edits.
    f. If the loop completes and `mismatch` is 2 or less, return `true`.
6. If `match(query, dict)` returns `true`:
    a. Add the `query` word to the `ans` list.
    b. Use `break` to exit the inner loop (iterating through `dictionary`) because we only need one match for the `query` word.
7. After iterating through all `queries`, return the `ans` list.

## Concept to Remember
*   **String Comparison:** Efficiently comparing strings character by character.
*   **Edit Distance (Simplified):** Understanding the concept of character differences as a measure of similarity. This problem uses a simplified version where only substitutions are implicitly considered due to fixed-length comparison.
*   **Nested Loops:** The common pattern for comparing elements between two collections.
*   **Early Exit (Break):** Optimizing loops by stopping when a condition is met.

## Common Mistakes
*   **Incorrect Mismatch Count:** Forgetting to increment the `mismatch` counter or incorrectly resetting it.
*   **Not Handling Length Differences:** Assuming all strings have the same length without explicit checks, leading to `IndexOutOfBoundsException` or incorrect logic.
*   **Not Breaking Early:** Continuing to check the rest of the dictionary for a query word even after a match has been found, leading to redundant computations.
*   **Off-by-One Errors:** In loop conditions or index access when comparing characters.
*   **Misinterpreting "Edits":** The provided solution implicitly assumes edits are only substitutions because it compares strings of equal length. If insertions/deletions were allowed, a more complex edit distance algorithm (like Levenshtein) would be needed.

## Complexity Analysis
*   Time: O(Q * D * L) - where Q is the number of queries, D is the number of words in the dictionary, and L is the maximum length of a word. We iterate through each query, and for each query, we iterate through the entire dictionary. The `match` function takes O(L) time.
*   Space: O(Q) - In the worst case, all query words might be added to the `ans` list. This is for storing the result. The auxiliary space used by variables is O(1).

## Commented Code
```java
class Solution {
    // Main method to find words within two edits of the dictionary
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        // Initialize an ArrayList to store the resulting query words
        List<String> ans = new ArrayList<>();
        // Iterate through each query word in the input queries array
        for(String query: queries){
            // For each query, iterate through each word in the dictionary
            for(String dict: dictionary){
                // Call the helper function 'match' to check if the current query
                // is within two edits of the current dictionary word
                if(match(query,dict)){
                    // If they match (within two edits), add the query word to the result list
                    ans.add(query);
                    // Since we found a match for this query, we can stop checking
                    // against other dictionary words and move to the next query
                    break;
                }
            }
        }
        // Return the list of query words that met the criteria
        return ans;
    }

    // Helper function to check if two strings are within two character mismatches
    public boolean match(String a,String b){
        // Get the length of the first string
        int n =a.length();
        // If the lengths of the two strings are different, they cannot be within
        // two edits (based on the problem's implicit assumption of fixed-length comparison)
        if(n!=b.length()) return false;
        // Initialize an index variable to iterate through the strings
        int i = 0;
        // Initialize a counter for the number of character mismatches
        int mismatch =0 ;
        // Loop through the strings character by character as long as the index is within bounds
        while(i<n){
            // Compare the characters at the current index in both strings
            if(a.charAt(i)!=b.charAt(i)){
                // If the characters are different, increment the mismatch counter
                mismatch++;
                // If the number of mismatches exceeds 2, we can immediately return false
                // as the condition is no longer met
                if(mismatch>2) return false;
            }
            // Move to the next character
            i++;
        }
        // If the loop completes and the total number of mismatches is 2 or less,
        // it means the strings are within two edits, so return true
        return true;
    }
}
```

## Interview Tips
*   **Clarify "Edits":** Ask the interviewer to clarify what "edits" mean. Does it include insertions/deletions, or only substitutions? The provided solution assumes only substitutions due to fixed-length string comparison.
*   **Explain the `match` function:** Clearly articulate how the `match` function works, especially the mismatch counting and the early exit condition.
*   **Discuss Time Complexity:** Be prepared to explain why the time complexity is O(Q * D * L) and how it can be improved if the dictionary were very large (e.g., using Tries).
*   **Edge Cases:** Consider edge cases like empty `queries` or `dictionary` arrays, or empty strings within them. The current code handles empty arrays gracefully.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Implement the `match` helper function correctly, handling length checks and mismatch counts.
- [ ] Use nested loops to iterate through queries and dictionary words.
- [ ] Implement the `break` statement for early exit after finding a match.
- [ ] Analyze time and space complexity.
- [ ] Consider potential optimizations if dictionary size is a concern.

## Similar Problems
*   Edit Distance (LeetCode 72)
*   Word Break (LeetCode 139)
*   Prefix Tree (Trie) related problems for efficient dictionary lookups.

## Tags
`Array` `String` `Two Pointers`
