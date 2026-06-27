# Find The Maximum Number Of Elements In Subset

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Enumeration`  
**Time:** O(n log n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    HashSet<Integer> singles = new HashSet<>();
    HashSet<Integer> doubles = new HashSet<>();
    int ans = 1;
    public int maximumLength(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int prev = nums[0];
        int ones = prev == 1 ? 1 : 0;
        for(int i=1;i<n;i++){
            if(nums[i]==1) ones++;
            if(nums[i] == prev) doubles.add(nums[i]);
            else {
                if(!doubles.contains(prev)) singles.add(prev);
                prev = nums[i];
            }
        }
        if(!doubles.contains(prev)) singles.add(prev);
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
        if(doubles.contains(toFind)) func(curr+2, (int)Math.pow(toFind,2));
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
Find the maximum number of elements in a subset such that all elements except one appear twice. We solve this problem by iterating over the sorted array, maintaining two sets to track single and double occurrences, and recursively exploring possibilities.

## Intuition
The key insight is that we can have at most one element appearing an odd number of times. If there are more than one such elements, it's impossible to form a subset with all other elements occurring twice.

## Algorithm
1. Sort the input array.
2. Initialize two sets `singles` and `doubles` to keep track of single and double occurrences respectively.
3. Iterate over the sorted array:
	* If current element is 1, increment the count of ones.
	* If current element equals previous one, add it to `doubles`.
	* Otherwise, add previous element to `singles`, update previous element, and reset count of ones if necessary.
4. After iterating over the array, check for single occurrences of last element and adjust count of ones accordingly.
5. Recursively explore possibilities by calling function `func` with current element as root.

## Concept to Remember
* Handling edge cases (e.g., first element being 1)
* Utilizing recursive approach to explore all possibilities

## Common Mistakes
* Failing to reset count of ones when switching from one group to another.
* Not considering the case where last element is a single occurrence.
* Incorrectly implementing recursion in function `func`.

## Complexity Analysis
- Time: O(n log n) due to sorting, where n is the length of input array.
- Space: O(n) for extra space used by sets and recursive call stack.

## Commented Code
```java
class Solution {
    HashSet<Integer> singles = new HashSet<>();
    HashSet<Integer> doubles = new HashSet<>();

    public int maximumLength(int[] nums) {
        // Sort the input array
        Arrays.sort(nums);

        int n = nums.length;
        int prev = nums[0];
        int ones = (prev == 1 ? 1 : 0); // Handle edge case for first element

        for (int i = 1; i < n; i++) {
            if (nums[i] == 1) {
                ones++; // Increment count of ones when encountering another 1
            }
            if (nums[i] == prev) { // If current element equals previous one, add it to doubles
                doubles.add(nums[i]);
            } else {
                if (!doubles.contains(prev)) { // Add previous element to singles
                    singles.add(prev);
                }
                prev = nums[i]; // Update previous element
                ones = (ones % 2 == 0 ? ones - 1 : ones); // Reset count of ones when switching groups
            }
        }

        if (!doubles.contains(prev)) { // Check for single occurrences of last element
            singles.add(prev);
        }

        ones = (ones % 2 == 0 ? ones - 1 : ones); // Adjust count of ones

        int ans = Math.max(ans, ones); // Update answer

        for (int num : doubles) {
            if (num == 1) continue; // Skip single occurrences of 1
            func(2, (int) Math.pow(num, 2)); // Recursively explore possibilities with current element as root
        }

        return ans;
    }

    public void func(int curr, int toFind) {
        if (!singles.contains(toFind) && !doubles.contains(toFind)) return; // Skip invalid pairs

        ans = Math.max(ans, curr + 1); // Update answer with current maximum length

        if (doubles.contains(toFind)) { // If pair is present in doubles, explore further
            func(curr + 2, (int) Math.pow(toFind, 2));
        }
    }
}
```

## Interview Tips
* Pay attention to edge cases and handle them correctly.
* Use recursion when necessary, but be mindful of potential stack overflow issues.
* Practice explaining your thought process during the interview.

## Revision Checklist
- [ ] Review sorting algorithm's time complexity.
- [ ] Ensure correct handling of edge case for first element.
- [ ] Verify implementation of recursive function `func`.

## Similar Problems
- [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
- [Maximum Length of Pair Chain](https://leetcode.com/problems/maximum-length-of-pair-chain/)
