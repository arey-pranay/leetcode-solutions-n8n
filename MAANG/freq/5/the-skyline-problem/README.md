# The Skyline Problem

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Array` `Hash Map` `TreeMap` `Skyline` `Building`  
**Time:** O(n log n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
      int n = buildings.length;
      int[][] events = new int[2*n][2];
      int idx = 0;
      for(int[] arr : buildings){
        events[idx++] = new int[]{arr[0],arr[2]};
        events[idx++] = new int[]{arr[1],-arr[2]};
      }
      Arrays.sort(events,(a,b)->a[0]-b[0]);
      TreeMap<Integer,Integer> tm = new TreeMap<>();
      tm.put(0,1);
      int prevMax = -1;
      List<List<Integer>> ans = new ArrayList<>();
      int i=0;
      while(i<2*n){
        int x= events[i][0];
        while(i<2*n && x == events[i][0]){
          int h = events[i][1];
          if(h>0){
            tm.put(h,tm.getOrDefault(h,0)+1);
          } else {
            h = -h;
            int count = tm.get(h)-1;
            if(count==0) tm.remove(h);
            else tm.put(h,count);
          }
          i++;
        }
        int currMax = tm.lastKey();
        if(currMax != prevMax){
           ans.add(Arrays.asList(x,currMax));
           prevMax = currMax;
        }
      }
      return ans;
    }
}

```

---

---
## Quick Revision
The problem is to find the skyline of a series of buildings, where each building is represented by its start and end times, and the height of the building. The solution is to use a TreeMap to keep track of the height of the current skyline.

## Intuition
The idea is to transform the building heights into events, where a start event has a positive height and an end event has a negative height. Then, we can use a TreeMap to keep track of the height of the current skyline.

## Algorithm
1. Create an array of events, where each event is represented by a start or end time and a height.
2. Sort the events by their start times.
3. Initialize a TreeMap to keep track of the height of the current skyline.
4. Iterate through the sorted events, adding and removing heights from the TreeMap as necessary.
5. When the height of the current skyline changes, add the current skyline to the result list.
6. Return the result list.

## Concept to Remember
* Using a TreeMap to keep track of the height of the current skyline
* Transforming the building heights into events
* Sorting the events by their start times

## Common Mistakes
* Failing to transform the building heights into events
* Failing to use a TreeMap to keep track of the height of the current skyline
* Not sorting the events by their start times

## Complexity Analysis
- Time: O(n log n) - reason: sorting the events takes O(n log n) time, and iterating through the events takes O(n) time.
- Space: O(n) - reason: we need to store the events in an array, and the TreeMap takes O(n) space.

## Commented Code
```java
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // Create an array of events
        int n = buildings.length;
        int[][] events = new int[2*n][2];
        int idx = 0;
        for(int[] arr : buildings){
            // Add a start event
            events[idx++] = new int[]{arr[0],arr[2]};
            // Add an end event
            events[idx++] = new int[]{arr[1],-arr[2]};
        }

        // Sort the events by their start times
        Arrays.sort(events,(a,b)->a[0]-b[0]);

        // Initialize a TreeMap to keep track of the height of the current skyline
        TreeMap<Integer,Integer> tm = new TreeMap<>();
        tm.put(0,1);

        // Initialize the result list
        List<List<Integer>> ans = new ArrayList<>();
        int i=0;

        // Iterate through the sorted events
        while(i<2*n){
            // Get the current event
            int x = events[i][0];
            while(i<2*n && x == events[i][0]){
                // Get the height of the current event
                int h = events[i][1];
                // If the height is positive, add it to the TreeMap
                if(h>0){
                    tm.put(h,tm.getOrDefault(h,0)+1);
                } else {
                    // If the height is negative, remove it from the TreeMap
                    h = -h;
                    int count = tm.get(h)-1;
                    if(count==0) tm.remove(h);
                    else tm.put(h,count);
                }
                i++;
            }
            // Get the height of the current skyline
            int currMax = tm.lastKey();
            // If the height of the current skyline has changed, add it to the result list
            if(currMax != prevMax){
                ans.add(Arrays.asList(x,currMax));
                prevMax = currMax;
            }
        }
        return ans;
    }
}
```

## Interview Tips
* Make sure to transform the building heights into events
* Use a TreeMap to keep track of the height of the current skyline
* Sort the events by their start times

## Revision Checklist
- [ ] Transform the building heights into events
- [ ] Use a TreeMap to keep track of the height of the current skyline
- [ ] Sort the events by their start times

## Similar Problems
* LeetCode 503: Next Greater Element II
* LeetCode 739: Daily Temperatures
* LeetCode 253: Meeting Rooms II

## Tags
`Array` `Hash Map` `TreeMap` `Skyline` `Building`
