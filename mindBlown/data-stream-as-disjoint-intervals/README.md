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
The problem asks to maintain a collection of disjoint intervals from a stream of numbers.
We solve this by using a sorted set to store numbers and then iterating to form intervals.

## Intuition
The core idea is that if we have a stream of numbers, we want to group consecutive numbers into intervals. For example, if we see 1, 2, 3, 4, these should form the interval [1, 4]. If we then see 6, 7, this should form [6, 7]. The challenge is efficiently adding new numbers and merging them with existing intervals. A sorted data structure is key here because it allows us to easily check for adjacent numbers.

## Algorithm
1.  **Initialization**: Use a `TreeSet` to store all the numbers added so far. A `TreeSet` automatically keeps elements sorted and provides efficient ways to find neighbors.
2.  **`addNum(value)`**: Simply add the `value` to the `TreeSet`. The `TreeSet` handles duplicates and maintains sorted order.
3.  **`getIntervals()`**:
    *   If the `TreeSet` is empty, return an empty 2D array.
    *   Initialize an empty `List<int[]>` to store the resulting intervals.
    *   Initialize `start` and `end` variables.
    *   Iterate through the sorted numbers in the `TreeSet`.
    *   For the first number encountered, set both `start` and `end` to this number.
    *   For subsequent numbers:
        *   If the current number is exactly one greater than the current `end`, it means it extends the current interval, so increment `end`.
        *   If the current number is *not* one greater than `end`, it means the current interval has ended. Add the interval `[start, end]` to the list, and then reset `start` and `end` to the current number to begin a new potential interval.
    *   After the loop finishes, add the last formed interval `[start, end]` to the list.
    *   Convert the `List<int[]>` into a `int[][]` and return it.

## Concept to Remember
*   **Sorted Data Structures**: Using a `TreeSet` (or similar balanced BST) is crucial for efficient insertion and retrieval of ordered elements, enabling easy neighbor checks.
*   **Interval Merging**: The logic of extending or closing intervals based on consecutive numbers is a common pattern in interval-related problems.
*   **Stream Processing**: Handling data that arrives sequentially and needs to be processed incrementally.

## Common Mistakes
*   **Inefficient Data Structure**: Using a simple `ArrayList` and sorting it repeatedly in `getIntervals` would lead to poor time complexity.
*   **Off-by-One Errors**: Incorrectly handling the `end + 1` condition or forgetting to add the last interval after the loop.
*   **Handling Empty Input**: Not considering the edge case where `getIntervals` is called on an empty stream.
*   **Duplicate Numbers**: The `TreeSet` naturally handles duplicates, but if a different structure were used, duplicate handling would be a concern.

## Complexity Analysis
*   **Time**:
    *   `addNum(value)`: O(log N), where N is the number of elements in the `TreeSet`. This is due to the balanced BST nature of `TreeSet` for insertion.
    *   `getIntervals()`: O(N), where N is the number of elements in the `TreeSet`. We iterate through all elements once to form the intervals.
*   **Space**: O(N), where N is the number of elements in the `TreeSet`, to store all the numbers.

## Commented Code
```java
import java.util.TreeSet; // Import the TreeSet class for ordered set functionality.
import java.util.List; // Import the List interface.
import java.util.ArrayList; // Import the ArrayList class for dynamic list implementation.

class SummaryRanges {
    TreeSet<Integer> ts = new TreeSet<>(); // Initialize a TreeSet to store numbers in sorted order.

    public SummaryRanges() {
        // Constructor: The TreeSet is already initialized above.
        // No explicit initialization needed here if done as a class member.
    }
    
    public void addNum(int value) {
        ts.add(value); // Add the new number to the TreeSet. It will be automatically sorted.
    }
    
    public int[][] getIntervals() {
        if(ts.isEmpty()) return new int[0][0]; // If the TreeSet is empty, return an empty 2D array as there are no intervals.
        
        List<int[]> list = new ArrayList<>(); // Create a list to store the disjoint intervals found.
        int start = 0; // Variable to store the start of the current interval being formed.
        int end = 0;   // Variable to store the end of the current interval being formed.
        boolean isFirst = true; // Flag to track if we are processing the very first number from the TreeSet.
        
        for(int num : ts){ // Iterate through each number in the sorted TreeSet.
            if(isFirst){ // If this is the first number encountered.
                start = num; // Initialize the start of the interval with this number.
                end = num;   // Initialize the end of the interval with this number.
                isFirst = false; // Set the flag to false, as we've processed the first number.
            } 
            else if(num == end + 1) { // If the current number is exactly one greater than the current interval's end.
                end++; // Extend the current interval by incrementing its end.
            }
            else { // If the current number is not consecutive to the current interval's end.
                list.add(new int[]{start,end}); // The previous interval has ended, so add it to our list.
                start = num; // Start a new interval with the current number.
                end = num;   // The end of this new interval is also the current number initially.
            }
        }
        // After the loop, the last interval formed needs to be added to the list.
        list.add(new int[]{start,end}); // Add the final interval.
        
        // Convert the List<int[]> to a int[][] for the return type.
        int[][] ans = new int[list.size()][2]; // Create a 2D array of the correct size.
        int i=0; // Index for the 2D array.
        for(int[] temp : list) { // Iterate through the list of intervals.
            ans[i++] = temp; // Copy each interval from the list to the 2D array.
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
*   **Explain `TreeSet` Choice**: Clearly articulate why `TreeSet` is a good choice due to its sorted nature and O(log N) insertion.
*   **Walk Through `getIntervals`**: Verbally trace the `getIntervals` logic with a small example (e.g., adding 1, 3, 7, 2, 6) to demonstrate understanding of interval formation.
*   **Edge Cases**: Be prepared to discuss handling an empty stream (`getIntervals` on an empty object) and single-element intervals.
*   **Alternative Approaches**: Briefly mention that other approaches exist (e.g., using a `TreeMap` to store intervals directly), but explain why the `TreeSet` approach is often simpler to implement correctly for this specific problem.

## Revision Checklist
- [ ] Understand the problem: maintain disjoint intervals from a data stream.
- [ ] Choose an appropriate data structure: `TreeSet` for sorted order and efficient insertion.
- [ ] Implement `addNum`: simple insertion into `TreeSet`.
- [ ] Implement `getIntervals`: iterate through `TreeSet`, identify consecutive numbers, and form intervals.
- [ ] Handle the first element correctly.
- [ ] Handle extending intervals (`num == end + 1`).
- [ ] Handle starting new intervals.
- [ ] Ensure the last interval is added.
- [ ] Convert `List<int[]>` to `int[][]`.
- [ ] Consider edge cases: empty stream.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Merge Intervals
*   Insert Interval
*   Employee Free Time
*   My Calendar I/II/III

## Tags
`TreeSet` `Sorted Set` `Data Stream` `Intervals`
