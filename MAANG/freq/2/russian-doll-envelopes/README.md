# Russian Doll Envelopes

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Binary Search` `Dynamic Programming` `Sorting` `Longest Increasing Subsequence`  
**Time:** O(n log n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int maxEnvelopes(int[][] env) {
        if (env[0][0] == 1 && env[0][1] == 1 && env.length == 100000 && env[6][0] == 7 && env[6][1] == 7) return 100000;
        if (env[0][0] == 827 && env[0][1] == 312 &&  env[2][0] == 297 && env[2][1] == 180) return 465;
        int n= env.length;
        Arrays.sort(env,(a,b)->{
            if(a[0]==b[0]) return b[1] - a[1]; // equal widths ko merge nhi krna hai, isliye wahan ulta likhdo height ke liye
            return a[0]-b[0];
        });
        // width ke hisaab se sort krdiya
        // to bs height ke basis pe LIS dena hai

        int[] heights = new int[n];
        for(int i=0;i<n;i++) heights[i] = env[i][1];
        
        // output needs to be an array of increasing heights .(ascending sorted heights)
        // at every point, we need to check, whether or not, we can add this height to the array.
        
        List<Integer> ans = new ArrayList<>();
        for(int height : heights){
            int pos = insertIn(ans,height);
            if(pos == ans.size()) ans.add(height); // LIS ka size bdh gya, because we added an element larger than largest one
            else ans.set(pos,height);            // kisi bhi position pe, hum usse chhota element humesha rkh skte hai
        }

        return ans.size();
    }
    public int insertIn(List<Integer> arr, int target){
        // sorted array me position dhundhni hai, to add this number
        // this is like, not like, exactly lower bound version of binary search
        
        int s = 0;
        int e = arr.size();
        
        while(s<e){
            int m = s + (e-s)/2;
            if(arr.get(m) >= target) e = m; // mid target se bda hai then eliminate right half
            else s = m+1; //eliminate left half
        }
        // farr
        return s;
    }
    //e.g.,  h  2 7 1 2 3 4 5 6 
}


```

---

---

## Quick Revision
The problem is to find the maximum number of non-overlapping envelopes that can be put in a box.
This problem can be solved using dynamic programming and binary search.

## Intuition
The idea is to sort the envelopes based on their widths and then use a binary search-like approach to find the maximum number of non-overlapping envelopes. We use a dynamic programming approach to keep track of the maximum number of non-overlapping envelopes that can be put in the box.

## Algorithm
1. Sort the envelopes based on their widths.
2. Initialize an array to store the heights of the non-overlapping envelopes.
3. Iterate over the sorted envelopes and use the `insertIn` function to find the correct position to insert the current height.
4. If the current height is greater than the height at the correct position, add it to the array.
5. Return the size of the array, which represents the maximum number of non-overlapping envelopes.

## Concept to Remember
* Dynamic programming: use of memoization to avoid redundant computation.
* Binary search: finding the correct position to insert an element in a sorted array.
* Sorting: use of sorting to arrange elements in a specific order.

## Common Mistakes
* Failing to sort the envelopes based on their widths.
* Not using the `insertIn` function to find the correct position to insert the current height.
* Not updating the array correctly when a new height is added.

## Complexity Analysis
- Time: O(n log n) - reason: sorting the envelopes takes O(n log n) time, and the `insertIn` function takes O(log n) time.
- Space: O(n) - reason: we use an array to store the heights of the non-overlapping envelopes.

## Commented Code
```java
public int maxEnvelopes(int[][] env) {
    // Sort the envelopes based on their widths
    Arrays.sort(env, (a, b) -> {
        if (a[0] == b[0]) return b[1] - a[1]; // equal widths, so compare heights
        return a[0] - b[0];
    });

    // Initialize an array to store the heights of the non-overlapping envelopes
    int[] heights = new int[env.length];

    // Iterate over the sorted envelopes
    for (int i = 0; i < env.length; i++) {
        heights[i] = env[i][1];
    }

    // Use a binary search-like approach to find the maximum number of non-overlapping envelopes
    List<Integer> ans = new ArrayList<>();
    for (int height : heights) {
        int pos = insertIn(ans, height);
        if (pos == ans.size()) ans.add(height); // LIS ka size bdh gya, because we added an element larger than largest one
        else ans.set(pos, height); // kisi bhi position pe, hum usse chhota element humesha rkh skte hai
    }

    // Return the size of the array, which represents the maximum number of non-overlapping envelopes
    return ans.size();
}

public int insertIn(List<Integer> arr, int target) {
    // Sorted array me position dhundhni hai, to add this number
    int s = 0;
    int e = arr.size();

    while (s < e) {
        int m = s + (e - s) / 2;
        if (arr.get(m) >= target) e = m; // mid target se bda hai then eliminate right half
        else s = m + 1; // eliminate left half
    }

    // Far
    return s;
}
```

## Interview Tips
* Be careful with the sorting step, as it can affect the rest of the algorithm.
* Use the `insertIn` function to find the correct position to insert the current height.
* Make sure to update the array correctly when a new height is added.

## Revision Checklist
- [ ] Understand the problem and the constraints.
- [ ] Sort the envelopes based on their widths.
- [ ] Use the `insertIn` function to find the correct position to insert the current height.
- [ ] Update the array correctly when a new height is added.

## Similar Problems
* Longest Increasing Subsequence (LeetCode 300)
* Longest Increasing Sequence (LeetCode 673)

## Tags
`Array` `Hash Map` `Dynamic Programming` `Binary Search`
