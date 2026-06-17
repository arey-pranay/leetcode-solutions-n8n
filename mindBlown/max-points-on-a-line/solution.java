class Solution {
    public int maxPoints(int[][] points) {
        // 12/8 = 1.5
        // 3/2 = 1.5
        if(points.length < 3) return points.length;
        
        int n = points.length;
        int max = Integer.MIN_VALUE;
        int ans = Integer.MIN_VALUE;
        
        for(int i =0;i<n;i++){
            HashMap<String,Integer> hm = new HashMap<>(); 
            for(int j=i+1;j<n;j++){
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                int gcd = gcd(dx,dy);
                dx /= gcd;
                dy /= gcd;
                String slope = dy+"/"+dx; 
                hm.put(slope,hm.getOrDefault(slope,0)+1);
                max = Math.max(max, hm.get(slope));
            }
            ans = Math.max(ans,max+1);
        }
        return ans;

    }
    public int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b,a%b);
    }
}
