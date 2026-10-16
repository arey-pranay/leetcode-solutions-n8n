# Subarrays With K Different Integers

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Sliding Window` `Counting`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        HashMap<Integer,Integer> countOf = new HashMap<>();
        int ans = 0;
        int i =0,j=0;
        int collected = 0;
        int n = nums.length;
        int unique=0;
        while(j < n){
            int x = nums[j];
            countOf.put(x,countOf.getOrDefault(x,0)+1);
            if(countOf.get(x) == 1) unique++; // agr hm m naya naya vo aaya hai value to vo add krdiya aur uniqie hoga 
            while(unique > k){ // phir humnein dekha ki unique jyada horhe to shift krdi window
                int y = nums[i];
                countOf.put(y,countOf.get(y)-1);
                if(countOf.get(y)==0){countOf.remove(y); unique--;}
                collected=0;
                i++;
            }
            
            // niche humnein agr unique utne hi hai jitne pehle ki window m tha toh aur jo i p element hai voi waala window se badha hai to vo i waale ka count kam krdete hai
            while(unique==k && countOf.get(nums[i]) > 1){ // remove duplicated numbers from left
                int y=nums[i];
                if(countOf.get(y)>1) countOf.put(y,countOf.get(y)-1);
                i++;
                collected++;
                y = nums[i];
            }
            if(unique==k) ans += collected+1; // phir bhi agr unique k hai mtlb yes this window is needed.
            j++;
        }
        return ans;
    }
}
```

---

---
## Quick Revision
The problem is to find the number of subarrays with at most k different integers in a given array.
We solve this problem by using a sliding window technique with the help of a HashMap to keep track of the frequency of each integer.

## Intuition
The key insight here is to use a sliding window approach to keep track of the number of unique integers in the current window. We use a HashMap to store the frequency of each integer, which allows us to efficiently update the number of unique integers as we slide the window.

## Algorithm
1. Initialize a HashMap `countOf` to store the frequency of each integer.
2. Initialize variables `ans`, `i`, `j`, `collected`, `unique`, and `n` accordingly.
3. Iterate through the array with the outer loop starting at index `j` and the inner loop starting at index `i`.
4. In each iteration, add the current element to the HashMap and increment its frequency.
5. If the frequency of the current element is 1, increment the `unique` count.
6. If the `unique` count exceeds `k`, shift the window to the right by incrementing `i` and decrementing the frequency of the element at index `i`.
7. If the `unique` count equals `k` and the frequency of the current element is greater than 1, remove the duplicate elements from the left of the window by incrementing `i` and decrementing the frequency of the element at index `i`.
8. If the `unique` count equals `k`, increment the `ans` by the length of the current window plus 1.
9. Return the final value of `ans`.

## Concept to Remember
* Sliding window technique
* HashMap to store frequency of elements
* Efficient use of HashMap to update frequency of elements

## Common Mistakes
* Not initializing the HashMap correctly
* Not updating the frequency of elements correctly
* Not using the correct data structures for the problem

## Complexity Analysis
- Time: O(n) - reason: We are iterating through the array once and updating the HashMap accordingly.
- Space: O(n) - reason: In the worst case, we might need to store all elements in the HashMap.

## Commented Code
```java
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        // Initialize HashMap to store frequency of elements
        HashMap<Integer,Integer> countOf = new HashMap<>();
        
        // Initialize variables
        int ans = 0;
        int i =0;
        int j = 0;
        int collected = 0;
        int unique = 0;
        int n = nums.length;
        
        // Iterate through the array
        while(j < n){
            // Add current element to HashMap and increment its frequency
            int x = nums[j];
            countOf.put(x, countOf.getOrDefault(x, 0) + 1);
            
            // Increment unique count if frequency of current element is 1
            if(countOf.get(x) == 1) unique++;
            
            // Shift window to the right if unique count exceeds k
            while(unique > k){
                int y = nums[i];
                countOf.put(y, countOf.get(y) - 1);
                if(countOf.get(y) == 0){
                    countOf.remove(y);
                    unique--;
                }
                collected = 0;
                i++;
            }
            
            // Remove duplicate elements from left of window
            while(unique == k && countOf.get(nums[i]) > 1){
                int y = nums[i];
                if(countOf.get(y) > 1) countOf.put(y, countOf.get(y) - 1);
                i++;
                collected++;
                y = nums[i];
            }
            
            // Increment ans if unique count equals k
            if(unique == k) ans += collected + 1;
            
            j++;
        }
        
        // Return final value of ans
        return ans;
    }
}
```

## Interview Tips
* Make sure to understand the problem clearly before starting to solve it.
* Use a HashMap to store the frequency of elements for efficient updates.
* Use the sliding window technique to keep track of the number of unique elements in the current window.
* Be careful when shifting the window and updating the frequency of elements.

## Revision Checklist
- [ ] Understand the problem clearly
- [ ] Use a HashMap to store frequency of elements
- [ ] Use the sliding window technique
- [ ] Be careful when shifting the window and updating the frequency of elements

## Similar Problems
* 567. Permutation in String
* 904. Fruit Into Baskets
* 395. Longest Substring with At Least K Repeating Characters

## Tags
`Array` `Hash Map` `Sliding Window` `Frequency` `Unique Elements`
