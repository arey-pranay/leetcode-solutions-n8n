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
We use a list of stacks, where each stack holds elements of a specific frequency.

## Intuition
The core idea is to maintain separate stacks for each frequency of elements. When we push an element, we increment its frequency and place it onto the stack corresponding to that new frequency. When we pop, we want the element with the highest frequency. This means we look at the stack with the largest index (highest frequency) and pop from there. If that stack becomes empty, we effectively reduce the maximum frequency.

## Algorithm
1. Initialize a hash map `hm` to store the frequency of each element.
2. Initialize a list of stacks `stacks`. Add an empty stack at index 0 to handle the base case (frequency 0).
3. **`push(val)`**:
    a. Get the current frequency of `val` from `hm`, defaulting to 0 if not present. Increment it by 1.
    b. Update `val`'s frequency in `hm`.
    c. If the new frequency is equal to the current number of stacks, it means we need a new stack for this frequency. Add a new empty stack to `stacks`.
    d. Push `val` onto the stack at the index corresponding to its new frequency (`stacks.get(freq)`).
4. **`pop()`**:
    a. Get the stack at the highest frequency index (`stacks.get(stacks.size() - 1)`). This is the stack containing elements with the maximum current frequency.
    b. Pop an element `val` from this stack.
    c. Decrement the frequency of `val` in `hm`.
    d. If the stack from which we popped is now empty, remove it from `stacks` (effectively reducing the maximum frequency).
    e. Return `val`.

## Concept to Remember
*   **Hash Maps**: Efficiently storing and retrieving element frequencies.
*   **Stacks**: LIFO data structure, useful for managing elements of the same frequency in order of their push.
*   **Dynamic Data Structures**: Using a list of stacks that grows as needed to accommodate increasing frequencies.
*   **Frequency Tracking**: Maintaining counts of elements to prioritize based on occurrence.

## Common Mistakes
*   Not handling the case where a new frequency requires a new stack to be added to the `stacks` list.
*   Incorrectly updating the frequency in the hash map after popping an element.
*   Forgetting to remove an empty stack from the `stacks` list when its last element is popped, which can lead to incorrect `pop` operations.
*   Off-by-one errors when indexing into the `stacks` list based on frequency.

## Complexity Analysis
- Time: O(1) - reason: Both `push` and `pop` operations involve constant time hash map lookups/updates and stack operations (push/pop). Adding/removing stacks from the list are also amortized O(1) operations.
- Space: O(N) - reason: In the worst case, all N elements are distinct and pushed, leading to N entries in the hash map. The `stacks` list can also store up to N elements in total across all its stacks.

## Commented Code
```java
class FreqStack {

    // HashMap to store the frequency of each integer. Key: integer value, Value: its current frequency.
    HashMap<Integer,Integer> hm;
    // List of Stacks. Each stack at index 'i' will store numbers with frequency 'i'.
    // stacks.get(0) is a dummy stack, stacks.get(1) stores numbers with frequency 1, etc.
    List<Stack<Integer>> stacks;

    // Constructor to initialize the data structures.
    public FreqStack() {
        // Initialize the frequency map.
        this.hm = new HashMap<>();
        // Initialize the list of stacks.
        this.stacks = new ArrayList<>();
        // Add an initial empty stack at index 0. This simplifies logic as frequency 1 will map to index 1.
        stacks.add(new Stack<>());
    }
    
    // Pushes an integer onto the stack.
    public void push(int val) {
        // Get the current frequency of 'val', default to 0 if not present, and increment it.
        int freq = hm.getOrDefault(val,0)+1;
        // Update the frequency of 'val' in the hash map.
        hm.put(val, freq);
        // If the new frequency is equal to the current number of stacks, it means we need a new stack for this frequency.
        // For example, if stacks.size() is 2 (meaning we have stacks for freq 0 and 1), and freq becomes 2, we need a new stack for freq 2.
        if(freq==stacks.size()) stacks.add(new Stack<>());
        // Push 'val' onto the stack corresponding to its new frequency.
        stacks.get(freq).add(val);
    }
    
    // Pops an integer from the stack with the highest frequency.
    public int pop() {
        // Get the stack with the highest frequency. This is the last stack in our list.
        Stack<Integer> st = stacks.get(stacks.size()-1);
        // Pop the top element from this highest frequency stack.
        int val = st.pop();
        // If the stack becomes empty after popping, it means there are no more elements with this maximum frequency.
        // So, we remove this stack from our list, effectively reducing the maximum frequency.
        if(st.isEmpty()) stacks.remove(stacks.size()-1);
        // Decrement the frequency of the popped element in the hash map.
        hm.put(val,hm.get(val)-1);
        // Return the popped element.
        return val;
    }
}
```

## Interview Tips
*   Clearly explain the dual data structure approach (hash map for frequencies, list of stacks for elements).
*   Walk through a `push` and `pop` example step-by-step to demonstrate how the data structures are updated.
*   Be prepared to discuss edge cases, such as pushing the same element multiple times or popping when the stack is empty (though the problem constraints usually prevent the latter).
*   Emphasize the O(1) time complexity and how it's achieved.

## Revision Checklist
- [ ] Understand the problem statement: elements with highest frequency are popped first.
- [ ] Grasp the intuition: using a list of stacks indexed by frequency.
- [ ] Implement `push` correctly: update frequency, add to correct stack, potentially add a new stack.
- [ ] Implement `pop` correctly: get from highest frequency stack, update frequency, potentially remove empty stack.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases and common mistakes.

## Similar Problems
*   Least Frequent Stack (if such a problem existed, it would be a variation)
*   Top K Frequent Elements
*   Frequency Counter problems

## Tags
`Array` `Hash Map` `Stack` `Design`
