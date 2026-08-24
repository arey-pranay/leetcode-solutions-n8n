# Word Ladder Ii

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `String` `Backtracking` `Breadth-First Search` `Bidirectional Search`  
**Time:** O(N * L^2 * 26)  
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
This is solved using a bidirectional BFS to find shortest paths and then DFS to reconstruct all such paths.

## Intuition
The problem asks for *all* shortest paths, not just one. A standard BFS finds the shortest path length. To find all shortest paths, we need to explore level by level and keep track of how we reached each word. Once we find the `endWord`, we know the shortest path length. Then, we can use a Depth First Search (DFS) starting from the `endWord` and working backward using the parent pointers (or graph) built during BFS to reconstruct all valid shortest paths.

The provided solution uses a slightly modified BFS. It first builds a graph where an edge exists between two words if they differ by one character. This graph is built by processing words level by level. After the BFS completes, it uses DFS to reconstruct paths from `endWord` back to `beginWord` using the constructed graph.

## Algorithm
1.  **Initialization**:
    *   Create an empty list `ans` to store all the resulting word ladders.
    *   Create a `graph` (e.g., `HashMap<String, HashSet<String>>`) to store the adjacency list representation of words that are one character apart.
    *   Convert `wordList` into a `HashSet` called `wordSet` for efficient lookups.
    *   Initialize a `Queue` for BFS, adding `beginWord`.
    *   Remove `beginWord` from `wordSet` to avoid cycles and redundant processing.
    *   Set `target` to `beginWord` (this seems to be used as the starting point for DFS reconstruction).
    *   Initialize a `found` flag to `false`.

2.  **BFS (Level by Level Graph Construction)**:
    *   While the queue is not empty:
        *   Get the current level's size (`sz`).
        *   Create a `HashSet` called `nextLevel` to store words to be added to the queue in the next iteration.
        *   Process `sz` words from the queue:
            *   Dequeue a `word`.
            *   Iterate through all `neigh` in `wordSet`:
                *   If `word` and `neigh` differ by exactly one character (using `canGo` function):
                    *   Add an edge from `neigh` to `word` in the `graph`. If `neigh` is not already a key, initialize its `HashSet`.
                    *   Add `neigh` to `nextLevel`.
                    *   If `neigh` is the `endWord`, set `found` to `true`.
        *   Remove all words in `nextLevel` from `wordSet` to ensure we only consider shortest paths.
        *   If `found` is `true`, break the BFS loop (we've found the shortest path length).
        *   Add all words from `nextLevel` to the queue for the next level's processing.

3.  **DFS (Path Reconstruction)**:
    *   Initialize a `path` list with `endWord`.
    *   Call a recursive helper function `func(endWord, path)`.

4.  **`func(currWord, path)` Helper Function**:
    *   **Base Case**: If `currWord` is equal to `target` (`beginWord`):
        *   Create a copy of the current `path`.
        *   Reverse the copied `path`.
        *   Add the reversed `path` to the `ans` list.
        *   Return.
    *   **Recursive Step**: If `currWord` is a key in the `graph`:
        *   For each `neigh` in `graph.get(currWord)`:
            *   Add `neigh` to the current `path`.
            *   Recursively call `func(neigh, path)`.
            *   **Backtrack**: Remove `neigh` from the `path` (to explore other branches).

5.  **`canGo(a, b)` Helper Function**:
    *   Checks if two strings `a` and `b` differ by exactly one character.
    *   Iterate through the characters of both strings.
    *   Count the number of differing characters.
    *   Return `true` if the count is exactly 1, `false` otherwise.

6.  **Return `ans`**.

## Concept to Remember
*   **Breadth-First Search (BFS)**: Used to find the shortest path length and explore words level by level.
*   **Depth-First Search (DFS)**: Used to reconstruct all shortest paths once the graph and shortest distance are established.
*   **Graph Representation**: Adjacency list is suitable for representing relationships between words that are one character apart.
*   **Backtracking**: Essential in DFS to explore all possible paths without getting stuck in a single branch.

## Common Mistakes
*   **Not handling `beginWord` correctly**: `beginWord` might not be in `wordList`. It should be treated as the starting point.
*   **Infinite loops/Cycles**: Not removing visited words from the `wordSet` at each level can lead to cycles and incorrect paths.
*   **Reconstructing paths incorrectly**: Forgetting to reverse the path after DFS or not handling the base case of DFS properly.
*   **Inefficient `canGo` function**: A naive character-by-character comparison is fine, but ensure it's correct.
*   **Not finding *all* shortest paths**: A simple BFS only finds one shortest path. The approach needs to store parent information or build a graph to allow for multiple path reconstructions.

## Complexity Analysis
*   **Time**: O(N * L^2 * 26) in the worst case for BFS, where N is the number of words and L is the length of each word. For each word, we iterate through all other words (N) and compare characters (L). The `26` comes from generating neighbors, but this solution iterates through `wordSet` instead. So, it's closer to O(N^2 * L) for BFS. The DFS part can be exponential in the worst case if there are many paths, but since we are only considering shortest paths found by BFS, it's bounded. The graph construction is the dominant factor.
    *   Reason: The BFS explores each word at most once. For each word, it iterates through the remaining `wordSet` (up to N words) and calls `canGo` (which takes O(L) time). So, BFS is roughly O(N^2 * L). The DFS part, while potentially exponential, is constrained by the shortest path length found by BFS.
*   **Space**: O(N * L) for storing the graph, queue, and word set.
    *   Reason: The `graph` can store up to N words, each with potentially N neighbors (though practically much fewer). The `wordSet` stores N words. The queue and recursion stack for DFS can also store up to N words.

## Commented Code
```java
class Solution {
    // List to store all the valid word ladders (shortest paths)
    List<List<String>> ans = new ArrayList<>();
    // Adjacency list to represent the graph where keys are words and values are sets of words that are one character different
    HashMap<String,HashSet<String>> graph = new HashMap<>();
    // A set to store all words from the wordList for efficient lookups
    HashSet<String> wordSet;
    // The starting word of the transformation
    String target; // Renamed from beginWord in the original code for clarity in DFS context

    // Main function to find all shortest transformation sequences
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
      // Initialize the wordSet with all words from the input list
      wordSet = new HashSet<>(wordList);
      // Queue for Breadth-First Search (BFS)
      Queue<String> q = new LinkedList<>();
      // Remove beginWord from wordSet if it exists, as we start from it and don't want to revisit it immediately
      wordSet.remove(beginWord);
      // Add the beginWord to the queue to start the BFS
      q.add(beginWord);
      // Set the target for DFS reconstruction (the starting word)
      target = beginWord;
      // Flag to indicate if the endWord has been found during BFS
      boolean found = false;

      // BFS loop: continues as long as there are words to process in the queue
      while(!q.isEmpty()){ // O(N) iterations in total for BFS levels
        // Get the number of words at the current level of BFS
        int sz = q.size();
        // A set to store words that will be processed in the next level
        HashSet<String> nextLevel = new HashSet<>();

        // Process all words at the current level
        while(sz-->0){
          // Dequeue the current word to process
          String word = q.poll();

          // Iterate through all available words in the wordSet to find neighbors
          for(String neigh : wordSet){
            // Check if the current word and the neighbor differ by exactly one character
            if(!canGo(word,neigh)) continue; // If not, skip to the next neighbor

            // If a valid neighbor is found, build the graph:
            // Ensure the neighbor exists as a key in the graph, if not, initialize its HashSet
            if(!graph.containsKey(neigh)) graph.put(neigh,new HashSet<>());
            // Add the current word to the set of predecessors for the neighbor
            // This means 'word' can transform into 'neigh' in one step (or 'neigh' can transform into 'word' in reverse)
            graph.get(neigh).add(word);

            // Add the neighbor to the set of words to be processed in the next level
            nextLevel.add(neigh);
            // If the neighbor is the endWord, set the found flag to true
            if(neigh.equals(endWord)) found=true; // Use .equals for string comparison
          }
        }

        // After processing all words at the current level, remove them from wordSet
        // This ensures that we only consider shortest paths and avoid cycles
        wordSet.removeAll(nextLevel);

        // If the endWord was found in this level, we can stop BFS early
        // because we are only interested in shortest paths.
        // If we don't break, we might find longer paths.
        if(!found)
          // Add all words from the nextLevel to the queue for processing in the subsequent BFS level
          q.addAll(nextLevel);
      }

      // After BFS, we have constructed a graph representing possible transformations.
      // Now, use DFS to reconstruct all shortest paths from endWord back to beginWord.
      List<String> path = new ArrayList<>();
      // Start the path with the endWord
      path.add(endWord);
      // Call the recursive DFS function to find all paths
      func(endWord,path);
      // Return the list of all found shortest word ladders
      return ans;
    }

    // Recursive DFS function to reconstruct paths
    public void func(String currWord, List<String> path){
      // Base case: If the current word is the target (beginWord), we have found a complete path
      if(currWord.equals(target)) {
        // Create a copy of the current path
        List<String> temp = new ArrayList<>(path);
        // Reverse the path because we built it from endWord to beginWord
        Collections.reverse(temp);
        // Add the reversed path to the final answer list
        ans.add(temp);
        // Return to explore other branches
        return;
      }
      // If the current word is not in the graph (meaning it has no predecessors that lead to it in a shortest path), return
      if(!graph.containsKey(currWord)) return;

      // Recursive step: Iterate through all predecessors of the current word in the graph
      for(String neigh : graph.get(currWord)){
        // Add the predecessor to the current path
        path.add(neigh);
        // Recursively call func for the predecessor
        func(neigh,path);
        // Backtrack: Remove the predecessor from the path to explore other branches
        path.remove(path.size()-1);
      }
    }
    
    // Helper function to check if two strings differ by exactly one character
    public boolean canGo(String a , String b){
        // Counter for differing characters
        int count = 0;
        // Iterate through the characters of both strings
        for(int i =0 ; i<a.length();i++){
          // If characters at the same position are different, increment count
          if(a.charAt(i)!=b.charAt(i)) count++;
          // If the count exceeds 1, they differ by more than one character, so return false immediately
          if(count>1) return false;
        }
        // Return true if and only if the count of differing characters is exactly 1
        return count==1;
    }
}
```

## Interview Tips
*   **Explain the Two-Phase Approach**: Clearly articulate that you'll use BFS to find the shortest path length and build a graph, followed by DFS to reconstruct all shortest paths.
*   **Handle Edge Cases**: Discuss what happens if `beginWord` is the same as `endWord`, or if `endWord` is not reachable. The current solution implicitly handles `beginWord == endWord` by returning `[[beginWord]]` if `endWord` is reachable. If `endWord` is not reachable, `ans` will be empty.
*   **Graph Construction Logic**: Emphasize why you're building the graph in reverse (from `neigh` to `word` in the BFS loop) and how this facilitates the DFS reconstruction from `endWord` back to `beginWord`.
*   **BFS Level Processing**: Explain the importance of processing level by level and removing visited words from `wordSet` at each level to guarantee shortest paths.

## Revision Checklist
- [ ] Understand the problem: find ALL shortest word ladders.
- [ ] BFS for shortest path length and graph construction.
- [ ] DFS for path reconstruction.
- [ ] `canGo` function logic.
- [ ] Graph representation (adjacency list).
- [ ] Backtracking in DFS.
- [ ] Handling `beginWord` and `endWord`.
- [ ] Time and Space Complexity.

## Similar Problems
*   Word Ladder
*   Shortest Path in Binary Matrix
*   Keys and Rooms
*   Find the City With the Smallest Number of Neighbors at a Threshold Distance

## Tags
`Array` `Hash Map` `String` `Breadth-First Search` `Depth-First Search` `Graph` `Backtracking`
