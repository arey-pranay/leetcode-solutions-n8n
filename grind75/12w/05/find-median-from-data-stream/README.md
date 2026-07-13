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
This problem asks to find the median of a data stream in real-time.
We solve it by maintaining two heaps: a max-heap for the smaller half and a min-heap for the larger half of the numbers.

## Intuition
The median is the middle element in a sorted list. If we can efficiently keep track of the two halves of the sorted list, we can find the median. A max-heap is perfect for storing the smaller half because its top element is the largest among the smaller numbers. Similarly, a min-heap is ideal for the larger half, with its top being the smallest among the larger numbers. By keeping these two heaps balanced in size, the median will either be the top of one heap (if the total count is odd) or the average of the tops of both heaps (if the total count is even).

## Algorithm
1. Initialize two priority queues: `maxHeap` (for the smaller half, using reverse order) and `minHeap` (for the larger half, using natural order).
2. Initialize a boolean flag `isEven` to `true` to track if the total number of elements seen so far is even.
3. When `addNum(num)` is called:
    a. If `isEven` is true (meaning the total count was even before adding `num`):
        i. Add `num` to `minHeap`.
        ii. Move the smallest element from `minHeap` to `maxHeap`. This ensures `maxHeap` always contains elements smaller than or equal to `minHeap`'s elements.
    b. If `isEven` is false (meaning the total count was odd before adding `num`):
        i. Add `num` to `maxHeap`.
        ii. Move the largest element from `maxHeap` to `minHeap`. This ensures `minHeap` always contains elements greater than or equal to `maxHeap`'s elements.
    c. Toggle the `isEven` flag.
4. When `findMedian()` is called:
    a. If `isEven` is true (total count is even): The median is the average of the top elements of `maxHeap` and `minHeap`.
    b. If `isEven` is false (total count is odd): The median is the top element of `maxHeap` (which will hold the middle element).

## Concept to Remember
*   **Heaps (Priority Queues):** Efficient data structures for retrieving the minimum or maximum element.
*   **Two-Heap Approach:** A common pattern for median finding, where one heap stores the smaller half and the other stores the larger half.
*   **Balancing Heaps:** Maintaining a size difference of at most 1 between the two heaps is crucial for correctness.

## Common Mistakes
*   Incorrectly initializing the priority queues (e.g., not using `Collections.reverseOrder()` for the max-heap).
*   Not properly balancing the heaps after adding a new number, leading to incorrect median calculations.
*   Handling the edge case of an empty stream or the first element added.
*   Integer division issues when calculating the average for an even number of elements.

## Complexity Analysis
*   **Time:** O(log N) for `addNum` (due to heap operations) and O(1) for `findMedian` (accessing heap tops). N is the number of elements added so far.
*   **Space:** O(N) to store all the numbers in the two heaps.

## Commented Code
```java
import java.util.Collections; // Import the Collections class for reverse order comparator
import java.util.PriorityQueue; // Import the PriorityQueue class for heap implementation

class MedianFinder {
    // minHeap will store the larger half of the numbers. Its top element is the smallest among the larger half.
    PriorityQueue<Integer> minHeap ;
    // maxHeap will store the smaller half of the numbers. Its top element is the largest among the smaller half.
    // Collections.reverseOrder() makes it a max-heap.
    PriorityQueue<Integer> maxHeap ;
    // isEven tracks if the total number of elements added so far is even.
    boolean isEven = true;

    // Constructor to initialize the heaps.
    public MedianFinder() {
       // Initialize minHeap with natural ordering (smallest element at the top).
       minHeap = new PriorityQueue<>();
       // Initialize maxHeap with reverse ordering (largest element at the top).
       maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    // Method to add a new number to the data stream.
    public void addNum(int num) {
        // If the total count of numbers is currently even (before adding 'num').
        if(isEven){
            // Add the new number to the minHeap first. This is a temporary placement.
            minHeap.offer(num);
            // Then, move the smallest element from minHeap to maxHeap.
            // This ensures that maxHeap always contains elements smaller than or equal to minHeap's elements.
            maxHeap.offer(minHeap.poll());
        } else { // If the total count of numbers is currently odd (before adding 'num').
            // Add the new number to the maxHeap first. This is a temporary placement.
            maxHeap.offer(num);
            // Then, move the largest element from maxHeap to minHeap.
            // This ensures that minHeap always contains elements greater than or equal to maxHeap's elements.
            minHeap.offer(maxHeap.poll());
        }
        // Toggle the isEven flag because we just added one element.
        isEven = !isEven;
    }

    // Method to find the median of the numbers added so far.
    public double findMedian() {
        // If the total count of numbers is even.
        if(isEven) {
            // The median is the average of the top elements of maxHeap and minHeap.
            // We cast to double to ensure floating-point division.
            return (double)(minHeap.peek() + maxHeap.peek()) /2;
        }
        // If the total count of numbers is odd.
        // The median is the top element of maxHeap (which will hold the middle element).
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

## Interview Tips
*   Clearly explain the two-heap strategy and why it works for maintaining the median.
*   Walk through an example of adding numbers and how the heaps are updated.
*   Discuss the time and space complexity of your solution.
*   Be prepared to discuss alternative approaches (like sorting, which is inefficient for a stream) and why the heap approach is superior.

## Revision Checklist
- [ ] Understand the problem: finding median from a stream.
- [ ] Grasp the two-heap intuition (max-heap for smaller half, min-heap for larger half).
- [ ] Implement `addNum` correctly, ensuring heaps are balanced.
- [ ] Implement `findMedian` correctly for both even and odd total counts.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty stream, first element).

## Similar Problems
*   Sliding Window Median
*   Kth Largest Element in an Array
*   Find K Pairs with Smallest Sums

## Tags
`Heap` `Two Pointers` `Design` `Data Stream`
