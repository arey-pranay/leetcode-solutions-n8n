# Clone Graph

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `Depth-First Search` `Breadth-First Search` `Graph Theory`  
**Time:** O(V + E)  
**Space:** O(V)

---

## Solution (java)

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    HashMap<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node==null)return null;
        if(map.containsKey(node)) return map.get(node);
        Node copy = new Node(node.val);
        map.put(node,copy);
        for(Node neighbor : node.neighbors) copy.neighbors.add(cloneGraph(neighbor));
        return copy;
    }
}
```

---

---
## Quick Revision
Given a reference of a node in a connected undirected graph, return a deep copy of the graph.
Solve using Depth First Search (DFS) or Breadth First Search (BFS) with a map to track visited nodes and their clones.

## Intuition
The core challenge is to create a new graph structure where each node and its connections are replicated. If we simply iterate and create new nodes, we might end up creating duplicate nodes for the same original node if it's reachable through multiple paths. The "aha moment" is realizing that we need a way to remember which original nodes have already been cloned and what their corresponding cloned nodes are. A hash map is perfect for this: it allows us to quickly check if a node has been cloned and retrieve its clone. This prevents infinite recursion and ensures each original node maps to exactly one clone.

## Algorithm
1.  **Handle Base Case:** If the input `node` is `null`, return `null`.
2.  **Check Visited/Cloned:** If the `node` is already present in our `map` (meaning it has been cloned), return its corresponding cloned node from the `map`.
3.  **Create Clone:** Create a new `Node` (`copy`) with the same `val` as the original `node`.
4.  **Store Mapping:** Add the mapping from the original `node` to its `copy` in the `map`. This is crucial to avoid infinite loops and redundant cloning.
5.  **Clone Neighbors:** Iterate through each `neighbor` of the original `node`.
6.  **Recursive Call:** For each `neighbor`, recursively call `cloneGraph` to get its clone.
7.  **Add Cloned Neighbor:** Add the returned cloned neighbor to the `neighbors` list of the `copy` node.
8.  **Return Clone:** Return the `copy` node.

## Concept to Remember
*   **Graph Traversal:** Understanding how to systematically visit all nodes and edges in a graph (DFS or BFS).
*   **Recursion/Iteration:** Applying recursive or iterative techniques to explore graph structures.
*   **Hash Maps for Memoization/Visited Tracking:** Using hash maps to store computed results or track visited states to avoid redundant work and infinite loops.
*   **Deep Copy vs. Shallow Copy:** Differentiating between copying references and creating entirely new, independent objects.

## Common Mistakes
*   **Not Handling Cycles:** Failing to use a mechanism (like a hash map) to detect and handle cycles in the graph, leading to infinite recursion.
*   **Shallow Copying Neighbors:** Copying the references to the original neighbors instead of recursively cloning them.
*   **Missing Base Case:** Not handling the `null` input node, which can lead to `NullPointerException`.
*   **Incorrect Map Usage:** Putting the mapping into the map *after* processing neighbors, which can still lead to issues in certain graph structures. The mapping should be established *before* processing neighbors.

## Complexity Analysis
*   **Time:** O(V + E) - Each node (V) and each edge (E) is visited and processed exactly once. The hash map lookups and insertions are O(1) on average.
*   **Space:** O(V) - In the worst case, the recursion depth can be V (for a skewed graph), and the hash map will store V entries, mapping each original node to its clone.

## Commented Code
```java
/*
// Definition for a Node.
class Node {
    public int val; // Stores the value of the node.
    public List<Node> neighbors; // Stores a list of references to neighboring nodes.
    public Node() { // Default constructor.
        val = 0; // Initializes value to 0.
        neighbors = new ArrayList<Node>(); // Initializes an empty list for neighbors.
    }
    public Node(int _val) { // Constructor with value.
        val = _val; // Sets the node's value.
        neighbors = new ArrayList<Node>(); // Initializes an empty list for neighbors.
    }
    public Node(int _val, ArrayList<Node> _neighbors) { // Constructor with value and neighbors.
        val = _val; // Sets the node's value.
        neighbors = _neighbors; // Sets the list of neighbors.
    }
}
*/

class Solution {
    // A HashMap to store the mapping from original nodes to their cloned counterparts.
    // This is crucial for handling cycles and avoiding redundant cloning.
    HashMap<Node, Node> map = new HashMap<>();

    // The main function to clone the graph starting from a given node.
    public Node cloneGraph(Node node) {
        // Base case: If the input node is null, return null.
        if(node==null)return null;

        // If the node has already been cloned (i.e., it's in our map),
        // return its existing clone to avoid re-cloning and infinite loops.
        if(map.containsKey(node)) return map.get(node);

        // Create a new node (the clone) with the same value as the original node.
        Node copy = new Node(node.val);

        // Add the mapping from the original node to its newly created clone in the map.
        // This must be done BEFORE processing neighbors to handle cycles correctly.
        map.put(node,copy);

        // Iterate through all the neighbors of the original node.
        for(Node neighbor : node.neighbors) {
            // Recursively call cloneGraph for each neighbor. This will either return
            // an existing clone (if already processed) or create a new one.
            // Add the returned cloned neighbor to the neighbors list of our current 'copy' node.
            copy.neighbors.add(cloneGraph(neighbor));
        }

        // Return the fully cloned node (which now has its cloned neighbors attached).
        return copy;
    }
}
```

## Interview Tips
*   **Explain the Map's Purpose:** Clearly articulate why the hash map is essential for preventing infinite recursion in cyclic graphs and for ensuring each node is cloned only once.
*   **Trace an Example:** Be prepared to walk through a small graph (e.g., a triangle or a square) with the interviewer, showing how the map is populated and how recursion unfolds.
*   **Discuss DFS vs. BFS:** Mention that both DFS (as used in the provided solution) and BFS can solve this problem. Briefly explain how a BFS approach would use a queue instead of recursion.
*   **Edge Cases:** Highlight the importance of the `null` node check and how the algorithm handles disconnected components (though the problem statement implies a connected graph).

## Revision Checklist
- [ ] Understand the graph node structure.
- [ ] Recognize the need for a visited/cloned tracking mechanism.
- [ ] Implement DFS or BFS traversal.
- [ ] Use a HashMap to store node mappings.
- [ ] Handle the null input node.
- [ ] Correctly add cloned neighbors to the new node.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Serialize and Deserialize Binary Tree
*   Serialize and Deserialize N-ary Tree
*   Graph Valid Tree
*   Number of Islands

## Tags
`Depth-First Search` `Breadth-First Search` `Graph` `Hash Map` `Recursion`
