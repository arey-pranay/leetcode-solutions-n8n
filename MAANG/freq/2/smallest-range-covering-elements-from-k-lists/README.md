# Smallest Range Covering Elements From K Lists

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Greedy` `Sliding Window` `Sorting` `Heap (Priority Queue)`  
**Time:** O(N log N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        // 4A 10A 15A 24A 26A 0B 9B 12B 20B 5C 18C 22C 30C
        // sort it 
        // 0B 4A 5C 9B 10A 12B 15A 18C 20B 22C 24A 26A 30C => 0,1 4,1 5,2 10,1
        // then find the smallest range that has 1 element at least with each group
        
       
        List<int[]> list = new ArrayList<>();
        int k = nums.size();
        for(int i=0;i<k;i++) for(int num : nums.get(i)) list.add(new int[]{num,i});
        Collections.sort(list, (a,b) -> a[0]-b[0]);
        
        int left = 0;
        int[] count = new int[k];
        int found = 0;
        int bestR = Integer.MAX_VALUE, bestL = 0;
        for(int right = 0;right < list.size(); right++){
            int group = list.get(right)[1];
            if(count[group] == 0) found++;
            count[group]++;
            
            while(found==k){
                int l = list.get(left)[0]; //left pointer ka index
                int r = list.get(right)[0]; 
                int range = r-l;
                if(range < bestR - bestL){
                    bestR = r;
                    bestL = l;
                }
                
                group = list.get(left)[1];
                count[group]--;
                if(count[group] == 0) found--;
                
                left++;
            }
        }
        return new int[]{bestL, bestR};
    }
}
// 2-4
// 6-8
```

---

---
## Quick Revision
The problem asks for the smallest range that includes at least one element from each of the k sorted lists.
This is solved using a sliding window approach on a merged and sorted list of all elements, keeping track of elements from each list.

## Intuition
Imagine merging all elements from all k lists into a single sorted list, but importantly, keeping track of which original list each element came from. We are looking for a "window" in this merged list such that it contains at least one element from *each* of the k original lists. To find the *smallest* such range, we can use a sliding window. We expand the window by moving the right pointer, and when the window is "valid" (contains elements from all k lists), we try to shrink it from the left to find the smallest possible valid range.

## Algorithm
1.  **Create a Merged List:** Create a list of pairs (or small arrays) where each pair stores an element's value and the index of the list it belongs to. Iterate through all k input lists and add all elements with their respective list indices to this merged list.
2.  **Sort the Merged List:** Sort the merged list based on the element values in ascending order.
3.  **Initialize Sliding Window Variables:**
    *   `left`: A pointer to the start of the current window in the sorted merged list. Initialize to 0.
    *   `count`: An array of size k, where `count[i]` stores the number of elements from list `i` currently within the window. Initialize all to 0.
    *   `found`: An integer representing the number of lists that currently have at least one element in the window. Initialize to 0.
    *   `bestL`, `bestR`: Variables to store the start and end of the smallest valid range found so far. Initialize `bestR` to a very large value (e.g., `Integer.MAX_VALUE`) and `bestL` to 0.
4.  **Slide the Window:** Iterate through the sorted merged list with a `right` pointer from 0 to the end of the list.
    *   For each element at `list.get(right)`:
        *   Get its original list index: `group = list.get(right)[1]`.
        *   If `count[group]` was 0 before incrementing, it means we've just added the first element from this list into the window, so increment `found`.
        *   Increment `count[group]`.
    *   **Check for Valid Window and Shrink:** While `found` is equal to `k` (meaning the current window covers all k lists):
        *   Get the current window's start value: `l = list.get(left)[0]`.
        *   Get the current window's end value: `r = list.get(right)[0]`.
        *   Calculate the current range: `range = r - l`.
        *   If this `range` is smaller than the current best range (`bestR - bestL`), update `bestL = l` and `bestR = r`.
        *   Now, try to shrink the window from the left to find a potentially smaller valid range.
        *   Get the list index of the element at the `left` pointer: `group = list.get(left)[1]`.
        *   Decrement `count[group]`.
        *   If `count[group]` becomes 0 after decrementing, it means we've removed the last element from this list from the window, so decrement `found`.
        *   Increment `left` to shrink the window.
5.  **Return Result:** After iterating through the entire merged list, `bestL` and `bestR` will hold the smallest range. Return `new int[]{bestL, bestR}`.

## Concept to Remember
*   **Sliding Window Technique:** Efficiently processing a contiguous sub-sequence of data by maintaining a window and moving its boundaries.
*   **Two Pointers:** Using two indices to traverse a data structure, often in conjunction with sorting or a sliding window.
*   **Greedy Approach:** Making locally optimal choices at each step with the hope of finding a global optimum. Here, we greedily try to shrink the window whenever it's valid.

## Common Mistakes
*   **Not handling duplicates correctly:** Ensuring that `count[group]` accurately reflects the number of elements from a list within the window, especially when multiple elements from the same list are present.
*   **Incorrectly updating `found`:** Forgetting to decrement `found` when the last element of a list is removed from the window, or incorrectly incrementing it.
*   **Off-by-one errors in window boundaries:** Miscalculating the range or mismanaging the `left` and `right` pointers.
*   **Not initializing `bestR` to a sufficiently large value:** This can lead to incorrect comparisons and failure to update the best range.

## Complexity Analysis
*   **Time:** O(N log N), where N is the total number of elements across all k lists.
    *   Creating the merged list takes O(N) time.
    *   Sorting the merged list takes O(N log N) time.
    *   The sliding window part iterates through the merged list once with the `right` pointer, and the `left` pointer also moves at most N times. Each operation inside the loop (incrementing/decrementing counts, checking `found`) is O(1). Thus, the sliding window part is O(N).
    *   The dominant factor is sorting.
*   **Space:** O(N) for storing the merged list of elements and their list indices. The `count` array takes O(k) space, which is usually less than N.

## Commented Code
```java
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        // Initialize a list to store all elements along with their original list index.
        // Each element in 'list' will be an int array: {value, list_index}.
        List<int[]> list = new ArrayList<>();
        // Get the number of input lists.
        int k = nums.size();
        // Iterate through each of the k input lists.
        for(int i = 0; i < k; i++) {
            // Iterate through each number in the current list.
            for(int num : nums.get(i)) {
                // Add the number and its list index to our merged list.
                list.add(new int[]{num, i});
            }
        }
        
        // Sort the merged list based on the element values in ascending order.
        // This is crucial for the sliding window approach.
        Collections.sort(list, (a, b) -> a[0] - b[0]);
        
        // Initialize the left pointer of the sliding window.
        int left = 0;
        // Initialize an array to keep track of how many elements from each list are currently in the window.
        // count[i] will store the count of elements from list 'i'.
        int[] count = new int[k];
        // Initialize 'found' to count how many lists have at least one element in the current window.
        int found = 0;
        // Initialize variables to store the start and end of the smallest range found so far.
        // bestR is initialized to a very large value to ensure the first valid range found becomes the best.
        int bestR = Integer.MAX_VALUE, bestL = 0;
        
        // Iterate through the sorted merged list with the right pointer of the sliding window.
        for(int right = 0; right < list.size(); right++) {
            // Get the original list index of the element at the current 'right' pointer.
            int group = list.get(right)[1];
            
            // If this is the first element from 'group' entering the window (count was 0), increment 'found'.
            if(count[group] == 0) {
                found++;
            }
            // Increment the count of elements from 'group' in the window.
            count[group]++;
            
            // While the current window is valid (contains at least one element from all k lists).
            while(found == k) {
                // Get the value of the element at the left pointer of the window.
                int l = list.get(left)[0];
                // Get the value of the element at the right pointer of the window.
                int r = list.get(right)[0];
                // Calculate the current range.
                int range = r - l;
                
                // If the current range is smaller than the best range found so far.
                if(range < bestR - bestL) {
                    // Update the best range.
                    bestR = r;
                    bestL = l;
                }
                
                // Now, try to shrink the window from the left to find a potentially smaller valid range.
                // Get the original list index of the element at the 'left' pointer.
                group = list.get(left)[1];
                // Decrement the count of elements from this list in the window.
                count[group]--;
                
                // If decrementing the count makes it zero, it means we've removed the last element from 'group' from the window.
                // So, decrement 'found' as this list is no longer represented in the window.
                if(count[group] == 0) {
                    found--;
                }
                
                // Move the left pointer one step to the right to shrink the window.
                left++;
            }
        }
        // Return the start and end of the smallest range found.
        return new int[]{bestL, bestR};
    }
}
```

## Interview Tips
*   **Explain the Merged List Idea:** Clearly articulate why merging and sorting all elements is a good starting point. Emphasize tracking the origin of each element.
*   **Walk Through the Sliding Window:** Verbally explain how the `left` and `right` pointers move, how `count` and `found` are updated, and why shrinking the window is important when it's valid.
*   **Edge Cases:** Discuss what happens if a list is empty, or if all lists have only one element. The current solution handles these gracefully.
*   **Alternative Approaches (Briefly):** You could briefly mention that a min-heap approach is also possible (similar to merging k sorted lists), where you maintain k pointers, one for each list, and use a min-heap to track the minimum element across all lists. This can sometimes be more intuitive for some interviewers.

## Revision Checklist
- [ ] Understand the problem: Smallest range covering elements from k sorted lists.
- [ ] Merge all elements with their list indices.
- [ ] Sort the merged list.
- [ ] Implement the sliding window with `left`, `right`, `count`, and `found`.
- [ ] Correctly update `count` and `found` when expanding and shrinking the window.
- [ ] Track and update the `bestL` and `bestR` for the smallest valid range.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases.

## Similar Problems
*   Merge k Sorted Lists
*   Find K Pairs with Smallest Sums
*   Sliding Window Maximum

## Tags
`Array` `Hash Map` `Sliding Window` `Greedy` `Heap`
