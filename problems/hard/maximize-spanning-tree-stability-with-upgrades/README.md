# Maximize Spanning Tree Stability With Upgrades

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Binary Search` `Greedy` `Union-Find` `Graph Theory` `Minimum Spanning Tree`  
**Time:** O(E * log(MaxWeight)  
**Space:** O(N)

---

## Solution (java)

```java
class DSU {
    int[] parent;
    int[] rank;
    int components;

    public DSU(int n) {
        parent = new int[n];
        rank = new int[n];
        components = n;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean unite(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;

        if (rank[pa] < rank[pb]) {
            int temp = pa;
            pa = pb;
            pb = temp;
        }

        parent[pb] = pa;

        if (rank[pa] == rank[pb]) {
            rank[pa]++;
        }

        components--;
        return true;
    }
}

class Solution {

    public boolean canAchieve(int n, int[][] edges, int k, int x) {
        DSU dsu = new DSU(n);

        // Mandatory edges
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            if (must == 1) {
                if (s < x) return false;
                if (!dsu.unite(u, v)) return false;
            }
        }

        // Free optional edges
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            if (must == 0 && s >= x) {
                dsu.unite(u, v);
            }
        }

        // Upgrade edges
        int usedUpgrades = 0;

        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            if (must == 0 && s < x && 2 * s >= x) {
                if (dsu.unite(u, v)) {
                    usedUpgrades++;
                    if (usedUpgrades > k) return false;
                }
            }
        }

        return dsu.components == 1;
    }

    public int maxStability(int n, int[][] edges, int k) {

        // Check mandatory cycle
        DSU dsu = new DSU(n);

        for (int[] e : edges) {
            if (e[3] == 1) {
                if (!dsu.unite(e[0], e[1])) {
                    return -1;
                }
            }
        }

        int low = 1, high = 200000;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canAchieve(n, edges, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }
}
```

---

---
## Quick Revision
This problem asks for the maximum possible minimum edge weight (stability) in a spanning tree, given constraints on upgrades. We solve it by using binary search on the stability value and checking feasibility with a Disjoint Set Union (DSU) data structure.

## Intuition
The core idea is that if we can achieve a minimum stability of `X`, we can also achieve any minimum stability less than `X`. This monotonic property suggests binary search on the answer (the minimum stability). For a given target stability `X`, we need to determine if it's possible to form a spanning tree where all edges have a weight of at least `X`, using at most `k` upgrades. The DSU structure is perfect for efficiently tracking connected components and checking if a graph is connected.

## Algorithm
1.  **Binary Search on Stability:**
    *   Initialize `low = 1` and `high = 200000` (or a reasonable upper bound for edge weights).
    *   While `low <= high`:
        *   Calculate `mid = low + (high - low) / 2`. This `mid` is our candidate minimum stability.
        *   Call a helper function `canAchieve(n, edges, k, mid)` to check if a spanning tree with minimum stability `mid` is possible.
        *   If `canAchieve` returns `true`:
            *   We can achieve this stability, so store `mid` as a potential answer (`ans = mid`).
            *   Try for a higher stability: `low = mid + 1`.
        *   If `canAchieve` returns `false`:
            *   This stability is too high, so we need to aim lower: `high = mid - 1`.
    *   Return `ans`.

2.  **`canAchieve(n, edges, k, x)` Helper Function:**
    *   Initialize a DSU structure for `n` nodes.
    *   **Process Mandatory Edges:**
        *   Iterate through all `edges`.
        *   If an edge is mandatory (`must == 1`):
            *   If its stability `s` is less than the target `x`, it's impossible to satisfy this mandatory edge with the current stability target. Return `false`.
            *   Try to unite the two nodes of this edge using DSU. If they are already connected (`!dsu.unite(u, v)`), it means adding this mandatory edge creates a cycle, which is not allowed in a spanning tree. Return `false`.
    *   **Process Free Optional Edges:**
        *   Iterate through all `edges` again.
        *   If an edge is optional (`must == 0`) and its stability `s` is greater than or equal to the target `x`:
            *   Unite the nodes of this edge using DSU. These edges are "free" in terms of upgrades and contribute to connecting components.
    *   **Process Upgradeable Optional Edges:**
        *   Initialize `usedUpgrades = 0`.
        *   Iterate through all `edges` one last time.
        *   If an edge is optional (`must == 0`), its stability `s` is less than the target `x`, but `2 * s >= x` (meaning it can be upgraded to meet the target):
            *   Try to unite the nodes of this edge. If they are not already connected (`dsu.unite(u, v)` returns `true`):
                *   Increment `usedUpgrades`.
                *   If `usedUpgrades` exceeds `k`, we've used too many upgrades. Return `false`.
    *   **Final Check:**
        *   After processing all edges, check if the DSU structure has exactly one component (`dsu.components == 1`). If so, it means all nodes are connected, and we have successfully formed a spanning tree meeting the stability requirement with at most `k` upgrades. Return `true`. Otherwise, return `false`.

3.  **Initial Mandatory Cycle Check:**
    *   Before starting the binary search, perform an initial check using DSU on only the mandatory edges. If any mandatory edge creates a cycle, it's impossible to form a spanning tree at all. Return `-1`.

## Concept to Remember
*   **Binary Search:** Applicable when a property exhibits monotonicity (if a value `X` works, any value less than `X` also works).
*   **Disjoint Set Union (DSU):** Efficiently manages disjoint sets, used here to track connected components and detect cycles.
*   **Spanning Tree Properties:** A spanning tree connects all vertices in a graph with the minimum number of edges (V-1) and no cycles.
*   **Greedy Approach within Binary Search:** Within `canAchieve`, we prioritize mandatory edges, then "free" optional edges, and finally use upgrades on the cheapest possible optional edges that meet the stability requirement.

## Common Mistakes
*   **Incorrect Binary Search Range:** Setting `low` or `high` too small or too large, missing the optimal answer.
*   **Order of Edge Processing in `canAchieve`:** Not processing mandatory edges first, or not correctly distinguishing between "free" optional edges and "upgradeable" optional edges.
*   **Cycle Detection Logic:** Incorrectly implementing or interpreting the `dsu.unite` return value for cycle detection.
*   **Upgrade Condition:** Misinterpreting the `2 * s >= x` condition or not correctly counting used upgrades.
*   **Initial Mandatory Cycle Check:** Forgetting to check if mandatory edges themselves form a cycle before starting the binary search.

## Complexity Analysis
*   **Time:** O(E * log(MaxWeight) * α(N))
    *   The binary search performs `log(MaxWeight)` iterations, where `MaxWeight` is the maximum possible edge weight (200000 in this case).
    *   Inside each iteration, `canAchieve` iterates through all `E` edges multiple times.
    *   Each DSU operation (`find` and `unite`) takes nearly constant time on average, amortized O(α(N)), where α is the inverse Ackermann function, which grows extremely slowly and is practically constant for all realistic inputs.
    *   The initial mandatory cycle check is O(E * α(N)).
*   **Space:** O(N)
    *   The DSU structure uses two arrays (`parent` and `rank`), each of size `N`.

## Commented Code
```java
class DSU {
    int[] parent; // Stores the parent of each element. If parent[i] == i, then i is the root of its set.
    int[] rank;   // Stores the rank (or height) of the tree rooted at each element. Used for union by rank optimization.
    int components; // Tracks the number of disjoint sets (connected components).

    // Constructor to initialize the DSU structure for 'n' elements.
    public DSU(int n) {
        parent = new int[n]; // Initialize parent array.
        rank = new int[n];   // Initialize rank array.
        components = n;      // Initially, each element is in its own component.

        // Each element is initially its own parent (root of its own set).
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        // Ranks are initialized to 0 by default for int arrays.
    }

    // Finds the representative (root) of the set that element 'x' belongs to.
    // Implements path compression for optimization.
    public int find(int x) {
        // If 'x' is not the root of its set (i.e., its parent is not itself).
        if (parent[x] != x) {
            // Recursively find the root and set it as the parent of 'x' (path compression).
            parent[x] = find(parent[x]);
        }
        // Return the root of the set.
        return parent[x];
    }

    // Unites the sets containing elements 'a' and 'b'.
    // Returns true if the sets were different and merged, false if they were already in the same set.
    // Implements union by rank optimization.
    public boolean unite(int a, int b) {
        int pa = find(a); // Find the root of the set containing 'a'.
        int pb = find(b); // Find the root of the set containing 'b'.

        // If 'a' and 'b' are already in the same set, do nothing and return false.
        if (pa == pb) return false;

        // Union by rank: attach the shorter tree to the root of the taller tree.
        // If rank of pa is less than rank of pb, swap them so pa is always the root of the taller tree.
        if (rank[pa] < rank[pb]) {
            int temp = pa;
            pa = pb;
            pb = temp;
        }

        // Make the root of the taller tree (pa) the parent of the root of the shorter tree (pb).
        parent[pb] = pa;

        // If the ranks were equal, increment the rank of the new root (pa).
        if (rank[pa] == rank[pb]) {
            rank[pa]++;
        }

        // Decrement the number of components because two sets have merged into one.
        components--;
        // Return true to indicate that a union operation was performed.
        return true;
    }
}

class Solution {

    // Helper function to check if it's possible to achieve a spanning tree
    // with a minimum edge stability of 'x', using at most 'k' upgrades.
    public boolean canAchieve(int n, int[][] edges, int k, int x) {
        DSU dsu = new DSU(n); // Initialize DSU for 'n' nodes.

        // First, process all mandatory edges.
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3]; // Unpack edge properties.

            // If the edge is mandatory ('must' == 1).
            if (must == 1) {
                // If the mandatory edge's stability is less than the target 'x', it's impossible.
                if (s < x) return false;
                // Try to unite the nodes. If they are already connected, this mandatory edge forms a cycle.
                if (!dsu.unite(u, v)) return false;
            }
        }

        // Next, process optional edges that already meet the stability requirement ('s' >= 'x').
        // These edges are "free" and don't consume upgrades.
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3]; // Unpack edge properties.

            // If the edge is optional ('must' == 0) AND its stability meets the target 'x'.
            if (must == 0 && s >= x) {
                // Unite the nodes. This edge helps connect components without using upgrades.
                dsu.unite(u, v);
            }
        }

        // Finally, process optional edges that need an upgrade to meet the stability requirement.
        int usedUpgrades = 0; // Counter for the number of upgrades used.

        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3]; // Unpack edge properties.

            // If the edge is optional ('must' == 0), its stability is less than 'x',
            // BUT it can be upgraded (2 * s >= x means upgrading it to 'x' is possible).
            if (must == 0 && s < x && 2 * s >= x) {
                // Try to unite the nodes. If they are not already connected, this upgrade is useful.
                if (dsu.unite(u, v)) {
                    usedUpgrades++; // Increment the upgrade count.
                    // If we exceed the allowed number of upgrades 'k', it's impossible.
                    if (usedUpgrades > k) return false;
                }
            }
        }

        // If after processing all edges, there is only one connected component,
        // it means a spanning tree has been formed with the required stability.
        return dsu.components == 1;
    }

    // Main function to find the maximum possible minimum edge weight (stability)
    // of a spanning tree, given 'n' nodes, 'edges', and at most 'k' upgrades.
    public int maxStability(int n, int[][] edges, int k) {

        // Initial check: Ensure that mandatory edges themselves do not form a cycle.
        DSU dsu = new DSU(n); // Initialize DSU for this check.

        for (int[] e : edges) {
            // If the edge is mandatory ('must' == 1).
            if (e[3] == 1) {
                // If uniting the nodes of a mandatory edge creates a cycle, it's impossible to form any spanning tree.
                if (!dsu.unite(e[0], e[1])) {
                    return -1; // Return -1 to indicate impossibility.
                }
            }
        }

        // Binary search for the maximum possible minimum stability.
        int low = 1;              // The minimum possible stability is 1.
        int high = 200000;        // A reasonable upper bound for edge weights.
        int ans = -1;             // Initialize answer to -1 (no solution found yet).

        // Standard binary search loop.
        while (low <= high) {
            int mid = low + (high - low) / 2; // Calculate the middle value as the candidate stability.

            // Check if it's possible to achieve a spanning tree with minimum stability 'mid'.
            if (canAchieve(n, edges, k, mid)) {
                ans = mid;         // If possible, 'mid' is a potential answer. Store it.
                low = mid + 1;     // Try to find a higher stability by searching in the right half.
            } else {
                high = mid - 1;    // If not possible, 'mid' is too high. Search in the left half.
            }
        }

        // Return the maximum stability found.
        return ans;
    }
}
```

## Interview Tips
*   **Explain Binary Search First:** Start by explaining the binary search approach on the answer (stability) and why it's applicable due to the monotonic property.
*   **Detail the `canAchieve` Logic:** Clearly articulate the three phases of processing edges within `canAchieve`: mandatory, free optional, and upgradeable optional. Emphasize the greedy choices made.
*   **DSU Explanation:** Be prepared to explain how DSU works, including `find` with path compression and `unite` with union by rank, and why it's suitable for this problem.
*   **Edge Cases:** Discuss the initial check for mandatory cycles and what `-1` signifies.
*   **Complexity Justification:** Clearly explain the time and space complexity, breaking down the contributions of binary search and DSU operations.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the monotonic property for binary search.
- [ ] Implement the `canAchieve` function correctly.
- [ ] Process mandatory edges first in `canAchieve`.
- [ ] Differentiate and process "free" optional edges.
- [ ] Correctly handle "upgradeable" optional edges and count upgrades.
- [ ] Implement DSU with path compression and union by rank.
- [ ] Perform the initial check for mandatory cycles.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases and constraints.

## Similar Problems
*   Kruskal's Algorithm (for Minimum Spanning Tree)
*   Prim's Algorithm (for Minimum Spanning Tree)
*   Graph connectivity problems
*   Problems involving binary search on the answer

## Tags
`Binary Search` `Disjoint Set Union` `Graph` `Greedy`
