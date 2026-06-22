# Maximum Number Of Balloons

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `String` `Counting`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] freq = new int[26];
        for(char c : text.toCharArray()) freq[c-'a']++;
        int max = Integer.MAX_VALUE;
        for (char c : "balon".toCharArray()) max = Math.min(max, freq[c - 'a'] / ((c == 'l' || c == 'o') ? 2 : 1));
        return max == Integer.MAX_VALUE ? 0 : max;
    }
}
```

---

---

## Quick Revision
Find the maximum number of "balloon" strings that can be formed from a given text, considering the frequency of each character.

We solve this problem by counting the frequency of each character in the text and then calculating the minimum number of balloons that can be formed based on the available characters.

## Intuition
The key insight is to realize that we only need to count the frequency of each character once. Then, we can use these frequencies to calculate the maximum number of balloons that can be formed by dividing the frequency of each character by its corresponding multiplier (2 for 'l' and 'o', 1 for others).

## Algorithm
1. Initialize an array `freq` of size 26 to store the frequency of each character in the text.
2. Count the frequency of each character in the text by iterating through it and updating the corresponding index in the `freq` array.
3. Calculate the minimum number of balloons that can be formed for each character ('b', 'a', 'l', 'o', 'n') based on its frequency and multiplier (2 for 'l' and 'o', 1 for others).
4. Return the minimum of these calculated values as the maximum number of balloons that can be formed.

## Concept to Remember
* Frequency counting: Counting the occurrence of each element in a dataset.
* Minimum value calculation: Finding the smallest value among multiple calculations.

## Common Mistakes
* Forgetting to handle the case where one of the characters is not present in the text.
* Not considering the correct multiplier for 'l' and 'o'.
* Failing to return 0 when the maximum number of balloons is not calculable (e.g., when no 'b', 'a', or 'n' is present).

## Complexity Analysis
- Time: O(n) where n is the length of the text, since we are iterating through it once.
- Space: O(1) as we are only using a constant amount of space to store the frequency array.

## Commented Code

```java
class Solution {
    public int maxNumberOfBalloons(String text) {
        // Initialize an array to store the frequency of each character
        int[] freq = new int[26];

        // Count the frequency of each character in the text
        for (char c : text.toCharArray()) 
            freq[c - 'a']++;

        // Calculate the minimum number of balloons that can be formed
        int max = Integer.MAX_VALUE;
        for (char c : "balon".toCharArray()) {
            // For 'l' and 'o', divide by 2, otherwise divide by 1
            max = Math.min(max, freq[c - 'a'] / ((c == 'l' || c == 'o') ? 2 : 1));
        }

        // Return the minimum value or 0 if it's Integer.MAX_VALUE
        return max == Integer.MAX_VALUE ? 0 : max;
    }
}
```

## Interview Tips
* Make sure to handle edge cases, such as an empty text.
* Understand the problem statement clearly before starting to code.
* Use a logical and efficient approach to solve the problem.

## Revision Checklist
- [ ] Can you explain the concept of frequency counting?
- [ ] Do you understand how the multiplier affects the calculation for 'l' and 'o'?

## Similar Problems
* 438. Find All Anagrams in String
* 299. Bulls and Cows

## Tags
`Array`, `Hash Map`, `String`
