# Word Break

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Dynamic Programming` `Trie` `Memoization`  
**Time:** O(n*m)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    Boolean[] memo;
    public boolean wordBreak(String s, List<String> wordDict) {      
        HashSet<String> hs = new HashSet<>(wordDict);
        memo = new Boolean[s.length()+1];
        return func(0,0,s,hs);
    }
    private boolean func(int i, int j, String s, HashSet<String> hs){
        if(i>= s.length()) return true;
        if(j>=s.length()) return false;
        if(memo[i] != null) return memo[i];
        boolean ans = func(i,j+1,s,hs);
        if(hs.contains(s.substring(i,j+1))) ans |= func(j+1,j+1,s,hs);
        return memo[i] = ans; 
    }
}
```

---

---

## Quick Revision
Word Break problem: given a non-empty string `s` and a dictionary of words `wordDict`, determine if `s` can be segmented into a space-separated sequence of one or more dictionary words. We solve this by implementing a depth-first search with memoization.

## Intuition
The key insight here is that we can break down the problem into smaller subproblems, each of which asks whether a prefix of the string can be segmented into dictionary words. By storing the results of these subproblems in a memoization table, we avoid redundant computation and achieve efficiency.

## Algorithm

1. Create a `HashSet` of dictionary words for O(1) lookups.
2. Initialize a memoization array with `Boolean` values to store the results of subproblems.
3. Define a recursive function `func` that takes four parameters: the current index `i`, the last index `j`, the string `s`, and the `HashSet` of dictionary words `hs`.
4. Base cases:
	* If `i >= s.length()`, return true (we've reached the end of the string).
	* If `j >= s.length()`, return false (we've exceeded the length of the string).
5. If we've already computed the result for this subproblem, return the memoized value.
6. Otherwise, recursively call `func` with `i` and `j+1` as arguments to check if there's a possible segmentation without using the current substring.
7. If the current substring is in the dictionary, recursively call `func` with `j+1` and `j+1` as arguments to check if we can segment the remaining string.
8. Store the result of this subproblem in the memoization array.

## Concept to Remember

* **Memoization**: storing the results of expensive function calls to avoid redundant computation.
* **Dynamic Programming**: breaking down a problem into smaller subproblems, solving each one only once, and combining the solutions to solve the original problem.
* **HashSet** operations: O(1) average time complexity for lookups, insertions, and deletions.

## Common Mistakes

* Failing to initialize the memoization array properly, leading to incorrect results or infinite loops.
* Not handling edge cases (e.g., empty string, single-character string) correctly.
* Using a recursive approach without considering the potential for stack overflow errors.

## Complexity Analysis
- Time: O(n*m), where n is the length of the input string and m is the number of words in the dictionary. We perform at most n recursive calls, each of which takes up to m operations (looking up substrings in the dictionary).
- Space: O(n), for storing the memoization array.

## Commented Code

```java
class Solution {
    Boolean[] memo;

    public boolean wordBreak(String s, List<String> wordDict) {
        // Create a HashSet of dictionary words for O(1) lookups.
        HashSet<String> hs = new HashSet<>(wordDict);
        
        // Initialize a memoization array with Boolean values to store the results of subproblems.
        memo = new Boolean[s.length()+1];
        
        return func(0, 0, s, hs); // Start the recursive search from index 0.
    }

    private boolean func(int i, int j, String s, HashSet<String> hs) {
        // Base case: if we've reached the end of the string, return true.
        if (i >= s.length()) return true;
        
        // Base case: if we've exceeded the length of the string, return false.
        if (j >= s.length()) return false;
        
        // If we've already computed the result for this subproblem, return the memoized value.
        if (memo[i] != null) return memo[i];
        
        boolean ans = func(i, j+1, s, hs); // Check if there's a possible segmentation without using the current substring.
        
        // If the current substring is in the dictionary, check if we can segment the remaining string.
        if (hs.contains(s.substring(i,j+1))) 
            ans |= func(j+1, j+1, s, hs);
        
        return memo[i] = ans; // Store the result of this subproblem in the memoization array.
    }
}
```

## Interview Tips

* Be prepared to explain the intuition behind the solution and how it avoids redundant computation.
* Practice converting recursive solutions into iterative ones using a stack or queue data structure.
* Familiarize yourself with common pitfalls, such as failing to handle edge cases correctly.

## Revision Checklist
- [ ] Review dynamic programming concepts and memoization techniques.
- [ ] Practice solving Word Break problems on LeetCode or other platforms.
- [ ] Improve time complexity by optimizing the recursive function call stack.

## Similar Problems

* LeetCode 140. Word Break II (more advanced)
* LeetCode 409. Longest Palindrome (similar dynamic programming approach)

## Tags
`Array` `Hash Map` `Dynamic Programming`
