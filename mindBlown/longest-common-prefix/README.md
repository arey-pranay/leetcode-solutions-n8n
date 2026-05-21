# Longest Common Prefix

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `String` `Trie`  
**Time:** O(N log N * M)  
**Space:** O(1)

---

## Solution (java)

```java
// class Solution {
//     class Node{
//         Node[] children;
//         String word;
//         Node(){
//             this.children = new Node[26];
//             this.word = "";
//         }
//     }
//     public void add(String s){
//         Node curr = root;
//         StringBuilder sb = new StringBuilder("");
//         for(char c : s.toCharArray()){
//             int i= c-'a';
//             if(curr.children[i] == null) curr.children[i] = new Node();
//             curr = curr.children[i];
//             sb.append(c);
//             curr.word = sb.toString();
//         }
//     }
//     public String check(String s){
//         Node curr = root;
//         for(char c : s.toCharArray()){
//             int i = c-'a';
//             if(curr.children[i] == null) return curr.word;
//             else curr = curr.children[i];
//         }
//         return curr.word;
//     }
//     Node root = new Node();
    
//     public String longestCommonPrefix(String[] strs) {
//         add(strs[0]);
//         String ans = strs[0];
//         for(int i=1;i<strs.length;i++){
//             String temp = check(strs[i]);
//             if(temp.length() < ans.length()) ans = temp;
//         }
//         return ans.toString();
//     }
// }


class Solution {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String s1 = strs[0];
        String s2 = strs[strs.length-1];
        int idx = 0;
        while(idx < s1.length() && idx < s2.length()){
            if(s1.charAt(idx) == s2.charAt(idx)){
                idx++;
            }else{
                break;
            }
        }
        return s1.substring(0, idx);
    }
}

//string m sorting alphabets k hisaab se prefix mtlb uske according hi hoti hai naa ki length k 



//   []
// /    \
// f
// /
// l
// /\
// o i
// /  \
// w  g
// /   \
// e   h 
// /    \
// r    t
```

---

---
## Quick Revision
Find the longest common prefix string amongst an array of strings.
The solution involves sorting the array and comparing the first and last strings.

## Intuition
If we sort the array of strings alphabetically, the longest common prefix must be a prefix of both the lexicographically smallest and largest strings in the sorted array. This is because any string in between them will share at least that same prefix. Therefore, we only need to compare the first and last strings after sorting.

## Algorithm
1. Handle edge cases: If the input array is empty or null, return an empty string.
2. Sort the input array of strings alphabetically.
3. Take the first string (`s1`) and the last string (`s2`) from the sorted array.
4. Initialize an index `idx` to 0.
5. Iterate while `idx` is within the bounds of both `s1` and `s2`.
6. In each iteration, compare the characters at `idx` in `s1` and `s2`.
7. If the characters match, increment `idx`.
8. If the characters do not match, break the loop.
9. The longest common prefix is the substring of `s1` from index 0 up to (but not including) `idx`. Return this substring.

## Concept to Remember
*   Lexicographical Ordering: Understanding how strings are sorted alphabetically.
*   Prefix Property: The common prefix of a set of strings must be a prefix of the lexicographically smallest and largest strings.
*   String Manipulation: Efficiently extracting substrings.

## Common Mistakes
*   Not handling empty or null input arrays.
*   Incorrectly comparing characters or loop conditions.
*   Returning the wrong substring (e.g., including an extra character or missing one).
*   Assuming all strings will have a common prefix (e.g., not breaking when characters mismatch).
*   Overcomplicating the solution when a simpler approach like sorting exists.

## Complexity Analysis
- Time: O(N log N * M) - reason: Sorting the array of N strings takes O(N log N) comparisons, and each comparison can take up to O(M) time where M is the length of the longest string. The subsequent character-by-character comparison takes O(M) time.
- Space: O(1) or O(log N) or O(N) - reason: Depending on the sorting algorithm used by the language's standard library. In-place sorts might use O(log N) for recursion stack, or O(N) if a non-in-place sort is used. If we consider the space for storing the sorted array, it could be O(N*M) in some implementations, but typically sorting modifies the input array or uses auxiliary space proportional to the number of elements or recursion depth. For this specific Java solution, `Arrays.sort` for strings typically uses Timsort which has O(N) auxiliary space in the worst case. However, if we consider the space used *beyond* the input, it's often considered O(1) or O(log N) for the recursion stack of quicksort-like algorithms.

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for sorting functionality.

class Solution {
    public String longestCommonPrefix(String[] strs) { // Define the method to find the longest common prefix.
        // Handle edge cases: if the array is null or empty, there's no common prefix.
        if (strs == null || strs.length == 0) {
            return ""; // Return an empty string.
        }

        // Sort the array of strings alphabetically.
        // This is the key insight: the LCP must be a prefix of the first and last string after sorting.
        Arrays.sort(strs);

        // Get the first string (lexicographically smallest) and the last string (lexicographically largest).
        String s1 = strs[0];
        String s2 = strs[strs.length - 1];

        // Initialize an index to track the current character position being compared.
        int idx = 0;

        // Iterate as long as the index is within the bounds of both strings.
        while (idx < s1.length() && idx < s2.length()) {
            // Compare the characters at the current index in both strings.
            if (s1.charAt(idx) == s2.charAt(idx)) {
                // If characters match, move to the next character.
                idx++;
            } else {
                // If characters don't match, the common prefix ends here. Break the loop.
                break;
            }
        }

        // The longest common prefix is the substring of s1 from the beginning up to the index where mismatch occurred.
        return s1.substring(0, idx); // Extract and return the common prefix.
    }
}
```

## Interview Tips
*   Clearly explain the sorting-based approach and why it works.
*   Mention the edge cases (empty array, single string array) and how they are handled.
*   Be prepared to discuss the time and space complexity of sorting.
*   If asked for an alternative, you could briefly mention a character-by-character comparison approach (vertical scanning) but highlight why the sorting method is often cleaner for this specific problem.

## Revision Checklist
- [ ] Understand the problem statement.
- [ ] Identify edge cases (empty/null input).
- [ ] Implement sorting of the string array.
- [ ] Correctly identify the first and last strings.
- [ ] Implement character-by-character comparison.
- [ ] Handle loop termination correctly.
- [ ] Extract the correct substring for the prefix.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Longest Common Prefix II (Harder variation, often involves Trie)
*   Find Common Characters
*   Repeated DNA Sequences

## Tags
`Array` `String` `Sorting`

## My Notes
trie is another approach, but this is also quite nice, sort krne pe first aur last elements me throughout jo common prefixes hai wo maintained rhege sort krne pr, so we only need to compare first and last strings then.
