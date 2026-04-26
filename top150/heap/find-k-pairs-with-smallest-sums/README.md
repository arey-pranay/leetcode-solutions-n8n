# Find K Pairs With Smallest Sums

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Heap (Priority Queue)`  
**Time:** O(k * log(min(k, m)  
**Space:** O(min(k, m)

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
We use a min-heap to efficiently find the smallest sums by exploring pairs in increasing order of their sums.

## Intuition
The core idea is that if we have a pair `(nums1[i], nums2[j])` with a small sum, then the next potential smallest sum involving `nums1[i]` would be `(nums1[i], nums2[j+1])` (if `j+1` is within bounds). Similarly, if we consider pairs involving `nums2[j]`, the next smallest sum might come from `(nums1[i+1], nums2[j])`.

Since we want the *k* smallest sums, we can think of this as a search problem. We start with the smallest possible sums and expand outwards. The smallest sum must involve `nums1[0]` and `nums2[0]`. After picking `(nums1[i], nums2[j])`, the next candidates for smallest sums are `(nums1[i+1], nums2[j])` and `(nums1[i], nums2[j+1])`.

A min-priority queue is perfect for this. We can store potential pairs (or rather, their sums and indices) in the priority queue, ordered by sum. We initially seed the priority queue with pairs formed by `nums1[i]` and `nums2[0]` for the first `k` elements of `nums1`. Then, we repeatedly extract the smallest sum pair, add it to our result, and if the extracted pair was `(nums1[i], nums2[j])`, we add the next potential pair `(nums1[i], nums2[j+1])` to the priority queue. This ensures we always consider the next smallest sum.

## Algorithm
1. Initialize an empty list `result` to store the `k` smallest pairs.
2. Initialize a min-priority queue `pq`. The priority queue will store arrays of size 3: `[sum, index_in_nums1, index_in_nums2]`. The comparator for the priority queue should prioritize smaller sums.
3. Iterate through the first `min(k, nums1.length)` elements of `nums1`. For each element `nums1[i]`:
    a. Add a new entry to the priority queue: `[nums1[i] + nums2[0], i, 0]`. This represents the pair `(nums1[i], nums2[0])`.
4. Loop `k` times (or until the priority queue is empty):
    a. Extract the element with the smallest sum from the priority queue. Let this be `[current_sum, i, j]`.
    b. Create a new list containing `nums1[i]` and `nums2[j]`.
    c. Add this list to the `result`.
    d. If `j + 1` is less than `nums2.length` (meaning there's a next element in `nums2` for `nums1[i]`):
        i. Add a new entry to the priority queue: `[nums1[i] + nums2[j+1], i, j+1]`. This represents the pair `(nums1[i], nums2[j+1])`.
5. Return the `result` list.

## Concept to Remember
*   **Min-Heap (Priority Queue):** Efficiently retrieving the minimum element and maintaining order.
*   **Greedy Approach:** At each step, we make the locally optimal choice (picking the smallest sum pair) hoping it leads to a globally optimal solution.
*   **Exploration Strategy:** Systematically exploring potential candidates for the smallest sums.

## Common Mistakes
*   **Incorrect Priority Queue Initialization:** Not seeding the PQ with initial valid pairs or using the wrong comparator.
*   **Duplicate Pair Generation:** Adding `(nums1[i+1], nums2[j])` and `(nums1[i], nums2[j+1])` without proper checks can lead to duplicates or missing pairs. The chosen approach avoids this by only advancing the `nums2` index.
*   **Off-by-One Errors:** Incorrectly handling array bounds for `nums1` and `nums2`.
*   **Not Handling Empty Inputs:** The code should ideally handle cases where `nums1` or `nums2` are empty, though LeetCode constraints might prevent this.
*   **Inefficiently Generating Pairs:** Trying to generate all `m*n` pairs first and then sorting is too slow.

## Complexity Analysis
*   **Time:** O(k * log(min(k, m))), where `m` is the length of `nums1`.
    *   We initially add up to `min(k, m)` elements to the priority queue, each taking O(log(min(k, m))) time.
    *   We then perform `k` extractions and up to `k` insertions. Each operation takes O(log(min(k, m))) time.
    *   The size of the priority queue is at most `min(k, m)`.
*   **Space:** O(min(k, m))
    *   The space is dominated by the priority queue, which stores at most `min(k, m)` elements.

## Commented Code
```java
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // Initialize the list to store the resulting k smallest pairs.
        List<List<Integer>> result = new ArrayList<>();
        
        // Initialize a min-priority queue.
        // It stores int arrays of size 3: [sum, index_in_nums1, index_in_nums2].
        // The lambda expression (a,b) -> a[0]-b[0] defines the comparator to sort by the sum (the first element).
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0]-b[0]);
        
        // Seed the priority queue with initial pairs.
        // We consider pairs formed by each element of nums1 with the first element of nums2 (nums2[0]).
        // We only need to consider up to k elements from nums1 because if k is smaller than nums1.length,
        // any pair involving nums1[k] or beyond will likely have a larger sum than the k smallest pairs.
        for(int i = 0; i < Math.min(k, nums1.length); i++) {
            // Offer a new entry to the priority queue: [sum, index_of_nums1_element, index_of_nums2_element (which is 0)].
            pq.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }
        
        // Loop k times to extract the k smallest pairs.
        // The loop also ensures we don't try to extract more pairs than available or requested.
        for(int count = 0; count < k; count++) {
            // If the priority queue is empty, we have found all possible pairs or fewer than k pairs.
            if (pq.isEmpty()) {
                break; // Exit the loop if no more pairs can be formed.
            }
            
            // Poll (extract) the pair with the smallest sum from the priority queue.
            int[] currentPairInfo = pq.poll();
            
            // Extract the indices from the polled information.
            int index1 = currentPairInfo[1]; // Index in nums1
            int index2 = currentPairInfo[2]; // Index in nums2
            
            // Create a new list to represent the current pair.
            List<Integer> pair = new ArrayList<>();
            // Add the elements from nums1 and nums2 that form this pair.
            pair.add(nums1[index1]);
            pair.add(nums2[index2]);
            
            // Add the formed pair to our result list.
            result.add(pair);
            
            // If there is a next element in nums2 for the current nums1 element (nums1[index1]),
            // we should add the next potential pair to the priority queue.
            // This is the core of the exploration strategy: if (nums1[i], nums2[j]) was the smallest,
            // then (nums1[i], nums2[j+1]) is the next candidate involving nums1[i].
            if (index2 + 1 < nums2.length) {
                // Offer the next potential pair to the priority queue:
                // [sum of (nums1[index1], nums2[index2+1]), index_of_nums1_element, next_index_of_nums2_element].
                pq.offer(new int[]{nums1[index1] + nums2[index2 + 1], index1, index2 + 1});
            }
        }
        
        // Return the list containing the k smallest pairs.
        return result;
    }
}
```

## Interview Tips
*   **Explain the PQ Logic:** Clearly articulate why a min-priority queue is suitable and how it helps explore pairs efficiently.
*   **Trace an Example:** Walk through a small example like `nums1 = [1, 7, 11]`, `nums2 = [2, 4, 6]`, `k = 3` to demonstrate the algorithm's steps and PQ state.
*   **Discuss Edge Cases:** Mention how you handle `k` being larger than the total number of possible pairs, or when `nums1` or `nums2` are empty.
*   **Complexity Justification:** Be ready to explain the time and space complexity derivation.

## Revision Checklist
- [ ] Understand the problem statement: find k pairs with smallest sums.
- [ ] Identify the need for an efficient way to find minimums.
- [ ] Recognize the suitability of a min-priority queue.
- [ ] Implement the PQ with correct comparator.
- [ ] Correctly seed the PQ with initial pairs.
- [ ] Implement the loop to extract k pairs.
- [ ] Correctly add the next candidate pair to the PQ.
- [ ] Handle array bounds and empty PQ conditions.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Merge K Sorted Lists
*   Find Median from Data Stream
*   Kth Smallest Element in a Sorted Matrix

## Tags
`Array` `Heap` `Priority Queue` `Two Pointers`
