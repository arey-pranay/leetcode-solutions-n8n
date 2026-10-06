# Minimum Number Of K Consecutive Bit Flips

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Bit Manipulation` `Queue` `Sliding Window` `Prefix Sum` `Brute-Force Search`  
**Time:** O(n)  
**Space:** O(k)

---

## Solution (java)

```java
class Solution {
    public int minKBitFlips(int[] nums, int k) {
        // select any subarray of length k 
        // change all 0s to 1 and all 1s to zero
        // return min number of ops to make array with no 0s
        int count =0;
        int n = nums.length;
        // even times flips => no net change in value => 0 flips
        // odd times flips => no net change in value => 1 flip
        
        Queue<Integer> flips = new LinkedList<>();
        for(int i=0;i<n;i++){
            
            if(!flips.isEmpty()){ // discard the flip entries which are expired now
               int oldestFlipLimit = flips.peek();
               if(i>oldestFlipLimit) flips.poll(); 
               // jese hi old hojaata hai tb hum flip krdenge kyonki ab aur koi nayi sliding window m nhi aayega vo 
            }
            
            int netFlips = flips.size() % 2;
            
            if(netFlips==1) nums[i] = nums[i]==0?1:0;
            
            if(nums[i]==0) {
                if(i+k >n) return -1;
                count++;                
                flips.offer(i+k-1);  // is flip ki expiry agle k indices tk hai
            }
           
          
        }
        
        return count;
    }
}


```

---

---
## Quick Revision
The problem asks for the minimum number of operations to make an array of binary digits all ones, where an operation flips a subarray of length `k`.
We solve this by greedily flipping subarrays starting from the left, tracking the effect of flips using a queue.

## Intuition
The core idea is that we want to make the array all ones. When we encounter a `0`, we *must* flip it to a `1`. The most efficient way to do this, given the constraint of flipping subarrays of length `k`, is to perform a flip operation starting at the current index `i`. This flip will affect `nums[i]` and the next `k-1` elements. If we encounter a `0` and performing a flip starting at `i` would go out of bounds (`i + k > n`), then it's impossible to make the array all ones, and we return -1.

The challenge is that flips are cumulative. A flip at index `j` affects elements from `j` to `j+k-1`. When we are at index `i`, we need to know the *net effect* of all flips that are currently active and cover index `i`. A flip operation is like a "state change" that lasts for `k` positions. If an even number of flips affect `nums[i]`, its original value remains. If an odd number of flips affect `nums[i]`, its value is inverted.

We can track the active flips using a queue. When we decide to flip starting at index `i`, we add the *end index* of this flip (`i + k - 1`) to the queue. As we iterate through the array, if the current index `i` is past the end index of the oldest flip in the queue, we remove that flip from the queue. The size of the queue then tells us how many flips are currently active and affecting the current element `nums[i]`.

## Algorithm
1. Initialize `count` to 0 (to store the number of flips).
2. Initialize a queue `flips` to store the *end indices* of active flip operations.
3. Iterate through the array `nums` from index `i = 0` to `n-1`.
4. **Check for expired flips:** If the queue `flips` is not empty, check if the current index `i` is greater than the index at the front of the queue (`flips.peek()`). If it is, it means the oldest flip operation has ended, so remove it from the queue (`flips.poll()`).
5. **Determine current state:** Calculate the `netFlips` affecting the current element `nums[i]`. This is `flips.size() % 2`. If `netFlips` is 1 (odd number of flips), the current value of `nums[i]` is effectively inverted. So, if `nums[i]` was originally 0, it's now 1, and if it was 1, it's now 0.
6. **Handle `0`s:** If, after considering the `netFlips`, `nums[i]` is still 0:
    a. **Check for impossibility:** If `i + k` is greater than `n` (meaning a flip starting at `i` would go out of bounds), return -1.
    b. **Perform flip:** Increment `count` because we are performing a flip operation.
    c. **Record flip:** Add the end index of this new flip operation (`i + k - 1`) to the `flips` queue.
7. After iterating through the entire array, return `count`.

## Concept to Remember
*   **Greedy Approach:** Making the locally optimal choice (flipping a `0` as soon as it's encountered) leads to the globally optimal solution.
*   **Sliding Window / State Tracking:** Using a queue to manage the "active window" of flip effects. The queue size represents the current state of inversions.
*   **Parity:** The effect of multiple flips is determined by the parity (even or odd) of the number of flips.

## Common Mistakes
*   **Not handling out-of-bounds flips:** Forgetting to check if `i + k > n` when a flip is needed.
*   **Incorrectly calculating net flips:** Misunderstanding how the queue size relates to the actual inversion state of `nums[i]`.
*   **Modifying `nums` in place without considering its original value:** The logic `nums[i] = nums[i]==0?1:0;` should be applied *before* checking if `nums[i]` is 0 for the purpose of initiating a new flip.
*   **Not removing expired flips from the queue:** This leads to an incorrect `flips.size()` calculation.

## Complexity Analysis
*   **Time:** O(n) - Each element is visited once. Each element is enqueued and dequeued at most once.
*   **Space:** O(k) - The queue `flips` can store at most `k` elements, representing the end indices of `k` consecutive flip operations.

## Commented Code
```java
class Solution {
    public int minKBitFlips(int[] nums, int k) {
        // Initialize count to store the total number of flip operations performed.
        int count = 0;
        // Get the length of the input array.
        int n = nums.length;
        
        // Use a queue to store the end indices of the active flip operations.
        // The size of the queue will tell us how many flips are currently affecting the current element.
        Queue<Integer> flips = new LinkedList<>();
        
        // Iterate through each element of the array.
        for (int i = 0; i < n; i++) {
            
            // Check if the oldest flip operation in the queue has expired.
            // An operation expires if its end index (stored in flips.peek()) is less than the current index i.
            if (!flips.isEmpty()) {
               // Get the end index of the oldest flip.
               int oldestFlipLimit = flips.peek();
               // If the current index i is past the end of the oldest flip, remove it from the queue.
               if (i > oldestFlipLimit) {
                   flips.poll(); 
               }
            }
            
            // Determine the net effect of active flips on the current element nums[i].
            // If flips.size() is odd, the element's value is effectively flipped.
            // If flips.size() is even, the element's value is its original value.
            int netFlips = flips.size() % 2;
            
            // If netFlips is 1 (odd), it means the current element's value is inverted from its original state.
            // So, if nums[i] was 0, it's now effectively 1. If it was 1, it's now effectively 0.
            if (netFlips == 1) {
                // Invert nums[i] to reflect the net flip effect.
                nums[i] = nums[i] == 0 ? 1 : 0;
            }
            
            // Now, check the effective value of nums[i]. If it's 0, we must perform a flip operation.
            if (nums[i] == 0) {
                // If performing a flip starting at index i would go beyond the array bounds,
                // it's impossible to make the array all ones. Return -1.
                if (i + k > n) {
                    return -1;
                }
                // Increment the count of flip operations.
                count++;                
                // Add the end index of this new flip operation to the queue.
                // This flip will affect elements from index i to i + k - 1.
                flips.offer(i + k - 1);
            }
        }
        
        // If we successfully iterated through the entire array, return the total count of flips.
        return count;
    }
}
```

## Interview Tips
*   **Explain the greedy choice:** Clearly articulate why flipping a `0` as soon as you see it is the optimal strategy.
*   **Visualize the queue:** Use an example to walk through how the queue `flips` tracks active flip operations and how its size determines the current element's effective value.
*   **Discuss edge cases:** Mention the `i + k > n` impossibility condition and how the queue handles expired flips.
*   **Clarify in-place modification:** If you modify `nums` in place, explain that it's to track the *current effective value* after considering active flips, not necessarily the original value.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Grasp the greedy strategy: flip `0`s from left to right.
- [ ] Implement the queue to track active flip ranges.
- [ ] Correctly handle expired flips by polling from the queue.
- [ ] Accurately calculate the net flip effect using `flips.size() % 2`.
- [ ] Implement the out-of-bounds check (`i + k > n`).
- [ ] Test with examples: `[0,1,0] k=2`, `[1,1,0] k=2`, `[0,0,0,1,0,1,1,0] k=3`.

## Similar Problems
*   Sliding Window Maximum
*   Shortest Subarray with Sum at Least K
*   Candy

## Tags
`Array` `Greedy` `Queue` `Sliding Window`
