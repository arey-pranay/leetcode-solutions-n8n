# Word Break Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Dynamic Programming` `Backtracking` `Trie` `Memoization`  
**Time:** O(2^N * L)  
**Space:** O(N)

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
Given a string `s` and a dictionary of words, find all possible ways to segment `s` into a space-separated sequence of dictionary words.
This problem is solved using a recursive backtracking approach with memoization (implicitly through the `done` StringBuilder and explicit pruning).

## Intuition
The core idea is to explore all possible ways to break the string. At each character, we have two choices:
1. If the current prefix we've built is a valid word in the dictionary, we can consider it a complete word, add it to our current sentence, and then recursively try to break the *rest* of the string starting from the *same* current character (because the current character might be the start of a *new* word if we decide to split here).
2. We can extend the current prefix by adding the next character and continue building a potential word.

The "aha moment" comes from realizing that we need to explore both possibilities: treating the current prefix as a word and continuing, *and* extending the current prefix. The `done` StringBuilder helps us build the sentence incrementally, and the `currWord` StringBuilder helps us build the potential word.

## Algorithm
1. Initialize an empty list `ans` to store all valid sentences.
2. Convert the `wordDict` into a `HashSet` for efficient O(1) lookups.
3. Define a recursive helper function `func(index, currentWord, completedSentence)`.
    * `index`: The current character index in the input string `s` we are considering.
    * `currentWord`: A `StringBuilder` to build the current potential word.
    * `completedSentence`: A `StringBuilder` to build the sentence formed so far.
4. **Base Case:** If `index` reaches the end of the string (`s.length()`):
    * Check if `currentWord` itself is a valid word in the `wordSet`.
    * If it is, append `currentWord` to `completedSentence` and add `completedSentence.toString()` to `ans`.
    * Return.
5. **Recursive Steps:**
    * **Option 1: Treat `currentWord` as a complete word.**
        * If `currentWord` is not empty and is present in `wordSet`:
            * Store the current length of `completedSentence` (to backtrack later).
            * Append `currentWord` and a space to `completedSentence`.
            * Recursively call `func(index, new StringBuilder(), completedSentence)`: We start building a *new* `currentWord` from scratch, but continue from the *same* `index` because the current character `s.charAt(index)` might be the start of the *next* word.
            * Backtrack: Reset `completedSentence` to its `oldLength`.
    * **Option 2: Extend `currentWord`.**
        * Append the character `s.charAt(index)` to `currentWord`.
        * Recursively call `func(index + 1, currentWord, completedSentence)` to explore further.
        * Backtrack: Remove the last character from `currentWord` (the one just added).
6. Call the helper function initially with `func(0, new StringBuilder(), new StringBuilder())`.
7. Return `ans`.

## Concept to Remember
*   **Backtracking:** Exploring all possible paths by making choices and undoing them if they don't lead to a solution.
*   **String Manipulation with `StringBuilder`:** Efficiently building and modifying strings, especially in recursive contexts, to avoid repeated string object creation.
*   **Set for Dictionary Lookups:** Using a `HashSet` for O(1) average time complexity word existence checks.
*   **State Management in Recursion:** Carefully managing the state of `currentWord` and `completedSentence` through parameters and backtracking.

## Common Mistakes
*   **Incorrect Backtracking:** Failing to properly reset `completedSentence` or `currentWord` after a recursive call, leading to incorrect sentence constructions.
*   **Missing the "Split Here" Logic:** Not considering the case where the current prefix is a valid word and we should "commit" it and start a new word from the *same* index. The provided solution's logic `func(i,new StringBuilder(),done)` handles this.
*   **Inefficient Dictionary Lookups:** Using `List.contains()` instead of a `HashSet`, leading to O(N) lookups.
*   **Overlapping Subproblems (without memoization):** While this specific solution doesn't explicitly use a memoization table for results, it implicitly prunes branches. A more robust solution might use DP/memoization to store results for substrings.
*   **Off-by-one errors in index handling:** Incorrectly advancing or resetting the `index` parameter in recursive calls.

## Complexity Analysis
*   **Time:** O(2^N * L), where N is the length of the string `s` and L is the average length of words in the dictionary. In the worst case, the recursion tree can be exponential. For each valid segmentation, we might iterate through the dictionary. The `StringBuilder` operations take time proportional to the length of the string being appended.
*   **Space:** O(N) for the recursion depth (call stack) and the `StringBuilder`s. In the worst case, `ans` can also store many strings, potentially up to O(2^N * N) if all possible segmentations are valid and long.

## Commented Code
```java
class Solution {
    // List to store all valid sentences found.
    List<String> ans = new ArrayList<>();
    // HashSet for efficient O(1) average time lookup of dictionary words.
    HashSet<String> wordSet;
    // The input string to be segmented.
    String ip;

    // Main method to initiate the word break process.
    public List<String> wordBreak(String s, List<String> wordDict) {
      // Populate the wordSet with words from the dictionary.
      wordSet = new HashSet<>(wordDict);
      // Store the input string for easy access in the recursive function.
      ip = s;
      // Start the recursive function from index 0, with empty current word and empty completed sentence.
      func(0, new StringBuilder(), new StringBuilder());
      // Return the list of all valid sentences.
      return ans;
    }

    // Recursive helper function to find word break combinations.
    // i: current index in the input string 'ip'.
    // currWord: StringBuilder to build the current potential word.
    // done: StringBuilder to build the sentence formed so far.
    private void func(int i, StringBuilder currWord, StringBuilder done) {
        // Base case: If we have reached the end of the input string.
        if (i == ip.length()) {
            // Check if the last formed 'currWord' is a valid dictionary word.
            if (wordSet.contains(currWord.toString())) {
                // If it is, append it to the 'done' sentence.
                done.append(currWord);
                // Add the complete sentence to the answer list.
                ans.add(done.toString());
            }
            // Return from this path.
            return;
        }

        // --- Decision Point 1: Treat 'currWord' as a complete word and split here ---
        // Check if the current 'currWord' (formed by characters before index 'i') is a valid dictionary word.
        // This condition is checked *before* adding the character at index 'i' to 'currWord'.
        if (wordSet.contains(currWord.toString())) {
            // Store the current length of 'done' to backtrack later.
            int oldLength = done.length(); // e.g., "cats"
            // Append the valid 'currWord' and a space to the 'done' sentence.
            done.append(currWord).append(" "); // e.g., "cats "
            // Recursively call 'func' to find breaks for the rest of the string.
            // We start building a *new* current word (new StringBuilder()).
            // Crucially, we continue from the *same* index 'i' because the character at 'ip.charAt(i)'
            // could be the start of the *next* word.
            func(i, new StringBuilder(), done); // e.g., try to break from "and" if ip="catsanddog"
            // Backtrack: Remove the appended word and space from 'done' to explore other possibilities.
            done.setLength(oldLength); // e.g., reset to "cats"
        }

        // --- Decision Point 2: Extend 'currWord' by adding the character at index 'i' ---
        // Append the current character from the input string to 'currWord'.
        currWord.append(ip.charAt(i)); // e.g., if currWord was "cat", it becomes "cats"
        // Recursively call 'func' to explore further by moving to the next character (i+1).
        func(i + 1, currWord, done); // e.g., explore "cats"
        // Backtrack: Remove the last appended character from 'currWord' to explore other possibilities
        // where this character might not be part of the current word.
        currWord.deleteCharAt(currWord.length() - 1); // e.g., if currWord was "cats", it becomes "cat"
    }
}
```

## Interview Tips
1.  **Explain the Backtracking Logic Clearly:** Emphasize the two choices at each step: either the current prefix is a valid word and we can "commit" it, or we extend the current prefix.
2.  **Discuss the `done` and `currWord` `StringBuilder`s:** Explain why `StringBuilder` is used for efficiency and how `done` accumulates the sentence while `currWord` builds a potential word. Highlight the backtracking mechanism for both.
3.  **Address Potential for Exponential Complexity:** Be prepared to discuss the worst-case time complexity and how it arises from the branching nature of the recursion. Mention that while this solution is exponential, it's often the most intuitive way to solve it, and optimizations like memoization can be discussed if asked.
4.  **Walk Through an Example:** Use a simple example like `s = "catsanddog", wordDict = ["cat", "cats", "and", "sand", "dog"]` to trace the execution flow, especially the base cases and backtracking steps.

## Revision Checklist
- [ ] Understand the problem statement: segmenting a string into dictionary words.
- [ ] Recognize the need for exploring all possibilities (backtracking).
- [ ] Implement the recursive helper function with correct parameters.
- [ ] Handle the base case when the end of the string is reached.
- [ ] Implement the two recursive choices: commit current word or extend current word.
- [ ] Use `HashSet` for efficient dictionary lookups.
- [ ] Implement backtracking correctly for `StringBuilder`s.
- [ ] Analyze time and space complexity.
- [ ] Practice tracing the algorithm with examples.

## Similar Problems
*   Word Break (LeetCode 139)
*   Concatenated Words (LeetCode 472)
*   Palindrome Partitioning (LeetCode 131)

## Tags
`Dynamic Programming` `Recursion` `Backtracking` `String` `Hash Set`
