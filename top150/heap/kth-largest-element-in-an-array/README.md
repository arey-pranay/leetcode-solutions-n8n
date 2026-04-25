# Kth Largest Element In An Array

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Divide and Conquer` `Sorting` `Heap (Priority Queue)` `Quickselect`  
**Time:** O(N log K)  
**Space:** O(K)

---

## Solution (java)

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<k;i++) pq.add(nums[i]);
        for(int i=k;i<nums.length;i++){
            if(nums[i] > pq.peek()){
                pq.poll();
                pq.offer(nums[i]);
            }
        }
        return pq.poll();
    }
}
```

---

---
## Quick Revision
Find the k-th largest element in an unsorted array.
Use a min-heap of size k to efficiently track the k largest elements seen so far.

## Intuition
The problem asks for the k-th largest element. If we were to sort the array, it would be the element at index `n - k` (where `n` is the length of the array). However, sorting the entire array might be overkill. We only care about the top `k` elements.

A min-heap is a data structure that always keeps the smallest element at the root. If we maintain a min-heap of size `k`, and iterate through the array:
1. If the heap has fewer than `k` elements, we add the current element.
2. If the heap already has `k` elements, and the current element is *larger* than the smallest element in the heap (which is at the root, `pq.peek()`), it means the current element *could* be among the `k` largest. So, we remove the smallest element from the heap (`pq.poll()`) and add the current element (`pq.offer(nums[i])`).

After processing all elements, the min-heap will contain the `k` largest elements from the array. The smallest element *within this heap* will be the k-th largest element overall.

## Algorithm
1. Initialize a min-priority queue (min-heap).
2. Iterate through the first `k` elements of the input array `nums`. Add each of these elements to the priority queue.
3. Iterate through the remaining elements of the input array `nums` (from index `k` to the end).
4. For each element, compare it with the smallest element currently in the priority queue (which is `pq.peek()`).
5. If the current element is greater than the smallest element in the priority queue:
    a. Remove the smallest element from the priority queue (`pq.poll()`).
    b. Add the current element to the priority queue (`pq.offer(nums[i])`).
6. After iterating through all elements, the smallest element remaining in the priority queue (`pq.poll()`) is the k-th largest element. Return this value.

## Concept to Remember
*   **Priority Queue (Heap):** Efficiently stores elements and allows retrieval of the minimum (or maximum) element in logarithmic time.
*   **Min-Heap Property:** The parent node is always less than or equal to its children.
*   **Maintaining Top K Elements:** Using a heap of fixed size `k` is a common pattern to find the k-th smallest/largest element or the top k elements.

## Common Mistakes
*   **Using a Max-Heap:** If a max-heap is used, it would store the `k` smallest elements, and `pq.peek()` would be the largest among them, not the k-th largest overall.
*   **Incorrectly Handling Heap Size:** Not ensuring the heap is filled to size `k` before starting comparisons, or not correctly replacing elements when a larger one is found.
*   **Off-by-One Errors:** Miscalculating the index for the k-th largest element if sorting was considered, or misinterpreting `k` (e.g., thinking it's 0-indexed).
*   **Not Clearing the Heap:** If the solution were to reuse a heap, forgetting to clear it between calls.

## Complexity Analysis
*   **Time:** O(N log K) - reason: We iterate through all N elements of the array. For each element, we might perform a heap operation (poll and offer), which takes O(log K) time, where K is the size of the heap.
*   **Space:** O(K) - reason: The priority queue stores at most K elements.

## Commented Code
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Initialize a min-priority queue. This will store the k largest elements encountered so far.
        // The smallest element in this min-heap will be the k-th largest overall.
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // First, populate the priority queue with the first k elements.
        // This ensures our heap has a baseline of k elements to compare against.
        for(int i = 0; i < k; i++) {
            // Add the current element to the min-heap.
            pq.add(nums[i]);
        }

        // Now, iterate through the rest of the array, starting from the k-th element.
        for(int i = k; i < nums.length; i++) {
            // Check if the current element is larger than the smallest element in our heap (pq.peek()).
            // If it is, this current element is a candidate for being among the top k largest.
            if(nums[i] > pq.peek()) {
                // Remove the smallest element from the heap. This is because we found a larger element
                // that should take its place to maintain the k largest elements.
                pq.poll();
                // Add the current larger element to the heap.
                pq.offer(nums[i]);
            }
            // If nums[i] is not greater than pq.peek(), it means nums[i] is smaller than or equal to
            // the current k-th largest element, so it cannot be the k-th largest or larger.
            // We simply ignore it and move to the next element.
        }

        // After iterating through all elements, the min-heap contains the k largest elements.
        // The smallest element in this heap (at the root) is precisely the k-th largest element overall.
        // We poll it to retrieve and return this value.
        return pq.poll();
    }
}
```

## Interview Tips
*   **Explain the Heap Choice:** Clearly articulate why a min-heap of size `k` is chosen over sorting or a max-heap.
*   **Walk Through an Example:** Use a small example array and `k` value to trace the algorithm's execution step-by-step.
*   **Discuss Alternatives:** Briefly mention other approaches like QuickSelect (average O(N) time) and their trade-offs (e.g., worst-case O(N^2) for QuickSelect, higher constant factors for heap).
*   **Edge Cases:** Consider cases like `k=1`, `k=nums.length`, or an array with duplicate elements.

## Revision Checklist
- [ ] Understand the problem: find the k-th largest element.
- [ ] Recall the min-heap approach for finding top K elements.
- [ ] Implement the initialization of the min-heap with the first K elements.
- [ ] Implement the iteration and comparison logic for the remaining elements.
- [ ] Ensure correct heap operations (`add`, `poll`, `peek`, `offer`).
- [ ] Verify the final return value is `pq.poll()`.
- [ ] Analyze time and space complexity.
- [ ] Consider alternative solutions (sorting, QuickSelect).

## Similar Problems
*   Find All Numbers Disappeared in an Array
*   Top K Frequent Elements
*   Kth Smallest Element in a Sorted Matrix
*   Merge K Sorted Lists

## Tags
`Array` `Heap` `Priority Queue` `Divide and Conquer` `QuickSelect`
