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
// hum hashset bss isliye bna rhe hai taaki order of 1 m traverse kr ske 
// queue m  humnein 1st word daala begin waala 
// usko end waale se check kiya nahi hai to usko remove kra 
// remove k baad jese hit hai word , tp h k liye a to z se bdl kr wordlist se check kra agr hai usme to queue m daala otherwise nahi 
// ese hi hit k i aur t k liye bhi 
// queue m dalte gaye  jese hi h , i aur t k liye bhi hogya end m aagye to level ++ kiya 

// kyonki level ++ tb hi krna hai jb ek later k checge ki kahani khtm hogyi ho bhale vo koi bhi ho 
// hit se hot , hit se bit yaa hit se his , this is a total level , agr teeno bhi queue m add hogye to bhi level 0 se 1 hi hoga 3 nahi , kyonki vo ek word ka dusre word m change ka level hai smjhee

// queue k  saare elemets k liye ek level hai haa
// ese hi phir har word jo queue m daal rhe hai uske liye krte gaye krte gaye 
// aur jese hi match mila level return .

```

---

---
## Quick Revision
This problem asks for the shortest transformation sequence from a begin word to an end word, changing one letter at a time, using a given dictionary.
We solve this using Breadth-First Search (BFS) to explore possible transformations level by level.

## Intuition
The core idea is to find the shortest path in a graph where words are nodes and an edge exists between two words if they differ by exactly one character. BFS is the natural choice for finding the shortest path in an unweighted graph. We can think of the transformation process as expanding outwards from the `beginWord`, exploring all one-letter-different words, then all two-letter-different words, and so on, until we reach the `endWord`. The first time we encounter the `endWord`, we've found the shortest path.

## Algorithm
1.  **Initialization**:
    *   Convert the `wordList` into a `HashSet` (`bankset`) for efficient O(1) lookups.
    *   Create a `Queue` (`q`) for BFS and add `beginWord` to it.
    *   Initialize `level` to 1, representing the current transformation length.
    *   Create an array of characters `chars` from 'a' to 'z'.
2.  **BFS Loop**:
    *   While the queue is not empty:
        *   Get the current size of the queue (`sz`). This represents all words at the current `level`.
        *   Process all words at the current level:
            *   Dequeue a `word`.
            *   If `word` is equal to `endWord`, return `level`.
            *   Generate all possible one-letter transformations of `word`:
                *   Iterate through each character position `i` in `word`.
                *   For each character position, iterate through all possible characters `c` from 'a' to 'z'.
                *   Create a `temp` word by replacing the character at position `i` with `c`.
                *   If `temp` exists in `bankset`:
                    *   Enqueue `temp` into the queue.
                    *   Remove `temp` from `bankset` to avoid revisiting and cycles.
        *   Increment `level` after processing all words at the current level.
3.  **No Path Found**: If the loop finishes and `endWord` is not found, return 0.

## Concept to Remember
*   **Breadth-First Search (BFS)**: Ideal for finding the shortest path in an unweighted graph. It explores nodes level by level.
*   **Graph Representation (Implicit)**: The problem can be modeled as a graph where words are nodes and an edge exists between words differing by one character. We don't explicitly build the graph; we generate neighbors on the fly.
*   **Set for Efficient Lookups**: Using a `HashSet` for the word dictionary allows for O(1) average time complexity for checking word existence and removal.
*   **Level-by-Level Processing**: The BFS structure naturally helps track the "distance" or number of transformations.

## Common Mistakes
*   **Not handling `beginWord` not in `wordList`**: The problem statement implies `beginWord` might not be in the dictionary, but it's the starting point. The solution correctly handles this by adding it to the queue.
*   **Infinite Loops/Cycles**: Failing to remove visited words from the dictionary (`bankset`) can lead to infinite loops if the graph contains cycles.
*   **Inefficient Word Transformation**: Generating transformations by repeatedly creating new strings can be slow. Using `StringBuilder` is more efficient.
*   **Incorrect Level Increment**: Incrementing `level` at the wrong time (e.g., inside the inner loop) will lead to an incorrect shortest path length. It should be incremented only after all words at a given level have been processed.
*   **Not checking if `endWord` is in `wordList`**: If `endWord` is not in the dictionary, it's impossible to reach it. The current solution implicitly handles this because it will never find `endWord` in `bankset`.

## Complexity Analysis
*   **Time**: O(N * L^2 * 26), where N is the number of words in the `wordList`, and L is the length of each word.
    *   The BFS visits each word at most once.
    *   For each word, we iterate through its L positions.
    *   At each position, we try 26 possible character replacements.
    *   Creating the `temp` string using `StringBuilder` takes O(L).
    *   Checking `bankset.contains(temp)` takes O(L) on average (due to string hashing).
    *   Removing from `bankset` takes O(L) on average.
    *   The initial conversion of `wordList` to `HashSet` takes O(N * L).
    *   The dominant factor is the BFS exploration.
*   **Space**: O(N * L) for storing the `wordList` in the `HashSet` and the queue. In the worst case, all words could be in the queue.

## Commented Code
```java
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
       // Convert the wordList to a HashSet for O(1) average time complexity lookups.
       // This allows us to quickly check if a transformed word exists in the dictionary.
       HashSet<String> bankset = new HashSet<>(wordList);

       // If the endWord is not in the wordList, it's impossible to reach it.
       // Although not explicitly checked here, the BFS will naturally fail to find it.
       // A pre-check could be: if (!bankset.contains(endWord)) return 0;

       // Initialize a queue for Breadth-First Search (BFS).
       // BFS is used to find the shortest path in an unweighted graph.
       Queue<String> q = new LinkedList<>();
       // Add the starting word to the queue.
       q.add(beginWord);

       // Initialize the level counter. The beginWord itself is level 1.
       int level = 1;

       // Create an array of all possible lowercase English alphabet characters.
       // This will be used to generate all possible one-character transformations.
       char[] chars = new char[26];
       for(int i = 0 ; i < chars.length; i++) {
           chars[i] = (char)('a' + i);
       }

       // Start the BFS loop. Continue as long as there are words to process in the queue.
       while(!q.isEmpty()){
            // Get the number of words at the current level.
            // This is crucial for processing level by level.
            int sz = q.size();

            // Process all words at the current level.
            while(sz-- > 0){
                // Dequeue a word from the front of the queue.
                String word =  q.remove();

                // If the dequeued word is the endWord, we have found the shortest transformation sequence.
                // Return the current level, which represents the length of this sequence.
                if(word.equals(endWord)) {
                    return level;
                }

                // Generate all possible one-character transformations of the current word.
                // Iterate through each character position in the word.
                for(int i = 0; i < beginWord.length(); i++){ // Using beginWord.length() assumes all words have same length.
                    // For each position, try replacing the character with every letter from 'a' to 'z'.
                    for(char c : chars){
                        // Create a mutable copy of the current word using StringBuilder.
                        StringBuilder sb = new StringBuilder(word);
                        // Replace the character at the current position 'i' with the character 'c'.
                        sb.setCharAt(i, c);
                        // Convert the StringBuilder back to a String.
                        String temp = sb.toString();

                        // Check if the transformed word 'temp' exists in our dictionary (bankset).
                        if(bankset.contains(temp)){
                            // If it exists, add it to the queue for processing in the next level.
                            q.add(temp);
                            // Remove the word from the bankset to prevent revisiting it and avoid cycles.
                            // This is a critical step for efficiency and correctness.
                            bankset.remove(temp);
                        }
                    }
                }
            }
            // After processing all words at the current level, increment the level counter.
            // This signifies moving to the next set of transformations.
            level++;
        }

        // If the queue becomes empty and we haven't found the endWord, it means no transformation sequence exists.
        // Return 0 to indicate that the endWord is unreachable.
        return 0;
    }
}
```

## Interview Tips
1.  **Explain BFS Clearly**: Articulate why BFS is the correct algorithm for finding the shortest path. Emphasize the level-by-level exploration.
2.  **Discuss Graph Analogy**: Explain how the problem can be viewed as a graph problem, even though the graph is implicitly defined.
3.  **Address Edge Cases**: Be prepared to discuss what happens if `beginWord` equals `endWord`, if `endWord` is not in `wordList`, or if `wordList` is empty.
4.  **Optimize Word Generation**: Show awareness of efficient string manipulation (e.g., using `StringBuilder` instead of repeated string concatenation) and the importance of removing visited words from the dictionary.
5.  **Complexity Justification**: Clearly explain the time and space complexity, breaking down the factors involved in the BFS traversal.

## Revision Checklist
- [ ] Understand the problem: shortest word transformation sequence.
- [ ] Recognize BFS as the appropriate algorithm.
- [ ] Implement BFS with a queue.
- [ ] Handle level tracking correctly.
- [ ] Use a `HashSet` for efficient dictionary lookups.
- [ ] Implement efficient one-character word transformations.
- [ ] Prevent cycles by removing visited words from the dictionary.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases.

## Similar Problems
*   Word Break
*   Shortest Path in Binary Matrix
*   Keys and Rooms
*   Pacific Atlantic Water Flow
*   Rotting Oranges

## Tags
`Breadth-First Search` `String` `Hash Set` `Graph`
