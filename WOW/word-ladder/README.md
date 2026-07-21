# Word Ladder

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `String` `Breadth-First Search`  
**Time:** O(N \* M \* 26)  
**Space:** See complexity section

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
// hum har character ko abcd to z tk replace kr rhe hai aur check kr rhe hai kya vo set m hai agr hai to queue m daal dete hai aur aage badh jaate hai , phir aakhir tk hoga sb change to ab hum que se nikaal kr ek level badha dete hai
```

---

---
## Quick Revision
Word Ladder problem is to find the shortest transformation sequence from one word to another using a given list of words.
We solve this problem by implementing a Breadth-First Search (BFS) algorithm with a queue data structure.

## Intuition
The key insight here is that we can transform each character in the current word into any letter of the alphabet, and check if the resulting word is present in the word list. If it is, we add it to the queue for further exploration. This process continues until we find the target word or exhaust all possible transformations.

## Algorithm
1. Convert the given word list into a hash set for efficient lookups.
2. Create a queue to hold words to be processed, and initialize it with the start word.
3. While the queue is not empty:
	* Dequeue the next word from the queue.
	* For each character position in the current word (i.e., each character):
		+ Replace the character at that position with every possible letter of the alphabet (from 'a' to 'z').
		+ Check if the resulting words are present in the word set and not visited before.
		+ If a new word is found, add it to the queue and mark it as visited.
4. Return the level (i.e., the length of the shortest transformation sequence) when the target word is reached.

## Concept to Remember
* **Hash Set**: allows for efficient lookups and insertion/deletion operations.
* **Queue Data Structure**: enables BFS traversal by maintaining a First-In-First-Out (FIFO) order of elements.
* **Breadth-First Search (BFS)**: explores all nodes at the current depth before moving on to the next level.

## Common Mistakes
* Failing to handle edge cases, such as an empty word list or invalid input words.
* Not properly initializing the queue and visited sets.
* Confusing the BFS algorithm with Depth-First Search (DFS) or other traversal methods.

## Complexity Analysis
- Time: O(N \* M \* 26), where N is the length of the longest word, M is the number of words in the list, and 26 represents the alphabet size.
- Reason: Each character position can be replaced by up to 26 different letters, resulting in a total of N \* M \* 26 possible transformations.

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
        // Convert the word list into a hash set
        HashSet<String> wordSet = new HashSet<>(wordList);
        
        // Initialize the queue and visited sets
        Queue<Pair> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        if (!wordSet.contains(endWord)) return 0;

        // Add the start word to the queue and mark it as visited
        q.add(new Pair(beginWord, 1));
        visited.add(beginWord);

        while (!q.isEmpty()) {
            // Dequeue the next word from the queue
            Pair currPair = q.poll();
            String currWord = currPair.word;
            int currLevel = currPair.level;

            for (int i = 0; i < currWord.length(); i++) {
                char[] wordArray = currWord.toCharArray();

                // Replace each character with every possible letter of the alphabet
                for (char c = 'a'; c <= 'z'; c++) {
                    wordArray[i] = c;
                    String newWord = new String(wordArray);

                    // Check if the resulting word is present in the word set and not visited before
                    if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                        // If a new word is found, add it to the queue and mark it as visited
                        q.offer(new Pair(newWord, currLevel + 1));
                        visited.add(newWord);

                        // Return the level when the target word is reached
                        if (newWord.equals(endWord)) return currLevel + 1;
                    }
                }
            }
        }

        return 0;
    }
}
```

## Interview Tips

* Make sure to handle edge cases and invalid input words.
* Use a hash set to efficiently store and look up the word list.
* Implement BFS using a queue data structure for efficient traversal.
* Pay attention to the level (i.e., the length of the shortest transformation sequence) when returning the result.

## Revision Checklist
- [ ] Review the implementation of the Breadth-First Search (BFS) algorithm.
- [ ] Ensure that the hash set is properly initialized and used throughout the code.
- [ ] Verify that edge cases, such as an empty word list or invalid input words, are handled correctly.

## Similar Problems

* LeetCode 79. Word Search
* LeetCode 127. Word Ladder II (extension of this problem)
* LeetCode 473. Matchsticks to Square
