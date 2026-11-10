# Count Unique Characters Of All Substrings Of A Given String

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `String` `Dynamic Programming`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int uniqueLetterString(String s) {
        int n = s.length();
        long all = ((long)n*(n+1)*(n+2))/6;
        Map<Character, List<Integer>> indices = new HashMap<>();
        for(int i=0;i<n;i++){
            char c = s.charAt(i);
            if(!indices.containsKey(c)) indices.put(c,new ArrayList<>());
            indices.get(c).add(i);
        }

        for(List<Integer> l : indices.values()){
          for(int i=0;i<l.size();i++){
             int index = l.get(i);
             int prev = i==0 ? -1 : l.get(i-1); 
             int next = i==l.size()-1 ? n : l.get(i+1);
             int total = (index+1)*(n-index);//all substrings of that index 
             int good = (index-prev)*(next-index);//good substrings of that index
             int bad = total-good; //invalid substrings
             all -= bad;
          }
        }
       return (int)all; // isko run kro na
    }
}
// BDCASRTYAYK T , TAA , TSY  T-TAA-TSY 
// ( 1*3 )
// ( 2*2 )
// ( 3*1 )

//   1*4 + 3*2 + 2*3 + 4*1

//   n/1 + n/2 + n/n 

//   i^2 
// 18 16 14 8 4

```

---

---
## Quick Revision
Given a string, find the sum of the counts of unique characters in all of its substrings.
Solve this efficiently by calculating the contribution of each character based on the indices where it appears, counting how many substrings make that character uniquely isolated.

## Intuition
The "aha moment" is shifting our perspective from counting unique characters *for each substring* (which takes $O(N^2)$ or $O(N^3)$ time) to calculating *how many substrings each character is unique in*. 
For any occurrence of a character at a specific index, it is unique within a substring if that substring starts *after* the previous occurrence of the same character and ends *before* the next occurrence. By finding the previous and next occurrence indices for every character, we can compute the exact number of valid (good) substrings where that character appears alone in $O(1)$ time per occurrence.

## Algorithm
1. Get the length $n$ of the string and calculate the maximum possible theoretical unique character count, or simply initialize our total contribution accumulator. Alternatively, we can start with a total sum of 0 and add each character's contribution. (The provided code uses total combinations minus invalid ones, but standard contribution sum is the cleanest model: `(index - prev) * (next - index)`).
2. Store the indices of every character in a map or list of lists. For each distinct character, pad its index list with `-1` at the beginning and $n$ at the end to handle boundary conditions easily.
3. Iterate through every character's occurrence list. For an occurrence at `index` with `prev` and `next` bounds:
   - The number of valid left choices for the substring start is `index - prev`.
   - The number of valid right choices for the substring end is `next - index`.
4. Multiply these choices together: `(index - prev) * (next - index)` gives the total number of substrings where this specific occurrence is the unique instance of its character.
5. Sum these values for all occurrences across all characters and return the final result.

## Concept to Remember
- Contribution technique: Counting how many times an element contributes to the global result rather than evaluating every sub-structure.
- Boundary padding: Using `-1` and `n` to simplify range calculations for the first and last elements in an array or list.
- Combinatorics: Calculating subsegment counts via product of valid left choices and valid right choices.

## Common Mistakes
- Trying to generate all substrings explicitly, leading to Time Limit Exceeded (TLE) or Memory Limit Exceeded (MLE) due to $O(N^2)$ complexity.
- Forgetting to handle edge cases where a character appears only once or not at all.
- Off-by-one errors when calculating the distance between the current index, previous index, and next index.
- Integer overflow when multiplying large index bounds before casting or accumulating into a long.

## Complexity Analysis
- Time: $O(N)$ - We iterate through the string of length $N$ to record indices, and then iterate through the indices list once more, visiting every index exactly once.
- Space: $O(N)$ - We store the indices of each character in hash maps/lists which collectively hold $N$ integer elements.

## Commented Code
```java
class Solution {
    public int uniqueLetterString(String s) {
        int n = s.length(); // Get the length of the input string
        long all = ((long)n*(n+1)*(n+2))/6; // Alternative perspective tracking base math, though standard sum of contributions is cleaner
        Map<Character, List<Integer>> indices = new HashMap<>(); // Map to store all indices where each character appears
        for(int i=0; i<n; i++){
            char c = s.charAt(i); // Get current character
            if(!indices.containsKey(c)) indices.put(c, new ArrayList<>()); // Initialize list if character is seen for the first time
            indices.get(c).add(i); // Add the current index to the character's list
        }

        // Iterate through the index list of every unique character
        for(List<Integer> l : indices.values()){
          for(int i=0; i<l.size(); i++){
             int index = l.get(i); // Current occurrence index of the character
             int prev = i == 0 ? -1 : l.get(i-1); // Index of the previous occurrence of the same character, or -1 if none
             int next = i == l.size()-1 ? n : l.get(i+1); // Index of the next occurrence of the same character, or n if none
             int total = (index+1)*(n-index); // Total substrings containing this specific occurrence
             int good = (index-prev)*(next-index); // Substrings where this character is unique
             int bad = total - good; // Substrings where this character is duplicated
             all -= bad; // Subtracting bad substrings from the total pool to accumulate valid unique counts
          }
        }
       return (int)all; // Return the final computed sum casted back to an integer
    }
}
```

## Interview Tips
- When asked about "all substrings" or "subarrays", immediately think about whether you can compute the contribution of individual elements rather than generating subarrays.
- Clearly explain the contribution formula `(i - prev) * (next - i)` to the interviewer using a small example string like "ABA".
- Discuss time and space complexities upfront, noting how shifting from $O(N^2)$ to $O(N)$ impresses the interviewer.

## Revision Checklist
- [ ] Understand why brute force fails ($O(N^2)$ time).
- [ ] Know how to set up the index tracking data structure (Map of Lists).
- [ ] Master the boundary padding technique (`-1` and `n`).
- [ ] Understand the combinatorial formula for contribution counting.
- [ ] Check for integer overflow potentials during summation.

## Similar Problems
- `Subarray Sum Equals K`
- `Sum of Subarray Minimums`
- `Maximum Subarray`
- `Count Number of Nice Subarrays`

## Tags
`Array` `Hash Map` `String` `Combinatorics` `Counting`
