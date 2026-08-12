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
    PriorityQueue<Integer> bigs;
    PriorityQueue<Integer> smalls;
    boolean isEven;
    public MedianFinder() {
        this.bigs = new PriorityQueue<>(); //minheap kyuki bde walo me se smallest chahiye median ke liye
        this.smalls = new PriorityQueue<>((a,b)->b-a); // maxheap
        this.isEven = true;
    }
    
    public void addNum(int num){
        if(this.isEven){ // ek bar smalls me daaleneg ek baar bigs me
          smalls.add(num);
          bigs.add(smalls.poll());// balance krne ke liye smalls ka biggest number nikaal kr bigs me daala hai
        } else {
          bigs.add(num);
          smalls.add(bigs.poll());
        }
        this.isEven = !this.isEven;
    }
    
    public double findMedian() {
        return isEven ? (bigs.peek()+smalls.peek())/2.0 : bigs.peek(); //by default uneven case me bigs me zyada elements hai
    }
}

```

---

---
## Quick Revision
This problem asks to find the median of a data stream as numbers are added.
We solve it by maintaining two heaps: a max-heap for the smaller half and a min-heap for the larger half.

## Intuition
The median of a sorted list is the middle element (or average of two middle elements). If we can efficiently keep track of the smaller half and the larger half of the numbers seen so far, we can find the median. Two heaps, a max-heap for the smaller half and a min-heap for the larger half, allow us to do this. The largest element in the max-heap will be the largest of the smaller half, and the smallest element in the min-heap will be the smallest of the larger half.

## Algorithm
1. Initialize two priority queues: `smalls` (a max-heap) and `bigs` (a min-heap).
2. Initialize a boolean flag `isEven` to `true`, indicating the total number of elements seen so far is even.
3. Implement `addNum(int num)`:
    a. If `isEven` is true (meaning we are adding the first element to a new pair or the total count is even):
        i. Add `num` to `smalls` (max-heap).
        ii. Move the largest element from `smalls` to `bigs` (min-heap). This ensures `bigs` always contains elements greater than or equal to `smalls`.
    b. If `isEven` is false (meaning the total count is odd):
        i. Add `num` to `bigs` (min-heap).
        ii. Move the smallest element from `bigs` to `smalls` (max-heap). This ensures `smalls` always contains elements less than or equal to `bigs`.
    c. Toggle `isEven`.
4. Implement `findMedian()`:
    a. If `isEven` is true (total count is even):
        i. The median is the average of the top elements of `smalls` and `bigs`. Return `(smalls.peek() + bigs.peek()) / 2.0`.
    b. If `isEven` is false (total count is odd):
        i. The median is the top element of `bigs` (since `bigs` will have one more element than `smalls` in this case). Return `bigs.peek()`.

## Concept to Remember
*   **Heaps (Priority Queues):** Efficient data structures for retrieving the minimum or maximum element.
*   **Two-Heap Approach:** A common pattern for median-finding problems, dividing data into two halves.
*   **Balancing Heaps:** Maintaining a consistent size difference between the two heaps is crucial for correctness.

## Common Mistakes
*   **Incorrect Heap Type:** Using two min-heaps or two max-heaps instead of one of each.
*   **Improper Balancing:** Not moving elements between heaps correctly after adding a new number, leading to incorrect size differences.
*   **Integer Division:** Forgetting to cast to `double` when calculating the average for an even number of elements, leading to incorrect median values.
*   **Off-by-One Errors in `isEven` Logic:** Mismanaging the `isEven` flag can lead to incorrect heap assignments or median calculations.

## Complexity Analysis
*   **Time:** O(log n) for `addNum` (due to heap insertions/deletions), O(1) for `findMedian` (peeking at heap tops).
*   **Space:** O(n) to store all the numbers in the two heaps.

## Commented Code
```java
import java.util.PriorityQueue; // Import the PriorityQueue class for heap implementation

class MedianFinder {
    // PriorityQueue for the smaller half of numbers. It's a max-heap, so the largest element is at the top.
    // We use a custom comparator (b-a) to achieve max-heap behavior with Integer.
    PriorityQueue<Integer> smalls;
    // PriorityQueue for the larger half of numbers. It's a min-heap by default.
    PriorityQueue<Integer> bigs;
    // Flag to track if the total number of elements added so far is even.
    boolean isEven;

    // Constructor to initialize the heaps and the flag.
    public MedianFinder() {
        // Initialize smalls as a max-heap.
        this.smalls = new PriorityQueue<>((a, b) -> b - a);
        // Initialize bigs as a min-heap (default behavior).
        this.bigs = new PriorityQueue<>();
        // Initially, no elements are added, so the count is considered even.
        this.isEven = true;
    }

    // Method to add a new number to the data stream.
    public void addNum(int num) {
        // If the total count of numbers is currently even (or we are adding the first element of a pair).
        if (this.isEven) {
            // Add the new number to the max-heap (smalls).
            smalls.add(num);
            // To maintain the property that all elements in bigs are >= elements in smalls,
            // move the largest element from smalls to bigs.
            bigs.add(smalls.poll());
        } else { // If the total count of numbers is currently odd.
            // Add the new number to the min-heap (bigs).
            bigs.add(num);
            // To maintain the property that all elements in smalls are <= elements in bigs,
            // move the smallest element from bigs to smalls.
            smalls.add(bigs.poll());
        }
        // Toggle the isEven flag because we just added one number.
        this.isEven = !this.isEven;
    }

    // Method to find the median of all numbers added so far.
    public double findMedian() {
        // If the total count of numbers is even.
        if (this.isEven) {
            // The median is the average of the largest element in the smaller half (smalls.peek())
            // and the smallest element in the larger half (bigs.peek()).
            // We use 2.0 for floating-point division.
            return (smalls.peek() + bigs.peek()) / 2.0;
        } else { // If the total count of numbers is odd.
            // In the odd case, bigs will always have one more element than smalls.
            // The median is simply the smallest element in the larger half (bigs.peek()).
            return bigs.peek();
        }
    }
}
```

## Interview Tips
*   **Explain the Two-Heap Strategy:** Clearly articulate why two heaps are necessary and how they partition the data.
*   **Discuss Balancing:** Emphasize the importance of maintaining the size invariant (difference of at most 1) between the heaps.
*   **Handle Edge Cases:** Be prepared to discuss what happens when the first few numbers are added, or when the stream is empty.
*   **Clarify Median Definition:** Ensure you understand whether the median for an even number of elements is the lower or upper middle element, or the average (standard definition is average).

## Revision Checklist
- [ ] Understand the problem: finding median from a stream.
- [ ] Recall the two-heap approach (max-heap for smaller half, min-heap for larger half).
- [ ] Implement `addNum` correctly, including heap balancing.
- [ ] Implement `findMedian` correctly for both even and odd counts.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty stream, first few elements).

## Similar Problems
*   Sliding Window Median
*   Kth Largest Element in an Array
*   Find Median from Data Stream (different language implementations)

## Tags
`Heap` `Two Pointers` `Design` `Data Stream`
