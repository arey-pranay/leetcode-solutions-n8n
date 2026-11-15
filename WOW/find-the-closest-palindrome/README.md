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
Generate candidate palindromes using mirroring, boundary powers of 10, and prefix increments/decrements, then pick the one with the minimum absolute difference.

## Intuition
The closest palindrome to any number will almost always be formed by taking the first half of the number and mirroring it to form a palindrome. However, edge cases exist near powers of 10 (e.g., 100 -> 99), where the closest palindrome changes length. By generating a finite set of smart candidate solutions—specifically the mirrored version, the mirrored version with the prefix incremented by 1, the mirrored version with the prefix decremented by 1, and the boundary numbers like $10^k + 1$ and $10^{k-1} - 1$—we guarantee that the true closest palindrome is included in our options.

## Algorithm
1. Parse the input string `n` into a numeric value and determine its length.
2. Generate the baseline candidate by mirroring the first half of the string onto the second half.
3. Generate edge-case candidates involving powers of 10, specifically $10^{\text{len}} + 1$ and $10^{\text{len-1}} - 1$.
4. Generate prefix-adjusted candidates by converting the first half into a number, incrementing and decrementing it by 1, and mirroring back.
5. Collect all candidate numbers into a list of options.
6. Iterate through all candidates, filtering out the original number itself.
7. Track the candidate that yields the minimum absolute difference from the original number, breaking ties by choosing the smaller numerical value.
8. Return the best candidate as a string.

## Concept to Remember
- **Candidate Generation Strategy:** For complex string-based mathematical constraints, boiling the search space down to a discrete, small set of deterministic candidates avoids exhaustive searching.
- **Boundary Edge Cases:** Numbers around powers of 10 frequently shift lengths when finding palindromes (e.g., 999 to 1001), requiring special boundary checks.
- **Tie-Breaking Rules:** When absolute differences are equal, the problem mandates picking the smaller numeric value.

## Common Mistakes
- Failing to handle edge cases where the closest palindrome changes digit length (like transitioning from 100 to 99).
- Forgetting to exclude the original number itself from being chosen as the closest palindrome.
- Mishandling odd versus even string lengths when splitting and mirroring the prefix.
- Incorrect tie-breaking logic when two candidates have the exact same absolute difference from the input.

## Complexity Analysis
- Time: $O(L)$ - where $L$ is the length of the string. Generating candidates and evaluating them takes proportional time to the number of digits since we check a fixed, small number of candidates.
- Space: $O(L)$ - to store the string builders and the small constant-sized list of generated candidates.

## Commented Code
```java
class Solution {
    public String nearestPalindromic(String n) {
        // Parse the length of the input string to determine digit constraints
        int len = n.length();
        int i = 0, j = len - 1;
        // Convert the input string into a long integer for numerical comparison
        long num = Long.parseLong(n);
        // List to hold all potential candidate palindromes
        List<Long> options = new ArrayList<>();
        // StringBuilder to manipulate the string for mirroring
        StringBuilder sb = new StringBuilder(n);
        // Mirror the first half of the string to the second half to form a base palindrome
        while (i < j) sb.setCharAt(j--, n.charAt(i++)); 
        long a = Long.parseLong(sb.toString());
        // Handle boundary palindromes like 1001 (10^len + 1) and 999 (10^(len-1) - 1)
        long b = (long)Math.pow(10, len) + 1, c = (long)Math.pow(10, len - 1) - 1; 
        // Generate candidates by incrementing and decrementing the prefix
        long d = incdec(n, -1), e = incdec(n, 1);
        // Add all generated candidates to our options list
        options.add(a); options.add(b); options.add(c); options.add(d); options.add(e);
        
        long ans = Long.MAX_VALUE, minDiff = Long.MAX_VALUE;
        // Iterate through all options to find the closest valid palindrome
        for (long option : options) {
            long diff = Math.abs(num - option);
            // Ensure we don't pick the number itself, minimize difference, and handle tie-breaks by picking the smaller value
            if (option != num && diff < minDiff || (diff == minDiff && option < ans)) {
                minDiff = diff;
                ans = option; 
            }
        }
        // Return the best candidate found as a string
        return String.valueOf(ans);
    }
    
    public long incdec(String s, int change) {
        // Extract the first half of the string (including the middle element if length is odd)
        StringBuilder half = new StringBuilder(s.substring(0, (s.length() + 1) / 2));
        long back = Long.parseLong(half.toString());
        // Apply the increment or decrement change (-1 or +1)
        back += change;
        half = new StringBuilder(String.valueOf(back));
        StringBuilder ans = new StringBuilder(half);
        // Determine the starting index for mirroring back, depending on even or odd length
        int start = s.length() % 2 == 0 ? half.length() - 1 : half.length() - 2;
        // Append characters in reverse to complete the palindrome
        for (int i = start; i >= 0; i--) ans.append(half.charAt(i));
        // Parse and return the newly formed palindrome candidate
        return Long.parseLong(ans.toString());
    }
}
```

## Interview Tips
- Clearly explain to the interviewer why a brute force check outward from `n` is inefficient, justifying the candidate-generation approach.
- Walk through a concrete edge case like `n = "100"` or `n = "99"` to demonstrate your grasp of boundary conditions.
- Explicitly mention the time and space complexity constraints, highlighting that $L$ is typically very small (e.g., length up to 18).

## Revision Checklist
- [ ] Understand why mirroring the first half works for most cases.
- [ ] Remember boundary candidates ($10^k+1$ and $10^{k-1}-1$).
- [ ] Know how to implement prefix increment and decrement correctly for both odd and even lengths.
- [ ] Handle the strict requirement of excluding the number itself.
- [ ] Implement tie-breaking logic favoring smaller numerical values.

## Similar Problems
- `Palindrome Number`
- `Find Maximum Number of Palindromic Subsequences`
- `Valid Palindrome`

## Tags
`String` `Math` `Simulation`
