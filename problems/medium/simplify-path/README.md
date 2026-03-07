# Simplify Path

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Stack`  
**Time:** O(N) - where N is the length of the input path string.  
**Space:** O(N) - where N is the length of the input path string.

---

## Solution (java)

```java
class Solution {
        public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        StringBuilder res = new StringBuilder();
        String[] arr = path.split("/");
        for (int i = 0;i < arr.length;i++)
        {
            if (arr[i].equals(".."))
            {
                if (!stack.isEmpty())
                    stack.pop();
           //     else
           //         continue;
            }
            else if (arr[i].equals(".") || arr[i].equals(""))
                continue;
            else
                stack.push(arr[i]);
        }
        for (int i = 0; i < stack.size();i++)
        {
            res.append("/").append(stack.get(i));
        }
    
        if (res.isEmpty())
            return "/";
        else
            return res.toString();
    }

}
```

---

---
## Problem Summary

The problem asks us to simplify a given absolute Unix-style file system path. This means we need to handle special directory names like "." (current directory), ".." (parent directory), and multiple consecutive slashes. The simplified path should start with a single slash, have directories separated by single slashes, and not end with a slash (unless it's the root directory "/").

## Approach and Intuition

The core idea is to process the path components and maintain a representation of the "current" directory structure. A stack is a natural fit for this because navigating up a directory ("..") corresponds to popping from the stack, and entering a directory corresponds to pushing onto the stack.

1.  **Split the path:** The path is split by the delimiter "/". This gives us an array of directory or special component names.
2.  **Iterate and process components:** We iterate through each component obtained from the split.
    *   If the component is "..": This signifies moving up one directory. If the stack is not empty, we pop the last directory from the stack. If the stack is empty, it means we are already at the root, so ".." has no effect.
    *   If the component is "." or an empty string "": These represent the current directory or redundant slashes, respectively. They don't change the path, so we simply ignore them.
    *   If the component is anything else (a valid directory name): This signifies entering a directory. We push this directory name onto the stack.
3.  **Reconstruct the path:** After processing all components, the stack contains the sequence of directories that form the simplified path. We iterate through the stack, prepending each directory name with a "/" to form the final path string.
4.  **Handle edge cases:**
    *   If the resulting path is empty (meaning the stack was empty), it implies the simplified path is the root directory, so we return "/".
    *   Otherwise, we return the constructed path string.

## Complexity Analysis

*   **Time:** O(N) - where N is the length of the input path string.
    *   Splitting the string takes O(N) time.
    *   Iterating through the split components (at most N components) and performing stack operations (push/pop) takes O(1) on average for each component.
    *   Reconstructing the path from the stack also takes time proportional to the number of elements in the stack, which is at most O(N).
*   **Space:** O(N) - where N is the length of the input path string.
    *   The `split` operation might create a temporary array of strings, which in the worst case could store parts of the original string, contributing to O(N) space.
    *   The `stack` can store up to O(N) directory names in the worst case (e.g., "/a/b/c/...").
    *   The `StringBuilder` used for reconstruction can also grow up to O(N) in size.

## Code Walkthrough

```java
class Solution {
    public String simplifyPath(String path) {
        // 1. Initialize a stack to store directory names.
        Stack<String> stack = new Stack<>();
        // 2. Initialize a StringBuilder to construct the result.
        StringBuilder res = new StringBuilder();
        // 3. Split the input path by the '/' delimiter.
        String[] arr = path.split("/");

        // 4. Iterate through each component obtained from the split.
        for (int i = 0; i < arr.length; i++) {
            // 5. Handle ".." (move up one directory).
            if (arr[i].equals("..")) {
                // If the stack is not empty, pop the last directory.
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                // If stack is empty, ".." at root has no effect, so we do nothing.
            }
            // 6. Handle "." (current directory) or "" (empty string from consecutive slashes).
            else if (arr[i].equals(".") || arr[i].equals("")) {
                // These components are ignored.
                continue;
            }
            // 7. Handle valid directory names.
            else {
                // Push the directory name onto the stack.
                stack.push(arr[i]);
            }
        }

        // 8. Reconstruct the simplified path from the stack.
        // Iterate through the stack elements.
        for (int i = 0; i < stack.size(); i++) {
            // Append a '/' followed by the directory name.
            res.append("/").append(stack.get(i));
        }

        // 9. Handle the edge case where the simplified path is the root directory.
        if (res.isEmpty()) {
            return "/"; // If the result is empty, return the root path.
        } else {
            // 10. Return the constructed simplified path.
            return res.toString();
        }
    }
}
```

## Interview Tips

*   **Clarify edge cases:** Before coding, ask the interviewer about how to handle cases like an empty input path, paths with only slashes, paths ending with slashes, and paths starting with multiple slashes.
*   **Explain your data structure choice:** Justify why a stack is a good choice for this problem (LIFO behavior for directory traversal).
*   **Walk through examples:** Be prepared to trace your code with examples like `/home/`, `/../`, `/home//foo/`, `/a/./b/../../c/`.
*   **Discuss complexity:** Clearly state the time and space complexity and explain your reasoning.
*   **Consider alternatives:** Think about whether other data structures could be used (e.g., a `LinkedList` acting as a deque) and their trade-offs.
*   **Code clarity:** Use meaningful variable names and add comments where necessary, especially for the logic handling special directory components.

## Optimization and Alternatives

*   **Using `Deque` instead of `Stack`:** While `Stack` is a legacy class, `Deque` (e.g., `ArrayDeque`) is generally preferred in modern Java. `ArrayDeque` offers better performance and is more flexible. The logic would remain the same, just replacing `Stack` with `ArrayDeque` and `push`/`pop` with `addLast`/`removeLast`.
*   **In-place modification (less practical here):** For some path simplification problems, if the path could be modified in place, it might save space. However, given the string input and the need to build a new string, this is less applicable.
*   **Manual String Building:** Instead of `split` and then `append`, one could iterate through the string character by character, building up directory names and managing the stack directly. This might offer a slight performance edge by avoiding intermediate string array creation, but it significantly increases code complexity.

## Revision Checklist

*   [ ] Understand the problem statement thoroughly.
*   [ ] Identify special path components: ".", "..", "//".
*   [ ] Choose an appropriate data structure (Stack/Deque).
*   [ ] Implement logic for handling each special component.
*   [ ] Handle the root directory case ("/") correctly.
*   [ ] Ensure the output path starts with "/" and has single slashes between components.
*   [ ] Analyze time and space complexity.
*   [ ] Practice explaining the solution and its trade-offs.

## Similar Problems

*   LeetCode 71: Simplify Path (This is the problem itself)
*   LeetCode 20: Valid Parentheses (Uses a stack for matching delimiters)
*   LeetCode 84: Largest Rectangle in Histogram (Uses a stack for finding next smaller elements)
*   LeetCode 22: Generate Parentheses (Often solved with recursion/backtracking, but stack-like thinking can be involved)

## Tags
`Stack` `String` `Array`
