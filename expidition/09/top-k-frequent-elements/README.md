# Top K Frequent Elements

**Difficulty:** Medium  
**Language:** Javascript  
**Tags:** `Array` `Hash Table` `Divide and Conquer` `Sorting` `Heap (Priority Queue)` `Bucket Sort` `Counting` `Quickselect`  
**Time:** O(N log k)  
**Space:** O(N)

---

## Solution (javascript)

```javascript
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Arrays.sort(nums);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->b[1]-a[1]);
        // priority  queue kehta hai jiski sbse jyada priority hogi vo sabse pehle bahr jaayega 
        // andr koi kese bhi aayaha ho koi wanada nai 
        // aur ye priority usme andr jo lambda function hai usse tay hota hai 
        //
        int num = nums[0];
        int freq = 1;
        
        for(int i=1;i<nums.length;i++){
            if(nums[i]==num) freq++;
            else{
                pq.add(new int[]{num,freq});
                num = nums[i];
                freq = 1;
            }
        }
        pq.add(new int[]{num,freq});
        
        int[] ans = new int[k];
        for(int i=0;i<k;i++) ans[i] = pq.remove()[0];
        return ans;
    }
}
```

---

---
## Quick Revision
Given an array of integers `nums` and an integer `k`, return the `k` most frequent elements.
This problem can be solved by counting frequencies and then using a data structure to efficiently retrieve the top `k` elements.

## Intuition
The core idea is to first count how many times each number appears in the input array. Once we have these frequencies, we need a way to quickly identify the numbers with the highest counts. A min-heap (or a max-heap) is a natural fit for this. If we use a min-heap of size `k`, we can iterate through all the frequency pairs. For each pair, if the heap size is less than `k`, we add it. If the heap is full, we compare the current frequency with the smallest frequency in the heap (the root of the min-heap). If the current frequency is greater, we remove the smallest element from the heap and insert the current one. This ensures that the heap always contains the `k` elements with the highest frequencies encountered so far.

## Algorithm
1. **Count Frequencies:** Create a hash map (or dictionary) to store the frequency of each number in the input array `nums`. Iterate through `nums`, and for each number, increment its count in the map.
2. **Populate Heap:** Initialize a min-priority queue. The priority queue will store pairs of `[number, frequency]`. The comparison for the priority queue should be based on the frequency (the second element of the pair).
3. **Iterate and Maintain Heap:** Iterate through the entries (key-value pairs) of the frequency map. For each `[number, frequency]` pair:
    a. Add the pair to the priority queue.
    b. If the size of the priority queue exceeds `k`, remove the element with the smallest frequency (the root of the min-heap).
4. **Extract Result:** After processing all frequency pairs, the priority queue will contain the `k` most frequent elements. Create a result array of size `k`. Extract the numbers (the first element of each pair) from the priority queue `k` times and store them in the result array.
5. **Return Result:** Return the result array.

## Concept to Remember
*   **Hash Maps/Dictionaries:** Efficiently storing and retrieving key-value pairs, ideal for frequency counting.
*   **Priority Queues (Heaps):** Maintaining an ordered collection and efficiently retrieving the minimum or maximum element. Specifically, a min-heap of size `k` is used to keep track of the `k` largest elements.
*   **Sorting (Implicitly or Explicitly):** While not always the most efficient, sorting can be a precursor to frequency counting or a way to group identical elements.
*   **Time-Space Trade-offs:** Choosing data structures that balance computational time with memory usage.

## Common Mistakes
*   **Incorrect Heap Ordering:** Using a max-heap when a min-heap of size `k` is more appropriate for keeping track of the *top* `k` elements.
*   **Not Handling Edge Cases:** Forgetting to handle cases where `k` is equal to the number of unique elements, or when the input array is empty.
*   **Inefficient Frequency Counting:** Not using a hash map, leading to slower O(N log N) or O(N^2) frequency counting.
*   **Off-by-One Errors:** Incorrectly managing the size of the priority queue or the loop bounds when extracting results.
*   **Not Clearing/Resetting Data Structures:** If the solution is part of a larger system or class, forgetting to clear maps or heaps between calls can lead to incorrect results.

## Complexity Analysis
- Time: O(N log k) - reason: O(N) to build the frequency map, and then O(N log k) to iterate through the N unique elements and perform heap operations (insertion/deletion takes O(log k) time). If k is close to N, this approaches O(N log N).
- Space: O(N) - reason: O(N) for the hash map to store frequencies of up to N unique elements, and O(k) for the priority queue. In the worst case, all elements are unique, so the map takes O(N) space.

## Commented Code
```javascript
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Create a hash map to store the frequency of each number.
        // The key will be the number, and the value will be its frequency.
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        // Iterate through the input array 'nums'.
        for (int num : nums) {
            // For each number, update its frequency in the map.
            // getOrDefault(key, defaultValue) returns the value for the key if it exists,
            // otherwise it returns the defaultValue (0 in this case).
            // Then, we add 1 to the retrieved frequency and put it back into the map.
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Step 2: Initialize a min-priority queue.
        // The priority queue will store arrays of size 2: [number, frequency].
        // The lambda expression (a, b) -> a[1] - b[1] defines the comparison logic.
        // It's a min-heap based on the frequency (the second element of the array).
        // This means the element with the smallest frequency will be at the top (root).
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        // Step 3: Iterate through the frequency map and populate the min-heap.
        // For each entry (number and its frequency) in the map:
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            // Get the current number and its frequency.
            int num = entry.getKey();
            int freq = entry.getValue();
            
            // Add the [number, frequency] pair to the min-heap.
            minHeap.offer(new int[]{num, freq});
            
            // If the size of the min-heap exceeds 'k', it means we have more than 'k' elements.
            // We need to remove the element with the smallest frequency to maintain only the top 'k'.
            if (minHeap.size() > k) {
                // poll() removes and returns the head of the queue (the element with the smallest frequency).
                minHeap.poll();
            }
        }
        
        // Step 4: Extract the top 'k' frequent elements from the min-heap.
        // Create a result array of size 'k'.
        int[] result = new int[k];
        // Iterate 'k' times to extract the 'k' elements.
        for (int i = 0; i < k; i++) {
            // poll() removes and returns the element with the smallest frequency from the heap.
            // Since we maintained the heap to have the top 'k' elements, and we are polling,
            // we are effectively getting the elements in increasing order of frequency among the top k.
            // However, the problem only asks for the elements, not their order.
            // We take the number (the first element of the pair) and store it in the result array.
            result[i] = minHeap.poll()[0];
        }
        
        // Step 5: Return the result array containing the k most frequent elements.
        return result;
    }
}
```

## Interview Tips
*   **Explain the Trade-offs:** Be prepared to discuss why a hash map and a priority queue are good choices, and what alternatives exist (e.g., sorting the frequency map, using a bucket sort approach).
*   **Clarify Constraints:** Ask about the range of numbers, the size of `nums`, and the value of `k`. This can influence the optimal approach (e.g., if numbers are within a small range, bucket sort might be better).
*   **Walk Through an Example:** Verbally walk through a small example array and `k` value to demonstrate your understanding of the algorithm and how the data structures are used.
*   **Focus on the Heap Logic:** The core of this problem is efficiently managing the top `k` elements. Clearly articulate how the min-heap of size `k` works to achieve this.

## Revision Checklist
- [ ] Understand the problem statement: find `k` most frequent elements.
- [ ] Implement frequency counting using a hash map.
- [ ] Choose the correct priority queue type (min-heap of size `k`).
- [ ] Implement the logic for adding to and maintaining the heap's size.
- [ ] Extract the final `k` elements from the heap.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty array, `k` equals unique elements).

## Similar Problems
*   Sort Characters By Frequency
*   Kth Largest Element in an Array
*   Top K Frequent Words

## Tags
`Array` `Hash Map` `Priority Queue` `Heap`

## My Notes
Max Heap Implemented. Nice solution
