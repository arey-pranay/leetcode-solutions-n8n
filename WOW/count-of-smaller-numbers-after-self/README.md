# Count Of Smaller Numbers After Self

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Divide and Conquer` `Binary Indexed Tree` `Segment Tree` `Merge Sort` `Ordered Set` `Treap`  
**Time:** O(N log K)  
**Space:** O(K)

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
The core idea is to count how many numbers smaller than the current number have already been encountered as we iterate from right to left. A Fenwick Tree is perfect for this because it allows us to efficiently query the sum of frequencies up to a certain point (representing counts of smaller numbers) and update frequencies. Since the input numbers can be negative, we need to offset them to map them to positive indices for the Fenwick Tree.

## Algorithm
1. **Offsetting Numbers:** The input numbers can range from -10000 to 10000. To use them as indices in a Fenwick Tree (which requires positive indices), we add an offset of 10001 to each number. This maps the range [-10000, 10000] to [1, 20001].
2. **Initialize Fenwick Tree:** Create a Fenwick Tree (an array, `fenwick`) of size 20002 (to accommodate the maximum offset value). Initialize all its elements to 0.
3. **Iterate from Right to Left:** Traverse the input array `nums` from the last element to the first.
4. **Query for Smaller Numbers:** For each element `nums[i]`:
    a. Calculate its offset index: `index = nums[i] + 10001`.
    b. Query the Fenwick Tree for the sum of frequencies up to `index - 1`. This sum represents the count of numbers smaller than `nums[i]` that have already been processed (i.e., are to its right). Store this count in `ans[i]`.
5. **Update Fenwick Tree:** After querying, update the Fenwick Tree to mark the presence of `nums[i]`. Increment the frequency at `index + 1` in the Fenwick Tree. This ensures that `nums[i]` will be counted for subsequent elements to its left.
6. **Return Result:** Convert the `ans` array (which stores `Integer` objects) into a `List<Integer>` and return it.

## Concept to Remember
*   **Fenwick Tree (Binary Indexed Tree):** A data structure that efficiently supports prefix sum queries and point updates. It's particularly useful for problems involving range sums or counts.
*   **Coordinate Compression/Offsetting:** When dealing with a range of numbers that might include negatives or be very large, mapping them to a smaller, contiguous range of positive integers is crucial for array-based data structures like Fenwick Trees.
*   **Right-to-Left Traversal:** For problems asking about elements *after* a given element, iterating from right to left and using a data structure to track seen elements is a common and effective pattern.

## Common Mistakes
*   **Incorrect Indexing:** Forgetting to add the offset or using 0-based indexing for the Fenwick Tree, which expects 1-based indexing.
*   **Off-by-One Errors in Queries/Updates:** Incorrectly calculating the range for queries (e.g., querying up to `index` instead of `index - 1`) or updating the wrong index.
*   **Not Handling Negative Numbers:** Failing to offset negative numbers, leading to array index out-of-bounds errors.
*   **Inefficient Data Structure:** Trying to solve this with nested loops (O(n^2)) instead of a more efficient data structure like a Fenwick Tree or Segment Tree.

## Complexity Analysis
*   **Time:** O(N log K), where N is the number of elements in `nums` and K is the range of possible values after offsetting (20001 in this case). Each query and update operation on the Fenwick Tree takes O(log K) time, and we perform N such operations.
*   **Space:** O(K) for the Fenwick Tree array.

## Commented Code
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    // Declare the Fenwick Tree (Binary Indexed Tree) array.
    // Size 20002 is chosen because the input numbers range from -10000 to 10000.
    // Adding an offset of 10001 maps this range to [1, 20001].
    // We need an extra element for 1-based indexing and to accommodate the max value.
    int[] fenwick = new int[20002];

    // The main method to count smaller numbers after self.
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        // Initialize an array to store the results. Using Integer wrapper class for potential nulls if needed, but here it's for Arrays.asList compatibility.
        Integer[] ans = new Integer[n];

        // Iterate through the input array from right to left.
        // This is crucial because we want to count smaller numbers *after* the current element.
        for (int i = n - 1; i >= 0; i--) {
            // Calculate the 1-based index for the Fenwick Tree.
            // We add 10001 to shift the range [-10000, 10000] to [1, 20001].
            int index = nums[i] + 10001;

            // Query the Fenwick Tree to get the count of numbers smaller than nums[i]
            // that have already been processed (i.e., are to the right of nums[i]).
            // The 'add' method sums frequencies from index 1 up to 'index - 1'.
            ans[i] = add(index);

            // Update the Fenwick Tree to mark the presence of nums[i].
            // We increment the frequency at 'index + 1' because 'index' represents nums[i],
            // and we want to count it for elements to its left.
            update(index + 1);
        }
        // Convert the array of Integer objects to a List<Integer> as required by the return type.
        return Arrays.asList(ans);
    }

    // This method queries the Fenwick Tree for the sum of frequencies up to a given index.
    // In this problem, it calculates the count of numbers smaller than the current number.
    public int add(int index) {
        int ans = 0; // Initialize the sum to 0.
        // Traverse up the Fenwick Tree structure.
        while (index > 0) {
            // Add the value at the current index to the sum.
            // This accumulates counts from relevant nodes in the tree.
            ans += fenwick[index];
            // Move to the parent node in the Fenwick Tree.
            // (index & -index) isolates the least significant bit, which is used to navigate the tree.
            index -= (index & -index);
        }
        // Return the total count of smaller numbers encountered so far.
        return ans;
    }

    // This method updates the Fenwick Tree by incrementing the frequency at a given index.
    // It propagates this increment up the tree to all affected parent nodes.
    public void update(int index) {
        // Traverse up the Fenwick Tree structure.
        // The loop continues as long as the index is within the bounds of the Fenwick Tree array.
        while (index < 20002) {
            // Increment the frequency at the current index.
            fenwick[index]++;
            // Move to the next node that needs to be updated.
            // (index & -index) isolates the least significant bit, which is used to navigate the tree.
            index += (index & -index);
        }
    }
}
```

## Interview Tips
*   **Explain the Offset:** Clearly articulate why an offset is necessary for negative numbers and how it maps to the Fenwick Tree's index requirements.
*   **Fenwick Tree Logic:** Be prepared to explain how the `add` (query) and `update` operations work in a Fenwick Tree, specifically the `index -= (index & -index)` and `index += (index & -index)` logic.
*   **Right-to-Left Processing:** Emphasize why iterating from right to left is the correct approach for this specific problem.
*   **Alternative Solutions:** Briefly mention that a Merge Sort-based approach can also solve this problem, but Fenwick Tree is often preferred for its cleaner implementation in this context.

## Revision Checklist
- [ ] Understand the problem statement: count smaller elements to the right.
- [ ] Recognize the need for an efficient data structure.
- [ ] Understand Fenwick Tree (BIT) basics: query and update.
- [ ] Handle negative numbers with an offset.
- [ ] Implement right-to-left traversal.
- [ ] Correctly map numbers to Fenwick Tree indices.
- [ ] Implement `add` (query) and `update` methods accurately.
- [ ] Analyze time and space complexity.

## Similar Problems
*   315. Count of Smaller Numbers After Self (This problem)
*   307. Range Sum Query - Mutable
*   318. Maximum Product of Word Lengths (Different problem, but uses bit manipulation)
*   208. Implement Trie (Prefix Tree) (Can be used for similar counting problems)

## Tags
`Array` `Binary Indexed Tree` `Segment Tree` `Merge Sort`
