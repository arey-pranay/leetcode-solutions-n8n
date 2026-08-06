# Maximum Frequency Stack

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `Stack` `Design` `Ordered Set`  
**Time:** O(1)  
**Space:** O(N)

---

## Solution (java)

```java
class FreqStack {

    HashMap<Integer,Integer> hm;
    List<Stack<Integer>> stacks;

    public FreqStack() {
        this.hm = new HashMap<>();
        this.stacks = new ArrayList<>();
        stacks.add(new Stack<>());
    }
    
    public void push(int val) {
        int freq = hm.getOrDefault(val,0)+1;
        hm.put(val, freq);
        if(freq==stacks.size()) stacks.add(new Stack<>());
        stacks.get(freq).add(val);
    }
    
    public int pop() {
        Stack<Integer> st = stacks.get(stacks.size()-1);
        int val = st.pop();
        if(st.isEmpty()) stacks.remove(stacks.size()-1);
        hm.put(val,hm.get(val)-1);
        return val;
    }
}
```

---

---
## Quick Revision
A stack that prioritizes popping elements with the highest frequency.
We use multiple stacks, each representing a frequency, to achieve this.

## Intuition
The core idea is to maintain separate stacks for elements based on their frequency. When we push an element, its frequency increases, so it should move to a stack corresponding to its new frequency. When we pop, we want the element with the highest frequency. This naturally means we should look at the stack representing the highest frequency. If that stack is empty, we move to the next highest frequency.

## Algorithm
1.  **Initialization**:
    *   Create a `HashMap` (`hm`) to store the frequency of each element.
    *   Create a `List` of `Stack`s (`stacks`). Each index `i` in `stacks` will represent a stack of elements that currently have a frequency of `i`.
    *   Initialize `stacks` with an empty stack at index 0 (though it won't be used for actual elements, it helps with indexing).

2.  **`push(val)` Operation**:
    *   Get the current frequency of `val` from `hm`. If `val` is not in `hm`, its frequency is 0.
    *   Increment the frequency of `val`.
    *   Update `val`'s frequency in `hm`.
    *   Check if a stack for this new frequency (`freq`) exists in `stacks`. If `freq` is equal to the current size of `stacks`, it means we need a new stack for this frequency, so add a new empty `Stack` to `stacks`.
    *   Push `val` onto the stack at index `freq` in `stacks`.

3.  **`pop()` Operation**:
    *   Identify the highest frequency currently present. This corresponds to the last non-empty stack in `stacks`. The index of this stack is `stacks.size() - 1`.
    *   Get the stack at this highest frequency index.
    *   Pop an element (`val`) from this stack.
    *   Decrement the frequency of `val` in `hm`.
    *   If the stack from which `val` was popped becomes empty, remove that stack from `stacks` (to keep `stacks.size() - 1` always pointing to the highest *actual* frequency).
    *   Return the popped element `val`.

## Concept to Remember
*   **Frequency Tracking**: Efficiently maintaining counts of elements.
*   **Data Structure Choice**: Using multiple stacks to group elements by a dynamic property (frequency).
*   **Dynamic Sizing**: Adjusting the number of auxiliary data structures (stacks) as needed.

## Common Mistakes
*   **Incorrect Frequency Update**: Forgetting to decrement the frequency in the `HashMap` during `pop`.
*   **Handling Empty Stacks**: Not properly removing empty stacks from the `stacks` list, which can lead to incorrect `pop` operations or unnecessary checks.
*   **Off-by-One Errors**: Mismanaging indices when accessing or adding stacks to the `stacks` list.
*   **Not Pre-allocating/Dynamically Adding Stacks**: Assuming a fixed number of frequency stacks or not creating a new stack when a new maximum frequency is reached.

## Complexity Analysis
- Time: O(1) - reason: Both `push` and `pop` operations involve constant time lookups, insertions, and deletions in HashMaps and Stacks, and list operations (add/remove at end, get at end) are also O(1) on average.
- Space: O(N) - reason: In the worst case, all N elements are distinct and pushed, leading to N entries in the HashMap and potentially N elements distributed across multiple stacks.

## Commented Code
```java
class FreqStack {

    // HashMap to store the frequency of each element. Key: element value, Value: its current frequency.
    HashMap<Integer,Integer> hm;
    // List of Stacks. stacks.get(i) will store elements with frequency i.
    // The index of the stack corresponds to the frequency.
    List<Stack<Integer>> stacks;

    // Constructor to initialize the data structures.
    public FreqStack() {
        // Initialize the frequency map.
        this.hm = new HashMap<>();
        // Initialize the list of stacks.
        this.stacks = new ArrayList<>();
        // Add an initial empty stack. This helps in indexing, ensuring stacks.get(freq) is always valid if freq > 0.
        // The stack at index 0 is a placeholder and won't store actual elements pushed by the user.
        stacks.add(new Stack<>());
    }
    
    // Pushes an element onto the stack.
    public void push(int val) {
        // Get the current frequency of 'val'. If 'val' is not in the map, default to 0.
        int freq = hm.getOrDefault(val,0)+1;
        // Update the frequency of 'val' in the HashMap.
        hm.put(val, freq);
        // Check if a stack for this new frequency 'freq' needs to be created.
        // If 'freq' is equal to the current number of stacks, it means we've reached a new maximum frequency.
        if(freq==stacks.size()) {
            // Add a new empty stack to accommodate elements with this new frequency.
            stacks.add(new Stack<>());
        }
        // Push 'val' onto the stack corresponding to its new frequency.
        // stacks.get(freq) accesses the stack for elements with frequency 'freq'.
        stacks.get(freq).add(val);
    }
    
    // Pops an element from the stack with the highest frequency.
    public int pop() {
        // Get the stack with the highest frequency. This is the last stack in the 'stacks' list.
        // stacks.size() - 1 gives the index of the stack with the maximum current frequency.
        Stack<Integer> st = stacks.get(stacks.size()-1);
        // Pop the top element from this highest frequency stack.
        int val = st.pop();
        // After popping, check if the stack has become empty.
        if(st.isEmpty()) {
            // If the stack is empty, it means no elements currently have this frequency.
            // Remove this stack from the list to ensure stacks.size() - 1 always points to the highest *actual* frequency.
            stacks.remove(stacks.size()-1);
        }
        // Decrement the frequency of the popped element 'val' in the HashMap.
        hm.put(val,hm.get(val)-1);
        // Return the popped element.
        return val;
    }
}
```

## Interview Tips
*   **Explain the Multi-Stack Approach**: Clearly articulate why using multiple stacks, indexed by frequency, is the key to solving this problem efficiently.
*   **Edge Cases**: Discuss how you handle the initial state (empty `stacks` list) and what happens when a stack becomes empty after a `pop`.
*   **Time/Space Trade-off**: Be prepared to discuss why this O(1) time solution uses O(N) space.

## Revision Checklist
- [ ] Understand the problem statement: elements with higher frequency are popped first.
- [ ] Grasp the intuition: using frequency as an index for separate stacks.
- [ ] Implement `push` correctly: update frequency, add new stack if needed, push to correct stack.
- [ ] Implement `pop` correctly: get from highest frequency stack, update frequency, remove empty stack.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases like empty stacks or new maximum frequencies.

## Similar Problems
*   LRU Cache
*   LFUCache (Least Frequently Used Cache)

## Tags
`Array` `Hash Map` `Stack` `Design`
