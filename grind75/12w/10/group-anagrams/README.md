# Group Anagrams

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Sorting`  
**Time:** O(NMlogM)  
**Space:** O(NM)

---

## Solution (java)

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
      HashMap<String,List<String>> hm = new HashMap<>();
      for(String s : strs){
        char[] temp = s.toCharArray();
        Arrays.sort(temp);
        String key = new String(temp);// string ka constructor character array leleta hai aur string m convert kr deta hai
        List<String> l = hm.getOrDefault(key,new ArrayList<>()); 
        l.add(s); 
        hm.put(key,l);
      }
      List<List<String>> ans = new ArrayList<>();
      for(List<String> l : hm.values()) ans.add(l);
      return ans;      
            // e.getKey() e.getValue() Map.Entry<String,List<Integer>> e : hm.entrySet()

    }
}
```

---

---
## Quick Revision
Group anagrams of a given array of strings. This is done by sorting each string and using it as a key in a hash map.

## Intuition
The approach works because sorting the characters in a string makes it unique for all strings that are anagrams of each other. By grouping these sorted strings together, we effectively group the original anagrammatic strings together.

## Algorithm

1. Create an empty hash map to store the grouped anagrams.
2. Iterate over each string in the input array:
	* Convert the string to a character array and sort it.
	* Use the sorted character array as a key in the hash map (converting it back to a string for hash map keys).
	* Get the list of strings associated with this key, or create a new one if it doesn't exist yet.
	* Add the original string to this list.
3. Iterate over the values of the hash map and add each list to the result.

## Concept to Remember

* Hash maps can be used for grouping similar elements together by using their keys (in this case, sorted strings).
* Sorting an array of characters makes it a unique key for anagrams.
* Using a hash map allows us to efficiently group elements that are anagrams of each other.

## Common Mistakes
* Forgetting to convert the character array back to a string before using it as a key in the hash map.
* Not initializing the list associated with each key properly (e.g., using `hm.getOrDefault()`).
* Trying to sort the original strings instead of creating a new sorted version for each key.

## Complexity Analysis
- Time: O(NMlogM) - where N is the number of strings and M is the maximum length of a string, due to sorting each string.
- Space: O(NM) - for storing the grouped anagrams in the hash map.

## Commented Code

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Create a hash map to store the grouped anagrams
        HashMap<String, List<String>> hm = new HashMap<>();

        // Iterate over each string in the input array
        for (String s : strs) {
            // Convert the string to a character array and sort it
            char[] temp = s.toCharArray();
            Arrays.sort(temp);

            // Use the sorted character array as a key in the hash map
            String key = new String(temp); // Convert back to a string

            // Get the list of strings associated with this key, or create a new one if it doesn't exist yet
            List<String> l = hm.getOrDefault(key, new ArrayList<>());

            // Add the original string to this list
            l.add(s);
            hm.put(key, l);
        }

        // Create an empty list to store the result
        List<List<String>> ans = new ArrayList<>();

        // Iterate over the values of the hash map and add each list to the result
        for (List<String> l : hm.values()) {
            ans.add(l);
        }
        return ans;
    }
}
```

## Interview Tips

* Make sure to explain the intuition behind your approach clearly.
* Emphasize the importance of using a hash map for grouping anagrams efficiently.
* Be prepared to answer follow-up questions about edge cases, such as empty strings or strings with only one character.

## Revision Checklist
- [ ] Understand the problem and constraints clearly.
- [ ] Explain the intuition behind the approach.
- [ ] Make sure to use a hash map for efficient grouping of anagrams.
- [ ] Consider edge cases and special input scenarios.

## Similar Problems

* LeetCode 49: Group Anagrams ( Python solution)
* LeetCode 451: Frequency Sort
* LeetCode 18: 4Sum
