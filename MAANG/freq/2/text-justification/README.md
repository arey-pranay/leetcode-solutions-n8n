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
The core idea is to process words greedily, forming lines as long as they fit within `maxWidth`. Once a line is full, we need to distribute spaces to justify it. The key challenge is how to distribute these spaces. For lines that are not the last one, we want to distribute spaces as evenly as possible between words. The last line is a special case, always left-justified.

## Algorithm
1. Initialize an empty list `ans` to store the justified lines.
2. Initialize a pointer `wordI` to the current word index, starting at 0.
3. While `wordI` is less than the total number of words:
    a. Start a new line:
        i. Initialize `widthTaken` to 0 and an empty list `currentLineWords` to store words for the current line.
        ii. Iterate through words starting from `wordI`:
            - If adding the current word (plus at least one space) exceeds `maxWidth`:
                - If `currentLineWords` is not empty, fully justify `currentLineWords` and add it to `ans`.
                - Recursively call `startNewLine` with the current word index `i` to process the remaining words.
                - Return.
            - Otherwise, add the current word to `currentLineWords` and update `widthTaken` (word length + 1 for a space).
    b. If the loop finishes (meaning all remaining words fit on the current line):
        i. Left-justify `currentLineWords` and add it to `ans`.
        ii. Return.

4. Helper function `fullJustify(List<String> words)`:
    a. Calculate the total length of all words in the list.
    b. Calculate the total number of spaces needed: `spaces = maxWidth - totalLen`.
    c. If there's only one word:
        i. Append the word to a `StringBuilder`.
        ii. Append `spaces` number of spaces.
        iii. Return the `StringBuilder`'s string.
    d. Calculate the base number of spaces to put between each pair of words: `spacePerWord = spaces / (count - 1)`.
    e. Calculate the number of extra spaces to distribute: `extraSpaces = spaces % (count - 1)`.
    f. Iterate through the words:
        i. Append the current word.
        ii. Append `spacePerWord` spaces.
        iii. If `extraSpaces > 0`, append one extra space and decrement `extraSpaces`.
    g. Append the last word.
    h. Return the `StringBuilder`'s string.

5. Helper function `leftJustify(List<String> words)`:
    a. Initialize a `StringBuilder`.
    b. Append words, separated by a single space, until the second-to-last word.
    c. Append the last word.
    d. Append remaining spaces to fill up to `maxWidth`.
    e. Return the `StringBuilder`'s string.

## Concept to Remember
*   **Greedy Approach:** Making locally optimal choices (filling lines as much as possible) to achieve a global optimum (correctly formatted text).
*   **String Manipulation & Space Distribution:** Carefully calculating and distributing spaces to meet justification requirements.
*   **Edge Case Handling:** Differentiating between the last line and intermediate lines, and handling lines with a single word.

## Common Mistakes
*   **Incorrect Space Calculation:** Miscalculating the number of spaces needed or how to distribute them, especially with the modulo operator.
*   **Off-by-One Errors:** Errors in loop bounds or when calculating the number of words versus the number of gaps between words.
*   **Handling the Last Line:** Forgetting to left-justify the last line or applying full justification logic to it.
*   **Single Word Lines:** Not correctly handling lines that contain only one word, which should be left-justified.
*   **Overlapping Word/Space Counts:** Incorrectly accounting for the length of words and the spaces between them when determining if a word fits on a line.

## Complexity Analysis
*   **Time:** O(N), where N is the total number of characters in all words. Each character is processed a constant number of times (when building lines, calculating lengths, and appending spaces).
*   **Space:** O(N) in the worst case, to store the resulting justified lines. The intermediate `List<String>` for `currentLineWords` will also contribute, but its size is bounded by the number of words that can fit on a line, which is related to N.

## Commented Code
```java
class Solution {
    // List to store the final justified lines.
    List<String> ans = new ArrayList<>();
    // Maximum width allowed for each line.
    int maxW;

    // Main function to initiate the text justification process.
    public List<String> fullJustify(String[] words, int maxWidth) {
      // Set the maximum width for this instance.
      maxW = maxWidth;
      // Start the process of building lines from the first word (index 0).
      startNewLine(0, words);
      // Return the list of justified lines.
      return ans;
    }

    // Recursive function to build lines greedily.
    public void startNewLine(int wordI, String[] words){
        // Variable to keep track of the width taken by words and spaces on the current line.
        int widthTaken = 0;
        // List to store words that will form the current line.
        List<String> list  = new ArrayList<>();

        // Iterate through words starting from the current word index 'wordI'.
        for(int i=wordI; i<words.length;i++){
            String word = words[i];
            int wordWidth = word.length();

            // Check if adding the current word (plus at least one space) exceeds the maximum width.
            // The '+1' accounts for the minimum space required between words.
            if((widthTaken + wordWidth) > maxW){
                // If the current line has words, fully justify them and add to the answer.
                if (!list.isEmpty()) {
                    ans.add(fullJustify(list));
                }
                // Recursively call startNewLine for the remaining words starting from the current word 'i'.
                startNewLine(i,words);
                // Return as this line has been processed and the rest are handled by the recursive call.
                return;
            }
            // Add the current word to the list for this line.
            list.add(word);
            // Update the width taken: current word length + 1 for a space.
            widthTaken += wordWidth + 1;
        }

        // If the loop finishes, it means all remaining words fit on the current line.
        // Left-justify these words and add them as the last line.
        ans.add(leftJustify(list));
        // Return as this is the end of processing.
        return;
    }

    // Function to fully justify a list of words for an intermediate line.
    public String fullJustify(List<String> words){
        // Number of words in the current line.
        int count = words.size();
        // StringBuilder to construct the justified line.
        StringBuilder sb = new StringBuilder("");

        // Calculate the total length of all words in the list.
        int totalLen = 0;
        for(String word : words) totalLen+=word.length();

        // Calculate the total number of spaces to be distributed.
        int spaces = maxW - totalLen;

        // Special case: if there's only one word on the line.
        if(count == 1){
            // Append the single word.
            sb.append(words.get(0)); // Corrected index to 0 for the single word
            // Append the remaining spaces to fill up to maxWidth.
            sb.append(" ".repeat(spaces));
            // Return the left-justified single word line.
            return sb.toString();
        }

        // Calculate the base number of spaces to put between each pair of words.
        // There are (count - 1) gaps between 'count' words.
        int spacePerWord = spaces / (count - 1);
        // Calculate the number of extra spaces that need to be distributed one by one.
        int extraSpaces = spaces % (count - 1);

        // Iterate through the words to build the justified line.
        for(int i = 0; i < count - 1; i++){
            // Append the current word.
            sb.append(words.get(i));
            // Append the base number of spaces.
            sb.append(" ".repeat(spacePerWord));
            // If there are extra spaces to distribute, add one.
            if(extraSpaces > 0){
                sb.append(" ");
                extraSpaces--;
            }
        }

        // Append the last word.
        sb.append(words.get(count - 1));
        // Return the fully justified line.
        return sb.toString();
    }

    // Function to left-justify the last line or a line with a single word.
    public String leftJustify(List<String> words){
        // StringBuilder to construct the left-justified line.
        StringBuilder sb = new StringBuilder("");
        // Append all words except the last one, separated by a single space.
        for(int i = 0; i < words.size() - 1; i++){
            sb.append(words.get(i));
            sb.append(" ");
        }

        // Append the last word.
        sb.append(words.get(words.size() - 1));
        // Append the remaining spaces to fill up to maxWidth.
        sb.append(" ".repeat(maxW - sb.length()));

        // Return the left-justified line.
        return sb.toString();
    }
}
```

## Interview Tips
1.  **Clarify Edge Cases:** Ask about how to handle empty input arrays, `maxWidth` being very small, or words longer than `maxWidth`. (Though the problem constraints usually prevent the last one).
2.  **Explain Space Distribution:** Clearly articulate how you'll calculate and distribute spaces for full justification, emphasizing the modulo and division operations.
3.  **Handle the Last Line:** Explicitly mention that the last line is treated differently (left-justified) and explain why.
4.  **Walk Through an Example:** Use a small example to trace your algorithm's execution, showing how lines are formed and spaces are distributed.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the greedy strategy for forming lines.
- [ ] Implement the logic for distributing spaces for full justification.
- [ ] Correctly handle the last line as left-justified.
- [ ] Handle the edge case of a line with only one word.
- [ ] Test with various inputs, including those with many short words, few long words, and different `maxWidth` values.

## Similar Problems
*   Line Reflection
*   Word Break II
*   Palindrome Partitioning

## Tags
`Array` `String` `Greedy`
