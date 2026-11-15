# Find The Closest Palindrome

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Math` `String`  
**Time:** O(L)  
**Space:** O(L)

---

## Solution (java)

```java
class Solution {
    public String nearestPalindromic(String n) {
        //rightmost se change krna betetr hai, to mimize the difference
        // long num = String.valueOf(n);
        int len = n.length();
        int i=0,j=len-1;
        long num = Long.parseLong(n);
        List<Long> options = new ArrayList<>();
        StringBuilder sb = new StringBuilder(n);
        while(i<j) sb.setCharAt(j--,n.charAt(i++)); //mirror
        long a = Long.parseLong(sb.toString());
        long b = (long)Math.pow(10,len)+1, c = (long)Math.pow(10,len-1)-1; //999 or 1001
        long d =incdec(n,-1), e = incdec(n,1);
        options.add(a); options.add(b); options.add(c); options.add(d); options.add(e);
        // 1997 
        // inc  or dec  or mirror
        // 2002 or 1881 or 1991
        // 5    or 16   or 6 (the case in which incr is better)
        long ans = Long.MAX_VALUE   , minDiff = Long.MAX_VALUE;
        for(long option : options){
            long diff = Math.abs(num-option);
            if(option != num && diff< minDiff  ||  (diff == minDiff && option < ans)){
                minDiff = diff;
                ans = option; 
            }
        }
        return String.valueOf(ans);
    }
    public long incdec(String s, int change){
        StringBuilder half = new StringBuilder(s.substring(0, (s.length()+1)/2));
        long back = Long.parseLong(half.toString());
        back+=change;
        half = new StringBuilder(String.valueOf(back));
        StringBuilder ans = new StringBuilder(half);
        int start = s.length()%2 == 0 ? half.length()-1 : half.length()-2;
        for(int i=start;i>=0;i--)ans.append(half.charAt(i));
        return Long.parseLong(ans.toString());
    }
}

```

---

---
## Quick Revision
Given a string representing an integer, find the closest integer (not equal to itself) that is a palindrome.
Generate candidate palindromes using mirror techniques, edge cases like boundary powers of ten, and variations of the prefix, then find the one with the minimum absolute difference.

## Intuition
The "aha moment" is realizing that the closest palindrome to a number will always be formed by making minor modifications to its prefix and mirroring it. Because a palindrome's first half largely determines its second half, we only need to test a small, finite set of candidate options: mirroring the current prefix, modifying the prefix by incrementing or decrementing its middle/first half, and checking boundary palindromes like $10^k + 1$ and $10^{k-1} - 1$. By collecting these distinct candidates, we can evaluate each for the absolute minimum difference, handling ties by choosing the smaller numerical value.

## Algorithm
1. Parse the input string $n$ into a `long` value and note its length.
2. Generate the first candidate by mirroring the first half of the string onto the second half.
3. Generate boundary candidate palindromes: $10^{\text{len}} + 1$ and $10^{\text{len}-1} - 1$ to handle edge cases where digits roll over (e.g., $99 \rightarrow 101$ or $100 \rightarrow 99$).
4. Generate two more candidates by taking the prefix, incrementing/decrementing it by $1$, and mirroring it back.
5. Store all valid candidates in a list.
6. Iterate through all candidates in the list. For each, compute its absolute difference with the original number.
7. Track the candidate that yields the strictly smaller difference, or if the differences are equal, pick the numerically smaller option, ensuring the original number itself is excluded.
8. Return the best candidate as a string.

## Concept to Remember
- **Candidate Generation Strategy**: Instead of searching outward blindly, identify the limited structural forms the answer can take.
- **Edge Cases with Powers of Ten**: Numbers near boundaries (like $100$ or $999$) change digit length when becoming palindromes, requiring distinct handling.
- **Tie-Breaking Rules**: When absolute differences are identical, problems typically require returning the smaller numerical value.

## Common Mistakes
- Forgetting to exclude the original number itself if it happens to be a palindrome.
- Missing boundary edge cases like single-digit numbers or inputs like $10$, which require checking powers of ten minus/plus one.
- Incorrectly handling odd versus even length string slicing when incrementing or decrementing the prefix.
- Using integer overflow issues by not utilizing `long` types for large string inputs.

## Complexity Analysis
- Time: $O(L)$ - where $L$ is the length of the string, since string manipulations, substring operations, and checking a fixed small number of candidates take linear time proportional to the number of digits.
- Space: $O(L)$ - to store the candidate strings, builders, and the small collection of options.

## Commented Code
```java
class Solution {
    public String nearestPalindromic(String n) {
        // Get the length of the input string representing the number
        int len = n.length();
        // Initialize pointers for mirroring the string ends
        int i=0, j=len-1;
        // Parse the input string into a long for numerical comparisons later
        long num = Long.parseLong(n);
        // List to hold all potential palindrome candidates
        List<Long> options = new ArrayList<>();
        // StringBuilder to construct the mirrored palindrome version
        StringBuilder sb = new StringBuilder(n);
        // Mirror the first half of the string onto the second half
        while(i<j) sb.setCharAt(j--, n.charAt(i++)); 
        // Parse the purely mirrored version as our first candidate
        long a = Long.parseLong(sb.toString());
        // Generate boundary palindromes: e.g., 1001 for len 3, and 99 for len 3
        long b = (long)Math.pow(10, len) + 1, c = (long)Math.pow(10, len - 1) - 1; 
        // Generate candidates by decrementing and incrementing the prefix
        long d = incdec(n, -1), e = incdec(n, 1);
        // Add all generated candidates to our list of options
        options.add(a); options.add(b); options.add(c); options.add(d); options.add(e);
        
        // Variables to keep track of the best answer and its distance
        long ans = Long.MAX_VALUE, minDiff = Long.MAX_VALUE;
        // Evaluate every candidate option
        for(long option : options){
            // Calculate absolute difference from the original number
            long diff = Math.abs(num - option);
            // Check if this option is valid (not equal to original) and closer, or has a tie with a smaller value
            if(option != num && diff < minDiff || (diff == minDiff && option < ans)){
                minDiff = diff;
                ans = option; 
            }
        }
        // Return the best palindrome found as a string
        String.valueOf(ans);
        return String.valueOf(ans);
    }
    
    // Helper method to increment or decrement the prefix and mirror it
    public long incdec(String s, int change){
        // Extract the prefix (first half including the middle element for odd lengths)
        StringBuilder half = new StringBuilder(s.substring(0, (s.length() + 1) / 2));
        // Parse prefix to long and apply the change (-1 or +1)
        long back = Long.parseLong(half.toString());
        back += change;
        // Convert back to string and prepare for mirroring
        half = new StringBuilder(String.valueOf(back));
        StringBuilder ans = new StringBuilder(half);
        // Determine start index for mirroring depending on string length parity
        int start = s.length() % 2 == 0 ? half.length() - 1 : half.length() - 2;
        // Append characters backwards to form the full palindrome
        for(int i = start; i >= 0; i--) ans.append(half.charAt(i));
        // Return the newly formed candidate as a long
        return Long.parseLong(ans.toString());
    }
}
```

## Interview Tips
- Clearly state upfront that there are a finite number of candidate structures (mirroring, prefix+1, prefix-1, and digit-length boundary shifts).
- Walk through a small example like `123` to show how the candidates are generated and evaluated.
- Explicitly mention edge cases like handling tie-breakers (choosing the smaller number) and bounds like inputs starting with `1` or consisting entirely of `9`s.

## Revision Checklist
- [ ] Understand why only 5 specific candidate patterns are necessary.
- [ ] Know how to handle boundary cases like `99` -> `101` and `100` -> `99`.
- [ ] Implement prefix increment and decrement correctly for both odd and even lengths.
- [ ] Handle tie-breaking conditions correctly (`option < ans`).
- [ ] Ensure the original number is never returned as the answer.

## Similar Problems
- `Super Palindromes`
- `Palindrome Number`
- `Find Possible Stable Binary Arrays II`

## Tags
`String` `Math` `Simulation`
