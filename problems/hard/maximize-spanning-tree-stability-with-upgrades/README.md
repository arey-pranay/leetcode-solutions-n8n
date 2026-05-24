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
The core idea is that if we can form a stable spanning tree with a minimum edge stability of `X`, we can also form one with any minimum stability less than `X`. This monotonic property suggests binary search on the answer (the minimum stability). For a given `X`, we need to determine if it's possible to connect all nodes into a single component (form a spanning tree) using at most `k` upgrades, where an upgrade allows us to use an edge with stability `s` such that `s < X` but `2*s >= X`.

## Algorithm
1.  **Binary Search on Minimum Stability:**
    *   Define a search space for the minimum stability `X`. The lower bound can be 1, and a reasonable upper bound can be the maximum possible edge stability (e.g., 200000 as given in the problem constraints).
    *   In each iteration of the binary search, pick a `mid` value as the potential minimum stability `X`.
    *   Call a helper function `canAchieve(n, edges, k, X)` to check if it's possible to form a spanning tree with minimum stability `X` using at most `k` upgrades.
    *   If `canAchieve` returns `true`, it means `X` is achievable, so we try for a higher minimum stability by setting `low = mid + 1` and store `mid` as a potential answer.
    *   If `canAchieve` returns `false`, `X` is too high, so we reduce the search space by setting `high = mid - 1`.
    *   The final `ans` will be the maximum achievable minimum stability.

2.  **`canAchieve(n, edges, k, X)` Helper Function:**
    *   Initialize a Disjoint Set Union (DSU) data structure with `n` nodes.
    *   **Process Mandatory Edges:** Iterate through all edges. If an edge is mandatory (`must == 1`):
        *   If its stability `s` is less than `X`, it's impossible to achieve the target minimum stability `X` because mandatory edges *must* meet this threshold. Return `false`.
        *   If `s >= X`, unite the two nodes of this edge in the DSU. If they are already in the same set, it implies a cycle with mandatory edges, which is impossible for a tree. Return `false`.
    *   **Process Free Optional Edges (Stability >= X):** Iterate through all edges again. If an edge is optional (`must == 0`) and its stability `s` is greater than or equal to `X`:
        *   Unite the two nodes of this edge in the DSU. These edges are "free" to use as they meet the stability requirement.
    *   **Process Upgradeable Optional Edges (Stability < X but 2*s >= X):**
        *   Initialize `usedUpgrades = 0`.
        *   Iterate through all edges. If an edge is optional (`must == 0`), its stability `s` is less than `X`, but `2 * s >= X` (meaning it can be upgraded to meet the `X` threshold):
            *   Try to unite the two nodes of this edge in the DSU.
            *   If `unite` returns `true` (meaning the edge connected two previously disconnected components), increment `usedUpgrades`.
            *   If `usedUpgrades` exceeds `k`, it means we've used too many upgrades. Return `false`.
    *   **Final Check:** After processing all edges, check if the DSU has exactly one component (`dsu.components == 1`). If it does, it means all nodes are connected, and we have successfully formed a spanning tree with minimum stability `X` using at most `k` upgrades. Return `true`. Otherwise, return `false`.

3.  **Initial Mandatory Cycle Check:** Before starting the binary search, perform a quick check for cycles formed *only* by mandatory edges. If any mandatory edge connects two nodes already in the same set, it's impossible to form a tree, and we should return -1 immediately. This is done by initializing a DSU and uniting mandatory edges.

## Concept to Remember
*   **Binary Search on Answer:** Applicable when a property is monotonic (if `X` works, any `Y < X` also works).
*   **Disjoint Set Union (DSU):** Efficiently tracks connected components and detects cycles.
*   **Spanning Tree Properties:** A connected graph with `N` nodes requires exactly `N-1` edges to form a tree, and it must not contain cycles.
*   **Greedy Approach within Binary Search:** Within `canAchieve`, we prioritize mandatory edges, then "free" optional edges, and finally use upgrades for optional edges that can meet the threshold.

## Common Mistakes
*   **Incorrect Binary Search Range:** Setting the `low` or `high` bounds too restrictively or too broadly can lead to incorrect answers or timeouts.
*   **Misinterpreting Upgrade Condition:** Not correctly applying the `2 * s >= X` condition for upgradeable edges, or incorrectly handling edges that are `s < X` but `2 * s < X` (these cannot be used).
*   **DSU Implementation Errors:** Bugs in `find` (e.g., missing path compression) or `unite` (e.g., missing union by rank/size) can lead to incorrect connectivity tracking or performance issues.
*   **Forgetting the Initial Mandatory Cycle Check:** Not checking for cycles formed solely by mandatory edges upfront can lead to incorrect results or unnecessary binary search iterations.
*   **Edge Cases:** Not handling cases where `k=0` or where no spanning tree can be formed at all (returning -1).

## Complexity Analysis
*   **Time:**
    *   The `canAchieve` function iterates through the edges a constant number of times (3 passes). Each DSU operation (`find`, `unite`) takes nearly constant time on average (amortized $O(\alpha(N))$, where $\alpha$ is the inverse Ackermann function, which grows extremely slowly). So, `canAchieve` is roughly $O(E \alpha(N))$, where `E` is the number of edges.
    *   The binary search performs $O(\log(\text{MaxStability}))$ iterations.
    *   The initial mandatory cycle check takes $O(E \alpha(N))$.
    *   Therefore, the total time complexity is $O(E \alpha(N) \log(\text{MaxStability}))$.
*   **Space:**
    *   The DSU data structure uses $O(N)$ space for `parent` and `rank` arrays.
    *   The `edges` array is input, not additional space.
    *   Therefore, the space complexity is $O(N)$.

## Commented Code
```java
class DSU {
    int[] parent; // Stores the parent of each element. If parent[i] == i, then i is the root of its set.
    int[] rank;   // Stores the rank (or height) of the tree rooted at each element. Used for union by rank optimization.
    int components; // Tracks the number of disjoint sets (connected components).

    // Constructor to initialize the DSU structure.
    public DSU(int n) {
        parent = new int[n]; // Initialize parent array for n elements.
        rank = new int[n];   // Initialize rank array for n elements.
        components = n;      // Initially, each element is in its own component.

        // Each element is initially its own parent (root of its own set).
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        // Ranks are initialized to 0 by default for int arrays.
    }

    // Finds the representative (root) of the set that element x belongs to.
    // Implements path compression for optimization.
    public int find(int x) {
        // If x is not the root of its set (i.e., its parent is not itself).
        if (parent[x] != x) {
            // Recursively find the root and set it as the parent of x (path compression).
            parent[x] = find(parent[x]);
        }
        // Return the root of the set.
        return parent[x];
    }

    // Unites the sets containing elements a and b.
    // Returns true if the sets were different and merged, false if they were already in the same set.
    // Implements union by rank optimization.
    public boolean unite(int a, int b) {
        int pa = find(a); // Find the root of the set containing a.
        int pb = find(b); // Find the root of the set containing b.

        // If a and b are already in the same set, no merge is needed.
        if (pa == pb) return false;

        // Union by rank: attach the shorter tree to the root of the taller tree.
        if (rank[pa] < rank[pb]) {
            // Swap pa and pb so that pa always points to the root of the taller tree.
            int temp = pa;
            pa = pb;
            pb = temp;
        }

        // Make the root of the taller tree (pa) the parent of the root of the shorter tree (pb).
        parent[pb] = pa;

        // If the ranks were equal, the height of the taller tree increases by one.
        if (rank[pa] == rank[pb]) {
            rank[pa]++;
        }

        // Decrement the number of components since two sets have been merged.
        components--;
        // Return true to indicate a successful merge.
        return true;
    }
}

class Solution {

    // Helper function to check if it's possible to form a spanning tree
    // with a minimum edge stability of 'x' using at most 'k' upgrades.
    public boolean canAchieve(int n, int[][] edges, int k, int x) {
        DSU dsu = new DSU(n); // Initialize DSU for 'n' nodes.

        // First pass: Process mandatory edges.
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            // If the edge is mandatory ('must' == 1).
            if (must == 1) {
                // If its stability 's' is less than the target minimum 'x', it's impossible.
                if (s < x) return false;
                // Try to unite the nodes. If they are already connected, it forms a cycle with mandatory edges.
                if (!dsu.unite(u, v)) return false;
            }
        }

        // Second pass: Process optional edges that meet the stability requirement 'x' without upgrades.
        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            // If the edge is optional ('must' == 0) and its stability 's' is >= 'x'.
            if (must == 0 && s >= x) {
                // Unite the nodes. These edges are "free" to use.
                dsu.unite(u, v);
            }
        }

        // Third pass: Process optional edges that can be upgraded.
        int usedUpgrades = 0; // Counter for the number of upgrades used.

        for (int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            // If the edge is optional ('must' == 0), its stability 's' is less than 'x',
            // but it can be upgraded (2 * s >= x).
            if (must == 0 && s < x && 2 * s >= x) {
                // Try to unite the nodes. If successful, it means this upgrade connected two components.
                if (dsu.unite(u, v)) {
                    usedUpgrades++; // Increment the upgrade counter.
                    // If we exceed the allowed number of upgrades 'k', it's impossible.
                    if (usedUpgrades > k) return false;
                }
            }
        }

        // Finally, check if all nodes are connected (i.e., there is only one component).
        return dsu.components == 1;
    }

    // Main function to find the maximum possible minimum stability.
    public int maxStability(int n, int[][] edges, int k) {

        // Initial check: Ensure no cycles are formed by mandatory edges alone.
        DSU dsu = new DSU(n);

        for (int[] e : edges) {
            // If the edge is mandatory ('must' == 1).
            if (e[3] == 1) {
                // If uniting the nodes of a mandatory edge creates a cycle, it's impossible.
                if (!dsu.unite(e[0], e[1])) {
                    return -1; // Return -1 to indicate impossibility.
                }
            }
        }

        // Binary search for the maximum possible minimum stability.
        int low = 1;             // Minimum possible stability is 1.
        int high = 200000;       // Maximum possible stability (given constraint).
        int ans = -1;            // Initialize answer to -1 (no solution found yet).

        // Standard binary search loop.
        while (low <= high) {
            int mid = low + (high - low) / 2; // Calculate the middle value to test.

            // Check if it's possible to achieve a minimum stability of 'mid'.
            if (canAchieve(n, edges, k, mid)) {
                ans = mid;         // If achievable, 'mid' is a potential answer.
                low = mid + 1;     // Try for a higher minimum stability.
            } else {
                high = mid - 1;    // If not achievable, 'mid' is too high, try lower.
            }
        }

        // Return the maximum achievable minimum stability found.
        return ans;
    }
}
```

## Interview Tips
1.  **Explain the Binary Search:** Clearly articulate why binary search is applicable here (monotonicity of the problem) and how you're searching for the "maximum minimum".
2.  **DSU Logic:** Be prepared to explain the DSU data structure, including `find` with path compression and `unite` with union by rank/size. Walk through how it's used to detect cycles and track connectivity.
3.  **Edge Prioritization:** Emphasize the order of processing edges within `canAchieve`: mandatory first, then free optional, then upgradeable optional. Explain the reasoning behind this greedy strategy.
4.  **Clarify Constraints:** Ask about the range of `n`, `edges`, `k`, and edge stability values. This helps in setting binary search bounds and understanding potential edge cases.
5.  **"What if...?" Scenarios:** Be ready to discuss variations, e.g., what if upgrades had a cost? What if there were multiple types of upgrades?

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Recognize the applicability of binary search on the answer.
- [ ] Implement Disjoint Set Union (DSU) correctly with path compression and union by rank.
- [ ] Design the `canAchieve` helper function to correctly process mandatory, free optional, and upgradeable optional edges.
- [ ] Handle the upgrade condition `2 * s >= x` accurately.
- [ ] Implement the initial check for cycles formed by mandatory edges.
- [ ] Set appropriate bounds for the binary search.
- [ ] Test with edge cases (e.g., `k=0`, no possible spanning tree).

## Similar Problems
*   [1319. Number of Operations to Make Network Connected](https://leetcode.com/problems/number-of-operations-to-make-network-connected/) (Uses DSU to count components)
*   [1135. Connecting Cities With Minimum Cost](https://leetcode.com/problems/connecting-cities-with-minimum-cost/) (Minimum Spanning Tree, Kruskal's algorithm)
*   [1584. Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/) (Minimum Spanning Tree)
*   [1489. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree](https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/) (More complex MST variations)

## Tags
`Binary Search` `Disjoint Set Union` `Graph` `Greedy` `Union Find`
