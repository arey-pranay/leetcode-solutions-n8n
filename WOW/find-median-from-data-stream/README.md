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
This problem asks to efficiently find the median of a continuously growing stream of numbers.
We solve it by maintaining two heaps: a max-heap for the smaller half and a min-heap for the larger half of the numbers.

## Intuition
The median is the middle element in a sorted list. If we can maintain two halves of the sorted list, where the smaller half is in a max-heap and the larger half is in a min-heap, the median will either be the top of the max-heap (if odd number of elements) or the average of the tops of both heaps (if even number of elements). The key is to keep these two heaps balanced in size.

## Algorithm
1. Initialize two priority queues: `maxHeap` (for the smaller half, ordered in descending) and `minHeap` (for the larger half, ordered in ascending).
2. Initialize a boolean flag `isEven` to `true`, indicating an even number of elements initially.
3. When `addNum(num)` is called:
    a. If `isEven` is true (meaning we are adding the first element to a new pair or the total count is even):
        i. Add `num` to `minHeap`.
        ii. Move the smallest element from `minHeap` to `maxHeap`. This ensures `maxHeap` always contains elements smaller than or equal to `minHeap`'s elements.
    b. If `isEven` is false (meaning the total count is odd):
        i. Add `num` to `maxHeap`.
        ii. Move the largest element from `maxHeap` to `minHeap`. This ensures `minHeap` always contains elements larger than or equal to `maxHeap`'s elements.
    c. Toggle the `isEven` flag.
4. When `findMedian()` is called:
    a. If `isEven` is true (even number of elements): The median is the average of the top elements of `maxHeap` and `minHeap`.
    b. If `isEven` is false (odd number of elements): The median is the top element of `maxHeap` (which will hold the middle element).

## Concept to Remember
*   **Heaps (Priority Queues):** Understanding how min-heaps and max-heaps work, and their logarithmic time complexity for insertion and extraction.
*   **Median Definition:** The middle element of a sorted dataset, or the average of the two middle elements.
*   **Balancing Data Structures:** The technique of using two heaps to maintain a balanced split of data for efficient median finding.
*   **Two-Pointer/Two-Heap Approach:** A common pattern for problems involving finding a middle element or partitioning data.

## Common Mistakes
*   **Incorrect Heap Initialization:** Forgetting to use `Collections.reverseOrder()` for the `maxHeap`.
*   **Improper Balancing Logic:** Not correctly transferring elements between heaps to maintain the invariant that `maxHeap` elements are <= `minHeap` elements.
*   **Off-by-One Errors in `isEven` Logic:** Mismanaging the `isEven` flag, leading to incorrect median calculation for odd/even counts.
*   **Integer Division:** Forgetting to cast to `double` before division when calculating the median for an even number of elements.
*   **Handling Empty Heaps:** Not considering edge cases where one or both heaps might be empty (though the provided solution implicitly handles this by always adding to one heap first).

## Complexity Analysis
*   **Time:** O(log n) - For `addNum`, we perform at most two heap insertions and two heap deletions, each taking O(log n) time. For `findMedian`, peeking at the top of heaps is O(1).
*   **Space:** O(n) - We store all `n` numbers in the two heaps.

## Commented Code
```java
import java.util.Collections; // Import the Collections class for reverse order comparator
import java.util.PriorityQueue; // Import the PriorityQueue class for heap implementation

class MedianFinder {
    PriorityQueue<Integer> minHeap ; // This heap will store the larger half of the numbers. It's a min-heap, so the smallest of the larger half is at the top.
    PriorityQueue<Integer> maxHeap ; // This heap will store the smaller half of the numbers. It's a max-heap (using reverseOrder), so the largest of the smaller half is at the top.
    boolean isEven = true; // A flag to track if the total number of elements added so far is even. Initially true as no elements are added.

    public MedianFinder() {
       minHeap = new PriorityQueue<>(); // Initialize the min-heap.
       // Initialize the max-heap. Collections.reverseOrder() makes it a max-heap.
       maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        // The logic here is to maintain the invariant:
        // 1. All elements in maxHeap are less than or equal to all elements in minHeap.
        // 2. The sizes of maxHeap and minHeap differ by at most 1.

        if(isEven){ // If the total count of numbers is currently even (or we are adding the first element of a pair)
            minHeap.offer(num); // Add the new number to the minHeap first.
            // Then, move the smallest element from minHeap to maxHeap.
            // This ensures that the largest element of the smaller half (maxHeap) is correctly placed.
            maxHeap.offer(minHeap.poll());
        } else { // If the total count of numbers is currently odd
            maxHeap.offer(num); // Add the new number to the maxHeap first.
            // Then, move the largest element from maxHeap to minHeap.
            // This ensures that the smallest element of the larger half (minHeap) is correctly placed.
            minHeap.offer(maxHeap.poll());
        }
        // After adding a number and rebalancing, toggle the flag.
        // If it was even, it becomes odd. If it was odd, it becomes even.
        isEven = !isEven;
    }
    
    public double findMedian() {
        // If the total count of numbers is even, the median is the average of the two middle elements.
        // These are the largest element in the smaller half (top of maxHeap) and the smallest element in the larger half (top of minHeap).
        if(isEven) return (double)(minHeap.peek() + maxHeap.peek()) /2;
        // If the total count of numbers is odd, the median is the single middle element.
        // This element will always be in the maxHeap (as we ensure maxHeap is either equal in size or one larger than minHeap).
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
*   **Explain the Two-Heap Strategy:** Clearly articulate why two heaps are necessary and how they maintain the sorted halves.
*   **Walk Through `addNum` Logic:** Verbally trace the `addNum` method with a few examples (e.g., adding 1, then 2, then 3) to show how the heaps are balanced.
*   **Discuss Edge Cases:** Mention what happens when the stream is empty or has only one element.
*   **Clarify Median for Even/Odd Counts:** Be precise about how the median is calculated in both scenarios.
*   **Ask About Constraints:** If not provided, ask about the potential range of numbers and the expected number of calls to `addNum` and `findMedian` to confirm the chosen approach is optimal.

## Revision Checklist
- [ ] Understand the problem: finding median from a data stream.
- [ ] Recall the two-heap approach (max-heap for smaller half, min-heap for larger half).
- [ ] Implement `addNum` with correct heap balancing logic.
- [ ] Implement `findMedian` for both even and odd number of elements.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty stream, single element).
- [ ] Practice explaining the intuition and algorithm clearly.

## Similar Problems
*   Sliding Window Median
*   Kth Largest Element in an Array
*   Find K Pairs with Smallest Sums

## Tags
`Heap` `Two Pointers` `Data Stream`
