class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); 
        int i=0,canGo = startFuel, stops=0;
        while(canGo < target){
            while(i < stations.length && stations[i][0] <= canGo) pq.add(stations[i++][1]);
            if(pq.isEmpty()) return -1;            
            canGo += pq.poll();  // this is the limiting factor, so we can run this loop max 500 times
            stops++;
        }
        return stops;
    }
}
