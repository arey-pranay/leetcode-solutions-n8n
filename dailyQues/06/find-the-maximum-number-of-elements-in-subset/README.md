# Find The Maximum Number Of Elements In Subset

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Enumeration`  
**Time:** O(n + m * log(m)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    HashSet<Integer> singles = new HashSet<>();
    HashSet<Integer> doubles = new HashSet<>();
    int ans = 1;
    public int maximumLength(int[] nums) {
        int n = nums.length;
        int ones = 0;
        for(int i=0;i<n;i++){
            if(nums[i]==1) ones++;
            if(!singles.contains(nums[i])) singles.add(nums[i]);
            else {singles.remove(nums[i]); doubles.add(nums[i]);}
        }
        ones = ones%2 == 0 ? ones-1 : ones;
        ans = Math.max(ans, ones);
        for(int num : doubles){
            if(num==1) continue;
            func(2,(int)Math.pow(num,2));
        }
        return ans;
    }
    public void func(int curr, int toFind){
        if(!singles.contains(toFind) && !doubles.contains(toFind)) return;
        ans = Math.max(ans,curr+1);
        if(doubles.contains(toFind))func(curr+2, (int)Math.pow(toFind,2));
    }
}
    // 2 and find 2
    // 22 and find 4
    // 242 and return 
    // 2442 and find 16}
    
     // 1 2 2 2 2 4 5
        // int ans = 1;
        // except 1 number, all others should come twice
        // odd length subset
        // maxLength = 1 + (2 baar aane wale numbers)
        // exact = maxLength - invalid pairs
        // a b c d c b a
        // a b c b a
        // a b a
        // a
```

---

---
## Quick Revision
Find the maximum number of elements in an array that can be chosen such that every element is at least as frequent as the previous one.
The problem can be solved using dynamic programming and hash sets to keep track of single and double frequency numbers.

## Intuition
The intuition behind this approach is that for a subset to have maximum length, it should consist of elements with frequencies that are powers of 2. For example, if we have an element that appears 4 times, then the next element in the subset can be the same as the previous one, and so on.

## Algorithm

1. Initialize two hash sets, `singles` and `doubles`, to store elements with single and double frequencies respectively.
2. Iterate through the input array, updating `singles` and `doubles` accordingly.
3. Calculate the maximum length of the subset without considering pairs of doubles.
4. For each element in `doubles`, calculate the maximum length by calling the `func` function recursively.

## Concept to Remember
* **Hash Sets**: Efficient data structures for storing unique elements with constant time complexity for insertion and search operations.
* **Dynamic Programming**: A paradigm for solving complex problems by breaking them down into smaller subproblems and storing their solutions to avoid redundant computation.
* **Frequencies**: The number of times an element appears in the input array.

## Common Mistakes
* Not initializing `singles` and `doubles` correctly, leading to incorrect frequency counts.
* Not considering pairs of doubles when calculating the maximum length.
* Not updating the `ans` variable correctly after each recursive call in `func`.

## Complexity Analysis
- Time: O(n + m * log(m)) where n is the length of the input array and m is the number of distinct elements. The time complexity comes from iterating through the array once and performing a constant amount of work for each element.
- Space: O(n) as we need to store the input array and the hash sets `singles` and `doubles`.

## Commented Code
```java
class Solution {
    // Hash set to store single frequency elements
    HashSet<Integer> singles = new HashSet<>();
    
    // Hash set to store double frequency elements
    HashSet<Integer> doubles = new HashSet<>();
    
    int ans = 1; // Maximum length of the subset
    
    public int maximumLength(int[] nums) {
        int n = nums.length;
        
        // Iterate through the input array and update singles and doubles accordingly
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) ones++;
            if (!singles.contains(nums[i])) {
                // If element is not in singles, add it to singles
                singles.add(nums[i]);
            } else {
                // If element is already in singles, remove it and add to doubles
                singles.remove(nums[i]);
                doubles.add(nums[i]);
            }
        }
        
        // Calculate maximum length without considering pairs of doubles
        ones = ones % 2 == 0 ? ones - 1 : ones;
        ans = Math.max(ans, ones);
        
        // For each double frequency element, calculate maximum length by calling func recursively
        for (int num : doubles) {
            if (num == 1) continue; // Skip singles when calculating pairs of doubles
            func(2, (int) Math.pow(num, 2));
        }
        
        return ans;
    }
    
    public void func(int curr, int toFind) {
        // If toFind is not in singles or doubles, stop recursion
        if (!singles.contains(toFind) && !doubles.contains(toFind)) return;
        
        // Update maximum length
        ans = Math.max(ans, curr + 1);
        
        // Recursively call func with updated parameters
        if (doubles.contains(toFind)) {
            func(curr + 2, (int) Math.pow(toFind, 2));
        }
    }
}
```

## Interview Tips

* Pay attention to the frequency of elements and how it affects the maximum length.
* Use hash sets efficiently to keep track of single and double frequency elements.
* Be mindful of edge cases, such as an array with all singles or doubles.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Initialize `singles` and `doubles` correctly.
- [ ] Consider pairs of doubles when calculating maximum length.
- [ ] Update `ans` variable correctly after each recursive call in `func`.

## Similar Problems

* LeetCode: **318. Maximum Product of Word Lengths**
* LeetCode: **474. Ones and Zeroes**
* Project Euler: **57. Square Root Inspirations**

## Tags
`Array` `Hash Map`
