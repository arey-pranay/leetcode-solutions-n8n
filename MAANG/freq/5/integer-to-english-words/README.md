# Integer To English Words

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `String` `Math` `Recursion`  
**Time:** O(1)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    String[] numbers = new String[] { "", "One ", "Two ", "Three ", "Four ", "Five ", "Six ",
            "Seven ", "Eight ", "Nine ", "Ten ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ",
            "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
    String[] tens = new String[] {"", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ",
            "Eighty ", "Ninety " };

    public String numberToWords(int num) {
        if(num==0) return "Zero";
        ArrayList<String> groups = new ArrayList<>();
        int index = 0;
        while (num != 0) {
            int curr = num % 1000;
            groups.add(threeDigit(index, curr));
            num /= 1000;
            index++;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = groups.size() - 1; i >= 0; i--)
            ans.append(groups.get(i));
        return ans.toString().trim();
    }

    public String threeDigit(int index, int num) {
        StringBuilder sb = new StringBuilder();
        int curr = num % 100;
        num /= 100;
        if (num > 0) {
            sb.append(numbers[num]);
            sb.append("Hundred ");
        }
        if (curr < 20)
            sb.append(numbers[curr]);
        else {
            sb.append(tens[curr / 10]);
            sb.append(numbers[curr % 10]);
        }
        String extra = sb.isEmpty() || index == 0 ? ""
                : index == 1 ? "Thousand " : index == 2 ? "Million " : index == 3 ? "Billion " : "Trillion ";
        sb.append(extra);
        return sb.toString();
    }
}
```

---

---
## Quick Revision
This problem asks to convert a non-negative integer into its English word representation.
The solution involves breaking down the number into groups of three digits and converting each group, then appending the appropriate scale (Thousand, Million, Billion).

## Intuition
The core idea is that English number names follow a pattern based on groups of three digits. For example, 123,456,789 can be thought of as "One Hundred Twenty-Three Million, Four Hundred Fifty-Six Thousand, Seven Hundred Eighty-Nine". This suggests a strategy of processing the number in chunks of three digits, from right to left, and applying the correct magnitude suffix (Thousand, Million, Billion) to each chunk.

## Algorithm
1.  **Handle Zero:** If the input number `num` is 0, return "Zero".
2.  **Initialize Data Structures:**
    *   Create an array `numbers` to store English words for digits 0-19.
    *   Create an array `tens` to store English words for tens (Twenty, Thirty, etc.).
    *   Create an `ArrayList` called `groups` to store the English word representation of each 3-digit chunk.
3.  **Process in Chunks of Three:**
    *   Iterate while `num` is not 0.
    *   In each iteration, extract the last three digits: `curr = num % 1000`.
    *   Convert this 3-digit chunk to words using a helper function `threeDigit(index, curr)` and add it to the `groups` list. The `index` tracks the magnitude (0 for units, 1 for thousands, 2 for millions, etc.).
    *   Divide `num` by 1000: `num /= 1000`.
    *   Increment the `index`.
4.  **Assemble the Final String:**
    *   Create a `StringBuilder` called `ans`.
    *   Iterate through the `groups` list in reverse order (from largest magnitude to smallest).
    *   Append each group's word representation to `ans`.
5.  **Return Result:** Return the `ans` string after trimming any leading/trailing whitespace.

**Helper Function `threeDigit(index, num)`:**
1.  **Initialize `sb`:** Create a `StringBuilder` `sb` for the current 3-digit chunk.
2.  **Process Hundreds:**
    *   Extract the hundreds digit: `hundredsDigit = num / 100`.
    *   If `hundredsDigit > 0`, append `numbers[hundredsDigit]` and "Hundred " to `sb`.
    *   Update `num`: `num %= 100`.
3.  **Process Remaining Two Digits:**
    *   If `num < 20`, append `numbers[num]` to `sb`.
    *   Else (if `num >= 20`):
        *   Append `tens[num / 10]` to `sb`.
        *   Append `numbers[num % 10]` to `sb`.
4.  **Append Magnitude Suffix:**
    *   Determine the appropriate suffix ("Thousand ", "Million ", "Billion ") based on the `index`. If `sb` is empty or `index` is 0, no suffix is needed.
    *   Append the suffix to `sb`.
5.  **Return:** Return the string representation from `sb`.

## Concept to Remember
*   **Modular Arithmetic:** Used extensively to extract digits and groups of digits (e.g., `num % 1000`, `num % 100`).
*   **String Manipulation:** Building the final English string by concatenating smaller parts and handling spaces.
*   **Base-10 Representation:** Understanding how numbers are structured in base-10 and how to break them down into hundreds, tens, and units.
*   **Handling Edge Cases:** Special cases like 0, numbers less than 20, and the absence of certain place values (e.g., no hundreds in a 3-digit number).

## Common Mistakes
*   **Incorrectly handling numbers less than 20:** Forgetting that numbers 10-19 have unique names and are not formed by combining "Ten" with a digit.
*   **Missing or incorrect magnitude suffixes:** Forgetting to append "Thousand", "Million", "Billion" or appending them incorrectly.
*   **Off-by-one errors with array indices:** Using incorrect indices for `numbers` or `tens` arrays.
*   **Trailing spaces:** Not trimming the final string, leading to extra spaces at the end.
*   **Incorrectly processing the hundreds place:** Not handling the "Hundred" suffix correctly or forgetting to update the remaining number after processing hundreds.

## Complexity Analysis
*   **Time:** O(1) - The input `num` is an integer, which has a fixed maximum value. The number of digits is bounded, and thus the number of 3-digit chunks and the operations within `threeDigit` are constant. The maximum number of digits for a 32-bit integer is around 10.
*   **Space:** O(1) - The space used by the `numbers` and `tens` arrays is constant. The `groups` `ArrayList` and the `StringBuilder` will store a number of strings proportional to the number of digits in the input integer. Since the input integer is bounded, this space is also considered constant.

## Commented Code
```java
class Solution {
    // Array to store English words for numbers 0 through 19.
    String[] numbers = new String[] { "", "One ", "Two ", "Three ", "Four ", "Five ", "Six ",
            "Seven ", "Eight ", "Nine ", "Ten ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ",
            "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
    // Array to store English words for tens (Twenty, Thirty, etc.).
    String[] tens = new String[] {"", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ",
            "Eighty ", "Ninety " };

    // Main function to convert an integer to its English word representation.
    public String numberToWords(int num) {
        // If the number is 0, return "Zero" directly.
        if(num==0) return "Zero";
        // ArrayList to store the English word representation of each 3-digit group.
        ArrayList<String> groups = new ArrayList<>();
        // Index to keep track of the magnitude (0 for units, 1 for thousands, 2 for millions, etc.).
        int index = 0;
        // Loop until the number becomes 0, processing it in chunks of 1000.
        while (num != 0) {
            // Get the last three digits of the number.
            int curr = num % 1000;
            // Convert the current 3-digit chunk to words and add it to the groups list.
            groups.add(threeDigit(index, curr));
            // Remove the last three digits from the number.
            num /= 1000;
            // Increment the index for the next magnitude.
            index++;
        }
        // StringBuilder to construct the final English word string.
        StringBuilder ans = new StringBuilder();
        // Iterate through the groups in reverse order (from largest magnitude to smallest).
        for (int i = groups.size() - 1; i >= 0; i--)
            // Append the word representation of each group to the answer.
            ans.append(groups.get(i));
        // Return the final string, trimming any leading or trailing whitespace.
        return ans.toString().trim();
    }

    // Helper function to convert a 3-digit number (or less) into English words.
    public String threeDigit(int index, int num) {
        // StringBuilder to build the word representation for the current 3-digit chunk.
        StringBuilder sb = new StringBuilder();
        // Extract the last two digits (tens and units).
        int curr = num % 100;
        // Extract the hundreds digit.
        num /= 100;
        // If there's a hundreds digit, append its word representation and "Hundred ".
        if (num > 0) {
            sb.append(numbers[num]); // Append the word for the hundreds digit.
            sb.append("Hundred "); // Append "Hundred ".
        }
        // Process the remaining two digits (tens and units).
        if (curr < 20)
            // If the number is less than 20, use the 'numbers' array directly (handles 0-19).
            sb.append(numbers[curr]);
        else {
            // If the number is 20 or greater, process tens and units separately.
            sb.append(tens[curr / 10]); // Append the word for the tens place (e.g., "Twenty ").
            sb.append(numbers[curr % 10]); // Append the word for the units digit.
        }
        // Determine the appropriate magnitude suffix (Thousand, Million, Billion).
        // 'extra' will be empty if sb is empty (meaning the 3-digit chunk was 0) or if it's the units place (index 0).
        String extra = sb.isEmpty() || index == 0 ? ""
                : index == 1 ? "Thousand " : index == 2 ? "Million " : index == 3 ? "Billion " : "Trillion "; // Note: LeetCode constraints usually don't go beyond Billion.
        // Append the magnitude suffix to the StringBuilder.
        sb.append(extra);
        // Return the complete word representation for this 3-digit chunk.
        return sb.toString();
    }
}
```

## Interview Tips
1.  **Break Down the Problem:** Explain your strategy of dividing the number into 3-digit chunks and handling each chunk separately. This shows structured thinking.
2.  **Handle Edge Cases Explicitly:** Mention how you'd handle 0, numbers less than 20, and the absence of certain place values (like hundreds or tens).
3.  **Discuss Data Structures:** Explain why you chose arrays for number words and a `StringBuilder` for efficient string construction.
4.  **Walk Through an Example:** Use a number like 123456 to illustrate how your algorithm processes it step-by-step, showing the `groups` list and the final assembly.
5.  **Clarify Constraints:** Ask about the maximum possible input value. This helps confirm if you need to handle "Trillion" or larger scales, and it shows you're thinking about practical limits.

## Revision Checklist
- [ ] Understand the problem: Convert integer to English words.
- [ ] Identify the pattern: Grouping by thousands, millions, billions.
- [ ] Implement helper for 3-digit numbers.
- [ ] Implement main logic for chunking and assembly.
- [ ] Handle the edge case of 0.
- [ ] Correctly use arrays for number words (0-19 and tens).
- [ ] Manage magnitude suffixes (Thousand, Million, Billion).
- [ ] Ensure correct string concatenation and trimming.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
*   Roman to Integer
*   Integer to Roman
*   String to Integer (atoi)
*   Number of Steps to Reduce a Number in Binary Representation to One

## Tags
`String` `Math` `Recursion`
