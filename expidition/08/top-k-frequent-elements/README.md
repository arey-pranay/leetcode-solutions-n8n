# Top K Frequent Elements

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Divide and Conquer` `Sorting` `Heap (Priority Queue)` `Bucket Sort` `Counting` `Quickselect`  
**Time:** O(N log K)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Arrays.sort(nums);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->b[1]-a[1]);
        
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
Given an array of integers, find the k most frequent elements.
We can count frequencies, then use a min-heap to keep track of the top k.

## Intuition
The core idea is to efficiently find which elements appear most often. A hash map is a natural fit for counting frequencies. Once we have frequencies, we need a way to select the top `k`. A priority queue (min-heap) is ideal here because we can maintain a heap of size `k`. As we iterate through the frequencies, if the heap size is less than `k`, we add the element. If the heap is full and the current element's frequency is greater than the smallest frequency in the heap (the root of the min-heap), we remove the smallest and add the current one. This ensures the heap always contains the `k` elements with the highest frequencies encountered so far.

## Algorithm
1. Count the frequency of each number in the input array `nums` using a hash map.
2. Create a min-priority queue that stores pairs of `[number, frequency]`. The priority queue should be ordered by frequency in ascending order.
3. Iterate through the entries (key-value pairs) of the frequency map.
4. For each `[number, frequency]` pair:
    a. Add the pair to the priority queue.
    b. If the size of the priority queue exceeds `k`, remove the element with the smallest frequency (the root of the min-heap).
5. After iterating through all frequencies, the priority queue will contain the `k` most frequent elements.
6. Extract the numbers from the priority queue and store them in a result array.
7. Return the result array.

## Concept to Remember
*   **Hash Maps**: Efficiently store and retrieve key-value pairs, ideal for frequency counting.
*   **Priority Queues (Heaps)**: Maintain a collection of elements with priorities, allowing efficient retrieval of the minimum or maximum element. A min-heap is used here to keep track of the *k* largest frequencies.
*   **Sorting**: While not strictly necessary for the core logic, sorting can sometimes simplify frequency counting if elements are grouped together.

## Common Mistakes
*   **Incorrect Priority Queue Ordering**: Using a max-heap instead of a min-heap when trying to keep track of the *k* largest elements. A min-heap of size `k` is used to discard elements with lower frequencies.
*   **Handling Edge Cases**: Not correctly handling cases where `k` is equal to the number of unique elements, or when the input array is empty.
*   **Inefficient Frequency Counting**: Not using a hash map, leading to a less optimal time complexity for counting.
*   **Off-by-One Errors**: Incorrectly managing the size of the priority queue or the loop bounds when extracting results.

## Complexity Analysis
- Time: O(N log K) - reason: O(N) to build the frequency map, and then O(N log K) to iterate through N unique elements and perform heap operations (each taking O(log K)). If K is close to N, this approaches O(N log N).
- Space: O(N) - reason: O(N) for the hash map to store frequencies of up to N unique elements, and O(K) for the priority queue. In the worst case, all elements are unique, so the map takes O(N) space.

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for sorting
import java.util.HashMap; // Import HashMap for frequency counting
import java.util.Map; // Import Map interface
import java.util.PriorityQueue; // Import PriorityQueue for heap operations

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Create a HashMap to store the frequency of each number.
        // Key: number, Value: frequency
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        // Iterate through the input array to count frequencies.
        for (int num : nums) {
            // Get the current count for 'num', default to 0 if not present, and increment.
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Create a Min-Priority Queue.
        // It will store int arrays of size 2: [number, frequency].
        // The comparator (a, b) -> a[1] - b[1] ensures it's a min-heap based on frequency (the second element).
        // We use a min-heap of size K to keep track of the K most frequent elements.
        // If a new element's frequency is higher than the smallest in the heap, we replace it.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        // Iterate through the entries of the frequency map.
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int num = entry.getKey(); // Get the number
            int freq = entry.getValue(); // Get its frequency
            
            // Add the [number, frequency] pair to the min-heap.
            minHeap.offer(new int[]{num, freq});
            
            // If the size of the heap exceeds k, remove the element with the smallest frequency.
            // This ensures the heap always contains at most k elements, and these are the k most frequent seen so far.
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove the root (smallest frequency element)
            }
        }
        
        // Create an array to store the result (the top k frequent elements).
        int[] result = new int[k];
        // Iterate k times to extract the top k elements from the heap.
        for (int i = 0; i < k; i++) {
            // The heap now contains the k elements with the highest frequencies.
            // We extract them one by one. Since it's a min-heap, the smallest of the top k will be at the root.
            // However, we are filling the result array from index 0 to k-1. The order doesn't strictly matter for the problem statement,
            // but typically, you'd want them in descending order of frequency if specified. Here, we just need the elements.
            // The problem asks for *any* k most frequent elements.
            result[i] = minHeap.poll()[0]; // Get the number (first element of the pair) and store it.
        }
        
        // Return the array containing the k most frequent elements.
        return result;
    }
}
```

## Interview Tips
*   **Explain the Trade-offs**: Discuss why a hash map is good for counting and why a priority queue is good for selecting the top K. Mention alternatives like sorting the frequency map (O(N log N)) and why the heap approach is often preferred for its better average time complexity when K << N.
*   **Clarify Constraints**: Ask about the range of numbers, the size of the array, and the value of `k`. This can influence the choice of data structures or algorithms.
*   **Walk Through an Example**: Be prepared to trace your algorithm with a small example array and `k` value. This demonstrates your understanding and helps catch logical errors.
*   **Consider Bucket Sort**: For certain constraints (e.g., frequencies are within a known, limited range), a bucket sort approach can achieve O(N) time complexity. Mentioning this shows broader knowledge.

## Revision Checklist
- [ ] Understand the problem: find k most frequent elements.
- [ ] Frequency Counting: Use a HashMap.
- [ ] Top K Selection: Use a Min-Priority Queue of size K.
- [ ] Heap Logic: Add elements, and if heap size > K, poll.
- [ ] Result Extraction: Poll from the heap K times.
- [ ] Complexity Analysis: Time O(N log K), Space O(N).
- [ ] Edge Cases: Empty array, K=N, K=1.

## Similar Problems
*   Kth Largest Element in an Array
*   Group Anagrams
*   Find All Anagrams in a String
*   Sort Characters By Frequency

## Tags
`Array` `Hash Map` `Priority Queue` `Heap`
