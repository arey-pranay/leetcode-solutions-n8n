# Jump Game Iv

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Breadth-First Search`  
**Time:** O(n)  
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
The problem is to find the minimum number of jumps required to reach the last index in an array, where each index has a value and a duplicate index is marked.

We solve this problem by using a breadth-first search (BFS) algorithm with a queue and a hash map to keep track of visited indices.

## Intuition
The key insight here is that we can move forward one step at a time, and we can also jump to any index that has the same value as the current index. By keeping track of the values and their corresponding indices, we can efficiently explore all possible paths and find the minimum number of jumps.

## Algorithm

1. Initialize a hash map `occ` to store the indices of each value in the array.
2. Create a queue `q` to store the indices and the number of steps taken to reach each index.
3. Initialize a boolean array `vis` to keep track of visited indices.
4. While the queue is not empty, dequeue an index and its corresponding number of steps.
5. If the dequeued index is the last index, return the number of steps.
6. For each neighbor of the dequeued index (left and right), if it's not visited, mark it as visited and enqueue it.
7. Get the value of the dequeued index and for each duplicate index of that value, if it's not visited, mark it as visited and enqueue it.
8. Clear the list of duplicate indices for the dequeued value.
9. Repeat steps 4-8 until the queue is empty.

## Concept to Remember

* Breadth-first search (BFS) algorithm
* Hash map to store indices of values
* Keep track of visited indices to avoid duplicates

## Common Mistakes

* Forgetting to clear the list of duplicate indices for the dequeued value
* Not keeping track of visited indices, resulting in duplicate explorations
* Not using a queue to efficiently explore all possible paths

## Complexity Analysis

- Time: O(n) - reason: Each index is visited once
- Space: O(n) - reason: Hash map and boolean array use extra space

## Commented Code
```java
class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        // Store indices of each value in the hash map
        Map<Integer, List<Integer>> occ = new HashMap<>();
        for(int i=0;i<n;i++) 
            occ.computeIfAbsent(arr[i],k->new ArrayList<>()).add(i);
        
        // Queue to store indices and number of steps
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        
        // Boolean array to keep track of visited indices
        boolean[] vis = new boolean[n];
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int index = curr[0];
            int steps = curr[1];
            // If dequeued index is the last index, return steps
            if(index==n-1) return steps;
            steps++;
            // Enqueue unvisited neighbors
            if(index-1 >= 0 && !vis[index-1]){
                vis[index-1] = true;
                q.offer(new int[]{index-1,steps});
            }
            if(index+1 < n && !vis[index+1]){
                vis[index+1] = true;
                q.offer(new int[]{index+1,steps});
            }
            // Enqueue unvisited duplicates of current value
            int val = arr[index];
            for(int duplicate : occ.get(val)){
                if(vis[duplicate]) continue;
                vis[duplicate] = true;
                q.offer(new int[]{duplicate,steps});
            }
            // Clear list of duplicate indices for current value
            occ.get(val).clear();
        }
        // If no solution is found, return -1
        return -1;
    }
}
```

## Interview Tips

* Practice the problem on a whiteboard or paper to understand the algorithm clearly
* Use a queue to efficiently explore all possible paths
* Keep track of visited indices to avoid duplicates
* Use a hash map to store indices of values for efficient lookups

## Revision Checklist
- [ ] Understand the problem clearly
- [ ] Implement BFS algorithm with a queue
- [ ] Use a hash map to store indices of values
- [ ] Keep track of visited indices to avoid duplicates

## Similar Problems

* Jump Game
* Jump Game II
* Jump Game III

## Tags
`Array` `Hash Map` `Breadth-First Search` `Greedy Algorithm`
