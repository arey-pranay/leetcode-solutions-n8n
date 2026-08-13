# Integer To English Words

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Math` `String` `Recursion`  
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
The core idea is that English number names follow a pattern based on groups of three digits. For example, 123,456,789 can be thought of as "One Hundred Twenty-Three Million, Four Hundred Fifty-Six Thousand, Seven Hundred Eighty-Nine". This suggests a strategy of processing the number in chunks of three digits, from right to left, and then combining these chunks with their corresponding scale words.

## Algorithm
1.  **Handle Zero:** If the input number is 0, return "Zero".
2.  **Define Number and Tens Arrays:** Create arrays to store the English words for digits 0-19 (`numbers`) and for tens (Twenty, Thirty, etc.) (`tens`).
3.  **Process in Groups of Three:**
    *   Iterate while the number is not zero.
    *   In each iteration, extract the last three digits using the modulo operator (`num % 1000`).
    *   Convert these three digits into English words using a helper function (`threeDigit`).
    *   Store the converted group and its scale (Thousand, Million, Billion) in a list.
    *   Divide the number by 1000 (`num /= 1000`) to process the next group.
    *   Increment an index to keep track of the scale (0 for no scale, 1 for Thousand, 2 for Million, 3 for Billion).
4.  **Assemble the Result:**
    *   Iterate through the list of converted groups in reverse order (from largest scale to smallest).
    *   Append each group's English word representation to a `StringBuilder`.
5.  **Trim and Return:** Trim any leading/trailing whitespace from the final string and return it.

5.  **Helper Function `threeDigit(index, num)`:**
    *   Takes the scale `index` and the three-digit `num` as input.
    *   Initializes a `StringBuilder` for the current group.
    *   **Convert Hundreds:** If `num` has a hundreds digit, append its word and "Hundred ".
    *   **Convert Remaining Two Digits:**
        *   Extract the last two digits (`curr = num % 100`).
        *   If `curr` is less than 20, use the `numbers` array directly.
        *   Otherwise, use the `tens` array for the tens digit and the `numbers` array for the units digit.
    *   **Append Scale:** Determine the appropriate scale word ("Thousand ", "Million ", "Billion ") based on the `index` and append it, unless it's the first group (index 0) or the number is empty.
    *   Return the `StringBuilder`'s string.

## Concept to Remember
*   **Modular Arithmetic:** Used extensively to extract digits and groups of digits from the integer.
*   **String Manipulation and Building:** Efficiently constructing the final English string using `StringBuilder`.
*   **Handling Edge Cases:** Special care for 0, numbers less than 20, and the correct placement of scale words.
*   **Base-10 Representation:** Understanding how numbers are structured in groups of thousands.

## Common Mistakes
*   **Incorrectly handling numbers less than 20:** Forgetting that 10-19 have unique names and are not formed by "Ten" + digit.
*   **Off-by-one errors with scale words:** Misplacing or omitting "Thousand", "Million", "Billion" for the correct groups.
*   **Not trimming whitespace:** Leaving extra spaces at the beginning or end of the final string.
*   **Integer overflow for very large numbers:** While LeetCode constraints usually prevent this, it's good to be aware of potential limits if not specified.
*   **Incorrectly processing the hundreds place:** Forgetting to append "Hundred" or handling it separately from the tens/units.

## Complexity Analysis
*   **Time:** O(1) - The input integer is bounded (e.g., up to 2^31 - 1). The number of digits is fixed, and we process them in fixed-size chunks (3 digits). The number of groups (Thousand, Million, Billion) is also limited. Therefore, the operations are constant time relative to the maximum possible input value.
*   **Space:** O(1) - The space used by the `numbers` and `tens` arrays is constant. The `ArrayList` to store groups and the `StringBuilder` will store a number of elements proportional to the number of digits in the input, which is bounded by the maximum integer value. Thus, space complexity is constant.

## Commented Code
```java
class Solution {
    // Array to store English words for numbers 0-19.
    String[] numbers = new String[] { "", "One ", "Two ", "Three ", "Four ", "Five ", "Six ",
            "Seven ", "Eight ", "Nine ", "Ten ", "Eleven ", "Twelve ", "Thirteen ", "Fourteen ",
            "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
    // Array to store English words for tens (Twenty, Thirty, etc.).
    String[] tens = new String[] {"", "", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ",
            "Eighty ", "Ninety " };

    // Main function to convert an integer to English words.
    public String numberToWords(int num) {
        // If the number is 0, return "Zero" directly.
        if(num==0) return "Zero";
        // ArrayList to store the English word representation of each 3-digit group.
        ArrayList<String> groups = new ArrayList<>();
        // Index to keep track of the scale (Thousand, Million, Billion).
        int index = 0;
        // Loop until the number becomes 0, processing it in chunks of 1000.
        while (num != 0) {
            // Get the last three digits of the number.
            int curr = num % 1000;
            // Convert the current 3-digit group to words and add it to the groups list.
            groups.add(threeDigit(index, curr));
            // Divide the number by 1000 to process the next group.
            num /= 1000;
            // Increment the index for the next scale.
            index++;
        }
        // StringBuilder to efficiently build the final English word string.
        StringBuilder ans = new StringBuilder();
        // Iterate through the groups in reverse order (from largest scale to smallest).
        for (int i = groups.size() - 1; i >= 0; i--)
            // Append each group's word representation to the StringBuilder.
            ans.append(groups.get(i));
        // Trim any leading or trailing whitespace and return the final string.
        return ans.toString().trim();
    }

    // Helper function to convert a 3-digit number into English words.
    public String threeDigit(int index, int num) {
        // StringBuilder to build the English words for the current 3-digit group.
        StringBuilder sb = new StringBuilder();
        // Extract the last two digits (tens and units).
        int curr = num % 100;
        // Extract the hundreds digit.
        num /= 100;
        // If there's a hundreds digit, append its word and "Hundred ".
        if (num > 0) {
            sb.append(numbers[num]);
            sb.append("Hundred ");
        }
        // Handle the last two digits (tens and units).
        if (curr < 20)
            // If less than 20, use the 'numbers' array directly (e.g., "Ten", "Eleven").
            sb.append(numbers[curr]);
        else {
            // If 20 or greater, use the 'tens' array for the tens digit and 'numbers' for the units digit.
            sb.append(tens[curr / 10]); // Append the tens word (e.g., "Twenty ").
            sb.append(numbers[curr % 10]); // Append the units word (e.g., "Three ").
        }
        // Determine the appropriate scale word based on the index.
        // 'extra' will be empty if the group is empty or it's the first group (no scale).
        // Otherwise, it appends "Thousand ", "Million ", or "Billion ".
        String extra = sb.isEmpty() || index == 0 ? ""
                : index == 1 ? "Thousand " : index == 2 ? "Million " : index == 3 ? "Billion " : "Trillion "; // Added Trillion for completeness, though typically not needed for int.
        // Append the scale word to the StringBuilder.
        sb.append(extra);
        // Return the English word representation of the 3-digit group.
        return sb.toString();
    }
}
```

## Interview Tips
*   **Break it Down:** Emphasize that the problem can be solved by breaking the number into 3-digit chunks. This shows structured thinking.
*   **Helper Function:** Explain the utility of a `threeDigit` helper function to keep the main logic clean and manageable.
*   **Edge Cases:** Be prepared to discuss how you handle 0, numbers less than 20, and the absence of scale words for the first group.
*   **Clarity of Output:** Mention the importance of trimming whitespace to ensure the output matches expected formats.
*   **Scalability (Conceptual):** While the solution is O(1) for `int`, briefly mention how you might extend it for larger number types (e.g., `long` or arbitrary precision) if asked, by potentially adding more scale words.

## Revision Checklist
- [ ] Understand the problem: convert integer to English words.
- [ ] Identify the pattern: groups of three digits with scale words (Thousand, Million, Billion).
- [ ] Implement helper for 3-digit conversion.
- [ ] Handle numbers < 20 separately.
- [ ] Handle tens correctly (Twenty, Thirty, etc.).
- [ ] Correctly append scale words based on group index.
- [ ] Process number in chunks of 1000.
- [ ] Assemble final string in correct order (largest scale first).
- [ ] Handle the edge case of input 0.
- [ ] Ensure no leading/trailing whitespace in the output.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Roman to Integer
*   Integer to Roman
*   String to Integer (atoi)
*   Number of Steps to Reduce a Number in Binary Representation to One

## Tags
`Math` `String` `Recursion`
