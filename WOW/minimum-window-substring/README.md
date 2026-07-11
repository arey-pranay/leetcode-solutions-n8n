# Minimum Window Substring

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `String` `Sliding Window`  
**Time:** O(N + M)  
**Space:** O(M)

---

## Solution (java)

```java
class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character,Integer> freq = new HashMap<>();
        for (char i : t.toCharArray()) freq.put(i, freq.getOrDefault(i, 0) + 1);
        int index=-1;
        int minLength = Integer.MAX_VALUE;
        int count = t.length();
        int i =0;
        for(int j=0;j<s.length();j++){
            char c = s.charAt(j);
            if(freq.containsKey(c)){
                if(freq.get(c) > 0) count--;
                freq.put(c,freq.get(c)-1);
            } 
            
                // XIADOBECODEBANC
                //    i      j
                //    2      7
            while(count==0){
// we will increment count only when we realize that we lost a useful character, so now let's increment j again to find that character
              if(j+1 - i < minLength){
                minLength = j+1 -i;
                index = i;
              }
              char firstMatch = s.charAt(i);
              if(freq.containsKey(firstMatch)){
                int newCount = freq.get(firstMatch)+1;
                freq.put(firstMatch, newCount);
                if(newCount > 0) count++;
              }
              i++;
            }
        }
        return index == -1 ? "" : s.substring(index,index+minLength);
    }
}
// ADOBECODEBA
// ADOBECODEBANC
// ABC
```

---

---
## Quick Revision
Find the minimum window substring in `s` that contains all characters of `t`.

Use a sliding window approach with two pointers and a hash map to count character frequencies.

## Intuition
The key insight is that we need to maintain a window where all characters of `t` are present. We use a hash map to keep track of the frequency of each character in `t`. When the window size is reduced, we increment the pointer at the start of the window and decrement the frequency count until we reach the minimum window size.

## Algorithm

1. Initialize a hash map `freq` with character frequencies from string `t`.
2. Set up variables to store the minimum length (`minLength`) and its corresponding index (`index`).
3. Iterate through string `s` with pointer `j`. For each character:
	* Check if it's present in the frequency hash map.
	* If so, decrement its frequency count; if the new count is 0, increment a counter (`count`).
4. When `count` reaches 0 (i.e., all characters of `t` are present within the window), expand the window by incrementing pointer `j`. If the new window size is smaller than `minLength`, update `minLength` and store its index.
5. To minimize the window, increment pointer `i` until it finds a character that was previously present in the window but has now been removed.

## Concept to Remember
* Sliding window technique
* Hash map for efficient frequency counting
* Character frequency management

## Common Mistakes
* Failing to initialize variables correctly (e.g., `minLength`, `count`)
* Not maintaining the sliding window properly, resulting in incorrect minimum length calculation
* Overlooking the importance of character frequency updates when expanding or shrinking the window

## Complexity Analysis

- Time: O(N + M) - where N is the length of string s and M is the length of string t. This accounts for the hash map operations (insertion, lookup) and the two pointer movements through s.
- Space: O(M) - to store character frequencies in the hash map.

## Commented Code

```java
class Solution {
    public String minWindow(String s, String t) {
        // Initialize frequency hash map with characters from string t
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char i : t.toCharArray()) 
            freq.put(i, freq.getOrDefault(i, 0) + 1);

        int index = -1; // Store the starting index of the minimum window
        int minLength = Integer.MAX_VALUE; // Initialize with maximum possible value

        int count = t.length(); // Number of characters in string t within the window
        int i = 0; // Left pointer for the sliding window

        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (freq.containsKey(c)) {
                // Decrement frequency count and increment counter when character is found within the window
                if (freq.get(c) > 0) 
                    count--;
                freq.put(c, freq.get(c) - 1);
            }

            while (count == 0) {
                // Expand window by moving right pointer j. Update minimum length and index if necessary.
                if (j + 1 - i < minLength) {
                    minLength = j + 1 - i;
                    index = i;
                }
                char firstMatch = s.charAt(i);
                if (freq.containsKey(firstMatch)) {
                    // Increment frequency count when a character is found outside the current window
                    int newCount = freq.get(firstMatch) + 1;
                    freq.put(firstMatch, newCount);
                    if (newCount > 0) 
                        count++;
                }
                i++; // Move left pointer to find the next useful character.
            }
        }

        return index == -1 ? "" : s.substring(index, index + minLength);
    }
}
```

## Interview Tips

* Be prepared to explain the intuition behind the sliding window approach and the importance of maintaining the frequency hash map.
* Practice explaining the code with a focus on clear communication and concise explanation.
* Be ready to discuss edge cases, such as an empty string `t` or `s`, and how they affect the algorithm's performance.

## Revision Checklist
- Review hash map operations for efficiency and correctness
- Ensure proper maintenance of the sliding window (i.e., updating pointers i and j)
- Double-check that all character frequencies are correctly updated

## Similar Problems
* Minimum Window Substring variations with different constraints or inputs (e.g., non-overlapping characters, character weights)
* Other string searching and substring problems involving frequency counting and sliding windows
