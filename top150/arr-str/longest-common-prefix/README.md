# Longest Common Prefix

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `String` `Trie`  
**Time:** O(n log n)  
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
* Problem: Find the longest common prefix among an array of strings.
* Solution: Sort the array, compare the first and last string, find the common prefix.

## Intuition
The key insight is that the longest common prefix must be a prefix of all strings. By sorting the array, we can easily find the most different pair of strings (i.e., one with the longest common prefix and another with a shorter common prefix). Then, by comparing these two strings, we can efficiently find the common prefix.

## Algorithm
1. Sort the input array of strings.
2. Set `s1` to the first string in the sorted array and `s2` to the last string in the sorted array.
3. Initialize an index `idx` to 0.
4. While `idx` is less than the length of both `s1` and `s2`, check if the characters at index `idx` are equal. If they are, increment `idx`. Otherwise, break out of the loop.
5. Return the substring of `s1` from index 0 to `idx`.

## Concept to Remember
* Sorting algorithms can be used to solve string-related problems by reducing complexity and making comparisons easier.
* The concept of a prefix tree (or trie) is not needed here; instead, we use the sorted array approach.

## Common Mistakes
* Not considering the time complexity of sorting the array, which might be O(n log n).
* Not handling edge cases where the input array is empty or contains only one string.
* Misunderstanding how to find the common prefix between two strings.

## Complexity Analysis
- Time: O(n log n) due to sorting.
- Space: O(1), excluding space needed for sorting.

## Commented Code
```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        // Sort the array of strings
        Arrays.sort(strs);
        
        // Set s1 and s2 to the first and last string in the sorted array
        String s1 = strs[0];
        String s2 = strs[strs.length-1];
        
        int idx = 0; // Initialize index
        
        while (idx < s1.length() && idx < s2.length()) {
            // Check if characters at index are equal
            if (s1.charAt(idx) == s2.charAt(idx)) {
                idx++;
            } else {
                break;
            }
        }
        
        return s1.substring(0, idx);
    }
}
```

## Interview Tips
* Be prepared to explain why sorting the array is efficient for this problem.
* Show that you understand how to handle edge cases (e.g., empty array).
* Practice solving similar problems to improve your efficiency and accuracy.

## Revision Checklist
- [ ] Understand the role of sorting in reducing complexity.
- [ ] Review edge cases (empty or single-string inputs).
- [ ] Practice solving similar string-related problems efficiently.

## Similar Problems
* `Longest Common Substring`
* `Longest Palindromic Substring`
* `Shortest Unsorted Array`

## Tags
`Array` `String` `Sorting`
