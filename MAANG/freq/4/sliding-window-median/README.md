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
The problem asks to find the median of all subarrays of a given size `k` within an array `nums`.
This is solved using two balanced heaps (or `TreeSet`s in this case) to maintain the smaller and larger halves of the current window.

## Intuition
The core idea is to efficiently maintain the median of a sliding window. A naive approach of sorting each window would be too slow. We need a data structure that can:
1. Add elements.
2. Remove elements.
3. Quickly find the median.

Two heaps (or `TreeSet`s) can achieve this. One heap stores the smaller half of the elements, and the other stores the larger half. The median will always be at the top of one of these heaps (or the average of the tops if the window size is even).

The challenge here is that `TreeSet` in Java, by default, sorts based on the element's value. However, we need to remove elements from the `TreeSet` based on their *original index* in the `nums` array, not their value, because duplicate values can exist. To handle this, we store the *indices* in the `TreeSet`s and use a custom comparator that prioritizes the values at those indices in the `nums` array.

## Algorithm
1. Initialize two `TreeSet`s: `smalls` to store indices of elements in the smaller half and `bigs` to store indices of elements in the larger half.
2. Define a custom `Comparator` for the `TreeSet`s. This comparator will compare elements based on their values in the `nums` array. For `smalls`, we reverse the order to make it a max-heap-like structure (largest element at the top). For `bigs`, it's a min-heap-like structure (smallest element at the top). The comparator also handles ties by comparing indices to ensure uniqueness and consistent ordering.
3. Iterate through the `nums` array with index `i` from 0 to `n-1`.
4. **Window Maintenance:**
   - If `i >= k` (meaning the window has moved past the `k`-th element), remove the element at index `i-k` from the appropriate `TreeSet` using the `remove` helper function.
   - Add the current element's index `i` to the `TreeSet`s using the `add` helper function. This function ensures that the `smalls` and `bigs` sets remain balanced.
5. **Median Calculation:**
   - If `i >= k-1` (meaning the window is full), calculate the median using the `median` helper function and store it in the `ans` array.
6. Return the `ans` array.

**Helper Functions:**
- `add(smalls, bigs, index)`:
    - Add the `index` to `smalls`.
    - Move the largest element from `smalls` to `bigs` to maintain the invariant that `smalls` contains the smaller half.
    - If `bigs` becomes larger than `smalls`, move the smallest element from `bigs` back to `smalls` to rebalance.
- `remove(smalls, bigs, index)`:
    - Check if the `index` is in `smalls` or `bigs`.
    - Remove the `index` from the respective `TreeSet`.
    - After removal, rebalance the `TreeSet`s if their sizes become unequal by moving an element from the larger `TreeSet` to the smaller one.
- `median(smalls, bigs, nums, isEven)`:
    - If `smalls` and `bigs` have equal sizes (window size `k` is even), the median is the average of the largest element in `smalls` (which is `nums[smalls.first()]`) and the smallest element in `bigs` (which is `nums[bigs.first()]`).
    - If `smalls` has more elements than `bigs` (window size `k` is odd), the median is the largest element in `smalls` (`nums[smalls.first()]`).

## Concept to Remember
*   **Two Heaps/Balanced BSTs for Median:** Maintaining two heaps (or balanced BSTs like `TreeSet`) is a standard technique for efficiently finding the median of a dynamic set of numbers.
*   **Custom Comparators:** When using data structures like `TreeSet` or `PriorityQueue` with custom ordering or to handle specific constraints (like removing by index), a custom comparator is essential.
*   **Sliding Window Technique:** Efficiently processing contiguous subarrays by adding new elements and removing old ones without recomputing from scratch.
*   **Handling Duplicates and Removal by Index:** When elements can be duplicated, storing indices and using a comparator that considers values at those indices is crucial for correct removal from ordered collections.

## Common Mistakes
*   **Not handling duplicate values correctly:** If the `TreeSet` only stored values, removing a duplicate value would remove the wrong instance. Storing indices and using a value-based comparator solves this.
*   **Incorrect balancing of `smalls` and `bigs`:** Failing to rebalance the `TreeSet`s after adding or removing elements can lead to incorrect median calculations. The sizes should differ by at most 1.
*   **Off-by-one errors in window indexing:** Incorrectly calculating the start and end indices for adding and removing elements from the window.
*   **Forgetting to handle the `k=1` edge case:** The logic might not cover the simplest case where the window size is 1.
*   **Integer overflow when calculating the median average:** For even `k`, summing two large integers might overflow before casting to `double`.

## Complexity Analysis
- Time: O(N log K) - reason: For each of the N elements, we perform `add` and `remove` operations on `TreeSet`s. `TreeSet` operations (add, remove, pollFirst, first) take O(log K) time, where K is the maximum size of the `TreeSet`s (which is at most the window size `k`).
- Space: O(K) - reason: The `smalls` and `bigs` `TreeSet`s store at most `k` indices at any given time.

## Commented Code
```java
class Solution {
    // Main function to calculate the median of sliding windows.
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length; // Get the total number of elements in the input array.
        // Edge case: if there's only one element, the median is that element itself.
        if(n==1) return new double[]{nums[0]};
        // Calculate the number of windows (and thus the size of the answer array).
        int m = n-k+1;
        int j=0; // Index for the answer array.
        double[] ans = new double[m]; // Initialize the answer array to store medians.

        // Define a custom comparator for TreeSets.
        // It compares elements based on their values in the 'nums' array.
        // If values are equal, it compares their original indices to ensure uniqueness and stable ordering.
        Comparator<Integer> comparator = (a,b) -> nums[a]==nums[b] ? Integer.compare(a,b) : Integer.compare(nums[a],nums[b]);

        // 'smalls' TreeSet stores indices of elements in the smaller half of the window.
        // We use 'comparator.reversed()' to make it behave like a max-heap (largest element at the top).
        // We store indices because we need to remove elements by their original position, not just value.
        TreeSet<Integer> smalls = new TreeSet<>(comparator.reversed());

        // 'bigs' TreeSet stores indices of elements in the larger half of the window.
        // It uses the standard comparator to behave like a min-heap (smallest element at the top).
        TreeSet<Integer> bigs = new TreeSet<>(comparator);

        // Iterate through the input array 'nums'.
        for(int i=0;i<n;i++){
          // If the window has moved past its initial k elements (i.e., i >= k),
          // we need to remove the element that is now outside the window.
          // The element to remove is at index 'i-k'.
          if(i>=k)remove(smalls,bigs,i-k);

          // Add the current element's index 'i' to our two TreeSets.
          // The 'add' function handles placing it in the correct set and rebalancing.
          add(smalls,bigs,i);

          // If the window is full (i.e., we have processed at least k elements),
          // calculate the median for the current window and store it in the answer array.
          // 'k%2==0' checks if k is even, which affects median calculation.
          if(i>=k-1)ans[j++] = median(smalls,bigs,nums,k%2==0);
        }
        // Return the array containing medians of all sliding windows.
        return ans;
    }

    // Helper function to remove an index from the 'smalls' or 'bigs' TreeSet.
    public void remove(TreeSet<Integer> smalls,TreeSet<Integer> bigs,int index){
      // Check if the index to be removed is in the 'smalls' set.
      if(smalls.contains(index)){
        smalls.remove(index); // Remove it from 'smalls'.
        // After removal, if 'smalls' has fewer elements than 'bigs',
        // we need to rebalance by moving the smallest element from 'bigs' to 'smalls'.
        if(smalls.size()<bigs.size()) smalls.add(bigs.pollFirst());
      } else { // If the index is not in 'smalls', it must be in 'bigs'.
         bigs.remove(index); // Remove it from 'bigs'.
         // After removal, if 'bigs' has fewer elements than 'smalls',
         // we need to rebalance by moving the largest element from 'smalls' to 'bigs'.
         if(bigs.size()<smalls.size()) bigs.add(smalls.pollFirst());
      }
    }

    // Helper function to add an index to the 'smalls' and 'bigs' TreeSets and maintain balance.
    public void add(TreeSet<Integer> smalls,TreeSet<Integer> bigs,int index){
      // Initially, add the new index to the 'smalls' set.
      smalls.add(index);
      // Move the largest element from 'smalls' to 'bigs'. This ensures that 'smalls'
      // always contains elements that are less than or equal to the median, and 'bigs'
      // contains elements greater than or equal to the median.
      bigs.add(smalls.pollFirst());
      // If 'bigs' now has more elements than 'smalls', it means the balance is off.
      // Move the smallest element from 'bigs' back to 'smalls' to restore balance.
      // The goal is to keep sizes balanced: smalls.size() == bigs.size() or smalls.size() == bigs.size() + 1.
      if(bigs.size()>smalls.size())smalls.add(bigs.pollFirst());
    }

    // Helper function to calculate the median of the current window.
    public double median(TreeSet<Integer> smalls,TreeSet<Integer> bigs, int[] nums, boolean isEven){
      // The largest element in the 'smalls' set is always at the top (smalls.first()).
      // Its value is nums[smalls.first()].
      double a = nums[smalls.first()];
      // If the window size 'k' is even (isEven is true), the median is the average of
      // the largest element in 'smalls' and the smallest element in 'bigs'.
      if(smalls.size()==bigs.size()) return (double)(a+nums[bigs.first()])/2.0;
      // If the window size 'k' is odd, the median is simply the largest element in 'smalls'.
      else return a;
    }
}
```

## Interview Tips
*   **Explain the Two-Heap/TreeSet Strategy:** Clearly articulate why two data structures are needed and how they maintain the median. Emphasize the balancing act.
*   **Address the Index vs. Value Issue:** Be prepared to explain why storing indices in the `TreeSet`s and using a custom comparator is crucial for handling duplicate values and enabling correct removal.
*   **Walk Through an Example:** Use a small example array and window size to trace the `add`, `remove`, and `median` operations step-by-step. This demonstrates your understanding of the algorithm's flow.
*   **Discuss Edge Cases:** Mention how you handle `k=1` and the initial filling of the window before median calculation begins.

## Revision Checklist
- [ ] Understand the problem: find median of all subarrays of size k.
- [ ] Recall the two-heap/balanced BST approach for median finding.
- [ ] Implement custom comparator for `TreeSet` to sort by value but store indices.
- [ ] Implement `add` function to insert index and maintain balance.
- [ ] Implement `remove` function to delete index and rebalance.
- [ ] Implement `median` function to calculate median based on set sizes.
- [ ] Handle window sliding logic (adding new, removing old).
- [ ] Consider edge cases like `k=1` and array length.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Find Median from Data Stream
*   Sliding Window Maximum
*   Kth Largest Element in an Array

## Tags
`Array` `TreeSet` `Heap` `Two Pointers` `Data Stream`
