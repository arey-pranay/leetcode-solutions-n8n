# Word Ladder

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `String` `Breadth-First Search`  
**Time:** O(N * M * 26)  
**Space:** O(N + M)

---

## Solution (java)

```java
class Solution {
    class Pair{
        String word;
        int level;
        Pair(String w, int l){
            this.word = w;
            this.level = l;
        }
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {    
        HashSet<String> wordSet = new HashSet<>(wordList);
        HashSet<String> visited = new HashSet<>();
        
        if(!wordSet.contains(endWord)) return 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord,1));
        visited.add(beginWord);
        while(!q.isEmpty()){
            Pair currPair = q.poll();
            String currWord = currPair.word;
            int currLevel = currPair.level;
            for(int i =0;i<currWord.length();i++){
                char[] wordArray = currWord.toCharArray();
                for(char c='a';c<='z';c++){
                    wordArray[i] = c;
                    String newWord = new String(wordArray);
                    if(wordSet.contains(newWord) && !visited.contains(newWord)){
                        if(newWord.equals(endWord)) return currLevel+1;
                        q.offer(new Pair(newWord,currLevel+1));
                        visited.add(newWord);
                    }           
                }
            }
        }
        return 0;
    }
}

```

---

---
## Quick Revision
The problem requires transforming a given word into another word by changing one letter at a time, with each intermediate word being in a dictionary. This can be solved using breadth-first search (BFS) to explore all possible transformations.

## Intuition
This approach works because BFS is suitable for finding the shortest path between two nodes in an unweighted graph, which represents the transformation of words from one letter to another. By exploring all possible changes at each level, we can find the shortest ladder that connects the start and end word.

## Algorithm
1. Convert the input dictionary into a HashSet for efficient lookups.
2. Perform BFS from the start word:
	* Enqueue the start word with its corresponding level (distance).
	* Mark the start word as visited.
3. While the queue is not empty, dequeue an element and explore its neighbors:
	* For each position in the word, replace the character with all possible letters ('a' to 'z').
	* Check if the resulting word is in the dictionary and has not been visited before.
	* If it's the target word, return the current level + 1 as the length of the ladder.
	* Otherwise, mark the new word as visited and enqueue it with its updated level.

## Concept to Remember
• **Breadth-First Search (BFS)**: Suitable for finding shortest paths in unweighted graphs or traversing trees level by level.
• **HashSet**: Allows efficient lookups and insertions, which is crucial for this problem's BFS implementation.
• **Character Replacement**: Understanding how to modify a string by replacing individual characters with all possible values.

## Common Mistakes
• Failing to use an efficient data structure like HashSet for the word dictionary.
• Not correctly implementing BFS logic, leading to incorrect or incomplete results.
• Overlooking edge cases, such as when the target word is not in the dictionary.

## Complexity Analysis
- Time: O(N * M * 26), where N is the number of words and M is the average length of a word. This accounts for the BFS traversal and character replacement at each level.
- Space: O(N + M), which includes the space used by the HashSet, queue, and other auxiliary data structures.

## Commented Code
```java
class Solution {
    class Pair {
        String word;
        int level;

        Pair(String w, int l) {
            this.word = w;
            this.level = l;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert the input dictionary into a HashSet
        HashSet<String> wordSet = new HashSet<>(wordList);

        // Check if the target word is in the dictionary
        if (!wordSet.contains(endWord)) return 0;

        // Initialize BFS queue with start word and mark it as visited
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));
        HashSet<String> visited = new HashSet<>();
        visited.add(beginWord);

        while (!q.isEmpty()) {
            Pair currPair = q.poll();
            String currWord = currPair.word;
            int currLevel = currPair.level;

            // Explore neighbors of the current word
            for (int i = 0; i < currWord.length(); i++) {
                char[] wordArray = currWord.toCharArray();

                // Replace character at position 'i' with all possible letters ('a' to 'z')
                for (char c = 'a'; c <= 'z'; c++) {
                    wordArray[i] = c;
                    String newWord = new String(wordArray);

                    // Check if the resulting word is in the dictionary and has not been visited
                    if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                        // If it's the target word, return the current level + 1 as the length of the ladder
                        if (newWord.equals(endWord)) return currLevel + 1;
                        // Otherwise, mark the new word as visited and enqueue it with its updated level
                        q.offer(new Pair(newWord, currLevel + 1));
                        visited.add(newWord);
                    }
                }
            }
        }

        // If no ladder is found, return 0
        return 0;
    }
}
```

## Interview Tips

• Be prepared to explain the BFS algorithm and its application in this problem.
• Highlight the importance of using an efficient data structure like HashSet for lookups.
• Practice implementing BFS from scratch on a whiteboard or with a sample input.

## Revision Checklist
- [ ] Understand how to convert the input dictionary into a HashSet.
- [ ] Implement correct BFS logic, including marking visited words and updating levels.
- [ ] Review edge cases, such as when the target word is not in the dictionary.

## Similar Problems

* 127. Word Ladder II (LeetCode)
* 752. Open the Lock (LeetCode)

## Tags
`Array`, `Hash Map`, `Breadth-First Search`, `Graph Traversal`
