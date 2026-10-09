# Longest Duplicate Substring

**Difficulty:** Hard  
**Language:** Javascript  
**Tags:** `String` `Binary Search` `Sliding Window` `Rolling Hash` `Suffix Array` `Hash Function` `Suffix Automaton` `Suffix Tree` `Z Algorithm` `Boyer–Moore String-Search Algorithm`  
**Time:** O(N log N)  
**Space:** O(N)

---

## Solution (javascript)

```javascript
class Solution {
    String S= "";
    int PRIME = 29; // prime no. se multiply krne pr no. unique aane k jyada chances hai, criteria is char value se next prime
    // qki prime ki jb power krte hai to uss value k jyada koi factors nahi hote hai 
    int mod = 1000000007;
    public String longestDupSubstring(String s) {
        S = s;
        int start = 1;
        int end = S.length()-1;
        String ans = "";
        // har length ke liye check krne ki jagah search space half kr skte hai in terms of length. BinarySearch        
        while(start<=end){
            int mid = start+(end-start)/2;
            String duplicate = getDuplicate(mid); // iss length (mid) k duplicate kon kon se hai 
            if(duplicate.isEmpty()){
                end = mid-1; // agr x length ka duplicate nhi hai, to x se bda bhi possible nhi hai, agr wo hota to x bhi hota
            } else {
                ans = duplicate; //     kisi length ki duplicate agr mili hai, to usse chhoti length eliminate kr skte hai
                start = mid+1; // mid length ka answer aaya, ab mid se max length ke beech dhundhna hai kuch
            }
        }
        return ans;
    }
    private String getDuplicate(int length){
        long hash = findHash(S.substring(0,length));
        HashMap<Long,Integer> seen = new HashMap<>();// have we seen this hash before, if yes then at what index?
        seen.put(hash,0);
        long primeLast = 1; // b ki power n-1, for rolling hash formula
        // length = mid
        for(int i=0;i<length-1;i++) primeLast = (primeLast*PRIME)%mod;
        
        for(int i=length;i<S.length();i++){
            hash = nextHash(hash, S.charAt(i-length), S.charAt(i), primeLast);
            int start = i-length+1;
            
            
            // possibility kam hai ki 2 alg alg string k hash same aajaye kyonki isliye to prime liye lekin by chance aajata hai to hum char by char check kr rhe hai ki vo dono pkka same to hai na 
            
            if(seen.containsKey(hash)){// agr hash same aajata hai, then a final verification ki strings actually same hai ya nahi
                int prevStart = seen.get(hash);
                if(S.substring(prevStart,prevStart+length).equals(S.substring(i-length+1,i+1)))
                    return S.substring(i-length+1,i+1);
            }
            seen.put(hash,i-length+1);
        }
        
        return "";
    }
    private long findHash(String s){
        long hash=0,base=1; //base should be greater than char, and should probably be prime
        int n = s.length();
        for(int i=n-1;i>=0;i--){
            char c = s.charAt(i);
            hash = (hash+ ((c-'a')+1)*base) %mod; // agr +1 nhi krte, to 'a' har baar 0 contribute krta
            base = (base*PRIME)%mod;// 1 -> 29 -> 29^2 -> 29^3
        }
        return hash%mod;
    }
    // har baar hash nahi nikaal rhe , 5 length hai agr mid to uske liye ek baar hash nikaala aur roll kiya , jisme se pura ek character niklega aur naya ek character aayega aur isliye next hash function use kr rhe hai 
    private long nextHash(long hash, char prev, char next, long primeLast){
        int prevChar = prev-'a'+1;
        int nextChar = next-'a'+1;
        long toRemove = (prevChar*primeLast) %mod;
        long remaining = ((hash-toRemove)%mod + mod)%mod;
        return (remaining*PRIME + nextChar)%mod;
    }
}

```

---

---
## Quick Revision
This problem asks for the longest substring that appears at least twice within a given string.
We can solve this using binary search on the length of the duplicate substring combined with a rolling hash to efficiently check for duplicates of a given length.

## Intuition
The core idea is that if a duplicate substring of length `k` exists, then a duplicate substring of any length less than `k` also exists. This monotonic property allows us to use binary search on the possible lengths of the duplicate substring. For a fixed length `L`, we need an efficient way to check if any substring of length `L` appears more than once. A naive approach of comparing all substrings would be too slow. This is where the rolling hash comes in. A rolling hash allows us to compute the hash of a substring of length `L` in O(1) time after an initial O(L) computation, by "rolling" the hash window one character at a time. By storing the hashes of all substrings of length `L` in a hash map, we can quickly detect if a hash has been seen before. If a hash collision occurs, we perform a character-by-character comparison to confirm if it's a true duplicate or a hash collision.

## Algorithm
1.  **Binary Search on Length:**
    *   Define a search space for the length of the duplicate substring. The minimum possible length is 0, and the maximum is `n-1` (where `n` is the length of the input string `s`).
    *   Use binary search to find the largest length `L` for which a duplicate substring of length `L` exists.
    *   In each iteration of the binary search, calculate the `mid` length.
    *   Call a helper function `getDuplicate(mid)` to check if a duplicate substring of length `mid` exists.
    *   If `getDuplicate(mid)` returns a non-empty string (meaning a duplicate of length `mid` was found), it means we might be able to find an even longer duplicate. So, we store this found duplicate as a potential answer and try searching in the upper half of the length range (`start = mid + 1`).
    *   If `getDuplicate(mid)` returns an empty string, it means no duplicate of length `mid` exists. Therefore, no duplicate longer than `mid` can exist either. We search in the lower half of the length range (`end = mid - 1`).
2.  **`getDuplicate(length)` Function (Rolling Hash):**
    *   This function checks if there's any duplicate substring of the given `length`.
    *   Initialize a `HashMap` called `seen` to store `(hash_value, starting_index)` of substrings encountered so far.
    *   Calculate the initial hash for the first substring of `length` (i.e., `s.substring(0, length)`).
    *   Store this initial hash and its starting index (0) in the `seen` map.
    *   Pre-calculate `primeLast`, which is `PRIME^(length-1) % mod`. This is needed for the rolling hash formula to remove the contribution of the leftmost character.
    *   Iterate through the string from index `length` to `n-1`:
        *   For each position `i`, calculate the hash of the substring ending at `i` (i.e., `s.substring(i - length + 1, i + 1)`) using the rolling hash technique. This involves:
            *   Subtracting the contribution of the character leaving the window (`s.charAt(i - length)`).
            *   Multiplying the remaining hash by `PRIME`.
            *   Adding the contribution of the new character entering the window (`s.charAt(i)`).
            *   Applying the modulo operation at each step to prevent overflow.
        *   Check if the newly calculated `hash` is already present in the `seen` map.
        *   If the `hash` is present:
            *   Retrieve the `prevStart` index from the `seen` map.
            *   Perform a character-by-character comparison between `s.substring(prevStart, prevStart + length)` and `s.substring(i - length + 1, i + 1)` to confirm if it's a true duplicate (not just a hash collision).
            *   If it's a true duplicate, return the duplicate substring `s.substring(i - length + 1, i + 1)`.
        *   If the `hash` is not present, or if it was a hash collision and not a true duplicate, add the current `hash` and its starting index (`i - length + 1`) to the `seen` map.
    *   If the loop finishes without finding any duplicate, return an empty string.
3.  **`findHash(String s)` Function:**
    *   Calculates the polynomial rolling hash for a given string `s`.
    *   Uses `PRIME` as the base and `mod` for the modulo operation.
    *   The formula is `hash = (c1 * PRIME^0 + c2 * PRIME^1 + ... + ck * PRIME^(k-1)) % mod`, where `ci` is the value of the i-th character (e.g., `c - 'a' + 1`).
4.  **`nextHash(long hash, char prev, char next, long primeLast)` Function:**
    *   Efficiently updates the hash when the window slides one position to the right.
    *   `hash`: The hash of the previous window.
    *   `prev`: The character leaving the window from the left.
    *   `next`: The character entering the window from the right.
    *   `primeLast`: `PRIME^(length-1) % mod`.
    *   The formula is `new_hash = ((old_hash - prev_char_contribution) * PRIME + next_char_contribution) % mod`.
    *   `prev_char_contribution = (prev - 'a' + 1) * primeLast % mod`.
    *   Ensure correct handling of negative results from subtraction by adding `mod` before taking modulo again: `((hash - toRemove) % mod + mod) % mod`.

## Concept to Remember
*   **Binary Search on Answer:** Applicable when the problem exhibits a monotonic property, allowing us to search for the optimal value (in this case, the length of the duplicate substring).
*   **Rolling Hash (Rabin-Karp Algorithm):** An efficient technique to compute and compare hashes of substrings in O(1) time on average, after initial setup. It's crucial for problems involving substring matching or duplicate detection.
*   **Polynomial Hashing:** A common method for rolling hashes, where each character's value is multiplied by a power of a prime base, and the sum is taken modulo a large prime to prevent overflow and reduce collisions.
*   **Hash Collisions:** Understanding that different strings can produce the same hash value, and the necessity of a secondary verification step (character-by-character comparison) when a hash match is found.

## Common Mistakes
*   **Integer Overflow:** Not using `long` for hash calculations and not applying the modulo operator frequently enough can lead to incorrect hash values.
*   **Incorrect Rolling Hash Formula:** Errors in the formula for `nextHash`, especially in handling the removal of the leftmost character's contribution and ensuring positive results after subtraction.
*   **Inefficient Duplicate Checking:** Relying solely on hash comparison without a character-by-character verification can lead to false positives due to hash collisions.
*   **Off-by-One Errors:** Incorrectly calculating indices for substrings, `primeLast`, or loop bounds in the rolling hash or binary search.
*   **Not Handling Edge Cases:** Forgetting to consider empty strings, strings with no duplicates, or strings where the duplicate is the entire string itself.

## Complexity Analysis
*   **Time:** O(N log N)
    *   The binary search performs `log N` iterations (where N is the length of the string).
    *   Inside each binary search iteration, the `getDuplicate` function uses a rolling hash. Calculating the initial hash takes O(L) time (where L is the current length being checked). The rolling hash update takes O(1) for each of the N-L positions. In the worst case, L can be up to N.
    *   The `getDuplicate` function iterates through the string once, performing O(1) hash operations per character. The string comparison in case of a hash collision takes O(L) time, but this happens rarely on average with good hash functions.
    *   Therefore, `getDuplicate` takes O(N) time on average.
    *   The total time complexity is O(N log N).
*   **Space:** O(N)
    *   The `HashMap` in `getDuplicate` can store up to N entries in the worst case (if all substrings of a certain length have unique hashes). Each entry stores a hash (long) and an index (int).
    *   The space complexity is dominated by the hash map.

## Commented Code
```javascript
class Solution {
    // Store the input string globally for easy access in helper methods.
    String S = "";
    // A prime number used as the base for polynomial hashing.
    // Choosing a prime helps in distributing hash values more evenly, reducing collisions.
    int PRIME = 29;
    // A large prime number used as the modulus to prevent hash values from becoming too large and to wrap them around.
    int mod = 1000000007;

    /**
     * Main function to find the longest duplicate substring.
     * @param s The input string.
     * @return The longest duplicate substring, or an empty string if none exists.
     */
    public String longestDupSubstring(String s) {
        // Assign the input string to the class member for use in helper functions.
        S = s;
        // Initialize the binary search range for the length of the duplicate substring.
        // 'start' is the minimum possible length (1, as an empty string is trivial).
        // 'end' is the maximum possible length (length of the string - 1).
        int start = 1;
        int end = S.length() - 1;
        // Variable to store the longest duplicate substring found so far.
        String ans = "";

        // Perform binary search on the possible lengths of the duplicate substring.
        // The loop continues as long as the search space is valid (start <= end).
        while (start <= end) {
            // Calculate the middle length to check. Using (end - start) / 2 prevents potential overflow.
            int mid = start + (end - start) / 2;
            // Call getDuplicate to check if a duplicate substring of length 'mid' exists.
            String duplicate = getDuplicate(mid);

            // If getDuplicate returns an empty string, it means no duplicate of length 'mid' was found.
            // This implies that no duplicate longer than 'mid' can exist either.
            // So, we reduce the search space to the lower half (lengths less than 'mid').
            if (duplicate.isEmpty()) {
                end = mid - 1;
            } else {
                // If a duplicate of length 'mid' was found, it's a potential answer.
                // We store it and try to find an even longer duplicate by searching in the upper half of the length range.
                ans = duplicate;
                start = mid + 1;
            }
        }
        // After the binary search, 'ans' will hold the longest duplicate substring found.
        return ans;
    }

    /**
     * Checks if there is any duplicate substring of a given length using rolling hash.
     * @param length The length of the substring to check for duplicates.
     * @return The first duplicate substring of the given length found, or an empty string if none exists.
     */
    private String getDuplicate(int length) {
        // Calculate the hash of the first substring of the given length.
        long hash = findHash(S.substring(0, length));
        // A HashMap to store encountered hashes and their corresponding starting indices.
        // Key: hash value, Value: starting index of the substring.
        HashMap<Long, Integer> seen = new HashMap<>();
        // Put the hash of the first substring and its index (0) into the map.
        seen.put(hash, 0);

        // Pre-calculate PRIME^(length-1) % mod. This is needed for the rolling hash formula
        // to efficiently remove the contribution of the character leaving the window.
        long primeLast = 1;
        for (int i = 0; i < length - 1; i++) {
            primeLast = (primeLast * PRIME) % mod;
        }

        // Iterate through the string starting from the character that would complete the second substring of 'length'.
        // 'i' represents the end index of the current window.
        for (int i = length; i < S.length(); i++) {
            // Calculate the hash of the next substring using the rolling hash technique.
            // 'hash' is the previous hash.
            // 'S.charAt(i - length)' is the character leaving the window.
            // 'S.charAt(i)' is the character entering the window.
            // 'primeLast' is used to remove the contribution of the leaving character.
            hash = nextHash(hash, S.charAt(i - length), S.charAt(i), primeLast);
            // Calculate the starting index of the current substring.
            int start = i - length + 1;

            // Check if the current hash has been seen before.
            if (seen.containsKey(hash)) {
                // If the hash is in the map, it's a potential duplicate.
                // Retrieve the starting index of the previously seen substring with this hash.
                int prevStart = seen.get(hash);
                // Perform a character-by-character comparison to confirm if it's a true duplicate
                // and not just a hash collision.
                if (S.substring(prevStart, prevStart + length).equals(S.substring(start, start + length))) {
                    // If it's a true duplicate, return this substring.
                    return S.substring(start, start + length);
                }
            }
            // If the hash is not in the map, or if it was a hash collision and not a true duplicate,
            // add the current hash and its starting index to the map.
            seen.put(hash, start);
        }

        // If the loop completes without finding any duplicate substring of the given length, return an empty string.
        return "";
    }

    /**
     * Calculates the polynomial rolling hash for a given string.
     * @param s The string for which to calculate the hash.
     * @return The calculated hash value.
     */
    private long findHash(String s) {
        // Initialize hash value to 0.
        long hash = 0;
        // Initialize base to 1. This will be multiplied by PRIME in each step to get powers of PRIME.
        long base = 1;
        int n = s.length();
        // Iterate through the string from right to left (or left to right, but this way is common for polynomial hashing).
        // The formula used here is: hash = (c_n * PRIME^0 + c_{n-1} * PRIME^1 + ... + c_1 * PRIME^(n-1)) % mod
        // where c_i is the character value.
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            // Convert character to a numerical value (e.g., 'a' -> 1, 'b' -> 2, ...).
            // Adding 1 ensures that 'a' contributes a non-zero value.
            // Multiply by the current power of the base (PRIME) and add to the hash.
            // Apply modulo at each step to prevent overflow.
            hash = (hash + ((c - 'a') + 1) * base) % mod;
            // Update the base for the next character (multiply by PRIME).
            base = (base * PRIME) % mod;
        }
        // Return the final hash value.
        return hash % mod;
    }

    /**
     * Updates the rolling hash when the window slides one position to the right.
     * @param hash The current hash value of the window.
     * @param prev The character leaving the window from the left.
     * @param next The character entering the window from the right.
     * @param primeLast PRIME^(length-1) % mod, used to remove the contribution of the 'prev' character.
     * @return The new hash value for the shifted window.
     */
    private long nextHash(long hash, char prev, char next, long primeLast) {
        // Convert characters to their numerical values (e.g., 'a' -> 1).
        int prevChar = prev - 'a' + 1;
        int nextChar = next - 'a' + 1;

        // Calculate the contribution of the character leaving the window.
        // This is (prevChar * PRIME^(length-1)) % mod.
        long toRemove = (prevChar * primeLast) % mod;

        // Subtract the contribution of the leaving character from the current hash.
        // Add 'mod' and take modulo again to handle potential negative results from subtraction.
        long remaining = ((hash - toRemove) % mod + mod) % mod;

        // Shift the remaining hash to the left by multiplying by PRIME, and add the contribution of the new character.
        // Apply modulo at each step.
        return (remaining * PRIME + nextChar) % mod;
    }
}
```

## Interview Tips
1.  **Explain the Binary Search First:** Start by explaining that the problem has a monotonic property (if a duplicate of length `k` exists, so does one of length `k-1`), which makes binary search on the length a suitable approach.
2.  **Detail the Rolling Hash:** Clearly articulate how rolling hash works, why it's efficient for substring comparisons, and the formula for updating the hash. Emphasize the role of `PRIME` and `mod`.
3.  **Address Hash Collisions:** Be prepared to explain what hash collisions are and why a character-by-character comparison is necessary as a fallback to ensure correctness.
4.  **Walk Through an Example:** Use a small example string (e.g., "banana") to trace the binary search and the rolling hash process for a specific length. This demonstrates your understanding.
5.  **Discuss Complexity:** Be ready to explain the time and space complexity of both the binary search and the rolling hash components.

## Revision Checklist
- [ ] Understand the problem: find the longest substring that appears at least twice.
- [ ] Recognize the monotonic property for binary search on length.
- [ ] Implement binary search on the length of the duplicate substring.
- [ ] Understand and implement polynomial rolling hash.
- [ ] Implement the `findHash` function correctly.
- [ ] Implement the `nextHash` function correctly, handling modulo arithmetic and subtractions.
- [ ] Use a `HashMap` to store seen hashes and their indices.
- [ ] Include a character-by-character verification step to handle hash collisions.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty string, no duplicates).

## Similar Problems
*   Longest Common Prefix
*   Substring with Concatenation of All Words
*   Find All Anagrams in a String
*   Repeated DNA Sequences
*   Valid Anagram

## Tags
`String` `Binary Search` `Rolling Hash` `Hash Map` `Rabin-Karp`
