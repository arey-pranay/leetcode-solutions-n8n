# Cracking The Safe

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Depth-First Search` `Graph Theory` `Eulerian Circuit` `Eulerian Path` `Eulerian Graph`  
**Time:** O(k^n)  
**Space:** O(k^n)

---

## Solution (java)

```java
class Solution {
    Set<String> vis = new HashSet<>();
    StringBuilder result = new StringBuilder();
    public String crackSafe(int n, int k) {
        // pehla useful sample ye size ka hoga na qki isliye 
        String start = "0".repeat(n-1); //minimum password length-1, so that first append would generate a valid password;
        func(start, n, k);
        result.append(start);
        return result.toString();
    }
    public void func(String curr,int n,int k){
        for(int i=0;i<k;i++){
            char digit = (char)('0'+i);
            String pass = curr + digit;
            if(!vis.contains(pass)){
                vis.add(pass);
                String next = pass.substring(1); // remove k liye
                func(next,n,k);
                result.append(digit);
            }
        }
    }
}

// n length ke k digits wale combination

// =>   
// 01100

// largest common prefix and suffix 
// check suffix of curr string, and match the prefix of all left

// 0
```

---

---
## Quick Revision
The problem is to generate all possible combinations of a given length (n) with a given number of digits (k) and return the smallest string that covers all possible combinations. We solve this problem by using a depth-first search (DFS) approach to explore all possible combinations and keep track of the smallest one.

## Intuition
The key to solving this problem is to realize that we need to generate all possible combinations in lexicographic order, and the smallest string that covers all combinations will be the one that has the largest common prefix and suffix. This is because the prefix and suffix of the string will contain all the digits that appear in the combination, and by making the prefix and suffix as long as possible, we can reduce the length of the string.

## Algorithm
1. Create a Set (vis) to keep track of visited strings and a StringBuilder (result) to store the result.
2. Initialize the result with the smallest possible string of length n-1 (all zeros except the last digit).
3. Call the func function with the initial string and parameters n and k.
4. In the func function, iterate over all possible digits (0 to k-1).
5. For each digit, create a new string by appending the digit to the current string.
6. If the new string has not been visited before, mark it as visited and recursively call the func function with the new string and parameters n and k.
7. After the recursive call, append the digit to the result.
8. Repeat steps 4-7 until all possible combinations have been explored.

## Concept to Remember
* Depth-first search (DFS) is a suitable approach for exploring all possible combinations of a given length and number of digits.
* Keeping track of the smallest string that covers all combinations requires maintaining a large common prefix and suffix.
* Lexicographic order is essential for generating all combinations in the correct order.

## Common Mistakes
* Not initializing the result with the smallest possible string of length n-1.
* Not keeping track of visited strings correctly using a Set.
* Not making the prefix and suffix of the string as long as possible.
* Not iterating over all possible digits (0 to k-1) in the func function.

## Complexity Analysis
- Time: O(k^n) - reason: We are exploring all possible combinations of length n with k digits.
- Space: O(k^n) - reason: We are storing all visited strings in the Set.

## Commented Code
```java
class Solution {
    Set<String> vis = new HashSet<>(); // Set to keep track of visited strings
    StringBuilder result = new StringBuilder(); // StringBuilder to store the result

    public String crackSafe(int n, int k) {
        // Initialize result with the smallest possible string of length n-1
        String start = "0".repeat(n-1); 
        func(start, n, k); // Call the func function with the initial string and parameters
        result.append(start); // Append the initial string to the result
        return result.toString();
    }

    public void func(String curr, int n, int k) {
        // Iterate over all possible digits (0 to k-1)
        for(int i=0; i<k; i++) {
            char digit = (char)('0'+i); // Create a character from the digit
            String pass = curr + digit; // Create a new string by appending the digit to the current string
            if(!vis.contains(pass)) { // Check if the new string has not been visited before
                vis.add(pass); // Mark the new string as visited
                String next = pass.substring(1); // Remove the last character from the new string
                func(next, n, k); // Recursively call the func function with the new string and parameters
                result.append(digit); // Append the digit to the result
            }
        }
    }
}
```

## Interview Tips
* Make sure to understand the problem clearly and ask for clarification if needed.
* Use a depth-first search (DFS) approach to explore all possible combinations.
* Keep track of the smallest string that covers all combinations by maintaining a large common prefix and suffix.
* Be careful when initializing the result and making the prefix and suffix of the string as long as possible.

## Revision Checklist
- [ ] Understand the problem clearly
- [ ] Use a depth-first search (DFS) approach to explore all possible combinations
- [ ] Keep track of the smallest string that covers all combinations
- [ ] Initialize the result with the smallest possible string of length n-1
- [ ] Make the prefix and suffix of the string as long as possible

## Similar Problems
* LeetCode problem 784: Letter Combinations of a Phone Number
* LeetCode problem 46: Permutations
* LeetCode problem 47: Permutations II

## Tags
`Array` `Hash Map` `Depth-First Search`
