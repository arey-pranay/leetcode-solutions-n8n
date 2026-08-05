# Top K Frequent Words

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Trie` `Sorting` `Heap (Priority Queue)` `Bucket Sort` `Counting`  
**Time:** O(N log k)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
  class Pair{
    String word;
    int count;
    Pair(String w, int c){
      this.word = w;
      this.count = c;
    }
  }
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> hm = new HashMap<>();
        for(String word : words) hm.put(word,hm.getOrDefault(word,0)+1);
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
          if(a.count != b.count) return b.count-a.count;
          return a.word.compareTo(b.word);
        });
        for(Map.Entry<String,Integer> e : hm.entrySet()){pq.add(new Pair(e.getKey(),e.getValue()));}
        List<String> ans = new ArrayList<>();
        for(int i=0;i<k;i++) ans.add(pq.poll().word);
        return ans;
    }
}

```

---

---

## Quick Revision
Top K Frequent Words problem asks to find the top k frequent words from a given list of words. The solution uses a combination of HashMap and PriorityQueue to solve this problem in an efficient manner.

## Intuition
The key insight is that we can use a HashMap to count the frequency of each word and then use a PriorityQueue to select the top k frequent words based on their counts and lexicographical order.

## Algorithm
1. Create a HashMap to store the frequency of each word.
2. Iterate over the input array, incrementing the count for each word in the HashMap.
3. Create a custom Pair class to store the word and its count.
4. Use a PriorityQueue to select the top k frequent words based on their counts (in descending order) and lexicographical order (if counts are equal).
5. Extract the top k frequent words from the PriorityQueue.

## Concept to Remember
* Hashing: using HashMap to store key-value pairs efficiently.
* Priority Queue: selecting the highest priority element based on a custom comparator.

## Common Mistakes
* Forgetting to handle equal counts when comparing Pair objects.
* Using incorrect data structures or algorithms that lead to high time complexity.
* Failing to properly initialize the PriorityQueue with the word and its count.

## Complexity Analysis
- Time: O(N log k) - reason: inserting elements into the PriorityQueue, where N is the number of unique words.
- Space: O(N) - reason: storing the frequency of each word in the HashMap.

## Commented Code
```java
class Solution {
    class Pair {
        String word;
        int count;

        Pair(String w, int c) {
            this.word = w;
            this.count = c;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        // Step 1: Count the frequency of each word using HashMap
        HashMap<String, Integer> hm = new HashMap<>();
        for (String word : words) {
            hm.put(word, hm.getOrDefault(word, 0) + 1);
        }

        // Step 2: Create a custom PriorityQueue to select top k frequent words
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if (a.count != b.count) return b.count - a.count;
            return a.word.compareTo(b.word);
        });

        for (Map.Entry<String, Integer> e : hm.entrySet()) {
            pq.add(new Pair(e.getKey(), e.getValue()));
        }

        // Step 3: Extract the top k frequent words from the PriorityQueue
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(pq.poll().word);
        }
        return ans;
    }
}
```

## Interview Tips
* Be able to explain the trade-off between using HashMap and PriorityQueue.
* Make sure to handle edge cases, such as empty input arrays or k > N.
* Practice explaining your thought process when selecting data structures and algorithms.

## Revision Checklist
- [ ] Review priority queue implementation.
- [ ] Ensure correct handling of equal counts in Pair comparison.
- [ ] Verify that HashMap is used efficiently for counting frequencies.

## Similar Problems
* Top K Frequent Elements (Easy)
* Maximum Subarray Sum (Medium)
* Longest Increasing Subsequence (Hard)

## Tags
`Array` `Hash Map` `Priority Queue`

## My Notes
PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
          if(a.count != b.count) return b.count-a.count;
          return a.word.compareTo(b.word);
        });
