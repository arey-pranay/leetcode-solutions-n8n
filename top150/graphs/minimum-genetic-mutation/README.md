# Minimum Genetic Mutation

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Breadth-First Search` `Hash Set` `String Manipulation`  
**Time:** O(N * L^2 * C)  
**Space:** O(N * L)

---

## Solution (java)

```java
class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
       HashSet<String> bankset = new HashSet<>(Arrays.asList(bank));
       Queue<String> q = new LinkedList<>();
       q.add(startGene);
       int level = 0;
       char[] chars = new char[]{'A','C','G','T'};
       while(!q.isEmpty()){
        int sz = q.size();
            while(sz-- > 0){
                String gene =  q.remove();
                if(gene.equals(endGene)) return level; 
                for(int i =0;i<8;i++){
                    for(char c : chars){
                        StringBuilder sb = new StringBuilder(gene);
                            sb.setCharAt(i,c);
                            String temp = sb.toString();
                            if(bankset.contains(temp)){
                                q.add(temp);
                                bankset.remove(temp);
                            }
                    }
                }
            }
            level++;
       }
       return -1;
    }
    // because we are storing level-wise.
    // class Pair{
    //     String gene;
    //     int count;
    //     Pair(String g, int c){
    //         this.gene = g;
    //         this.count = c;
    //     }
        
    //     @Override
    //     public String toString(){
    //         return this.gene + " " + this.count;
    //     }
    // }
    
}
```

---

---
## Quick Revision
This problem asks for the minimum number of mutations to transform a start gene to an end gene, given a bank of valid intermediate genes.
We solve this using Breadth-First Search (BFS) to explore possible mutations level by level.

## Intuition
The problem can be modeled as finding the shortest path in a graph. Each valid gene is a node, and an edge exists between two genes if they differ by exactly one character (a single mutation). Since we want the *minimum* number of mutations, BFS is the ideal algorithm because it explores the graph layer by layer, guaranteeing that the first time we reach the `endGene`, it will be via the shortest path. The `bank` acts as a constraint, meaning we can only visit genes present in the bank.

## Algorithm
1. Initialize a `HashSet` called `bankset` with all genes from the `bank` array for efficient lookups.
2. Initialize a `Queue` called `q` for BFS and add the `startGene` to it.
3. Initialize an integer `level` to 0, representing the number of mutations.
4. Define the possible characters for a gene mutation: `{'A', 'C', 'G', 'T'}`.
5. Start a `while` loop that continues as long as the `q` is not empty.
6. Inside the loop, get the current size of the queue (`sz`) to process all nodes at the current level.
7. Start an inner `while` loop that runs `sz` times.
8. Dequeue a `gene` from the `q`.
9. If the current `gene` is equal to `endGene`, return the current `level` as this is the minimum number of mutations.
10. Iterate through each position `i` (0 to 7, assuming gene length is 8) of the current `gene`.
11. For each position, iterate through all possible characters `c` (`'A', 'C', 'G', 'T'`).
12. Create a `StringBuilder` from the current `gene` and set the character at position `i` to `c`.
13. Convert the `StringBuilder` back to a `String` called `temp`.
14. Check if `temp` exists in `bankset`.
15. If `temp` is in `bankset`:
    a. Enqueue `temp` into `q`.
    b. Remove `temp` from `bankset` to avoid revisiting it and creating cycles or redundant paths.
16. After processing all nodes at the current level (inner `while` loop finishes), increment `level`.
17. If the `while` loop finishes and `endGene` was not found, return -1, indicating no valid mutation path exists.

## Concept to Remember
*   **Breadth-First Search (BFS):** Essential for finding the shortest path in an unweighted graph.
*   **Graph Representation:** Implicitly representing genes as nodes and mutations as edges.
*   **Set for Visited/Valid Nodes:** Using a `HashSet` for efficient checking of valid genes and to mark visited genes.
*   **Level-Order Traversal:** Processing nodes level by level to ensure shortest path.

## Common Mistakes
*   **Not handling the `bank` as a constraint:** Forgetting to check if a mutated gene is actually in the `bank`.
*   **Not marking visited nodes:** Leading to infinite loops or exploring suboptimal paths if cycles exist. The provided solution correctly removes from `bankset`.
*   **Incorrectly calculating mutations:** Not understanding that a single character change constitutes one mutation.
*   **Not returning -1 when no path exists:** Failing to handle cases where `endGene` is unreachable.
*   **Inefficient gene comparison:** Using string equality checks repeatedly instead of a more optimized approach if gene lengths were variable or very large.

## Complexity Analysis
*   **Time:** O(N * L^2 * C), where N is the number of genes in the bank, L is the length of a gene (fixed at 8 in this problem), and C is the number of possible characters (4).
    *   The BFS explores each valid gene in the bank at most once.
    *   For each gene, we generate all possible single mutations. There are L positions, and for each position, C possible characters. So, L * C mutations are generated.
    *   String building and conversion takes O(L).
    *   Set operations (contains, remove) are O(1) on average.
    *   Therefore, for each of the N genes, we do O(L * C * L) work.
*   **Space:** O(N * L), where N is the number of genes in the bank and L is the length of a gene.
    *   The `bankset` stores up to N genes, each of length L.
    *   The `queue` can store up to N genes in the worst case.

## Commented Code
```java
class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
       // Convert the bank array into a HashSet for efficient O(1) average time lookups.
       HashSet<String> bankset = new HashSet<>(Arrays.asList(bank));
       // Initialize a queue for Breadth-First Search (BFS).
       Queue<String> q = new LinkedList<>();
       // Add the starting gene to the queue to begin the search.
       q.add(startGene);
       // Initialize the mutation level (distance from startGene).
       int level = 0;
       // Define the possible characters that can form a gene.
       char[] chars = new char[]{'A','C','G','T'};
       // Start the BFS loop, continuing as long as there are genes to explore.
       while(!q.isEmpty()){
        // Get the number of genes at the current level to process them all before moving to the next level.
        int sz = q.size();
            // Process all genes at the current level.
            while(sz-- > 0){
                // Dequeue the current gene to process.
                String gene =  q.remove();
                // If the current gene is the target endGene, we've found the shortest path.
                if(gene.equals(endGene)) return level;
                // Iterate through each position of the gene (assuming fixed length of 8).
                for(int i =0;i<8;i++){
                    // Iterate through each possible character ('A', 'C', 'G', 'T').
                    for(char c : chars){
                        // Create a mutable copy of the current gene using StringBuilder.
                        StringBuilder sb = new StringBuilder(gene);
                        // Mutate the gene by changing the character at the current position 'i' to 'c'.
                        sb.setCharAt(i,c);
                        // Convert the mutated gene back to a String.
                        String temp = sb.toString();
                        // Check if this mutated gene is a valid gene present in the bank.
                        if(bankset.contains(temp)){
                            // If it's a valid gene, add it to the queue for exploration in the next level.
                            q.add(temp);
                            // Remove the gene from the bankset to mark it as visited and prevent cycles.
                            // This is crucial for BFS to find the shortest path and avoid redundant work.
                            bankset.remove(temp);
                        }
                    }
                }
            }
            // After processing all genes at the current level, increment the mutation level.
            level++;
       }
       // If the queue becomes empty and the endGene was not found, it means no valid mutation path exists.
       return -1;
    }
    // The commented Pair class was an alternative way to store gene and its mutation count,
    // but level-based BFS implicitly handles the count.
    // class Pair{
    //     String gene;
    //     int count;
    //     Pair(String g, int c){
    //         this.gene = g;
    //         this.count = c;
    //     }
        
    //     @Override
    //     public String toString(){
    //         return this.gene + " " + this.count;
    //     }
    // }
    
}
```

## Interview Tips
*   **Explain BFS clearly:** Articulate why BFS is the correct approach for finding the shortest path.
*   **Discuss graph representation:** Explain how genes are nodes and mutations are edges, even if not explicitly building an adjacency list.
*   **Handle edge cases:** Be prepared to discuss what happens if `startGene` is `endGene`, if `endGene` is not in the bank, or if no path exists.
*   **Justify data structure choices:** Explain why `HashSet` is used for the bank and `Queue` for BFS.
*   **Trace an example:** Walk through a small example to demonstrate how the BFS algorithm progresses.

## Revision Checklist
- [ ] Understand the problem: minimum mutations between two genes using a valid bank.
- [ ] Recognize BFS as the shortest path algorithm.
- [ ] Implement BFS with a queue.
- [ ] Use a `HashSet` for efficient bank lookups and visited tracking.
- [ ] Correctly generate all single mutations for a given gene.
- [ ] Handle the level-by-level traversal for mutation count.
- [ ] Ensure proper return values for success (-1 for failure).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Word Ladder
*   Shortest Path in Binary Matrix
*   Keys and Rooms
*   Pacific Atlantic Water Flow

## Tags
`Breadth-First Search` `Hash Set` `String Manipulation`

## My Notes
BFS genes
