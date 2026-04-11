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
Use a stack to keep track of characters and their counts, popping when a count reaches `k`.

## Intuition
The core idea is that we only care about the *most recent* sequence of identical characters. If adding a new character makes its count reach `k`, that sequence must be removed. A stack is a natural fit for this "last-in, first-out" behavior, allowing us to easily access and modify the most recent character's count. When a sequence is removed, we effectively "go back" to the previous character sequence on the stack.

## Algorithm
1. Initialize an empty stack `st` that will store pairs of `[character, count]`.
2. Iterate through each character `c` in the input string `s`.
3. For each character `c`:
    a. If the stack is not empty AND the character at the top of the stack (`st.peek()[0]`) is the same as `c`:
        i. Increment the count of the character at the top of the stack (`st.peek()[1]++`).
        ii. If the count now equals `k` (`st.peek()[1] == k`), pop the element from the stack (removing the `k` adjacent duplicates).
    b. Else (if the stack is empty OR the character is different from the top):
        i. Push a new pair `[c, 1]` onto the stack.
4. Initialize an empty `StringBuilder sb`.
5. While the stack is not empty:
    a. Pop an element `temp` (which is an `int[]` of `[character, count]`) from the stack.
    b. Append the character `(char)temp[0]` to `sb` `temp[1]` times.
6. Reverse the `StringBuilder sb` and convert it to a string.
7. Return the resulting string.

## Concept to Remember
*   **Stack Data Structure**: LIFO (Last-In, First-Out) principle, useful for tracking sequential dependencies and backtracking.
*   **Character Counting**: Efficiently tracking occurrences of elements.
*   **String Manipulation**: Using `StringBuilder` for efficient string construction.
*   **Greedy Approach**: Making locally optimal choices (removing `k` duplicates as soon as they form) leads to a globally optimal solution.

## Common Mistakes
*   **Incorrect Stack Usage**: Not handling the empty stack case when peeking or pushing.
*   **Off-by-One Errors in Counting**: Mismanaging the count of characters, especially when incrementing or checking against `k`.
*   **Inefficient String Building**: Using `String` concatenation repeatedly instead of `StringBuilder`.
*   **Forgetting to Reverse**: The stack processes elements in reverse order of their final appearance, so the `StringBuilder` needs to be reversed at the end.
*   **Not Handling the `k=1` Case**: While the general logic works, it's a good edge case to consider mentally.

## Complexity Analysis
*   **Time**: O(N) - Each character in the input string `s` is pushed onto and potentially popped from the stack at most once. The final `StringBuilder` construction also iterates through the remaining elements on the stack, which in total corresponds to the original string length.
*   **Space**: O(N) - In the worst case, if no duplicates are removed, the stack could store all characters of the string.

## Commented Code
```java
class Solution {
    public String removeDuplicates(String s, int k) {
        // Initialize a stack to store pairs of [character, count].
        // The character is stored as an int (its ASCII value), and the count is an int.
        Stack<int[]> st = new Stack<>();

        // Iterate through each character in the input string s.
        for(char c : s.toCharArray()){
            // Check if the stack is not empty AND the current character 'c' is the same as the character at the top of the stack.
            if(!st.isEmpty() && st.peek()[0]==c){
                // If they are the same, increment the count of the character at the top of the stack.
                st.peek()[1]++;
                // If the count of this character has reached k, it means we have k adjacent duplicates.
                if(st.peek()[1] == k) {
                    // Remove this group of k duplicates by popping from the stack.
                    st.pop();
                }
            } else {
                // If the stack is empty OR the current character is different from the top of the stack,
                // push a new entry onto the stack with the current character and a count of 1.
                st.push(new int[]{c,1});
            }
        }

        // Initialize a StringBuilder to construct the result string.
        // Using StringBuilder is more efficient for repeated appends than String concatenation.
        StringBuilder sb = new StringBuilder("");

        // While there are still elements in the stack, process them to build the final string.
        while(!st.isEmpty()){
            // Pop the top element, which is an int array containing [character, count].
            int[] temp = st.pop();
            // Append the character (converted from its int ASCII value) to the StringBuilder,
            // 'temp[1]' times (its count).
            for(int i=0;i<temp[1];i++){
                sb.append((char)temp[0]);
            }
        }

        // The stack processes elements in reverse order of their final appearance.
        // Therefore, we need to reverse the StringBuilder to get the correct order.
        // Finally, convert the StringBuilder to a String and return it.
        return sb.reverse().toString();
    }
}
```

## Interview Tips
*   **Explain the Stack Choice**: Clearly articulate why a stack is suitable for this problem, emphasizing its LIFO property for tracking recent sequences.
*   **Trace with an Example**: Walk through a small example like `s = "deeedbbcccbddd", k = 3` to demonstrate how the stack evolves.
*   **Discuss Edge Cases**: Mention what happens if `k=1` (all characters are removed if they appear consecutively), or if the string is empty.
*   **Clarify Data Structure for Stack Elements**: Explain that you're storing `[character, count]` pairs, and how you're representing the character (e.g., as an `int` for ASCII value).

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the need for a data structure to track adjacent characters and their counts.
- [ ] Choose a stack as the appropriate data structure.
- [ ] Implement the logic for pushing new characters and incrementing counts.
- [ ] Implement the logic for popping when a count reaches `k`.
- [ ] Implement the logic for reconstructing the string from the stack.
- [ ] Consider time and space complexity.
- [ ] Test with various examples, including edge cases.

## Similar Problems
*   Remove All Adjacent Duplicates In String (LeetCode 1047)
*   String Compression (LeetCode 443)
*   Daily Temperatures (LeetCode 739)

## Tags
`Stack` `String` `Array` `StringBuilder`
