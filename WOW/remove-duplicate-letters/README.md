# Remove Duplicate Letters

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Stack` `Greedy` `Monotonic Stack`  
**Time:** O(N)  
**Space:** O(K)

---

## Solution (java)

```java
class Solution {
    public String removeDuplicateLetters(String s) {
        int[] freq = new int[26];
        for(int i =0;i<s.length();i++){
            char c= s.charAt(i);
            freq[c-'a']++;
        }
        Stack<Character> st = new Stack<>();
        HashSet<Character> already = new HashSet<>();
        for(int i=0;i<s.length();i++){
            char curr = s.charAt(i);
            freq[curr-'a']--;
            if(already.contains(curr)) continue;
            while(!st.isEmpty() && curr<st.peek() && freq[st.peek()-'a']>0){
                already.remove(st.peek()); st.pop(); 
            }
            st.push(curr); already.add(curr); 
        }
        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) sb.append(st.pop());
        return sb.reverse().toString();
    }
}
// 1 2 4 1
// 1 2 3 1
// c

```

---

---
## Quick Revision
Given a string `s`, remove duplicate letters so that every letter appears once and only once.
The resulting string should be the lexicographically smallest among all possible results.

## Intuition
To get the lexicographically smallest result, we want to keep smaller characters earlier in the string. When we encounter a character, if it's smaller than the top of our "result stack" and the character on top of the stack appears later in the string, we can safely remove the character from the stack to potentially get a smaller overall string. We use a frequency map to track remaining characters and a set to ensure we don't add duplicates to our result.

## Algorithm
1.  **Frequency Count:** Create an array `freq` of size 26 to store the frequency of each character ('a' through 'z') in the input string `s`. Iterate through `s` and populate `freq`.
2.  **Initialization:** Initialize an empty `Stack<Character>` called `st` to build our result and a `HashSet<Character>` called `already` to keep track of characters currently in the stack.
3.  **Iterate and Build:** Iterate through the input string `s` character by character (`curr`).
    *   Decrement the frequency of `curr` in `freq`.
    *   **Skip Duplicates:** If `curr` is already present in the `already` set, continue to the next character (it's already in our potential result).
    *   **Monotonic Stack Logic:** While the stack `st` is not empty, the current character `curr` is lexicographically smaller than the character at the top of the stack (`st.peek()`), AND the character at the top of the stack still has occurrences remaining in the string (checked by `freq[st.peek()-'a'] > 0`):
        *   Remove the top character from `already` (since it's being popped).
        *   Pop the character from `st`.
    *   **Add to Stack:** Push `curr` onto the stack `st`.
    *   **Mark as Added:** Add `curr` to the `already` set.
4.  **Construct Result:** After iterating through the entire string, the stack `st` contains the characters of the lexicographically smallest unique-character string in reverse order.
5.  **Reverse and Return:** Create a `StringBuilder`, pop characters from `st` and append them to the `StringBuilder`. Finally, reverse the `StringBuilder` and convert it to a `String` to return.

## Concept to Remember
*   **Monotonic Stack:** A stack where elements are maintained in a specific order (e.g., increasing or decreasing). Used here to greedily build the lexicographically smallest string.
*   **Greedy Approach:** Making locally optimal choices at each step with the hope of finding a global optimum. Here, we prioritize smaller characters early if possible.
*   **Character Frequency Tracking:** Essential for determining if a character can be removed from the stack.

## Common Mistakes
*   **Incorrectly handling the `freq` check:** Forgetting to check if `freq[st.peek()-'a'] > 0` before popping can lead to removing characters that are essential for the final string.
*   **Not using a set to track added characters:** This can lead to duplicate characters being added to the stack.
*   **Forgetting to reverse the final string:** The stack naturally builds the result in reverse order.
*   **Off-by-one errors with character indexing:** Ensuring `c - 'a'` correctly maps characters to array indices.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the string `s` a constant number of times (once for frequency count, once for building the stack). Stack operations (push, pop, peek) and set operations (add, contains, remove) take O(1) on average.
- Space: O(K) - reason: Where K is the number of unique characters in the alphabet (26 in this case). The `freq` array, `st` stack, and `already` set will store at most 26 distinct characters.

## Commented Code
```java
class Solution {
    public String removeDuplicateLetters(String s) {
        // freq: array to store the frequency of each character ('a' to 'z') in the string s.
        int[] freq = new int[26];
        // Iterate through the string to count character frequencies.
        for(int i =0;i<s.length();i++){
            char c= s.charAt(i);
            // Increment the count for the current character.
            freq[c-'a']++;
        }
        // st: a stack to build the lexicographically smallest subsequence.
        Stack<Character> st = new Stack<>();
        // already: a set to keep track of characters that are currently in the stack.
        HashSet<Character> already = new HashSet<>();
        // Iterate through the string again to build the result.
        for(int i=0;i<s.length();i++){
            char curr = s.charAt(i);
            // Decrement the frequency of the current character as we've processed it.
            freq[curr-'a']--;
            // If the current character is already in our stack, skip it to avoid duplicates.
            if(already.contains(curr)) continue;
            // This is the core monotonic stack logic:
            // While the stack is not empty, AND the current character is lexicographically smaller than the top of the stack,
            // AND the character at the top of the stack still has occurrences remaining in the string (meaning we can afford to remove it now and add it later if needed).
            while(!st.isEmpty() && curr < st.peek() && freq[st.peek()-'a'] > 0){
                // Remove the character from the 'already' set because we are popping it from the stack.
                already.remove(st.peek());
                // Pop the character from the stack.
                st.pop();
            }
            // Push the current character onto the stack.
            st.push(curr);
            // Add the current character to the 'already' set, marking it as present in the stack.
            already.add(curr);
        }
        // sb: a StringBuilder to construct the final result string.
        StringBuilder sb = new StringBuilder();
        // Pop all characters from the stack and append them to the StringBuilder.
        // The stack contains characters in reverse order of the desired result.
        while(!st.isEmpty()) sb.append(st.pop());
        // Reverse the StringBuilder to get the correct lexicographical order and convert it to a String.
        return sb.reverse().toString();
    }
}
```

## Interview Tips
*   **Explain the Monotonic Stack:** Clearly articulate why a monotonic stack is suitable for this problem and how it helps achieve the lexicographically smallest result.
*   **Trace with an Example:** Walk through a simple example like "cbacdcbc" or "bcabc" to demonstrate how the stack and set evolve.
*   **Discuss Edge Cases:** Consider cases like an empty string, a string with all unique characters, or a string with all duplicate characters.
*   **Justify Greedy Choice:** Explain the condition `curr < st.peek() && freq[st.peek()-'a'] > 0` and why it's a valid greedy choice.

## Revision Checklist
- [ ] Understand the goal: lexicographically smallest string with unique characters.
- [ ] Implement frequency counting.
- [ ] Use a stack for building the result.
- [ ] Use a set to track characters already in the stack.
- [ ] Correctly implement the monotonic stack logic (pop condition).
- [ ] Handle the case where a character is already in the stack.
- [ ] Reverse the final result from the stack.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Remove K Digits
*   Smallest Subsequence of Distinct Characters (identical problem)
*   Daily Temperatures
*   Next Greater Element I/II

## Tags
`Stack` `String` `Greedy` `Monotonic Stack` `Hash Set`
