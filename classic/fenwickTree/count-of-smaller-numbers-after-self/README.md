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
As we iterate through the array from right to left, for each element `nums[i]`, we need to quickly query how many numbers smaller than `nums[i]` have we already encountered (which are to its right). A Fenwick Tree is excellent for range sum queries and point updates. If we map the numbers to indices in the Fenwick Tree, we can use it to count occurrences. When we process `nums[i]`, we query the sum of counts for all numbers strictly less than `nums[i]` that we've already added to the Fenwick Tree. Then, we update the Fenwick Tree to include `nums[i]`.

## Algorithm
1. **Offsetting Numbers:** The input numbers can be negative. Fenwick Trees typically work with positive indices. We need to shift the numbers so they are all non-negative and can be used as indices. The problem statement implies a range of -10000 to 10000. Adding 10001 to each number will map them to the range [1, 20001]. This will be our offset.
2. **Initialize Fenwick Tree:** Create a Fenwick Tree (an array) of size `20002` (to accommodate indices up to 20001). Initialize all its elements to 0.
3. **Iterate from Right to Left:** Loop through the input array `nums` from the last element to the first (index `n-1` down to `0`).
4. **Calculate Index:** For the current number `nums[i]`, calculate its corresponding index in the Fenwick Tree: `index = nums[i] + 10001`.
5. **Query for Smaller Numbers:** Use the `add` (or `query`) function of the Fenwick Tree to get the sum of counts for all indices *less than* the current `index`. This sum represents the count of numbers smaller than `nums[i]` that have already been processed (i.e., are to its right). Store this count in `ans[i]`.
6. **Update Fenwick Tree:** Use the `update` function of the Fenwick Tree to increment the count at `index + 1`. We increment at `index + 1` because we want to count `nums[i]` itself for future queries of numbers greater than it. The `update` operation propagates this increment to all relevant parent nodes in the Fenwick Tree.
7. **Return Result:** After iterating through all elements, return the `ans` array.

## Concept to Remember
*   **Fenwick Tree (Binary Indexed Tree):** A data structure that efficiently supports prefix sum queries and point updates on an array. It uses bit manipulation (`index & -index`) to navigate its tree-like structure.
*   **Coordinate Compression/Offsetting:** Mapping original values to a smaller, contiguous range of indices, especially when dealing with large or negative values, to fit them into array-based data structures like Fenwick Trees or segment trees.
*   **Right-to-Left Traversal:** Processing elements from right to left is crucial for problems that ask for counts of elements *after* the current one.

## Common Mistakes
*   **Incorrect Indexing:** Forgetting to offset negative numbers or using 0-based indexing for the Fenwick Tree when it expects 1-based indexing.
*   **Off-by-One Errors in Query/Update:** Incorrectly defining the range for queries (e.g., querying up to `index` instead of `index-1` for strictly smaller numbers) or updating the wrong index.
*   **Fenwick Tree Implementation Errors:** Misunderstanding the `index & -index` logic or the loop conditions in `add` and `update` functions.
*   **Not Handling Duplicates Correctly:** The Fenwick Tree naturally handles duplicates by incrementing counts. The key is to ensure the query range is correct.

## Complexity Analysis
*   **Time:** O(N log M), where N is the number of elements in `nums` and M is the range of possible values after offsetting (20002 in this case). For each of the N elements, we perform a Fenwick Tree query and an update, both of which take O(log M) time.
*   **Space:** O(M) for the Fenwick Tree array.

## Commented Code
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    // Declare a Fenwick Tree (Binary Indexed Tree) array.
    // The size 20002 is chosen to accommodate the range of numbers after offsetting.
    // The problem implies numbers are in the range [-10000, 10000].
    // Adding 10001 to each number maps them to [1, 20001].
    int[] fenwick = new int[20002];

    // The main function to count smaller numbers after self.
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length; // Get the number of elements in the input array.
        // Create an array to store the results. Using Integer wrapper class for potential nullability if needed, though not strictly required here.
        Integer[] ans = new Integer[n];

        // Iterate through the input array from right to left.
        // This is crucial because we need to count elements *after* the current one.
        for (int i = n - 1; i >= 0; i--) {
            // Calculate the index for the Fenwick Tree.
            // We add 10001 to shift the number range [-10000, 10000] to [1, 20001].
            // This makes all indices positive and suitable for the Fenwick Tree.
            int index = nums[i] + 10001;

            // Query the Fenwick Tree to find the count of numbers smaller than nums[i]
            // that have already been processed (i.e., are to its right).
            // The 'add' function sums up frequencies from index 1 up to 'index - 1'.
            ans[i] = add(index);

            // Update the Fenwick Tree to mark the presence of nums[i].
            // We increment the count at 'index + 1' because we want to include nums[i] itself
            // for future queries of numbers greater than it. The update propagates this change.
            update(index + 1);
        }
        // Convert the array of Integers to a List and return it.
        return Arrays.asList(ans);
    }

    // Function to query the prefix sum in the Fenwick Tree.
    // It returns the sum of frequencies from index 1 up to the given 'index'.
    // In this problem, 'index' is actually the mapped value of the number.
    // So, add(k) returns the count of numbers whose mapped values are <= k.
    // To get count of numbers strictly smaller than nums[i] (mapped to 'index'),
    // we call add(index) which sums up counts for mapped values 1 to index-1.
    public int add(int index) {
        int ans = 0; // Initialize the sum to 0.
        // Traverse up the Fenwick Tree structure.
        while (index > 0) {
            // Add the value at the current index to the sum.
            // This accumulates counts from relevant nodes.
            ans += fenwick[index];
            // Move to the parent node by subtracting the least significant bit.
            // (index & -index) isolates the least significant bit.
            index -= (index & -index);
        }
        return ans; // Return the calculated prefix sum.
    }

    // Function to update a value in the Fenwick Tree.
    // It increments the frequency at the given 'index' and propagates this change upwards.
    // In this problem, 'index' is the mapped value of the number + 1.
    // update(k) increments the count for the number mapped to k-1.
    public void update(int index) {
        // Traverse up the Fenwick Tree structure.
        // The loop continues as long as the index is within the bounds of the Fenwick Tree array.
        while (index < 20002) {
            // Increment the count at the current index.
            fenwick[index]++;
            // Move to the next relevant node by adding the least significant bit.
            // This ensures that future queries that include this index will be updated.
            index += (index & -index);
        }
    }
}
```

## Interview Tips
*   **Explain the Fenwick Tree:** Be prepared to explain what a Fenwick Tree is, how it works (especially the `index & -index` part), and why it's suitable for this problem.
*   **Justify Right-to-Left Traversal:** Clearly articulate why iterating from right to left is essential for counting elements *after* the current one.
*   **Handle the Offset:** Explain the necessity of offsetting the numbers to handle negative values and map them to valid array indices for the Fenwick Tree.
*   **Walk Through an Example:** Use a small example array (e.g., `[5, 2, 6, 1]`) to trace the algorithm step-by-step, showing how the Fenwick Tree is updated and queried.

## Revision Checklist
- [ ] Understand the problem statement: count smaller elements to the right.
- [ ] Recognize Fenwick Tree as a suitable data structure.
- [ ] Implement Fenwick Tree `update` and `query` (prefix sum) operations.
- [ ] Handle negative numbers using an offset.
- [ ] Process the array from right to left.
- [ ] Correctly map numbers to Fenwick Tree indices.
- [ ] Ensure query range is for strictly smaller numbers.
- [ ] Analyze time and space complexity.

## Similar Problems
*   315. Count of Smaller Numbers After Self (This problem)
*   307. Range Sum Query - Mutable
*   327. Count of Range Sum
*   208. Implement Trie (Prefix Tree) - Can be used for a similar approach but often less efficient for this specific problem.
*   Merge Sort based solutions for counting inversions.

## Tags
`Array` `Binary Indexed Tree` `Segment Tree` `Divide and Conquer`
