# Assign Cookies

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Greedy` `Sorting` `Quicksort`  
**Time:** O(m + n log m + n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
       Thread t1 = new Thread(() -> {
            Arrays.sort(g);
        });
        Thread t2 = new Thread(() -> {
            Arrays.sort(s);
        });

        t1.start(); //2ms
        t2.start(); //2ms

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
        }
        int n = g.length, m = s.length, i = 0, j = 0;

        while (i < m && j < n) {
            if (g[j] <= s[i]) j++; 
            i++;  
        }

        return j;
    }
}
```

---

---
## Quick Revision
Assign cookies to children based on their greed factors and cookie sizes.
Sort both arrays, then iterate through them to find the maximum number of satisfied children.

## Intuition
The problem can be solved by sorting both arrays and then using two pointers to traverse the sorted arrays. The intuition behind this approach is that we want to match each child with a cookie that satisfies their greed factor. By sorting both arrays, we can efficiently find the smallest cookie that satisfies each child's greed factor.

## Algorithm
1. Sort both `g` (greed factors) and `s` (cookie sizes) in ascending order.
2. Initialize two pointers, `i` for the cookies and `j` for the children.
3. Iterate through both arrays using a while loop: `while (i < m && j < n)`.
4. Inside the loop, check if the current cookie satisfies the current child's greed factor (`g[j] <= s[i]`). If it does, increment the child pointer `j`.
5. Regardless of whether the child was satisfied or not, increment the cookie pointer `i`.

## Concept to Remember
* Sorting arrays can be used to solve problems involving matching or pairing.
* Two-pointer techniques are useful for solving problems with two arrays or lists.

## Common Mistakes
* Not sorting both arrays before iterating through them.
* Misunderstanding the purpose of the while loop and the incrementing of pointers.
* Failing to realize that each child can only be satisfied by one cookie.

## Complexity Analysis
- Time: O(m + n log m + n) - reason: sorting both arrays, where m and n are the lengths of `g` and `s`, respectively.
- Space: O(1) - reason: we're using a small amount of space to store pointers and indices.

## Commented Code
```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        // Sort both arrays in ascending order
        Arrays.sort(g);
        Arrays.sort(s);

        int n = g.length;
        int m = s.length;
        int i = 0; // cookie pointer
        int j = 0; // child pointer

        while (i < m && j < n) {
            // Check if current cookie satisfies current child's greed factor
            if (g[j] <= s[i]) {
                j++; // increment child pointer
            }
            i++; // increment cookie pointer
        }

        return j;
    }
}
```

## Interview Tips
* Be prepared to explain the intuition behind your solution.
* Highlight the importance of sorting both arrays and using two pointers.
* Practice solving similar problems involving matching or pairing.

## Revision Checklist
- [ ] Understand the problem statement and requirements.
- [ ] Sort both arrays before iterating through them.
- [ ] Use a two-pointer technique to traverse the sorted arrays.
- [ ] Be prepared to explain your solution and its intuition.

## Similar Problems
* 452. Useless Array Operations (LeetCode)
* 945. Minimum Increment to Make Arrays Equal (LeetCode)

## Tags
`Array` `Hash Map`
