# Stamping The Sequence

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Stack` `Greedy` `Queue`  
**Time:** O(N \cdot (N - M)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int[] movesToStamp(String stamp, String target) {
        char[] SC = stamp.toCharArray(), TC = target.toCharArray();
        int slen = SC.length, tlen = TC.length;
        List<Integer> ans = new ArrayList<>();
        
        boolean flag = true;
        int i=0,j=0,done=0;
        while(flag){
            flag = false;
            for(i=0;i<=tlen-slen; i++){
                for(j=0;j<slen;j++) if(TC[i+j] != '-' && TC[i+j] != SC[j]) break;
                if(j==slen){
                    boolean changed = false;
                    for(j=i; j<i+slen;j++) {
                        if(TC[j]!='-'){
                            TC[j] = '-';
                            done++;
                            changed = true;
                        }
                    }
                    if(changed){
                        ans.add(i);
                        flag = true;
                    }
                }
            }
        }
        
        if(done != tlen) return new int[]{};
        
        int sz = ans.size();
        int[] ret = new int[sz];
        for(i=sz-1;i>=0;i--) ret[sz-i-1] = ans.get(i);
        return ret;
    }
}
// Input: stamp = "abca", target = "aabcaca"

// 'a' 'a b c a' 'c a'
// a b c a
//         a b c a
//     a b c a


// stamp = 'abc'
//    abababcbcbababcbc 
// a b a b a b c b c b a b a b c b c
//         a b c           a b c
//     a b c   a b c   a b c   a b c
// a b c           a b c   

// 0 8 2 6 10 14 4 12
```

---

---
## Quick Revision
Given a stamp and a target string, find the sequence of stamp indices to form the target starting from all wildcards.
Solve this problem in reverse by greedily finding windows in the target that match the stamp (allowing already stamped wildcards) and replacing them with wildcards until the entire target becomes wildcards, then reversing the operations list.

## Intuition
Moving forward from an all-wildcard string to the target string is extremely difficult because there are too many branching choices for which stamp to apply and where. The "aha moment" is to run the process in reverse. We start with the fully constructed target string and look for any position where the stamp could have been applied last—meaning the current target characters in that window either match the stamp characters or are already wildcards (represented by `-`). Once found, we "unstamp" that window by turning its characters into wildcards and record the index. If we can reduce the entire target to wildcards, we reverse our recorded steps to get the valid forward stamping sequence.

## Algorithm
1. Convert both `stamp` and `target` into character arrays for easy in-place mutation, and initialize a tracking list for the answer indices and a counter for resolved characters.
2. Enter a loop that continues as long as we successfully unstamp at least one window in the current iteration.
3. Iterate through all possible starting positions in the target string from left to right where the stamp can fit.
4. For each starting position, check if the window matches the stamp: every character must either equal the corresponding stamp character or already be a wildcard (`-`).
5. If a valid window match is found and has not been completely blanked out yet, replace all non-wildcard characters in that window with wildcards, increment our resolved character count, and record the starting index.
6. If the entire target has been converted to wildcards, stop the loop; otherwise, if a full iteration passes without any stamps being removed, break out.
7. If the resolved character count equals the target length, reverse the recorded list of indices and return them as an array; otherwise, return an empty array indicating it's impossible.

## Concept to Remember
- Reverse Thinking / Backward Simulation: Reducing problem complexity by working backwards from the goal state to the initial state.
- Greedy Approach: Making the locally optimal choice (unstamping any currently matching window) to achieve the global solution.
- Wildcard Matching: Using placeholders (`-`) to represent previously processed states that can match any character.

## Common Mistakes
- Trying to solve the problem in the forward direction, which leads to massive state spaces and intractable branching.
- Failing to check if a matched window has already been completely converted to wildcards, leading to infinite loops.
- Forgetting to reverse the collected indices at the end, since the reverse simulation builds the solution from last step to first step.
- Returning an incorrect result when the target cannot be fully stamped (not checking if `done == target.length()`).

## Complexity Analysis
- Time: $O(N \cdot (N - M))$ where $N$ is the length of the target and $M$ is the length of the stamp. In the worst case, we scan the target $O(N)$ times, and each scan takes $O(N \cdot M)$ checks.
- Space: $O(N)$ to store the character arrays, answer list, and the resulting integer array.

## Commented Code
```java
class Solution {
    public int[] movesToStamp(String stamp, String target) {
        // Convert the stamp string to a character array for quick character lookups.
        char[] SC = stamp.toCharArray();
        // Convert the target string to a mutable character array to apply wildcards.
        char[] TC = target.toCharArray();
        // Store the length of the stamp.
        int slen = SC.length;
        // Store the length of the target.
        int tlen = TC.length;
        // List to record the indices where the stamp was applied in reverse order.
        List<Integer> ans = new ArrayList<>();
        
        // Flag to keep track of whether any progress (unstamping) was made in the current pass.
        boolean flag = true;
        // Declare loop variables for iteration and tracking resolved characters.
        int i = 0, j = 0, done = 0;
        // Continue looping as long as we can successfully unstamp at least one window per pass.
        while (flag) {
            // Assume no matches will be found in this pass until proven otherwise.
            flag = false;
            // Iterate through all possible starting indices where the stamp fits within the target.
            for (i = 0; i <= tlen - slen; i++) {
                // Check if the current target window matches the stamp (ignoring already processed wildcards '-').
                for (j = 0; j < slen; j++) {
                    if (TC[i + j] != '-' && TC[i + j] != SC[j]) break;
                }
                // If the entire window matches the stamp criteria...
                if (j == slen) {
                    // Flag to ensure we don't count a window that is already entirely wildcards.
                    boolean changed = false;
                    // Iterate through the matching window to convert characters to wildcards.
                    for (j = i; j < i + slen; j++) {
                        // If the character is not already a wildcard...
                        if (TC[j] != '-') {
                            // Convert it to a wildcard.
                            TC[j] = '-';
                            // Increment the count of successfully resolved characters.
                            done++;
                            // Mark that a change was made in this window.
                            changed = true;
                        }
                    }
                    // If the window actually changed the target state...
                    if (changed) {
                        // Record the starting index of this stamp application.
                        ans.add(i);
                        // Set flag to true so the outer loop continues searching for more matches.
                        flag = true;
                    }
                }
            }
        }
        
        // If we haven't successfully converted every character to a wildcard, it's impossible.
        if (done != tlen) return new int[]{};
        
        // Since we worked backwards, the operations are in reverse order. Prepare to reverse them.
        int sz = ans.size();
        int[] ret = new int[sz];
        // Populate the result array by reading the answer list from back to front.
        for (i = sz - 1; i >= 0; i--) {
            ret[sz - i - 1] = ans.get(i);
        }
        // Return the final forward sequence of stamp indices.
        return ret;
    }
}
```

## Interview Tips
- State clearly why the forward approach is difficult and why working backwards is a cleaner, more deterministic strategy.
- Discuss the time complexity upfront and explain why the worst-case bound is acceptable given standard constraints.
- Mention how wildcard characters (`-`) simplify state tracking without needing extra memory matrices.
- Be prepared to walk through a small example (like `stamp = "abc"`, `target = "ababc"`) to demonstrate the backward simulation.

## Revision Checklist
- [ ] Understand why reverse simulation simplifies the problem.
- [ ] Know how to check window matching with wildcard characters.
- [ ] Remember to reverse the resulting index list at the end.
- [ ] Validate that all characters are successfully transformed.

## Similar Problems
- Burst Balloons
- Minimum Number of Increments on Subarrays to Form a Target Array
- Broken Calculator

## Tags
`Array` `String` `Greedy` `Stack` `Simulation`
