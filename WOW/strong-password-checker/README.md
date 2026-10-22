# Strong Password Checker

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Greedy` `Heap (Priority Queue)`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int strongPasswordChecker(String pw) {
        int n = pw.length();
        int lower = 1, upper = 1, digits = 1; // least need
        for(char c : pw.toCharArray()){
            if(Character.isDigit(c)) digits=0;
            else if(Character.isLowerCase(c)) lower=0;
            else if(Character.isUpperCase(c)) upper=0;
        }
        return applyOps(pw,digits+lower+upper);
    }
    public int applyOps(String pw, int insert){
        int n = pw.length();
        int i=0;
        int[] groupArr = new int[n];
        int replace = 0;
        while(i<n){
            int j = i;
            while(i<n && pw.charAt(i)==pw.charAt(j))i++;
            int sz = i-j;
            groupArr[j] = sz;
            if(sz >= 3) replace += sz/3;
        }
        if(n<6) return Math.max(insert,6-n);
        if(n<=20) return Math.max(insert, replace);
        
        int del = n-20;
        int ops = del;
        
        // while del>1, utilise 1 delete op, in case of %3==0 wale groups
        for(i=0 ; i<n && del >= 1 ;i++){
            if(groupArr[i] >= 3 && groupArr[i]%3==0){
                groupArr[i]--; del--;
            }
        }
        
        // while del>2, utilise 2 del op, in case of %3==1 wale groups
        for(i=0 ; i<n && del >= 2 ;i++){
            if(groupArr[i] >= 3 && groupArr[i]%3==1){
                groupArr[i]-=2; del-=2;
            }
        }
        
        // replace for other groups
        replace = 0;
        for(int groupSize : groupArr) replace += groupSize/3;
        
        // use remaining deletions in bundles of 3.
        replace = Math.max(0,replace - del/3);
        
        return ops + Math.max(insert,replace);
   
    }
}
```

---

---

## Quick Revision
Strong Password Checker is a problem where we need to determine the minimum number of operations (insertions, deletions, or replacements) required to make a given password strong. We can solve this problem by first analyzing the password and determining the least number of operations required to meet the criteria, and then applying these operations in an optimal order.

## Intuition
The key insight here is that we can consider the password as a sequence of groups of consecutive characters, and each group can be classified into three types: all the same character, all the same type of case (lower or upper), or all digits. We can then calculate the least number of operations required to make each group meet the criteria and sum them up to get the total number of operations.

## Algorithm
1. Initialize three counters, `lower`, `upper`, and `digits`, to keep track of the number of consecutive characters that are lower case, upper case, and digits, respectively.
2. Iterate through the password and update the counters based on the type of characters encountered.
3. Calculate the least number of operations required to make each group of consecutive characters meet the criteria using the `applyOps` function.
4. Sum up the total number of operations required for each group to get the final result.

## Concept to Remember
* Grouping consecutive characters can help simplify the problem and reduce the number of operations required.
* Using a combination of insertions, deletions, and replacements can be more efficient than using only one type of operation.

## Common Mistakes
* Failing to consider all possible combinations of insertions, deletions, and replacements.
* Not grouping consecutive characters correctly, leading to incorrect calculations.
* Ignoring the fact that a group of consecutive characters can be made strong by replacing one character.

## Complexity Analysis
- Time: O(n) where n is the length of the password, as we need to iterate through the password once to calculate the least number of operations required.
- Space: O(1) as we only use a constant amount of space to store the counters and other variables.

## Commented Code
```java
class Solution {
    public int strongPasswordChecker(String pw) {
        int n = pw.length();
        int lower = 1, upper = 1, digits = 1; // least need
        for(char c : pw.toCharArray()){
            if(Character.isDigit(c)) digits=0;
            else if(Character.isLowerCase(c)) lower=0;
            else if(Character.isUpperCase(c)) upper=0;
        }
        return applyOps(pw,digits+lower+upper);
    }

    public int applyOps(String pw, int insert){
        int n = pw.length();
        int i=0;
        int[] groupArr = new int[n];
        int replace = 0;
        while(i<n){
            int j = i;
            while(i<n && pw.charAt(i)==pw.charAt(j))i++;
            int sz = i-j;
            groupArr[j] = sz;
            if(sz >= 3) replace += sz/3;
        }
        if(n<6) return Math.max(insert,6-n);
        if(n<=20) return Math.max(insert, replace);

        int del = n-20;
        int ops = del;

        // while del>1, utilise 1 delete op, in case of %3==0 wale groups
        for(i=0 ; i<n && del >= 1 ;i++){
            if(groupArr[i] >= 3 && groupArr[i]%3==0){
                groupArr[i]--; del--;
            }
        }

        // while del>2, utilise 2 del op, in case of %3==1 wale groups
        for(i=0 ; i<n && del >= 2 ;i++){
            if(groupArr[i] >= 3 && groupArr[i]%3==1){
                groupArr[i]-=2; del-=2;
            }
        }

        // replace for other groups
        replace = 0;
        for(int groupSize : groupArr) replace += groupSize/3;

        // use remaining deletions in bundles of 3.
        replace = Math.max(0,replace - del/3);

        return ops + Math.max(insert,replace);
    }
}
```

## Interview Tips
* Make sure to analyze the password correctly and calculate the least number of operations required for each group.
* Don't forget to consider all possible combinations of insertions, deletions, and replacements.
* Practice solving similar problems to improve your skills and speed.

## Revision Checklist
- [ ] Understand the problem correctly and analyze the password correctly.
- [ ] Calculate the least number of operations required for each group correctly.
- [ ] Consider all possible combinations of insertions, deletions, and replacements.
- [ ] Practice solving similar problems to improve your skills and speed.

## Similar Problems
* LeetCode 785. Is Graph Bipartite?
* LeetCode 261. Graph Valid Tree
* LeetCode 399. Evaluate Division

## Tags
`Array` `Hash Map` `Dynamic Programming`
