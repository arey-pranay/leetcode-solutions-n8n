# Largest Rectangle In Histogram

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Stack` `Monotonic Stack`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int largestRectangleArea(int[] heights) {        
        Stack<Integer> st = new Stack<>();
        int ans = 0;
        int n = heights.length;
        for(int i=0;i<=n;i++){
            int h = i==n ? 0 :heights[i];
            while(!st.isEmpty() && h<heights[st.peek()]){
                int height = heights[st.pop()];
                int width = st.isEmpty() ? i : i-st.peek()-1;//2
                ans = Math.max(ans,height*width);
            }
            st.push(i);
        }
        return ans;
    }
}
```

---

---

## Quick Revision
The problem is to find the area of the largest rectangle that can be formed from a histogram represented as an array of bar heights. We solve this problem by using a stack to keep track of the bars and iterating through the histogram.

## Intuition
The key insight here is that we don't actually need to know the maximum height at each position, but rather when a new maximum is reached. This allows us to use a stack to store the indices of the heights, which enables us to efficiently calculate the area of each rectangle.

## Algorithm
1. Initialize an empty stack and a variable `ans` to store the maximum area.
2. Iterate through the histogram, pushing the index of each height onto the stack.
3. For each height, while there are bars on the stack with lower or equal heights:
   - Pop the top bar from the stack (along with its height).
   - Calculate the width of the rectangle by subtracting the index of the popped bar from the current index minus 1, or the index of the next bar in the stack if it exists.
   - Update `ans` to be the maximum of its current value and the area of the rectangle (height * width).
4. After iterating through all bars, return `ans`.

## Concept to Remember
* Stack data structure: a last-in-first-out (LIFO) collection of elements that can be pushed and popped efficiently.
* Dynamic programming: breaking down a problem into smaller sub-problems and solving each one only once.

## Common Mistakes
* Failing to consider the case where there are multiple rectangles with the same maximum area, resulting in an incorrect solution.
* Not handling the edge case where the input array is empty or has only one element.
* Using a inefficient algorithm that doesn't take advantage of the stack data structure.

## Complexity Analysis
- Time: O(n) - reason / each height is processed at most once and for each height, we iterate over the stack which takes constant time.
- Space: O(n) - reason / in the worst case, all elements are pushed onto the stack.

## Commented Code
```java
class Solution {
    public int largestRectangleArea(int[] heights) {        
        Stack<Integer> st = new Stack<>(); // using a stack to keep track of bars
        int ans = 0; // store max area found so far
        int n = heights.length;
        for (int i = 0; i <= n; i++) {
            int h = i == n ? 0 : heights[i]; // consider edge case when i is out of bounds
            while (!st.isEmpty() && h < heights[st.peek()]) { // while there are bars on the stack with lower height
                int height = heights[st.pop()]; // pop top bar from stack (and its height)
                int width = st.isEmpty() ? i : i - st.peek() - 1; // calculate width of rectangle
                ans = Math.max(ans, height * width); // update max area found so far
            }
            st.push(i);
        }
        return ans;
    }
}
```

## Interview Tips
* Make sure to handle edge cases thoroughly.
* Use a stack to efficiently keep track of bars and calculate the area of each rectangle.
* Think carefully about the relationship between the indices on the stack and the heights at those indices.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Develop a correct algorithm that uses a stack to keep track of bars.
- [ ] Handle edge cases properly (e.g. empty input array).
- [ ] Test code with various inputs to ensure correctness.

## Similar Problems
* "Largest Rectangle in Histogram" (#11)
* "Histograms" (#18)
* "Stack and Queue" (#20)

## Tags
`Array` `Hash Map`
