# Find K Pairs With Smallest Sums

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Heap (Priority Queue)`  
**Time:** O(k log(min(k, n1)  
**Space:** O(min(k, n1)

---

## Solution (java)

```java
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> arr = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        for(int i=0;i<Math.min(k,nums1.length);i++) pq.offer(new int[]{nums1[i]+nums2[0],i,0});
        for(int I=0;I<k;I++){
            int[] group = pq.poll();
            int i = group[1];
            int j = group[2];
            List<Integer> l = new ArrayList<>();
            l.add(nums1[i]);
            l.add(nums2[j]);
            arr.add(l);
            if(j<nums2.length-1)pq.offer(new int[]{nums1[i]+nums2[j+1], i, j+1});
        }
        return arr;
    }
}

// [1,2,4,5,6]
// [3,5,7,9]

// sum, i, j
// 1+3,0,0
// 2+3,1,0
// 4+3,2,0
// 5+3,3,0
// 6+3,4,0


// 4,0,0


// 13,0 15,1 17,2 19,3
// 23 15 17 19 
// 15 25 17 19 

// k break;
// our: [[1,3],[2,3],[4,3]]
// exp: [[1,3],[2,3],[1,5]]

```

---

---
## Quick Revision
This problem asks for the `k` pairs with the smallest sums formed by picking one element from `nums1` and one from `nums2`.
We use a min-heap to efficiently track and extract the pairs with the smallest sums.

## Intuition
The core idea is that if we have a pair `(nums1[i], nums2[j])` with a small sum, then the next smallest sum involving `nums1[i]` would likely be `(nums1[i], nums2[j+1])`. Similarly, if we consider `nums1[i+1]`, the smallest sum involving it would be `(nums1[i+1], nums2[0])`. This suggests a way to explore potential pairs systematically. A min-heap is perfect for keeping track of the "frontier" of smallest sums we've encountered so far and efficiently picking the absolute smallest one. We can initialize the heap with pairs formed by the first element of `nums2` and each element of `nums1` (up to `k` elements from `nums1`). When we extract a pair `(nums1[i], nums2[j])`, we then consider adding the next potential pair `(nums1[i], nums2[j+1])` to the heap.

## Algorithm
1. Initialize an empty list `result` to store the `k` smallest pairs.
2. Initialize a min-priority queue `pq`. The priority queue will store arrays of size 3: `[sum, index_in_nums1, index_in_nums2]`. The priority will be based on the `sum`.
3. Iterate through `nums1` from index `i = 0` up to `min(k, nums1.length - 1)`. For each `i`:
    a. Add a new element `[nums1[i] + nums2[0], i, 0]` to the priority queue. This represents the pair `(nums1[i], nums2[0])`.
4. While the `result` list has fewer than `k` pairs and the priority queue is not empty:
    a. Extract the element with the smallest sum from the priority queue. Let this be `[current_sum, i, j]`.
    b. Create a new list containing `nums1[i]` and `nums2[j]`.
    c. Add this list to the `result`.
    d. If `j + 1` is a valid index in `nums2` (i.e., `j + 1 < nums2.length`):
        i. Add a new element `[nums1[i] + nums2[j+1], i, j+1]` to the priority queue. This represents the next potential pair involving `nums1[i]`.
5. Return the `result` list.

## Concept to Remember
*   **Min-Heap (Priority Queue):** Essential for efficiently retrieving the minimum element among a dynamic set of candidates.
*   **Exploration Strategy:** Systematically exploring potential pairs by advancing the index in `nums2` for a fixed `nums1` element.
*   **Bounded Search:** Limiting the initial exploration to `k` elements from `nums1` and the total number of pairs extracted to `k`.

## Common Mistakes
*   **Incorrect Heap Initialization:** Not considering the initial pairs correctly, e.g., not starting with `nums2[0]` for all initial `nums1` elements.
*   **Infinite Loop/Missing Termination:** Not having a proper condition to stop extracting pairs (e.g., `result.size() < k` and `!pq.isEmpty()`).
*   **Index Out of Bounds:** Incorrectly handling the `j+1` index when adding new elements to the heap, especially when `j` is the last index of `nums2`.
*   **Not Handling Empty Inputs:** Although not explicitly stated in typical LeetCode constraints, robust code would consider empty `nums1` or `nums2`.

## Complexity Analysis
*   **Time:** O(k log(min(k, n1))), where n1 is the length of `nums1`.
    *   We initially push up to `min(k, n1)` elements into the heap. Each push takes O(log(min(k, n1))).
    *   We then perform `k` extractions and at most `k` insertions. Each operation takes O(log(min(k, n1))).
    *   Therefore, the total time complexity is dominated by these heap operations.
*   **Space:** O(min(k, n1)).
    *   The space is primarily used by the priority queue, which stores at most `min(k, n1)` elements at any given time.

## Commented Code
```java
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // Initialize the list to store the resulting k smallest pairs.
        List<List<Integer>> arr = new ArrayList<>();
        
        // Initialize a min-priority queue. It will store int arrays of size 3: [sum, index_in_nums1, index_in_nums2].
        // The comparator (a,b) -> a[0]-b[0] ensures it's a min-heap based on the sum (the first element).
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        
        // Initial population of the priority queue.
        // We iterate through nums1 up to min(k, nums1.length) because we only need at most k pairs,
        // and we pair each of these nums1 elements with the smallest element of nums2 (nums2[0]).
        for(int i = 0; i < Math.min(k, nums1.length); i++) {
            // Offer a new element to the priority queue: [sum of nums1[i] and nums2[0], index i in nums1, index 0 in nums2].
            pq.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }
        
        // Main loop to extract k smallest pairs.
        // We continue as long as we need more pairs (I < k) and there are still potential pairs in the priority queue.
        for(int I = 0; I < k; I++) {
            // If the priority queue is empty, we cannot form any more pairs, so we break.
            if (pq.isEmpty()) {
                break;
            }
            
            // Poll (extract) the element with the smallest sum from the priority queue.
            int[] group = pq.poll();
            // Extract the index in nums1 and index in nums2 from the polled element.
            int i = group[1]; // index in nums1
            int j = group[2]; // index in nums2
            
            // Create a new list to represent the current pair.
            List<Integer> l = new ArrayList<>();
            // Add the elements from nums1 and nums2 that form this pair.
            l.add(nums1[i]);
            l.add(nums2[j]);
            // Add this pair to our result list.
            arr.add(l);
            
            // If there is a next element in nums2 for the current nums1[i],
            // we add the next potential pair to the priority queue.
            if(j < nums2.length - 1) {
                // Offer the next pair: [sum of nums1[i] and nums2[j+1], index i in nums1, index j+1 in nums2].
                pq.offer(new int[]{nums1[i] + nums2[j+1], i, j+1});
            }
        }
        // Return the list containing the k smallest pairs.
        return arr;
    }
}
```

## Interview Tips
*   **Explain the Heap Logic:** Clearly articulate why a min-heap is suitable and how it helps maintain the smallest sums efficiently.
*   **Trace an Example:** Walk through a small example (like the one provided in the comments) to demonstrate your understanding of the algorithm's step-by-step execution.
*   **Discuss Edge Cases:** Mention how you'd handle cases where `k` is larger than the total number of possible pairs, or when `nums1` or `nums2` are empty.
*   **Complexity Justification:** Be prepared to explain the time and space complexity, justifying each part of the calculation.

## Revision Checklist
- [ ] Understand the problem statement: find k pairs with smallest sums.
- [ ] Recognize the need for an efficient way to explore pairs.
- [ ] Implement a min-priority queue to store `[sum, index1, index2]`.
- [ ] Correctly initialize the priority queue with `(nums1[i], nums2[0])` for relevant `i`.
- [ ] Implement the loop to extract `k` pairs.
- [ ] When a pair `(nums1[i], nums2[j])` is extracted, add `(nums1[i], nums2[j+1])` to the PQ if `j+1` is valid.
- [ ] Handle the termination condition (`k` pairs found or PQ empty).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Merge K Sorted Lists
*   Kth Smallest Element in a Sorted Matrix
*   Find K Closest Elements

## Tags
`Array` `Heap` `Priority Queue` `Two Pointers`
