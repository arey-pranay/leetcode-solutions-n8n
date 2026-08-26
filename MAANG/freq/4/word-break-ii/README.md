# Word Break Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Dynamic Programming` `Backtracking` `Trie` `Memoization`  
**Time:** O(2^N * L)  
**Space:** O(N^2)

---

## Solution (java)

```java
class Solution {
    List<String> ans = new ArrayList<>();
    HashSet<String> wordSet;
    String ip;
    public List<String> wordBreak(String s, List<String> wordDict) {
      wordSet = new HashSet<>(wordDict); 
      ip = s;
      func(0,new StringBuilder(),new StringBuilder());
      return ans;
    }
    private void func(int i, StringBuilder currWord, StringBuilder done){
        if(i==ip.length()){            
            if(wordSet.contains(currWord.toString())){
                done.append(currWord);
                ans.add(done.toString());
            }
            return;
        }
       
        if(wordSet.contains(currWord.toString())){
            int oldLength = done.length(); // cats
            done.append(currWord).append(" "); // cats and 
            func(i,new StringBuilder(),done); //cats and
            done.setLength(oldLength); //cats
        }
        
         //cat
        currWord.append(ip.charAt(i));//cats
        func(i+1,currWord,done);//cats
        currWord.deleteCharAt(currWord.length()-1);//cat
        
    }
}
```

---

---
## Quick Revision
Given a string `s` and a dictionary of words `wordDict`, return all possible sentences where `s` can be segmented into a space-separated sequence of one or more dictionary words.
This problem is solved using backtracking with memoization (or dynamic programming) to explore all valid segmentations.

## Intuition
The core idea is to explore all possible ways to break the string `s` into words from the dictionary. This naturally leads to a recursive or backtracking approach. At each position in the string, we try to form a word using characters from that position onwards. If the formed word is in the dictionary, we then recursively try to break the rest of the string. The "aha moment" comes when we realize that we need to build sentences by appending valid words and spaces, and importantly, backtrack by removing these additions when exploring alternative paths. The provided solution uses a slightly unconventional backtracking approach that builds words character by character and also explores segmenting the *remaining* string from the current position.

## Algorithm
1. **Initialization**:
   - Create a `HashSet` from `wordDict` for efficient O(1) word lookups.
   - Initialize an empty `List<String>` to store the final valid sentences.
   - Store the input string `s` in a member variable for easy access.

2. **Recursive Function `func(index, currentWord, builtSentence)`**:
   - `index`: The current character index in the input string `s` we are considering.
   - `currentWord`: A `StringBuilder` to build the potential word starting from a certain point.
   - `builtSentence`: A `StringBuilder` to accumulate the sentence being formed so far.

3. **Base Case**:
   - If `index` reaches the end of the input string (`s.length()`):
     - Check if `currentWord` itself is a valid dictionary word.
     - If it is, append `currentWord` to `builtSentence` and add the complete `builtSentence` to the result list.
     - Return.

4. **Recursive Steps**:
   - **Option 1: If `currentWord` is a valid dictionary word**:
     - This means we have found a valid word ending at `index - 1`.
     - Append `currentWord` and a space to `builtSentence`.
     - Recursively call `func` with `index` (to consider the *next* character for a *new* word) and an *empty* `currentWord` (to start building a new word), passing the updated `builtSentence`.
     - **Backtrack**: After the recursive call returns, remove the appended `currentWord` and space from `builtSentence` to explore other possibilities.

   - **Option 2: Extend `currentWord`**:
     - Append the character `s.charAt(index)` to `currentWord`.
     - Recursively call `func` with `index + 1` (move to the next character) and the updated `currentWord`, passing the current `builtSentence`.
     - **Backtrack**: After the recursive call returns, remove the last appended character from `currentWord` to explore other possibilities.

## Concept to Remember
*   **Backtracking**: Exploring all possible solutions by systematically trying out combinations and undoing choices when they don't lead to a solution.
*   **String Manipulation with `StringBuilder`**: Efficiently building and modifying strings, especially in recursive contexts, by using `append` and `deleteCharAt`.
*   **HashSet for Dictionary Lookups**: Optimizing word existence checks to O(1) on average.
*   **State Management in Recursion**: Carefully managing the parameters passed to recursive calls and how they are modified and restored during backtracking.

## Common Mistakes
*   **Not Backtracking Properly**: Failing to reset `builtSentence` or `currentWord` after a recursive call, leading to incorrect sentence constructions.
*   **Inefficient Dictionary Lookups**: Using `List.contains()` instead of a `HashSet`, resulting in O(N) lookups and a much slower overall solution.
*   **Handling Spaces Incorrectly**: Adding spaces at the wrong times or not handling the last word without a trailing space.
*   **Redundant Computations**: Without memoization (which this specific solution doesn't explicitly show but is crucial for performance on larger inputs), the same subproblems might be solved multiple times.
*   **Off-by-One Errors**: Incorrectly handling string indices or lengths when appending/deleting characters or words.

## Complexity Analysis
*   **Time**: O(2^N * L) in the worst case, where N is the length of the string `s` and L is the average length of words in the dictionary. This is because, in the worst case, we might explore an exponential number of paths. Each path involves string operations and dictionary lookups. The `L` factor comes from string concatenations and dictionary lookups.
*   **Space**: O(N^2) in the worst case. This is due to the recursion depth (which can be up to N) and the space used by `StringBuilder`s to store `currentWord` and `builtSentence` (which can also be up to N). The `ans` list can also store many sentences, potentially leading to O(N^2) space if many valid segmentations exist.

## Commented Code
```java
import java.util.ArrayList; // Import the ArrayList class for dynamic arrays.
import java.util.HashSet; // Import the HashSet class for efficient set operations.
import java.util.List; // Import the List interface.

class Solution { // Define the Solution class.
    List<String> ans = new ArrayList<>(); // Initialize a list to store the final valid sentences.
    HashSet<String> wordSet; // Declare a HashSet to store the dictionary words for quick lookups.
    String ip; // Declare a String to hold the input string for easy access within the class.

    public List<String> wordBreak(String s, List<String> wordDict) { // The main method to find all word breaks.
        wordSet = new HashSet<>(wordDict); // Populate the wordSet with words from the dictionary for O(1) average time complexity lookups.
        ip = s; // Store the input string in the member variable 'ip'.
        // Start the recursive helper function 'func'.
        // Initial call: index 0, an empty StringBuilder for the current word, and an empty StringBuilder for the built sentence.
        func(0, new StringBuilder(), new StringBuilder());
        return ans; // Return the list of all valid sentences found.
    }

    // Recursive helper function to explore word break possibilities.
    // i: the current character index in the input string 'ip' being considered.
    // currWord: a StringBuilder to construct the potential word starting from a certain point.
    // done: a StringBuilder to accumulate the sentence being built so far.
    private void func(int i, StringBuilder currWord, StringBuilder done) {
        // Base Case: If we have reached the end of the input string.
        if (i == ip.length()) {
            // Check if the 'currWord' we've built is a valid word in the dictionary.
            if (wordSet.contains(currWord.toString())) {
                // If it's a valid word, append it to the 'done' sentence.
                done.append(currWord);
                // Add the complete sentence to the answer list.
                ans.add(done.toString());
            }
            // Return from this path as we've reached the end of the string.
            return;
        }

        // --- Exploration Step 1: If the current word built so far is a valid dictionary word ---
        // This means we have found a valid word ending at index i-1.
        if (wordSet.contains(currWord.toString())) {
            // Store the current length of the 'done' sentence before modification.
            int oldLength = done.length(); // Example: if done is "cats", oldLength is 4.
            // Append the valid 'currWord' and a space to the 'done' sentence.
            done.append(currWord).append(" "); // Example: done becomes "cats "
            // Recursively call 'func' to find breaks for the rest of the string.
            // We pass 'i' (the current index) because we want to start building a *new* word from this position.
            // We pass a *new* empty StringBuilder for 'currWord' to start building a fresh word.
            func(i, new StringBuilder(), done); // Example: explore breaks after "cats "
            // Backtrack: Restore 'done' to its state before this recursive call.
            // This is crucial to explore other possibilities where 'currWord' might not be a separate word.
            done.setLength(oldLength); // Example: done reverts back to "cats"
        }

        // --- Exploration Step 2: Extend the current word by adding the next character ---
        // Append the character at the current index 'i' to 'currWord'.
        currWord.append(ip.charAt(i)); // Example: if currWord was "cat", it becomes "cats"
        // Recursively call 'func' to continue building the current word.
        // We increment 'i' to move to the next character in the input string.
        func(i + 1, currWord, done); // Example: explore breaks for "cats"
        // Backtrack: Remove the last appended character from 'currWord'.
        // This allows us to explore other possibilities where the word might end before this character.
        currWord.deleteCharAt(currWord.length() - 1); // Example: if currWord was "cats", it reverts to "cat"
    }
}
```

## Interview Tips
*   **Clarify Constraints**: Ask about the maximum length of `s` and `wordDict`, and the number of words. This helps gauge if a brute-force backtracking approach is feasible or if optimization (like memoization) is strictly required.
*   **Explain Backtracking**: Clearly articulate the process of exploring paths, making choices, and undoing them. Use an example to walk through the recursion.
*   **Discuss Optimization**: If the interviewer hints at performance issues or if constraints are large, mention memoization (using a `HashMap<Integer, List<String>>` to store results for `s.substring(index)`) as a way to avoid recomputing results for the same substrings. The provided solution is a pure backtracking approach.
*   **Handle Edge Cases**: Be prepared to discuss cases like an empty string `s`, an empty `wordDict`, or `s` not being breakable at all.

## Revision Checklist
- [ ] Understand the problem: Given `s` and `wordDict`, find all valid sentence segmentations.
- [ ] Recognize backtracking as a suitable approach.
- [ ] Implement dictionary lookup efficiently using `HashSet`.
- [ ] Correctly manage `StringBuilder` for `currentWord` and `builtSentence`.
- [ ] Implement the base case for recursion.
- [ ] Implement the two recursive exploration paths: forming a new word vs. extending the current word.
- [ ] Ensure proper backtracking by resetting `StringBuilder` states.
- [ ] Consider time and space complexity.
- [ ] Think about memoization for optimization if needed.

## Similar Problems
*   Word Break (LeetCode 139)
*   Concatenated Words (LeetCode 472)
*   Palindrome Partitioning (LeetCode 131)

## Tags
`Backtracking` `Dynamic Programming` `String` `Recursion` `Depth-First Search`
