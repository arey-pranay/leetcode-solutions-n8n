# Count Unique Characters Of All Substrings Of A Given String

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `String` `Dynamic Programming`  
**Time:** O(n)  
**Space:** O(n)

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
Given a string `s`, calculate the sum of the counts of unique characters across all of its possible substrings.
Solve this efficiently by calculating the contribution of each character based on the indices where it appears, using the count of "good" substrings where the character is uniquely present.

## Intuition
Instead of generating all substrings (which takes $O(n^2)$ time), we can count how many times each character contributes uniquely to the overall sum. A character at index `i` is unique within any substring that starts between the previous occurrence of this character (exclusive) and `index` (inclusive), and ends between `index` (inclusive) and the next occurrence of this character (exclusive). By multiplying the number of valid starting choices by the number of valid ending choices, we find the exact number of substrings in which this specific occurrence is unique. Summing this up for all occurrences of all characters gives the total answer in linear time.

## Algorithm
1. Get the length `n` of the string `s`.
2. Initialize a hash map to store the list of indices where each character appears in the string.
3. Traverse the string from left to right, populating the index lists for every character, and include virtual boundary indices `-1` and `n` conceptually.
4. Iterate through each character's list of indices. For each occurrence at position `index`:
   - Identify the previous occurrence index (`prev`) and the next occurrence index (`next`).
   - Calculate the number of valid starting points: `index - prev`.
   - Calculate the number of valid ending points: `next - index`.
   - Multiply these two values to get the contribution (number of substrings where this character is unique).
5. Accumulate these contributions for all characters across all their occurrences.
6. Return the accumulated sum as an integer.

## Concept to Remember
- Contribution technique: Counting how many times each element contributes to the final result instead of generating all structures.
- Combinatorics: Using the multiplication rule to find the number of subarrays bounded by previous and next occurrences.
- Index mapping: Pre-storing indices of identical characters to quickly determine boundaries.

## Common Mistakes
- Forgetting to handle edge cases where a character appears only once or not at all.
- Off-by-one errors when calculating the range of valid starting and ending indices (`prev` and `next`).
- Integer overflow during multiplication before casting or accumulating into a larger data type.
- Trying to simulate the problem by generating all substrings directly, leading to Time Limit Exceeded (TLE).

## Complexity Analysis
- Time: $O(n)$ - We iterate through the string of length `n` once to record indices, and then iterate through the index lists a second time, visiting each index exactly once.
- Space: $O(n)$ - The hash map stores every index of every character, which collectively takes linear space proportional to the length of the string.

## Commented Code
```java
class Solution {
    public int uniqueLetterString(String s) {
        // Store the length of the string to easily determine boundaries
        int n = s.length();
        
        // A map to keep track of all indices where each character appears in the string
        Map<Character, List<Integer>> indices = new HashMap<>();
        
        // Populate the map with indices for each character
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // If the character is not yet in the map, initialize its list
            if(!indices.containsKey(c)) indices.put(c, new ArrayList<>());
            // Add the current index to the character's list of occurrences
            indices.get(c).add(i);
        }

        // Variable to accumulate the total count of unique characters across all substrings
        long totalUnique = 0;

        // Iterate through the index lists of every unique character
        for(List<Integer> l : indices.values()) {
            // Iterate through each occurrence of the current character
            for(int i = 0; i < l.size(); i++) {
                int index = l.get(i);
                
                // Find the previous occurrence index; if it's the first occurrence, boundary is -1
                int prev = i == 0 ? -1 : l.get(i - 1); 
                
                // Find the next occurrence index; if it's the last occurrence, boundary is n
                int next = i == l.size() - 1 ? n : l.get(i + 1);
                
                // Number of valid starting positions for a substring containing this unique character
                long leftChoices = index - prev;
                
                // Number of valid ending positions for a substring containing this unique character
                long rightChoices = next - index;
                
                // The contribution is the product of choices on the left and right
                totalUnique += leftChoices * rightChoices;
            }
        }
        
        // Return the final accumulated count cast back to an integer
        return (int) totalUnique; 
    }
}
```

## Interview Tips
- Clarify whether the string contains only uppercase letters, lowercase letters, or any ASCII characters.
- Explain the "contribution technique" clearly to the interviewer before writing any code, as it demonstrates strong algorithmic thinking.
- Walk through a small manual example (like `"ABA"`) to prove how the index boundaries (`prev` and `next`) work.
- Discuss how this transforms an $O(n^2)$ or $O(n^3)$ brute-force solution into an optimal $O(n)$ approach.

## Revision Checklist
- [ ] Understand the contribution of a single element to all subarrays.
- [ ] Know how to find previous and next occurrences using a hash map of indices.
- [ ] Handle boundary conditions for the first and last occurrences of a character.
- [ ] Avoid integer overflow during combination products.

## Similar Problems
- Subarray Sum Equals K
- Sum of Subarray Minimums
- Maximum Width Ramp

## Tags
`String` `Hash Map` `Combinatorics` `Dynamic Programming`
