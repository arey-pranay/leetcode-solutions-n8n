# Guess The Word

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Math` `String` `Minimax` `Interactive` `Game Theory`  
**Time:** O(N * L * G)  
**Space:** O(N * L)

---

## Solution (java)

```java
/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 
 */
 // problem is to minimize guess k calls.
 // random humne isliye lagaya taaki eligible arraylist me se 1 valid index le ske aur baar baar same same index naa aajaye
class Solution {
    public void findSecretWord(String[] words, Master master) {
        int run =0;
        List<String> eligible = new ArrayList<>();
        for(String word : words) eligible.add(word);
        Random random = new Random();
        while(run < 30){
            int index = random.nextInt(eligible.size()); 
            String word = eligible.get(index);
            List<String> newer = new ArrayList<>();
            int match = master.guess(word); // goal is not n or n^2, it is to minimize the calls made to guess
            if(match==6) return;
            for(String word2 : eligible){
                if(charMatches(word,word2) == match){
                    newer.add(word2);
                }
            }
            eligible = newer;
            run++;
        }
    }
    public int charMatches(String word1, String word2){
        int match = 0;
        for(int i=0;i<6;i++) if(word1.charAt(i)==word2.charAt(i)) match++;
        return match;
    }
}
```

---

---
## Quick Revision
The problem asks to find a secret word of length 6 from a given list of words using a Master API with a limited number of guesses.
The solution uses a randomized approach to pick a candidate word and then filters the list of possible words based on the number of character matches returned by the Master API.

## Intuition
The core idea is to efficiently reduce the search space with each guess. If we pick a word and get a certain number of matches, we know that the secret word must also have that exact number of matches with our guessed word. This allows us to eliminate many words from our candidate list. A randomized approach for picking the initial guess helps to avoid worst-case scenarios where a poorly chosen word might not significantly reduce the search space. The goal is to make progress towards the solution quickly, and a random choice is a good heuristic for this.

## Algorithm
1. Initialize a list `eligible` with all the words from the input `words` array.
2. Initialize a `Random` object for random selection.
3. Loop for a maximum of 30 attempts (or until the secret word is found).
4. In each iteration:
    a. Randomly select an index from the current `eligible` list.
    b. Get the word at that index as the `guessWord`.
    c. Call `master.guess(guessWord)` to get the number of character matches (`match`).
    d. If `match` is 6, the secret word is found, so return.
    e. Create a new empty list `newer`.
    f. Iterate through each `candidateWord` in the `eligible` list.
    g. Calculate the number of character matches between `guessWord` and `candidateWord` using a helper function `charMatches`.
    h. If the number of matches equals the `match` returned by the master API, add `candidateWord` to the `newer` list.
    i. Update `eligible` to be `newer`, effectively pruning the search space.
    j. Increment the attempt counter.
5. The `charMatches` helper function takes two strings of length 6 and returns the count of characters that are identical at the same positions.

## Concept to Remember
*   **Randomized Algorithms:** Using randomness to make decisions can often lead to simpler and efficient solutions, especially when dealing with unknown distributions or worst-case scenarios.
*   **Pruning Search Space:** Effectively reducing the number of possibilities with each step is crucial for problems with large search spaces.
*   **Candidate Filtering:** Maintaining a list of valid candidates and filtering them based on constraints is a common pattern in search and guessing games.
*   **Heuristics:** Employing strategies that are likely to work well in practice, even if not mathematically guaranteed to be optimal in all cases.

## Common Mistakes
*   **Not handling the `match == 6` case:** Forgetting to return immediately when the secret word is found.
*   **Inefficient `charMatches`:** Implementing the character matching logic incorrectly or inefficiently.
*   **Not updating the `eligible` list correctly:** Incorrectly filtering words, leading to the loss of the actual secret word.
*   **Fixed guess strategy:** Always picking the first word or a word based on a deterministic rule, which can lead to worst-case performance if the input `words` array is structured adversarially.
*   **Exceeding guess limit:** Not having a mechanism to stop after a reasonable number of guesses, potentially leading to an infinite loop or exceeding the problem's constraints.

## Complexity Analysis
- Time: O(N * L * G) in the worst case, where N is the number of words, L is the length of each word (6 in this case), and G is the maximum number of guesses allowed (30). Each guess involves iterating through the current `eligible` list (up to N words) and comparing characters (L operations). The randomization aims to reduce the effective N in each step.
- Space: O(N * L) to store the `eligible` list of words.

## Commented Code
```java
/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {} // The API to guess a word and get the number of matching characters.
 * }
 */
class Solution {
    // The main method to find the secret word.
    public void findSecretWord(String[] words, Master master) {
        // Initialize a counter for the number of guesses made.
        int run = 0;
        // Create a list to hold all possible candidate words. Initially, it's all words.
        List<String> eligible = new ArrayList<>();
        // Populate the eligible list with all words from the input array.
        for(String word : words) eligible.add(word);
        // Initialize a random number generator for selecting a guess word.
        Random random = new Random();
        // Loop for a maximum of 30 guesses. This is a common heuristic for this problem.
        while(run < 30){
            // Randomly select an index from the current list of eligible words.
            int index = random.nextInt(eligible.size());
            // Get the word at the randomly selected index to use as our guess.
            String word = eligible.get(index);
            // Create a new list to store words that will be eligible after this guess.
            List<String> newer = new ArrayList<>();
            // Make a guess using the Master API and get the number of matching characters.
            int match = master.guess(word);
            // If the number of matches is 6, we have found the secret word, so return.
            if(match==6) return;
            // Iterate through all words currently in the eligible list.
            for(String word2 : eligible){
                // Calculate the number of character matches between our guessed word and the current candidate word.
                // If this count equals the 'match' returned by the master API, it means 'word2' is still a potential secret word.
                if(charMatches(word,word2) == match){
                    // Add the word to the 'newer' list if it's still a valid candidate.
                    newer.add(word2);
                }
            }
            // Update the eligible list to only contain the words that passed the filter.
            eligible = newer;
            // Increment the guess counter.
            run++;
        }
    }

    // Helper function to count the number of matching characters at the same positions between two words.
    public int charMatches(String word1, String word2){
        // Initialize a counter for matches.
        int match = 0;
        // Iterate through each character position (0 to 5 for a 6-letter word).
        for(int i=0;i<6;i++) {
            // If the characters at the current position are the same in both words, increment the match count.
            if(word1.charAt(i)==word2.charAt(i)) match++;
        }
        // Return the total count of matching characters.
        return match;
    }
}
```

## Interview Tips
*   **Explain the strategy:** Clearly articulate why a randomized approach is chosen and how it helps prune the search space.
*   **Discuss edge cases:** Consider what happens if the `words` list is empty, or if the secret word is not in the list (though the problem constraints usually guarantee it is).
*   **Clarify the `guess` API:** Ensure you understand what the `guess` function returns (number of exact matches) and its limitations.
*   **Walk through an example:** Use a small example to demonstrate how the `eligible` list shrinks with each guess.
*   **Mention the 30-guess limit:** Explain that this is a common heuristic and why it's chosen (to balance exploration and exploitation).

## Revision Checklist
- [ ] Understand the `Master` API and its `guess` method.
- [ ] Implement the `charMatches` helper function correctly.
- [ ] Initialize the `eligible` list with all words.
- [ ] Use a `Random` object to pick a guess word.
- [ ] Filter the `eligible` list based on the `master.guess` result.
- [ ] Handle the case where `match == 6`.
- [ ] Ensure the loop has a termination condition (e.g., max guesses).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Word Ladder
*   Boggle Game
*   Hangman (conceptual similarity in guessing letters)

## Tags
`Array` `Hash Map` `Randomized` `Backtracking`
