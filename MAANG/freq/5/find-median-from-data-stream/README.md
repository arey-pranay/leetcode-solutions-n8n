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
Given a stream of numbers, find the median at any point.
Solve by maintaining two heaps: a max-heap for the smaller half and a min-heap for the larger half.

## Intuition
The median is the middle element in a sorted list. If we can efficiently keep track of the smaller half of the numbers and the larger half, the median will be either the largest element of the smaller half or the smallest element of the larger half, or the average of these two. Two heaps, a max-heap for the smaller half and a min-heap for the larger half, allow us to do this.

## Algorithm
1. Initialize two priority queues: `smalls` (a max-heap) and `bigs` (a min-heap).
2. Initialize a boolean flag `isEven` to `true` (representing an even number of elements initially).
3. **`addNum(int num)`:**
    a. If `isEven` is true (meaning we are adding the first element to a new pair or the stream currently has an even number of elements):
        i. Add `num` to `smalls` (max-heap).
        ii. Move the largest element from `smalls` to `bigs` (min-heap). This ensures `bigs` always contains elements greater than or equal to `smalls`.
    b. If `isEven` is false (meaning the stream currently has an odd number of elements):
        i. Add `num` to `bigs` (min-heap).
        ii. Move the smallest element from `bigs` to `smalls` (max-heap). This ensures `smalls` always contains elements less than or equal to `bigs`.
    c. Toggle the `isEven` flag.
4. **`findMedian()`:**
    a. If `isEven` is true (meaning the total number of elements is even):
        i. The median is the average of the top element of `smalls` (largest of the smaller half) and the top element of `bigs` (smallest of the larger half). Return `(smalls.peek() + bigs.peek()) / 2.0`.
    b. If `isEven` is false (meaning the total number of elements is odd):
        i. The median is the top element of `bigs` (which will have one more element than `smalls` in this case). Return `bigs.peek()`.

## Concept to Remember
*   **Heaps (Priority Queues):** Efficient data structures for retrieving the minimum or maximum element.
*   **Two-Heap Approach:** A common pattern for median-finding problems, balancing elements between a max-heap and a min-heap.
*   **Maintaining Balance:** Crucial to ensure the heaps are of similar sizes (differing by at most 1) for correct median calculation.

## Common Mistakes
*   Incorrectly initializing the heaps (e.g., using min-heap for `smalls` or max-heap for `bigs`).
*   Not handling the balancing act between the two heaps correctly after adding a number.
*   Forgetting to use floating-point division (e.g., `/ 2` instead of `/ 2.0`) when calculating the median for an even number of elements.
*   Off-by-one errors in heap size management, leading to incorrect median calculation.

## Complexity Analysis
*   **Time:** O(log n) - for `addNum`, as heap operations (add, poll, peek) take logarithmic time. `findMedian` is O(1) as it only involves peeking.
*   **Space:** O(n) - to store all the numbers in the two heaps.

## Commented Code
```java
import java.util.PriorityQueue; // Import the PriorityQueue class for heap implementation

class MedianFinder {
    // PriorityQueue 'bigs' will act as a min-heap to store the larger half of the numbers.
    // The smallest element of the larger half will be at the top.
    PriorityQueue<Integer> bigs;
    // PriorityQueue 'smalls' will act as a max-heap to store the smaller half of the numbers.
    // The largest element of the smaller half will be at the top.
    // We achieve a max-heap by providing a custom comparator (b-a).
    PriorityQueue<Integer> smalls;
    // A boolean flag to track if the total number of elements added so far is even.
    // Initially, we assume an even count (0 elements).
    boolean isEven;

    // Constructor to initialize the MedianFinder object.
    public MedianFinder() {
        // Initialize 'bigs' as a min-heap (default behavior of PriorityQueue).
        this.bigs = new PriorityQueue<>();
        // Initialize 'smalls' as a max-heap using a lambda expression for the comparator.
        this.smalls = new PriorityQueue<>((a,b)->b-a);
        // Set the initial state to even.
        this.isEven = true;
    }
    
    // Method to add a new number to the data stream.
    public void addNum(int num){
        // If the current total count of numbers is even (or we are starting a new pair).
        if(this.isEven){
          // Add the new number to the max-heap 'smalls'.
          smalls.add(num);
          // To maintain the property that 'bigs' contains larger numbers,
          // we move the largest element from 'smalls' to 'bigs'.
          // This ensures 'smalls' doesn't grow larger than 'bigs' by more than 1.
          bigs.add(smalls.poll());
        } else { // If the current total count of numbers is odd.
          // Add the new number to the min-heap 'bigs'.
          bigs.add(num);
          // To maintain the property that 'smalls' contains smaller numbers,
          // we move the smallest element from 'bigs' to 'smalls'.
          // This ensures 'bigs' doesn't grow larger than 'smalls' by more than 1.
          smalls.add(bigs.poll());
        }
        // Toggle the 'isEven' flag because we've just added one element.
        this.isEven = !this.isEven;
    }
    
    // Method to find the median of all numbers added so far.
    public double findMedian() {
        // If the total number of elements is even (meaning 'smalls' and 'bigs' have equal size).
        // The median is the average of the largest element in 'smalls' and the smallest element in 'bigs'.
        // We use 2.0 for floating-point division.
        // If the total number of elements is odd (meaning 'bigs' has one more element than 'smalls').
        // The median is simply the smallest element in 'bigs' (which is the middle element).
        return isEven ? (bigs.peek()+smalls.peek())/2.0 : bigs.peek();
    }
}
```

## Interview Tips
*   Clearly explain the two-heap strategy and why it works for finding the median.
*   Walk through an example step-by-step, showing how numbers are added and how the heaps are balanced.
*   Be prepared to discuss the time and space complexity of both `addNum` and `findMedian`.
*   If asked to optimize, consider if there are any scenarios where the heaps might become unbalanced and how to fix them.

## Revision Checklist
- [ ] Understand the definition of a median.
- [ ] Recall how to implement max-heaps and min-heaps using `PriorityQueue`.
- [ ] Explain the logic of splitting numbers into two halves.
- [ ] Trace the `addNum` operation with an example.
- [ ] Trace the `findMedian` operation with an example.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Sliding Window Median
*   Kth Largest Element in an Array
*   Find K Pairs with Smallest Sums

## Tags
`Heap` `Two Pointers` `Design` `Data Stream`
