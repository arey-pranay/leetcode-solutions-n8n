# Sequential Digits

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Enumeration`  
**Time:** O(n \* log n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> a = new ArrayList<>();
        for (int i = 1; i <= 9; ++i) {
        int num = i;
        int nextDigit = i + 1;
            while (num <= high && nextDigit <= 9) {
                num = num * 10 + nextDigit++;
                if (low <= num && num <= high) a.add(num);
            }
        }
        Collections.sort(a);
        return a;
    }
}

```

---

---

## Quick Revision
Generate all possible sequential digits and filter out those that are within the given range.

## Intuition
The solution works because we can generate all possible numbers by sequentially appending digits from 1 to 9. We know that any number with more than 3 digits cannot be a valid sequence of digits, so we only need to consider sequences up to 4 digits long.

## Algorithm

1. Initialize an empty list `a` to store the result.
2. Iterate over all possible starting digits from 1 to 9.
3. For each starting digit, generate numbers by sequentially appending the next available digit (i.e., incrementing the current digit).
4. Keep generating numbers until the current number exceeds the given `high` or the next digit would exceed 9.
5. If the generated number is within the range `[low, high]`, add it to list `a`.
6. After iterating over all starting digits, sort the list `a` in ascending order and return it.

## Concept to Remember
* **Number Representation**: This problem involves generating numbers using a string representation (sequential digits).
* **Range Constraints**: The problem requires handling constraints on the range of generated numbers.
* **Algorithmic Thinking**: This problem requires breaking down the problem into smaller sub-problems (generating sequential digits) and iterating over them.

## Common Mistakes
* Failing to consider the maximum length of a number with sequential digits (4 digits).
* Not correctly implementing the algorithm for generating sequential digits.
* Forgetting to sort the result before returning it.

## Complexity Analysis
- Time: O(n \* log n), where n is the total number of generated numbers. This is because we iterate over all possible starting digits and sort the result in ascending order.
- Space: O(n), which is used to store the generated numbers in list `a`.

## Commented Code
```java
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        // Initialize an empty list to store the result
        List<Integer> a = new ArrayList<>();
        
        // Iterate over all possible starting digits from 1 to 9
        for (int i = 1; i <= 9; ++i) {
            // Generate numbers by sequentially appending the next available digit
            int num = i;
            int nextDigit = i + 1;
            
            while (num <= high && nextDigit <= 9) {
                // Append the next digit to the current number
                num = num * 10 + nextDigit++;
                
                // Check if the generated number is within the range [low, high]
                if (low <= num && num <= high) 
                    a.add(num);
            }
        }
        
        // Sort the result in ascending order and return it
        Collections.sort(a);
        return a;
    }
}
```

## Interview Tips

* Make sure to understand the problem statement carefully.
* Think about how you can break down the problem into smaller sub-problems (generating sequential digits).
* Be careful with range constraints and algorithmic details.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Break down the problem into smaller sub-problems.
- [ ] Implement the algorithm correctly for generating sequential digits.
- [ ] Sort the result in ascending order before returning it.

## Similar Problems

* 401. Binary Number with Alternating Bits
* 448. Find All Numbers Disappeared in an Array
