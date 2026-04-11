# Remove All Adjacent Duplicates In String Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Stack`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<int[]> st = new Stack<>();
        for(char c : s.toCharArray()){
            if(!st.isEmpty() && st.peek()[0]==c){
                st.peek()[1]++;
                if(st.peek()[1] == k) st.pop();
            } else{
                st.push(new int[]{c,1});
            }
        }
        StringBuilder sb = new StringBuilder("");
        while(!st.isEmpty()){
            int[] temp = st.pop();
            for(int i=0;i<temp[1];i++){
                sb.append((char)temp[0]);
            }
        }
        return sb.reverse().toString();
    }
    
}


```

---

---
## Quick Revision
Given a string `s` and an integer `k`, repeatedly remove `k` adjacent and equal characters.
Use a stack to store character counts, popping when a count reaches `k`.

## Intuition
The core idea is to process the string character by character and keep track of consecutive identical characters. When a sequence of `k` identical characters is formed, it needs to be removed. A stack is a natural fit for this because it allows us to easily access and modify the *most recently seen* character and its count. If the current character matches the top of the stack, we increment its count. If the count reaches `k`, we remove that entry from the stack. If it doesn't match, we push a new entry for the current character with a count of 1.

## Algorithm
1. Initialize an empty stack `st` that will store pairs of `[character, count]`.
2. Iterate through each character `c` in the input string `s`.
3. For each character `c`:
    a. Check if the stack is not empty and the character at the top of the stack (`st.peek()[0]`) is the same as `c`.
    b. If they are the same:
        i. Increment the count of the character at the top of the stack (`st.peek()[1]++`).
        ii. If the count now equals `k` (`st.peek()[1] == k`), pop the element from the stack.
    c. If they are not the same (or the stack is empty):
        i. Push a new pair `[c, 1]` onto the stack.
4. Initialize an empty `StringBuilder` called `sb`.
5. While the stack `st` is not empty:
    a. Pop the top element `temp` (which is an `int[]` of `[character, count]`).
    b. Append the character `(char)temp[0]` to `sb` `temp[1]` times.
6. Reverse the `StringBuilder` `sb`.
7. Convert `sb` to a string and return it.

## Concept to Remember
*   **Stack Data Structure:** Essential for managing state (character and its count) in a Last-In, First-Out manner, allowing efficient access to the most recent element.
*   **Greedy Approach:** At each step, we make the locally optimal choice (removing `k` duplicates as soon as they are formed) which leads to the globally optimal solution.
*   **String Manipulation:** Efficiently building the result string using `StringBuilder` and handling character conversions.

## Common Mistakes
*   **Incorrect Stack Element:** Storing only characters or only counts, instead of both. The pair `[character, count]` is crucial.
*   **Off-by-One Errors:** Mismanaging the count (e.g., checking `count == k-1` instead of `count == k`) or the number of appends in the final `StringBuilder`.
*   **Not Reversing the Result:** Building the string from the stack in reverse order and forgetting to reverse the `StringBuilder` at the end.
*   **Handling Empty Stack:** Not properly checking if the stack is empty before peeking or accessing its top element.

## Complexity Analysis
- Time: O(N) - We iterate through the string once to build the stack, and then iterate through the stack to build the result. Each character is pushed and popped at most once. Appending to the StringBuilder is amortized O(1) per character.
- Space: O(N) - In the worst case, the stack might store all unique characters if `k` is very large, or if no duplicates are removed.

## Commented Code
```java
class Solution {
    public String removeDuplicates(String s, int k) {
        // Initialize a stack to store pairs of [character, count].
        // The character is stored as an int (its ASCII value), and the count is an int.
        Stack<int[]> st = new Stack<>();

        // Iterate through each character in the input string s.
        for(char c : s.toCharArray()){
            // Check if the stack is not empty AND the character at the top of the stack
            // matches the current character c.
            if(!st.isEmpty() && st.peek()[0]==c){
                // If they match, increment the count of the character at the top of the stack.
                st.peek()[1]++;
                // If the count of this character has reached k, it means we have k adjacent duplicates.
                // So, remove this entry from the stack.
                if(st.peek()[1] == k) st.pop();
            } else{
                // If the stack is empty OR the current character does not match the top of the stack,
                // push a new entry onto the stack.
                // The new entry contains the current character c and its initial count of 1.
                st.push(new int[]{c,1});
            }
        }

        // Initialize a StringBuilder to construct the result string.
        StringBuilder sb = new StringBuilder("");

        // While the stack is not empty, process the remaining elements.
        while(!st.isEmpty()){
            // Pop the top element from the stack. This element is an int array [character, count].
            int[] temp = st.pop();
            // Append the character (converted from its int ASCII value) to the StringBuilder
            // 'count' number of times.
            for(int i=0;i<temp[1];i++){
                sb.append((char)temp[0]);
            }
        }
        // The characters were appended in reverse order as they were popped from the stack.
        // So, reverse the StringBuilder to get the correct order.
        // Finally, convert the StringBuilder to a String and return it.
        return sb.reverse().toString();
    }
}
```

## Interview Tips
*   **Explain the Stack Choice:** Clearly articulate why a stack is suitable for this problem, emphasizing its LIFO property for tracking recent elements and their counts.
*   **Walk Through an Example:** Use a small example like `s = "deeedbbcccbdaa", k = 3` to demonstrate how the stack evolves step-by-step.
*   **Discuss Edge Cases:** Consider cases like an empty input string, `k=1` (which means no characters are removed), or a string where no removals occur.
*   **Clarify Data Structure for Stack:** Mention that you'll store pairs (e.g., `[char, count]`) and how you'll represent them (e.g., `int[]` or a custom `Pair` class).

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the need for tracking adjacent character counts.
- [ ] Choose an appropriate data structure (Stack).
- [ ] Implement the logic for pushing, incrementing count, and popping from the stack.
- [ ] Implement the logic for reconstructing the final string from the stack.
- [ ] Consider time and space complexity.
- [ ] Test with various examples and edge cases.

## Similar Problems
*   Remove All Adjacent Duplicates In String (LeetCode 1047)
*   String Compression (LeetCode 443)
*   Daily Temperatures (LeetCode 739)

## Tags
`Stack` `String` `Array` `StringBuilder`
