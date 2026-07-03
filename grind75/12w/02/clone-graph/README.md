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
    HashMap<Node,Node> hm =new HashMap<>();
    public Node cloneGraph(Node node) {
        // we just need to clone this node.
        if(node == null) return null;
        if(hm.containsKey(node)) return hm.get(node);
        Node copy = new Node(node.val);
        hm.put(node,copy);
        for(Node n : node.neighbors) copy.neighbors.add(cloneGraph(n));
        return copy;
    }
}
```

---

---
## Quick Revision
Given a reference of a node in a connected undirected graph, return a deep copy of the graph.
This problem is solved using Depth First Search (DFS) or Breadth First Search (BFS) with a hash map to keep track of visited nodes.

## Intuition
The core challenge is to create a new graph structure that mirrors the original, ensuring that each node and its connections are replicated. When we encounter a node, we need to create its clone. If we've already cloned this node, we should reuse the existing clone to avoid infinite recursion and duplicate nodes. This suggests a recursive approach (like DFS) or an iterative approach (like BFS) where we maintain a mapping from original nodes to their cloned counterparts. The hash map is crucial for this mapping and for detecting already cloned nodes.

## Algorithm
1. **Handle Base Case:** If the input `node` is `null`, return `null`.
2. **Check Visited/Cloned:** If the `node` is already present in our `visited` map (which stores original node -> cloned node mappings), return its corresponding clone from the map.
3. **Create Clone:** Create a new `Node` with the same `val` as the original `node`.
4. **Store Mapping:** Add the mapping from the original `node` to its newly created `copy` into the `visited` map. This is crucial to prevent infinite recursion.
5. **Clone Neighbors:** Iterate through each `neighbor` of the original `node`. For each `neighbor`, recursively call `cloneGraph` to get its cloned version.
6. **Add Cloned Neighbors:** Add the cloned `neighbor` to the `neighbors` list of the `copy` node.
7. **Return Clone:** Return the `copy` node.

## Concept to Remember
*   **Graph Traversal:** Understanding DFS or BFS is fundamental for exploring all nodes and edges in a graph.
*   **Deep Copy vs. Shallow Copy:** The problem requires a deep copy, meaning new nodes and new connections must be created, not just references to the original ones.
*   **Hash Maps for Visited Tracking:** Hash maps are essential for efficiently storing and retrieving mappings between original and cloned nodes, preventing redundant work and cycles.
*   **Recursion/Iteration for Graph Structures:** Recursive solutions (like DFS) or iterative solutions (like BFS) are common patterns for processing graph data structures.

## Common Mistakes
*   **Not handling cycles:** Without a mechanism to track visited nodes (like a hash map), the algorithm can enter an infinite loop on graphs with cycles.
*   **Shallow copy:** Creating new nodes but reusing references to original neighbors, leading to a graph that is not truly independent.
*   **Incorrectly updating the visited map:** Adding the mapping *after* processing neighbors can lead to issues if a neighbor points back to the current node. The mapping should be established as soon as the clone is created.
*   **Not handling the null input:** Forgetting to check if the initial node is null.

## Complexity Analysis
*   **Time:** O(V + E) - We visit each node (V) and each edge (E) exactly once. The hash map lookups and insertions are O(1) on average.
*   **Space:** O(V) - The space complexity is dominated by the recursion stack (in DFS) or the queue (in BFS), which can store up to V nodes in the worst case, and the hash map which stores V mappings.

## Commented Code
```java
/*
// Definition for a Node.
class Node {
    public int val; // Stores the value of the node.
    public List<Node> neighbors; // Stores a list of neighboring nodes.
    public Node() { // Default constructor.
        val = 0; // Initialize value to 0.
        neighbors = new ArrayList<Node>(); // Initialize neighbors list as empty.
    }
    public Node(int _val) { // Constructor with value.
        val = _val; // Set the node's value.
        neighbors = new ArrayList<Node>(); // Initialize neighbors list as empty.
    }
    public Node(int _val, ArrayList<Node> _neighbors) { // Constructor with value and neighbors.
        val = _val; // Set the node's value.
        neighbors = _neighbors; // Set the node's neighbors.
    }
}
*/

class Solution {
    // HashMap to store the mapping from original nodes to their cloned counterparts.
    // This is crucial to avoid infinite recursion and to reuse already cloned nodes.
    HashMap<Node,Node> hm =new HashMap<>();

    // The main function to clone the graph starting from a given node.
    public Node cloneGraph(Node node) {
        // If the input node is null, there's nothing to clone, so return null.
        if(node == null) return null;

        // If this node has already been cloned (i.e., it's in our hash map),
        // return the existing clone to avoid creating duplicates and infinite loops.
        if(hm.containsKey(node)) return hm.get(node);

        // Create a new Node (the clone) with the same value as the original node.
        Node copy = new Node(node.val);

        // Add the mapping from the original node to its newly created clone in the hash map.
        // This must be done BEFORE processing neighbors to handle cycles correctly.
        hm.put(node,copy);

        // Iterate through all the neighbors of the original node.
        for(Node n : node.neighbors) {
            // Recursively call cloneGraph for each neighbor. This will either return
            // an existing clone (if already processed) or create a new clone for the neighbor.
            // Then, add the cloned neighbor to the neighbors list of our current 'copy' node.
            copy.neighbors.add(cloneGraph(n));
        }

        // Return the fully cloned node, with its value and all its cloned neighbors.
        return copy;
    }
}
```

## Interview Tips
*   **Explain your approach clearly:** Start by explaining that you'll use a traversal (DFS or BFS) and a hash map to keep track of cloned nodes.
*   **Walk through an example:** Use a small graph (e.g., 2-3 nodes with a few edges) to trace your algorithm's execution, showing how the hash map is populated and how recursion unfolds.
*   **Discuss edge cases:** Mention handling `null` input and graphs with cycles.
*   **Clarify the Node definition:** Ensure you understand the `Node` class structure and how to access its value and neighbors.

## Revision Checklist
- [ ] Understand the problem: deep copy of a connected undirected graph.
- [ ] Choose a traversal strategy: DFS (recursive) or BFS (iterative).
- [ ] Implement visited tracking: Use a HashMap to map original nodes to cloned nodes.
- [ ] Handle base cases: `null` node.
- [ ] Handle cycles: Ensure the visited map prevents infinite recursion.
- [ ] Correctly create new nodes and populate their neighbor lists.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Serialize and Deserialize Binary Tree
*   Serialize and Deserialize N-ary Tree
*   Graph Valid Tree
*   Number of Islands

## Tags
`Depth-First Search` `Breadth-First Search` `Graph` `Hash Map` `Recursion`
