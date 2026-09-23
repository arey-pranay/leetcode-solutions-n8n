# Data Stream As Disjoint Intervals

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `Binary Search` `Union-Find` `Design` `Data Stream` `Ordered Set`  
**Time:** O(log N)  
**Space:** O(N)

---

## Solution (java)

```java
class SummaryRanges {
    List<int[]> intervals;
    public SummaryRanges() {
        intervals = new ArrayList<>();
    }
    public void addNum(int target) {
        if(intervals.isEmpty()){intervals.add(new int[]{target,target}); return;}
        int low =0, high=intervals.size()-1;
        int mid = low + (high-low)/2;
        while(low <= high){
            mid = low + (high-low)/2;
            int start = intervals.get(mid)[0], end = intervals.get(mid)[1];
            if(target < start) high = mid-1;
            else if(target > end) low = mid+1;
            else return;
        }
        
        int left = low-1, right = low;
        
        boolean mergeLeft = left >= 0 && intervals.get(left)[1] + 1 == target;
        boolean mergeRight = right <= intervals.size()-1 && intervals.get(right)[0] - 1 == target;
        
        if(mergeLeft && mergeRight){
            intervals.get(left)[1] = intervals.get(right)[1];
            intervals.remove(right);
        } 
        else if (mergeLeft) intervals.get(left)[1] = target;
        else if (mergeRight) intervals.get(right)[0] = target;
        else intervals.add(low, new int[]{target,target});
        
        // <1,2,3,4,5,6>
        
        // 12 on 4th position
        
    }
    
    public int[][] getIntervals() {
        return intervals.toArray(new int[0][]);
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */
         //    addNum(312) => 312 313 314 315
        //   [[312,315]]
           // 9 8 5 
        // [9],[8],[5]
    //    getIntervals()
    //   [ 
    //     [1,1]
    //     [3,3]
    //     [4,10]
    //     [100,101]
    //     [200,250]
    // ->
    //     [500,501] low mid high
    //     [700,901] high
    //    ]
```

---

---
## Quick Revision
This problem asks to maintain a collection of disjoint intervals from a stream of numbers. We need to efficiently add numbers and retrieve the current set of disjoint intervals.

The solution uses a sorted list of intervals and binary search to find the correct insertion/merge point for new numbers.

## Intuition
The core idea is that if we keep the intervals sorted by their start times, we can efficiently determine where a new number `target` fits. If `target` is already within an existing interval, we do nothing. If `target` is adjacent to one or two existing intervals, we merge them. Otherwise, `target` forms a new, single-element interval. Binary search is the natural choice for finding the position in a sorted list.

## Algorithm
1. Initialize an empty list `intervals` to store disjoint intervals, where each interval is represented as `[start, end]`.
2. When `addNum(target)` is called:
    a. If `intervals` is empty, add `[target, target]` as the first interval.
    b. Perform a binary search on `intervals` to find the potential insertion point for `target`. The binary search should aim to find the index `low` such that all intervals before `low` start before `target`, and all intervals at or after `low` start at or after `target`.
    c. After binary search, `low` will point to the first interval whose start is greater than or equal to `target`, or `intervals.size()` if `target` is greater than all interval starts.
    d. Let `left = low - 1` and `right = low`. These represent the intervals immediately before and at/after the potential insertion point.
    e. Check if `target` can merge with the interval at `left`: `mergeLeft = (left >= 0 && intervals.get(left)[1] + 1 == target)`.
    f. Check if `target` can merge with the interval at `right`: `mergeRight = (right < intervals.size() && intervals.get(right)[0] - 1 == target)`.
    g. **Case 1: Merge Left and Right:** If `mergeLeft` and `mergeRight` are both true, it means `target` connects two existing intervals. Merge them by updating the end of the `left` interval to the end of the `right` interval (`intervals.get(left)[1] = intervals.get(right)[1]`) and then remove the `right` interval (`intervals.remove(right)`).
    h. **Case 2: Merge Left Only:** If `mergeLeft` is true but `mergeRight` is false, `target` extends the `left` interval. Update the end of the `left` interval (`intervals.get(left)[1] = target`).
    i. **Case 3: Merge Right Only:** If `mergeRight` is true but `mergeLeft` is false, `target` extends the `right` interval. Update the start of the `right` interval (`intervals.get(right)[0] = target`).
    j. **Case 4: No Merge:** If neither `mergeLeft` nor `mergeRight` is true, `target` forms a new disjoint interval. Insert `[target, target]` at index `low` (`intervals.add(low, new int[]{target, target})`).
3. When `getIntervals()` is called, return the current list of intervals converted to a 2D array.

## Concept to Remember
*   **Disjoint Intervals:** Understanding how to represent and manage sets of numbers that do not overlap.
*   **Binary Search:** Efficiently finding an element or insertion point in a sorted data structure.
*   **List Manipulation:** Efficiently adding, removing, and updating elements in a dynamic list.
*   **Edge Cases:** Handling empty lists, single-element intervals, and boundary conditions during merging.

## Common Mistakes
*   **Incorrect Binary Search Logic:** Off-by-one errors in binary search or not correctly determining the insertion point.
*   **Handling Merge Conditions:** Missing cases where `target` connects two intervals, or incorrectly merging when it should only extend one.
*   **Modifying List During Iteration:** If not careful, removing elements while iterating can lead to index out of bounds errors or skipped elements. The provided solution avoids this by using indices derived from binary search.
*   **Not Handling Empty List:** Forgetting to initialize the `intervals` list correctly when the first number is added.
*   **Incorrectly Updating Interval Boundaries:** Errors in setting the `start` or `end` of merged or extended intervals.

## Complexity Analysis
- Time: O(log N) for `addNum`, where N is the number of disjoint intervals. This is because binary search takes O(log N) time, and list insertion/removal at a specific index can take O(N) in the worst case for `ArrayList`. However, if we consider the total number of intervals ever added, and the fact that merging reduces the number of intervals, the amortized time complexity for `addNum` can be considered closer to O(log N) if the number of intervals remains relatively small. `getIntervals` is O(N) to convert the list to an array.
- Space: O(N), where N is the number of disjoint intervals stored.

## Commented Code
```java
import java.util.ArrayList; // Import the ArrayList class to use dynamic arrays.
import java.util.List; // Import the List interface.

class SummaryRanges {
    List<int[]> intervals; // Declare a list to store disjoint intervals. Each interval is an int array of size 2: [start, end].

    public SummaryRanges() {
        intervals = new ArrayList<>(); // Initialize the list of intervals as an empty ArrayList.
    }

    public void addNum(int target) {
        // If the intervals list is empty, add the target as the first interval [target, target].
        if (intervals.isEmpty()) {
            intervals.add(new int[]{target, target});
            return; // Exit the method as the first number is handled.
        }

        // Perform binary search to find the correct position for the target number.
        // 'low' will eventually point to the index where 'target' should be inserted or the first interval that starts >= target.
        int low = 0, high = intervals.size() - 1;
        // The loop continues as long as the search space is valid.
        while (low <= high) {
            int mid = low + (high - low) / 2; // Calculate the middle index to avoid potential integer overflow.
            int start = intervals.get(mid)[0]; // Get the start of the interval at the middle index.
            int end = intervals.get(mid)[1]; // Get the end of the interval at the middle index.

            // If the target is less than the start of the current interval, search in the left half.
            if (target < start) {
                high = mid - 1;
            }
            // If the target is greater than the end of the current interval, search in the right half.
            else if (target > end) {
                low = mid + 1;
            }
            // If the target falls within an existing interval [start, end], do nothing and return.
            else {
                return;
            }
        }

        // After binary search, 'low' is the index where the new interval might be inserted,
        // or the index of the first interval that starts after 'target'.
        // 'left' is the index of the interval immediately before 'low'.
        // 'right' is the index of the interval at 'low' (which is the first interval starting >= target).
        int left = low - 1;
        int right = low;

        // Check if the target can merge with the interval to its left.
        // This happens if 'left' is a valid index and 'target' is exactly one greater than the end of the 'left' interval.
        boolean mergeLeft = left >= 0 && intervals.get(left)[1] + 1 == target;

        // Check if the target can merge with the interval to its right.
        // This happens if 'right' is a valid index and 'target' is exactly one less than the start of the 'right' interval.
        boolean mergeRight = right < intervals.size() && intervals.get(right)[0] - 1 == target;

        // Case 1: Merge with both left and right intervals.
        if (mergeLeft && mergeRight) {
            // Extend the end of the left interval to the end of the right interval.
            intervals.get(left)[1] = intervals.get(right)[1];
            // Remove the right interval as it's now merged into the left one.
            intervals.remove(right);
        }
        // Case 2: Merge only with the left interval.
        else if (mergeLeft) {
            // Extend the end of the left interval to the target.
            intervals.get(left)[1] = target;
        }
        // Case 3: Merge only with the right interval.
        else if (mergeRight) {
            // Extend the start of the right interval to the target.
            intervals.get(right)[0] = target;
        }
        // Case 4: No merge possible, insert a new interval [target, target].
        else {
            // Insert the new interval at the 'low' index to maintain sorted order.
            intervals.add(low, new int[]{target, target});
        }
    }

    public int[][] getIntervals() {
        // Convert the list of intervals into a 2D integer array and return it.
        // new int[0][] is a common way to provide an empty array of the correct type for toArray.
        return intervals.toArray(new int[0][]);
    }
}
```

## Interview Tips
*   **Explain the Data Structure Choice:** Justify why a sorted list of intervals is a good choice. Mention that it allows for efficient searching and merging.
*   **Walk Through Merge Scenarios:** Clearly explain the four cases for `addNum`: no merge, merge left, merge right, and merge both. Use examples to illustrate each.
*   **Discuss Complexity Trade-offs:** Be prepared to discuss the time complexity of `addNum` (O(log N) for search, but O(N) for list modification) and how it might be improved with other data structures (e.g., balanced BSTs, though `ArrayList` is often sufficient for typical interview constraints).
*   **Handle Edge Cases:** Explicitly mention how you handle an empty `intervals` list and boundary conditions during merging (e.g., `left >= 0`, `right < intervals.size()`).

## Revision Checklist
- [ ] Understand the problem: maintain disjoint intervals from a stream.
- [ ] Chosen data structure: sorted list of `[start, end]` intervals.
- [ ] `addNum` logic:
    - [ ] Handle empty list.
    - [ ] Binary search to find insertion/merge point.
    - [ ] Identify `left` and `right` potential merge candidates.
    - [ ] Implement all four merge/insert cases correctly.
- [ ] `getIntervals` logic: convert list to 2D array.
- [ ] Complexity analysis: Time and Space.
- [ ] Edge cases: empty list, single-element intervals, merging at boundaries.

## Similar Problems
*   Merge Intervals
*   Insert Interval
*   Employee Free Time
*   My Calendar I, II, III

## Tags
`Array` `Binary Search` `List` `Data Stream`
