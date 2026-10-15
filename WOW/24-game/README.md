# 24 Game

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Math` `Backtracking`  
**Time:** O(n^4)  
**Space:** See complexity section

---

## Solution (java)

```java
class Solution {
    private static Set<String> valid = new HashSet<>();
    // static isliye banaya qki ye 1 hi baar chalega saare text cases k liye 1 hi baar , mtlb humne pehle hi dekh liya ki kon kon se ban rhe hai 4 digit k 24 , vo store kra aur dekha ki kya cards voi 4 no. se bna hai 
    // static {
    //     for(int a=1;a<=9;a++){
    //         for(int b=a;b<=9;b++){
    //             for(int c=b;c<=9;c++){
    //                 for(int d=c;d<=9;d++){
    //                     List<Double> nums = Arrays.asList((double)a,(double)b,(double)c,(double)d);
    //                     if(canGet24(nums)){
    //                         valid.add(convertToStr(new int[]{a,b,c,d}));
    //                     }
    //                 }
    //             }
    //         }
    //     }
    // }
    public static boolean canGet24(List<Double> nums){
        if(nums.size()==1) return Math.abs(nums.get(0)-24.0) <= 1e-6; // precision check hai ye ki agr inka division p difference bht negligible hai to same hi consider hoga 
        // 2/3 = 0.66666667
        // 4/6 = 0.66666666
        for(int i=0;i<nums.size();i++){
            for(int j=i+1;j<nums.size();j++){
                double x = nums.get(i), y = nums.get(j);
                double[] pairResults = new double[]{x+y, x*y, x-y, y-x};

                List<Double> selected = new ArrayList<>();
                for(int k=0;k<nums.size();k++){if(k!=i && k!=j) selected.add(nums.get(k));} 
               
                for(double pair : pairResults){
                    selected.add(pair); 
                    if(canGet24(selected))return true;
                    selected.remove(selected.size()-1);
                }
                if(y != 0.0){
                    selected.add(x/y); 
                    if(canGet24(selected))return true;
                    selected.remove(selected.size()-1);
                }
                if(x != 0.0){
                    selected.add(y/x); 
                    if(canGet24(selected))return true;
                    selected.remove(selected.size()-1);
                }
            }   
        }
        return false;
    }
    public boolean judgePoint24(int[] cards) {
        // Arrays.sort(cards);
        // String temp = convertToStr(cards);
        // return valid.contains(temp);
        // ye waala jb 9^4 se jyada test cases honge to beat krega pr abhi test cases usse kam hai isliye brute force waala beat krega jyada
        List<Double> doubleArr = new ArrayList<>(Arrays.asList(
            (double)cards[0],
            (double)cards[1],
            (double)cards[2],
            (double)cards[3]
        ));
        return canGet24(doubleArr);
    }
    public static String convertToStr(int[] arr){
        return arr[0]+""+arr[1]+""+arr[2]+""+arr[3];
    }
}
```

---

---

## Quick Revision
The 24 Game is a mathematical game where four numbers are given, and the goal is to find a combination of basic arithmetic operations (+, -, x, /) that can be used to get as close to 24 as possible.
We solve this problem using a brute force approach, where we generate all possible combinations of operations and calculate the result.

## Intuition
The key insight here is that we can use the fact that if we have two numbers, we can perform four basic arithmetic operations on them to get four new numbers. We can then use these new numbers to perform operations again, and so on. By recursively applying this process, we can generate all possible combinations of operations and calculate the result.

## Algorithm
1. Initialize an empty set `valid` to store all possible combinations of four numbers that can be used to get 24.
2. Generate all possible combinations of four numbers from 1 to 9.
3. For each combination, convert it to a list of doubles and check if it is possible to get 24 using the `canGet24` method.
4. If it is possible, add the combination to the `valid` set.
5. Create a `judgePoint24` method that takes an array of four integers as input and returns `true` if it is possible to get 24 using these numbers.
6. In the `judgePoint24` method, convert the input array to a list of doubles and call the `canGet24` method.

## Concept to Remember
* Recursion
* Brute force approach
* Basic arithmetic operations (+, -, x, /)

## Common Mistakes
* Not considering the order of operations
* Not handling division by zero correctly
* Not using a recursive approach

## Complexity Analysis
- Time: O(n^4) where n is the number of possible combinations of four numbers
- Reason: We generate all possible combinations of four numbers and check each one recursively

## Commented Code
```java
class Solution {
    private static Set<String> valid = new HashSet<>();

    public static boolean canGet24(List<Double> nums) {
        // If there is only one number left, check if it is close to 24
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - 24.0) <= 1e-6;
        }

        // Try all possible operations between two numbers
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                double x = nums.get(i), y = nums.get(j);
                double[] pairResults = new double[]{x + y, x * y, x - y, y - x};

                // Try all possible operations with the remaining numbers
                List<Double> selected = new ArrayList<>();
                for (int k = 0; k < nums.size(); k++) {
                    if (k != i && k != j) {
                        selected.add(nums.get(k));
                    }
                }

                for (double pair : pairResults) {
                    selected.add(pair);
                    if (canGet24(selected)) {
                        return true;
                    }
                    selected.remove(selected.size() - 1);
                }

                // Try division and multiplication
                if (y != 0.0) {
                    selected.add(x / y);
                    if (canGet24(selected)) {
                        return true;
                    }
                    selected.remove(selected.size() - 1);
                }

                if (x != 0.0) {
                    selected.add(y / x);
                    if (canGet24(selected)) {
                        return true;
                    }
                    selected.remove(selected.size() - 1);
                }
            }
        }

        return false;
    }

    public boolean judgePoint24(int[] cards) {
        List<Double> doubleArr = new ArrayList<>(Arrays.asList(
            (double) cards[0],
            (double) cards[1],
            (double) cards[2],
            (double) cards[3]
        ));
        return canGet24(doubleArr);
    }

    public static String convertToStr(int[] arr) {
        return arr[0] + "" + arr[1] + "" + arr[2] + "" + arr[3];
    }
}
```

## Interview Tips
* Make sure to understand the problem clearly and ask clarifying questions if necessary.
* Use a brute force approach to solve the problem, as it is the most straightforward way to solve it.
* Use a recursive approach to generate all possible combinations of operations.
* Handle division by zero correctly and make sure to use a precision check when comparing numbers.

## Revision Checklist
- [ ] Understand the problem clearly
- [ ] Use a brute force approach
- [ ] Use a recursive approach
- [ ] Handle division by zero correctly
- [ ] Use a precision check when comparing numbers

## Similar Problems
* 50. Pow(x, n)
* 19. Remove Nth Node From End of List
* 9. Palindrome Number

## Tags
`Array` `Hash Map` `Brute Force` `Recursion` `Math` `Problem Solving`
