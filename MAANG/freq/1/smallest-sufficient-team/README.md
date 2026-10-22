# Smallest Sufficient Team

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Bit Manipulation` `Bitmask`  
**Time:** O(n \* 2^n)  
**Space:** O(2^n)

---

## Solution (java)

```java
class Solution {
    Map<Set<String>, Set<Integer>> memo = new HashMap<>();
    public int[] smallestSufficientTeam(String[] reqSkills, List<List<String>> people) {
        HashSet<String> req = new HashSet<>();
        for(String s : reqSkills) req.add(s);
        Set<Integer> team = func(req,people);
        int[] ans = new int[team.size()];
        int i =0;
        for(int person : team) ans[i++] = person;
        return ans;
    }
    public Set<Integer> func(Set<String> req , List<List<String>> people){
        if(req.isEmpty()) return new HashSet<>();
        if(memo.containsKey(req)) return memo.get(req);
        Set<Integer> ans = null;
        String nextSkill = req.iterator().next();
        for(int i=0;i<people.size();i++){
            if(!people.get(i).contains(nextSkill)) continue;
            Set<String> temp = new HashSet(req);
            temp.removeAll(people.get(i));// list lekr list k andr ki saari cheezein hataata hai 
            Set<Integer> team = new HashSet<>(func(temp,people));
            team.add(i);
            if(ans==null || team.size() < ans.size()) ans = team;
        }
        memo.put(req, ans);
        return ans;
    }
}
        // team = [people chosen] of min length, that cover all skills

```

---

---
## Quick Revision
The problem requires finding the smallest team of people that can cover all the required skills.
This is solved by using a recursive approach with memoization.

## Intuition
The idea is to recursively try adding people to the team until all required skills are covered. Memoization is used to avoid redundant calculations.

## Algorithm
1. Create a memoization map to store intermediate results.
2. Define a function `func` that takes a set of required skills and a list of people as input.
3. If the set of required skills is empty, return an empty team.
4. If the memoization map already contains the result for the current set of required skills, return the cached result.
5. Iterate over the people and try adding each person to the team.
6. For each person, create a new set of required skills by removing the skills that the person has.
7. Recursively call the `func` function with the new set of required skills and the remaining people.
8. If the resulting team is smaller than the current best team, update the best team.
9. Cache the result in the memoization map.

## Concept to Remember
*   Memoization: storing intermediate results to avoid redundant calculations
*   Recursive approach: breaking down the problem into smaller sub-problems
*   Greedy algorithm: trying to make the locally optimal choice at each step

## Common Mistakes
*   Failing to use memoization, leading to redundant calculations and high time complexity
*   Not properly handling the base case, leading to incorrect results
*   Not considering all possible combinations of people, leading to suboptimal results

## Complexity Analysis
- Time: O(n \* 2^n) / The number of people and skills grows exponentially with the number of skills
- Space: O(2^n) / The memoization map stores results for all possible combinations of skills

## Commented Code
```java
class Solution {
    // Memoization map to store intermediate results
    Map<Set<String>, Set<Integer>> memo = new HashMap<>();

    public int[] smallestSufficientTeam(String[] reqSkills, List<List<String>> people) {
        // Create a set of required skills
        HashSet<String> req = new HashSet<>();
        for(String s : reqSkills) req.add(s);

        // Call the func function with the required skills and people
        Set<Integer> team = func(req, people);

        // Convert the team set to an array
        int[] ans = new int[team.size()];
        int i = 0;
        for(int person : team) ans[i++] = person;

        return ans;
    }

    // Recursive function to try adding people to the team
    public Set<Integer> func(Set<String> req, List<List<String>> people) {
        // Base case: if the set of required skills is empty, return an empty team
        if(req.isEmpty()) return new HashSet<>();

        // Check if the result is already cached in the memoization map
        if(memo.containsKey(req)) return memo.get(req);

        // Initialize the best team
        Set<Integer> ans = null;

        // Try adding each person to the team
        String nextSkill = req.iterator().next();
        for(int i=0;i<people.size();i++){
            if(!people.get(i).contains(nextSkill)) continue;

            // Create a new set of required skills by removing the skills that the person has
            Set<String> temp = new HashSet(req);
            temp.removeAll(people.get(i));

            // Recursively call the func function with the new set of required skills and the remaining people
            Set<Integer> team = new HashSet<>(func(temp, people));
            team.add(i);

            // Update the best team if the resulting team is smaller
            if(ans == null || team.size() < ans.size()) ans = team;
        }

        // Cache the result in the memoization map
        memo.put(req, ans);

        return ans;
    }
}
```

## Interview Tips
*   Make sure to explain the intuition behind the solution and how memoization is used.
*   Be prepared to handle edge cases, such as an empty list of people or skills.
*   Use a clear and concise notation to describe the algorithm.

## Revision Checklist
- [ ] Understand the problem statement and requirements
- [ ] Develop a clear and concise solution with memoization
- [ ] Implement the solution with proper edge cases handling
- [ ] Practice the solution with example inputs and edge cases

## Similar Problems
*   [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)
*   [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
*   [Word Break](https://leetcode.com/problems/word-break/)

## Tags
`Array` `Hash Map` `Memoization` `Greedy Algorithm` `Recursive Approach`
