# Sliding Window Median

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Sliding Window` `Heap (Priority Queue)` `Treap`  
**Time:** O(N log K)  
**Space:** O(K)

---

## Solution (java)

```java
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n==1) return new double[]{nums[0]};
        int m = n-k+1;
        int j=0;
        double[] ans = new double[m];
        Comparator<Integer> comparator = (a,b) -> nums[a]==nums[b] ? Integer.compare(a,b) : Integer.compare(nums[a],nums[b]);
        TreeSet<Integer> smalls = new TreeSet<>(comparator.reversed()); // because we need to manually remove using index, but treeset should sort and respond based on nums[]
        TreeSet<Integer> bigs = new TreeSet<>(comparator);
        for(int i=0;i<n;i++){
          if(i>=k)remove(smalls,bigs,i-k);
          add(smalls,bigs,i);
          if(i>=k-1)ans[j++] = median(smalls,bigs,nums,k%2==0);
        }
        return ans;
    }
    public void remove(TreeSet<Integer> smalls,TreeSet<Integer> bigs,int index){
      if(smalls.contains(index)){
        smalls.remove(index);
        if(smalls.size()<bigs.size()) smalls.add(bigs.pollFirst());
      } else {
         bigs.remove(index);
         if(bigs.size()<smalls.size()) bigs.add(smalls.pollFirst());
      }    
    }
    public void add(TreeSet<Integer> smalls,TreeSet<Integer> bigs,int index){
      smalls.add(index);
      bigs.add(smalls.pollFirst());
      if(bigs.size()>smalls.size())smalls.add(bigs.pollFirst());
    }
    public double median(TreeSet<Integer> smalls,TreeSet<Integer> bigs, int[] nums, boolean isEven){
      double a = nums[smalls.first()];
      if(smalls.size()==bigs.size()) return (double)(a+nums[bigs.first()])/2.0;
      else return a;
    }
}
```

---

---
## Quick Revision
This problem asks for the median of all subarrays of a fixed size `k` as the window slides across an array.
We solve it using two balanced heaps (or `TreeSet`s in this case) to maintain the smaller and larger halves of the current window's elements.

## Intuition
The core idea is to efficiently find the median of a dynamic set of numbers. A median divides a sorted set into two halves. If we can maintain two data structures that represent these two halves, we can quickly find the median. Specifically, we want a structure for the smaller half and a structure for the larger half. The median will either be the largest element of the smaller half (if the total count is odd) or the average of the largest element of the smaller half and the smallest element of the larger half (if the total count is even).

When the window slides, we need to add a new element and remove an old element. This is where balanced data structures come in. `TreeSet`s in Java, when used with a custom comparator, can act like balanced binary search trees, allowing efficient insertion, deletion, and retrieval of min/max elements. By storing indices and comparing values from the original `nums` array, we can effectively manage the elements within the window.

The key is to keep the sizes of the two `TreeSet`s balanced (or nearly balanced). If the total number of elements `k` is odd, one set will have one more element than the other. If `k` is even, they will have equal sizes.

## Algorithm
1.  **Initialization**:
    *   Create an array `ans` to store the medians, with size `n - k + 1`.
    *   Initialize two `TreeSet`s: `smalls` to store indices of elements in the smaller half and `bigs` to store indices of elements in the larger half.
    *   Define a custom `Comparator` for the `TreeSet`s. This comparator should sort indices based on the values in the `nums` array. `smalls` will use a reversed comparator to effectively act as a max-heap (storing the largest of the small half at the top), and `bigs` will use a standard comparator to act as a min-heap (storing the smallest of the large half at the top). The comparator also handles duplicate values by comparing indices to ensure uniqueness in the `TreeSet`.

2.  **Sliding Window Iteration**:
    *   Iterate through the `nums` array from `i = 0` to `n - 1`.
    *   **Removal**: If `i >= k`, it means the window has moved past the element at index `i - k`. Remove the index `i - k` from either `smalls` or `bigs`. After removal, rebalance the sets if necessary by moving an element from the larger set to the smaller set if the size difference becomes too large.
    *   **Addition**: Add the current index `i` to the `smalls` set. Then, move the largest element from `smalls` to `bigs` to maintain the property that all elements in `smalls` are less than or equal to all elements in `bigs`. Finally, rebalance the sets: if `bigs` has more elements than `smalls`, move the smallest element from `bigs` to `smalls`.
    *   **Median Calculation**: If `i >= k - 1`, the window is full. Calculate the median using the `median` helper function and store it in `ans[j++]`.

3.  **Median Calculation Helper (`median`)**:
    *   If `k` is even, the median is the average of the largest element in `smalls` (accessed via `nums[smalls.first()]`) and the smallest element in `bigs` (accessed via `nums[bigs.first()]`).
    *   If `k` is odd, the median is the largest element in `smalls` (accessed via `nums[smalls.first()]`).

4.  **Return**: Return the `ans` array.

## Concept to Remember
*   **Two Heaps/Balanced BSTs for Median**: Maintaining two heaps (or balanced BSTs like `TreeSet`) is a standard technique for efficiently finding the median of a dynamic set of numbers. One heap stores the smaller half, and the other stores the larger half.
*   **Sliding Window Technique**: This problem involves processing a fixed-size window that moves across a data structure, requiring efficient addition and removal of elements.
*   **Custom Comparators**: When using data structures like `TreeSet` or `PriorityQueue` to store custom objects or to sort based on external data (like values in another array), custom comparators are essential.
*   **Index Management**: In this specific solution, storing indices in `TreeSet`s is crucial because `TreeSet` requires unique elements. If we stored values directly, duplicate values would cause issues with removal. Using indices allows us to uniquely identify elements even if their values are the same.

## Common Mistakes
*   **Incorrect Comparator Logic**: Errors in the custom comparator can lead to `TreeSet`s not being sorted correctly, resulting in wrong median calculations. For example, not handling duplicate values by comparing indices can cause issues.
*   **Improper Rebalancing**: Failing to rebalance the `smalls` and `bigs` sets after additions or removals can lead to incorrect median calculations. The size difference between the sets must be maintained within a specific range (0 or 1).
*   **Off-by-One Errors in Window Management**: Incorrectly handling the start and end indices of the sliding window, especially when adding and removing elements, can lead to missing elements or processing elements outside the current window.
*   **Handling Edge Cases**: Not considering edge cases like `k=1` or an empty input array can lead to crashes or incorrect results.
*   **Direct Value Storage in `TreeSet`**: Storing the actual numbers in `TreeSet`s instead of their indices can be problematic if there are duplicate numbers in the input array, as `TreeSet`s store unique elements.

## Complexity Analysis
*   **Time**: O(N log K) - For each of the N elements, we perform insertions and deletions into `TreeSet`s. Each `TreeSet` operation (add, remove, pollFirst, first) takes O(log K) time, where K is the maximum size of the `TreeSet`s (which is at most `k`).
*   **Space**: O(K) - The `smalls` and `bigs` `TreeSet`s store at most `k` indices at any given time.

## Commented Code
```java
class Solution {
    // Main function to calculate the median of sliding windows
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length; // Get the total number of elements in the input array
        // Handle the edge case where the array has only one element
        if(n==1) return new double[]{nums[0]};
        // Calculate the number of windows (and thus the number of medians to compute)
        int m = n-k+1;
        int j=0; // Index for the answer array
        double[] ans = new double[m]; // Array to store the computed medians

        // Define a custom comparator for the TreeSets.
        // It compares elements based on their values in the 'nums' array.
        // If values are equal, it compares their original indices to ensure uniqueness in TreeSet.
        Comparator<Integer> comparator = (a,b) -> nums[a]==nums[b] ? Integer.compare(a,b) : Integer.compare(nums[a],nums[b]);

        // 'smalls' TreeSet stores indices of elements in the smaller half of the window.
        // It uses a reversed comparator to act as a max-heap (largest element at the top).
        TreeSet<Integer> smalls = new TreeSet<>(comparator.reversed());
        // 'bigs' TreeSet stores indices of elements in the larger half of the window.
        // It uses the standard comparator to act as a min-heap (smallest element at the top).
        TreeSet<Integer> bigs = new TreeSet<>(comparator);

        // Iterate through the input array 'nums'
        for(int i=0;i<n;i++){
          // If the window has moved past the first 'k' elements (i.e., i >= k),
          // we need to remove the element that is no longer in the window.
          // The index of the element to remove is 'i - k'.
          if(i>=k)remove(smalls,bigs,i-k);

          // Add the current element's index 'i' to the data structures.
          add(smalls,bigs,i);

          // If the window has reached its full size 'k' (i.e., i >= k - 1),
          // calculate the median and store it in the answer array.
          if(i>=k-1)ans[j++] = median(smalls,bigs,nums,k%2==0);
        }
        // Return the array containing all computed medians
        return ans;
    }

    // Helper function to remove an index from the two TreeSets
    public void remove(TreeSet<Integer> smalls,TreeSet<Integer> bigs,int index){
      // Check if the index to be removed is in the 'smalls' set
      if(smalls.contains(index)){
        smalls.remove(index); // Remove it from 'smalls'
        // If after removal, 'smalls' has fewer elements than 'bigs',
        // move the smallest element from 'bigs' to 'smalls' to rebalance.
        if(smalls.size()<bigs.size()) smalls.add(bigs.pollFirst());
      } else { // If the index is not in 'smalls', it must be in 'bigs'
         bigs.remove(index); // Remove it from 'bigs'
         // If after removal, 'bigs' has fewer elements than 'smalls',
         // move the largest element from 'smalls' to 'bigs' to rebalance.
         if(bigs.size()<smalls.size()) bigs.add(smalls.pollFirst());
      }
    }

    // Helper function to add an index to the two TreeSets and maintain balance
    public void add(TreeSet<Integer> smalls,TreeSet<Integer> bigs,int index){
      smalls.add(index); // Add the new index to the 'smalls' set first.
      // Move the largest element from 'smalls' to 'bigs'. This ensures that
      // 'smalls' always contains elements smaller than or equal to elements in 'bigs'.
      bigs.add(smalls.pollFirst());
      // Rebalance the sets: if 'bigs' has more elements than 'smalls',
      // move the smallest element from 'bigs' back to 'smalls'.
      // This ensures 'smalls' has either equal or one more element than 'bigs'.
      if(bigs.size()>smalls.size())smalls.add(bigs.pollFirst());
    }

    // Helper function to calculate the median from the two TreeSets
    public double median(TreeSet<Integer> smalls,TreeSet<Integer> bigs, int[] nums, boolean isEven){
      // Get the value of the largest element in the 'smalls' set (which is at the top due to reversed comparator)
      double a = nums[smalls.first()];
      // If the total number of elements 'k' is even, the median is the average of
      // the largest element in 'smalls' and the smallest element in 'bigs'.
      if(smalls.size()==bigs.size()) return (double)(a+nums[bigs.first()])/2.0;
      // If 'k' is odd, the median is simply the largest element in the 'smalls' set.
      else return a;
    }
}
```

## Interview Tips
*   **Explain the Two-Heap/TreeSet Strategy**: Clearly articulate why using two balanced data structures (like heaps or `TreeSet`s) is efficient for maintaining the median of a dynamic set.
*   **Comparator Nuances**: Be prepared to explain the custom comparator, especially why it compares indices for tie-breaking and why `smalls` uses a reversed comparator. This shows attention to detail and understanding of `TreeSet` behavior.
*   **Rebalancing Logic**: Walk through the rebalancing steps (`if(smalls.size()<bigs.size())` and `if(bigs.size()>smalls.size())`) and explain *why* they are necessary to maintain the median property.
*   **Time/Space Complexity Justification**: Be ready to justify the O(N log K) time complexity by breaking down the operations within the loop and the O(K) space complexity by referring to the size of the `TreeSet`s.

## Revision Checklist
- [ ] Understand the problem: finding the median of a sliding window of size `k`.
- [ ] Recall the two-heap/balanced BST approach for median finding.
- [ ] Implement `TreeSet`s with custom comparators for `smalls` (max-heap behavior) and `bigs` (min-heap behavior).
- [ ] Handle index-based storage in `TreeSet`s to manage duplicate values.
- [ ] Implement the `add` function, ensuring elements are placed correctly and the balance is maintained.
- [ ] Implement the `remove` function, correctly identifying which set to remove from and rebalancing afterward.
- [ ] Implement the `median` function for both even and odd `k`.
- [ ] Pay close attention to window boundary conditions (`i >= k` for removal, `i >= k-1` for median calculation).
- [ ] Test with edge cases: `k=1`, `k=n`, arrays with duplicates, sorted/reverse-sorted arrays.

## Similar Problems
*   Find Median from Data Stream (LeetCode 295)
*   Sliding Window Maximum (LeetCode 239)
*   Kth Largest Element in an Array (LeetCode 215)

## Tags
`Array` `TreeSet` `Heap` `Two Pointers` `Sliding Window`
