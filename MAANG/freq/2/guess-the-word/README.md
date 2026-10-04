# Guess The Word

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Math` `String` `Minimax` `Interactive` `Game Theory`  
**Time:** O(N * M * G)  
**Space:** O(N)

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
The problem asks to guess a secret word of length 6 using a Master API with a limited number of guesses.
The solution uses a randomized approach to pick a candidate word and then filters the list of possible words based on the number of matching characters returned by the API.

## Intuition
The core idea is to efficiently reduce the search space with each guess. If we pick a word that has a certain number of matches with the secret word, we can eliminate all other words that *don't* have that exact number of matches with our chosen word. A randomized approach helps avoid worst-case scenarios where a poorly chosen pivot word might not eliminate many candidates. The goal is to make progress towards the secret word by narrowing down the possibilities.

## Algorithm
1. Initialize a list `eligible` with all the words from the input `words` array.
2. Initialize a `Random` object for random selection.
3. Loop for a maximum of 30 attempts (a reasonable upper bound for this problem, as 1000 words * 30 guesses is unlikely to be needed).
4. Inside the loop:
    a. Randomly select an index from the `eligible` list.
    b. Get the word at that random index, let's call it `candidate_word`.
    c. Call `master.guess(candidate_word)` to get the number of matching characters, `match_count`.
    d. If `match_count` is 6, the secret word has been found, so return.
    e. Create a new empty list `newer_eligible`.
    f. Iterate through each `word` in the current `eligible` list.
    g. For each `word`, calculate the number of character matches between `candidate_word` and `word` using a helper function `charMatches`.
    h. If the number of matches equals `match_count`, add `word` to `newer_eligible`.
    i. Update `eligible` to be `newer_eligible`.
    j. Increment the attempt counter.
5. If the loop finishes without finding the word (which is unlikely given the constraints and typical test cases), the function implicitly returns.

## Concept to Remember
*   **Randomized Algorithms:** Using randomness to improve average-case performance or avoid deterministic worst-case scenarios.
*   **Search Space Reduction:** Efficiently pruning the set of possible solutions based on feedback.
*   **Candidate Elimination:** A strategy where incorrect hypotheses are systematically removed.
*   **Helper Functions:** Breaking down complex logic into smaller, manageable, and reusable functions (like `charMatches`).

## Common Mistakes
*   **Not handling the `match == 6` case early:** Failing to return immediately when the secret word is guessed.
*   **Inefficient `charMatches` function:** A slow character comparison could impact overall performance.
*   **Deterministic pivot selection:** Always picking the first word or a word based on a fixed pattern, which can lead to worst-case scenarios.
*   **Not correctly filtering candidates:** Errors in the logic for comparing `candidate_word` with other `eligible` words and their match counts.
*   **Exceeding guess limit without a strategy:** Not having a reasonable upper bound on guesses or a clear exit condition.

## Complexity Analysis
*   **Time:** O(N * M * G), where N is the number of words in the input list, M is the length of each word (which is 6), and G is the maximum number of guesses (given as 30 in the code). In each guess, we iterate through the current `eligible` list (at most N words) and for each word, we compare characters (M operations). The `eligible` list shrinks over time. The dominant factor is the filtering process within the guess limit.
*   **Space:** O(N) to store the `eligible` list. In the worst case, the `eligible` list might initially contain all N words.

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
    /**
     * Finds the secret word using the Master API.
     * @param words A list of possible secret words.
     * @param master The Master API object.
     */
    public void findSecretWord(String[] words, Master master) {
        int run = 0; // Initialize a counter for the number of guesses made.
        List<String> eligible = new ArrayList<>(); // Create a list to store words that are still potential candidates.
        for(String word : words) { // Iterate through all the given words.
            eligible.add(word); // Add each word to the initial list of eligible candidates.
        }
        Random random = new Random(); // Initialize a Random object to pick words randomly.
        while(run < 30){ // Loop for a maximum of 30 guesses. This is a heuristic limit.
            int index = random.nextInt(eligible.size()); // Generate a random index within the bounds of the current eligible list.
            String word = eligible.get(index); // Get the word at the randomly selected index. This is our current guess.
            List<String> newer = new ArrayList<>(); // Create a new list to store candidates for the next iteration.
            int match = master.guess(word); // Call the Master API to guess the word and get the number of matching characters.
            if(match == 6) { // If the number of matches is 6, we have found the secret word.
                return; // Exit the function.
            }
            for(String word2 : eligible){ // Iterate through all words currently in the eligible list.
                // Check if the number of character matches between the guessed word ('word') and the current candidate ('word2')
                // is exactly equal to the number of matches returned by the master API ('match').
                if(charMatches(word, word2) == match){
                    newer.add(word2); // If the match count is the same, this word ('word2') is still a potential candidate. Add it to the 'newer' list.
                }
            }
            eligible = newer; // Update the eligible list to contain only the words that passed the filtering criteria.
            run++; // Increment the guess counter.
        }
    }

    /**
     * Calculates the number of characters that match at the same position in two words.
     * @param word1 The first word.
     * @param word2 The second word.
     * @return The count of matching characters at the same index.
     */
    public int charMatches(String word1, String word2){
        int match = 0; // Initialize a counter for matching characters.
        for(int i = 0; i < 6; i++) { // Iterate through each character position (0 to 5, since words are length 6).
            if(word1.charAt(i) == word2.charAt(i)) { // Compare the characters at the current index 'i' in both words.
                match++; // If the characters are the same, increment the match counter.
            }
        }
        return match; // Return the total count of matching characters.
    }
}
```

## Interview Tips
*   **Explain your strategy:** Clearly articulate why you're choosing a random word and how the filtering process works.
*   **Discuss edge cases:** What if the input `words` list is empty? What if no word can be formed with the given constraints? (Though typically LeetCode problems have valid inputs).
*   **Justify the guess limit:** Explain why 30 guesses is a reasonable heuristic, or ask the interviewer if there's a specific constraint on the number of guesses.
*   **Clarify the `Master` API:** Ensure you understand what `guess()` returns and its implications.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Implement the `charMatches` helper function correctly.
- [ ] Initialize the `eligible` list with all words.
- [ ] Implement the main loop with a guess limit.
- [ ] Use `Random` for selecting a candidate word.
- [ ] Call `master.guess()` and check for the `match == 6` condition.
- [ ] Filter the `eligible` list based on the `match` count.
- [ ] Update the `eligible` list for the next iteration.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Word Ladder
*   Scrabble Score
*   Boggle Game

## Tags
`Array` `Hash Map` `Randomized` `Backtracking`
