# Data Stream As Disjoint Intervals

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `Binary Search` `Union-Find` `Design` `Data Stream` `Ordered Set`  
**Time:** See complexity section  
**Space:** O(N)

---

## Solution (java)

```java
class SummaryRanges {
    TreeSet<Integer> ts = new TreeSet<>();
    public SummaryRanges() {
        ts = new TreeSet<>();
    }
    
    public void addNum(int value) {
        ts.add(value);
    }
    
    public int[][] getIntervals() {
        if(ts.isEmpty()) return new int[0][0];
        List<int[]> list = new ArrayList<>();
        int start = 0;
        int end = 0;
        boolean isFirst = true;
        for(int num : ts){
            if(isFirst){
                start = end = num;
                isFirst = false;
            } 
            else if(num==end+1) end++;
            else {
                list.add(new int[]{start,end});
                start = end = num;
            }
        }
        list.add(new int[]{start,end});
        int[][] ans = new int[list.size()][2];
        int i=0;
        for(int[] temp : list)ans[i++] = temp;
        return ans;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */
```

---

---
## Quick Revision
This problem asks to maintain a collection of disjoint intervals from a stream of numbers.
We solve it by using a sorted set to store numbers and then iterating to form intervals.

## Intuition
The core idea is that if we have a stream of numbers, we want to group consecutive numbers into intervals. For example, if we see 1, 2, 3, 4, these should form the interval [1, 4]. If we then see 7, 8, these form [7, 8]. The key is to efficiently check for consecutiveness and merge intervals. A sorted data structure is ideal for this. A `TreeSet` in Java keeps elements sorted, allowing us to easily find neighbors of a newly added number. When `getIntervals` is called, we can iterate through the sorted numbers and build the disjoint intervals.

## Algorithm
1.  **Initialization**: Create a `TreeSet` to store all the numbers added so far.
2.  **`addNum(value)`**: Simply add the `value` to the `TreeSet`. The `TreeSet` automatically handles duplicates and keeps the numbers sorted.
3.  **`getIntervals()`**:
    a.  If the `TreeSet` is empty, return an empty 2D array.
    b.  Initialize an empty `List` to store the resulting intervals (each interval as an `int[]` of size 2).
    c.  Initialize `start` and `end` variables.
    d.  Iterate through each `num` in the sorted `TreeSet`.
    e.  If it's the first number encountered, set both `start` and `end` to this `num`.
    f.  If the current `num` is exactly one greater than the current `end` (`num == end + 1`), it means it extends the current interval, so increment `end`.
    g.  If the current `num` is not consecutive to `end` (i.e., `num > end + 1`), it signifies the end of the previous interval and the start of a new one. Add the previous interval `[start, end]` to the list, and then reset `start` and `end` to the current `num`.
    h.  After the loop finishes, there will always be one pending interval (`[start, end]`) that needs to be added to the list. Add this final interval.
    i.  Convert the `List<int[]>` into a `int[][]` and return it.

## Concept to Remember
*   **Sorted Data Structures**: Using structures like `TreeSet` or balanced binary search trees to maintain ordered elements is crucial for efficient neighbor finding and interval merging.
*   **Interval Merging**: The logic of identifying consecutive numbers and extending or starting new intervals is a common pattern in interval-related problems.
*   **Stream Processing**: Handling data that arrives sequentially and requires maintaining a state.

## Common Mistakes
*   **Inefficient Data Structure**: Using an unsorted list or array and sorting it repeatedly in `getIntervals` would lead to poor time complexity.
*   **Off-by-One Errors**: Incorrectly handling the `end + 1` condition or forgetting to add the last interval after the loop.
*   **Handling Empty Stream**: Not considering the edge case where `getIntervals` is called on an empty `TreeSet`.
*   **Duplicate Numbers**: Not realizing that `TreeSet` automatically handles duplicates, which simplifies the logic.

## Complexity Analysis
*   **Time**:
    *   `addNum(value)`: O(log N), where N is the number of elements in the `TreeSet`. This is due to the balanced binary search tree nature of `TreeSet`.
    *   `getIntervals()`: O(N), where N is the number of elements in the `TreeSet`. We iterate through all elements once to form the intervals.
*   **Space**: O(N), where N is the number of elements stored in the `TreeSet`.

## Commented Code
```java
import java.util.TreeSet; // Import the TreeSet class for ordered storage
import java.util.List; // Import List interface
import java.util.ArrayList; // Import ArrayList implementation for dynamic list

class SummaryRanges {
    TreeSet<Integer> ts; // Declare a TreeSet to store all numbers added, automatically sorted and unique

    public SummaryRanges() {
        ts = new TreeSet<>(); // Initialize the TreeSet when the object is created
    }
    
    public void addNum(int value) {
        ts.add(value); // Add the new number to the TreeSet. Duplicates are ignored, and numbers are kept sorted.
    }
    
    public int[][] getIntervals() {
        if(ts.isEmpty()) return new int[0][0]; // If the TreeSet is empty, return an empty 2D array as there are no intervals.
        
        List<int[]> list = new ArrayList<>(); // Create a list to hold the disjoint intervals (each interval is an int array of size 2).
        
        int start = 0; // Variable to store the start of the current interval being built.
        int end = 0; // Variable to store the end of the current interval being built.
        boolean isFirst = true; // Flag to track if we are processing the very first number from the TreeSet.
        
        for(int num : ts){ // Iterate through each number in the sorted TreeSet.
            if(isFirst){ // If this is the first number encountered in the stream.
                start = num; // Initialize the start of the first interval.
                end = num; // Initialize the end of the first interval.
                isFirst = false; // Set the flag to false, as we've processed the first number.
            } 
            else if(num == end + 1) { // If the current number is exactly one greater than the current interval's end.
                end++; // Extend the current interval by incrementing its end.
            }
            else { // If the current number is not consecutive to the current interval's end (i.e., num > end + 1).
                list.add(new int[]{start,end}); // The previous interval [start, end] is complete, so add it to our list.
                start = num; // Start a new interval with the current number.
                end = num; // The end of this new interval is also the current number.
            }
        }
        // After the loop, the last interval [start, end] needs to be added to the list.
        list.add(new int[]{start,end});
        
        // Convert the List of int arrays into a 2D int array for the return type.
        int[][] ans = new int[list.size()][2]; // Create the result array with the correct dimensions.
        int i=0; // Index for the result array.
        for(int[] temp : list) { // Iterate through the list of intervals.
            ans[i++] = temp; // Copy each interval from the list to the result array.
        }
        return ans; // Return the 2D array of disjoint intervals.
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */
```

## Interview Tips
*   **Clarify `getIntervals` Frequency**: Ask how often `getIntervals` is expected to be called relative to `addNum`. If `getIntervals` is called very frequently, optimizing its performance might be more critical. If `addNum` is more frequent, the `addNum` complexity is key.
*   **Explain `TreeSet` Choice**: Justify why `TreeSet` is a good choice (sorted, efficient add/search, handles duplicates). Mention alternatives like a balanced BST or even a sorted list with careful merging, but highlight `TreeSet`'s convenience.
*   **Walk Through `getIntervals` Logic**: Clearly explain the `start`, `end`, and `isFirst` logic. Use a small example (e.g., adding 1, 3, 2, 7, 8) to trace how the intervals are formed.
*   **Edge Cases**: Be prepared to discuss the empty stream case and how the code handles it.

## Revision Checklist
- [ ] Understand the problem: maintain disjoint intervals from a stream.
- [ ] Choose an appropriate data structure: `TreeSet` for sorted order.
- [ ] Implement `addNum`: simple insertion into `TreeSet`.
- [ ] Implement `getIntervals`: iterate through `TreeSet` to form intervals.
- [ ] Handle consecutive numbers correctly (`end + 1`).
- [ ] Handle non-consecutive numbers correctly (start new interval).
- [ ] Remember to add the last interval.
- [ ] Handle the empty stream edge case.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Merge Intervals
*   Insert Interval
*   Employee Free Time
*   Non-overlapping Intervals

## Tags
`TreeSet` `SortedSet` `Interval` `Data Stream`
