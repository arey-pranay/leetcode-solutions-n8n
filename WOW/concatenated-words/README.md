# Concatenated Words

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `String` `Dynamic Programming` `Depth-First Search` `Trie` `Sorting`  
**Time:** O(N * L^3)  
**Space:** O(N * L)

---

## Solution (java)

```java
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
      Arrays.sort(words,(a,b) -> a.length()-b.length());
      List<String> ans = new ArrayList<>();
      HashSet<String> set = new HashSet<>();
      for(String word : words) {
        if(func(word,0,set, new Boolean[word.length()])) ans.add(word);
        set.add(word);
      }
      return ans;
    }
    public boolean func(String word, int start, HashSet<String> set, Boolean[] memo){
      int n = word.length();
      if(start==n) return true;
      if(memo[start] != null) return memo[start];
      for(int end=start;end<n;end++)if(set.contains(word.substring(start,end+1)) && func(word,end+1,set,memo)) return memo[start]= true;
      return memo[start]=false;
    }
}
```

---

---
## Quick Revision
Given a list of words, find all words that are formed by concatenating at least two shorter words from the same list.
This is solved by iterating through sorted words and using dynamic programming with memoization to check for concatenation.

## Intuition
The core idea is that if a word is a concatenated word, it must be formed by at least two *shorter* words. If we process words in increasing order of length, when we consider a word `w`, all shorter words that could potentially form `w` have already been added to our dictionary (or set). This allows us to efficiently check if `w` can be broken down into existing words. The check itself can be framed as a word break problem.

## Algorithm
1. **Sort Words:** Sort the input `words` array in ascending order of their lengths. This ensures that when we process a word, all its potential constituent shorter words are already in our dictionary.
2. **Initialize Data Structures:**
    - Create an empty `List<String> ans` to store the concatenated words.
    - Create an empty `HashSet<String> set` to store the words encountered so far (our dictionary).
3. **Iterate Through Words:** For each `word` in the sorted `words` array:
    - **Check if Concatenated:** Call a helper function `func` (or a similar DP function) to determine if the current `word` can be formed by concatenating words already present in the `set`.
        - The `func` function will take the `word`, a `start` index (initially 0), the `set` of existing words, and a memoization array.
        - **Base Case:** If `start` reaches the end of the `word` (`start == word.length()`), it means the entire word has been successfully segmented, so return `true`.
        - **Memoization Check:** If the result for `start` is already computed and stored in the memoization array, return it.
        - **Recursive Step:** Iterate from `end = start` to `word.length() - 1`. For each substring `word.substring(start, end + 1)`:
            - If this substring is present in the `set` AND recursively calling `func` for the rest of the word (starting from `end + 1`) returns `true`, then the current `word` is concatenated. Store `true` in the memoization array for `start` and return `true`.
        - **Failure:** If no such segmentation is found after checking all possible `end` points, store `false` in the memoization array for `start` and return `false`.
    - **Add to Result:** If `func` returns `true`, add the current `word` to the `ans` list.
    - **Add to Dictionary:** Add the current `word` to the `set` for future checks.
4. **Return Result:** Return the `ans` list.

## Concept to Remember
*   **Dynamic Programming (DP):** Used to solve overlapping subproblems efficiently by storing intermediate results.
*   **Memoization:** A top-down DP technique where results of function calls are cached to avoid recomputation.
*   **Word Break Problem:** A classic DP problem that checks if a string can be segmented into a space-separated sequence of dictionary words.
*   **Greedy Approach (with sorting):** Sorting by length allows a greedy-like processing where we assume shorter words are available before longer ones.

## Common Mistakes
*   **Not Sorting:** Failing to sort the words by length can lead to incorrect results because a word might be checked before its constituent shorter words are added to the dictionary.
*   **Inefficient Word Break Check:** Implementing the word break check without DP or memoization leads to exponential time complexity due to repeated computations.
*   **Incorrect Base Case/Recursive Step:** Errors in the base case (e.g., `start == n`) or the recursive call (e.g., `end + 1` vs. `end`) in the `func` method.
*   **Modifying Set During Iteration:** Adding words to the `set` *before* checking if the current word is concatenated can lead to a word being considered a constituent of itself, which is not allowed (a concatenated word must be formed by *at least two* shorter words). The provided solution correctly adds the word *after* checking.

## Complexity Analysis
*   **Time:** O(N * L^3) where N is the number of words and L is the maximum length of a word.
    - Sorting: O(N log N * L) if string comparisons take O(L).
    - Iterating through words: N words.
    - For each word: The `func` (word break) function with memoization has a time complexity of O(L^2) for generating substrings and O(L) for set lookups, leading to O(L^3) per word. The `substring` operation can take O(L) in some Java versions, hence O(L^3). If `substring` is O(1) and set lookup is O(L) on average, it's O(L^2).
*   **Space:** O(N * L) for storing words in the HashSet and O(L) for the memoization array per word.

## Commented Code
```java
class Solution {
    // Main function to find all concatenated words.
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
      // Sort words by length in ascending order. This is crucial because
      // we want to ensure that any shorter words that could form a longer word
      // are already processed and added to our dictionary (set).
      Arrays.sort(words,(a,b) -> a.length()-b.length());

      // List to store the final concatenated words.
      List<String> ans = new ArrayList<>();
      // HashSet to store words encountered so far. This acts as our dictionary.
      HashSet<String> set = new HashSet<>();

      // Iterate through each word in the sorted array.
      for(String word : words) {
        // For each word, check if it can be formed by concatenating words
        // already present in the 'set'. The 'func' method performs this check
        // using dynamic programming with memoization.
        // We pass 'new Boolean[word.length()]' for memoization, initialized to null.
        if(func(word,0,set, new Boolean[word.length()])) {
          // If 'func' returns true, it means the current 'word' is a concatenated word.
          ans.add(word);
        }
        // After checking, add the current word to the set. This makes it available
        // as a potential constituent for subsequent, longer words.
        set.add(word);
      }
      // Return the list of all concatenated words found.
      return ans;
    }

    // Helper function to check if a word can be segmented into words from the set.
    // This is a recursive function with memoization, essentially solving the Word Break problem.
    // 'word': the string to check.
    // 'start': the starting index in 'word' for the current segmentation attempt.
    // 'set': the dictionary of previously seen words.
    // 'memo': memoization array to store results of subproblems. memo[i] stores whether
    //         the substring word[i...] can be segmented.
    public boolean func(String word, int start, HashSet<String> set, Boolean[] memo){
      // Get the length of the word.
      int n = word.length();
      // Base case: If 'start' has reached the end of the word, it means the entire word
      // has been successfully segmented into words from the set.
      if(start==n) return true;
      // Memoization check: If the result for this 'start' index has already been computed,
      // return the stored result to avoid redundant calculations.
      if(memo[start] != null) return memo[start];

      // Iterate through all possible end points for the current segment.
      // 'end' represents the last index of the potential word segment.
      for(int end=start;end<n;end++) {
        // Extract the substring from 'start' to 'end' (inclusive).
        String sub = word.substring(start,end+1);
        // Check if this substring exists in our dictionary ('set') AND
        // if the rest of the word (from 'end + 1' onwards) can also be segmented
        // by recursively calling 'func'.
        if(set.contains(sub) && func(word,end+1,set,memo)) {
          // If both conditions are true, it means we found a valid segmentation.
          // Store 'true' in the memoization array for the current 'start' index
          // and return 'true'.
          return memo[start]= true;
        }
      }
      // If the loop finishes without finding any valid segmentation for the current 'start' index,
      // it means the substring word[start...] cannot be segmented.
      // Store 'false' in the memoization array for 'start' and return 'false'.
      return memo[start]=false;
    }
}
```

## Interview Tips
*   **Explain the Sorting:** Emphasize why sorting by length is critical. It ensures that when you check a word, all its potential shorter components are already in your dictionary.
*   **Word Break Analogy:** Clearly state that the core of the problem is a variation of the "Word Break" problem and explain how DP/memoization is used to solve it efficiently.
*   **Edge Cases:** Discuss edge cases like an empty input array, an array with only one word, or words that are prefixes of other words.
*   **Complexity Justification:** Be prepared to walk through the time and space complexity, explaining the contribution of sorting, iteration, substring operations, and set lookups.

## Revision Checklist
- [ ] Understand the problem statement: find words formed by concatenating *at least two* shorter words from the list.
- [ ] Recognize the need for sorting words by length.
- [ ] Implement the Word Break logic using recursion with memoization.
- [ ] Correctly handle the base case and recursive step in the DP function.
- [ ] Ensure words are added to the dictionary *after* being checked.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Word Break
*   Word Break II
*   Concatenated Words (different constraints/approaches)

## Tags
`Array` `Dynamic Programming` `Hash Set` `String` `Recursion` `Memoization`

## My Notes
also check word break 1 and 2
