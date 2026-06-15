# Kth Largest Element In A Stream

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Tree` `Design` `Binary Search Tree` `Heap (Priority Queue)` `Binary Tree` `Data Stream`  
**Time:**   
**Space:** O(K)

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
The core idea is that we only care about the *k* largest elements at any given time. If we can efficiently keep track of these *k* elements, the smallest among them will be our k-th largest element. A min-heap is perfect for this because its root always holds the smallest element. By maintaining a min-heap of size *k*, the root will always be the k-th largest element.

## Algorithm
1.  **Initialization (`KthLargest(int k, int[] nums)`):**
    *   Store the given integer `k`.
    *   Initialize an empty min-priority queue (`pq`).
    *   Iterate through the initial array `nums`. For each number, call the `add` method to populate the priority queue.

2.  **Adding a new element (`add(int val)`):**
    *   **Case 1: Priority queue size is less than `k`:**
        *   Add the new value `val` to the priority queue.
    *   **Case 2: Priority queue size is equal to `k`:**
        *   Compare `val` with the smallest element in the priority queue (which is `pq.peek()`).
        *   If `val` is greater than `pq.peek()`:
            *   Remove the smallest element from the priority queue (`pq.poll()`).
            *   Add the new value `val` to the priority queue (`pq.offer(val)`).
        *   If `val` is not greater than `pq.peek()`, do nothing (the new element is not among the top k largest).
    *   **Return:** Return the smallest element in the priority queue (`pq.peek()`), which is the k-th largest element seen so far.

## Concept to Remember
*   **Priority Queue (Min-Heap):** A data structure that allows efficient retrieval of the minimum element. In this case, we use it to keep track of the *k* largest elements, where the minimum of these *k* is the k-th largest overall.
*   **Stream Processing:** Handling data that arrives sequentially and continuously, often without knowing the total size beforehand.
*   **Maintaining Top K Elements:** A common pattern where you need to find the largest (or smallest) *k* elements from a dynamic set.

## Common Mistakes
*   **Using a Max-Heap:** A max-heap would store the *k* smallest elements, making it difficult to find the k-th largest.
*   **Not handling the initial `nums` array correctly:** Forgetting to process the initial elements or processing them inefficiently.
*   **Incorrectly comparing `val` with `pq.peek()`:** Not performing the comparison or performing it in the wrong direction (e.g., `val < pq.peek()`).
*   **Not maintaining the size `k` constraint:** Allowing the priority queue to grow beyond size `k` when it's not necessary.

## Complexity Analysis
*   **Time:**
    *   Constructor: O(N log K), where N is the number of elements in `nums`. Each `add` operation takes O(log K).
    *   `add` method: O(log K). Adding an element to a priority queue of size K takes logarithmic time. Polling and offering also take O(log K).
*   **Space:** O(K), to store up to K elements in the priority queue.

## Commented Code
```java
class KthLargest {
    // Store the target k value
    int k;
    // Initialize a min-priority queue to store the k largest elements
    // The smallest element in this PQ will be the k-th largest overall
    PriorityQueue<Integer> pq = new PriorityQueue();;

    // Constructor to initialize the object with k and initial numbers
    public KthLargest(int k, int[] nums) {
        // Assign the given k to the instance variable
        this.k = k;
        // Iterate through the initial array of numbers
        for(int num : nums) {
            // Add each initial number to the stream using the add method
            // This ensures the priority queue is populated correctly from the start
            add(num);
        }
    }

    // Method to add a new value to the stream and return the k-th largest element
    public int add(int val) {
        // If the priority queue currently holds fewer than k elements
        if(pq.size()<k) {
            // Simply add the new value to the priority queue
            pq.offer(val);
        }
        // If the priority queue already has k elements
        else if(pq.peek() < val){
            // And the new value is larger than the smallest element in the PQ (pq.peek())
            // This means the new value belongs in the top k largest elements
            // Remove the smallest element from the PQ
            pq.poll();
            // Add the new, larger value to the PQ
            pq.offer(val);
        }
        // If the new value is not larger than pq.peek(), it's not among the top k, so we do nothing.

        // After potentially adding or replacing an element, the smallest element in the PQ (pq.peek())
        // is guaranteed to be the k-th largest element seen so far.
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
*   **Explain the Min-Heap Choice:** Clearly articulate *why* a min-heap of size `k` is the optimal choice for this problem. Contrast it with a max-heap.
*   **Walk Through `add` Logic:** Verbally explain the two main cases in the `add` method: when the heap is not full and when it is full.
*   **Edge Cases:** Discuss what happens if `nums` is empty, or if `k` is 1.
*   **Complexity Justification:** Be ready to explain the time and space complexity of both the constructor and the `add` method.

## Revision Checklist
- [ ] Understand the problem statement: finding the k-th largest in a stream.
- [ ] Recall the properties of a min-heap.
- [ ] Implement the constructor to initialize `k` and the priority queue.
- [ ] Implement the `add` method with the two main logic branches (heap size < k, heap size == k).
- [ ] Correctly use `pq.offer()`, `pq.poll()`, and `pq.peek()`.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases.

## Similar Problems
*   Find K Pairs with Smallest Sums
*   Top K Frequent Elements
*   Kth Smallest Element in a Sorted Matrix

## Tags
`Heap` `PriorityQueue` `Design`
