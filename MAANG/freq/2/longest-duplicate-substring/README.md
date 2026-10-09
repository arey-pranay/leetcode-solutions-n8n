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
The core idea is that if a duplicate substring of length `L` exists, then a duplicate substring of any length `k < L` also *could* exist. Conversely, if no duplicate substring of length `L` exists, then no duplicate substring of any length `k > L` can exist. This monotonic property allows us to use binary search on the possible lengths of the duplicate substring.

For a fixed length `L`, we need an efficient way to check if any substring of that length appears more than once. A naive approach would be to generate all substrings of length `L` and store them in a hash set, checking for collisions. However, generating and comparing substrings repeatedly can be slow. This is where the rolling hash comes in. A rolling hash allows us to compute the hash of a substring of length `L` in O(1) time after the initial hash computation, by "rolling" the window one character at a time.

The "aha moment" is realizing that binary search on the *length* of the duplicate substring, combined with a rolling hash for efficient checking of duplicates for a given length, provides an optimal solution.

## Algorithm
1.  **Binary Search on Length:**
    *   Initialize `low = 1` (minimum possible length of a duplicate substring) and `high = S.length() - 1` (maximum possible length).
    *   Initialize `ans = ""` to store the longest duplicate substring found so far.
    *   While `low <= high`:
        *   Calculate `mid = low + (high - low) / 2`. This `mid` represents the current length we are checking for a duplicate.
        *   Call a helper function `getDuplicate(mid)` to find if a duplicate substring of length `mid` exists.
        *   If `getDuplicate(mid)` returns a non-empty string (meaning a duplicate of length `mid` was found):
            *   Update `ans = duplicate_substring`.
            *   Try to find a longer duplicate: `low = mid + 1`.
        *   If `getDuplicate(mid)` returns an empty string (no duplicate of length `mid` found):
            *   The duplicate must be shorter: `high = mid - 1`.
    *   Return `ans`.

2.  **`getDuplicate(length)` Function (using Rolling Hash):**
    *   Initialize a `HashMap<Long, Integer>` called `seen` to store `(hash_value, starting_index)` of substrings encountered.
    *   Calculate the initial hash for the first substring of the given `length` (i.e., `S.substring(0, length)`).
    *   Store this initial hash and its starting index (0) in the `seen` map.
    *   Precompute `primeLast = PRIME^(length-1) % mod`. This is needed for the rolling hash calculation to remove the contribution of the leftmost character.
    *   Iterate through the string from `i = length` to `S.length() - 1`:
        *   Calculate the hash of the current window `S.substring(i - length + 1, i + 1)` using the rolling hash formula:
            `new_hash = ( (old_hash - S.charAt(i - length) * primeLast) * PRIME + S.charAt(i) ) % mod`.
            Ensure intermediate results are handled correctly with modulo arithmetic (e.g., `(val % mod + mod) % mod` for negative results).
        *   If the `new_hash` is already present in the `seen` map:
            *   This is a potential duplicate. To avoid hash collisions, perform a character-by-character comparison between the current substring `S.substring(i - length + 1, i + 1)` and the substring starting at the index stored in `seen.get(new_hash)`.
            *   If they are indeed equal, return the current substring `S.substring(i - length + 1, i + 1)`.
        *   Add the `new_hash` and its starting index (`i - length + 1`) to the `seen` map.
    *   If no duplicate is found after iterating through all possible windows, return an empty string `""`.

3.  **`findHash(substring)` Function:**
    *   Calculates the polynomial rolling hash for a given substring.
    *   `hash = (c1 * PRIME^0 + c2 * PRIME^1 + ... + cn * PRIME^(n-1)) % mod`.
    *   Use `(char - 'a' + 1)` to map characters to positive integers (1-26) to avoid 'a' contributing zero.

4.  **`nextHash(hash, prevChar, nextChar, primeLast)` Function:**
    *   Efficiently updates the hash when sliding the window one character to the right.
    *   Removes the contribution of `prevChar` (the character leaving the window).
    *   Multiplies the remaining hash by `PRIME` to shift powers.
    *   Adds the contribution of `nextChar` (the character entering the window).
    *   Applies modulo arithmetic at each step.

## Concept to Remember
*   **Binary Search on Answer:** When a problem has a monotonic property (if a solution of size X exists, then solutions of size < X might also exist, and if no solution of size X exists, no solution of size > X can exist), binary search can be applied to find the optimal size.
*   **Rolling Hash (Rabin-Karp):** An efficient technique to compute and compare hashes of substrings in O(1) time after initial computation, by updating the hash incrementally as the window slides.
*   **Polynomial Hashing:** A common method for rolling hash where each character's value is multiplied by a power of a prime base, and the sum is taken modulo a large prime to prevent overflow and reduce collisions.
*   **Hash Collisions:** The possibility that two different strings can produce the same hash value. This necessitates a secondary check (e.g., character-by-character comparison) when a hash match is found.

## Common Mistakes
*   **Incorrect Modulo Arithmetic:** Failing to handle negative results from subtraction operations within modulo arithmetic (e.g., `(a - b) % mod` should be `(a - b % mod + mod) % mod`).
*   **Hash Collision Handling:** Forgetting to perform a character-by-character comparison when a hash match is found, leading to incorrect results due to hash collisions.
*   **Off-by-One Errors:** Incorrectly calculating indices for substrings, window boundaries, or the `primeLast` power.
*   **Integer Overflow:** Not using `long` for hash calculations or intermediate products, leading to incorrect hash values.
*   **Choosing Poor Hash Parameters:** Using a non-prime base or a small modulo can increase the likelihood of hash collisions.

## Complexity Analysis
*   **Time:** O(N log N)
    *   The binary search performs `log N` iterations, where `N` is the length of the string.
    *   Inside each iteration, the `getDuplicate` function uses a rolling hash. Calculating the initial hash takes O(L) time (where L is the current length being checked). The rolling hash updates take O(1) for each of the N-L windows. So, `getDuplicate` takes O(N) time.
    *   The character-by-character comparison in case of a hash collision takes O(L) time, but this happens rarely on average with good hash parameters. In the worst case, if many collisions occur, it could degrade performance. However, for typical inputs and good hash functions, the average time complexity remains O(N log N).
*   **Space:** O(N)
    *   The `HashMap` in `getDuplicate` can store up to N-L entries in the worst case, where each entry stores a hash value and an index. This can be up to O(N) space.

## Commented Code
```javascript
class Solution {
    // Store the input string globally for easy access in helper methods.
    String S = "";
    // A prime number used as the base for polynomial hashing.
    // Choosing a prime helps in distributing hash values more evenly and reducing collisions.
    // The value 29 is chosen as it's a prime greater than 26 (number of lowercase English letters).
    int PRIME = 29;
    // A large prime number used as the modulus to prevent integer overflow and further reduce collisions.
    int mod = 1000000007;

    /**
     * Main function to find the longest duplicate substring.
     * @param s The input string.
     * @return The longest duplicate substring, or an empty string if none exists.
     */
    public String longestDupSubstring(String s) {
        // Assign the input string to the class member for use in helper functions.
        S = s;
        // Initialize the search space for the length of the duplicate substring.
        // 'start' is the minimum possible length (1), 'end' is the maximum possible length (N-1).
        int start = 1;
        int end = S.length() - 1;
        // Variable to store the longest duplicate substring found so far.
        String ans = "";

        // Perform binary search on the possible lengths of the duplicate substring.
        // The goal is to find the largest 'mid' for which a duplicate substring of length 'mid' exists.
        while (start <= end) {
            // Calculate the middle length to check. Using this form prevents potential integer overflow.
            int mid = start + (end - start) / 2;
            // Call the helper function to find if a duplicate substring of length 'mid' exists.
            String duplicate = getDuplicate(mid);

            // If a duplicate substring of length 'mid' was found:
            if (!duplicate.isEmpty()) {
                // This 'duplicate' is a candidate for the longest duplicate. Update 'ans'.
                ans = duplicate;
                // Since we found a duplicate of length 'mid', we try to find an even longer one.
                // So, we shift the search space to the right half: [mid + 1, end].
                start = mid + 1;
            } else {
                // If no duplicate substring of length 'mid' was found:
                // It means any duplicate substring must be shorter than 'mid'.
                // So, we shift the search space to the left half: [start, mid - 1].
                end = mid - 1;
            }
        }
        // After the binary search, 'ans' will hold the longest duplicate substring found.
        return ans;
    }

    /**
     * Checks if a duplicate substring of a given length exists in S.
     * Uses rolling hash to efficiently find potential duplicates.
     * @param length The length of the substring to check for duplicates.
     * @return The first duplicate substring of the given length found, or an empty string if none exists.
     */
    private String getDuplicate(int length) {
        // Calculate the hash of the first substring of the given 'length'.
        long hash = findHash(S.substring(0, length));
        // A HashMap to store encountered hashes and their corresponding starting indices.
        // Key: hash value, Value: starting index of the substring that produced this hash.
        HashMap<Long, Integer> seen = new HashMap<>();
        // Store the hash of the first substring and its starting index (0).
        seen.put(hash, 0);

        // Precompute PRIME^(length-1) % mod. This value is needed to efficiently remove
        // the contribution of the leftmost character when rolling the hash.
        long primeLast = 1;
        for (int i = 0; i < length - 1; i++) {
            primeLast = (primeLast * PRIME) % mod;
        }

        // Iterate through the string, sliding a window of size 'length'.
        // 'i' represents the end index of the current window.
        for (int i = length; i < S.length(); i++) {
            // Calculate the hash of the next window using the rolling hash formula.
            // 'S.charAt(i - length)' is the character leaving the window.
            // 'S.charAt(i)' is the character entering the window.
            hash = nextHash(hash, S.charAt(i - length), S.charAt(i), primeLast);
            // The starting index of the current window.
            int start = i - length + 1;

            // Check if the current hash has been seen before.
            if (seen.containsKey(hash)) {
                // If the hash exists, it's a potential duplicate.
                // We need to verify if the actual substrings are identical to rule out hash collisions.
                int prevStart = seen.get(hash);
                // Compare the current substring with the previously seen substring that had the same hash.
                if (S.substring(prevStart, prevStart + length).equals(S.substring(start, start + length))) {
                    // If they are identical, we've found a duplicate substring of the given 'length'.
                    // Return this duplicate substring.
                    return S.substring(start, start + length);
                }
            }
            // Store the current hash and its starting index in the 'seen' map.
            // If a hash collision occurs and the strings are different, this entry will be overwritten,
            // which is fine as we are looking for *any* duplicate.
            seen.put(hash, start);
        }

        // If the loop finishes without finding any duplicate substring of the given 'length', return an empty string.
        return "";
    }

    /**
     * Calculates the polynomial rolling hash for a given substring.
     * Hash = (c1 * PRIME^0 + c2 * PRIME^1 + ... + cn * PRIME^(n-1)) % mod
     * @param s The substring to hash.
     * @return The computed hash value.
     */
    private long findHash(String s) {
        long hash = 0;
        long base = 1; // Represents PRIME^i for the current character.
        int n = s.length();
        // Iterate from right to left to match the power progression (e.g., c_n * PRIME^0, c_{n-1} * PRIME^1, ...)
        // or from left to right with powers increasing. The provided code iterates from right to left.
        // Let's re-evaluate the provided code's findHash: it iterates from right to left,
        // meaning char at index i gets multiplied by base which is PRIME^(n-1-i).
        // Example: "abc", n=3.
        // i=2 ('c'): hash = (0 + ('c'-'a'+1)*1) % mod. base = (1*PRIME)%mod.
        // i=1 ('b'): hash = (hash + ('b'-'a'+1)*base) % mod. base = (base*PRIME)%mod.
        // i=0 ('a'): hash = (hash + ('a'-'a'+1)*base) % mod. base = (base*PRIME)%mod.
        // This means 'c' is multiplied by PRIME^0, 'b' by PRIME^1, 'a' by PRIME^2.
        // This is a valid polynomial hash: hash = (c_n * P^0 + c_{n-1} * P^1 + ... + c_1 * P^{n-1}) % mod.
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            // Map character to a positive integer (1-26) to avoid 'a' contributing 0.
            // Add its contribution to the hash, multiplied by the current power of PRIME.
            hash = (hash + ((c - 'a') + 1) * base) % mod;
            // Update the base for the next character (increment the power of PRIME).
            base = (base * PRIME) % mod;
        }
        // Return the final hash value.
        return hash % mod;
    }

    /**
     * Calculates the next hash value efficiently when sliding the window one character to the right.
     * Formula: new_hash = ( (old_hash - prev_char_value * PRIME^(length-1)) * PRIME + next_char_value ) % mod
     * @param hash The current hash value.
     * @param prev The character leaving the window from the left.
     * @param next The character entering the window from the right.
     * @param primeLast PRIME^(length-1) % mod, precomputed for efficiency.
     * @return The new hash value.
     */
    private long nextHash(long hash, char prev, char next, long primeLast) {
        // Get the integer value of the previous character (leaving the window).
        int prevChar = prev - 'a' + 1;
        // Get the integer value of the next character (entering the window).
        int nextChar = next - 'a' + 1;

        // Calculate the contribution of the previous character to the hash.
        // This is (prevChar * PRIME^(length-1)) % mod.
        long toRemove = (prevChar * primeLast) % mod;

        // Subtract the contribution of the previous character from the current hash.
        // Add 'mod' and take modulo again to handle potential negative results from subtraction.
        long remaining = ((hash - toRemove) % mod + mod) % mod;

        // Multiply the remaining hash by PRIME to shift the powers of PRIME for the remaining characters.
        // Then, add the contribution of the new character.
        // Apply modulo at each step to prevent overflow.
        return (remaining * PRIME + nextChar) % mod;
    }
}
```

## Interview Tips
1.  **Explain Binary Search First:** Start by explaining the binary search approach on the length of the duplicate substring. This demonstrates your ability to identify monotonic properties and apply appropriate algorithms.
2.  **Detail Rolling Hash Logic:** Clearly articulate how the rolling hash works, including the polynomial hashing formula, the role of the prime base and modulus, and crucially, how the `nextHash` function efficiently updates the hash in O(1) time.
3.  **Address Hash Collisions:** Emphasize the importance of handling hash collisions. Explain that a direct string comparison is necessary when a hash match occurs to ensure correctness. This shows you understand the limitations of hashing.
4.  **Walk Through an Example:** Use a small example string (e.g., "banana") to trace the binary search and the rolling hash process for a specific `mid` length. This makes your explanation concrete and easier to follow.
5.  **Discuss Complexity:** Be prepared to analyze the time and space complexity of your solution, explaining how each part contributes to the overall complexity.

## Revision Checklist
- [ ] Understand the problem: find the longest substring appearing at least twice.
- [ ] Recognize the monotonic property for binary search on length.
- [ ] Implement binary search correctly with `low`, `high`, `mid`.
- [ ] Understand the need for efficient duplicate checking for a given length.
- [ ] Grasp the concept of rolling hash (Rabin-Karp).
- [ ] Implement polynomial hashing correctly.
- [ ] Implement the `nextHash` function for O(1) updates.
- [ ] Handle modulo arithmetic carefully, especially for subtractions.
- [ ] Implement hash collision detection (character-by-character comparison).
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty string, string with no duplicates).

## Similar Problems
*   Longest Common Prefix
*   Substring with Concatenation of All Words
*   Find All Anagrams in a String
*   Repeated DNA Sequences
*   Valid Anagram

## Tags
`Binary Search` `String` `Rolling Hash` `Hash Map` `Rabin-Karp`
