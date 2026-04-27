# Find Median From Data Stream

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Two Pointers` `Design` `Sorting` `Heap (Priority Queue)` `Data Stream`  
**Time:** O(log N)  
**Space:** O(N)

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
The median is the middle element of a sorted list. If the list has an even number of elements, it's the average of the two middle elements. To efficiently find the median as numbers are added, we can divide the numbers into two halves: a lower half and an upper half. A max-heap is perfect for storing the lower half because its root will always be the largest element in that half. Similarly, a min-heap is ideal for the upper half, with its root being the smallest element in that half. By keeping these two heaps balanced in size, the median will always be accessible from the roots of the heaps.

## Algorithm
1. Initialize two priority queues: `maxHeap` (for the lower half, storing elements in descending order) and `minHeap` (for the upper half, storing elements in ascending order).
2. Maintain a boolean flag, `even`, to track if the total number of elements added so far is even. Initialize `even` to `true`.
3. Implement `addNum(int num)`:
    a. If `even` is true (meaning the total count is currently even, and we're about to add the first element of a new pair):
        i. Add `num` to `minHeap`.
        ii. Move the smallest element from `minHeap` to `maxHeap`. This ensures `maxHeap` always contains elements less than or equal to `minHeap`'s smallest element.
    b. If `even` is false (meaning the total count is currently odd):
        i. Add `num` to `maxHeap`.
        ii. Move the largest element from `maxHeap` to `minHeap`. This ensures `minHeap` always contains elements greater than or equal to `maxHeap`'s largest element.
    c. Toggle the `even` flag.
4. Implement `findMedian()`:
    a. If `even` is true (total count is even):
        i. The median is the average of the top elements of `maxHeap` and `minHeap`. Return `(maxHeap.peek() + minHeap.peek()) / 2.0`.
    b. If `even` is false (total count is odd):
        i. The median is the top element of `maxHeap` (which will hold the single middle element). Return `maxHeap.peek()`.

## Concept to Remember
*   **Heaps (Priority Queues):** Understanding how max-heaps and min-heaps work, and their use in efficiently retrieving the minimum or maximum element.
*   **Balancing Data Structures:** The core idea of maintaining balance between two data structures to efficiently query a central value.
*   **Median Definition:** Knowing how to calculate the median for both odd and even sized datasets.

## Common Mistakes
*   **Incorrect Heap Balancing:** Not ensuring that the sizes of the two heaps differ by at most 1, which is crucial for `findMedian` to work correctly.
*   **Off-by-One Errors in `addNum` Logic:** Mishandling the initial insertion and subsequent transfers between heaps, especially when switching between even and odd counts.
*   **Integer Division:** Forgetting to cast to `double` when calculating the average for the median in the even case, leading to incorrect results.
*   **Not Handling Empty Heaps:** While this specific problem's constraints might prevent it, in a general scenario, one should consider edge cases where heaps might be empty.

## Complexity Analysis
*   **Time:** O(log N) - reason: Both `addNum` and `findMedian` operations involve heap operations (offer, poll, peek), which take logarithmic time with respect to the number of elements in the heap (N).
*   **Space:** O(N) - reason: We store all N elements in the two priority queues.

## Commented Code
```java
import java.util.Collections; // Import Collections for reverseOrder()
import java.util.PriorityQueue; // Import PriorityQueue for heap implementation

class MedianFinder {
    // maxHeap stores the smaller half of the numbers. Its root is the largest among the smaller half.
    // We use Collections.reverseOrder() to make it a max-heap.
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    // minHeap stores the larger half of the numbers. Its root is the smallest among the larger half.
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // 'even' flag tracks if the total number of elements added so far is even.
    // Initially, no elements are added, so the count is 0, which is even.
    private boolean even = true;

    /**
     * Returns the median of all elements so far.
     * @return The median value.
     */
    public double findMedian() {
        // If the total number of elements is even, the median is the average of the two middle elements.
        // These are the largest element in the maxHeap and the smallest element in the minHeap.
        if (even) {
            // We divide by 2.0 to ensure floating-point division.
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        // If the total number of elements is odd, the median is the single middle element.
        // This element will always be at the root of the maxHeap.
        else {
            return maxHeap.peek();
        }
    }

    /**
     * Adds a number into the data structure.
     * @param num The integer to add.
     */
    public void addNum(int num) {
        // If the total count of numbers is currently even (before adding 'num'),
        // we are about to make the count odd.
        if (even) {
            // Add the new number to the minHeap first.
            minHeap.offer(num);
            // Then, move the smallest element from minHeap to maxHeap.
            // This ensures that maxHeap always contains elements less than or equal to minHeap's smallest.
            maxHeap.offer(minHeap.poll());
        }
        // If the total count of numbers is currently odd (before adding 'num'),
        // we are about to make the count even.
        else {
            // Add the new number to the maxHeap first.
            maxHeap.offer(num);
            // Then, move the largest element from maxHeap to minHeap.
            // This ensures that minHeap always contains elements greater than or equal to maxHeap's largest.
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
*   **Explain the Two-Heap Strategy:** Clearly articulate why two heaps (max-heap and min-heap) are used and how they partition the data.
*   **Walk Through `addNum`:** Verbally trace the `addNum` method with a small example (e.g., adding 1, 2, 3, 4) to show how the heaps are populated and balanced.
*   **Discuss Balancing Logic:** Emphasize the importance of keeping the heap sizes balanced (differing by at most 1) and how the `addNum` logic achieves this.
*   **Clarify `findMedian` Logic:** Explain how the median is derived from the heap tops based on whether the total count is even or odd.

## Revision Checklist
- [ ] Understand the median definition for odd/even counts.
- [ ] Implement a max-heap and a min-heap.
- [ ] Design the `addNum` logic to maintain heap balance.
- [ ] Implement `findMedian` based on heap tops and parity.
- [ ] Consider edge cases (e.g., first element added).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Sliding Window Median
*   Kth Largest Element in an Array
*   Top K Frequent Elements

## Tags
`Heap` `Two Pointers` `Design` `Data Stream`
