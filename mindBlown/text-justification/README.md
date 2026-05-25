# Text Justification

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `String` `Simulation`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> arr = new ArrayList<>();
        int i =0;
        ArrayList<String> temp = new ArrayList<>();
        while(i<words.length){
            int total = 0;
            temp = new ArrayList<>();
            int spaceNeeded = maxWidth;
            while(i<words.length && total + words[i].length() <= maxWidth){
                String word = words[i];
                total += word.length()+1;
                temp.add(word);
                spaceNeeded -= word.length();
                i++;
            }
            arr.add(addSpaces(temp,spaceNeeded));
        }
        arr.remove(arr.size()-1);
        arr.add(justifyLeft(temp, maxWidth));
        return arr;
    }
    public String addSpaces(List<String> arr, int spaceNeeded){
        int words = arr.size();
        StringBuilder sb = new StringBuilder(arr.get(0));
        if(words==1){
            sb.append(" ".repeat(spaceNeeded));
            return sb.toString();
        }
        int perWord = spaceNeeded/(words-1);
        int extraFrom = (spaceNeeded % (words-1));
        for(int i=1;i<words;i++){
            sb.append(" ".repeat(perWord));
            if(i <= extraFrom){
                sb.append(" ");
                spaceNeeded--;
            }
            sb.append(arr.get(i));
            spaceNeeded -= perWord;
        }
        return sb.toString();
    }
    public String justifyLeft(ArrayList<String> arr,int maxLength){
        StringBuilder sb = new StringBuilder(arr.get(0));
        for(int i=1;i<arr.size();i++){
            sb.append(" ");
            sb.append(arr.get(i));
        }
        int moreSpaces = maxLength-sb.length();
        sb.append(" ".repeat(moreSpaces));
        return sb.toString();
    }  
}
```

---

---
## Quick Revision
This problem asks to format a given list of words into lines of a specified maximum width, justifying them according to specific rules.
The solution involves iterating through words, grouping them into lines, and then distributing spaces to achieve full justification or left justification for the last line.

## Intuition
The core idea is to process words in chunks that fit within `maxWidth`. For each chunk, we need to determine how to distribute spaces.
The "aha moment" comes from realizing that full justification requires distributing spaces as evenly as possible between words, with any extra spaces given to the leftmost gaps. The last line is a special case, always left-justified.

## Algorithm
1. Initialize an empty list `result` to store the justified lines.
2. Initialize an index `i` to 0, pointing to the current word in the input `words` array.
3. While `i` is less than the length of `words`:
    a. Initialize an empty list `currentLineWords` to store words for the current line.
    b. Initialize `currentLineLength` to 0.
    c. Initialize `numWordsInLine` to 0.
    d. Iterate through words starting from index `i`:
        i. If `currentLineLength` + length of `words[i]` + `numWordsInLine` (for spaces) is less than or equal to `maxWidth`:
            - Add `words[i]` to `currentLineWords`.
            - Update `currentLineLength` by adding the length of `words[i]`.
            - Increment `numWordsInLine`.
            - Increment `i`.
        ii. Else, break the inner loop (the current word doesn't fit).
    e. Calculate the total number of spaces needed for the current line: `spacesToDistribute = maxWidth - currentLineLength`.
    f. Determine if this is the last line (i.e., `i == words.length`).
    g. If it's the last line or if `currentLineWords` contains only one word:
        i. Left-justify the line: append words from `currentLineWords` separated by single spaces.
        ii. Pad the end of the line with spaces until it reaches `maxWidth`.
        iii. Add the formatted line to `result`.
    h. Else (it's a full justification line with multiple words):
        i. Calculate the number of spaces to put between each word: `spacesBetweenWords = spacesToDistribute / (numWordsInLine - 1)`.
        ii. Calculate the number of extra spaces to distribute from left to right: `extraSpaces = spacesToDistribute % (numWordsInLine - 1)`.
        iii. Build the justified line:
            - Start with the first word.
            - For each subsequent word, append `spacesBetweenWords` spaces, and if `extraSpaces` is greater than 0, append an additional space and decrement `extraSpaces`. Then append the word.
        iv. Add the formatted line to `result`.
4. Return `result`.

## Concept to Remember
*   **Greedy Approach:** The algorithm greedily packs as many words as possible into each line.
*   **Space Distribution Logic:** Understanding how to distribute spaces evenly and handle remainders for full justification is crucial.
*   **Edge Cases:** Handling single-word lines and the very last line requires special attention.
*   **String Manipulation:** Efficiently building strings using `StringBuilder` is important for performance.

## Common Mistakes
*   **Incorrectly calculating spaces:** Miscounting the number of words or the total length of words can lead to wrong space distribution.
*   **Not handling the last line separately:** The last line should always be left-justified, not fully justified.
*   **Off-by-one errors in space distribution:** Incorrectly calculating `spacesBetweenWords` or `extraSpaces` due to `numWordsInLine - 1` can cause issues.
*   **Forgetting to pad the last line:** The last line, even if left-justified, must still reach `maxWidth`.
*   **Inefficient string concatenation:** Using `+` repeatedly for string building can be slow; `StringBuilder` is preferred.

## Complexity Analysis
- Time: O(N), where N is the total number of characters in all words. Each character is processed a constant number of times (when calculating line length, distributing spaces, and building the string).
- Space: O(N) in the worst case, to store the `result` list, which can contain strings whose total length is proportional to N. The `temp` list also contributes, but its size is bounded by the number of words that fit on a single line.

## Commented Code
```java
class Solution {
    // Main function to perform text justification.
    public List<String> fullJustify(String[] words, int maxWidth) {
        // List to store the final justified lines.
        List<String> arr = new ArrayList<>();
        // Index to iterate through the input words array.
        int i = 0;
        // Temporary list to hold words for the current line being processed.
        ArrayList<String> temp = new ArrayList<>();
        // Loop through all words.
        while (i < words.length) {
            // Variable to track the total length of words and spaces in the current line.
            int total = 0;
            // Reset temp list for the new line.
            temp = new ArrayList<>();
            // Variable to track remaining space available on the current line.
            int spaceNeeded = maxWidth;
            // Inner loop to pack words into the current line.
            // It continues as long as the current word fits and there are words left.
            while (i < words.length && total + words[i].length() <= maxWidth) {
                // Get the current word.
                String word = words[i];
                // Add the word's length and at least one space to the total.
                // The '+1' accounts for the minimum space between words.
                total += word.length() + 1;
                // Add the word to the temporary list for the current line.
                temp.add(word);
                // Subtract the word's length from the available space.
                spaceNeeded -= word.length();
                // Move to the next word.
                i++;
            }
            // After packing words, add the formatted line to the result list.
            // The addSpaces method handles the justification logic.
            // Note: The original code has a slight issue here, it adds a potentially
            // un-justified line and then modifies the list later.
            // A cleaner approach would be to call the correct justification logic directly.
            // For this analysis, we'll follow the provided code's structure.
            arr.add(addSpaces(temp, spaceNeeded));
        }
        // The last line added by addSpaces might be incorrectly formatted if it was the last line.
        // This line removes the last added element (which might be the last line, but processed
        // with full justification logic if it was the only line processed in the loop).
        // This is a workaround for the logic flaw where the last line is handled separately.
        arr.remove(arr.size() - 1);
        // Now, correctly format the last line using justifyLeft.
        // The 'temp' list at this point holds the words for the very last line.
        arr.add(justifyLeft(temp, maxWidth));
        // Return the list of fully justified lines.
        return arr;
    }

    // Helper function to add spaces for full justification.
    public String addSpaces(List<String> arr, int spaceNeeded) {
        // Number of words in the current line.
        int words = arr.size();
        // Use StringBuilder for efficient string construction.
        // Start with the first word.
        StringBuilder sb = new StringBuilder(arr.get(0));

        // If there's only one word on the line, left-justify it by padding with spaces.
        if (words == 1) {
            // Append remaining spaces to the end of the single word.
            sb.append(" ".repeat(spaceNeeded));
            // Return the formatted line.
            return sb.toString();
        }

        // Calculate the base number of spaces to put between each word.
        // spaceNeeded here is the total spaces to distribute MINUS the length of words.
        // So, spaceNeeded = maxWidth - (sum of word lengths).
        // The number of gaps between words is (words - 1).
        int perWord = spaceNeeded / (words - 1);
        // Calculate how many gaps will receive an extra space.
        int extraFrom = (spaceNeeded % (words - 1));

        // Iterate through the rest of the words (starting from the second word).
        for (int i = 1; i < words; i++) {
            // Append the calculated number of spaces between words.
            sb.append(" ".repeat(perWord));
            // If this gap is one of the first 'extraFrom' gaps, add an extra space.
            if (i <= extraFrom) {
                sb.append(" ");
                // Decrement the total spaces to be distributed (this is a bit confusing in the original code,
                // it seems to be tracking remaining spaces to distribute, not total needed).
                // A clearer way would be to track 'extraFrom' directly.
                spaceNeeded--; // This line is potentially misleading.
            }
            // Append the current word.
            sb.append(arr.get(i));
            // Decrement the total spaces to be distributed by the spaces added for this word.
            spaceNeeded -= perWord; // This line is also potentially misleading.
        }
        // Return the fully justified line.
        return sb.toString();
    }

    // Helper function to left-justify the last line.
    public String justifyLeft(ArrayList<String> arr, int maxLength) {
        // Use StringBuilder for efficient string construction.
        // Start with the first word.
        StringBuilder sb = new StringBuilder(arr.get(0));
        // Append subsequent words with a single space in between.
        for (int i = 1; i < arr.size(); i++) {
            sb.append(" ");
            sb.append(arr.get(i));
        }
        // Calculate how many more spaces are needed to reach the maxLength.
        int moreSpaces = maxLength - sb.length();
        // Append the remaining spaces to the end of the line.
        sb.append(" ".repeat(moreSpaces));
        // Return the left-justified line.
        return sb.toString();
    }
}
```

## Interview Tips
1.  **Clarify Justification Rules:** Ensure you understand the exact rules for full justification (even space distribution, extra spaces to the left) and left justification (single spaces, padding at the end).
2.  **Handle Edge Cases Explicitly:** Discuss how you'll handle lines with a single word and the very last line. These are common sources of bugs.
3.  **Trace with Examples:** Walk through a small example manually to demonstrate your understanding of space distribution, especially with remainders.
4.  **Discuss `StringBuilder`:** Mention why `StringBuilder` is preferred over string concatenation for performance in loops.
5.  **Break Down the Logic:** Explain the two main parts: grouping words into lines and then formatting each line.

## Revision Checklist
- [ ] Understand the problem statement and justification rules.
- [ ] Implement logic to group words into lines greedily.
- [ ] Implement full justification for intermediate lines.
- [ ] Implement left justification for the last line.
- [ ] Handle single-word lines correctly.
- [ ] Use `StringBuilder` for efficient string manipulation.
- [ ] Test with various inputs, including empty arrays, single words, and `maxWidth` constraints.

## Similar Problems
- Word Break
- Palindrome Partitioning
- Group Anagrams

## Tags
`Array` `String` `Greedy`
