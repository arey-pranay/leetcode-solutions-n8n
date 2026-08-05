# Top K Frequent Words

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Hash Table` `String` `Trie` `Sorting` `Heap (Priority Queue)` `Bucket Sort` `Counting`  
**Time:** O(N log N)  
**Space:** O(M)

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
Given a list of words, find the k most frequent words.
Solve by counting frequencies, then using a priority queue to maintain the top k elements.

## Intuition
The core idea is to first determine how many times each word appears in the input list. Once we have these frequencies, we need an efficient way to select the top `k` words. A min-heap (or max-heap depending on implementation) is a natural fit for "top k" problems. We want to prioritize words by their frequency (higher frequency is better). If frequencies are equal, we need a tie-breaker: alphabetical order. A max-heap ordered by frequency (descending) and then word (ascending) will naturally give us the desired order when we extract elements.

## Algorithm
1.  **Count Frequencies:** Iterate through the input `words` array and use a `HashMap` to store the frequency of each word. The key will be the word (String) and the value will be its count (Integer).
2.  **Create a Custom Pair Class:** Define a simple class `Pair` to hold a `word` (String) and its `count` (int). This will be used to store elements in the priority queue.
3.  **Initialize a Max-Priority Queue:** Create a `PriorityQueue` that stores `Pair` objects. The custom comparator for the priority queue should prioritize:
    *   Higher `count` first (descending order).
    *   If `count` is the same, then lexicographically smaller `word` first (ascending alphabetical order).
4.  **Populate the Priority Queue:** Iterate through the entries of the frequency `HashMap`. For each entry (word, count), create a new `Pair` object and add it to the `PriorityQueue`.
5.  **Extract Top K Elements:** Create an `ArrayList` to store the result. Poll `k` times from the `PriorityQueue`. For each polled `Pair`, add its `word` to the result list.
6.  **Return Result:** Return the `ArrayList` containing the top `k` frequent words.

## Concept to Remember
*   **Hash Maps for Frequency Counting:** Efficiently store and retrieve counts of items.
*   **Priority Queues (Heaps) for Top K Problems:** Maintain a sorted collection of a fixed size, allowing efficient insertion and retrieval of extreme elements.
*   **Custom Comparators:** Define specific ordering logic for complex objects in data structures like `PriorityQueue`.
*   **Lexicographical Ordering:** Understanding alphabetical sorting for tie-breaking.

## Common Mistakes
*   **Incorrect Comparator Logic:** Reversing the order for frequency or word comparison, leading to incorrect top `k` selection. For example, using `a.count - b.count` instead of `b.count - a.count` for descending frequency.
*   **Not Handling Tie-Breakers:** Forgetting to sort alphabetically when frequencies are equal, which is a requirement.
*   **Using a Min-Heap Incorrectly:** If using a min-heap, one would typically push all elements and then pop `k` times, or maintain a heap of size `k` and only push if the new element is "better" than the smallest in the heap. The provided solution uses a max-heap approach which is simpler here.
*   **Off-by-One Errors:** Incorrectly looping `k` times or processing `k-1` elements.

## Complexity Analysis
*   **Time:** O(N log N) - where N is the number of words in the input array.
    *   Counting frequencies: O(N) to iterate through `words`.
    *   Populating the priority queue: O(M log M), where M is the number of unique words. In the worst case, M can be N.
    *   Extracting top k: O(k log M).
    *   The dominant factor is O(N log N) if M is close to N, or O(M log M) if M is much smaller than N.
*   **Space:** O(M) - where M is the number of unique words.
    *   `HashMap` stores M unique words and their counts.
    *   `PriorityQueue` stores up to M `Pair` objects.

## Commented Code
```java
class Solution {
    // Define a helper class to store word and its frequency.
    class Pair{
        String word; // The word itself.
        int count;  // The frequency of the word.

        // Constructor for the Pair class.
        Pair(String w, int c){
            this.word = w; // Initialize the word.
            this.count = c; // Initialize the count.
        }
    }

    // Main method to find the top k frequent words.
    public List<String> topKFrequent(String[] words, int k) {
        // Create a HashMap to store word frequencies.
        HashMap<String, Integer> hm = new HashMap<>();

        // Iterate through the input array of words to count frequencies.
        for(String word : words) {
            // For each word, get its current count from the map (defaulting to 0 if not present)
            // and increment it by 1. Then put it back into the map.
            hm.put(word,hm.getOrDefault(word,0)+1);
        }

        // Create a PriorityQueue (min-heap by default, but we'll customize it).
        // We want a max-heap based on frequency, and then lexicographical order for ties.
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
            // Custom comparator:
            // If counts are different, sort by count in descending order (higher count first).
            if(a.count != b.count) return b.count-a.count;
            // If counts are the same, sort by word lexicographically in ascending order (alphabetical).
            return a.word.compareTo(b.word);
        });

        // Iterate through the entries of the frequency HashMap.
        for(Map.Entry<String,Integer> e : hm.entrySet()){
            // For each entry (word, count), create a new Pair object.
            // Add the Pair object to the priority queue.
            pq.add(new Pair(e.getKey(),e.getValue()));
        }

        // Create an ArrayList to store the final result (the top k words).
        List<String> ans = new ArrayList<>();

        // Poll (remove and return) the top k elements from the priority queue.
        for(int i=0;i<k;i++) {
            // For each polled Pair, add its word to the result list.
            ans.add(pq.poll().word);
        }

        // Return the list of top k frequent words.
        return ans;
    }
}
```

## Interview Tips
*   **Clarify Tie-Breaking:** Always ask the interviewer how to handle words with the same frequency. This problem explicitly states lexicographical order, but it's a good habit.
*   **Explain Comparator Logic:** Be ready to walk through the custom comparator for the `PriorityQueue` step-by-step, explaining why `b.count - a.count` and `a.word.compareTo(b.word)` are used.
*   **Discuss Alternatives:** Briefly mention other approaches, like sorting the map entries directly (which would be O(M log M) for sorting unique words) or using a min-heap of size `k` (which can be more space-efficient if `k` is much smaller than M).
*   **Edge Cases:** Consider cases like an empty input array, `k=0`, or `k` being larger than the number of unique words.

## Revision Checklist
- [ ] Understand the problem: find k most frequent words.
- [ ] Frequency counting using HashMap.
- [ ] PriorityQueue for maintaining top k.
- [ ] Custom comparator for PQ (frequency descending, word ascending).
- [ ] Handling tie-breakers correctly.
- [ ] Time and Space complexity analysis.
- [ ] Code implementation with clear variable names.

## Similar Problems
*   347. Top K Frequent Elements
*   692. Top K Frequent Words (This problem)
*   215. Kth Largest Element in an Array

## Tags
`Array` `Hash Map` `String` `Priority Queue` `Heap` `Sorting`
