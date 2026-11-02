# Stamping The Sequence

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Stack` `Greedy` `Queue`  
**Time:** O(N^2 \cdot M)  
**Space:** O(N + M)

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
Given a stamp and a target string, find the sequence of stamp indices that can transform a string of '?' into the target string.
Solve it in reverse: iteratively find windows in the target that match the stamp (ignoring already stamped wildcards) and replace them with wildcards until the whole target is wildcarded, then reverse the order.

## Intuition
Working forward from a string of wildcards to the target is extremely difficult because a single stamp application can be overwritten multiple times, leading to massive branching. However, if we think in reverse, it becomes much easier. We start with the fully constructed target string and look for any position where the stamp could have been applied *last*. A valid position to stamp in reverse is one where every character in the target window either matches the corresponding character in the stamp or is already a wildcard (`-`). Once we find such a window, we "un-stamp" it by turning those characters into wildcards. Repeating this process until all characters become wildcards yields the correct sequence of stamps in reverse order.

## Algorithm
1. Convert both the `stamp` and `target` strings into character arrays (`SC` and `TC`) for in-place mutation.
2. Initialize an empty list `ans` to store the starting indices of the reverse stamp operations, and a counter `done` to track how many characters have been successfully replaced by wildcards.
3. Run a `while` loop that continues as long as we successfully make progress (use a boolean `flag` initialized to `true`).
4. Inside the loop, set `flag = false` and iterate through all possible starting indices `i` in the target from `0` to `tlen - slen`.
5. For each index `i`, check if the window of length `slen` matches the stamp: every character must either match `SC[j]` or already be a wildcard (`-`).
6. If a valid window is found and it contains at least one non-wildcard character, replace all characters in that window in `TC` with `-`, increment `done` by the number of newly wildcarded characters, add index `i` to `ans`, and set `flag = true` to indicate progress was made.
7. After the `while` loop finishes, check if `done` equals the total length of the target. If not, transformation is impossible, so return an empty array.
8. Since we found the stamp sequence in reverse, reverse the `ans` list, convert it to an integer array, and return it.

## Concept to Remember
- **Reverse Thinking (Backward Greedy):** Transforming forward is intractable, but working backward from goal to state 0 drastically simplifies constraints.
- **Wildcard / State Masking:** Using a special character (`-`) to abstract away already-processed portions of the input.
- **Topological Sorting / Dependency Resolution:** Finding independent actions that can be undone layer by layer.

## Common Mistakes
- Trying to simulate the problem forward instead of working backward from the target.
- Forgetting to check if a matched window actually changes anything (i.e., making sure it contains at least one non-wildcard character to prevent infinite loops).
- Failing to reverse the final answer list before returning, since the algorithm constructs the sequence from the last stamp to the first.
- Not verifying at the end whether every single character in the target was successfully converted to a wildcard (`done == tlen`).

## Complexity Analysis
- Time: $O(N^2 \cdot M)$ - In the worst case, we scan up to $N - M$ windows, and for each window, we compare up to $M$ characters, where $N$ is the target length and $M$ is the stamp length. The outer loop runs at most $N$ times.
- Space: $O(N + M)$ - Required for storing the character arrays of the target and stamp, as well as the answer list and output array.

## Commented Code
```java
class Solution {
    public int[] movesToStamp(String stamp, String target) {
        // Convert stamp and target to char arrays for efficient in-place modification
        char[] SC = stamp.toCharArray(), TC = target.toCharArray();
        int slen = SC.length, tlen = TC.length;
        // List to store the indices of the stamp operations in reverse order
        List<Integer> ans = new ArrayList<>();
        
        boolean flag = true; // Tracks if progress was made in the current iteration
        int i = 0, j = 0, done = 0; // done counts how many characters have been converted to '-'
        
        // Continue looping as long as we can successfully un-stamp at least one window
        while(flag){
            flag = false; // Assume no progress until proven otherwise
            
            // Iterate through all possible starting positions for the stamp in the target
            for(i = 0; i <= tlen - slen; i++){
                // Check if the current window matches the stamp (ignoring already wildcarded '-' characters)
                for(j = 0; j < slen; j++) {
                    if(TC[i+j] != '-' && TC[i+j] != SC[j]) break;
                }
                
                // If the entire window matches the stamp rules
                if(j == slen){
                    boolean changed = false;
                    
                    // Replace the window characters with '-' and count newly changed characters
                    for(j = i; j < i + slen; j++) {
                        if(TC[j] != '-'){
                            TC[j] = '-'; // Mark as wildcard
                            done++;      // Increment total converted count
                            changed = true; // We made a change in this window
                        }
                    }
                    
                    // If this window actually changed any non-wildcard characters
                    if(changed){
                        ans.add(i);       // Record the stamp start index
                        flag = true;  // Mark that progress was made, keep the outer loop going
                    }
                }
            }
        }
        
        // If we couldn't convert the entire target to wildcards, it's impossible
        if(done != tlen) return new int[]{};
        
        // The operations were found in reverse order, so we must reverse the list
        int sz = ans.size();
        int[] ret = new int[sz];
        for(i = sz - 1; i >= 0; i--) {
            ret[sz - i - 1] = ans.get(i);
        }
        
        // Return the correct chronological sequence of stamp indices
        return ret;
    }
}
```

## Interview Tips
- State clearly why a forward simulation fails (too many branching choices) and justify why a backward greedy approach is optimal.
- Discuss the time complexity upfront and explain why the nested loops don't TLE given the problem constraints ($N \le 1000$).
- Walk through a small dry-run example (like `stamp = "abc"`, `target = "ababc"`) to demonstrate how wildcards allow overlapping matches.

## Revision Checklist
- [ ] Understand why reverse simulation is necessary.
- [ ] Implement wildcard matching logic correctly (`TC[i+j] != '-' && TC[i+j] != SC[j]`).
- [ ] Ensure `changed` check prevents infinite loops on already-processed windows.
- [ ] Remember to reverse the resulting index list at the end.
- [ ] Verify that `done == tlen` to catch impossible cases.

## Similar Problems
- `Minimum Number of Increments on Subarrays to Form a Target Array`
- `Burst Balloons`
- `Remove Boxes`

## Tags
`Array` `String` `Greedy` `Stack` `Queue`
