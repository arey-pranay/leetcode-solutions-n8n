# Find Median From Data Stream

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Two Pointers` `Design` `Sorting` `Heap (Priority Queue)` `Data Stream`  
**Time:** O(log n)  
**Space:** O(n)

---

## Solution (java)

```java
class MedianFinder {
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private boolean even = true;

    public double findMedian() {
        if (even) return (maxHeap.peek() + minHeap.peek()) / 2.0;
        else return maxHeap.peek();
    }

// 1 2 5 3 4 8 6 7

// min = 5 3
// max = 1 2
    public void addNum(int num) {
        if (even) {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
        even = !even;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```

---

---
## Quick Revision
This problem asks to find the median of a data stream in real-time.
We solve it by maintaining two heaps: a max-heap for the lower half and a min-heap for the upper half of the numbers.

## Intuition
The median of a sorted list is the middle element (or the average of the two middle elements). If we can efficiently keep track of the smaller half and the larger half of the numbers seen so far, we can quickly find the median. Two heaps, a max-heap for the smaller half and a min-heap for the larger half, allow us to do this. The max-heap's root will be the largest element in the smaller half, and the min-heap's root will be the smallest element in the larger half.

## Algorithm
1. Initialize two priority queues: `maxHeap` (for the smaller half, ordered in descending order) and `minHeap` (for the larger half, ordered in ascending order).
2. Initialize a boolean flag `even` to `true`, indicating that the total number of elements seen so far is even.
3. Implement `addNum(int num)`:
    a. If `even` is true (meaning the total count is currently even):
        i. Add `num` to `minHeap`.
        ii. Move the smallest element from `minHeap` to `maxHeap`. This ensures `maxHeap` always contains elements less than or equal to `minHeap`'s elements.
    b. If `even` is false (meaning the total count is currently odd):
        i. Add `num` to `maxHeap`.
        ii. Move the largest element from `maxHeap` to `minHeap`. This ensures `minHeap` always contains elements greater than or equal to `maxHeap`'s elements.
    c. Toggle the `even` flag.
4. Implement `findMedian()`:
    a. If `even` is true (total count is even):
        i. The median is the average of the top elements of `maxHeap` and `minHeap`. Return `(maxHeap.peek() + minHeap.peek()) / 2.0`.
    b. If `even` is false (total count is odd):
        i. The median is the top element of `maxHeap` (since `maxHeap` will have one more element than `minHeap` when the count is odd). Return `maxHeap.peek()`.

## Concept to Remember
*   **Heaps (Priority Queues):** Efficient data structure for retrieving the minimum or maximum element.
*   **Two-Heap Approach:** A common pattern for median-finding problems, dividing data into two halves.
*   **Maintaining Balance:** Ensuring the sizes of the two heaps are balanced (differing by at most 1) is crucial for correct median calculation.
*   **Data Stream Processing:** Handling elements one by one without storing the entire dataset.

## Common Mistakes
*   **Incorrect Heap Ordering:** Using `PriorityQueue` without specifying `Collections.reverseOrder()` for the max-heap.
*   **Improper Balancing:** Not ensuring the heap sizes are maintained correctly after adding an element, leading to incorrect median calculation.
*   **Integer Division:** Forgetting to cast to `double` when calculating the average for an even number of elements, leading to truncated results.
*   **Edge Cases:** Not considering the initial state or when one heap might be empty.

## Complexity Analysis
*   **Time:** O(log n) - for `addNum`, as heap operations (offer, poll) take logarithmic time. `findMedian` is O(1).
*   **Space:** O(n) - to store all the numbers in the two heaps.

## Commented Code
```java
import java.util.Collections; // Import the Collections class for reverse order comparator
import java.util.PriorityQueue; // Import the PriorityQueue class for heap implementation

class MedianFinder {
    // maxHeap stores the smaller half of the numbers. It's a max-heap, so the largest element of the smaller half is at the top.
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    // minHeap stores the larger half of the numbers. It's a min-heap, so the smallest element of the larger half is at the top.
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // A flag to track if the total number of elements seen so far is even. Initially true as no elements are present.
    private boolean even = true;

    // This method finds the median of all numbers added so far.
    public double findMedian() {
        // If the total count of numbers is even, the median is the average of the top elements of both heaps.
        if (even) {
            // We use 2.0 to ensure floating-point division.
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        // If the total count of numbers is odd, the median is the top element of the maxHeap.
        // This is because when the count is odd, maxHeap will always have one more element than minHeap.
        else {
            return maxHeap.peek();
        }
    }

    // This method adds a new number to the data stream and maintains the heap properties.
    public void addNum(int num) {
        // If the total count of numbers is currently even:
        if (even) {
            // Add the new number to the minHeap first.
            minHeap.offer(num);
            // Then, move the smallest element from minHeap to maxHeap.
            // This ensures that maxHeap always contains elements smaller than or equal to minHeap's elements.
            maxHeap.offer(minHeap.poll());
        }
        // If the total count of numbers is currently odd:
        else {
            // Add the new number to the maxHeap first.
            maxHeap.offer(num);
            // Then, move the largest element from maxHeap to minHeap.
            // This ensures that minHeap always contains elements larger than or equal to maxHeap's elements.
            minHeap.offer(maxHeap.poll());
        }
        // Toggle the 'even' flag because we just added one number, changing the parity of the total count.
        even = !even;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```

## Interview Tips
*   Clearly explain the two-heap strategy and why it works for finding the median.
*   Walk through an example step-by-step, showing how `addNum` updates the heaps and how `findMedian` uses them.
*   Be prepared to discuss the time and space complexity of your solution.
*   Mention how you would handle potential edge cases, like the very first element added.

## Revision Checklist
- [ ] Understand the definition of a median.
- [ ] Recall how to implement a max-heap and a min-heap using `PriorityQueue`.
- [ ] Grasp the two-heap strategy for median finding.
- [ ] Practice balancing the sizes of the two heaps.
- [ ] Ensure correct handling of even vs. odd number of elements.
- [ ] Be mindful of integer division when calculating the average.

## Similar Problems
*   Sliding Window Median
*   Find Median from Data Stream (LintCode)

## Tags
`Heap` `Two Pointers` `Design` `Data Stream`
