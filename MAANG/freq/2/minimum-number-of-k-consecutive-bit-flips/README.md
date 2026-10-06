# Minimum Number Of K Consecutive Bit Flips

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Bit Manipulation` `Queue` `Sliding Window` `Prefix Sum` `Brute-Force Search`  
**Time:** O(n)  
**Space:** O(n)

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
The problem is to find the minimum number of bit flips required to make all elements in the array equal to 1, where each flip affects k consecutive elements. The solution involves using a queue to track the positions where a flip is needed and sliding window technique to optimize the process.

## Intuition
The key insight is that when the number of flips is odd, there will be one element left with a different value after the flips, and that element will be at the end of the current window. Therefore, we only need to flip the elements in the current window and the next window to make all elements equal to 1.

## Algorithm
1. Initialize a queue to store the positions where a flip is needed.
2. Iterate over the array, and for each element:
	* If the queue is not empty, discard the oldest flip entry if it has expired (i.e., its position is less than the current index).
	* Calculate the net number of flips in the current window (i.e., the number of flips in the queue modulo 2).
	* If the net number of flips is odd, flip the current element.
	* If the current element is 0 and the end of the current window is within the array, increment the count of flips and add the end of the current window to the queue.
3. Return the count of flips.

## Concept to Remember
* Sliding window technique: This problem requires using a sliding window to track the positions where a flip is needed and to optimize the process.
* Queue data structure: A queue is used to store the positions where a flip is needed and to efficiently discard expired entries.
* Bit manipulation: The problem involves flipping bits in the array, which requires understanding of bit manipulation concepts.

## Common Mistakes
* Not using a queue to track the positions where a flip is needed.
* Not discarding expired entries from the queue.
* Not handling the case where the current element is 0 and the end of the current window is within the array.
* Not considering the minimum number of flips required to make all elements equal to 1.

## Complexity Analysis
- Time: O(n) - reason: We iterate over the array once and perform constant-time operations for each element.
- Space: O(n) - reason: In the worst case, we need to store all elements in the queue.

## Commented Code
```java
class Solution {
    public int minKBitFlips(int[] nums, int k) {
        // Initialize a queue to store the positions where a flip is needed.
        Queue<Integer> flips = new LinkedList<>();
        
        int count = 0; // Initialize the count of flips.
        int n = nums.length; // Get the length of the array.
        
        // Iterate over the array.
        for (int i = 0; i < n; i++) {
            // If the queue is not empty, discard the oldest flip entry if it has expired.
            if (!flips.isEmpty()) {
                int oldestFlipLimit = flips.peek(); // Get the position of the oldest flip entry.
                if (i > oldestFlipLimit) {
                    flips.poll(); // Discard the oldest flip entry.
                }
            }
            
            // Calculate the net number of flips in the current window.
            int netFlips = flips.size() % 2;
            
            // If the net number of flips is odd, flip the current element.
            if (netFlips == 1) {
                nums[i] = nums[i] == 0 ? 1 : 0;
            }
            
            // If the current element is 0 and the end of the current window is within the array,
            // increment the count of flips and add the end of the current window to the queue.
            if (nums[i] == 0) {
                if (i + k > n) {
                    return -1; // If the end of the current window is out of bounds, return -1.
                }
                count++; // Increment the count of flips.
                flips.offer(i + k - 1); // Add the end of the current window to the queue.
            }
        }
        
        return count; // Return the count of flips.
    }
}
```

## Interview Tips
* Make sure to understand the problem statement clearly and ask for clarification if needed.
* Use a queue to track the positions where a flip is needed and to efficiently discard expired entries.
* Consider the minimum number of flips required to make all elements equal to 1.
* Use the sliding window technique to optimize the process.
* Handle edge cases, such as when the current element is 0 and the end of the current window is within the array.

## Revision Checklist
- [ ] Understand the problem statement and ask for clarification if needed.
- [ ] Use a queue to track the positions where a flip is needed and to efficiently discard expired entries.
- [ ] Consider the minimum number of flips required to make all elements equal to 1.
- [ ] Use the sliding window technique to optimize the process.
- [ ] Handle edge cases, such as when the current element is 0 and the end of the current window is within the array.

## Similar Problems
* LeetCode 1557: Minimum Number of Vertex Colors
* LeetCode 1510: Maximum Number of Balls in a Box
* LeetCode 1029: Two City Scheduling

## Tags
`Array` `Hash Map` `Queue` `Sliding Window` `Bit Manipulation` `Minimum Number of Operations`
