# Find The Length Of The Longest Common Prefix

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Trie`  
**Time:** O(N * L + M * L)  
**Space:** O(N * L)

---

## Solution (java)

```java
class Solution {
    class TrieNode {
        TrieNode[] mChildren = new TrieNode[10];
    }
    
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        TrieNode root = new TrieNode();
        for (int num : arr1) {
            addToTrie(root, num);
        }

        int maxLen = 0;

        for (int num : arr2) {
            maxLen = Math.max(maxLen, getCommonPrefixLen(root, num));
        }

        return maxLen;
    }

    private void addToTrie(TrieNode root, int num) {
        String numStr = String.valueOf(num);
        TrieNode currNode = root;
        for (int i = 0; i < numStr.length(); i++) {
            int charIndex = numStr.charAt(i) - '0';
            if (currNode.mChildren[charIndex] == null) {
                currNode.mChildren[charIndex] = new TrieNode();
            }
            currNode = currNode.mChildren[charIndex];
        }
    }

    private int getCommonPrefixLen(TrieNode root, int num) {
        String numStr = String.valueOf(num);
        TrieNode currNode = root;
        for (int i = 0; i < numStr.length(); i++) {
            int charIndex = numStr.charAt(i) - '0';
            if (currNode.mChildren[charIndex] == null) {
                return i;
            }
            currNode = currNode.mChildren[charIndex];
        }
        return numStr.length();
    }
}
```

---

---
## Quick Revision
This problem asks for the length of the longest common prefix between any number in `arr1` and any number in `arr2`.
We solve this by inserting all numbers from `arr1` into a Trie and then querying `arr2` for the longest prefix match against the Trie.

## Intuition
The core idea is that a Trie (prefix tree) is an excellent data structure for efficiently storing and searching for prefixes. By inserting all numbers from the first array into a Trie, we create a structure where we can quickly traverse and find common prefixes. When we iterate through the second array, for each number, we can traverse the Trie to see how far down the path we can go before a mismatch occurs or the Trie ends. This distance directly corresponds to the length of the common prefix.

## Algorithm
1.  **Trie Node Definition**: Define a `TrieNode` class. Each node will have an array of children (size 10 for digits '0'-'9').
2.  **Trie Construction**:
    *   Initialize an empty `TrieNode` as the `root`.
    *   For each integer `num` in `arr1`:
        *   Convert `num` to its string representation.
        *   Traverse the Trie, creating new nodes if a path for a digit doesn't exist.
        *   Mark the end of the number's path (though not strictly necessary for this problem, it's good Trie practice).
3.  **Longest Common Prefix Calculation**:
    *   Initialize `maxLen` to 0.
    *   For each integer `num` in `arr2`:
        *   Convert `num` to its string representation.
        *   Initialize `currentPrefixLen` to 0.
        *   Start traversing from the `root` of the Trie.
        *   For each digit in the string representation of `num`:
            *   Calculate the index for the digit.
            *   If the current Trie node does not have a child for this digit, break the inner loop (mismatch found).
            *   Otherwise, move to the child node and increment `currentPrefixLen`.
        *   Update `maxLen = Math.max(maxLen, currentPrefixLen)`.
4.  **Return Result**: Return `maxLen`.

## Concept to Remember
*   **Trie (Prefix Tree)**: A tree-like data structure used for efficient retrieval of keys in a dataset of strings. Each node represents a character, and paths from the root to nodes represent prefixes.
*   **String to Integer Conversion**: Understanding how to convert integers to strings and access individual digits is crucial for Trie operations.
*   **Greedy Approach**: At each step of traversing the Trie for a number in `arr2`, we try to extend the common prefix as much as possible.

## Common Mistakes
*   **Incorrect Trie Implementation**: Errors in handling child nodes, especially when creating new ones or accessing existing ones.
*   **Off-by-One Errors**: Miscalculating the length of the common prefix, particularly when a mismatch occurs or when a number from `arr2` is a prefix of a number in `arr1`.
*   **Handling Edge Cases**: Not considering empty arrays, single-digit numbers, or numbers with leading zeros (though the problem statement implies positive integers without leading zeros).
*   **Inefficient String Conversion**: Repeatedly converting numbers to strings inside loops without optimization.

## Complexity Analysis
- Time: O(N * L + M * L) - where N is the number of elements in `arr1`, M is the number of elements in `arr2`, and L is the maximum number of digits in any integer. Building the Trie takes O(N * L) time, and querying for each element in `arr2` takes O(M * L) time.
- Space: O(N * L) - in the worst case, the Trie can store all digits of all numbers from `arr1`, leading to a space complexity proportional to the total number of digits.

## Commented Code
```java
class Solution {
    // Define a TrieNode class to represent nodes in our prefix tree.
    class TrieNode {
        // Each node has an array of children, indexed by digits '0' through '9'.
        // The size is 10 because we are dealing with decimal digits.
        TrieNode[] mChildren = new TrieNode[10];
    }
    
    // The main function to find the length of the longest common prefix.
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        // Initialize the root of the Trie.
        TrieNode root = new TrieNode();
        
        // Insert all numbers from the first array into the Trie.
        for (int num : arr1) {
            // Call a helper function to add each number to the Trie.
            addToTrie(root, num);
        }

        // Initialize a variable to keep track of the maximum common prefix length found so far.
        int maxLen = 0;

        // Iterate through each number in the second array.
        for (int num : arr2) {
            // For each number, find the length of its common prefix with any number in arr1 (stored in Trie).
            // Update maxLen if the current common prefix length is greater.
            maxLen = Math.max(maxLen, getCommonPrefixLen(root, num));
        }

        // Return the overall maximum common prefix length.
        return maxLen;
    }

    // Helper function to add an integer to the Trie.
    private void addToTrie(TrieNode root, int num) {
        // Convert the integer to its string representation to easily access digits.
        String numStr = String.valueOf(num);
        // Start traversal from the root of the Trie.
        TrieNode currNode = root;
        // Iterate through each character (digit) of the number string.
        for (int i = 0; i < numStr.length(); i++) {
            // Get the current digit and convert it to an integer index (0-9).
            int charIndex = numStr.charAt(i) - '0';
            // If the child node for this digit does not exist, create a new one.
            if (currNode.mChildren[charIndex] == null) {
                currNode.mChildren[charIndex] = new TrieNode();
            }
            // Move to the child node corresponding to the current digit.
            currNode = currNode.mChildren[charIndex];
        }
        // Note: For this specific problem, we don't need to mark the end of a word,
        // as we are only interested in prefix lengths.
    }

    // Helper function to get the length of the common prefix for a given number against the Trie.
    private int getCommonPrefixLen(TrieNode root, int num) {
        // Convert the integer to its string representation.
        String numStr = String.valueOf(num);
        // Start traversal from the root of the Trie.
        TrieNode currNode = root;
        // Iterate through each character (digit) of the number string.
        for (int i = 0; i < numStr.length(); i++) {
            // Get the current digit and convert it to an integer index.
            int charIndex = numStr.charAt(i) - '0';
            // If the current Trie node does not have a child for this digit,
            // it means the common prefix ends here. Return the current length (i).
            if (currNode.mChildren[charIndex] == null) {
                return i; // 'i' represents the number of digits matched so far.
            }
            // Move to the child node corresponding to the current digit.
            currNode = currNode.mChildren[charIndex];
        }
        // If we successfully traversed the entire number string without a mismatch,
        // it means the entire number is a prefix of at least one number in arr1.
        // Return the full length of the number string.
        return numStr.length();
    }
}
```

## Interview Tips
1.  **Explain the Trie**: Clearly articulate why a Trie is suitable for prefix-related problems. Discuss its structure and how it stores prefixes.
2.  **Walk Through an Example**: Use a small example (e.g., `arr1 = [123, 124]`, `arr2 = [125, 12]`) to demonstrate how the Trie is built and how `getCommonPrefixLen` works.
3.  **Discuss Alternatives (Briefly)**: You might briefly mention a brute-force approach (comparing every pair of numbers) and explain why it's less efficient (e.g., O(N*M*L)) compared to the Trie solution.
4.  **Clarify Constraints**: Ask about the range of numbers, potential for negative numbers, or leading zeros if not explicitly stated, as this might affect the Trie implementation (e.g., character set).

## Revision Checklist
- [ ] Understand the problem: Find the longest common prefix length between numbers in two arrays.
- [ ] Recall Trie structure: Nodes, children array for digits.
- [ ] Implement `addToTrie`: Convert int to string, traverse, create nodes.
- [ ] Implement `getCommonPrefixLen`: Convert int to string, traverse Trie, count matches.
- [ ] Handle mismatches correctly in `getCommonPrefixLen`.
- [ ] Handle full number match correctly in `getCommonPrefixLen`.
- [ ] Calculate overall `maxLen`.
- [ ] Analyze Time and Space Complexity.

## Similar Problems
*   Longest Common Prefix (String Array)
*   Implement Trie (Prefix Tree)
*   Word Break II
*   Add and Search Word - Data structure design

## Tags
`Trie` `Array` `String`
