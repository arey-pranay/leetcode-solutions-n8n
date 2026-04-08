# Word Ladder

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `String` `Breadth-First Search`  
**Time:** O(N * L^2 * 26)  
**Space:** O(N * L)

---

## Solution (java)

```java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
       HashSet<String> bankset = new HashSet<>(wordList);  // o(1) m check k liye hashset
       Queue<String> q = new LinkedList<>();
       q.add(beginWord);
       int level = 1;
       char[] chars = new char[26];
       for(int i =0 ; i<chars.length;i++) chars[i] = (char)('a'+i);
       while(!q.isEmpty()){
            int sz = q.size();
            while(sz-- > 0){
                String word =  q.remove();
                if(word.equals(endWord)) return level; 
                for(int i =0;i<beginWord.length();i++){
                    for(char c : chars){
                        StringBuilder sb = new StringBuilder(word);
                        sb.setCharAt(i,c);
                        String temp = sb.toString();
                        if(bankset.contains(temp)){
                            q.add(temp);
                            bankset.remove(temp);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }
}
```

---

---
## Quick Revision
This problem asks for the shortest transformation sequence from a begin word to an end word, changing one letter at a time, using a given dictionary.
We solve this using Breadth-First Search (BFS) to explore possible transformations level by level.

## Intuition
The problem can be modeled as finding the shortest path in a graph. Each word is a node, and an edge exists between two words if they differ by exactly one character. Since we need the *shortest* path, BFS is the natural choice. We start from `beginWord` and explore all one-letter transformations. If `endWord` is found, we've found the shortest path. We use a queue for BFS and a set to efficiently check dictionary words and avoid cycles/revisiting.

## Algorithm
1. Create a `HashSet` from the `wordList` for efficient O(1) lookups.
2. Initialize a `Queue` and add `beginWord` to it.
3. Initialize `level` to 1 (representing the length of the transformation sequence).
4. Create an array of characters 'a' through 'z' for easy character substitution.
5. While the queue is not empty:
    a. Get the current size of the queue (`sz`). This represents all words at the current `level`.
    b. Process `sz` words from the queue:
        i. Dequeue a `word`.
        ii. If `word` is equal to `endWord`, return `level`.
        iii. For each character position `i` in `word`:
            1. For each character `c` from 'a' to 'z':
                a. Create a `StringBuilder` from `word`.
                b. Replace the character at position `i` with `c`.
                c. Convert the `StringBuilder` back to a `String` (`temp`).
                d. If `temp` exists in the `wordList` (i.e., `bankset.contains(temp)`):
                    i. Enqueue `temp`.
                    ii. Remove `temp` from `bankset` to avoid revisiting and ensure shortest path.
    c. Increment `level` after processing all words at the current level.
6. If the loop finishes without finding `endWord`, return 0.

## Concept to Remember
*   **Breadth-First Search (BFS):** Ideal for finding the shortest path in an unweighted graph.
*   **Graph Representation:** Implicitly representing words as nodes and one-letter differences as edges.
*   **Set for Efficient Lookups:** Using `HashSet` for O(1) average time complexity for `contains` and `remove` operations.
*   **Level-by-Level Traversal:** Processing nodes in layers to guarantee finding the shortest path first.

## Common Mistakes
*   **Not handling `beginWord` not being in `wordList`:** The algorithm correctly starts with `beginWord` even if it's not in the dictionary.
*   **Not removing visited words from the dictionary:** This can lead to infinite loops or incorrect longer paths if words are revisited.
*   **Inefficient word transformation generation:** Generating all possible one-letter transformations for each word is crucial.
*   **Forgetting to increment the level:** The `level` variable must be incremented after processing all nodes at a given depth.
*   **Not returning 0 if `endWord` is unreachable:** The problem requires returning 0 in such cases.

## Complexity Analysis
*   **Time:** O(N * L^2 * 26), where N is the number of words in `wordList` and L is the length of each word.
    *   The outer `while` loop runs at most N times (each word is enqueued and dequeued at most once).
    *   Inside the loop, we iterate through each word (N words max).
    *   For each word, we generate all possible transformations: L positions * 26 characters. This is O(L * 26).
    *   String building and `contains` check on `HashSet` are O(L) and O(1) on average, respectively.
    *   So, roughly N * (L * 26 * L) = O(N * L^2 * 26).
*   **Space:** O(N * L), where N is the number of words and L is the length of each word.
    *   The `HashSet` stores all words from `wordList`, taking O(N * L) space.
    *   The `Queue` can store up to N words in the worst case, also taking O(N * L) space.

## Commented Code
```java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
       // Convert the wordList to a HashSet for O(1) average time complexity lookups.
       HashSet<String> bankset = new HashSet<>(wordList);
       // Initialize a queue for Breadth-First Search (BFS).
       Queue<String> q = new LinkedList<>();
       // Add the starting word to the queue.
       q.add(beginWord);
       // Initialize the level counter. The sequence length starts at 1.
       int level = 1;
       // Create an array of all lowercase English alphabet characters for easy substitution.
       char[] chars = new char[26];
       for(int i =0 ; i<chars.length;i++) chars[i] = (char)('a'+i);
       // Start the BFS traversal.
       while(!q.isEmpty()){
            // Get the number of words at the current level to process.
            int sz = q.size();
            // Process all words at the current level.
            while(sz-- > 0){
                // Dequeue a word from the front of the queue.
                String word =  q.remove();
                // If the dequeued word is the endWord, we've found the shortest path. Return the current level.
                if(word.equals(endWord)) return level;
                // Iterate through each character position in the current word.
                for(int i =0;i<beginWord.length();i++){ // Assuming all words have the same length as beginWord
                    // Iterate through all possible characters ('a' to 'z').
                    for(char c : chars){
                        // Create a mutable copy of the current word.
                        StringBuilder sb = new StringBuilder(word);
                        // Replace the character at the current position 'i' with the character 'c'.
                        sb.setCharAt(i,c);
                        // Convert the modified StringBuilder back to a String.
                        String temp = sb.toString();
                        // Check if the transformed word exists in our dictionary (bankset).
                        if(bankset.contains(temp)){
                            // If it exists, add it to the queue for processing in the next level.
                            q.add(temp);
                            // Remove the word from the bankset to mark it as visited and prevent cycles/revisiting.
                            bankset.remove(temp);
                        }
                    }
                }
            }
            // After processing all words at the current level, increment the level counter.
            level++;
        }
        // If the queue becomes empty and endWord was not found, it means endWord is unreachable.
        return 0;
    }
}
```

## Interview Tips
*   **Explain the Graph Analogy:** Clearly articulate how the problem can be viewed as a shortest path problem on a graph where words are nodes and one-letter differences are edges.
*   **Justify BFS:** Explain why BFS is the optimal algorithm for finding the shortest path in an unweighted graph.
*   **Discuss Optimization:** Mention the use of `HashSet` for efficient dictionary lookups and the importance of removing visited words from the set to avoid redundant computations and cycles.
*   **Handle Edge Cases:** Be prepared to discuss what happens if `endWord` is not in `wordList` or if `beginWord` and `endWord` are the same.

## Revision Checklist
- [ ] Understand the problem statement: transforming words by one letter.
- [ ] Recognize the problem as a shortest path on a graph.
- [ ] Implement BFS correctly with a queue.
- [ ] Use a `HashSet` for efficient dictionary lookups.
- [ ] Implement the logic to generate all one-letter transformations.
- [ ] Ensure visited words are removed from the dictionary.
- [ ] Track the transformation sequence length (level).
- [ ] Handle the case where `endWord` is unreachable.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Word Break
*   Shortest Path in Binary Matrix
*   Keys and Rooms
*   Pacific Atlantic Water Flow

## Tags
`Breadth-First Search` `Hash Set` `String` `Graph`

## My Notes
nice question.
