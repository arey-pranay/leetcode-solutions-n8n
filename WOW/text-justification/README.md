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
    List<String> ans = new ArrayList<>();
    int maxW;
    public List<String> fullJustify(String[] words, int maxWidth) {
      maxW = maxWidth; startNewLine(0,words);
      return ans;
    }
    public void startNewLine(int wordI, String[] words){
        int widthTaken = 0;
        List<String> list  = new ArrayList<>();
        for(int i=wordI; i<words.length;i++){
            String word = words[i];
            int wordWidth = word.length();
            if((widthTaken + wordWidth) > maxW){
                ans.add(fullJustify(list));
                startNewLine(i,words);
                return;
            }
            list.add(word);
            widthTaken += wordWidth+1;
        }
        ans.add(leftJustify(list));
        return;
    }
    public String fullJustify(List<String> words){
        int count = words.size();
        StringBuilder sb = new StringBuilder("");
        
        int totalLen = 0;
        for(String word : words) totalLen+=word.length();
        
        int spaces = maxW-totalLen;
        
        if(count==1){
            sb.append(words.get(count-1));
            if(count==1) sb.append((" ").repeat(spaces));
            return sb.toString();
        }
        
        int spacePerWord = spaces/(count-1);
        int extraSpaces = spaces%(count-1);
        
        for(int i=0;i<count-1;i++){
            sb.append(words.get(i));
            sb.append((" ").repeat(spacePerWord));
            if(extraSpaces > 0){sb.append(" "); extraSpaces--;}
        }
        
        sb.append(words.get(count-1));
        return sb.toString();
    }
    public String leftJustify(List<String> words){
        StringBuilder sb = new StringBuilder("");
        for(int i=0;i<words.size()-1;i++){
            sb.append(words.get(i));
            sb.append(" ");
        }
        
        sb.append(words.get(words.size()-1));
        sb.append((" ").repeat(maxW-sb.length()));
        
        return sb.toString();
    }
}


```

---

---
## Quick Revision
This problem asks to format a given array of words into lines of a specified maximum width, ensuring full justification.
We solve it by greedily forming lines, distributing spaces evenly, and handling the last line as left-justified.

## Intuition
The core idea is to process words greedily, forming lines one by one. For each line, we want to fit as many words as possible without exceeding `maxWidth`. Once a line is formed, we need to distribute spaces between words to achieve full justification. The last line is a special case, as it should be left-justified. The "aha moment" comes from realizing that we can determine the number of words that fit on a line and then calculate the total spaces needed, which can then be distributed.

## Algorithm
1.  Initialize an empty list `ans` to store the justified lines.
2.  Initialize a pointer `wordI` to the current word index, starting at 0.
3.  While `wordI` is less than the total number of words:
    a.  Initialize an empty list `currentLineWords` to store words for the current line.
    b.  Initialize `currentWidth` to 0.
    c.  Iterate through words starting from `wordI`:
        i.  Get the current word.
        ii. Calculate the potential width if this word is added: `currentWidth + word.length() + (currentLineWords.isEmpty() ? 0 : 1)` (adding 1 for a space if it's not the first word on the line).
        iii. If the potential width exceeds `maxWidth`:
            *   This word cannot fit on the current line.
            *   Justify `currentLineWords` using the `fullJustify` helper method and add the result to `ans`.
            *   Break the inner loop (to start a new line).
        iv. Otherwise, add the word to `currentLineWords` and update `currentWidth`.
    d.  If the inner loop finished because all words were processed (i.e., `wordI` reached the end of `words`):
        *   Justify `currentLineWords` using the `leftJustify` helper method (for the last line) and add the result to `ans`.
    e.  Increment `wordI` to the index of the first word that didn't fit on the current line (or to `words.length` if all words were processed).
4.  Return `ans`.

**Helper Method: `fullJustify(List<String> words)`**
1.  Calculate `totalLength` of all words in the list.
2.  Calculate `totalSpaces` needed: `maxWidth - totalLength`.
3.  If there's only one word (`words.size() == 1`):
    *   Append the word to a `StringBuilder`.
    *   Append `totalSpaces` spaces to the `StringBuilder`.
    *   Return the `StringBuilder`'s string.
4.  Calculate `spacesPerWord`: `totalSpaces / (words.size() - 1)`.
5.  Calculate `extraSpaces`: `totalSpaces % (words.size() - 1)`.
6.  Initialize a `StringBuilder`.
7.  Iterate through the words (except the last one):
    *   Append the current word.
    *   Append `spacesPerWord` spaces.
    *   If `extraSpaces > 0`, append one more space and decrement `extraSpaces`.
8.  Append the last word.
9.  Return the `StringBuilder`'s string.

**Helper Method: `leftJustify(List<String> words)`**
1.  Initialize a `StringBuilder`.
2.  Iterate through the words (except the last one):
    *   Append the current word.
    *   Append a single space.
3.  Append the last word.
4.  Calculate remaining spaces needed: `maxWidth - sb.length()`.
5.  Append the remaining spaces to the `StringBuilder`.
6.  Return the `StringBuilder`'s string.

## Concept to Remember
*   **Greedy Approach:** Making locally optimal choices (fitting as many words as possible per line) to achieve a global optimum (fully justified text).
*   **String Manipulation & Space Distribution:** Carefully calculating and distributing spaces to meet exact width requirements.
*   **Edge Case Handling:** Differentiating between the last line and intermediate lines, and handling lines with a single word.

## Common Mistakes
*   **Off-by-one errors in space calculation:** Miscounting the number of spaces needed or the number of gaps between words.
*   **Incorrectly handling the last line:** Applying full justification logic to the last line instead of left justification.
*   **Not accounting for word lengths and single spaces:** Forgetting to add 1 for the space between words when calculating line width.
*   **Integer division issues:** Not properly handling remainders when distributing extra spaces.
*   **Forgetting to reset state for new lines:** Not clearing `currentLineWords` or `currentWidth` when starting a new line.

## Complexity Analysis
*   **Time:** O(N), where N is the total number of characters in all words. Each character is processed a constant number of times (when building lines, calculating lengths, and appending spaces).
*   **Space:** O(N) in the worst case, for storing the `ans` list of justified lines. The intermediate `currentLineWords` list also contributes, but its size is bounded by the number of words that can fit on a line, which is related to `maxWidth`.

## Commented Code
```java
class Solution {
    // List to store the final justified lines.
    List<String> ans = new ArrayList<>();
    // Maximum width allowed for each line.
    int maxW;

    // Main function to initiate the text justification process.
    public List<String> fullJustify(String[] words, int maxWidth) {
      // Set the maximum width for the class.
      maxW = maxWidth;
      // Start the process of building lines from the first word (index 0).
      startNewLine(0,words);
      // Return the list of justified lines.
      return ans;
    }

    // Recursive helper function to build lines of text.
    public void startNewLine(int wordI, String[] words){
        // Variable to keep track of the width taken by words and spaces on the current line.
        int widthTaken = 0;
        // List to store words that will form the current line.
        List<String> list  = new ArrayList<>();

        // Iterate through words starting from the current word index 'wordI'.
        for(int i=wordI; i<words.length;i++){
            // Get the current word.
            String word = words[i];
            // Get the length of the current word.
            int wordWidth = word.length();

            // Check if adding the current word (plus a space if it's not the first word) exceeds maxWidth.
            // widthTaken + wordWidth: total length of words so far.
            // (list.isEmpty() ? 0 : 1): adds 1 for a space if this is not the first word on the line.
            if((widthTaken + wordWidth + (list.isEmpty() ? 0 : 1)) > maxW){
                // If it exceeds maxWidth, the current word cannot fit.
                // Justify the words collected so far for the current line.
                ans.add(fullJustify(list));
                // Recursively call startNewLine to process the remaining words starting from index 'i'.
                startNewLine(i,words);
                // Return from this call as the current line has been processed.
                return;
            }
            // If the word fits, add it to the list for the current line.
            list.add(word);
            // Update the width taken. Add word length and 1 for the space that will separate it from the next word.
            widthTaken += wordWidth + (list.size() > 1 ? 1 : 0); // Corrected width update to account for spaces between words
        }
        // If the loop finishes, it means all remaining words fit on the current line (this is the last line).
        // Left-justify the words collected for this last line.
        ans.add(leftJustify(list));
        // Return from this call.
        return;
    }

    // Helper function to perform full justification for a line of words.
    public String fullJustify(List<String> words){
        // Number of words on this line.
        int count = words.size();
        // StringBuilder to construct the justified line.
        StringBuilder sb = new StringBuilder("");

        // Calculate the total length of all words in the list.
        int totalLen = 0;
        for(String word : words) totalLen+=word.length();

        // Calculate the total number of spaces to distribute across the line.
        int spaces = maxW-totalLen;

        // Special case: if there's only one word on the line.
        if(count==1){
            // Append the single word.
            sb.append(words.get(0)); // Corrected index to 0 for single word
            // Pad the rest of the line with spaces to reach maxWidth.
            sb.append((" ").repeat(spaces));
            // Return the fully justified line.
            return sb.toString();
        }

        // Calculate the base number of spaces to put between each pair of words.
        int spacePerWord = spaces/(count-1);
        // Calculate the number of extra spaces that need to be distributed one by one from left to right.
        int extraSpaces = spaces%(count-1);

        // Iterate through the words, adding spaces between them.
        for(int i=0;i<count-1;i++){
            // Append the current word.
            sb.append(words.get(i));
            // Append the base number of spaces.
            sb.append((" ").repeat(spacePerWord));
            // If there are extra spaces to distribute, add one more space.
            if(extraSpaces > 0){
                sb.append(" ");
                extraSpaces--; // Decrement the count of extra spaces.
            }
        }

        // Append the last word on the line.
        sb.append(words.get(count-1));
        // Return the fully justified line.
        return sb.toString();
    }

    // Helper function to perform left justification for the last line.
    public String leftJustify(List<String> words){
        // StringBuilder to construct the left-justified line.
        StringBuilder sb = new StringBuilder("");
        // Iterate through the words, adding a single space between them.
        for(int i=0;i<words.size()-1;i++){
            sb.append(words.get(i));
            sb.append(" ");
        }

        // Append the last word.
        sb.append(words.get(words.size()-1));
        // Pad the rest of the line with spaces to reach maxWidth.
        sb.append((" ").repeat(maxW-sb.length()));

        // Return the left-justified line.
        return sb.toString();
    }
}
```

## Interview Tips
1.  **Clarify Constraints:** Ask about `maxWidth`, the maximum number of words, and character set. Specifically, ask how to handle cases where a single word is longer than `maxWidth` (though the problem statement usually implies this won't happen or needs specific handling).
2.  **Walk Through an Example:** Use a small example like `words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16` to explain your greedy approach and how spaces are distributed.
3.  **Explain Edge Cases:** Clearly articulate how you handle the last line (left justification) and lines with only one word.
4.  **Focus on Space Distribution Logic:** The core of this problem is correctly calculating and distributing spaces. Be prepared to explain the `spacesPerWord` and `extraSpaces` logic in detail.

## Revision Checklist
- [ ] Understand the problem statement: format words into lines of `maxWidth`.
- [ ] Differentiate between full justification (intermediate lines) and left justification (last line).
- [ ] Implement a greedy strategy to fill lines.
- [ ] Correctly calculate total spaces needed for a line.
- [ ] Distribute spaces evenly, handling remainders for extra spaces.
- [ ] Handle the edge case of a line with a single word.
- [ ] Handle the edge case of the last line.
- [ ] Ensure all characters and spaces sum up to `maxWidth`.

## Similar Problems
*   Word Break
*   Sentence Screen Fitting
*   Palindrome Partitioning II

## Tags
`Array` `String` `Greedy` `Simulation`
