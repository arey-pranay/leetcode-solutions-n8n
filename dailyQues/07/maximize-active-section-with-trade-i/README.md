# Maximize Active Section With Trade I

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `String` `HashMap` `Greedy`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int curr0 =0;
        int start =-1;
        boolean streak1 = false;
        HashMap<Integer,Integer> hm = new HashMap<>();
        int ones = 0;
        int max=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='1'){
                ones++;
                if(!streak1){ 
                    if(start!=-1){
                        int before = hm.get(start);
                        hm.put(start,before+curr0);
                        if(before!=0) max = Math.max(max,hm.get(start));
                    }
                    hm.put(i,curr0);
                    start = i;
                    streak1 = true;
                    curr0=0;
                }
            }
            else {
                curr0++;
                streak1=false;
            }
        } 
        if(start!=-1 && !streak1){
            int before = hm.get(start);
            hm.put(start,before+curr0);
            if(before!=0) max = Math.max(max,hm.get(start));
        }
        return ones+max;
    }
}
```

---

---
## Quick Revision
Given a binary string, find the maximum number of '1's we can have after flipping at most one '0' to a '1'.
This is solved by finding the longest contiguous subarray of '1's that can be formed by flipping a single '0'.

## Intuition
The core idea is that flipping a '0' can potentially merge two adjacent blocks of '1's, or extend a single block of '1's. We want to find the '0' that, when flipped, results in the longest continuous sequence of '1's. This means we should look for a '0' that is surrounded by '1's, or is at the beginning/end of the string adjacent to '1's.

The provided solution uses a HashMap to store the count of consecutive '0's *before* a block of '1's. When a '1' is encountered, it signifies the potential start of a new block of '1's. If a '1' follows a '0', we can potentially merge the '1's before and after that '0'. The HashMap helps track the length of the '0' sequence preceding the current '1' block.

## Algorithm
1. Initialize `ones` to count the total number of '1's in the string.
2. Initialize `curr0` to count consecutive '0's.
3. Initialize `start` to -1, marking the starting index of a '1' streak.
4. Initialize `streak1` to false, indicating if we are currently in a streak of '1's.
5. Initialize a HashMap `hm` to store `start_index_of_1s_streak -> count_of_0s_before_it`.
6. Initialize `max` to 0, to store the maximum number of '1's we can achieve by flipping one '0'.
7. Iterate through the string `s` from left to right (index `i`):
    a. If `s.charAt(i)` is '1':
        i. Increment `ones`.
        ii. If `streak1` is false (meaning we just entered a '1' streak or are continuing after '0's):
            - If `start` is not -1 (meaning there was a previous '1' streak):
                - Get the count of '0's before the previous streak from `hm.get(start)`.
                - Update the entry in `hm` for `start` by adding `curr0` (the current consecutive '0's) to the previous count.
                - If the previous streak's count was not 0 (meaning there were '1's before the '0's we just counted), update `max` with the maximum of `max` and the new combined count (`hm.get(start)`).
            - Set `start` to the current index `i` (start of the new '1' streak).
            - Put the current `curr0` count into `hm` with `start` as the key.
            - Reset `curr0` to 0.
            - Set `streak1` to true.
    b. If `s.charAt(i)` is '0':
        i. Increment `curr0`.
        ii. Set `streak1` to false.
8. After the loop, if `start` is not -1 and `streak1` is false (meaning the string ended with '0's after a '1' streak):
    a. Get the count of '0's before the last streak from `hm.get(start)`.
    b. Update the entry in `hm` for `start` by adding `curr0` (the trailing '0's) to the previous count.
    c. If the previous streak's count was not 0, update `max` with the maximum of `max` and the new combined count (`hm.get(start)`).
9. Return `ones + max`.

## Concept to Remember
*   **Greedy Approach:** Making the locally optimal choice (flipping the '0' that maximizes the immediate gain) leads to the globally optimal solution.
*   **Prefix/Suffix Sums (Implicit):** The `curr0` variable acts like a running count of zeros, and the HashMap stores information about preceding zero counts, similar to how prefix sums are used to calculate sums of subarrays.
*   **HashMap for State Tracking:** Using a HashMap to store counts associated with specific starting points of '1' streaks allows efficient retrieval and update of information about preceding '0's.

## Common Mistakes
*   **Off-by-one errors:** Incorrectly handling the start and end of streaks of '0's and '1's.
*   **Not handling edge cases:** Forgetting to consider strings that start or end with '0's or '1's, or strings with only '0's or only '1's.
*   **Incorrectly merging streaks:** Failing to properly combine the counts of '1's before and after a flipped '0'.
*   **Mismanaging the HashMap:** Not correctly updating or retrieving values from the HashMap, especially when dealing with consecutive '0's or multiple '1' streaks.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the string once. HashMap operations (put, get, containsKey) are O(1) on average.
- Space: O(N) - reason: In the worst case, the HashMap could store an entry for each '1' streak, and there could be up to N/2 streaks.

## Commented Code
```java
class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        // curr0: counts consecutive '0's encountered since the last '1' streak.
        int curr0 = 0;
        // start: stores the starting index of the current streak of '1's. Initialized to -1.
        int start = -1;
        // streak1: a boolean flag to indicate if we are currently inside a streak of '1's.
        boolean streak1 = false;
        // hm: a HashMap to store the count of consecutive '0's *before* a streak of '1's.
        // Key: the starting index of a '1' streak. Value: the count of '0's preceding that streak.
        HashMap<Integer, Integer> hm = new HashMap<>();
        // ones: counts the total number of '1's in the original string.
        int ones = 0;
        // max: stores the maximum number of additional '1's we can get by flipping one '0'.
        // This represents the longest possible merged streak of '1's.
        int max = 0;

        // Iterate through each character of the input string s.
        for (int i = 0; i < s.length(); i++) {
            // Check if the current character is '1'.
            if (s.charAt(i) == '1') {
                // If it's a '1', increment the total count of '1's.
                ones++;
                // If we were not previously in a streak of '1's (meaning we just encountered a '1' after '0's or at the beginning).
                if (!streak1) {
                    // If 'start' is not -1, it means there was a previous streak of '1's.
                    if (start != -1) {
                        // Get the count of '0's that were before the *previous* '1' streak.
                        int before = hm.get(start);
                        // Update the HashMap for the *previous* '1' streak.
                        // We add the current 'curr0' (zeros encountered since the last '1' streak)
                        // to the 'before' count. This effectively merges the '0's between two '1' streaks.
                        hm.put(start, before + curr0);
                        // If 'before' was not 0, it means there were '1's before the '0's we just counted.
                        // So, flipping a '0' between these two streaks could potentially create a longer sequence.
                        // We update 'max' to keep track of the longest such merged sequence.
                        if (before != 0) {
                            max = Math.max(max, hm.get(start));
                        }
                    }
                    // Set 'start' to the current index 'i', as this is the beginning of a new '1' streak.
                    start = i;
                    // Store the current count of '0's ('curr0') in the HashMap, associated with this new '1' streak's start index.
                    // This 'curr0' represents the zeros *before* this new streak of '1's.
                    hm.put(i, curr0);
                    // Reset 'curr0' to 0, as we are now in a '1' streak.
                    curr0 = 0;
                    // Set 'streak1' to true, indicating we are now inside a '1' streak.
                    streak1 = true;
                }
            } else { // If the current character is '0'.
                // Increment the count of consecutive '0's.
                curr0++;
                // Set 'streak1' to false, as we are no longer in a '1' streak.
                streak1 = false;
            }
        }

        // After the loop, we need to handle the case where the string ends with '0's after a '1' streak.
        // If 'start' is not -1 (meaning there was at least one '1' streak) AND we are not currently in a '1' streak (meaning the string ended with '0's).
        if (start != -1 && !streak1) {
            // Get the count of '0's that were before the *last* '1' streak.
            int before = hm.get(start);
            // Update the HashMap for the *last* '1' streak.
            // Add the trailing 'curr0' (zeros at the end of the string) to the 'before' count.
            hm.put(start, before + curr0);
            // If 'before' was not 0, it means there were '1's before the trailing '0's.
            // Flipping one of these trailing '0's could extend the last '1' streak.
            // Update 'max' if this extension is greater than the current max.
            if (before != 0) {
                max = Math.max(max, hm.get(start));
            }
        }

        // The final result is the total number of original '1's plus the maximum number of additional '1's
        // we can achieve by flipping one '0' (which is stored in 'max').
        return ones + max;
    }
}
```

## Interview Tips
*   **Clarify the problem:** Ensure you understand if you can flip *at most* one '0' or *exactly* one '0'. The problem statement implies "at most".
*   **Walk through examples:** Use simple cases like "11011", "01110", "111", "000" to trace your logic and the HashMap's behavior.
*   **Explain the HashMap's purpose:** Clearly articulate why the HashMap is used to store the count of zeros *before* a '1' streak and how it helps in merging streaks.
*   **Discuss edge cases:** Be prepared to talk about how your solution handles strings with all '0's, all '1's, or strings starting/ending with '0's or '1's.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the core idea: merging '1' streaks by flipping a '0'.
- [ ] Trace the algorithm with examples.
- [ ] Understand the role of `curr0`, `start`, `streak1`, and `hm`.
- [ ] Verify edge case handling (start/end of string, all '0's/all '1's).
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution and its trade-offs.

## Similar Problems
*   Max Consecutive Ones III
*   Longest Substring Without Repeating Characters
*   Binary Subarrays With Sum

## Tags
`String` `HashMap` `Greedy`
