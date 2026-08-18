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
