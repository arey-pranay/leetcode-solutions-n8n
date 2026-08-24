# Word Ladder Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `String` `Backtracking` `Breadth-First Search` `Bidirectional Search`  
**Time:** O(N * L^2 * A)  
**Space:** O(N * L)

---

## Solution (java)

```java
class Solution {
    List<List<String>> ans = new ArrayList<>();
    HashMap<String,HashSet<String>> graph = new HashMap<>();
    HashSet<String> wordSet;
    String target;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
      wordSet = new HashSet<>(wordList);
      Queue<String> q = new LinkedList<>();
      wordSet.remove(beginWord);
      q.add(beginWord);
      target = beginWord;
      boolean found = false;
      while(!q.isEmpty()){ //O(n)
        int sz = q.size();
        HashSet<String> nextLevel = new HashSet<>();
        while(sz-->0){
          String word = q.poll();
          //bfs = processing level by level. But "Processing" means different things in every single context.
          for(String neigh : wordSet){
            if(!canGo(word,neigh)) continue;
            if(!graph.containsKey(neigh)) graph.put(neigh,new HashSet<>());
            graph.get(neigh).add(word);
            nextLevel.add(neigh);
            if(neigh==endWord) found=true;
          }
        }
        wordSet.removeAll(nextLevel);
        if(!found)q.addAll(nextLevel);
      }
      List<String> path = new ArrayList<>();
      path.add(endWord);
      func(endWord,path);
      return ans;
    }

    public void func(String currWord, List<String> path){
      if(currWord.equals(target)) {
        List<String> temp = new ArrayList<>(path);
        Collections.reverse(temp);
        ans.add(temp);
        return;
      }
      if(!graph.containsKey(currWord)) return;
      for(String neigh : graph.get(currWord)){
        path.add(neigh);
        func(neigh,path);
        path.remove(path.size()-1);
      }
    }
    
    public boolean canGo(String a , String b){
        int count = 0;
        for(int i =0 ; i<a.length();i++){
          if(a.charAt(i)!=b.charAt(i)) count++;
          if(count>1) return false;
        }
        return count==1;
    }
}
```

---

---
## Quick Revision
Find all shortest transformation sequences from beginWord to endWord.
This is solved using a modified Breadth-First Search (BFS) to find shortest paths and then Depth-First Search (DFS) to reconstruct all such paths.

## Intuition
The core idea is to find the shortest path length first, and then explore all paths of that specific length. A standard BFS finds the shortest path length. However, to find *all* shortest paths, we need to keep track of how we reached each word. This suggests building a graph where an edge exists between two words if they differ by one character. The BFS can be used to build this graph level by level, ensuring we only consider words at the shortest distance. Once the graph is built, a DFS can traverse it from the `endWord` back to the `beginWord` to reconstruct all valid paths.

## Algorithm
1.  **Initialization**:
    *   Create an adjacency list `graph` (e.g., `HashMap<String, HashSet<String>>`) to store the relationships between words.
    *   Create a `wordSet` from the input `wordList` for efficient lookups.
    *   Initialize a queue `q` for BFS and add `beginWord`.
    *   Remove `beginWord` from `wordSet` to avoid cycles and self-loops.
    *   Set a flag `found` to `false` to indicate if `endWord` has been reached.
    *   Store `beginWord` as `target` for DFS backtracking.

2.  **BFS (Level by Level Shortest Path Finding)**:
    *   While the queue is not empty:
        *   Get the current level's size `sz`.
        *   Create a `nextLevel` set to store words to be added to the queue in the next iteration.
        *   Process `sz` words from the queue:
            *   Dequeue a `word`.
            *   Iterate through all words `neigh` in the remaining `wordSet`:
                *   If `word` and `neigh` differ by exactly one character (using `canGo` helper function):
                    *   Add an edge from `neigh` to `word` in the `graph`. This means `word` is a predecessor of `neigh` in a valid transformation.
                    *   Add `neigh` to the `nextLevel` set.
                    *   If `neigh` is the `endWord`, set `found` to `true`.
        *   Remove all words in `nextLevel` from `wordSet`. This is crucial to ensure we only consider shortest paths. If a word is visited at a certain level, it shouldn't be considered again at a later level (which would imply a longer path).
        *   If `found` is `true`, break the BFS loop (we've found the shortest path length and built the necessary graph for it).
        *   Add all words from `nextLevel` to the queue for the next level's processing.

3.  **DFS (Path Reconstruction)**:
    *   Initialize an empty list `ans` to store all valid word ladders.
    *   Create a `path` list and add `endWord` to it.
    *   Call a recursive helper function `func(endWord, path)` to reconstruct paths.

4.  **`func(currWord, path)` Helper Function**:
    *   **Base Case**: If `currWord` is equal to `target` (`beginWord`):
        *   Create a copy of the current `path`.
        *   Reverse the copied `path`.
        *   Add the reversed `path` to `ans`.
        *   Return.
    *   **Recursive Step**: If `currWord` exists as a key in the `graph` (meaning it has predecessors):
        *   For each `neigh` (predecessor) in `graph.get(currWord)`:
            *   Add `neigh` to the current `path`.
            *   Recursively call `func(neigh, path)`.
            *   **Backtrack**: Remove `neigh` from the `path` to explore other branches.

5.  **`canGo(a, b)` Helper Function**:
    *   Checks if two strings `a` and `b` differ by exactly one character.
    *   Iterate through the characters of both strings.
    *   Count the number of differing characters.
    *   Return `true` if the count is exactly 1, `false` otherwise.

## Concept to Remember
*   **Breadth-First Search (BFS)**: Used to find the shortest path in an unweighted graph. Here, it's adapted to find the shortest path *length* and build a graph of predecessors level by level.
*   **Depth-First Search (DFS)**: Used to explore all possible paths in a graph. Here, it's used to reconstruct all shortest paths from the `endWord` back to the `beginWord` using the graph built by BFS.
*   **Graph Representation**: Adjacency list is suitable for representing relationships between words, especially when the number of words is large but connections are sparse.
*   **Backtracking**: Essential in DFS to explore different branches of the search space without getting stuck in one path.

## Common Mistakes
*   **Not handling levels correctly in BFS**: If BFS is not strictly level by level, it might find longer paths first or miss some shortest paths. The `wordSet.removeAll(nextLevel)` is critical.
*   **Building the graph in the wrong direction**: The graph should store predecessors (e.g., `graph.get(neighbor).add(current_word)`) to facilitate DFS from `endWord` to `beginWord`.
*   **Infinite loops or cycles**: Not removing visited words from the `wordSet` or not handling the `beginWord` correctly can lead to cycles.
*   **Not reversing the path**: The DFS reconstructs paths from `endWord` to `beginWord`, so the final path needs to be reversed to match the `beginWord` to `endWord` transformation.
*   **Inefficient `canGo` function**: A naive character-by-character comparison is fine, but if the alphabet or word length were extremely large, more optimized string comparison might be considered (though not typical for this problem).

## Complexity Analysis
*   **Time**: O(N * L^2 * A), where N is the number of words in the dictionary, L is the length of each word, and A is the size of the alphabet (26).
    *   **BFS**: In the worst case, we visit each word. For each word, we iterate through the remaining `wordSet` (N words). For each pair of words, `canGo` takes O(L) time. So, building the graph can be roughly O(N^2 * L). However, a more refined analysis considers that each word is processed at most once per level, and there are at most L levels. The `canGo` check is done for each word against potential neighbors. A common upper bound cited is O(N * L^2) if we consider generating all possible one-letter transformations for each word and checking against the dictionary (which is O(N * L^2 * L) for generation and O(N * L^2) for lookup). The provided solution iterates through `wordSet` for each word, leading to O(N^2 * L) for graph construction.
    *   **DFS**: In the worst case, the number of shortest paths can be exponential. For each path, we traverse it, which takes O(L) time. The graph construction is the dominant factor.
    *   The overall time complexity is often dominated by the graph construction phase. The provided solution's BFS part is O(N^2 * L) due to iterating through `wordSet` for each word.
*   **Space**: O(N * L) for storing the `graph`, `wordSet`, queue, and recursion stack.
    *   `graph`: Stores up to N words, each with potentially N-1 neighbors. In the worst case, it can be O(N^2). However, considering the structure of word ladders, it's often closer to O(N * L) for storing strings.
    *   `wordSet`: O(N * L) to store all words.
    *   `queue`: In the worst case, can hold all N words, O(N * L).
    *   `recursion stack` for DFS: In the worst case, the depth can be L, and each path can store L words, O(L^2). However, the number of paths can be exponential, so the space for `ans` can be very large. If we consider the space for the graph and auxiliary structures, it's O(N * L).

## Commented Code
```java
class Solution {
    // List to store all the valid word ladders found.
    List<List<String>> ans = new ArrayList<>();
    // Adjacency list to represent the graph where keys are words and values are their predecessors.
    // This graph helps in reconstructing paths from endWord back to beginWord.
    HashMap<String,HashSet<String>> graph = new HashMap<>();
    // A set containing all words from the wordList for efficient lookups.
    HashSet<String> wordSet;
    // The starting word of the transformation.
    String target;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
      // Initialize the wordSet with the provided wordList.
      wordSet = new HashSet<>(wordList);
      // Initialize a queue for Breadth-First Search (BFS).
      Queue<String> q = new LinkedList<>();
      // Remove beginWord from wordSet to avoid processing it as a neighbor of itself or in cycles.
      wordSet.remove(beginWord);
      // Add the beginWord to the queue to start the BFS.
      q.add(beginWord);
      // Set the target word to beginWord. This is used as the termination condition for DFS.
      target = beginWord;
      // Flag to indicate if the endWord has been found during BFS.
      boolean found = false;

      // Start BFS to find shortest paths and build the predecessor graph.
      // The loop continues as long as there are words to process in the queue.
      while(!q.isEmpty()){ // O(N) iterations in the worst case for the outer loop, where N is the number of words.
        // Get the number of words at the current level of BFS.
        int sz = q.size();
        // A set to store words that will be processed in the next level.
        // This is crucial for level-by-level processing and ensuring shortest paths.
        HashSet<String> nextLevel = new HashSet<>();

        // Process all words at the current level.
        while(sz-->0){ // This inner loop runs 'sz' times for each level.
          // Dequeue a word from the current level.
          String word = q.poll();

          // Iterate through all words remaining in the wordSet to find potential neighbors.
          // This is a key part of graph construction.
          for(String neigh : wordSet){ // O(N) iterations in the worst case for each word.
            // Check if 'neigh' is a valid one-character transformation of 'word'.
            if(!canGo(word,neigh)) continue; // O(L) for canGo, where L is word length.

            // If 'neigh' is a valid transformation, add it to the graph.
            // We store 'word' as a predecessor of 'neigh'. This means 'word' can transform into 'neigh'.
            // This direction is chosen so that DFS can backtrack from endWord to beginWord.
            if(!graph.containsKey(neigh)) graph.put(neigh,new HashSet<>());
            graph.get(neigh).add(word);

            // Add 'neigh' to the set of words for the next level.
            nextLevel.add(neigh);

            // If 'neigh' is the endWord, set the 'found' flag to true.
            // This indicates we have reached the endWord at this level, meaning we've found the shortest path length.
            if(neigh.equals(endWord)) found=true; // Use .equals() for string comparison.
          }
        }

        // After processing all words at the current level, remove all words that were added to 'nextLevel'
        // from the main 'wordSet'. This ensures that words are only considered at their shortest distance.
        // If a word is found at level k, it won't be considered again at level k+1 or beyond,
        // preventing longer paths from being explored.
        wordSet.removeAll(nextLevel);

        // If the endWord was found in this level, we can stop the BFS.
        // We have identified the shortest path length and built the necessary predecessor graph for it.
        // We do NOT add nextLevel to the queue if found, because we only want paths of this shortest length.
        if(!found)q.addAll(nextLevel);
      }

      // Now that BFS is complete and the predecessor graph is built for shortest paths,
      // we use DFS to reconstruct all valid paths from endWord back to beginWord.

      // Initialize a list to store the current path being built by DFS.
      List<String> path = new ArrayList<>();
      // Start the path with the endWord.
      path.add(endWord);
      // Call the recursive DFS function to find and build all paths.
      func(endWord,path);

      // Return the list of all found word ladders.
      return ans;
    }

    // Recursive DFS function to reconstruct paths.
    public void func(String currWord, List<String> path){
      // Base case: If the current word is the target (beginWord), we have found a complete path.
      if(currWord.equals(target)) {
        // Create a copy of the current path.
        List<String> temp = new ArrayList<>(path);
        // Reverse the path because it was built from endWord to beginWord.
        Collections.reverse(temp);
        // Add the reversed path to the final answer list.
        ans.add(temp);
        // Return to explore other branches.
        return;
      }

      // If the current word is not in the graph (i.e., it has no predecessors that lead to it
      // at the shortest distance), then this branch cannot lead to the beginWord.
      if(!graph.containsKey(currWord)) return;

      // Iterate through all predecessors ('neigh') of the current word in the graph.
      for(String neigh : graph.get(currWord)){
        // Add the predecessor to the current path.
        path.add(neigh);
        // Recursively call func for the predecessor to continue building the path.
        func(neigh,path);
        // Backtrack: Remove the predecessor from the path to explore other possible predecessors
        // and thus other paths. This is crucial for finding all possible shortest paths.
        path.remove(path.size()-1);
      }
    }
    
    // Helper function to check if two strings differ by exactly one character.
    public boolean canGo(String a , String b){
        // Initialize a counter for differing characters.
        int count = 0;
        // Iterate through the characters of both strings.
        for(int i =0 ; i<a.length();i++){
          // If characters at the same position are different, increment the count.
          if(a.charAt(i)!=b.charAt(i)) count++;
          // If the count exceeds 1, it means they differ by more than one character,
          // so they are not valid neighbors. Return false immediately.
          if(count>1) return false;
        }
        // Return true only if the count of differing characters is exactly 1.
        return count==1;
    }
}
```

## Interview Tips
1.  **Explain the Two-Phase Approach**: Clearly articulate that the problem requires a two-phase solution: first, finding the shortest path length using BFS, and second, reconstructing all paths of that length using DFS.
2.  **BFS Level Management is Key**: Emphasize the importance of processing BFS level by level and how `wordSet.removeAll(nextLevel)` is crucial for ensuring only shortest paths are considered. Explain why this prevents exploring longer paths.
3.  **Graph Direction for DFS**: Explain why the graph is built with predecessors (`graph.get(neighbor).add(current_word)`) to enable backtracking from `endWord` to `beginWord` during DFS.
4.  **Edge Cases**: Discuss handling cases where `beginWord` is not in `wordList`, `endWord` is not reachable, or `beginWord` equals `endWord`. The provided solution implicitly handles `beginWord` not being in `wordList` by not removing it from `wordSet` initially if it's not there. If `endWord` is unreachable, `ans` will remain empty.

## Revision Checklist
- [ ] Understand the problem: find ALL shortest transformation sequences.
- [ ] BFS for shortest path length and graph construction.
- [ ] DFS for path reconstruction.
- [ ] Correctly implement level-by-level BFS.
- [ ] Correctly manage `wordSet` to avoid longer paths.
- [ ] Build the predecessor graph in the correct direction.
- [ ] Implement DFS with backtracking.
- [ ] Reverse the reconstructed paths.
- [ ] Handle edge cases (e.g., no path, `beginWord` == `endWord`).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Word Ladder
*   Shortest Path in Binary Matrix
*   Keys and Rooms
*   Find Eventual Safe States

## Tags
`Array` `Hash Map` `String` `Breadth-First Search` `Depth-First Search` `Graph` `Backtracking`
