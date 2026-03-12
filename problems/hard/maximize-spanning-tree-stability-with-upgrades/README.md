# Maximize Spanning Tree Stability With Upgrades

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Binary Search` `Greedy` `Union-Find` `Graph Theory` `Minimum Spanning Tree`  
**Time:**   
**Space:** 

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
This problem asks for the maximum possible minimum edge stability (weight) in a spanning tree, given constraints on upgrades. We solve it using binary search on the minimum stability and a Disjoint Set Union (DSU) data structure to check feasibility.

## Intuition
The core idea is that if we can achieve a spanning tree with a minimum edge stability of `X`, we can also achieve any minimum stability less than `X`. This monotonicity allows us to use binary search on the possible values of minimum stability. For a given `X`, we need to determine if it's possible to form a connected graph (a spanning tree) using the available edges and upgrades, such that all edges used have a stability of at least `X`. The DSU structure is perfect for efficiently tracking connected components and checking if the graph becomes connected.

## Algorithm
1.  **Binary Search on Minimum Stability:**
    *   Define a search range for the minimum stability. The lower bound can be 1, and the upper bound can be the maximum possible edge stability (e.g., 200000 as given in the problem constraints).
    *   In each iteration of the binary search, pick a `mid` value as the target minimum stability `X`.
    *   Call a helper function `canAchieve(n, edges, k, X)` to check if a spanning tree can be formed with all edges having stability at least `X`, using at most `k` upgrades.
    *   If `canAchieve` returns `true`, it means `X` is achievable, so we try for a higher minimum stability by setting `low = mid + 1` and store `mid` as a potential answer.
    *   If `canAchieve` returns `false`, `X` is too high, so we reduce the search space by setting `high = mid - 1`.
    *   The final `ans` will be the maximum achievable minimum stability.

2.  **`canAchieve(n, edges, k, X)` Helper Function:**
    *   Initialize a Disjoint Set Union (DSU) data structure for `n` nodes.
    *   **Process Mandatory Edges:** Iterate through all edges. If an edge is mandatory (`must == 1`):
        *   If its stability `s` is less than `X`, it's impossible to achieve `X` as the minimum stability, so return `false`.
        *   Try to unite the two nodes of this edge using `dsu.unite(u, v)`. If they are already in the same set (`dsu.unite` returns `false`), it means adding this mandatory edge creates a cycle, which is impossible for a spanning tree, so return `false`.
    *   **Process Free Optional Edges (Stability >= X):** Iterate through all edges again. If an edge is optional (`must == 0`) and its stability `s` is greater than or equal to `X`:
        *   Unite the nodes `u` and `v` using `dsu.unite(u, v)`. These edges are "free" in terms of upgrades and help connect components.
    *   **Process Upgradeable Optional Edges (Stability < X):** Initialize `usedUpgrades = 0`. Iterate through all edges one last time. If an edge is optional (`must == 0`), its stability `s` is less than `X`, but `2 * s >= X` (meaning it can be upgraded to meet the `X` threshold):
        *   Try to unite the nodes `u` and `v` using `dsu.unite(u, v)`.
        *   If `dsu.unite` returns `true` (meaning it connected two previously disconnected components), increment `usedUpgrades`.
        *   If `usedUpgrades` exceeds `k`, it means we've used too many upgrades to satisfy the minimum stability `X`, so return `false`.
    *   **Final Check:** After processing all edges, if `dsu.components == 1`, it means all nodes are connected, and we have successfully formed a spanning tree with minimum stability `X` using at most `k` upgrades. Return `true`. Otherwise, return `false`.

3.  **Initial Mandatory Cycle Check:** Before starting the binary search, perform a quick check. Initialize a DSU and process only the mandatory edges (`must == 1`). If any mandatory edge creates a cycle (i.e., `dsu.unite` returns `false`), it's impossible to form a spanning tree at all, so return `-1`. This is an optimization to quickly identify impossible scenarios.

## Concept to Remember
*   **Disjoint Set Union (DSU):** Efficiently manages a collection of disjoint sets and supports operations like finding the representative of a set and merging two sets. Crucial for connectivity problems.
*   **Binary Search:** Applicable when a property is monotonic. Here, the ability to achieve a minimum stability `X` is monotonic.
*   **Spanning Tree Properties:** A spanning tree connects all vertices in a graph with the minimum number of edges (V-1) and no cycles.
*   **Greedy Approach within Binary Search:** Within `canAchieve`, we prioritize mandatory edges, then free optional edges, and finally use upgrades for optional edges that can meet the threshold.

## Common Mistakes
*   **Incorrect Binary Search Range:** Not setting the `low` and `high` bounds correctly, or off-by-one errors in the binary search loop.
*   **Misunderstanding Upgrade Condition:** Incorrectly applying the `2 * s >= x` condition or not realizing that only optional edges can be upgraded.
*   **DSU Implementation Errors:** Bugs in `find` (e.g., missing path compression) or `unite` (e.g., missing union by rank/size) can lead to incorrect connectivity checks and TLE.
*   **Forgetting Mandatory Edge Cycle Check:** Not returning `false` immediately if a mandatory edge creates a cycle in `canAchieve`.
*   **Not Handling the `-1` Case:** Failing to check for impossible scenarios (like mandatory cycles) upfront or if no stability can be achieved.

## Complexity Analysis
*   **Time:**
    *   The `canAchieve` function iterates through the edges a constant number of times (3 passes). Each DSU operation (`find`, `unite`) takes nearly constant time on average (amortized O(α(n)), where α is the inverse Ackermann function, which grows extremely slowly and is practically constant). So, `canAchieve` is O(E * α(n)), where E is the number of edges.
    *   The binary search performs `log(MaxStability)` iterations.
    *   Therefore, the total time complexity is O(E * α(n) * log(MaxStability)). Given MaxStability is up to 200000, log(MaxStability) is around 18.
*   **Space:**
    *   The DSU structure uses O(n) space for `parent` and `rank` arrays.
    *   The `edges` array is given, so we don't count it as extra space.
    *   Therefore, the space complexity is O(n).

## Commented Code
```java
// Disjoint Set Union (DSU) data structure for efficient set management.
class DSU {
    int[] parent; // Stores the parent of each element. parent[i] = i means i is a root.
    int[] rank;   // Stores the rank (or height) of the tree rooted at each element. Used for union by rank optimization.
    int components; // Tracks the number of disjoint sets (connected components).

    // Constructor initializes DSU for 'n' elements.
    public DSU(int n) {
        parent = new int[n]; // Initialize parent array of size n.
        rank = new int[n];   // Initialize rank array of size n.
        components = n;      // Initially, each element is in its own component.

        // Each element is initially its own parent (root of its own set).
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        // Ranks are initialized to 0.
    }

    // Finds the representative (root) of the set containing element 'x'.
    // Implements path compression for optimization.
    public int find(int x) {
        // If 'x' is not the root of its set (i.e., its parent is not itself).
        if (parent[x] != x) {
            // Recursively find the root and set it as the direct parent of 'x' (path compression).
            parent[x] = find(parent[x]);
        }
        // Return the root of the set.
        return parent[x];
    }

    // Unites the sets containing elements 'a' and 'b'.
    // Returns true if the sets were different and merged, false if they were already in the same set.
    // Implements union by rank for optimization.
    public boolean unite(int a, int b) {
        int pa = find(a); // Find the root of the set containing 'a'.
        int pb = find(b); // Find the root of the set containing 'b'.

        // If 'a' and 'b' are already in the same set, do nothing and return false.
        if (pa == pb) return false;

        // Union by rank: attach the shorter tree to the root of the taller tree.
        // If rank of pa is less than rank of pb, swap them so pa is always the root of the taller or equal height tree.
        if (rank[pa] < rank[pb]) {
            int temp = pa;
            pa = pb;
            pb = temp;
        }

        // Make the root of the taller tree (pa) the parent of the root of the shorter tree (pb).
        parent[pb] = pa;

        // If the ranks were equal, the height of the new tree increases, so increment the rank of the new root (pa).
        if (rank[pa] == rank[pb]) {
            rank[pa]++;
        }

        // Since two components have merged, decrement the total number of components.
        components--;
        // Return true to indicate a successful merge.
        return true;
    }
}

class Solution {

    // Helper function to check if it's possible to achieve a minimum edge stability of 'x'
    // using at most 'k' upgrades.
    public boolean canAchieve(int n, int[][] edges, int k, int x) {
        DSU dsu = new DSU(n); // Initialize DSU for 'n' nodes.

        // First pass: Process mandatory edges.
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            // If the edge is mandatory ('must' == 1).
            if (must == 1) {
                // If its stability 's' is less than the target minimum 'x', it's impossible.
                if (s < x) return false;
                // Try to unite the nodes. If they are already connected, it forms a cycle, which is invalid for a spanning tree.
                if (!dsu.unite(u, v)) return false;
            }
        }

        // Second pass: Process free optional edges that meet the stability requirement 'x'.
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            // If the edge is optional ('must' == 0) AND its stability 's' is at least 'x'.
            if (must == 0 && s >= x) {
                // Unite the nodes. These edges don't consume upgrades.
                dsu.unite(u, v);
            }
        }

        // Third pass: Process optional edges that need upgrades.
        int usedUpgrades = 0; // Counter for upgrades used.

        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            // If the edge is optional ('must' == 0), its stability 's' is less than 'x',
            // BUT it can be upgraded to meet 'x' (i.e., 2*s >= x).
            if (must == 0 && s < x && 2 * s >= x) {
                // Try to unite the nodes. If they were in different components, this edge is useful.
                if (dsu.unite(u, v)) {
                    usedUpgrades++; // Increment upgrade count if a merge happened.
                    // If we exceed the allowed number of upgrades 'k', it's impossible.
                    if (usedUpgrades > k) return false;
                }
            }
        }

        // If after considering all edges and upgrades, all nodes are connected (components == 1), then 'x' is achievable.
        return dsu.components == 1;
    }

    // Main function to find the maximum possible minimum edge stability.
    public int maxStability(int n, int[][] edges, int k) {

        // Initial check: Ensure mandatory edges don't form a cycle.
        DSU dsu = new DSU(n);
        for (int[] e : edges) {
            // If an edge is mandatory ('must' == 1).
            if (e[3] == 1) {
                // If uniting the nodes of a mandatory edge creates a cycle, it's impossible to form any spanning tree.
                if (!dsu.unite(e[0], e[1])) {
                    return -1; // Return -1 to indicate impossibility.
                }
            }
        }

        // Binary search for the maximum possible minimum stability.
        int low = 1;              // Minimum possible stability is 1.
        int high = 200000;        // Maximum possible stability (given constraint).
        int ans = -1;             // Initialize answer to -1 (in case no stability is achievable).

        // Standard binary search loop.
        while (low <= high) {
            int mid = low + (high - low) / 2; // Calculate the middle value to test.

            // Check if it's possible to achieve a minimum stability of 'mid'.
            if (canAchieve(n, edges, k, mid)) {
                // If achievable, 'mid' is a possible answer. Try for a higher stability.
                ans = mid;          // Store 'mid' as a potential answer.
                low = mid + 1;      // Move the lower bound up.
            } else {
                // If not achievable, 'mid' is too high. Try for a lower stability.
                high = mid - 1;     // Move the upper bound down.
            }
        }

        // Return the maximum stability found.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Monotonicity:** Clearly articulate why binary search is applicable by explaining the monotonic property of the problem (if `X` is achievable, any `Y < X` is also achievable).
*   **DSU Rationale:** Explain why DSU is the right tool for connectivity checks and how path compression and union by rank optimize it.
*   **Edge Prioritization:** Walk through the `canAchieve` function step-by-step, emphasizing the order of processing edges: mandatory, free optional, and then upgradeable optional. Explain the logic behind each step.
*   **Edge Cases:** Discuss how you handle the `-1` return case (mandatory cycles) and the scenario where no stable spanning tree can be formed.
*   **Complexity Justification:** Be prepared to explain the time and space complexity, especially the amortized constant time of DSU operations.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Recognize the monotonic property for binary search.
- [ ] Implement Disjoint Set Union (DSU) correctly with path compression and union by rank.
- [ ] Design the `canAchieve` helper function to check feasibility for a given minimum stability `X`.
- [ ] Correctly prioritize and process mandatory, free optional, and upgradeable optional edges within `canAchieve`.
- [ ] Handle the upgrade condition (`2 * s >= x`) accurately.
- [ ] Implement the binary search loop correctly.
- [ ] Add the initial check for mandatory edge cycles to return `-1`.
- [ ] Consider edge cases like `k=0` or no possible spanning tree.

## Similar Problems
*   LeetCode 1319: Number of Operations to Make Network Connected
*   LeetCode 1579: Remove Max Number of Edges to Keep Graph Fully Traversable
*   LeetCode 1135: Connecting Cities With Minimum Cost (Kruskal's Algorithm, related to MST)
*   LeetCode 1383: Maximum Performance of a Team (uses heaps, but related to selecting optimal components)

## Tags
`Graph` `Disjoint Set Union` `Binary Search` `Greedy` `Tree`

## My Notes
disjoint set
