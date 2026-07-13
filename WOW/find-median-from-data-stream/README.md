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
    PriorityQueue<Integer> minHeap ;
    PriorityQueue<Integer> maxHeap ;
    boolean isEven = true;
    public MedianFinder() {
       minHeap = new PriorityQueue<>(); // minheap m humaare saare bade numbers jaayenge lekin vo niklne m unn sab bade numbers ka sbse chhote chhote niklenge
       maxHeap = new PriorityQueue<>(Collections.reverseOrder());// maxheap m humare saare chhote numbers jaayenge lekin  vo nikaalne m sab chhote numbers m se jo bade bade hai vo niklenge
    }
    
    public void addNum(int num) {
        if(isEven){
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
        isEven = !isEven;
    }
    
    public double findMedian() {
        if(isEven) return (double)(minHeap.peek() + maxHeap.peek()) /2;
        return maxHeap.peek();
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
This problem asks to efficiently find the median of a stream of numbers as they are added.
We solve this by maintaining two heaps: a max-heap for the smaller half and a min-heap for the larger half.

## Intuition
The median of a sorted list is the middle element (or the average of the two middle elements). If we can keep the numbers split into two halves such that the largest element of the smaller half is less than or equal to the smallest element of the larger half, we can easily find the median. Priority queues (heaps) are perfect for this because they efficiently give us the minimum or maximum element.

## Algorithm
1. Initialize two priority queues: `maxHeap` (for the smaller half) and `minHeap` (for the larger half). `maxHeap` should store elements in descending order, and `minHeap` in ascending order.
2. When `addNum(num)` is called:
    a. If `maxHeap` is empty or `num` is less than or equal to the top of `maxHeap`, add `num` to `maxHeap`.
    b. Otherwise, add `num` to `minHeap`.
3. Rebalance the heaps:
    a. If `maxHeap` has more than one element more than `minHeap`, move the top element from `maxHeap` to `minHeap`.
    b. If `minHeap` has more elements than `maxHeap`, move the top element from `minHeap` to `maxHeap`.
4. When `findMedian()` is called:
    a. If the total number of elements is odd, the median is the top element of the larger heap (which will be `maxHeap` after rebalancing).
    b. If the total number of elements is even, the median is the average of the top elements of `maxHeap` and `minHeap`.

*Note: The provided solution uses a slightly different rebalancing strategy that achieves the same goal by always adding to one heap and then immediately moving an element to the other to maintain balance. It also uses a boolean flag `isEven` to track the total count parity.*

## Concept to Remember
*   **Heaps (Priority Queues):** Understanding how min-heaps and max-heaps work and their logarithmic time complexity for insertion and extraction.
*   **Median Definition:** Knowing how to find the median for both odd and even sized datasets.
*   **Data Stream Processing:** Techniques for handling data that arrives sequentially without storing the entire dataset.
*   **Balancing Data Structures:** Maintaining a balanced state between two data structures to efficiently query properties like the median.

## Common Mistakes
*   **Incorrect Heap Initialization:** Forgetting to use `Collections.reverseOrder()` for the max-heap.
*   **Improper Rebalancing:** Not ensuring that the sizes of the two heaps differ by at most one, leading to incorrect median calculations.
*   **Off-by-One Errors in Median Calculation:** Incorrectly handling the cases for even and odd numbers of elements when calculating the median.
*   **Not Handling Empty Heaps:** Failing to consider edge cases where one or both heaps might be empty during `addNum` or `findMedian`.
*   **Integer Overflow:** Forgetting to cast to `double` before division when calculating the median for an even number of elements.

## Complexity Analysis
*   **Time:** O(log n) - For `addNum`, inserting into a heap takes O(log n) and polling/offering between heaps also takes O(log n). For `findMedian`, peeking at the top of a heap is O(1).
*   **Space:** O(n) - To store all the numbers in the two heaps.

## Commented Code
```java
import java.util.Collections; // Import Collections for reverseOrder
import java.util.PriorityQueue; // Import PriorityQueue for heap implementation

class MedianFinder {
    // minHeap will store the larger half of the numbers. Its smallest element is at the top.
    PriorityQueue<Integer> minHeap ;
    // maxHeap will store the smaller half of the numbers. Its largest element is at the top.
    PriorityQueue<Integer> maxHeap ;
    // isEven tracks if the total number of elements added so far is even.
    boolean isEven = true; // Initially, no elements, so it's even.

    // Constructor to initialize the heaps.
    public MedianFinder() {
       // Initialize minHeap as a standard min-priority queue.
       minHeap = new PriorityQueue<>();
       // Initialize maxHeap as a max-priority queue using Collections.reverseOrder().
       maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    // Method to add a number to the data stream.
    public void addNum(int num) {
        // If the total count of numbers is currently even (meaning we are about to add the (2k+1)th element).
        if(isEven){
            // Add the new number to the minHeap first. This heap will temporarily hold the new number.
            minHeap.offer(num);
            // Then, move the smallest element from minHeap to maxHeap.
            // This ensures that maxHeap always contains elements smaller than or equal to elements in minHeap.
            // And it helps maintain the balance by ensuring maxHeap gets the "larger" of the two halves.
            maxHeap.offer(minHeap.poll());
        } else { // If the total count of numbers is currently odd (meaning we are about to add the (2k+2)th element).
            // Add the new number to the maxHeap first. This heap will temporarily hold the new number.
            maxHeap.offer(num);
            // Then, move the largest element from maxHeap to minHeap.
            // This ensures that minHeap always contains elements larger than or equal to elements in maxHeap.
            // And it helps maintain the balance by ensuring minHeap gets the "smaller" of the two halves.
            minHeap.offer(maxHeap.poll());
        }
        // Toggle the isEven flag because we just added one number.
        isEven = !isEven;
    }
    
    // Method to find the median of all numbers added so far.
    public double findMedian() {
        // If the total count of numbers is even (after the last addNum operation).
        if(isEven) {
            // The median is the average of the largest element in the smaller half (maxHeap.peek())
            // and the smallest element in the larger half (minHeap.peek()).
            // Cast to double to ensure floating-point division.
            return (double)(minHeap.peek() + maxHeap.peek()) /2;
        } else { // If the total count of numbers is odd.
            // The median is the middle element, which will be the largest element in the smaller half (maxHeap.peek())
            // because maxHeap will have one more element than minHeap in this case.
            return maxHeap.peek();
        }
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
*   **Explain the Two-Heap Strategy:** Clearly articulate why two heaps (a max-heap and a min-heap) are necessary and how they divide the data.
*   **Walk Through `addNum` Logic:** Verbally explain the insertion process and the rebalancing steps. Use a small example to illustrate how elements move between heaps.
*   **Discuss `findMedian` Logic:** Explain how the median is derived from the top elements of the heaps based on whether the total count is even or odd.
*   **Address Edge Cases:** Be prepared to discuss what happens when the first few numbers are added, or when heaps are empty.
*   **Complexity Justification:** Be ready to explain the time and space complexity of both `addNum` and `findMedian` operations.

## Revision Checklist
- [ ] Understand the problem: find median from a data stream.
- [ ] Recall the two-heap approach (max-heap for smaller half, min-heap for larger half).
- [ ] Implement `addNum` with correct heap insertion and rebalancing.
- [ ] Implement `findMedian` for both even and odd total element counts.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty stream, first few elements).
- [ ] Practice explaining the intuition and algorithm clearly.

## Similar Problems
*   Sliding Window Median
*   Kth Largest Element in an Array
*   Find Median from Data Stream (similar but often asked in a slightly different context or with variations)

## Tags
`Heap` `Two Pointers` `Design` `Data Stream`
