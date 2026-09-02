# Count Of Smaller Numbers After Self

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Divide and Conquer` `Binary Indexed Tree` `Segment Tree` `Merge Sort` `Ordered Set` `Treap`  
**Time:** O(N log M)  
**Space:** O(M)

---

## Solution (java)

```java
class Solution {
    int[] fenwick = new int[20002];
    // btata hai ki Fenwick Array me us index pe kitne numbers ka sum already hai. Instead of adding 7 elements individually, Fenwick breaks it into large chunks.
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        // We need to do 1-based indexing for fenwick trees => ans[1] = -1000 ka answer => ans[2001] = 1000 ka answer (range oif numbers is -1000 to 1000)
        // So the conversion would be arr[num+1001]
        Integer[] ans = new Integer[n];
        for(int i=n-1 ; i>=0 ; i--){
          int index = nums[i]+10001;
          ans[i] = add(index);
          update(index+1);
       }
      return Arrays.asList(ans);
    }
    public int add(int index){
      int ans = 0;
      while(index > 0){
        ans += fenwick[index]; //ikattha kiya answer, har index se, jinpe mai depend krta hu
        index -= (index & -index);
      }
      return ans;
    }
   public void update(int index){
      while(index < 20002){
        fenwick[index]++;
        index += (index & -index); // update kara frequence, har index ka, jo mujhpe depend krte hai
      }
    }
}
```

---

---
## Quick Revision
Given an integer array `nums`, return an array `counts` where `counts[i]` is the number of smaller elements to the right of `nums[i]`.
This problem is efficiently solved using a Fenwick Tree (Binary Indexed Tree) by processing the array from right to left.

## Intuition
The core idea is to count how many numbers smaller than the current number have already been encountered as we iterate from right to left. A Fenwick Tree is perfect for this because it allows us to efficiently query prefix sums (representing counts of numbers less than a certain value) and update counts. Since the input numbers can be negative and have a specific range, we need to offset them to map them to positive indices for the Fenwick Tree.

## Algorithm
1. **Offsetting and Fenwick Tree Initialization**:
   - The problem states numbers are in the range `[-10000, 10000]`. To use a Fenwick Tree (which requires positive indices), we need to offset these numbers. A common offset is `10001` to map `[-10000, 10000]` to `[1, 20001]`.
   - Initialize a Fenwick Tree (an array, `fenwick`) of size `20002` (to accommodate indices up to `20001`) with all zeros.
   - Initialize an array `ans` of the same size as `nums` to store the results.

2. **Iterate from Right to Left**:
   - Loop through the input array `nums` from the last element (`n-1`) down to the first element (`0`).

3. **For Each Element `nums[i]`**:
   - **Calculate the Fenwick Tree Index**: `index = nums[i] + 10001`. This maps the current number to its corresponding positive index in the Fenwick Tree.
   - **Query for Smaller Numbers**: Call a `query` (or `add` in the provided code) function with `index`. This function will sum up the counts in the Fenwick Tree for all indices *less than* `index`. This sum represents the count of numbers smaller than `nums[i]` that have already been processed (i.e., are to its right). Store this count in `ans[i]`.
   - **Update the Fenwick Tree**: Call an `update` function with `index + 1`. This increments the count at `index + 1` in the Fenwick Tree, signifying that we have now encountered the number `nums[i]`. The `+1` is crucial because we want to count numbers strictly smaller than `nums[i]`. When we query for `nums[i]`, we sum up counts for indices *before* `nums[i]`'s index. When we update, we update `nums[i]`'s index and all its ancestors, so that future queries for numbers greater than `nums[i]` will include `nums[i]` in their counts.

4. **Return the Result**:
   - Convert the `ans` array to a `List<Integer>` and return it.

## Concept to Remember
*   **Fenwick Tree (Binary Indexed Tree)**: A data structure that efficiently supports prefix sum queries and point updates on an array. It's particularly useful for problems involving cumulative counts or sums.
*   **Coordinate Compression/Offsetting**: When dealing with a range of numbers that might include negatives or be sparse, mapping them to a contiguous range of positive integers is often necessary for array-based data structures like Fenwick Trees or segment trees.
*   **Right-to-Left Traversal**: For problems asking about elements "after self" or "to the right," processing the array in reverse order is a common and effective strategy.

## Common Mistakes
*   **Incorrect Indexing**: Forgetting to offset the numbers to handle negative values or using 0-based indexing for the Fenwick Tree when it expects 1-based indexing.
*   **Off-by-One Errors in Updates/Queries**: Incorrectly handling the `index` in `update` and `query` functions, especially when dealing with strict inequalities (e.g., counting strictly smaller numbers). The `update` should increment the count for the current number's index and its ancestors, while the `query` should sum counts for indices *before* the current number's index.
*   **Fenwick Tree Size**: Not allocating enough space for the Fenwick Tree to accommodate the maximum possible offset index.
*   **Not Handling Duplicates Correctly**: The Fenwick Tree naturally handles duplicates by incrementing counts. The key is ensuring the `update` and `query` logic correctly reflects the problem's requirement (e.g., strictly smaller).

## Complexity Analysis
*   **Time**: O(N log M), where N is the number of elements in `nums` and M is the range of possible values (after offsetting). Each of the N elements involves a Fenwick Tree `query` and `update` operation, both of which take O(log M) time.
*   **Space**: O(M), where M is the range of possible values. This is for the Fenwick Tree array. The `ans` array takes O(N) space. So, the total space complexity is O(N + M). If M is significantly larger than N, it dominates. If M is proportional to N or smaller, then O(N) is a reasonable approximation.

## Commented Code
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    // Declare the Fenwick Tree (Binary Indexed Tree) array.
    // Size 20002 is chosen because the input numbers are in the range [-10000, 10000].
    // We offset them by 10001 to map them to [1, 20001].
    int[] fenwick = new int[20002];

    // The countSmaller method takes the input array nums and returns a list of counts.
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        // Initialize an array to store the results. Using Integer wrapper class for potential nulls if needed, though not strictly required here.
        Integer[] ans = new Integer[n];

        // Iterate through the input array from right to left.
        // This is crucial because we need to count smaller numbers *after* the current element.
        for (int i = n - 1; i >= 0; i--) {
            // Calculate the index for the Fenwick Tree.
            // We add 10001 to nums[i] to map the range [-10000, 10000] to [1, 20001].
            // This ensures all indices are positive and within the bounds of our Fenwick Tree.
            int index = nums[i] + 10001;

            // Query the Fenwick Tree to get the count of numbers smaller than nums[i] that have already been processed (i.e., are to its right).
            // The 'add' method (which is a query operation in Fenwick Tree context) sums up frequencies from index 1 up to 'index - 1'.
            ans[i] = query(index); // Renamed 'add' to 'query' for clarity in this comment.

            // Update the Fenwick Tree to record that we have encountered the number nums[i].
            // We increment the count at 'index + 1' and all its ancestors.
            // This ensures that future queries for numbers greater than nums[i] will include nums[i] in their count.
            update(index + 1);
        }
        // Convert the Integer array to a List<Integer> and return it.
        return Arrays.asList(ans);
    }

    // The query method (originally named 'add') calculates the prefix sum up to 'index - 1'.
    // In this context, it counts how many numbers smaller than the current number have been added to the Fenwick Tree.
    public int query(int index) {
        int count = 0;
        // Traverse up the Fenwick Tree structure.
        while (index > 0) {
            // Add the value at the current Fenwick Tree index to the total count.
            // This value represents the sum of frequencies in a specific range.
            count += fenwick[index];
            // Move to the parent node in the Fenwick Tree structure.
            // (index & -index) isolates the least significant bit, which is used to navigate the tree.
            index -= (index & -index);
        }
        // Return the total count of smaller numbers encountered so far.
        return count;
    }

    // The update method increments the frequency count for a given index and its ancestors in the Fenwick Tree.
    public void update(int index) {
        // Traverse up the Fenwick Tree structure.
        // The loop continues as long as the index is within the bounds of the Fenwick Tree.
        while (index < 20002) {
            // Increment the count at the current Fenwick Tree index.
            fenwick[index]++;
            // Move to the next node that needs to be updated (an ancestor).
            // (index & -index) isolates the least significant bit, which is used to navigate the tree.
            index += (index & -index);
        }
    }
}
```

## Interview Tips
*   **Explain the Fenwick Tree**: Be prepared to explain what a Fenwick Tree is, how it works (prefix sums, updates), and why it's suitable for this problem.
*   **Justify the Offset**: Clearly explain why you need to offset the input numbers and how you chose the offset value and Fenwick Tree size.
*   **Trace an Example**: Walk through a small example array (e.g., `[5, 2, 6, 1]`) to demonstrate how the algorithm processes each element, updates the Fenwick Tree, and calculates the result.
*   **Discuss Alternatives**: Briefly mention alternative approaches (like Merge Sort with modifications or a balanced BST) and explain why the Fenwick Tree is often preferred for its cleaner implementation and good performance.

## Revision Checklist
- [ ] Understand the problem statement: count smaller elements to the right.
- [ ] Recognize the need for efficient counting of elements seen so far.
- [ ] Understand Fenwick Tree (BIT) basics: `update` and `query` operations.
- [ ] Handle negative numbers and range mapping using an offset.
- [ ] Implement the right-to-left traversal.
- [ ] Correctly implement `update` and `query` for the Fenwick Tree.
- [ ] Ensure correct Fenwick Tree size and indexing.
- [ ] Analyze time and space complexity.

## Similar Problems
*   315. Count of Smaller Numbers After Self (This problem)
*   307. Range Sum Query - Mutable
*   327. Count of Range Sum
*   1310. XOR Queries of a Subarray
*   208. Implement Trie (Prefix Tree) - Can be used for a similar counting problem, but often less efficient than BIT for this specific case.

## Tags
`Array` `Binary Indexed Tree` `Segment Tree` `Divide and Conquer`
