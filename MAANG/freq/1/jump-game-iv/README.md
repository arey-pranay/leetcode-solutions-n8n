# Jump Game Iv

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Breadth-First Search`  
**Time:** O(n^2)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> occ = new HashMap<>();
        for(int i=0;i<n;i++) occ.computeIfAbsent(arr[i],k->new ArrayList<>()).add(i);
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        
        boolean[] vis = new boolean[n];
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int index = curr[0];
            int steps = curr[1];
            if(index==n-1) return steps;
            steps++;
            if(index-1 >= 0 && !vis[index-1]){
                vis[index-1] = true;
                q.offer(new int[]{index-1,steps});
            }
            if(index+1 < n && !vis[index+1]){
                vis[index+1] = true;
                q.offer(new int[]{index+1,steps});
            }
            int val = arr[index];
            for(int duplicate : occ.get(val)){
                if(vis[duplicate]) continue;
                vis[duplicate] = true;
                q.offer(new int[]{duplicate,steps});
            }
            occ.get(val).clear();
        }
        return -1;
    }
}
// 100 1 2 3 4 10 4 5 6 100 10 8 9 10 404
```

---

---
## Quick Revision
The problem is to find the minimum number of jumps required to reach the last index in an array, where each element represents the maximum reachable index from that position.
This problem can be solved using a breadth-first search (BFS) approach with the help of a HashMap to store the indices of elements.

## Intuition
The key insight here is that we can use the BFS approach to explore the possible indices that can be reached from each position. The HashMap helps us to efficiently find the indices of elements that have the same value, which allows us to skip exploring those indices again.

## Algorithm
1. Initialize a HashMap `occ` to store the indices of elements, where each key is an element and its value is a list of indices.
2. Create a queue `q` to store the indices and the number of steps taken to reach them, and offer the starting index with 0 steps.
3. While the queue is not empty, poll the front element and explore the possible indices that can be reached from it:
	* Check if the current index is the last index, and if so, return the number of steps.
	* Mark the current index and its neighboring indices as visited.
	* Add the neighboring indices to the queue with the updated number of steps.
	* Remove the current index from the HashMap.
4. If the queue is empty and the last index is not reachable, return -1.

## Concept to Remember
* Breadth-first search (BFS) algorithm
* Using a HashMap to store indices of elements
* Efficiently exploring possible indices using a queue

## Common Mistakes
* Not initializing the HashMap correctly
* Not marking the current index as visited
* Not removing the current index from the HashMap
* Not handling the case where the last index is not reachable

## Complexity Analysis
- Time: O(n^2) - reason: In the worst case, we may need to explore all indices and their neighboring indices.
- Space: O(n) - reason: We need to store the indices of elements in the HashMap and the queue.

## Commented Code
```java
class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        // Initialize the HashMap to store the indices of elements
        Map<Integer, List<Integer>> occ = new HashMap<>();
        for(int i=0;i<n;i++) 
            // Store the indices of elements in the HashMap
            occ.computeIfAbsent(arr[i],k->new ArrayList<>()).add(i);
        
        // Create a queue to store the indices and the number of steps taken
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        
        // Create a boolean array to mark the visited indices
        boolean[] vis = new boolean[n];
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int index = curr[0];
            int steps = curr[1];
            if(index==n-1) return steps; // If the current index is the last index, return the number of steps
            steps++;
            if(index-1 >= 0 && !vis[index-1]){
                // Mark the neighboring index as visited
                vis[index-1] = true;
                q.offer(new int[]{index-1,steps});
            }
            if(index+1 < n && !vis[index+1]){
                // Mark the neighboring index as visited
                vis[index+1] = true;
                q.offer(new int[]{index+1,steps});
            }
            int val = arr[index];
            // Explore the possible indices that can be reached from the current index
            for(int duplicate : occ.get(val)){
                if(vis[duplicate]) continue; // If the index is already visited, skip it
                // Mark the index as visited
                vis[duplicate] = true;
                q.offer(new int[]{duplicate,steps});
            }
            // Remove the current index from the HashMap
            occ.get(val).clear();
        }
        return -1; // If the last index is not reachable, return -1
    }
}
```

## Interview Tips
* Make sure to understand the problem clearly and identify the key constraints.
* Use a BFS approach to efficiently explore the possible indices.
* Use a HashMap to store the indices of elements and efficiently find the indices of elements with the same value.
* Handle the case where the last index is not reachable.
* Practice explaining the solution and the thought process behind it.

## Revision Checklist
- [ ] Understand the problem clearly and identify the key constraints.
- [ ] Use a BFS approach to efficiently explore the possible indices.
- [ ] Use a HashMap to store the indices of elements.
- [ ] Handle the case where the last index is not reachable.

## Similar Problems
* Jump Game II
* Jump Game III
* Minimum Jumps to Reach Home

## Tags
`Array` `Hash Map` `Breadth-first search` `Greedy Algorithm`

## My Notes
bfs done in this for shortest path here.
