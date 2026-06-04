# Simplify Path

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Stack`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public String simplifyPath(String path) {
        Stack<String> st = new Stack<>();
        String[] arr = path.split("/");
        
        for(int i=arr.length-1; i>=0; i--){
            String curr = arr[i];
            if(curr.isEmpty() || curr.equals(".")) continue;
            else if(!st.isEmpty() && st.peek().equals("..") && !curr.equals("..")) st.pop();
            else st.push(curr);
        }
        
        while(!st.isEmpty() && st.peek().equals(".."))st.pop();
        
        StringBuilder sb = new StringBuilder("/");
        while(!st.isEmpty()){
            if(st.size() > 1) sb.append(st.pop() + "/");
            else sb.append(st.pop());
        }
        
        return sb.toString();
    }
}
```

---

---
## Quick Revision
Given an absolute path for a file system, simplify it.
Use a stack to process directory components, handling '.', '..', and empty strings.

## Intuition
The core idea is to treat the path as a sequence of directory names. We need to navigate these directories. A stack is a natural fit because navigating into a directory is like pushing onto the stack, and going up one level ('..') is like popping from the stack. Special cases like '.' (current directory) and empty strings (multiple slashes) should be ignored.

## Algorithm
1. Split the input `path` string by the '/' delimiter to get an array of directory components.
2. Initialize an empty stack to store valid directory names.
3. Iterate through the array of components.
    a. If the current component is empty or ".", skip it (it represents the current directory or redundant slashes).
    b. If the current component is "..":
        i. If the stack is not empty and the top element is not "..", pop from the stack (move up one directory).
        ii. If the stack is empty or the top is "..", push ".." onto the stack (effectively staying at the root or going up from root).
    c. Otherwise (it's a valid directory name), push the component onto the stack.
4. After processing all components, there might be leading ".." on the stack that need to be handled. While the stack is not empty and its top element is "..", pop it. This ensures we don't go above the root.
5. Construct the simplified path using a `StringBuilder`. Start with a root "/".
6. While the stack is not empty, pop elements and append them to the `StringBuilder`, adding a "/" between directory names. If it's the last element, don't append a trailing "/".
7. Return the `StringBuilder`'s string representation.

## Concept to Remember
*   **Stack Data Structure:** Essential for managing hierarchical navigation (pushing for entering, popping for exiting).
*   **String Manipulation:** Splitting strings and building new strings efficiently.
*   **Path Normalization:** Understanding how to handle special path components like '.' and '..'.
*   **Edge Cases:** Recognizing and handling empty components, multiple slashes, and paths starting/ending with '/'.

## Common Mistakes
*   Incorrectly handling the ".." component when the stack is empty or contains only "..".
*   Failing to ignore empty strings resulting from multiple slashes (e.g., "//").
*   Not properly constructing the final path string, leading to missing root "/" or incorrect trailing "/".
*   Forgetting to handle the case where the simplified path is just the root "/".
*   Incorrectly processing the stack in reverse order, leading to an incorrect final path.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the path string once to split it (O(N)), then iterate through the resulting array of components (at most O(N) components), and finally build the result string (at most O(N) characters).
- Space: O(N) - reason: In the worst case, the stack can store all directory names if the path is very deep and contains no ".." or "." components. The split array also takes O(N) space.

## Commented Code
```java
class Solution {
    public String simplifyPath(String path) {
        // Use a stack to keep track of valid directory names.
        Stack<String> st = new Stack<>();
        // Split the path by '/' to get individual directory components.
        String[] arr = path.split("/");
        
        // Iterate through the components in reverse order.
        // This specific implementation seems to be processing in reverse, which is unusual for a stack-based path simplification.
        // A more standard approach would iterate forward and push/pop.
        // Let's analyze this specific code's logic:
        // It seems to be building a reversed stack of what *should* be in the final path.
        for(int i=arr.length-1; i>=0; i--){
            String curr = arr[i];
            // If the component is empty (e.g., from "//") or "." (current directory), ignore it.
            if(curr.isEmpty() || curr.equals(".")) continue;
            // If the component is ".." (parent directory) and the stack is not empty
            // and the top of the stack is NOT ".." (meaning we are not already at root or trying to go above root with multiple ".."),
            // then pop the ".." from the stack, effectively cancelling out a ".." with a directory name.
            else if(!st.isEmpty() && st.peek().equals("..") && !curr.equals("..")) st.pop();
            // Otherwise, push the current component onto the stack.
            // This includes directory names and ".." if it's the first element or follows another "..".
            else st.push(curr);
        }
        
        // After the initial pass, there might be leading ".." on the stack that need to be removed
        // if they represent going above the root.
        while(!st.isEmpty() && st.peek().equals(".."))st.pop();
        
        // Build the simplified path using a StringBuilder. Start with the root "/".
        StringBuilder sb = new StringBuilder("/");
        // While the stack is not empty, pop elements and append them to the StringBuilder.
        while(!st.isEmpty()){
            // If there's more than one element left, append the popped element and a "/".
            if(st.size() > 1) sb.append(st.pop() + "/");
            // If it's the last element, just append it without a trailing "/".
            else sb.append(st.pop());
        }
        
        // Return the final simplified path string.
        return sb.toString();
    }
}
```
*Self-correction on the provided code's logic:* The provided code iterates backward and uses a stack in a way that seems to be constructing a reversed representation of the path. A more conventional approach iterates forward. Let's re-evaluate the provided code's logic:
The loop `for(int i=arr.length-1; i>=0; i--)` iterates backward.
`if(curr.isEmpty() || curr.equals(".")) continue;` - This correctly skips empty and current directory components.
`else if(!st.isEmpty() && st.peek().equals("..") && !curr.equals("..")) st.pop();` - This is the most confusing part. If `curr` is a directory name and the stack top is `..`, it pops `..`. This implies `..` is pushed first, then a directory name, and then this `..` is removed. This seems to be handling cases like `/a/b/../c` by processing `c`, then `..`, then `b`, then `a`. If `c` is pushed, then `..` is encountered, and the stack top is `c`, it would pop `c`. This is not standard.

Let's assume the provided code has a specific, albeit unconventional, logic. The standard stack approach iterates forward:
1. Split path by '/'.
2. Initialize stack.
3. For each component:
   - If component is "." or empty, continue.
   - If component is "..": if stack not empty, pop.
   - Else (directory name): push component.
4. Build result from stack.

The provided code's logic seems to be:
Iterate backward.
- If `curr` is `.` or empty, skip.
- If `curr` is `..`:
    - If stack is not empty and top is `..`, push `..`. (This part is missing in the provided code, it only handles popping)
    - If stack is empty or top is not `..`, push `..`.
- If `curr` is a directory name:
    - If stack is not empty and top is `..`, pop `..`. (This is what `else if(!st.isEmpty() && st.peek().equals("..") && !curr.equals("..")) st.pop();` does)
    - Else, push `curr`.

This backward iteration with the given logic is quite complex and likely prone to errors or misinterpretation. The standard forward iteration with a stack is much clearer.

Let's re-comment the provided code assuming its intended (though unusual) logic:
```java
class Solution {
    public String simplifyPath(String path) {
        // Stack to store components. The logic here is unconventional, processing backward.
        Stack<String> st = new Stack<>();
        // Split the path into components using '/' as delimiter.
        String[] arr = path.split("/");
        
        // Iterate through the components from right to left (end to beginning).
        for(int i=arr.length-1; i>=0; i--){
            String curr = arr[i];
            // Ignore empty components (e.g., from multiple slashes) and the current directory marker ".".
            if(curr.isEmpty() || curr.equals(".")) continue;
            // This is the core of the unconventional logic:
            // If the stack is NOT empty, AND the top element is "..", AND the current component is NOT "..",
            // then pop the ".." from the stack. This effectively means a directory name cancels out a preceding "..".
            // Example: path = "/a/b/../c". arr = ["", "a", "b", "..", "c"].
            // Processing "c": push "c". Stack: ["c"]
            // Processing "..": push "..". Stack: ["c", ".."]
            // Processing "b": curr="b". Stack top is "..". !curr.equals("..") is true. Pop "..". Stack: ["c"]
            // Processing "a": curr="a". Stack top is "c". This condition is false. Push "a". Stack: ["c", "a"]
            // This logic seems to be building a reversed path where ".." is handled by popping it when a directory name is encountered.
            else if(!st.isEmpty() && st.peek().equals("..") && !curr.equals("..")) st.pop();
            // Otherwise, push the current component onto the stack. This includes directory names that don't cancel a "..",
            // and ".." components that are not cancelled.
            else st.push(curr);
        }
        
        // After the loop, any remaining ".." at the top of the stack represent going above the root.
        // Remove them to ensure the path doesn't go above the root directory.
        while(!st.isEmpty() && st.peek().equals(".."))st.pop();
        
        // Build the final simplified path string. Start with the root directory "/".
        StringBuilder sb = new StringBuilder("/");
        // Process the stack to construct the path. Since we processed backward, the stack now holds components in reverse order of their final appearance.
        while(!st.isEmpty()){
            // If there's more than one element left in the stack, it means we need a "/" separator.
            // Pop the element and append it with a "/".
            if(st.size() > 1) sb.append(st.pop() + "/");
            // If it's the last element, just pop and append it without a trailing "/".
            else sb.append(st.pop());
        }
        
        // Return the constructed simplified path.
        return sb.toString();
    }
}
```
*Final Note on Provided Code:* The provided code's logic is indeed unconventional and harder to reason about than the standard forward-iterating stack approach. The backward iteration with the specific `else if` condition is attempting to resolve `../` pairs by popping `..` when a directory name is encountered. This is a valid, though less intuitive, way to solve it. The final `while(!st.isEmpty() && st.peek().equals(".."))st.pop();` handles cases like `/../` or `/a/../../`.

## Interview Tips
1.  **Clarify Path Components:** Explicitly ask the interviewer about how to handle '.', '..', empty strings, and leading/trailing slashes.
2.  **Explain Stack Usage:** Clearly articulate why a stack is suitable for this problem, relating push/pop operations to directory navigation.
3.  **Walk Through Examples:** Use examples like `/home/`, `/../`, `/home//foo/`, `/a/./b/../../c/` to demonstrate your understanding and algorithm.
4.  **Discuss Edge Cases:** Be prepared to discuss edge cases like an empty path, a path that simplifies to just "/", or a path that requires going above the root.
5.  **Consider Alternative Implementations:** Briefly mention that while a stack is common, other approaches (like string manipulation with careful indexing) are possible but might be more complex.

## Revision Checklist
- [ ] Understand the problem: absolute path simplification.
- [ ] Identify special path components: '.', '..', empty strings.
- [ ] Choose appropriate data structure: Stack.
- [ ] Implement splitting the path.
- [ ] Handle '.' and empty components.
- [ ] Implement '..' logic (pop if stack not empty).
- [ ] Handle cases where '..' might go above root.
- [ ] Construct the final path string correctly.
- [ ] Test with various edge cases.

## Similar Problems
*   [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) (Uses stack for a different purpose)
*   [71. Simplify Path](https://leetcode.com/problems/simplify-path/) (This is the problem itself)
*   [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) (Classic stack problem for matching pairs)

## Tags
`Stack` `String` `Array`
