class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int[][] ans = new int[k][2];
        PriorityQueue<double[]> pq = new PriorityQueue<>((a,b) -> Double.compare(a[1],b[1])); // index aur distance 
        for(int i=0;i<points.length;i++) pq.add(new double[]{i, distOf(points[i])});
        for(int i=0;i<k;i++) ans[i] = points[(int) (pq.poll()[0])];
        return ans;
    }
    public double distOf(int[] arr){
        int x = arr[0]; int y = arr[1];
        return Math.sqrt(x*x + y*y);
    }
}