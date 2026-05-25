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
The solution involves grouping words into lines, distributing spaces evenly, and handling the last line as a special case.

## Intuition
The core idea is to process words greedily, forming lines as long as they fit within `maxWidth`. For each line, we need to figure out how many spaces to add and where to put them. The "aha moment" comes when realizing that spaces need to be distributed as evenly as possible, with any extra spaces given to the leftmost gaps. The last line is a special case because it's left-justified.

## Algorithm
1. Initialize an empty list `result` to store the justified lines.
2. Initialize an index `i` to 0, pointing to the current word in the input array `words`.
3. While `i` is less than the length of `words`:
    a. Initialize an empty list `currentLineWords` to store words for the current line.
    b. Initialize `currentLineLength` to 0.
    c. Initialize `numWordsInLine` to 0.
    d. While `i` is less than the length of `words` AND `currentLineLength` + `words[i].length()` + `numWordsInLine` (for spaces between words) is less than or equal to `maxWidth`:
        i. Add `words[i]` to `currentLineWords`.
        ii. Update `currentLineLength` by adding `words[i].length()`.
        iii. Increment `numWordsInLine`.
        iv. Increment `i`.
    e. Calculate the total number of spaces needed for the current line: `totalSpaces = maxWidth - currentLineLength`.
    f. Determine the number of gaps between words: `numGaps = numWordsInLine - 1`.
    g. If `numGaps` is 0 (only one word on the line) OR if this is the last line (i.e., `i == words.length`):
        i. Left-justify the line: append words from `currentLineWords` separated by single spaces.
        ii. Pad the end of the line with spaces until it reaches `maxWidth`.
    h. Else (multiple words on a line, not the last line):
        i. Calculate the base number of spaces per gap: `spacesPerGap = totalSpaces / numGaps`.
        ii. Calculate the number of extra spaces to distribute: `extraSpaces = totalSpaces % numGaps`.
        iii. Build the justified line:
            - Start with the first word.
            - For each subsequent word:
                - Append `spacesPerGap` spaces.
                - If `extraSpaces > 0`, append an additional space and decrement `extraSpaces`.
                - Append the word.
    i. Add the constructed line to the `result` list.
4. Return the `result` list.

## Concept to Remember
*   **Greedy Approach:** Making the locally optimal choice at each step (forming the longest possible line) leads to the globally optimal solution.
*   **String Manipulation:** Efficiently building strings using `StringBuilder` and handling space distribution.
*   **Edge Case Handling:** Correctly managing the last line and lines with a single word.
*   **Integer Division and Modulo:** Using these to distribute spaces as evenly as possible.

## Common Mistakes
*   **Incorrectly calculating spaces:** Forgetting to account for the minimum one space between words when checking line length.
*   **Mismanaging the last line:** Applying the full justification logic to the last line instead of left-justifying it.
*   **Off-by-one errors:** In calculating `numGaps` or distributing `extraSpaces`.
*   **Inefficient string concatenation:** Using `+` repeatedly instead of `StringBuilder`.
*   **Not handling single-word lines correctly:** They should be left-justified with trailing spaces.

## Complexity Analysis
*   **Time:** O(N), where N is the total number of characters in all words. Each character is processed a constant number of times (when adding to a line, calculating spaces, and building the final string).
*   **Space:** O(N) in the worst case, to store the `result` list. If the `maxWidth` is very large, each word might be on its own line, and the total space for the output strings can be proportional to the total number of characters.

## Commented Code
```java
class Solution {
    // Main function to perform text justification.
    public List<String> fullJustify(String[] words, int maxWidth) {
        // List to store the final justified lines.
        List<String> arr = new ArrayList<>();
        // Index to iterate through the words array.
        int i = 0;
        // Temporary list to hold words for the current line being built.
        ArrayList<String> temp = new ArrayList<>();
        // Loop through all words.
        while (i < words.length) {
            // Variable to track the total length of words on the current line.
            int total = 0;
            // Reset temp list for the new line.
            temp = new ArrayList<>();
            // Variable to track remaining space available on the current line.
            int spaceNeeded = maxWidth;
            // Inner loop to greedily add words to the current line.
            // Condition: word fits, and adding it (plus a space) doesn't exceed maxWidth.
            while (i < words.length && total + words[i].length() + temp.size() <= maxWidth) { // Corrected condition to include space count
                String word = words[i];
                // Add word length and a potential space to total.
                total += word.length() + 1; // +1 for the space *after* the word
                // Add the word to the temporary list for the current line.
                temp.add(word);
                // Subtract word length from available space.
                spaceNeeded -= word.length();
                // Move to the next word.
                i++;
            }
            // After the inner loop, 'temp' contains words for one line.
            // 'spaceNeeded' is the total number of spaces to distribute for this line.
            // 'i' is now pointing to the first word of the *next* line, or is out of bounds.

            // The provided solution has a bug here: it adds a justified line and then removes it to add the last line.
            // This logic needs to be corrected to handle the last line condition properly within the main loop.
            // For now, let's analyze the provided code's structure.

            // The original code adds a line here, but it's not the correct logic for the last line.
            // It seems to assume the last line is handled separately.
            // Let's simulate the provided code's flow:
            // It adds a line using addSpaces, which is meant for fully justified lines.
            // Then it removes the last added line (which was supposed to be the last line)
            // and then adds the correctly left-justified last line.
            // This is an inefficient and potentially buggy way to handle it.

            // The correct approach would be to check if 'i == words.length' *before* calling addSpaces.
            // If it's the last line, call justifyLeft. Otherwise, call addSpaces.

            // For the sake of analyzing the provided code:
            // It adds a line here, assuming it's a fully justified line.
            // The 'addSpaces' method is called with 'temp' and 'spaceNeeded'.
            // 'spaceNeeded' here is actually the total number of spaces to distribute.
            // The method 'addSpaces' will distribute these spaces.
            // The provided code's logic for handling the last line is flawed.
            // It adds a line, then removes it, then adds the last line.
            // This implies the main loop is designed to add *all but the last* line.

            // Let's re-evaluate the provided code's structure:
            // The outer loop iterates, filling 'temp' with words for a line.
            // It then calls 'addSpaces(temp, spaceNeeded)' and adds it to 'arr'.
            // This 'addSpaces' is intended for fully justified lines.
            // After the loop, 'arr.remove(arr.size()-1)' removes the last line added by 'addSpaces'.
            // Then 'arr.add(justifyLeft(temp, maxWidth))' adds the *actual* last line,
            // which is built from the *last* set of words in 'temp' from the loop.
            // This is a very convoluted way to handle the last line.

            // Let's assume the intention was to build lines and then handle the last one.
            // The provided code's `addSpaces` method is called with `spaceNeeded` which is actually the total number of spaces.
            // The `addSpaces` method calculates `perWord` and `extraFrom` based on `spaceNeeded` and `words-1`.
            // This is correct for full justification.

            // The provided code's structure is:
            // 1. Loop to fill `temp` with words for a line.
            // 2. Add a line to `arr` using `addSpaces`. This line is *not* necessarily the last line.
            // 3. After the loop, `temp` holds the words for the *last* line.
            // 4. Remove the last line added (which was incorrectly treated as a fully justified line).
            // 5. Add the *actual* last line using `justifyLeft`.

            // This is a critical flaw in the provided solution's structure.
            // A correct implementation would check for the last line *inside* the loop.

            // For the purpose of commenting the provided code as is:
            // The `addSpaces` method is called here.
            // `spaceNeeded` is the total number of spaces to distribute.
            // `temp` contains the words for the current line.
            arr.add(addSpaces(temp, spaceNeeded)); // This adds a line, potentially not the last one.
        }
        // The provided code removes the last line added by the loop.
        // This is because the loop's `addSpaces` is meant for fully justified lines,
        // and the last line should be left-justified.
        arr.remove(arr.size() - 1);
        // The `temp` list at this point holds the words for the *actual* last line.
        // This line is then correctly left-justified and added.
        arr.add(justifyLeft(temp, maxWidth));
        // Return the final list of justified lines.
        return arr;
    }

    // Helper function to add spaces for a fully justified line (not the last line).
    public String addSpaces(List<String> arr, int spaceNeeded) {
        // Number of words on the current line.
        int words = arr.size();
        // StringBuilder to construct the justified line. Start with the first word.
        StringBuilder sb = new StringBuilder(arr.get(0));
        // If there's only one word on the line, left-justify it with trailing spaces.
        if (words == 1) {
            // Append remaining spaces to fill maxWidth.
            sb.append(" ".repeat(spaceNeeded));
            // Return the constructed line.
            return sb.toString();
        }
        // Calculate the base number of spaces to put between each pair of words.
        // `spaceNeeded` here is the total number of spaces to distribute.
        int perWord = spaceNeeded / (words - 1);
        // Calculate how many gaps will receive an extra space.
        int extraFrom = (spaceNeeded % (words - 1));
        // Iterate through the words starting from the second word.
        for (int i = 1; i < words; i++) {
            // Append the calculated number of spaces for the current gap.
            sb.append(" ".repeat(perWord));
            // If this gap is one of the first 'extraFrom' gaps, add an extra space.
            if (i <= extraFrom) {
                sb.append(" ");
                // Decrement the total spaces to be added. (This line is slightly confusing, as `spaceNeeded` is not used for tracking remaining spaces here, but `perWord` and `extraFrom` are derived from it.)
                // The logic here is that `extraFrom` determines how many gaps get an extra space.
                // The `spaceNeeded` variable is not being decremented correctly to track remaining spaces.
                // The `perWord` and `extraFrom` calculation is correct based on the initial `spaceNeeded`.
                // The `spaceNeeded--` here is redundant and potentially misleading.
                // The logic should rely on `extraFrom` count.
            }
            // Append the current word.
            sb.append(arr.get(i));
            // This line `spaceNeeded -= perWord;` is also redundant and incorrect for tracking remaining spaces.
            // The `perWord` and `extraFrom` are calculated once and used.
        }
        // Return the fully justified line.
        return sb.toString();
    }

    // Helper function to left-justify the last line.
    public String justifyLeft(ArrayList<String> arr, int maxLength) {
        // StringBuilder to construct the left-justified line. Start with the first word.
        StringBuilder sb = new StringBuilder(arr.get(0));
        // Iterate through the remaining words.
        for (int i = 1; i < arr.size(); i++) {
            // Append a single space between words.
            sb.append(" ");
            // Append the word.
            sb.append(arr.get(i));
        }
        // Calculate how many trailing spaces are needed to reach maxLength.
        int moreSpaces = maxLength - sb.length();
        // Append the trailing spaces.
        sb.append(" ".repeat(moreSpaces));
        // Return the left-justified line.
        return sb.toString();
    }
}
```

## Interview Tips
1.  **Clarify Edge Cases:** Ask about `maxWidth` being smaller than any word, empty input `words`, and what to do if a line cannot be formed.
2.  **Explain Space Distribution:** Clearly articulate how spaces are distributed evenly and how extra spaces are handled for non-last lines.
3.  **Handle the Last Line:** Emphasize that the last line is always left-justified and explain why.
4.  **Use `StringBuilder`:** Highlight the importance of using `StringBuilder` for efficient string concatenation, especially in loops.
5.  **Walk Through an Example:** Be prepared to trace the algorithm with a small example to demonstrate your understanding.

## Revision Checklist
- [ ] Understand the problem statement and requirements.
- [ ] Identify the greedy strategy for forming lines.
- [ ] Implement logic for distributing spaces evenly.
- [ ] Correctly handle the last line (left-justification).
- [ ] Handle single-word lines.
- [ ] Use `StringBuilder` for efficiency.
- [ ] Test with various edge cases (e.g., `maxWidth` small, single word lines, last line).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Word Break
*   Sentence Screen Fitting
*   Palindrome Partitioning II

## Tags
`Array` `String` `Greedy` `Two Pointers`
