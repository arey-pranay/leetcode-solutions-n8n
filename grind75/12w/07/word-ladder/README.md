# Word Ladder

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `String` `Breadth-First Search`  
**Time:** O(N \* M \* 26)  
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
The problem is to find the minimum number of single-character changes needed to transform a given word into another word, using a dictionary of valid words.
To solve this problem, we use a breadth-first search (BFS) algorithm with a queue data structure.

## Intuition
This approach works because BFS explores all possible transformations level by level. By maintaining a set of visited words and checking for validity at each step, we efficiently explore the search space without getting stuck in infinite loops.

## Algorithm

1. Convert the input word list to a HashSet for efficient lookups.
2. Check if the end word is present in the word set; return 0 if it's not.
3. Initialize a queue with the starting word and its level (1).
4. While the queue is not empty, dequeue an item and generate all possible transformations by replacing each character with every letter of the alphabet.
5. For each new transformation:
	* Check if it's in the word set and hasn't been visited before.
	* If it matches the end word, return its level + 1.
	* Otherwise, mark it as visited and enqueue it for further exploration.

## Concept to Remember
• **Breadth-First Search (BFS)**: explores all neighbors of a node at each level before moving on to the next level.
• **HashSet**: provides constant-time lookups and insertions.
• **Queue data structure**: useful for implementing BFS and other graph traversal algorithms.

## Common Mistakes

* Failing to handle edge cases, such as an empty word list or invalid input words.
* Not properly initializing the visited set, leading to infinite loops.
* Misunderstanding the BFS algorithm and incorrectly implementing it.

## Complexity Analysis
- Time: O(N \* M \* 26), where N is the average length of a word and M is the number of words in the dictionary. This is because we potentially explore all possible transformations for each word.
- Space: O(N + M), as we store the input words and visited words in memory.

## Commented Code
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
        // Convert word list to HashSet for efficient lookups
        HashSet<String> wordSet = new HashSet<>(wordList);

        // Check if end word is present in the set; return 0 if not
        if(!wordSet.contains(endWord)) return 0;

        // Initialize queue with starting word and its level (1)
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));
        HashSet<String> visited = new HashSet<>();

        while(!q.isEmpty()){
            Pair currPair = q.poll();
            String currWord = currPair.word;
            int currLevel = currPair.level;

            // Generate all possible transformations by replacing each character
            for(int i = 0; i < currWord.length(); i++){
                char[] wordArray = currWord.toCharArray();
                for(char c='a'; c <= 'z'; c++){
                    wordArray[i] = c;
                    String newWord = new String(wordArray);

                    // Check if transformation is valid and hasn't been visited before
                    if(wordSet.contains(newWord) && !visited.contains(newWord)){
                        if(newWord.equals(endWord)) return currLevel + 1; // found the end word!
                        q.offer(new Pair(newWord, currLevel + 1));
                        visited.add(newWord);
                    }
                }
            }
        }

        return 0;
    }
}
```

## Interview Tips

* Be prepared to explain your BFS algorithm and how it applies to this problem.
* Make sure to handle edge cases and invalid input correctly.
* Use a queue data structure to implement the BFS, as it's essential for this problem.

## Revision Checklist
- [ ] Review BFS algorithm and its implementation.
- [ ] Ensure correct handling of edge cases and invalid input.
- [ ] Understand how the HashSet operations affect time complexity.

## Similar Problems

* Word Chain (LeetCode #953)
* Shortest Path in a Grid (LeetCode #79)
