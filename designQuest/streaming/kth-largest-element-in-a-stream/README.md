# Kth Largest Element In A Stream

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Tree` `Design` `Binary Search Tree` `Heap (Priority Queue)` `Binary Tree` `Data Stream`  
**Time:** O(log k)  
**Space:** O(k)

---

## Solution (java)

```java
class KthLargest {
    int k;
    PriorityQueue<Integer> pq = new PriorityQueue();;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        for(int num : nums) add(num);
    }
    public int add(int val) {
        if(pq.size()<k) pq.offer(val);
        else if(pq.peek() < val){pq.poll(); pq.offer(val);}
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
```

---

---
## Quick Revision
This problem asks to find the k-th largest element in a dynamically growing stream of numbers.
We maintain a min-heap of size k to efficiently track the k largest elements seen so far.

## Intuition
The core idea is that we only care about the *k* largest elements. If we can efficiently keep track of these *k* elements, the smallest among them will be our k-th largest element. A min-heap is perfect for this because its root always holds the smallest element. By maintaining a min-heap of size *k*, the root will always be the k-th largest element encountered.

## Algorithm
1. Initialize a min-priority queue (min-heap) and store the given integer `k`.
2. Iterate through the initial array `nums`. For each number, call the `add` method.
3. The `add` method:
    a. If the priority queue's size is less than `k`, add the new value `val` to the priority queue.
    b. If the priority queue's size is equal to `k` and the new value `val` is greater than the smallest element in the priority queue (which is `pq.peek()`), then remove the smallest element (`pq.poll()`) and add the new value `val` (`pq.offer(val)`).
    c. If the priority queue's size is equal to `k` and `val` is not greater than `pq.peek()`, do nothing.
    d. Return the smallest element in the priority queue (`pq.peek()`), which is the k-th largest element.

## Concept to Remember
*   **Min-Heap Properties**: A min-heap always has the smallest element at the root. This allows for efficient retrieval of the minimum element and efficient insertion/deletion while maintaining the heap property.
*   **Stream Processing**: Handling data that arrives sequentially and needs to be processed without storing the entire history.
*   **Data Structures for Order Statistics**: Understanding which data structures are suitable for finding elements based on their rank (like k-th largest/smallest).

## Common Mistakes
*   Using a max-heap instead of a min-heap: A max-heap would store the largest elements, and its root would be the absolute largest, not the k-th largest.
*   Not handling the initial `nums` array correctly: Forgetting to populate the heap with initial elements before processing stream additions.
*   Inefficiently managing the heap size: Adding elements without removing the smallest when the heap exceeds size `k`.
*   Returning the wrong element: Returning `pq.poll()` instead of `pq.peek()` after an update, which would remove the k-th largest element.

## Complexity Analysis
*   Time: O(log k) for `add` operation. Each `add` operation involves at most one `offer` and one `poll` on the priority queue, both of which take O(log k) time since the heap size is capped at `k`. The constructor takes O(N log k) where N is the length of `nums`, as it calls `add` for each element.
*   Space: O(k) to store up to `k` elements in the priority queue.

## Commented Code
```java
class KthLargest {
    // Store the target k value
    int k;
    // Initialize a min-priority queue to store the k largest elements
    // A min-heap is used so that the smallest element among the k largest is always at the top (peek)
    PriorityQueue<Integer> pq = new PriorityQueue();;

    // Constructor: initializes the KthLargest object
    // k: the desired rank (k-th largest)
    // nums: the initial array of numbers
    public KthLargest(int k, int[] nums) {
        // Assign the given k to the instance variable
        this.k = k;
        // Process each number in the initial array by adding it to the stream
        for(int num : nums) {
            // Call the add method to maintain the k largest elements
            add(num);
        }
    }

    // Adds a new value to the stream and returns the k-th largest element
    // val: the new integer to add to the stream
    public int add(int val) {
        // If the priority queue has fewer than k elements, simply add the new value
        // This ensures we are building up to k elements initially
        if(pq.size()<k) {
            pq.offer(val);
        }
        // If the priority queue already has k elements, we need to check if the new value is larger than the smallest of the current k largest elements
        // pq.peek() returns the smallest element in the min-heap (which is the current k-th largest)
        else if(pq.peek() < val){
            // If the new value is larger, it means it should be among the k largest
            // So, remove the smallest element from the heap (pq.poll())
            pq.poll();
            // And add the new, larger value to the heap (pq.offer(val))
            pq.offer(val);
        }
        // After potentially updating the heap, the smallest element in the heap (pq.peek()) is the k-th largest element seen so far
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
```

## Interview Tips
*   Clearly explain why a min-heap of size `k` is the optimal data structure.
*   Walk through the `add` method logic step-by-step, especially the conditions for adding and removing elements.
*   Be prepared to discuss the time and space complexity of both the constructor and the `add` method.
*   If asked, consider alternative approaches (e.g., sorting the entire stream, which is inefficient) and explain why they are less suitable.

## Revision Checklist
- [ ] Understand the problem statement: k-th largest in a stream.
- [ ] Identify the core data structure: min-heap.
- [ ] Explain the logic for maintaining heap size `k`.
- [ ] Trace the `add` method with examples.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty initial array, `k=1`).

## Similar Problems
*   Find K Pairs with Smallest Sums
*   Top K Frequent Elements
*   Kth Smallest Element in a Sorted Matrix

## Tags
`Heap` `Priority Queue` `Data Stream`
